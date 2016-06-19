/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuotationInfoPanel.java
 *
 * Created on Apr 6, 2013, 10:45:15 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.action.QuotationAction;
import com.freebasicacc.model.QuotationDetail;
import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.renderer.QuotationTableCellRenderer;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author benzyaa
 */
public class QuotationInfoViewPanel extends javax.swing.JPanel {

    /** Creates new form QuotationInfoPanel */
    public QuotationInfoViewPanel() {
        initComponents();
        customerIdHiddenValueLabel.setVisible(false);
        this.quotationDetailTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
        /*QuotationDetailListTableListener tableListener = new QuotationDetailListTableListener();
        tableListener.setHandlerPanel(this);
        this.quotationDetailTable.getModel().addTableModelListener(tableListener);*/
    }
    
    public javax.swing.table.DefaultTableModel generateModelForColumn(){
        return new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    MessageUtil.getMessage("common.sequence"), 
                    MessageUtil.getMessage("quotation.no"),
                    MessageUtil.getMessage("quotation.title"),
                    MessageUtil.getMessage("quotation.customer"),
                    MessageUtil.getMessage("quotation.date"),   
                    MessageUtil.getMessage("quotation.deliveryplace"),
                    MessageUtil.getMessage("quotation.leaddate"),
                    MessageUtil.getMessage("quotation.paymentterm"),
                    MessageUtil.getMessage("quotation.totalvalue"),
                    MessageUtil.getMessage("quotation.valueaddedtax"),
                    MessageUtil.getMessage("quotation.netvalue"),
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quotationInfoTitleLabel = new javax.swing.JLabel();
        quotationTitleLabel = new javax.swing.JLabel();
        quotationTitleTextField = new javax.swing.JTextField();
        customerContractLabel = new javax.swing.JLabel();
        customerContractTextField = new javax.swing.JTextField();
        quotationIdLabel = new javax.swing.JLabel();
        quotationIdTextField = new javax.swing.JTextField();
        customerNameLabel = new javax.swing.JLabel();
        customerNameTextField = new javax.swing.JTextField();
        paymentTermLabel = new javax.swing.JLabel();
        paymentTermTextField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        deliveryplaceLabel = new javax.swing.JLabel();
        deliveryPlaceTextField = new javax.swing.JTextField();
        leadTimeLabel = new javax.swing.JLabel();
        leadTimeTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        quotationDetailTable = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        totalValueTextField = new javax.swing.JTextField();
        vatTextField = new javax.swing.JTextField();
        netValueTextField = new javax.swing.JTextField();
        totalValueLabel = new javax.swing.JLabel();
        vatLabel = new javax.swing.JLabel();
        netValueLabel = new javax.swing.JLabel();
        netValueTextLabel = new javax.swing.JLabel();
        customerIdHiddenValueLabel = new javax.swing.JLabel();

        quotationInfoTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quotationInfoTitleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        quotationInfoTitleLabel.setText(bundle.getString("dialog.quotation.title.info")); // NOI18N

        quotationTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationTitleLabel.setText(bundle.getString("quotation.title")); // NOI18N

        quotationTitleTextField.setEditable(false);
        quotationTitleTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationTitleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quotationTitleTextFieldActionPerformed(evt);
            }
        });

        customerContractLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerContractLabel.setText(bundle.getString("quotation.dear")); // NOI18N

        customerContractTextField.setEditable(false);
        customerContractTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        quotationIdLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationIdLabel.setText(bundle.getString("quotation.no")); // NOI18N

        quotationIdTextField.setEditable(false);
        quotationIdTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationIdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quotationIdTextFieldActionPerformed(evt);
            }
        });

        customerNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerNameLabel.setText(bundle.getString("quotation.customer")); // NOI18N

        customerNameTextField.setEditable(false);
        customerNameTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameTextFieldActionPerformed(evt);
            }
        });

        paymentTermLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentTermLabel.setText(bundle.getString("quotation.paymentterm")); // NOI18N

        paymentTermTextField.setEditable(false);
        paymentTermTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentTermTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentTermTextFieldActionPerformed(evt);
            }
        });

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phoneLabel.setText(bundle.getString("quotation.phone")); // NOI18N

        phoneTextField.setEditable(false);

        deliveryplaceLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deliveryplaceLabel.setText(bundle.getString("quotation.deliveryplace")); // NOI18N

        deliveryPlaceTextField.setEditable(false);
        deliveryPlaceTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deliveryPlaceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryPlaceTextFieldActionPerformed(evt);
            }
        });

        leadTimeLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        leadTimeLabel.setText(bundle.getString("quotation.leaddate")); // NOI18N

        leadTimeTextField.setEditable(false);
        leadTimeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        quotationDetailTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationDetailTable.setModel(this.generateModelForColumn());
        quotationDetailTable.setEnabled(false);
        quotationDetailTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quotationDetailTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quotationDetailTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(quotationDetailTable);

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/33.png"))); // NOI18N
        cancelButton.setText(bundle.getString("common.close")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        totalValueTextField.setEditable(false);
        totalValueTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalValueTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalValueTextField.setText("0");

        vatTextField.setEditable(false);
        vatTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        vatTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        vatTextField.setText("0");

        netValueTextField.setEditable(false);
        netValueTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        netValueTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        netValueTextField.setText("0");

        totalValueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalValueLabel.setText(bundle.getString("quotation.totalvalue")); // NOI18N

        vatLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        vatLabel.setText(bundle.getString("quotation.valueaddedtax")); // NOI18N

        netValueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        netValueLabel.setText(bundle.getString("quotation.netvalue")); // NOI18N

        netValueTextLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        netValueTextLabel.setText("jLabel1");

        customerIdHiddenValueLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(phoneLabel)
                                .addGap(18, 18, 18)
                                .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerNameLabel)
                                    .addComponent(customerContractLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customerNameTextField)
                                    .addComponent(customerContractTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(customerIdHiddenValueLabel)))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(deliveryplaceLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deliveryPlaceTextField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(paymentTermLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(paymentTermTextField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(leadTimeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leadTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(quotationIdLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quotationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(totalValueLabel)
                                    .addComponent(vatLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(netValueTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(netValueLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(vatTextField)
                                    .addComponent(totalValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(netValueTextField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(cancelButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quotationInfoTitleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(quotationTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quotationTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quotationInfoTitleLabel)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quotationTitleLabel)
                    .addComponent(quotationTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerContractTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quotationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quotationIdLabel)
                    .addComponent(customerContractLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerNameLabel)
                        .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paymentTermLabel)
                        .addComponent(paymentTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryplaceLabel)
                    .addComponent(deliveryPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(leadTimeLabel)
                        .addComponent(leadTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customerIdHiddenValueLabel))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vatLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(netValueLabel)
                    .addComponent(netValueTextLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(90, 90, 90))
        );
    }// </editor-fold>//GEN-END:initComponents

private void quotationIdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quotationIdTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_quotationIdTextFieldActionPerformed

private void quotationTitleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quotationTitleTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_quotationTitleTextFieldActionPerformed

private void customerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerNameTextFieldActionPerformed

private void paymentTermTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentTermTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_paymentTermTextFieldActionPerformed

private void deliveryPlaceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryPlaceTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_deliveryPlaceTextFieldActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
     MainFrameAction action = new MainFrameAction();
     action.closeInternalFrame(this.getInternalFrame());
}//GEN-LAST:event_cancelButtonActionPerformed

private void quotationDetailTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quotationDetailTableKeyReleased
 
}//GEN-LAST:event_quotationDetailTableKeyReleased

private void quotationDetailTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quotationDetailTableKeyPressed
 
}//GEN-LAST:event_quotationDetailTableKeyPressed

public void assignViewDefaultValue(QuotationHead quotation){
     this.quotationIdTextField.setText(quotation.getQuotationHeadId());
     this.quotationTitleTextField.setText(quotation.getQuotationTitle());
     this.customerNameTextField.setText(quotation.getCustomer().getCustomerName());
     this.customerIdHiddenValueLabel.setText(quotation.getCustomer().getCustomerId());
     this.customerContractTextField.setText(quotation.getCustomer().getCustomerContract());
     this.phoneTextField.setText(quotation.getCustomer().getCustomerPhone());
     this.paymentTermTextField.setText(quotation.getPaymentTerm());
     this.deliveryPlaceTextField.setText(quotation.getDeliveryPlace());
     this.leadTimeTextField.setText(DateUtil.convertDateUtilToDateStr(quotation.getLeadDate()));
     this.totalValueTextField.setText(StringUtil.formatThaiCurrency(quotation.getTotalValue()));
     this.vatTextField.setText(StringUtil.formatThaiCurrency(quotation.getVat()));
     this.netValueTextField.setText(StringUtil.formatThaiCurrency(quotation.getNetValue()));
     this.netValueTextLabel.setText(quotation.getNetValueText());
     
    quotationDetailTable.setDefaultRenderer(Object.class, new QuotationTableCellRenderer());
    DefaultTableModel defaultTableModel = (DefaultTableModel) quotationDetailTable.getModel();
    List<QuotationDetail> detailList = quotation.getQuotationDetailSet();
    for(QuotationDetail detail : detailList){
        defaultTableModel.addRow(new Object[]{detail.getItemNo(),detail.getMaterial(),detail.getQuantity(),detail.getMaterial().getUnit(),StringUtil.formatThaiCurrency(detail.getPricePerUnit()),StringUtil.formatThaiCurrency(detail.getTotalPrice())});
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton cancelButton;
    protected javax.swing.JLabel customerContractLabel;
    protected javax.swing.JTextField customerContractTextField;
    protected javax.swing.JLabel customerIdHiddenValueLabel;
    protected javax.swing.JLabel customerNameLabel;
    protected javax.swing.JTextField customerNameTextField;
    protected javax.swing.JTextField deliveryPlaceTextField;
    protected javax.swing.JLabel deliveryplaceLabel;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JLabel leadTimeLabel;
    protected javax.swing.JTextField leadTimeTextField;
    protected javax.swing.JLabel netValueLabel;
    protected javax.swing.JTextField netValueTextField;
    protected javax.swing.JLabel netValueTextLabel;
    protected javax.swing.JLabel paymentTermLabel;
    protected javax.swing.JTextField paymentTermTextField;
    protected javax.swing.JLabel phoneLabel;
    protected javax.swing.JTextField phoneTextField;
    protected javax.swing.JTable quotationDetailTable;
    protected javax.swing.JLabel quotationIdLabel;
    protected javax.swing.JTextField quotationIdTextField;
    protected javax.swing.JLabel quotationInfoTitleLabel;
    protected javax.swing.JLabel quotationTitleLabel;
    protected javax.swing.JTextField quotationTitleTextField;
    protected javax.swing.JLabel totalValueLabel;
    protected javax.swing.JTextField totalValueTextField;
    protected javax.swing.JLabel vatLabel;
    protected javax.swing.JTextField vatTextField;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JFrame mainFrame;
    private JPanel openerPanel;
    private JInternalFrame internalFrame;
    private QuotationHead quotationHead;

    /**
     * @return the cancelButton
     */
    public javax.swing.JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * @param cancelButton the cancelButton to set
     */
    public void setCancelButton(javax.swing.JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    /**
     * @return the customerContractLabel
     */
    public javax.swing.JLabel getCustomerContractLabel() {
        return customerContractLabel;
    }

    /**
     * @param customerContractLabel the customerContractLabel to set
     */
    public void setCustomerContractLabel(javax.swing.JLabel customerContractLabel) {
        this.customerContractLabel = customerContractLabel;
    }

    /**
     * @return the customerContractTextField
     */
    public javax.swing.JTextField getCustomerContractTextField() {
        return customerContractTextField;
    }

    /**
     * @param customerContractTextField the customerContractTextField to set
     */
    public void setCustomerContractTextField(javax.swing.JTextField customerContractTextField) {
        this.customerContractTextField = customerContractTextField;
    }

    /**
     * @return the customerIdHiddenValueLabel
     */
    public javax.swing.JLabel getCustomerIdHiddenValueLabel() {
        return customerIdHiddenValueLabel;
    }

    /**
     * @param customerIdHiddenValueLabel the customerIdHiddenValueLabel to set
     */
    public void setCustomerIdHiddenValueLabel(javax.swing.JLabel customerIdHiddenValueLabel) {
        this.customerIdHiddenValueLabel = customerIdHiddenValueLabel;
    }

    /**
     * @return the customerNameLabel
     */
    public javax.swing.JLabel getCustomerNameLabel() {
        return customerNameLabel;
    }

    /**
     * @param customerNameLabel the customerNameLabel to set
     */
    public void setCustomerNameLabel(javax.swing.JLabel customerNameLabel) {
        this.customerNameLabel = customerNameLabel;
    }

    /**
     * @return the customerNameTextField
     */
    public javax.swing.JTextField getCustomerNameTextField() {
        return customerNameTextField;
    }

    /**
     * @param customerNameTextField the customerNameTextField to set
     */
    public void setCustomerNameTextField(javax.swing.JTextField customerNameTextField) {
        this.customerNameTextField = customerNameTextField;
    }

    /**
     * @return the deliveryPlaceTextField
     */
    public javax.swing.JTextField getDeliveryPlaceTextField() {
        return deliveryPlaceTextField;
    }

    /**
     * @param deliveryPlaceTextField the deliveryPlaceTextField to set
     */
    public void setDeliveryPlaceTextField(javax.swing.JTextField deliveryPlaceTextField) {
        this.deliveryPlaceTextField = deliveryPlaceTextField;
    }

    /**
     * @return the deliveryplaceLabel
     */
    public javax.swing.JLabel getDeliveryplaceLabel() {
        return deliveryplaceLabel;
    }

    /**
     * @param deliveryplaceLabel the deliveryplaceLabel to set
     */
    public void setDeliveryplaceLabel(javax.swing.JLabel deliveryplaceLabel) {
        this.deliveryplaceLabel = deliveryplaceLabel;
    }

    /**
     * @return the jScrollPane1
     */
    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    /**
     * @param jScrollPane1 the jScrollPane1 to set
     */
    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    /**
     * @return the leadTimeLabel
     */
    public javax.swing.JLabel getLeadTimeLabel() {
        return leadTimeLabel;
    }

    /**
     * @param leadTimeLabel the leadTimeLabel to set
     */
    public void setLeadTimeLabel(javax.swing.JLabel leadTimeLabel) {
        this.leadTimeLabel = leadTimeLabel;
    }

    /**
     * @return the leadTimeTextField
     */
    public javax.swing.JTextField getLeadTimeTextField() {
        return leadTimeTextField;
    }

    /**
     * @param leadTimeTextField the leadTimeTextField to set
     */
    public void setLeadTimeTextField(javax.swing.JTextField leadTimeTextField) {
        this.leadTimeTextField = leadTimeTextField;
    }

    /**
     * @return the netValueLabel
     */
    public javax.swing.JLabel getNetValueLabel() {
        return netValueLabel;
    }

    /**
     * @param netValueLabel the netValueLabel to set
     */
    public void setNetValueLabel(javax.swing.JLabel netValueLabel) {
        this.netValueLabel = netValueLabel;
    }

    /**
     * @return the netValueTextField
     */
    public javax.swing.JTextField getNetValueTextField() {
        return netValueTextField;
    }

    /**
     * @param netValueTextField the netValueTextField to set
     */
    public void setNetValueTextField(javax.swing.JTextField netValueTextField) {
        this.netValueTextField = netValueTextField;
    }

    /**
     * @return the netValueTextLabel
     */
    public javax.swing.JLabel getNetValueTextLabel() {
        return netValueTextLabel;
    }

    /**
     * @param netValueTextLabel the netValueTextLabel to set
     */
    public void setNetValueTextLabel(javax.swing.JLabel netValueTextLabel) {
        this.netValueTextLabel = netValueTextLabel;
    }

    /**
     * @return the paymentTermLabel
     */
    public javax.swing.JLabel getPaymentTermLabel() {
        return paymentTermLabel;
    }

    /**
     * @param paymentTermLabel the paymentTermLabel to set
     */
    public void setPaymentTermLabel(javax.swing.JLabel paymentTermLabel) {
        this.paymentTermLabel = paymentTermLabel;
    }

    /**
     * @return the paymentTermTextField
     */
    public javax.swing.JTextField getPaymentTermTextField() {
        return paymentTermTextField;
    }

    /**
     * @param paymentTermTextField the paymentTermTextField to set
     */
    public void setPaymentTermTextField(javax.swing.JTextField paymentTermTextField) {
        this.paymentTermTextField = paymentTermTextField;
    }

    /**
     * @return the phoneLabel
     */
    public javax.swing.JLabel getPhoneLabel() {
        return phoneLabel;
    }

    /**
     * @param phoneLabel the phoneLabel to set
     */
    public void setPhoneLabel(javax.swing.JLabel phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    /**
     * @return the phoneTextField
     */
    public javax.swing.JTextField getPhoneTextField() {
        return phoneTextField;
    }

    /**
     * @param phoneTextField the phoneTextField to set
     */
    public void setPhoneTextField(javax.swing.JTextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }

    /**
     * @return the quotationDetailTable
     */
    public javax.swing.JTable getQuotationDetailTable() {
        return quotationDetailTable;
    }

    /**
     * @param quotationDetailTable the quotationDetailTable to set
     */
    public void setQuotationDetailTable(javax.swing.JTable quotationDetailTable) {
        this.quotationDetailTable = quotationDetailTable;
    }

    /**
     * @return the quotationIdLabel
     */
    public javax.swing.JLabel getQuotationIdLabel() {
        return quotationIdLabel;
    }

    /**
     * @param quotationIdLabel the quotationIdLabel to set
     */
    public void setQuotationIdLabel(javax.swing.JLabel quotationIdLabel) {
        this.quotationIdLabel = quotationIdLabel;
    }

    /**
     * @return the quotationIdTextField
     */
    public javax.swing.JTextField getQuotationIdTextField() {
        return quotationIdTextField;
    }

    /**
     * @param quotationIdTextField the quotationIdTextField to set
     */
    public void setQuotationIdTextField(javax.swing.JTextField quotationIdTextField) {
        this.quotationIdTextField = quotationIdTextField;
    }

    /**
     * @return the quotationInfoTitleLabel
     */
    public javax.swing.JLabel getQuotationInfoTitleLabel() {
        return quotationInfoTitleLabel;
    }

    /**
     * @param quotationInfoTitleLabel the quotationInfoTitleLabel to set
     */
    public void setQuotationInfoTitleLabel(javax.swing.JLabel quotationInfoTitleLabel) {
        this.quotationInfoTitleLabel = quotationInfoTitleLabel;
    }

    /**
     * @return the quotationTitleLabel
     */
    public javax.swing.JLabel getQuotationTitleLabel() {
        return quotationTitleLabel;
    }

    /**
     * @param quotationTitleLabel the quotationTitleLabel to set
     */
    public void setQuotationTitleLabel(javax.swing.JLabel quotationTitleLabel) {
        this.quotationTitleLabel = quotationTitleLabel;
    }

    /**
     * @return the quotationTitleTextField
     */
    public javax.swing.JTextField getQuotationTitleTextField() {
        return quotationTitleTextField;
    }

    /**
     * @param quotationTitleTextField the quotationTitleTextField to set
     */
    public void setQuotationTitleTextField(javax.swing.JTextField quotationTitleTextField) {
        this.quotationTitleTextField = quotationTitleTextField;
    }

    /**
     * @return the totalValueLabel
     */
    public javax.swing.JLabel getTotalValueLabel() {
        return totalValueLabel;
    }

    /**
     * @param totalValueLabel the totalValueLabel to set
     */
    public void setTotalValueLabel(javax.swing.JLabel totalValueLabel) {
        this.totalValueLabel = totalValueLabel;
    }

    /**
     * @return the totalValueTextField
     */
    public javax.swing.JTextField getTotalValueTextField() {
        return totalValueTextField;
    }

    /**
     * @param totalValueTextField the totalValueTextField to set
     */
    public void setTotalValueTextField(javax.swing.JTextField totalValueTextField) {
        this.totalValueTextField = totalValueTextField;
    }

    /**
     * @return the vatLabel
     */
    public javax.swing.JLabel getVatLabel() {
        return vatLabel;
    }

    /**
     * @param vatLabel the vatLabel to set
     */
    public void setVatLabel(javax.swing.JLabel vatLabel) {
        this.vatLabel = vatLabel;
    }

    /**
     * @return the vatTextField
     */
    public javax.swing.JTextField getVatTextField() {
        return vatTextField;
    }

    /**
     * @param vatTextField the vatTextField to set
     */
    public void setVatTextField(javax.swing.JTextField vatTextField) {
        this.vatTextField = vatTextField;
    }

    /**
     * @return the mainFrame
     */
    public javax.swing.JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(javax.swing.JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * @return the openerPanel
     */
    public JPanel getOpenerPanel() {
        return openerPanel;
    }

    /**
     * @param openerPanel the openerPanel to set
     */
    public void setOpenerPanel(JPanel openerPanel) {
        this.openerPanel = openerPanel;
    }

    /**
     * @return the internalFrame
     */
    public JInternalFrame getInternalFrame() {
        return internalFrame;
    }

    /**
     * @param internalFrame the internalFrame to set
     */
    public void setInternalFrame(JInternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    /**
     * @return the quotationHead
     */
    public QuotationHead getQuotationHead() {
        return quotationHead;
    }

    /**
     * @param quotationHead the quotationHead to set
     */
    public void setQuotationHead(QuotationHead quotationHead) {
        this.quotationHead = quotationHead;
    }
}