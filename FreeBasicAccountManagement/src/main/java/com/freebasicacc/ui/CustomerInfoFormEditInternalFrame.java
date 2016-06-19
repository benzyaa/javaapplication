/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerInfoFormEditInternalFrame.java
 *
 * Created on Mar 31, 2013, 5:46:33 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.model.Customer;
import com.freebasicacc.ui.action.MainFrameAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author benzyaa
 */
public class CustomerInfoFormEditInternalFrame extends javax.swing.JInternalFrame {

    /** Creates new form CustomerInfoFormEditInternalFrame */
    public CustomerInfoFormEditInternalFrame() {
        initComponents();
         this.SetIcon();
         this.setTitleFont();
    }
    
    
    public CustomerInfoFormEditInternalFrame(JPanel openerPanel) {
        initComponents();
         this.SetIcon();
        this.setTitleFont();
        this.customerInfoPanelEdit2.setOpenerPanel(openerPanel);

    }
    
     private void SetIcon(){
        ImageIcon mainIcon = new ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Office-Client-Male-Light-icon1616.png")); 
        this.setFrameIcon(mainIcon);
    }
    
    private void setTitleFont(){
        MainFrameAction action = new MainFrameAction();
        action.setInternalFramTitle(this);
    }
    
    public CustomerInfoFormEditInternalFrame(JPanel openerPanel, Customer customer) {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
        this.customerInfoPanelEdit2.setOpenerPanel(openerPanel);
        this.customerInfoPanelEdit2.setCustomer(customer);
        this.customerInfoPanelEdit2.assignDefaultValue(customer);
        this.customerInfoPanelEdit2.setInternalFrame(this);
    }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerInfoPanelEdit1 = new com.freebasicacc.ui.CustomerInfoPanelEdit();
        customerInfoPanelEdit2 = new com.freebasicacc.ui.CustomerInfoPanelEdit();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setClosable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        setTitle(bundle.getString("dialog.customer.title.edit")); // NOI18N

        customerInfoPanelEdit2.setPreferredSize(new java.awt.Dimension(701, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerInfoPanelEdit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerInfoPanelEdit2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.freebasicacc.ui.CustomerInfoPanelEdit customerInfoPanelEdit1;
    private com.freebasicacc.ui.CustomerInfoPanelEdit customerInfoPanelEdit2;
    // End of variables declaration//GEN-END:variables
}