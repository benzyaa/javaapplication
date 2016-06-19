package com.freebasicacc.services;

import com.freebasicacc.util.FileUtil;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseService {
    
   public Connection getConnection()throws Exception{ 
       DriverManager.registerDriver(new com.mysql.jdbc.Driver());
       //DriverManager.registerDriver(new org.sqlite.JDBC());
       Connection conn = DriverManager.getConnection(this.getJDBCConnectionString());
       conn.setAutoCommit(false);
       return conn;
   }
   
   public String getJDBCConnectionString() throws Exception{
       //return "jdbc:sqlite:"+ FileUtil.getCurrentJarPath()+"db/FreebasicAcc.s3db";
       Properties prop = this.getJDBCConnectionProperties();
       String connectionStringtemplate = "jdbc:mysql://%s/%s?user=%s&password=%s";
       prop.load(new FileInputStream(FileUtil.getCurrentJarPath()+"/config/jdbc.properties"));
       return String.format(connectionStringtemplate, prop.get("host"), prop.get("db"), prop.get("username"), prop.get("password"));
   }
   
   
   public Properties getJDBCConnectionProperties() throws Exception{
       //return "jdbc:sqlite:"+ FileUtil.getCurrentJarPath()+"db/FreebasicAcc.s3db";
       Properties prop = new Properties();
       prop.load(new FileInputStream(FileUtil.getCurrentJarPath()+"/config/jdbc.properties"));
       return prop;
   }
}