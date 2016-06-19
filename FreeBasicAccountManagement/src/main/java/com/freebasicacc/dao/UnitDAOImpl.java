package com.freebasicacc.dao;

import com.freebasicacc.model.Unit;
import com.freebasicacc.services.DatabaseService;
import com.freebasicacc.services.SequenceService;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CollectionUtil;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.SqlUtil;
import com.freebasicacc.util.StringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnitDAOImpl extends DatabaseService implements UnitDAO{
    private static transient Logger logger = LogManager.getLogger(UnitDAOImpl.class);
    @Override
    public void insertUnit(Unit unit) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            sql = new StringBuffer();        
            sql.append("INSERT  INTO freeacc_unit(UNIT_ID,UNIT_NAME,UNIT_DESC,CREATE_BY,CREATE_DATE,");
            sql.append("UPDATE_BY,UPDATE_DATE,IS_ACTIVE");
            sql.append(")VALUES( ?,?,?,?,?,?,?,?)");
            SequenceService seqService = SequenceService.getInstance();
            String nextVal = seqService.nextVal("UNIT_SEQ", conn);
            ps = conn.prepareStatement(sql.toString());
            
            int index = 1;
            ps.setString(index++, nextVal);
            ps.setString(index++, unit.getUnitName());
            ps.setString(index++, unit.getUnitDesc());
            ps.setString(index++, unit.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getCreateDate()));
            ps.setString(index++, unit.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getUpdateDate()));
            ps.setInt(index++, unit.getIsActive());
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
    
    public void updateUnit(Unit unit)  throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            sql = new StringBuffer();        
            sql.append("UPDATE freeacc_unit SET UNIT_NAME = ?,UNIT_DESC = ?,");
            sql.append("CREATE_BY = ?,CREATE_DATE = ?,UPDATE_BY = ?,UPDATE_DATE = ?,IS_ACTIVE = ? ");
            sql.append("WHERE UNIT_ID = ?");
            
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, unit.getUnitName());
            ps.setString(index++, unit.getUnitDesc());
            ps.setString(index++, unit.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getCreateDate()));
            ps.setString(index++, unit.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getUpdateDate()));
            ps.setInt(index++, unit.getIsActive());
            ps.setString(index++, unit.getUnitId());
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
    public List<Unit> getUnitAll()  throws Exception{
        return this.getUnitByCriteria(null);
    }

    @Override
    public List<Unit> getUnitByCriteria(Unit unit) throws Exception{
         Connection conn = null;
        try{
          conn = this.getConnection();
          return this.getUnitByCriteria(unit,null,conn);
        }catch(Exception ex){
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
    public List<Unit> getUnitByCriteria(Set<String> unitSet, Connection conn) throws Exception {
            return this.getUnitByCriteria(null,unitSet,conn);
    }
    
    @Override
    public List<Unit> getUnitByCriteria(Unit unit, Connection conn) throws Exception{
         return  this.getUnitByCriteria(unit,null,conn);
    }

    private List<Unit> getUnitByCriteria(Unit unit,Set<String> unitSetCriteria, Connection conn) throws Exception{
        List<Unit> unitSet = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            sql = new StringBuffer();        
            sql.append("SELECT UNIT_ID,UNIT_NAME,UNIT_DESC,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,");
            sql.append("IS_ACTIVE FROM freeacc_unit ");
            
            StringBuffer conditionString = new StringBuffer();
            int criteriaCount = 0;
            if(CollectionUtil.isHasElement(unitSetCriteria)){
                conditionString.append(SqlUtil.createInCondition("UNIT_ID", unitSetCriteria.toArray()));
            }
            else if(unit !=null){
                if(StringUtil.isContainText(unit.getUnitId())){conditionString.append("AND UNIT_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(unit.getUnitName())){conditionString.append(" AND UNIT_NAME LIKE ?");criteriaCount++;}
                if(StringUtil.isContainText(unit.getUnitDesc())){conditionString.append(" AND UNIT_DESC LIKE ?");criteriaCount++;}
                if(StringUtil.isContainText(unit.getCreateBy())){conditionString.append(" AND CREATE_BY = ?");criteriaCount++;}
                if(unit.getCreateDate()!=null && unit.getCreateDateTo() !=null){conditionString.append(" AND CREATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(unit.getCreateDate()!=null){conditionString.append(" AND CREATE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(unit.getUpdateBy())){conditionString.append(" AND UPDATE_BY = ?"); criteriaCount++;} 
                if(unit.getUpdateDate()!=null && unit.getUpdateDateTo() !=null){conditionString.append(" AND UPDATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(unit.getUpdateDate()!=null){conditionString.append(" AND UPDATE_DATE = ?");criteriaCount++;}
                SqlUtil.addSqlTail(unit);
            }
            
            if(!CollectionUtil.isHasElement(unitSetCriteria) && unit !=null){
                conditionString.append(" AND IS_ACTIVE = ?");criteriaCount++;
            }
            sql.append(conditionString.toString().replaceFirst("AND", "WHERE"));
            ps = conn.prepareStatement(sql.toString());
              int index = 1;
            if(unit !=null){
                if(StringUtil.isContainText(unit.getUnitId()))ps.setString(index++,unit.getUnitId());
                if(StringUtil.isContainText(unit.getUnitName())) ps.setString(index++, "%"+unit.getUnitName()+"%");
                if(StringUtil.isContainText(unit.getUnitDesc())) ps.setString(index++, "%"+unit.getUnitDesc()+"%");
                if(StringUtil.isContainText(unit.getCreateBy())) ps.setString(index++, unit.getCreateBy());
                if(unit.getCreateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getCreateDate()));
                if(unit.getCreateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getCreateDateTo()));
                if(StringUtil.isContainText(unit.getUpdateBy())) ps.setString(index++, unit.getUpdateBy());
                if(unit.getUpdateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getUpdateDate()));
                if(unit.getUpdateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(unit.getUpdateDateTo()));
            }
            if(!CollectionUtil.isHasElement(unitSetCriteria) && unit !=null){
                ps.setInt(index++, unit.getIsActive());
                conditionString.append(SqlUtil.addSqlTail(unit));
            }
            
            logger.info("SQL :" + ps.toString());
            
            rs = ps.executeQuery();
            unitSet = new ArrayList();
            while(rs.next()){
               Unit unitResult = new Unit();
               unitResult.setUnitId(rs.getString("UNIT_ID"));
               unitResult.setUnitName( rs.getString("UNIT_NAME"));
               unitResult.setUnitDesc(rs.getString("UNIT_DESC"));
               unitResult.setCreateBy(rs.getString("CREATE_BY"));
               unitResult.setCreateDate(rs.getDate("CREATE_DATE"));
               unitResult.setUpdateBy(rs.getString("UPDATE_BY"));
               unitResult.setUpdateDate(rs.getDate("UPDATE_DATE"));
               unitResult.setIsActive(rs.getInt("IS_ACTIVE"));
               unitSet.add(unitResult);
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
        return unitSet;
    }
}