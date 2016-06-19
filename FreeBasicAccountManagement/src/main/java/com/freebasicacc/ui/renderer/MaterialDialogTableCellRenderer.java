/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.renderer;

import com.freebasicacc.model.Material;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author benzyaa
 */
public class MaterialDialogTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(column == 1 && value !=null && value instanceof Material){
            Material material = (Material)value;
            setText(material.getMaterialId());
        }
        return this;
    }
    
}
