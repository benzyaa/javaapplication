/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author benzyaa
 */
public class DialogUtil {
    public static void displayDataSaveSuccessMessage(){
         DialogUtil.displayDataSaveSuccessMessage(MessageUtil.getMessage("notification.datasave.message.success"));
    }
    
   
    
     public static void displayDataSaveEditSuccessMessage(){
         DialogUtil.displayDataSaveSuccessMessage(MessageUtil.getMessage("notification.datasave.message.editsuccess"));
     }
     
      public static void displayDataSaveSuccessMessage(String message){
         JOptionPane.showMessageDialog(new JInternalFrame(),message, MessageUtil.getMessage("notification.datasave.title"), JOptionPane.INFORMATION_MESSAGE);        
    }
     
   
    
    public static void displayErrorMessage(Exception ex){
         JOptionPane.showMessageDialog(new JInternalFrame(),ex.getMessage() , MessageUtil.getMessage("notification.error"), JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displaySelectAtleastOneItemDialog(){
          JOptionPane.showMessageDialog(new JInternalFrame(),MessageUtil.getMessage("notification.select.atleastoneitem"), MessageUtil.getMessage("notification.warning"), JOptionPane.ERROR_MESSAGE);
    }
}
