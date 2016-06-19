/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.dao;

import com.freebasicacc.model.Material;
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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author benzyaa
 */
public class MaterialDAOImpl extends DatabaseService implements MaterialDAO{
    private static transient Logger logger = LogManager.getLogger(MaterialDAOImpl.class);
    @Override
    public void insertMaterial(Material material) throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            sql = new StringBuffer();   
            
            sql.append("INSERT INTO freeacc_material(MATERIAL_ID,MATERIAL_NAME, MATERIAL_DESC,MATERIAL_PRICE,");
            sql.append("UNIT_ID,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE");
            sql.append(")VALUES (?,?,?,?,?,?,?,?,?,?)");
    
            SequenceService seqService = SequenceService.getInstance();
            String nextVal = seqService.nextVal("MAT_SEQ", conn);
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, nextVal);
            ps.setString(index++, material.getMaterialName());
            ps.setString(index++, material.getMaterialDesc());
            ps.setBigDecimal(index++, material.getMaterialPrice());
            Unit materialUnit = material.getUnit();
            ps.setString(index++, materialUnit.getUnitId());
            ps.setString(index++, material.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getCreateDate()));
            ps.setString(index++, material.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getUpdateDate()));
            ps.setInt(index++, material.getIsActive());
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
    public void updateMaterial(Material material) throws Exception{
         Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = null;
        try{
            conn = this.getConnection();
            conn.setAutoCommit(false);
            sql = new StringBuffer();
    
            sql.append("UPDATE freeacc_material SET MATERIAL_NAME = ?,MATERIAL_DESC = ?,MATERIAL_PRICE = ?,");
            sql.append("UNIT_ID = ?,CREATE_BY = ?,CREATE_DATE = ?,UPDATE_BY = ?,UPDATE_DATE = ?,IS_ACTIVE = ? WHERE ");
            sql.append("MATERIAL_ID = ?");
            
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            ps.setString(index++, material.getMaterialName());
            ps.setString(index++, material.getMaterialDesc());
            ps.setBigDecimal(index++, material.getMaterialPrice());
            Unit materialUnit = material.getUnit();
            ps.setString(index++, materialUnit.getUnitId());
            ps.setString(index++, material.getCreateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getCreateDate()));
            ps.setString(index++, material.getUpdateBy());
            ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getUpdateDate()));
            ps.setInt(index++, material.getIsActive());
            ps.setString(index++, material.getMaterialId());
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
    public List<Material> getMaterialAll() throws Exception{
        return this.getMaterialByCriteria(null);
    }

    @Override
    public List<Material> getMaterialByCriteria(Material material)throws Exception {
         Connection conn = null;
        try{
          conn = this.getConnection();
          return this.getMaterialByCriteria(material, conn);
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
    public List<Material> getUnitByCriteria(Set<String> materialId, Connection conn) throws Exception {
        return this.getMaterialByCriteria(null, materialId, conn);
    }

    @Override
    public List<Material> getMaterialByCriteria(Material material, Connection conn) throws Exception {
        return this.getMaterialByCriteria(material, null, conn);
    }
    
    private List<Material> getMaterialByCriteria(Material material,Set<String> materialIdCri, Connection conn) throws Exception {
        List<Material> materialList = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuffer sql = null;
        UnitDAO unitDAO = null;
        try{
            conn = this.getConnection();
            sql = new StringBuffer();
            sql.append("SELECT MATERIAL_ID,MATERIAL_NAME, MATERIAL_DESC, MATERIAL_PRICE,");
            sql.append("UNIT_ID, CREATE_BY,CREATE_DATE,UPDATE_BY, UPDATE_DATE,IS_ACTIVE FROM freeacc_material ");
        
            StringBuffer conditionString = new StringBuffer();
            int criteriaCount = 0;
            if(CollectionUtil.isHasElement(materialIdCri)){
               conditionString.append(SqlUtil.createInCondition("MATERIAL_ID", materialIdCri.toArray()));
            }else if(material !=null){
                if(StringUtil.isContainText(material.getMaterialId())){conditionString.append("AND MATERIAL_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(material.getMaterialName())){conditionString.append(" AND MATERIAL_NAME LIKE ?");criteriaCount++;}
                if(StringUtil.isContainText(material.getMaterialDesc())){conditionString.append(" AND MATERIAL_DESC LIKE ?");criteriaCount++;}
                if(material.getMaterialPrice()!=null && material.getMaterialPriceTo() !=null){conditionString.append(" AND MATERIAL_PRICE BETWEEN ? AND ?");criteriaCount++;}
                else if(material.getMaterialPrice()!=null){conditionString.append(" AND MATERIAL_PRICE = ?");criteriaCount++;}
                if(material.getUnit()!=null && StringUtil.isContainText(material.getUnit().getUnitId())){conditionString.append(" AND UNIT_ID = ?");criteriaCount++;}
                if(StringUtil.isContainText(material.getCreateBy())){conditionString.append(" AND CREATE_BY = ?");criteriaCount++;}
                if(material.getCreateDate()!=null && material.getCreateDateTo() !=null){conditionString.append(" AND CREATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(material.getCreateDate()!=null){conditionString.append(" AND CREATE_DATE = ?");criteriaCount++;}
                if(StringUtil.isContainText(material.getUpdateBy())){conditionString.append(" AND UPDATE_BY = ?"); criteriaCount++;} 
                if(material.getUpdateDate()!=null && material.getUpdateDateTo() !=null){conditionString.append(" AND UPDATE_DATE BETWEEN ? AND ?");criteriaCount++;}
                else if(material.getUpdateDate()!=null){conditionString.append(" AND UPDATE_DATE = ?");criteriaCount++;}
                conditionString.append(" AND IS_ACTIVE = ?");criteriaCount++;
                conditionString.append(SqlUtil.addSqlTail(material));
            }
                
            sql.append(conditionString.toString().replaceFirst("AND", "WHERE"));
            ps = conn.prepareStatement(sql.toString());
              int index = 1;
            if(material !=null){
                if(StringUtil.isContainText(material.getMaterialId()))ps.setString(index++,material.getMaterialId());
                if(StringUtil.isContainText(material.getMaterialName())) ps.setString(index++, "%"+material.getMaterialName()+"%");
                if(StringUtil.isContainText(material.getMaterialDesc())) ps.setString(index++, "%"+material.getMaterialDesc()+"%");
                if(material.getMaterialPrice()!=null) ps.setBigDecimal(index++,material.getMaterialPrice());
                if(material.getMaterialPriceTo()!=null) ps.setBigDecimal(index++,material.getMaterialPriceTo());
                if(material.getUnit()!=null && StringUtil.isContainText(material.getUnit().getUnitId())) ps.setString(index++,material.getUnit().getUnitId());
                if(StringUtil.isContainText(material.getCreateBy())) ps.setString(index++, material.getCreateBy());
                if(material.getCreateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getCreateDate()));
                if(material.getCreateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getCreateDateTo()));
                if(StringUtil.isContainText(material.getUpdateBy())) ps.setString(index++, material.getUpdateBy());
                if(material.getUpdateDate() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getUpdateDate()));
                if(material.getUpdateDateTo() !=null)ps.setTimestamp(index++, DateUtil.convertDateUtilToTimeStamp(material.getUpdateDateTo()));
                ps.setInt(index++, material.getIsActive());
                //conditionString.append(SqlUtil.addSqlTail(material));
            }
            logger.info("SQL :" + ps.toString());
            rs = ps.executeQuery();
            materialList = new ArrayList();
            Map<Material,String> materialUnitMap = new LinkedHashMap<Material, String>();
            while(rs.next()){
               Material materialResult = new Material();
               materialResult.setMaterialId(rs.getString("MATERIAL_ID"));
               materialResult.setMaterialName(rs.getString("MATERIAL_NAME"));
               materialResult.setMaterialDesc(rs.getString("MATERIAL_DESC"));
               materialResult.setMaterialPrice(rs.getBigDecimal("MATERIAL_PRICE"));
               String unitId = rs.getString("UNIT_ID");
               materialUnitMap.put(materialResult, unitId);
               materialResult.setCreateBy(rs.getString("CREATE_BY"));
               materialResult.setCreateDate(rs.getDate("CREATE_DATE"));
               materialResult.setUpdateBy(rs.getString("UPDATE_BY"));
               materialResult.setUpdateDate(rs.getDate("UPDATE_DATE"));
               materialResult.setIsActive(rs.getInt("IS_ACTIVE"));
               materialList.add(materialResult);
            }
            unitDAO = new UnitDAOImpl();
            Set<String> criteriaSet = new LinkedHashSet<String>(materialUnitMap.values());
            List<Unit> unitSet = unitDAO.getUnitByCriteria(criteriaSet, conn);
            Map<String,Unit> unitMap = new LinkedHashMap<String, Unit>();
            for(Unit u : unitSet){
                unitMap.put(u.getUnitId(), u);
            }
            for(Material m : materialUnitMap.keySet()){
                m.setUnit(unitMap.get(materialUnitMap.get(m)));
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
            unitDAO = null;
        }
        return materialList;
    }
}