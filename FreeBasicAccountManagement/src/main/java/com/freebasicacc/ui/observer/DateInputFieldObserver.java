/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.observer;

import com.freebasicacc.util.DateUtil;
import com.qt.datapicker.DatePicker;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/**
 *
 * @author benzyaa
 */
public class DateInputFieldObserver implements Observer{

    private JTextField dateTextField;
    @Override
    public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar)arg;
        DatePicker dp = (DatePicker)o;
        this.dateTextField.setText(DateUtil.convertDateUtilToDateStr(calendar.getTime()));
        this.dateTextField.setText(dp.formatDate(calendar));
    }

    /**
     * @return the dateTextField
     */
    public JTextField getDateTextField() {
        return dateTextField;
    }

    /**
     * @param dateTextField the dateTextField to set
     */
    public void setDateTextField(JTextField dateTextField) {
        this.dateTextField = dateTextField;
    }
}