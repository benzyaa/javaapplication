/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuotationInfoViewInternalFrame.java
 *
 * Created on Apr 7, 2013, 4:59:14 PM
 */
package com.freebasicacc.ui;

import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.ui.action.MainFrameAction;
import javax.swing.ImageIcon;

/**
 *
 * @author benzyaa
 */
public class QuotationInfoViewInternalFrame extends javax.swing.JInternalFrame {

    /** Creates new form QuotationInfoViewInternalFrame */
    public QuotationInfoViewInternalFrame() {
        initComponents();
        this.SetIcon();
        this.setTitleFont();
    }
    
    public QuotationInfoViewInternalFrame(QuotationHead quotation) {
        initComponents();
        this.quotationInfoViewPanel1.setQuotationHead(quotation);
        this.quotationInfoViewPanel1.assignViewDefaultValue(quotation);
        this.quotationInfoViewPanel1.setInternalFrame(this);
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

        quotationInfoViewPanel1 = new com.freebasicacc.ui.QuotationInfoViewPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setResizable(true);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        setTitle(bundle.getString("dialog.quotation.title.info")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quotationInfoViewPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quotationInfoViewPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected com.freebasicacc.ui.QuotationInfoViewPanel quotationInfoViewPanel1;
    // End of variables declaration//GEN-END:variables
}
