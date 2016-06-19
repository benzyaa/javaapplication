/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui;

import com.qt.datapicker.DatePicker;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/**
 *
 * @author benzyaa
 */
class ObservingTextField extends JTextField
    implements Observer
{
    
    ObservingTextField()
    {
    }

    public void update(Observable o, Object arg)
    {
        Calendar calendar = (Calendar)arg;
        DatePicker dp = (DatePicker)o;
        System.out.println("picked=" + dp.formatDate(calendar));
        setText(dp.formatDate(calendar));
    }
}
