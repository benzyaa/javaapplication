/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.renderer;

import com.freebasicacc.model.DisplayLang;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author benzyaa
 */
public class DisplayLangComboboxRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value !=null || index ==-1){
            DisplayLang displayLang = (DisplayLang)value;
            setText(displayLang.getLangName());
        }
        return this;
    }
}