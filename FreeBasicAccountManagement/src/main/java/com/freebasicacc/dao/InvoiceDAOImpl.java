package com.freebasicacc.dao;


import com.freebasicacc.model.Customer;
import com.freebasicacc.model.InvoiceDetail;
import com.freebasicacc.model.InvoiceHead;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.Unit;
import com.freebasicacc.services.DatabaseService;
import com.freebasicacc.services.SequenceService;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.SqlUtil;
import com.freebasicacc.util.StringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvoiceDAOImpl extends DatabaseService implements InvoiceDAO{
    private static transient Logger logger = LogManager.getLogger(InvoiceDAOImpl.class);
    @Override
    public void insertInvoice(InvoiceHead invoice) throws Exception{
        Connection conn = null;
        CustomerDAO customerDAO = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            SequenceService seqService = SequenceService.getInstance();
            performResetInvoiceSeq(seqService, conn);
            String nextVal = seqService.nextVal("INV_SEQ", conn);
            nextVal = this.generateInvoiceNo(nextVal);
            insertInvoiceHead(invoice,nextVal,conn);
            invoice.setInvoiceHeadId(nextVal);
            insertInvoiceDetail(invoice,conn);
            Customer customer = invoice.getCustomer();
            customer.setIsActive(-1);
            customerDAO = new CustomerDAOImpl();
            customer = customerDAO.getCustomerByCriteria(customer).get(0);
            customer.setLastContractDate(invoice.getCreateDate());
            customer.setUpdateBy(CommonUtil.getComputerName());
            customer.setUpdateDate(invoice.getCreateDate());
            customerDAO.updateCustomer(customer,conn);
            conn.commit();
        }catch(Exception ex){
            try {
                conn.rollback();
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
            logger.info("Error", ex);
            throw new Exception(AppConstants.SQL_ERROR_MESSAGE, ex);
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (Exception ex) {
                logger.info("Error", ex);
            }
            customerDAO = null;
        }
    }
    
    private void performResetInvoiceSeq(SequenceService seqService,Connection conn) throws Exception{
        String MaxInvoiceId = this.getMaxInvoiceId(conn);
        if(!StringUtil.isContainText(MaxInvoiceId))return;
            String monthOfMaxInvoiceIdStr = MaxInvoiceId.substring(3,4);
            Calendar calendar = Calendar.getInstance(new Locale("th","TH"));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM", new Locale("th","TH"));
            String dateText = dateFormat.format(calendar.getTime());
            int monthOfMaxInvoiceId = Integer.parseInt(monthOfMaxInvoiceIdStr);
            int dateTextId = Integer.parseInt(dateText);
            if((monthOfMaxInvoiceId >= dateTextId)) return;
            seqService.reset("INV_SEQ", conn);
    }
    
    private String generateInvoiceNo(String nextVal){
        int invoiceCount = Integer.parseInt(nextVal);
        Calendar calendar = Calendar.getInstance(new Locale("th","TH"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM", new Locale("th","TH"));
        return dateFormat.format(calendar.getTime())+StringUtil.rpad(String.valueOf(invoiceCount), 3, "0");
    }
    
    private void insertInvoiceHead(InvoiceHead head,String nextVal,Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();              
            sql.append("INSERT INTO freeacc_invoice_head (INVOICE_HEAD_ID, CUSTOMER_ID,PO_NUMBER, TOTAL_AMOUNT, DISCOUNT,VAT,");
            sql.append("NET_AMOUNT, NET_AMOUNT_TEXT,  PAYMENT_TYPE, INVOICE_DUE_DATE,");
            sql.append("PAYMENT_DUE_DATE, CREATE_BY,CREATE_DATE,UPDATE_BY, UPDATE_DATE,IS_ACTIVE )");
            sql.append("VALUES (?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?, ? )");
            
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, nextVal);
            ps.setString(index++, head.getCustomer().getCustomerId());
            ps.setString(index++, head.getPoNumber());
            ps.setBigDecimal(index++, head.getTotalAmount());
            ps.setBigDecimal(index++, head.getDiscount());
            ps.setBigDecimal(index++, head.getVat());
            ps.setBigDecimal(index++, head.getNetAmount());
            ps.setString(index++, head.getNetAmountText());
            ps.setString(index++, head.getPaymentType());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getInvoiceDueDate()));
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getPaymentDueDate()));
            ps.setString(index++, head.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getCreateDate()));
            ps.setString(index++, head.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getUpdateDate()));
            ps.setInt(index++, head.getIsActive());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    } 
    private void insertInvoiceDetail(InvoiceHead head,Connection conn) throws Exception{
        
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{     
            sql = new StringBuffer();           
            sql.append("INSERT INTO freeacc_invoice_detail(INVOICE_HEAD_ID,INVOICE_DETAIL_ID,ITEM_NO,MATERIAL_ID,");
            sql.append("PRICE_PER_UNIT,UNIT_ID,QUANTITY,TOTAL_VALUE,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE)");
            sql.append("VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps = conn.prepareStatement(sql.toString());
            
            List<InvoiceDetail> detailSet = head.getInvoiceDetailSet();
            int itemNo = 0;
            for(InvoiceDetail detail : detailSet){
                int index = 1;
                ps.setString(index++, head.getInvoiceHeadId());
                ps.setString(index++, String.valueOf(itemNo));
                ps.setString(index++, String.valueOf(itemNo));
                ps.setString(index++, detail.getMaterial().getMaterialId());
                ps.setBigDecimal(index++, detail.getPricePerUnit());
                ps.setString(index++, detail.getUnit().getUnitId());
                ps.setInt(index++, detail.getQuantity());
                ps.setBigDecimal(index++, detail.getTotalValue());
                ps.setString(index++, detail.getCreateBy());
                ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(detail.getCreateDate()));
                ps.setString(index++, detail.getUpdateBy());
                ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(detail.getUpdateDate()));
                ps.setInt(index++, detail.getIsActive());
                ps.addBatch();
                logger.info("SQL :" + ps.toString());
                itemNo++;
            }
            ps.executeBatch();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    }

    @Override
    public void updateInvoice(InvoiceHead invoice) throws Exception{
          Connection conn = null;
         
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            updateInvoiceHead(invoice,conn);
            deleteInvoiceDetail(invoice,conn);
            insertInvoiceDetail(invoice,conn);
            conn.commit();
        }catch(Exception ex){
  
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            logger.info("Error", ex);
            throw new Exception(AppConstants.SQL_ERROR_MESSAGE, ex);
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (SQLException ex) {
                logger.info("Error", ex);
            }
        }
    }
 
    private void updateInvoiceHead(InvoiceHead head, Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();           
            
            sql.append("UPDATE freeacc_invoice_head SET CUSTOMER_ID = ?,PO_NUMBER = ?,");
            sql.append("TOTAL_AMOUNT = ?,DISCOUNT = ?,VAT = ?, NET_AMOUNT = ?,NET_AMOUNT_TEXT = ?,");
            sql.append("PAYMENT_TYPE = ?,INVOICE_DUE_DATE = ?,PAYMENT_DUE_DATE = ?, CREATE_BY = ?,");
            sql.append("CREATE_DATE = ?,  UPDATE_BY = ?,UPDATE_DATE = ?,IS_ACTIVE = ? ");
            sql.append("WHERE INVOICE_HEAD_ID = ?");
    
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getCustomer().getCustomerId());
            ps.setString(index++, head.getPoNumber());
            ps.setBigDecimal(index++, head.getTotalAmount());
            ps.setBigDecimal(index++, head.getDiscount());
            ps.setBigDecimal(index++, head.getVat());
            ps.setBigDecimal(index++, head.getNetAmount());
            ps.setString(index++, head.getNetAmountText());
            ps.setString(index++, head.getPaymentType());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getInvoiceDueDate()));
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getPaymentDueDate()));
            ps.setString(index++, head.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getCreateDate()));
            ps.setString(index++, head.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getUpdateDate()));
            ps.setInt(index++, head.getIsActive());
            ps.setString(index++, head.getInvoiceHeadId());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    }
    
    private void deleteInvoiceDetail(InvoiceHead head, Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();           
            sql.append("DELETE FROM FREEACC_INVOICE_DETAIL WHERE INVOICE_DETAIL_ID = ?");
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getInvoiceHeadId());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    }

    @Override
    public List<InvoiceHead> getInvoiceByCriteria(InvoiceHead invoice) throws Exception{
          Connection conn = null;
          List<InvoiceHead> invoiceHeadSet = null;
        try{
            conn = this.getConnection();
            invoiceHeadSet = getInvoiceHeadByCriteria(invoice, conn);
            for(InvoiceHead head : invoiceHeadSet){
                List<InvoiceDetail> invoiceDetailSet = this.getInvoiceDetail(head, conn);
                head.setInvoiceDetailSet(invoiceDetailSet);
            }
        }catch(Exception ex){
            logger.info("Error", ex);
            throw new Exception(AppConstants.SQL_ERROR_MESSAGE, ex);
            
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (SQLException ex) {
               logger.info("Error", ex);
            }
        }
        return invoiceHeadSet;
    }
    
    private List<InvoiceHead> getInvoiceHeadByCriteria(InvoiceHead invoice,Connection conn) throws Exception{
            List<InvoiceHead> invoiceHeadSet = null;
            CustomerDAO customerDAO = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            StringBuffer sql = null;
        try{
            sql = new StringBuffer();        
            
            sql.append("SELECT INVOICE_HEAD_ID,CUSTOMER_ID,PO_NUMBER, TOTAL_AMOUNT, DISCOUNT,");
            sql.append("VAT, NET_AMOUNT,NET_AMOUNT_TEXT,  PAYMENT_TYPE,INVOICE_DUE_DATE,PAYMENT_DUE_DATE,");
            sql.append("CREATE_BY, CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE FROM FREEACC_INVOICE_HEAD ");
            
            StringBuffer conditionString = new StringBuffer();
            int criteriaCount = 0;
           if(invoice !=null){
                if(StringUtil.isContainText(invoice.getInvoiceHeadId())){conditionString.append("AND INVOICE_HEAD_ID = ?");criteriaCount++;}
                if(invoice.getCustomer() !=null ){conditionString.append(" AND CUSTOMER_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(invoice.getPoNumber())){conditionString.append(" AND PO_NUMBER = ?");criteriaCount++;}
                if(StringUtil.isContainText(invoice.getPaymentType())){conditionString.append(" AND PAYMENT_TYPE = ?");criteriaCount++;}     
                if(invoice.getInvoiceDueDate()!=null && invoice.getInvoiceDueDateTo() !=null){conditionString.append(" AND INVOICE_DUE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(invoice.getInvoiceDueDate()!=null){conditionString.append(" AND INVOICE_DUE_DATE = ?");criteriaCount++;}
                if(invoice.getPaymentDueDate()!=null && invoice.getPaymentDueDateTo() !=null){conditionString.append(" AND PAYMENT_DUE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(invoice.getPaymentDueDate()!=null){conditionString.append(" AND PAYMENT_DUE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(invoice.getCreateBy())){conditionString.append(" AND CREATE_BY = ?");criteriaCount++;}
                if(invoice.getCreateDate()!=null && invoice.getCreateDateTo() !=null){conditionString.append(" AND CREATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(invoice.getCreateDate()!=null){conditionString.append(" AND CREATE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(invoice.getUpdateBy())){conditionString.append(" AND UPDATE_BY = ?"); criteriaCount++;} 
                if(invoice.getUpdateDate()!=null && invoice.getUpdateDateTo() !=null){conditionString.append(" AND UPDATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(invoice.getUpdateDate()!=null){conditionString.append(" AND UPDATE_DATE = ?");criteriaCount++;}
                conditionString.append(SqlUtil.addSqlTail(invoice));
            }

            sql.append(conditionString.toString().replaceFirst("AND", "WHERE"));
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            if(invoice !=null){
                if(StringUtil.isContainText(invoice.getInvoiceHeadId()))ps.setString(index++,invoice.getInvoiceHeadId());
                if(invoice.getCustomer() !=null )ps.setString(index++,invoice.getCustomer().getCustomerId());
                if(StringUtil.isContainText(invoice.getPoNumber()))ps.setString(index++,invoice.getPoNumber());
                if(StringUtil.isContainText(invoice.getPaymentType()))ps.setString(index++,invoice.getPaymentType());                
                if(invoice.getInvoiceDueDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getInvoiceDueDate()));
                if(invoice.getInvoiceDueDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getInvoiceDueDateTo()));
                if(invoice.getPaymentDueDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getPaymentDueDate()));
                if(invoice.getPaymentDueDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getPaymentDueDateTo()));
                if(invoice.getCreateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getCreateDate()));
                if(invoice.getCreateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getCreateDateTo()));
                if(StringUtil.isContainText(invoice.getUpdateBy())) ps.setString(index++, invoice.getUpdateBy());
                if(invoice.getUpdateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getUpdateDate()));
                if(invoice.getUpdateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(invoice.getUpdateDateTo()));    
            }
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            invoiceHeadSet = new ArrayList<InvoiceHead>();
            Map<InvoiceHead,String> InvoiceCustomerMap = new LinkedHashMap<InvoiceHead, String>();
            while(rs.next()){
               InvoiceHead invoiceHeadResult = new InvoiceHead();
               invoiceHeadResult.setInvoiceHeadId(rs.getString("INVOICE_HEAD_ID"));
               String customerId =  rs.getString("CUSTOMER_ID");
               InvoiceCustomerMap.put(invoiceHeadResult,customerId);
               invoiceHeadResult.setPoNumber(rs.getString("PO_NUMBER"));
               invoiceHeadResult.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
               invoiceHeadResult.setDiscount(rs.getBigDecimal("DISCOUNT"));
               invoiceHeadResult.setVat(rs.getBigDecimal("VAT"));
               invoiceHeadResult.setNetAmount(rs.getBigDecimal("NET_AMOUNT"));
               invoiceHeadResult.setNetAmountText(rs.getString("NET_AMOUNT_TEXT"));
               invoiceHeadResult.setPaymentType(rs.getString("PAYMENT_TYPE"));
               invoiceHeadResult.setInvoiceDueDate(rs.getDate("INVOICE_DUE_DATE"));
               invoiceHeadResult.setPaymentDueDate(rs.getDate("PAYMENT_DUE_DATE"));
               invoiceHeadResult.setCreateBy(rs.getString("CREATE_BY"));
               invoiceHeadResult.setCreateDate(rs.getDate("CREATE_DATE"));
               invoiceHeadResult.setUpdateBy(rs.getString("UPDATE_BY"));
               invoiceHeadResult.setUpdateDate(rs.getDate("UPDATE_DATE"));
               invoiceHeadResult.setIsActive(rs.getInt("IS_ACTIVE"));
               invoiceHeadSet.add(invoiceHeadResult);
          }
            customerDAO = new CustomerDAOImpl();
            Set<String> criteriaSet = new LinkedHashSet<String>(InvoiceCustomerMap.values());
            List<Customer> customerSet = customerDAO.getCustomerByCriteria(criteriaSet, conn);
            Map<String,Customer> customerMap = new LinkedHashMap<String, Customer>();
            for(Customer c : customerSet){
                customerMap.put(c.getCustomerId(), c);
            }
            for(InvoiceHead in : InvoiceCustomerMap.keySet()){
                in.setCustomer(customerMap.get(InvoiceCustomerMap.get(in)));
            }
        }finally{
            try{
             if(rs != null) rs.close();
             if(ps != null) ps.close();
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            rs = null;
            ps = null;
            sql = null;
        }
        return invoiceHeadSet;
     }
    
     private List<InvoiceDetail> getInvoiceDetail(InvoiceHead head, Connection conn) throws Exception{
            List<InvoiceDetail> invoiceDetailSet = null;
            MaterialDAO materialDAO = null;
            UnitDAO unitDAO = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            StringBuffer sql = null;
        try{
            sql = new StringBuffer();             
            sql.append("SELECT INVOICE_HEAD_ID,INVOICE_DETAIL_ID,ITEM_NO,MATERIAL_ID,PRICE_PER_UNIT,");
            sql.append("UNIT_ID,QUANTITY,TOTAL_VALUE, CREATE_BY, CREATE_DATE,");
            sql.append("UPDATE_BY,UPDATE_DATE,IS_ACTIVE FROM freeacc_invoice_detail WHERE INVOICE_HEAD_ID = ? ");
        
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getInvoiceHeadId());
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            invoiceDetailSet = new ArrayList();
            Map<InvoiceDetail,String> detailMaterialMap = new LinkedHashMap<InvoiceDetail, String>();
            Map<InvoiceDetail,String> detailUnitMap = new LinkedHashMap<InvoiceDetail, String>();
            while(rs.next()){
               InvoiceDetail invoiceDetailResult = new InvoiceDetail();
               invoiceDetailResult.setInvoiceHeadId(rs.getString("INVOICE_HEAD_ID"));
               invoiceDetailResult.setInvoiceDetailId(rs.getString("INVOICE_DETAIL_ID"));
               invoiceDetailResult.setItemNo(rs.getInt("ITEM_NO"));
               
               String materialId =  rs.getString("MATERIAL_ID");
               detailMaterialMap.put(invoiceDetailResult, materialId);
               invoiceDetailResult.setPricePerUnit(rs.getBigDecimal("PRICE_PER_UNIT"));
               String unitId =  rs.getString("UNIT_ID");
               detailUnitMap.put(invoiceDetailResult, unitId);
               invoiceDetailResult.setQuantity(rs.getInt("QUANTITY"));
              
               invoiceDetailResult.setTotalValue(rs.getBigDecimal("TOTAL_VALUE"));
               invoiceDetailResult.setCreateBy( rs.getString("CREATE_BY"));
               invoiceDetailResult.setCreateDate( rs.getDate("CREATE_DATE"));
               invoiceDetailResult.setUpdateBy( rs.getString("UPDATE_BY"));
               invoiceDetailResult.setUpdateDate( rs.getDate("UPDATE_DATE"));
               invoiceDetailResult.setIsActive(rs.getInt("IS_ACTIVE"));
               invoiceDetailSet.add(invoiceDetailResult);
          }
            materialDAO = new MaterialDAOImpl();
            Set<String> matCriteriaSet = new LinkedHashSet<String>(detailMaterialMap.values());
            List<Material> materialSet = materialDAO.getUnitByCriteria(matCriteriaSet, conn);
            Map<String,Material> materialMap = new LinkedHashMap<String, Material>();
            for(Material m : materialSet){
                materialMap.put(m.getMaterialId(),m);
            }
            for(InvoiceDetail d : detailMaterialMap.keySet()){
                d.setMaterial(materialMap.get(detailMaterialMap.get(d)));
            }
            
            unitDAO = new UnitDAOImpl();
            Set<String> criteriaSet = new LinkedHashSet<String>(detailUnitMap.values());
            List<Unit> unitSet = unitDAO.getUnitByCriteria(criteriaSet, conn);
            Map<String,Unit> unitMap = new LinkedHashMap<String, Unit>();
            for(Unit u : unitSet){
                unitMap.put(u.getUnitId(), u);
            }
            for(InvoiceDetail d : detailUnitMap.keySet()){
                d.setUnit(unitMap.get(detailUnitMap.get(d)));
            }
            
        }finally{
            try{
             if(rs != null) rs.close();
             if(ps != null) ps.close();
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            rs = null;
            ps = null;
            sql = null;
        }
        return invoiceDetailSet;
    }
     
     public synchronized String getMaxInvoiceId(Connection conn) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = null;
       
        try{
            sql = new StringBuffer();        
            sql.append("SELECT MAX(INVOICE_HEAD_ID) AS MAX_INVOICE_ID FROM FREEACC_INVOICE_HEAD");
            ps = conn.prepareStatement(sql.toString());
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            return (rs.next()) ? rs.getString("MAX_INVOICE_ID"):"";
        }catch(Exception ex){
            logger.info("Error", ex);
            throw new Exception(AppConstants.SQL_ERROR_MESSAGE, ex);
        }finally{
            try{
             if(ps != null) ps.close();
             if(rs != null) rs.close();
             
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            rs = null;
            ps = null;
        }
    }
}