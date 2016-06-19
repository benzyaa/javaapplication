/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author benzyaa
 */
public class BannedKeyListener extends KeyAdapter{
    private String inputType;
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();        
        if("NUMBER".equals(inputType) && ((c >= '0') && (c <= '9')))return;
        else if("CURRENCY".equals(inputType) && ((c >= '0') && (c <= '9')) || (c =='.') || (c ==','))return;
        else if("DATE".equals(inputType) && (((c >= '0') && (c <= '9'))) || (c=='/'))return;
        e.consume();     
    }

    public BannedKeyListener(String inputType) {
        this.inputType = inputType;
    }
    
    /**
     * @return the inputType
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * @param inputType the inputType to set
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

}
