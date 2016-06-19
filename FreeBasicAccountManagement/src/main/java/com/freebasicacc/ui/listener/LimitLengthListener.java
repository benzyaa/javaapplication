/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.text.JTextComponent;

/**
 *
 * @author benzyaa
 */
public class LimitLengthListener extends KeyAdapter{
    private int maxlength;
    @Override
    public void keyTyped(KeyEvent e) {
        JTextComponent textField = (JTextComponent) e.getComponent();
        if(textField.getText().length() == maxlength){
            e.consume();
        }
    }

    public LimitLengthListener(int maxlength) {
        this.maxlength = maxlength;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }
    
}
