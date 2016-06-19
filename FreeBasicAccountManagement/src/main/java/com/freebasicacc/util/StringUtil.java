/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benzyaa
 */
public class StringUtil {

	public static boolean isContainText(String text){
		return !("".equals(text) || text == null || "null".equalsIgnoreCase(text) || "undefined".equalsIgnoreCase(text));
	}
	
    public static String getZeroFill(Long index,int num){
        String stnum = "00000000"+ index;
        int len = stnum.length();
        String stindex = stnum.substring(len-num);
        return stindex;
    }
    
    public static String getStr(Object obj) {
        if (obj != null) {
            if (obj.toString().length() > 0) {
                return obj.toString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    
    public static String toUtf8(String str) {
    	return str; // For Oracle WebLogic
        /*try {
        	if(!StringUtil.isContainText(str)) return str;
			return new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
		return null;*/
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

 
    public static String lpad(String str, Integer length, String car) {
    	  return str+String.format("%" + (length) + "s", "") .replace(" ", String.valueOf(car));
    	}

    public static String rpad(String str, Integer length, String car) {
    	  return String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car)) + str;
    	}
    
    public static String formatThaiCurrency(Number number){ 
        if(number == null)return "";
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("th","TH"));
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("");
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(number);
    }
    
    public static Number formatThaiCurrencyStrToNumber(String numberStr){  
          try {
                DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("th","TH"));
                DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                symbols.setCurrencySymbol("");
                formatter.setDecimalFormatSymbols(symbols);
                return formatter.parse(numberStr);
        } catch (ParseException ex) {
           ex.printStackTrace();    
        }
        return null;
    }
}