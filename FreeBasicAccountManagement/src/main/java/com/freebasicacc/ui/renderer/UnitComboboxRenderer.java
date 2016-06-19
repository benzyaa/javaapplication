/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.renderer;

import com.freebasicacc.model.Unit;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author benzyaa
 */
public class UnitComboboxRenderer extends BasicComboBoxRenderer{
    private Object unitIdBankObject;
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value !=null || index ==-1){
            Unit unit = (Unit)value;
            setText(unit.getUnitName());
            unitIdBankObject = unit.getUnitId();
        }
        return this;
    }

    /**
     * @return the unitIdBankObject
     */
    public Object getUnitIdBankObject() {
        return unitIdBankObject;
    }

    /**
     * @param unitIdBankObject the unitIdBankObject to set
     */
    public void setUnitIdBankObject(Object unitIdBankObject) {
        this.unitIdBankObject = unitIdBankObject;
    }    
}
