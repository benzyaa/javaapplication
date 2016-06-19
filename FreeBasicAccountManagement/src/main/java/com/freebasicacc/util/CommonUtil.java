/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author benzyaa
 */
public class CommonUtil {
    public static String getComputerName(){
        String hostname = "Unknown";
        try{
            hostname = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException ex){
          
        }
        return hostname;
    }
}
