/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.verifier;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author benzyaa
 */
public class EmailTextFieldInputVerifier extends InputVerifier{

    @Override
    public boolean verify(JComponent input) {
        return false;
    }
    
}
