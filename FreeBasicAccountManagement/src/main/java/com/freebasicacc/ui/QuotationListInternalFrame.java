/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuotationListInternalFrame.java
 *
 * Created on Apr 7, 2013, 11:24:35 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.ui.action.MainFrameAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author benzyaa
 */
public class QuotationListInternalFrame extends javax.swing.JInternalFrame {

    /** Creates new form QuotationListInternalFrame */
    public QuotationListInternalFrame() {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
    }
    
    public QuotationListInternalFrame(JFrame mainFrame) {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
        this.quotationInfoListPanel1.setMainFrame(mainFrame);
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

        quotationInfoListPanel1 = new com.freebasicacc.ui.QuotationInfoListPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        setTitle(bundle.getString("list.quotation")); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quotationInfoListPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(quotationInfoListPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected com.freebasicacc.ui.QuotationInfoListPanel quotationInfoListPanel1;
    // End of variables declaration//GEN-END:variables
}