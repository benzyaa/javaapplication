/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UnitAddPanelInternalFrame.java
 *
 * Created on Mar 31, 2013, 11:55:20 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.action.MainFrameAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author benzyaa
 */
public class UnitEditPanelInternalFrame extends javax.swing.JInternalFrame {

    /** Creates new form UnitAddPanelInternalFrame */
    public UnitEditPanelInternalFrame() {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
        this.unitEditPanel1.setInternalFrame(this);
    }
    
    public UnitEditPanelInternalFrame(JPanel openerPanel) {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
        this.unitEditPanel1.setInternalFrame(this);
        this.unitEditPanel1.setOpenerPanel(openerPanel);
    }
    
    public UnitEditPanelInternalFrame(JPanel openerPanel,Unit unit) {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
        this.unitEditPanel1.setInternalFrame(this);
        this.unitEditPanel1.setOpenerPanel(openerPanel);
        this.unitEditPanel1.setUnit(unit);
        this.unitEditPanel1.setDefaultValue(unit);
    }
    
    private void SetIcon(){
        ImageIcon mainIcon = new ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Misc-Misc-Box-icon1616.png")); 
        this.setFrameIcon(mainIcon);
    }
    
    private void setTitleFont(){
        MainFrameAction action = new MainFrameAction();
        action.setInternalFramTitle(this);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unitEditPanel1 = new com.freebasicacc.ui.UnitEditPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        setTitle(bundle.getString("dialog.unit.title.edit")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(unitEditPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unitEditPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.freebasicacc.ui.UnitEditPanel unitEditPanel1;
    // End of variables declaration//GEN-END:variables
}