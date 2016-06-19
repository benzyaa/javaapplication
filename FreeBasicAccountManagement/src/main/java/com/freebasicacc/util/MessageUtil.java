/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

/**
 *
 * @author benzyaa
 */
public class MessageUtil {
    public static String getMessage(String key){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle");
        return bundle.getString(key);
    }
}
