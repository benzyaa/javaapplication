package com.freebasicacc.dao;

import java.sql.Connection;
import com.freebasicacc.model.Customer;
import com.freebasicacc.services.DatabaseService;
import com.freebasicacc.services.SequenceService;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CollectionUtil;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.SqlUtil;
import com.freebasicacc.util.StringUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerDAOImpl extends DatabaseService implements CustomerDAO {
    private static transient Logger logger = LogManager.getLogger(CustomerDAOImpl.class);
    @Override
    public void insertCustomer(Customer customer) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            sql = new StringBuffer();        
            sql.append("INSERT INTO freeacc_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,");
            sql.append("CUSTOMER_CONTRACT,CUSTOMER_PHONE,CUSTOMER_FAX,CUSTOMER_EMAIL,REMARK,");
            sql.append("REGISTER_DATE,LAST_CONTRACT_DATE,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            SequenceService seqService = SequenceService.getInstance();
            String nextVal = seqService.nextVal("CUST_SEQ", conn);
            ps = conn.prepareStatement(sql.toString());
            
            int index = 1;
            ps.setString(index++, nextVal);
            ps.setString(index++, customer.getCustomerName());
            ps.setString(index++, customer.getCustomerAddres());
            ps.setString(index++, customer.getCustomerContract());
            ps.setString(index++, customer.getCustomerPhone());
            ps.setString(index++, customer.getCustomerFax());
            ps.setString(index++, customer.getCustomerEmail());
            ps.setString(index++, customer.getRemark());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getRegisterDate()));
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getLastContractDate()));
            ps.setString(index++, customer.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getCreateDate()));
            ps.setString(index++, customer.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getUpdateDate()));
            ps.setInt(index++, customer.getIsActive());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
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
            try{
             if(ps != null) ps.close();
             if(conn != null) conn.close();
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            ps = null;
            conn = null;
            sql = null;
        }
    }
    
      @Override
    public void updateCustomer(Customer customer) throws Exception{
         Connection conn = null;
        try{
          conn = this.getConnection();
          this.updateCustomer(customer, conn);
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
            try{
                if(conn !=null && !conn.isClosed())conn.close();
                conn = null;
            }catch(Exception ex1){
                ex1.printStackTrace();
            }
        }
    }
      
    @Override
    public void updateCustomer(Customer customer,Connection conn) throws Exception{
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn.setAutoCommit(false);
            sql = new StringBuffer();        
            
            sql.append("UPDATE freeacc_customer SET CUSTOMER_NAME = ?,CUSTOMER_ADDRESS = ?,CUSTOMER_CONTRACT = ?,");
            sql.append("CUSTOMER_PHONE = ?,CUSTOMER_FAX = ?,CUSTOMER_EMAIL = ?,REMARK = ?,REGISTER_DATE = ?,LAST_CONTRACT_DATE = ?,");
            sql.append("CREATE_BY = ?,CREATE_DATE = ?,UPDATE_BY = ?,UPDATE_DATE = ?,IS_ACTIVE = ? WHERE ");
            sql.append("CUSTOMER_ID = ?");
    
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, customer.getCustomerName());
            ps.setString(index++, customer.getCustomerAddres());
            ps.setString(index++, customer.getCustomerContract());
            ps.setString(index++, customer.getCustomerPhone());
            ps.setString(index++, customer.getCustomerFax());
            ps.setString(index++, customer.getCustomerEmail());
            ps.setString(index++, customer.getRemark());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getRegisterDate()));
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getLastContractDate()));
            ps.setString(index++, customer.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getCreateDate()));
            ps.setString(index++, customer.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getUpdateDate()));
            ps.setInt(index++, customer.getIsActive());
            ps.setString(index++,customer.getCustomerId());
            logger.info("SQL :" + ps.toString());
            ps.executeUpdate();
        }finally{
            try{
             if(ps != null) ps.close();
            }catch(Exception ex2){
                ex2.printStackTrace();
            }
            ps = null;
            sql = null;
        }
    }
    
    @Override
    public List<Customer> getCustomerByCriteria(Customer customer) throws Exception{
         Connection conn = null;
        try{
          conn = this.getConnection();
          return this.getCustomerByCriteria(customer, conn);
        }catch(Exception ex){
            logger.info("Error", ex);
            throw new Exception(AppConstants.SQL_ERROR_MESSAGE, ex);
        }finally{
            try{
                if(conn !=null && !conn.isClosed())conn.close();
                conn = null;
            }catch(Exception ex1){
                logger.info("Error", ex1);
            }
        }
    }
     
    @Override
    public List<Customer> getCustomerByCriteria(Set<String> customerId, Connection connection) throws Exception {
        return this.getCustomerByCriteria(null,customerId, connection);
    }
    
    @Override
    public List<Customer> getCustomerByCriteria(Customer customer,Connection conn)throws Exception {
        return this.getCustomerByCriteria(customer, null, conn);
    }
    
    private List<Customer> getCustomerByCriteria(Customer customer,Set<String> customerIdSet,Connection conn)throws Exception {
        List<Customer> customerSet = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        try{
            sql = new StringBuffer();        
       
            sql.append("SELECT CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_CONTRACT,CUSTOMER_PHONE,");
            sql.append("CUSTOMER_FAX,CUSTOMER_EMAIL,REMARK,REGISTER_DATE,LAST_CONTRACT_DATE,CREATE_BY,CREATE_DATE, ");
            sql.append("UPDATE_BY,UPDATE_DATE,IS_ACTIVE FROM FREEACC_CUSTOMER ");
            
            StringBuffer conditionString = new StringBuffer();
            int criteriaCount = 0;
            if(CollectionUtil.isHasElement(customerIdSet)){
                conditionString.append(SqlUtil.createInCondition("CUSTOMER_ID", customerIdSet.toArray()));
            }else if(customer !=null){
                if(StringUtil.isContainText(customer.getCustomerId())){conditionString.append("AND CUSTOMER_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerName())){conditionString.append(" AND CUSTOMER_NAME LIKE ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerAddres())){conditionString.append(" AND CUSTOMER_ADDRESS LIKE ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerPhone())){conditionString.append(" AND CUSTOMER_PHONE = ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerContract())){conditionString.append(" AND CUSTOMER_CONTRACT = ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerFax())){conditionString.append(" AND CUSTOMER_FAX = ?"); criteriaCount++;}
                if(StringUtil.isContainText(customer.getCustomerEmail())){conditionString.append(" AND CUSTOMER_EMAIL = ?"); criteriaCount++;}
                if(StringUtil.isContainText(customer.getRemark())){conditionString.append(" AND REMARK = ?");criteriaCount++;}
                if(customer.getRegisterDate()!=null && customer.getRegisterDateTo() !=null){conditionString.append(" AND REGISTER_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(customer.getRegisterDate()!=null){conditionString.append(" AND REGISTER_DATE = ?");criteriaCount++;}
                if(customer.getLastContractDate()!=null && customer.getLastContractDateTo() !=null){conditionString.append(" AND LAST_CONTRACT_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(customer.getLastContractDate()!=null){conditionString.append(" AND LAST_CONTRACT_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getCreateBy())){conditionString.append(" AND CREATE_BY = ?");criteriaCount++;}
                if(customer.getCreateDate()!=null && customer.getCreateDateTo() !=null){conditionString.append(" AND CREATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(customer.getCreateDate()!=null){conditionString.append(" AND CREATE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(customer.getUpdateBy())){conditionString.append(" AND UPDATE_BY = ?"); criteriaCount++;} 
                if(customer.getUpdateDate()!=null && customer.getUpdateDateTo() !=null){conditionString.append(" AND UPDATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(customer.getUpdateDate()!=null){conditionString.append(" AND UPDATE_DATE = ?");criteriaCount++;}
                else if(customer.getIsActive()!= -1)conditionString.append(" AND IS_ACTIVE = ?");criteriaCount++;
                conditionString.append(SqlUtil.addSqlTail(customer));
            }
            
            sql.append(conditionString.toString().replaceFirst("AND", "WHERE"));
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            if(customer !=null){
                if(StringUtil.isContainText(customer.getCustomerId()))ps.setString(index++,customer.getCustomerId());
                if(StringUtil.isContainText(customer.getCustomerName())) ps.setString(index++, "%"+customer.getCustomerName()+"%");
                if(StringUtil.isContainText(customer.getCustomerAddres())) ps.setString(index++, "%"+customer.getCustomerAddres()+"%");
                if(StringUtil.isContainText(customer.getCustomerContract())) ps.setString(index++, customer.getCustomerContract());
                if(StringUtil.isContainText(customer.getCustomerPhone())) ps.setString(index++, customer.getCustomerPhone());
                if(StringUtil.isContainText(customer.getCustomerFax())) ps.setString(index++, customer.getCustomerFax());
                if(StringUtil.isContainText(customer.getCustomerEmail())) ps.setString(index++, customer.getCustomerEmail());
                if(StringUtil.isContainText(customer.getRemark())) ps.setString(index++, customer.getRemark());
                if(customer.getRegisterDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getRegisterDate()));
                if(customer.getRegisterDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getRegisterDateTo()));
                if(customer.getLastContractDate() !=null) ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getLastContractDate()));
                if(customer.getLastContractDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getLastContractDateTo()));
                if(StringUtil.isContainText(customer.getCreateBy())) ps.setString(index++, customer.getCreateBy());
                if(customer.getCreateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getCreateDate()));
                if(customer.getCreateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getCreateDateTo()));
                if(StringUtil.isContainText(customer.getUpdateBy())) ps.setString(index++, customer.getUpdateBy());
                if(customer.getUpdateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getUpdateDate()));
                if(customer.getUpdateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(customer.getUpdateDateTo()));
                if(customer.getIsActive()!= -1)ps.setInt(index++, customer.getIsActive());
            }
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            customerSet = new ArrayList<Customer>();
            while(rs.next()){
                Customer customerResult = new Customer();
               customerResult.setCustomerId(rs.getString("CUSTOMER_ID"));
               customerResult.setCustomerName( rs.getString("CUSTOMER_NAME"));
               customerResult.setCustomerAddres( rs.getString("CUSTOMER_ADDRESS"));
               customerResult.setCustomerContract( rs.getString("CUSTOMER_CONTRACT"));
               customerResult.setCustomerPhone( rs.getString("CUSTOMER_PHONE"));
               customerResult.setCustomerFax( rs.getString("CUSTOMER_FAX"));
               customerResult.setCustomerEmail( rs.getString("CUSTOMER_EMAIL"));
               customerResult.setRemark( rs.getString("REMARK"));
               customerResult.setRegisterDate( rs.getDate("REGISTER_DATE"));
               customerResult.setLastContractDate( rs.getDate("LAST_CONTRACT_DATE"));
               customerResult.setCreateBy( rs.getString("CREATE_BY"));
               customerResult.setCreateDate( rs.getDate("CREATE_DATE"));
               customerResult.setUpdateBy( rs.getString("UPDATE_BY"));
               customerResult.setUpdateDate( rs.getDate("UPDATE_DATE"));
               customerResult.setIsActive(rs.getInt("IS_ACTIVE"));
               customerSet.add(customerResult);
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
        return customerSet;
    }

    @Override
    public int getAllRowCount() throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        Connection conn = null;
        try{
            conn = this.getConnection();
            sql = new StringBuffer();        
            sql.append("SELECT COUNT(*) AS ALL_ROW_COUNT FROM FREEACC_CUSTOMER ");
            ps = conn.prepareStatement(sql.toString());
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            return (rs.next()) ? rs.getInt("ALL_ROW_COUNT"):0;
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