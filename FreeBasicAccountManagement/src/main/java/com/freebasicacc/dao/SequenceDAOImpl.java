package com.freebasicacc.dao;

import com.freebasicacc.util.StringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SequenceDAOImpl implements SequenceDAO{
    private static transient Logger logger = LogManager.getLogger(SequenceDAOImpl.class);
    private static SequenceDAOImpl dao = null;
    private SequenceDAOImpl(){
    }
    
    public static SequenceDAOImpl getInstance(){
        dao = dao != null ? dao : new SequenceDAOImpl(); 
        return dao;
    }
    
    public static void deleteInstance(){
       dao = null;
    }
    
    @Override
    public String nextVal(String sequenceName,Connection connection)throws Exception{
        int currInt = this.getCurrentInt(sequenceName, connection);
        currInt +=1;
        String nextVal = StringUtil.rpad(String.valueOf(currInt), 10, "0");
        this.updateCurrSequence(sequenceName, nextVal, currInt, connection);
        return nextVal;
    }
    
    private int getCurrentInt(String sequenceName,Connection conn) throws Exception{
        StringBuffer sql = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
      try{
         sql = new StringBuffer();
         sql.append("SELECT SEQUENCE_CURR_NO FROM FREEACC_SEQUENCE WHERE SEQUENCE_NAME = ?");
         ps = conn.prepareStatement(sql.toString());
         int index = 1;
         ps.setString(index++, sequenceName);
         logger.info("SQL :" + ps.toString());
         rs  = ps.executeQuery();
        return rs.next() ? rs.getInt("SEQUENCE_CURR_NO") : 0;
    }finally{
          if(rs != null)rs.close();
          if(ps != null)ps.close();
          rs = null;
          ps = null;
          sql = null;
     }
    }
    
    private void updateCurrSequence(String sequenceName,String seqCurrValue,int seqCurrNo,Connection conn) throws Exception{
        StringBuffer sql = null;
        PreparedStatement ps = null;
      try{
         sql = new StringBuffer();
         sql.append(" UPDATE FREEACC_SEQUENCE SET SEQUENCE_CURR_VALUE = ?, SEQUENCE_CURR_NO = ? WHERE SEQUENCE_NAME = ?");
         
         ps = conn.prepareStatement(sql.toString());
         int index = 1;
         ps.setString(index++, seqCurrValue);
         ps.setInt(index++, seqCurrNo);
         ps.setString(index++, sequenceName);
         logger.info("SQL :" + ps.toString());
         ps.executeUpdate();
    }finally{
          if(ps != null)ps.close();
          ps = null;
          sql = null;
     }
    }

    @Override
    public void reset(String sequenceName, Connection connection) throws Exception {
        int currInt = 0;
        String nextVal = StringUtil.rpad(String.valueOf(currInt), 10, "0");
        this.updateCurrSequence(sequenceName, nextVal, currInt, connection);
    }
}