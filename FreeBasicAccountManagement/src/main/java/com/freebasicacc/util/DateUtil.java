/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author benzyaa
 */
public class DateUtil {
    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static Timestamp convertDateUtilToTimeStamp(java.util.Date date){
        if(date == null) return null;
        return new Timestamp(date.getTime());
    }
    
    public static java.util.Date convertDateStrToDateUtil(String dateStr) throws Exception{
        return convertDateStrToDateUtil(dateStr,DateUtil.DEFAULT_DATE_PATTERN, new Locale("th","TH"));
    }
    
    public static java.util.Date convertDateStrToDateUtil(String dateStr,String pattern) throws Exception{
        return convertDateStrToDateUtil(dateStr,pattern, new Locale("th","TH"));
    }
    
    public static java.util.Date convertDateStrToDateUtil(String dateStr,String pattern, Locale locale) throws Exception{
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
            dateFormat.setLenient(false);
            return dateFormat.parse(dateStr);
        } catch (ParseException ex) {
            throw new IllegalArgumentException(AppConstants.WRONG_DATE_FORMAT_WARNING, ex);
        }
    }
    public static String convertDateUtilToDateStr(java.util.Date dateUtil){
        return convertDateUtilToDateStr(dateUtil,DateUtil.DEFAULT_DATE_PATTERN,new Locale("th","TH"));
    }
    public static String convertDateUtilToDateStr(java.util.Date dateUtil,String pattern){
        return convertDateUtilToDateStr(dateUtil,pattern,new Locale("th","TH"));
    }
    public static String convertDateUtilToDateStr(java.util.Date dateUtil,String pattern,Locale locale){
        if(dateUtil == null) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);
        return dateFormat.format(dateUtil);
    }
    
    public static long calculateDateDiff(java.util.Date currentDate,java.util.Date earierDate){
        Calendar currentDateCalendar = Calendar.getInstance();
        Calendar earierDateCalendar = Calendar.getInstance();
        currentDateCalendar.setTime(currentDate);
        earierDateCalendar.setTime(earierDate);
        long currentDateInMills = currentDateCalendar.getTimeInMillis();
        long earierDateImMills = earierDateCalendar.getTimeInMillis();
        return (currentDateInMills - earierDateImMills) / (24 * 60 * 60 * 1000);
    }
}