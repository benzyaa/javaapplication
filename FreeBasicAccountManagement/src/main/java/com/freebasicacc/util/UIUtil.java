/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import com.freebasicacc.ui.observer.DateInputFieldObserver;
import com.qt.datapicker.DatePicker;
import java.awt.Component;
import java.util.Date;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author benzyaa
 */
public class UIUtil {
    public void openDatepickerForTextField(JTextField textField,JFrame mainFrame){
        
        DateInputFieldObserver dateInputFieldObserver = new DateInputFieldObserver();
        dateInputFieldObserver.setDateTextField(textField);
        DatePicker dp = new DatePicker(dateInputFieldObserver, new Locale("th","TH"));
        Date selectedDate = dp.parseDate(textField.getText());
        dp.setSelectedDate(selectedDate);
        dp.start(textField);
    }
   
}
