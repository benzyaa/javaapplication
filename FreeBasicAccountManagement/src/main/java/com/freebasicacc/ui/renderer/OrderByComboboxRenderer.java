/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.renderer;

import com.freebasicacc.model.OrderBy;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author benzyaa
 */
public class OrderByComboboxRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value !=null || index ==-1){
            OrderBy orderBy = (OrderBy)value;
            setText(orderBy.getName());
        }
        return this;
    }
}