/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.renderer;

import com.freebasicacc.model.Material;
import com.freebasicacc.model.Unit;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author benzyaa
 */
public class QuotationTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == 1 && value !=null && value instanceof Material){
            Material material = (Material)value;
            setText(material.getMaterialName());
        }else if(column == 3 && value !=null && value instanceof Unit){
            Unit unit = (Unit)value;
            setText(unit.getUnitName());
        }
        return this;
    }
    
}
