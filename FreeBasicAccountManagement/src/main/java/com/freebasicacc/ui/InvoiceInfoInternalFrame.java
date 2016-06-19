/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceInfoInternalFrame.java
 *
 * Created on Apr 7, 2013, 8:22:46 PM
 */
package com.freebasicacc.ui;

import com.freebasicacc.ui.action.MainFrameAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author benzyaa
 */
public class InvoiceInfoInternalFrame extends javax.swing.JInternalFrame {

    /** Creates new form InvoiceInfoInternalFrame */
    public InvoiceInfoInternalFrame() {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
    }
    
    public InvoiceInfoInternalFrame(JPanel openerPanel) {
        initComponents();
        invoiceInfoPanel1.setOpenerPanel(openerPanel);
        this.SetIcon();
        this.setTitleFont();
    }
    
    public InvoiceInfoInternalFrame(JFrame mainFrame,JPanel openerPanel) {
        initComponents();
        invoiceInfoPanel1.setOpenerPanel(openerPanel);
        invoiceInfoPanel1.setMainFrame(mainFrame);
        invoiceInfoPanel1.setInternalFrame(this);
        this.SetIcon();
        this.setTitleFont();
    }
    
    private void SetIcon(){
        ImageIcon mainIcon = new ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Document1616.png")); 
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

        invoiceInfoPanel1 = new com.freebasicacc.ui.InvoiceInfoPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        setTitle(bundle.getString("dialog.invoice.title.create")); // NOI18N
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(883, 768));

        invoiceInfoPanel1.setPreferredSize(new java.awt.Dimension(847, 761));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(invoiceInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(invoiceInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected com.freebasicacc.ui.InvoiceInfoPanel invoiceInfoPanel1;
    // End of variables declaration//GEN-END:variables
}