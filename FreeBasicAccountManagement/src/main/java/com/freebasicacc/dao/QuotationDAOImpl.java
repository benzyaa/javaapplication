package com.freebasicacc.dao;

import com.freebasicacc.model.Customer;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.QuotationDetail;
import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.model.Unit;
import com.freebasicacc.services.DatabaseService;
import com.freebasicacc.services.SequenceService;
import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.SqlUtil;
import com.freebasicacc.util.StringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuotationDAOImpl extends DatabaseService implements QuotationDAO{
    private static transient Logger logger = LogManager.getLogger(QuotationDAOImpl.class);
    @Override
    public void insertQuotation(QuotationHead quotation) {
        
        Connection conn = null;
        CustomerDAO customerDAO = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            SequenceService seqService = SequenceService.getInstance();
            String nextVal = seqService.nextVal("QUO_SEQ", conn);
            insertQuotationHead(quotation,nextVal,conn);
            quotation.setQuotationHeadId(nextVal);
            insertQuotationDetail(quotation,conn);
            Customer customer = quotation.getCustomer();
            customer.setIsActive(-1);
            customerDAO = new CustomerDAOImpl();
            customer = customerDAO.getCustomerByCriteria(customer).get(0);
            customer.setLastContractDate(quotation.getCreateDate());
            customer.setUpdateBy(CommonUtil.getComputerName());
            customer.setUpdateDate(quotation.getCreateDate());
            customerDAO.updateCustomer(customer,conn);
            conn.commit();
        }catch(Exception ex){
            logger.info("Error", ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (SQLException ex) {
               logger.info("Error", ex);
            }
        }
    }
    private void insertQuotationHead(QuotationHead head,String nextVal,Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();           
            sql.append("INSERT INTO freeacc_quotation_head (QUOTATION_HEAD_ID,QUOTATION_TITLE,CUSTOMER_ID,QUOTATION_DATE,DELIVERY_PLACE,");
            sql.append("LEAD_DATE,PAYMENT_TERM,TOTAL_VALUE,VAT,NET_VALUE,NET_VALUE_TEXT,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,");
            sql.append("IS_ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, nextVal);
            ps.setString(index++, head.getQuotationTitle());
            ps.setString(index++, head.getCustomer().getCustomerId());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getQuotationDate()));
            ps.setString(index++, head.getDeliveryPlace());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getLeadDate()));
            ps.setString(index++, head.getPaymentTerm());
            ps.setBigDecimal(index++, head.getTotalValue());
            ps.setBigDecimal(index++, head.getVat());
            ps.setBigDecimal(index++, head.getNetValue());
            ps.setString(index++, head.getNetValueText());
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
    private void insertQuotationDetail(QuotationHead head,Connection conn) throws Exception{
        
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            
            sql = new StringBuffer();           
            sql.append("INSERT INTO freeacc_quotation_detail(QUOTATION_HEAD_ID,QUOTATION_DETAIL_ID,ITEM_NO,MATERIAL_ID,");
            sql.append("QUANTITY, UNIT_ID, PRICE_PER_UNIT,TOTAL_PRICE,CREATE_BY,CREATE_DATE,");
            sql.append("UPDATE_BY, UPDATE_DATE,IS_ACTIVE) ");
            sql.append("VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?)");
            ps = conn.prepareStatement(sql.toString());
            
            List<QuotationDetail> detailSet = head.getQuotationDetailSet();
            int itemNo = 0;
            for(QuotationDetail detail : detailSet){
                int index = 1;
                ps.setString(index++, head.getQuotationHeadId());
                ps.setString(index++, String.valueOf(itemNo+1));
                ps.setString(index++, String.valueOf(itemNo+1));
                ps.setString(index++, detail.getMaterial().getMaterialId());
                ps.setInt(index++, detail.getQuantity());
                ps.setString(index++, detail.getUnit().getUnitId());
                ps.setBigDecimal(index++, detail.getPricePerUnit());
                ps.setBigDecimal(index++, detail.getTotalPrice());
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
    public void updateQuotation(QuotationHead quotation) {
        Connection conn = null;
         
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            updateQuotationHead(quotation,conn);
            deleteQuotationDetail(quotation,conn);
            insertQuotationDetail(quotation,conn);
            conn.commit();
        }catch(Exception ex){
           logger.info("Error", ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (SQLException ex) {
                logger.info("Error", ex);
            }
        }
    }
    
    private void updateQuotationHead(QuotationHead head, Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();           
            sql.append("UPDATE freeacc_quotation_head SET QUOTATION_TITLE = ?,CUSTOMER_ID = ?,");
            sql.append("QUOTATION_DATE = ?,DELIVERY_PLACE = ?,LEAD_DATE = ?,PAYMENT_TERM = ?,");
            sql.append("TOTAL_VALUE = ?,VAT = ?,NET_VALUE = ?,NET_VALUE_TEXT = ?,CREATE_BY = ?,");
            sql.append("CREATE_DATE = ?,UPDATE_BY = ?,UPDATE_DATE = ?,IS_ACTIVE = ? ");
            sql.append("WHERE QUOTATION_HEAD_ID = ?");
            
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getQuotationTitle());
            ps.setString(index++, head.getCustomer().getCustomerId());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getQuotationDate()));
            ps.setString(index++, head.getDeliveryPlace());
            ps.setBigDecimal(index++, head.getTotalValue());
            ps.setBigDecimal(index++, head.getVat());
            ps.setBigDecimal(index++, head.getNetValue());
            ps.setString(index++, head.getNetValueText());
            ps.setString(index++, head.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getCreateDate()));
            ps.setString(index++, head.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(head.getUpdateDate()));
            ps.setInt(index++, head.getIsActive());
            ps.setString(index++, head.getQuotationHeadId());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    }
    private void deleteQuotationDetail(QuotationHead head, Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();           
            sql.append("DELETE FROM freeacc_quotation_detail WHERE QUOTATION_HEAD_ID = ?");
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getQuotationHeadId());         
            ps.executeUpdate();
        }finally{
             if(ps != null) ps.close();
             ps = null;
             sql = null;
        }
    }

    @Override
    public List<QuotationHead> getQuotationByCriteria(QuotationHead quotation) {
        Connection conn = null;
        List<QuotationHead> quotationHeadSet = null;
        try{
            conn = this.getConnection();
            quotationHeadSet = getQuotationHeadByCriteria(quotation, conn);
            for(QuotationHead head : quotationHeadSet){
                List<QuotationDetail> quotationDetailSet = this.getQuotationDetail(head, conn);
                head.setQuotationDetailSet(quotationDetailSet);
            }
        }catch(Exception ex){
            logger.info("Error", ex);
            
        }finally{
            try {
                if(conn!=null)conn.close();
                conn = null;
            } catch (SQLException ex) {
               logger.info("Error", ex);
            }
        }
        return quotationHeadSet;
    }
    
    public List<QuotationHead> getQuotationHeadByCriteria(QuotationHead quotation,Connection conn) throws Exception{
            List<QuotationHead> quotationHeadSet = null;
            CustomerDAO customerDAO = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            StringBuffer sql = null;
        try{
            sql = new StringBuffer();        
            sql.append("SELECT QUOTATION_HEAD_ID, QUOTATION_TITLE, CUSTOMER_ID, QUOTATION_DATE,");
            sql.append("DELIVERY_PLACE,LEAD_DATE,PAYMENT_TERM,TOTAL_VALUE,VAT,NET_VALUE,NET_VALUE_TEXT,");
            sql.append("CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE FROM freeacc_quotation_head ");

            StringBuffer conditionString = new StringBuffer();
            int criteriaCount = 0;
           if(quotation !=null){
                if(StringUtil.isContainText(quotation.getQuotationHeadId())){conditionString.append("AND QUOTATION_HEAD_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(quotation.getQuotationTitle())){conditionString.append(" AND QUOTATION_TITLE LIKE ?");criteriaCount++;}
                if(quotation.getCustomer() !=null && StringUtil.isContainText(quotation.getCustomer().getCustomerId())){conditionString.append(" AND CUSTOMER_ID = ?");criteriaCount++;}
                if(quotation.getQuotationDate()!=null && quotation.getQuotationDateTo() !=null){conditionString.append(" AND QUOTATION_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(quotation.getQuotationDate()!=null){conditionString.append(" AND QUOTATION_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(quotation.getDeliveryPlace())){conditionString.append(" AND DELIVERY_PLACE LIKE ?");criteriaCount++;}
                if(quotation.getLeadDate()!=null && quotation.getLeadDateTo() !=null){conditionString.append(" AND LEAD_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(quotation.getLeadDate()!=null){conditionString.append(" AND LEAD_DATE = ?");criteriaCount++;}
                else if(StringUtil.isContainText(quotation.getPaymentTerm())){conditionString.append(" AND PAYMENT_TERM = ?");criteriaCount++;}  
                if(StringUtil.isContainText(quotation.getCreateBy())){conditionString.append(" AND CREATE_BY = ?");criteriaCount++;}
                if(quotation.getCreateDate()!=null && quotation.getCreateDateTo() !=null){conditionString.append(" AND CREATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(quotation.getCreateDate()!=null){conditionString.append(" AND CREATE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(quotation.getUpdateBy())){conditionString.append(" AND UPDATE_BY = ?"); criteriaCount++;} 
                if(quotation.getUpdateDate()!=null && quotation.getUpdateDateTo() !=null){conditionString.append(" AND UPDATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(quotation.getUpdateDate()!=null){conditionString.append(" AND UPDATE_DATE = ?");criteriaCount++;}
                conditionString.append(SqlUtil.addSqlTail(quotation));
            }

            sql.append(conditionString.toString().replaceFirst("AND", "WHERE"));
            ps = conn.prepareStatement(sql.toString());
            
            int index = 1;
            if(quotation !=null){
                if(StringUtil.isContainText(quotation.getQuotationHeadId()))ps.setString(index++,quotation.getQuotationHeadId());
                if(StringUtil.isContainText(quotation.getQuotationTitle()))ps.setString(index++,quotation.getQuotationTitle());
                if(quotation.getCustomer() !=null && StringUtil.isContainText(quotation.getCustomer().getCustomerId()))ps.setString(index++,quotation.getCustomer().getCustomerId());
                if(quotation.getQuotationDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getQuotationDate()));
                if(quotation.getQuotationDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getQuotationDateTo()));
                if(StringUtil.isContainText(quotation.getDeliveryPlace()))ps.setString(index++,quotation.getDeliveryPlace());
                if(quotation.getLeadDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getLeadDate()));
                if(quotation.getLeadDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getLeadDateTo()));
                if(StringUtil.isContainText(quotation.getPaymentTerm()))ps.setString(index++,quotation.getPaymentTerm());
                if(quotation.getCreateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getCreateDate()));
                if(quotation.getCreateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getCreateDateTo()));
                if(StringUtil.isContainText(quotation.getUpdateBy())) ps.setString(index++, quotation.getUpdateBy());
                if(quotation.getUpdateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getUpdateDate()));
                if(quotation.getUpdateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(quotation.getUpdateDateTo()));
            }
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            quotationHeadSet = new ArrayList();
            Map<QuotationHead,String> QuotationCustomerMap = new LinkedHashMap<QuotationHead, String>();
            while(rs.next()){
               QuotationHead quotationHeadResult = new QuotationHead();
               quotationHeadResult.setQuotationHeadId(rs.getString("QUOTATION_HEAD_ID"));
               quotationHeadResult.setQuotationTitle(rs.getString("QUOTATION_TITLE"));
               quotationHeadResult.setQuotationDate( rs.getDate("QUOTATION_DATE"));
               String customerId =  rs.getString("CUSTOMER_ID");
               QuotationCustomerMap.put(quotationHeadResult,customerId);
               quotationHeadResult.setDeliveryPlace( rs.getString("DELIVERY_PLACE"));
               quotationHeadResult.setLeadDate(rs.getDate("LEAD_DATE"));
               quotationHeadResult.setPaymentTerm(rs.getString("PAYMENT_TERM"));
               quotationHeadResult.setTotalValue(rs.getBigDecimal("TOTAL_VALUE"));
               quotationHeadResult.setVat(rs.getBigDecimal("VAT"));
               quotationHeadResult.setNetValue(rs.getBigDecimal("NET_VALUE"));
               quotationHeadResult.setNetValueText(rs.getString("NET_VALUE_TEXT"));
               quotationHeadResult.setCreateBy( rs.getString("CREATE_BY"));
               quotationHeadResult.setCreateDate( rs.getDate("CREATE_DATE"));
               quotationHeadResult.setUpdateBy( rs.getString("UPDATE_BY"));
               quotationHeadResult.setUpdateDate( rs.getDate("UPDATE_DATE"));
               quotationHeadResult.setIsActive(rs.getInt("IS_ACTIVE"));
               quotationHeadSet.add(quotationHeadResult);
          }
            customerDAO = new CustomerDAOImpl();
            Set<String> criteriaSet = new LinkedHashSet<String>(QuotationCustomerMap.values());
            List<Customer> customerSet = customerDAO.getCustomerByCriteria(criteriaSet, conn);
            Map<String,Customer> customerMap = new LinkedHashMap<String, Customer>();
            for(Customer c : customerSet){
                customerMap.put(c.getCustomerId(), c);
            }
            for(QuotationHead q : QuotationCustomerMap.keySet()){
                String customerId = QuotationCustomerMap.get(q);
                q.setCustomer(customerMap.get(customerId));
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
        return quotationHeadSet;
        }
    
    private List<QuotationDetail> getQuotationDetail(QuotationHead head, Connection conn) throws Exception{
            List<QuotationDetail> quotationDetailSet = null;
            MaterialDAO materialDAO = null;
            UnitDAO unitDAO = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            StringBuffer sql = null;
        try{
            sql = new StringBuffer();        
            sql.append("SELECT QUOTATION_HEAD_ID, QUOTATION_DETAIL_ID, ITEM_NO, MATERIAL_ID,");
            sql.append("QUANTITY, UNIT_ID, PRICE_PER_UNIT, TOTAL_PRICE, CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,");
            sql.append("IS_ACTIVE FROM freeacc_quotation_detail WHERE QUOTATION_HEAD_ID = ? ");
           
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, head.getQuotationHeadId());
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            quotationDetailSet = new ArrayList();
            Map<QuotationDetail,String> detailMaterialMap = new LinkedHashMap<QuotationDetail, String>();
            Map<QuotationDetail,String> detailUnitMap = new LinkedHashMap<QuotationDetail, String>();
            while(rs.next()){
               QuotationDetail quotationDetailResult = new QuotationDetail();
               quotationDetailResult.setQuotationHeadId(rs.getString("QUOTATION_HEAD_ID"));
               quotationDetailResult.setQuotationDetailId(rs.getString("QUOTATION_DETAIL_ID"));
               quotationDetailResult.setItemNo(rs.getInt("ITEM_NO"));
               
               String materialId =  rs.getString("MATERIAL_ID");
               detailMaterialMap.put(quotationDetailResult, materialId);
               String unitId =  rs.getString("UNIT_ID");
               detailUnitMap.put(quotationDetailResult, unitId);
               quotationDetailResult.setQuantity(rs.getInt("QUANTITY"));
               quotationDetailResult.setPricePerUnit(rs.getBigDecimal("PRICE_PER_UNIT"));
               quotationDetailResult.setTotalPrice(rs.getBigDecimal("TOTAL_PRICE"));
               quotationDetailResult.setCreateBy( rs.getString("CREATE_BY"));
               quotationDetailResult.setCreateDate( rs.getDate("CREATE_DATE"));
               quotationDetailResult.setUpdateBy( rs.getString("UPDATE_BY"));
               quotationDetailResult.setUpdateDate( rs.getDate("UPDATE_DATE"));
               quotationDetailResult.setIsActive(rs.getInt("IS_ACTIVE"));
               quotationDetailSet.add(quotationDetailResult);
          }
            materialDAO = new MaterialDAOImpl();
            Set<String> matCriteriaSet = new LinkedHashSet<String>(detailMaterialMap.values());
            List<Material> materialSet = materialDAO.getUnitByCriteria(matCriteriaSet, conn);
            Map<String,Material> materialMap = new LinkedHashMap<String, Material>();
            for(Material m : materialSet){
                materialMap.put(m.getMaterialId(),m);
            }
            for(QuotationDetail q : detailMaterialMap.keySet()){
                q.setMaterial(materialMap.get(detailMaterialMap.get(q)));
            }
            
            unitDAO = new UnitDAOImpl();
            Set<String> criteriaSet = new LinkedHashSet<String>(detailUnitMap.values());
            List<Unit> unitSet = unitDAO.getUnitByCriteria(criteriaSet, conn);
            Map<String,Unit> unitMap = new LinkedHashMap<String, Unit>();
            for(Unit u : unitSet){
                unitMap.put(u.getUnitId(), u);
            }
            for(QuotationDetail q : detailUnitMap.keySet()){
                q.setUnit(unitMap.get(detailUnitMap.get(q)));
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
        return quotationDetailSet;
    }
}