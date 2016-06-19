/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * QuotationInfoListPanel.java
 *
 * Created on Apr 7, 2013, 10:38:49 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.action.InvoiceAction;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.listener.BannedKeyListener;
import com.freebasicacc.ui.listener.LimitLengthListener;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import com.freebasicacc.util.UIUtil;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JTable;

/**
 *
 * @author benzyaa
 */
public class InvoiceInfoListPanel extends javax.swing.JPanel {

    /** Creates new form QuotationInfoListPanel */
    public InvoiceInfoListPanel() {
        initComponents();
      
        companyIdValueLabel.setVisible(false);
        this.customerIdHiddenValueLabel.setVisible(false);
        MainFrameAction mainFrameAction = new MainFrameAction();
        Object[] orderByArr = new OrderBy[10];
        orderByArr[0] = new OrderBy("", MessageUtil.getMessage("common.select"));
        orderByArr[1] = new OrderBy("INVOICE_HEAD_ID",    MessageUtil.getMessage("common.number"));
        orderByArr[2] = new OrderBy("PO_NUMBER",MessageUtil.getMessage("invoice.ponumber"));
        orderByArr[3] = new OrderBy("CREATE_DATE",MessageUtil.getMessage("common.date"));
        orderByArr[4] = new OrderBy("CUSTOMER_ID",MessageUtil.getMessage("invoice.company"));
        orderByArr[5] = new OrderBy("INVOICE_DUE_DATE",MessageUtil.getMessage("invoice.paymentduedate"));
        orderByArr[6] = new OrderBy("INVOICE_DUE_DATE",MessageUtil.getMessage("invoice.duedate"));
        orderByArr[7] = new OrderBy("TOTAL_AMOUNT",MessageUtil.getMessage("invoice.totalvalue"));
        orderByArr[8] = new OrderBy("VAT",MessageUtil.getMessage("invoice.tax"));
        orderByArr[9] = new OrderBy("NET_AMOUNT", MessageUtil.getMessage("invoice.netvalue"));        
        mainFrameAction.assignComboBoxModel(sortingByCombobox, orderByArr,"");
        InvoiceAction action = new InvoiceAction();
        action.searchInvoiceAction(this, true);
        this.buttonGroup = new ButtonGroup();
        this.buttonGroup.add(this.ascendantRadioButton);
        this.buttonGroup.add(this.descendantRadioButton);
        final InvoiceInfoListPanel invoiceForListener = this;
        this.pageNoTextField.addKeyListener(new KeyListener() {
         
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    InvoiceAction action = new InvoiceAction();
                    action.setInvoicePanelTableWithNextPrevious(invoiceForListener);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
         this.quotationListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.quotationListTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
          this.companyTextField.addKeyListener(new LimitLengthListener(50));
          this.invoiceDateTextField.addKeyListener(new LimitLengthListener(10));
          this.invoiceDateToTextField.addKeyListener(new LimitLengthListener(10));
          this.invoiceDueDateTextField.addKeyListener(new LimitLengthListener(10));
          this.invoiceDueDateToTextField.addKeyListener(new LimitLengthListener(10));
          this.invoiceIdTextField.addKeyListener(new LimitLengthListener(10));
          this.pageNoTextField.addKeyListener(new LimitLengthListener(3));
          this.paymentDueDateTextField.addKeyListener(new LimitLengthListener(10));
          this.paymentDueDateToTextField.addKeyListener(new LimitLengthListener(10));
          this.poNumberTextField.addKeyListener(new LimitLengthListener(10));
          
          this.invoiceDateTextField.addKeyListener(new BannedKeyListener("DATE"));
          this.invoiceDateToTextField.addKeyListener(new BannedKeyListener("DATE"));
          this.invoiceDueDateTextField.addKeyListener(new BannedKeyListener("DATE"));
          this.invoiceDueDateToTextField.addKeyListener(new BannedKeyListener("DATE"));
          this.invoiceIdTextField.addKeyListener(new BannedKeyListener("NUMBER"));
          this.pageNoTextField.addKeyListener(new BannedKeyListener("NUMBER"));
          this.paymentDueDateTextField.addKeyListener(new BannedKeyListener("DATE"));
          this.paymentDueDateToTextField.addKeyListener(new BannedKeyListener("DATE"));
    }
    
    public javax.swing.table.DefaultTableModel generateModelForColumn(){
        return new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    MessageUtil.getMessage("common.sequence"), 
                    MessageUtil.getMessage("common.number"),
                    MessageUtil.getMessage("invoice.ponumber"),
                    MessageUtil.getMessage("common.date"),
                    MessageUtil.getMessage("invoice.company"),   
                    MessageUtil.getMessage("invoice.paymentduedate"),
                    MessageUtil.getMessage("invoice.duedate"),
                    MessageUtil.getMessage("invoice.totalvalue"),
                    MessageUtil.getMessage("invoice.tax"),
                    MessageUtil.getMessage("invoice.netvalue")
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

        invoiceSearchLabel = new javax.swing.JLabel();
        invoiceIdLabel = new javax.swing.JLabel();
        invoiceIdTextField = new javax.swing.JTextField();
        poNumberLabel = new javax.swing.JLabel();
        poNumberTextField = new javax.swing.JTextField();
        companyLabel = new javax.swing.JLabel();
        companyTextField = new javax.swing.JTextField();
        companyIdValueLabel = new javax.swing.JLabel();
        invoiceDateLabel = new javax.swing.JLabel();
        invoiceDateTextField = new javax.swing.JTextField();
        invoiceDateToLabel = new javax.swing.JLabel();
        invoiceDateToTextField = new javax.swing.JTextField();
        customerPopupButton = new javax.swing.JButton();
        invoiceDateFromPicker = new javax.swing.JButton();
        invoiceDateToPicker = new javax.swing.JButton();
        sortingByLabel = new javax.swing.JLabel();
        sortingByCombobox = new javax.swing.JComboBox();
        ascendantRadioButton = new javax.swing.JRadioButton();
        descendantRadioButton = new javax.swing.JRadioButton();
        AddButton = new javax.swing.JButton();
        SearchButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        OpenEditPopup = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        pageNoLabel = new javax.swing.JLabel();
        pageNoTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        pageTotalLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rowPerpageCombobox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        quotationListTable = new javax.swing.JTable();
        paymentDueDateLabel = new javax.swing.JLabel();
        paymentDueDateTextField = new javax.swing.JTextField();
        paymentDueDateFromPicker = new javax.swing.JButton();
        paymentDueDateToLabel = new javax.swing.JLabel();
        paymentDueDateToTextField = new javax.swing.JTextField();
        paymentDueDateToPicker = new javax.swing.JButton();
        invoiceDueDateLabel = new javax.swing.JLabel();
        invoiceDueDateTextField = new javax.swing.JTextField();
        invoiceDueDateFromPicker = new javax.swing.JButton();
        invoiceDueDateToLabel = new javax.swing.JLabel();
        invoiceDueDateToTextField = new javax.swing.JTextField();
        invoiceDueDateToPicker = new javax.swing.JButton();
        customerIdHiddenValueLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        invoiceSearchLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        invoiceSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        invoiceSearchLabel.setText(bundle.getString("search.invoice")); // NOI18N

        invoiceIdLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceIdLabel.setText(bundle.getString("invoice.no")); // NOI18N

        invoiceIdTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        poNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        poNumberLabel.setText(bundle.getString("invoice.ponumber")); // NOI18N

        poNumberTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        companyLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        companyLabel.setText(bundle.getString("invoice.company")); // NOI18N

        companyTextField.setEditable(false);
        companyTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        invoiceDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDateLabel.setText(bundle.getString("invoice.date")); // NOI18N

        invoiceDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        invoiceDateToLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDateToLabel.setText(bundle.getString("common.to")); // NOI18N

        invoiceDateToTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDateToTextFieldActionPerformed(evt);
            }
        });

        customerPopupButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerPopupButton.setText("...");
        customerPopupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerPopupButtonActionPerformed(evt);
            }
        });

        invoiceDateFromPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        invoiceDateFromPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDateFromPickerActionPerformed(evt);
            }
        });

        invoiceDateToPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        invoiceDateToPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDateToPickerActionPerformed(evt);
            }
        });

        sortingByLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByLabel.setText(bundle.getString("common.orderby")); // NOI18N

        sortingByCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortingByCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortingByComboboxActionPerformed(evt);
            }
        });

        ascendantRadioButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ascendantRadioButton.setText(bundle.getString("common.ascending")); // NOI18N
        ascendantRadioButton.setName("ASC"); // NOI18N
        ascendantRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascendantRadioButtonActionPerformed(evt);
            }
        });

        descendantRadioButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        descendantRadioButton.setText(bundle.getString("common.descending")); // NOI18N
        descendantRadioButton.setName("DESC"); // NOI18N
        descendantRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descendantRadioButtonActionPerformed(evt);
            }
        });

        AddButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/13.png"))); // NOI18N
        AddButton.setText(bundle.getString("invoice.button.create")); // NOI18N
        AddButton.setToolTipText("");
        AddButton.setActionCommand("");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        SearchButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/65.png"))); // NOI18N
        SearchButton.setText(bundle.getString("common.search")); // NOI18N
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        ClearButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ClearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/45.png"))); // NOI18N
        ClearButton.setText(bundle.getString("common.clear")); // NOI18N
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        OpenEditPopup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        OpenEditPopup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/65.png"))); // NOI18N
        OpenEditPopup.setText(bundle.getString("common.viewselecteddata")); // NOI18N
        OpenEditPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenEditPopupActionPerformed(evt);
            }
        });

        previousButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/56.png"))); // NOI18N
        previousButton.setText(bundle.getString("common.previous")); // NOI18N
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/57.png"))); // NOI18N
        nextButton.setText(bundle.getString("common.next")); // NOI18N
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        pageNoLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageNoLabel.setText(bundle.getString("common.pageno")); // NOI18N

        pageNoTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageNoTextField.setText("1");
        pageNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageNoTextFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText(bundle.getString("common.from")); // NOI18N

        pageTotalLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageTotalLabel.setText("jLabel7");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText(bundle.getString("common.rowperpage")); // NOI18N

        rowPerpageCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rowPerpageCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50" }));
        rowPerpageCombobox.setToolTipText("");
        rowPerpageCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowPerpageComboboxActionPerformed(evt);
            }
        });

        quotationListTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationListTable.setModel(this.generateModelForColumn());
        jScrollPane1.setViewportView(quotationListTable);

        paymentDueDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentDueDateLabel.setText(bundle.getString("invoice.paymentduedate")); // NOI18N

        paymentDueDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        paymentDueDateFromPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        paymentDueDateFromPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentDueDateFromPickerActionPerformed(evt);
            }
        });

        paymentDueDateToLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentDueDateToLabel.setText(bundle.getString("common.to")); // NOI18N

        paymentDueDateToTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentDueDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentDueDateToTextFieldActionPerformed(evt);
            }
        });

        paymentDueDateToPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        paymentDueDateToPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentDueDateToPickerActionPerformed(evt);
            }
        });

        invoiceDueDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDueDateLabel.setText(bundle.getString("invoice.duedate")); // NOI18N

        invoiceDueDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        invoiceDueDateFromPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        invoiceDueDateFromPicker.setToolTipText("");
        invoiceDueDateFromPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDueDateFromPickerActionPerformed(evt);
            }
        });

        invoiceDueDateToLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDueDateToLabel.setText(bundle.getString("common.to")); // NOI18N

        invoiceDueDateToTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        invoiceDueDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDueDateToTextFieldActionPerformed(evt);
            }
        });

        invoiceDueDateToPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        invoiceDueDateToPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceDueDateToPickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(invoiceIdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                        .addGap(428, 428, 428))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(invoiceDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceDateFromPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceDateToLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(customerIdHiddenValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 589, Short.MAX_VALUE)
                                .addComponent(companyIdValueLabel)
                                .addGap(34, 34, 34))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(invoiceDateToPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(companyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerPopupButton)
                        .addGap(707, 707, 707))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceSearchLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(sortingByLabel)
                                            .addComponent(invoiceDueDateLabel)))
                                    .addComponent(paymentDueDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SearchButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClearButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(paymentDueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(paymentDueDateFromPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(paymentDueDateToLabel)
                                        .addGap(10, 10, 10)
                                        .addComponent(paymentDueDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(paymentDueDateToPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(invoiceDueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(invoiceDueDateFromPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(invoiceDueDateToLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(invoiceDueDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(invoiceDueDateToPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ascendantRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(descendantRadioButton))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(OpenEditPopup, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(previousButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pageNoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pageTotalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invoiceSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceIdLabel)
                    .addComponent(invoiceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poNumberLabel)
                    .addComponent(poNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyLabel)
                    .addComponent(companyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerPopupButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoiceDateFromPicker)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(invoiceDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(invoiceDateLabel))
                    .addComponent(invoiceDateToLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(companyIdValueLabel)
                            .addComponent(customerIdHiddenValueLabel))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoiceDateToPicker))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(paymentDueDateFromPicker)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentDueDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentDueDateToLabel))
                        .addComponent(paymentDueDateToPicker))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paymentDueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(paymentDueDateLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(invoiceDueDateLabel)
                                .addComponent(invoiceDueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(invoiceDueDateToLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(invoiceDueDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(invoiceDueDateFromPicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sortingByLabel)
                            .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ascendantRadioButton)
                            .addComponent(descendantRadioButton)))
                    .addComponent(invoiceDueDateToPicker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchButton)
                    .addComponent(ClearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(OpenEditPopup)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(pageNoLabel)
                    .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(pageTotalLabel)
                    .addComponent(jLabel13)
                    .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
private void customerPopupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerPopupButtonActionPerformed
    MainFrameAction action = new MainFrameAction();
    JMenuItem menuItem = new JMenuItem();
    menuItem.setName("CUSTOMER_DIALOG_MENU_ITEM");
    action.openInternalFormByType((MainFrame)this.getMainFrame(), menuItem,this,null);
}//GEN-LAST:event_customerPopupButtonActionPerformed

private void invoiceDateToPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDateToPickerActionPerformed
    UIUtil util = new UIUtil();
    util.openDatepickerForTextField(this.invoiceDateToTextField,this.getMainFrame());
}//GEN-LAST:event_invoiceDateToPickerActionPerformed

private void invoiceDateFromPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDateFromPickerActionPerformed
    UIUtil util = new UIUtil();
    util.openDatepickerForTextField(this.invoiceDateTextField,this.getMainFrame());
}//GEN-LAST:event_invoiceDateFromPickerActionPerformed

private void sortingByComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingByComboboxActionPerformed

}//GEN-LAST:event_sortingByComboboxActionPerformed

private void ascendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendantRadioButtonActionPerformed
            this.setRadioSelectName(this.getAscendantRadioButton().getName());
}//GEN-LAST:event_ascendantRadioButtonActionPerformed

private void descendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendantRadioButtonActionPerformed
            this.setRadioSelectName(this.getDescendantRadioButton().getName());
}//GEN-LAST:event_descendantRadioButtonActionPerformed

private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
      MainFrameAction action = new MainFrameAction();
      JMenuItem item = new  JMenuItem();
      item.setName("INVOICE_ADD_MENU_ITEM");
      action.openInternalFormByType((MainFrame)this.getMainFrame(), item, this, null);
}//GEN-LAST:event_AddButtonActionPerformed

private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
    InvoiceAction action = new InvoiceAction();
    this.pageNoTextField.setText("1");
    action.searchInvoiceAction(this,false);
}//GEN-LAST:event_SearchButtonActionPerformed

private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
    InvoiceAction action = new InvoiceAction();
    action.searchInvoiceAction(this,true);
    assignDefaultValue();
}//GEN-LAST:event_ClearButtonActionPerformed

private void OpenEditPopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenEditPopupActionPerformed
        InvoiceAction action = new InvoiceAction();
        action.openInvoiceViewFormAction(this);
}//GEN-LAST:event_OpenEditPopupActionPerformed

private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
           int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            pageNo = pageNo <= 1 ? 1 : pageNo-1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            InvoiceAction action = new InvoiceAction();
            action.setInvoicePanelTableWithNextPrevious(this);
}//GEN-LAST:event_previousButtonActionPerformed

private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            int totalPage = Integer.parseInt(String.valueOf(this.getPageTotalLabel().getText()));
            pageNo = pageNo +1;
            pageNo = pageNo > totalPage ? totalPage : pageNo;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            InvoiceAction action = new InvoiceAction();
            action.setInvoicePanelTableWithNextPrevious(this);
}//GEN-LAST:event_nextButtonActionPerformed

private void pageNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageNoTextFieldActionPerformed
                
}//GEN-LAST:event_pageNoTextFieldActionPerformed

private void rowPerpageComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowPerpageComboboxActionPerformed
            String pageNoStr = String.valueOf(this.getPageNoTextField().getText());
            int pageNo = StringUtil.isContainText(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            InvoiceAction action = new InvoiceAction();
            action.setInvoicePanelTableWithNextPrevious(this);
}//GEN-LAST:event_rowPerpageComboboxActionPerformed

private void invoiceDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDateToTextFieldActionPerformed

}//GEN-LAST:event_invoiceDateToTextFieldActionPerformed

private void paymentDueDateFromPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentDueDateFromPickerActionPerformed
             UIUtil util = new UIUtil();
            util.openDatepickerForTextField(this.paymentDueDateTextField,this.getMainFrame());
}//GEN-LAST:event_paymentDueDateFromPickerActionPerformed

private void paymentDueDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentDueDateToTextFieldActionPerformed

}//GEN-LAST:event_paymentDueDateToTextFieldActionPerformed

private void paymentDueDateToPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentDueDateToPickerActionPerformed
             UIUtil util = new UIUtil();
            util.openDatepickerForTextField(this.paymentDueDateToTextField,this.getMainFrame());
}//GEN-LAST:event_paymentDueDateToPickerActionPerformed

private void invoiceDueDateFromPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDueDateFromPickerActionPerformed
               UIUtil util = new UIUtil();
            util.openDatepickerForTextField(this.invoiceDueDateTextField,this.getMainFrame());
}//GEN-LAST:event_invoiceDueDateFromPickerActionPerformed

private void invoiceDueDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDueDateToTextFieldActionPerformed

}//GEN-LAST:event_invoiceDueDateToTextFieldActionPerformed

private void invoiceDueDateToPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceDueDateToPickerActionPerformed
            UIUtil util = new UIUtil();
            util.openDatepickerForTextField(this.invoiceDueDateToTextField,this.getMainFrame());
}//GEN-LAST:event_invoiceDueDateToPickerActionPerformed

private void assignDefaultValue(){
    this.companyIdValueLabel.setText("");
    this.companyTextField.setText("");
    this.invoiceDateTextField.setText("");
    this.invoiceDateToTextField.setText("");
    this.invoiceDueDateTextField.setText("");
    this.invoiceDueDateToTextField.setText("");
    this.invoiceIdTextField.setText("");
    this.pageNoTextField.setText("");
    this.paymentDueDateTextField.setText("");
    this.paymentDueDateToTextField.setText("");
    this.poNumberTextField.setText("");
    this.rowPerpageCombobox.setSelectedIndex(0);
    this.sortingByCombobox.setSelectedIndex(0);
    this.buttonGroup.clearSelection();
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton AddButton;
    protected javax.swing.JButton ClearButton;
    protected javax.swing.JButton OpenEditPopup;
    protected javax.swing.JButton SearchButton;
    protected javax.swing.JRadioButton ascendantRadioButton;
    protected javax.swing.JLabel companyIdValueLabel;
    protected javax.swing.JLabel companyLabel;
    protected javax.swing.JTextField companyTextField;
    protected javax.swing.JLabel customerIdHiddenValueLabel;
    protected javax.swing.JButton customerPopupButton;
    protected javax.swing.JRadioButton descendantRadioButton;
    protected javax.swing.JButton invoiceDateFromPicker;
    protected javax.swing.JLabel invoiceDateLabel;
    protected javax.swing.JTextField invoiceDateTextField;
    protected javax.swing.JLabel invoiceDateToLabel;
    protected javax.swing.JButton invoiceDateToPicker;
    protected javax.swing.JTextField invoiceDateToTextField;
    protected javax.swing.JButton invoiceDueDateFromPicker;
    protected javax.swing.JLabel invoiceDueDateLabel;
    protected javax.swing.JTextField invoiceDueDateTextField;
    protected javax.swing.JLabel invoiceDueDateToLabel;
    protected javax.swing.JButton invoiceDueDateToPicker;
    protected javax.swing.JTextField invoiceDueDateToTextField;
    protected javax.swing.JLabel invoiceIdLabel;
    protected javax.swing.JTextField invoiceIdTextField;
    protected javax.swing.JLabel invoiceSearchLabel;
    protected javax.swing.JLabel jLabel12;
    protected javax.swing.JLabel jLabel13;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JButton nextButton;
    protected javax.swing.JLabel pageNoLabel;
    protected javax.swing.JTextField pageNoTextField;
    protected javax.swing.JLabel pageTotalLabel;
    protected javax.swing.JButton paymentDueDateFromPicker;
    protected javax.swing.JLabel paymentDueDateLabel;
    protected javax.swing.JTextField paymentDueDateTextField;
    protected javax.swing.JLabel paymentDueDateToLabel;
    protected javax.swing.JButton paymentDueDateToPicker;
    protected javax.swing.JTextField paymentDueDateToTextField;
    protected javax.swing.JLabel poNumberLabel;
    protected javax.swing.JTextField poNumberTextField;
    protected javax.swing.JButton previousButton;
    protected javax.swing.JTable quotationListTable;
    protected javax.swing.JComboBox rowPerpageCombobox;
    protected javax.swing.JComboBox sortingByCombobox;
    protected javax.swing.JLabel sortingByLabel;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JFrame mainFrame;
    private List dataList;
    private ButtonGroup buttonGroup;
    private String radioSelectName;

    /**
     * @return the AddButton
     */
    public javax.swing.JButton getAddButton() {
        return AddButton;
    }

    /**
     * @param AddButton the AddButton to set
     */
    public void setAddButton(javax.swing.JButton AddButton) {
        this.AddButton = AddButton;
    }

   
    /**
     * @return the ClearButton
     */
    public javax.swing.JButton getClearButton() {
        return ClearButton;
    }

    /**
     * @param ClearButton the ClearButton to set
     */
    public void setClearButton(javax.swing.JButton ClearButton) {
        this.ClearButton = ClearButton;
    }

    /**
     * @return the OpenEditPopup
     */
    public javax.swing.JButton getOpenEditPopup() {
        return OpenEditPopup;
    }

    /**
     * @param OpenEditPopup the OpenEditPopup to set
     */
    public void setOpenEditPopup(javax.swing.JButton OpenEditPopup) {
        this.OpenEditPopup = OpenEditPopup;
    }

    /**
     * @return the SearchButton
     */
    public javax.swing.JButton getSearchButton() {
        return SearchButton;
    }

    /**
     * @param SearchButton the SearchButton to set
     */
    public void setSearchButton(javax.swing.JButton SearchButton) {
        this.SearchButton = SearchButton;
    }

    /**
     * @return the ascendantRadioButton
     */
    public javax.swing.JRadioButton getAscendantRadioButton() {
        return ascendantRadioButton;
    }

    /**
     * @param ascendantRadioButton the ascendantRadioButton to set
     */
    public void setAscendantRadioButton(javax.swing.JRadioButton ascendantRadioButton) {
        this.ascendantRadioButton = ascendantRadioButton;
    }

    /**
     * @return the companyIdValueLabel
     */
    public javax.swing.JLabel getCompanyIdValueLabel() {
        return companyIdValueLabel;
    }

    /**
     * @param companyIdValueLabel the companyIdValueLabel to set
     */
    public void setCompanyIdValueLabel(javax.swing.JLabel companyIdValueLabel) {
        this.companyIdValueLabel = companyIdValueLabel;
    }

    /**
     * @return the companyLabel
     */
    public javax.swing.JLabel getCompanyLabel() {
        return companyLabel;
    }

    /**
     * @param companyLabel the companyLabel to set
     */
    public void setCompanyLabel(javax.swing.JLabel companyLabel) {
        this.companyLabel = companyLabel;
    }

    /**
     * @return the companyTextField
     */
    public javax.swing.JTextField getCompanyTextField() {
        return companyTextField;
    }

    /**
     * @param companyTextField the companyTextField to set
     */
    public void setCompanyTextField(javax.swing.JTextField companyTextField) {
        this.companyTextField = companyTextField;
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
     * @return the customerPopupButton
     */
    public javax.swing.JButton getCustomerPopupButton() {
        return customerPopupButton;
    }

    /**
     * @param customerPopupButton the customerPopupButton to set
     */
    public void setCustomerPopupButton(javax.swing.JButton customerPopupButton) {
        this.customerPopupButton = customerPopupButton;
    }

    /**
     * @return the descendantRadioButton
     */
    public javax.swing.JRadioButton getDescendantRadioButton() {
        return descendantRadioButton;
    }

    /**
     * @param descendantRadioButton the descendantRadioButton to set
     */
    public void setDescendantRadioButton(javax.swing.JRadioButton descendantRadioButton) {
        this.descendantRadioButton = descendantRadioButton;
    }

    /**
     * @return the invoiceDateLabel
     */
    public javax.swing.JLabel getInvoiceDateLabel() {
        return invoiceDateLabel;
    }

    /**
     * @param invoiceDateLabel the invoiceDateLabel to set
     */
    public void setInvoiceDateLabel(javax.swing.JLabel invoiceDateLabel) {
        this.invoiceDateLabel = invoiceDateLabel;
    }

    /**
     * @return the invoiceDateTextField
     */
    public javax.swing.JTextField getInvoiceDateTextField() {
        return invoiceDateTextField;
    }

    /**
     * @param invoiceDateTextField the invoiceDateTextField to set
     */
    public void setInvoiceDateTextField(javax.swing.JTextField invoiceDateTextField) {
        this.invoiceDateTextField = invoiceDateTextField;
    }

    /**
     * @return the invoiceDateToLabel
     */
    public javax.swing.JLabel getInvoiceDateToLabel() {
        return invoiceDateToLabel;
    }

    /**
     * @param invoiceDateToLabel the invoiceDateToLabel to set
     */
    public void setInvoiceDateToLabel(javax.swing.JLabel invoiceDateToLabel) {
        this.invoiceDateToLabel = invoiceDateToLabel;
    }

    /**
     * @return the invoiceDateToTextField
     */
    public javax.swing.JTextField getInvoiceDateToTextField() {
        return invoiceDateToTextField;
    }

    /**
     * @param invoiceDateToTextField the invoiceDateToTextField to set
     */
    public void setInvoiceDateToTextField(javax.swing.JTextField invoiceDateToTextField) {
        this.invoiceDateToTextField = invoiceDateToTextField;
    }

    /**
     * @return the invoiceDueDateLabel
     */
    public javax.swing.JLabel getInvoiceDueDateLabel() {
        return invoiceDueDateLabel;
    }

    /**
     * @param invoiceDueDateLabel the invoiceDueDateLabel to set
     */
    public void setInvoiceDueDateLabel(javax.swing.JLabel invoiceDueDateLabel) {
        this.invoiceDueDateLabel = invoiceDueDateLabel;
    }

    /**
     * @return the invoiceDueDateTextField
     */
    public javax.swing.JTextField getInvoiceDueDateTextField() {
        return invoiceDueDateTextField;
    }

    /**
     * @param invoiceDueDateTextField the invoiceDueDateTextField to set
     */
    public void setInvoiceDueDateTextField(javax.swing.JTextField invoiceDueDateTextField) {
        this.invoiceDueDateTextField = invoiceDueDateTextField;
    }

    /**
     * @return the invoiceDueDateToLabel
     */
    public javax.swing.JLabel getInvoiceDueDateToLabel() {
        return invoiceDueDateToLabel;
    }

    /**
     * @param invoiceDueDateToLabel the invoiceDueDateToLabel to set
     */
    public void setInvoiceDueDateToLabel(javax.swing.JLabel invoiceDueDateToLabel) {
        this.invoiceDueDateToLabel = invoiceDueDateToLabel;
    }

    /**
     * @return the invoiceDueDateToTextField
     */
    public javax.swing.JTextField getInvoiceDueDateToTextField() {
        return invoiceDueDateToTextField;
    }

    /**
     * @param invoiceDueDateToTextField the invoiceDueDateToTextField to set
     */
    public void setInvoiceDueDateToTextField(javax.swing.JTextField invoiceDueDateToTextField) {
        this.invoiceDueDateToTextField = invoiceDueDateToTextField;
    }

    /**
     * @return the invoiceIdLabel
     */
    public javax.swing.JLabel getInvoiceIdLabel() {
        return invoiceIdLabel;
    }

    /**
     * @param invoiceIdLabel the invoiceIdLabel to set
     */
    public void setInvoiceIdLabel(javax.swing.JLabel invoiceIdLabel) {
        this.invoiceIdLabel = invoiceIdLabel;
    }

    /**
     * @return the invoiceIdTextField
     */
    public javax.swing.JTextField getInvoiceIdTextField() {
        return invoiceIdTextField;
    }

    /**
     * @param invoiceIdTextField the invoiceIdTextField to set
     */
    public void setInvoiceIdTextField(javax.swing.JTextField invoiceIdTextField) {
        this.invoiceIdTextField = invoiceIdTextField;
    }

    /**
     * @return the invoiceSearchLabel
     */
    public javax.swing.JLabel getInvoiceSearchLabel() {
        return invoiceSearchLabel;
    }

    /**
     * @param invoiceSearchLabel the invoiceSearchLabel to set
     */
    public void setInvoiceSearchLabel(javax.swing.JLabel invoiceSearchLabel) {
        this.invoiceSearchLabel = invoiceSearchLabel;
    }

    /**
     * @return the jButton2
     */
    public javax.swing.JButton getjButton2() {
        return invoiceDateFromPicker;
    }

    /**
     * @param jButton2 the jButton2 to set
     */
    public void setjButton2(javax.swing.JButton jButton2) {
        this.invoiceDateFromPicker = jButton2;
    }

    /**
     * @return the jButton3
     */
    public javax.swing.JButton getjButton3() {
        return invoiceDateToPicker;
    }

    /**
     * @param jButton3 the jButton3 to set
     */
    public void setjButton3(javax.swing.JButton jButton3) {
        this.invoiceDateToPicker = jButton3;
    }

    /**
     * @return the jButton4
     */
    public javax.swing.JButton getjButton4() {
        return paymentDueDateFromPicker;
    }

    /**
     * @param jButton4 the jButton4 to set
     */
    public void setjButton4(javax.swing.JButton jButton4) {
        this.paymentDueDateFromPicker = jButton4;
    }

    /**
     * @return the jButton5
     */
    public javax.swing.JButton getjButton5() {
        return paymentDueDateToPicker;
    }

    /**
     * @param jButton5 the jButton5 to set
     */
    public void setjButton5(javax.swing.JButton jButton5) {
        this.paymentDueDateToPicker = jButton5;
    }

    /**
     * @return the jButton6
     */
    public javax.swing.JButton getjButton6() {
        return invoiceDueDateFromPicker;
    }

    /**
     * @param jButton6 the jButton6 to set
     */
    public void setjButton6(javax.swing.JButton jButton6) {
        this.invoiceDueDateFromPicker = jButton6;
    }

    /**
     * @return the jButton7
     */
    public javax.swing.JButton getjButton7() {
        return invoiceDueDateToPicker;
    }

    /**
     * @param jButton7 the jButton7 to set
     */
    public void setjButton7(javax.swing.JButton jButton7) {
        this.invoiceDueDateToPicker = jButton7;
    }

    /**
     * @return the jLabel12
     */
    public javax.swing.JLabel getjLabel12() {
        return jLabel12;
    }

    /**
     * @param jLabel12 the jLabel12 to set
     */
    public void setjLabel12(javax.swing.JLabel jLabel12) {
        this.jLabel12 = jLabel12;
    }

    /**
     * @return the jLabel13
     */
    public javax.swing.JLabel getjLabel13() {
        return jLabel13;
    }

    /**
     * @param jLabel13 the jLabel13 to set
     */
    public void setjLabel13(javax.swing.JLabel jLabel13) {
        this.jLabel13 = jLabel13;
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
     * @return the nextButton
     */
    public javax.swing.JButton getNextButton() {
        return nextButton;
    }

    /**
     * @param nextButton the nextButton to set
     */
    public void setNextButton(javax.swing.JButton nextButton) {
        this.nextButton = nextButton;
    }

    /**
     * @return the pageNoLabel
     */
    public javax.swing.JLabel getPageNoLabel() {
        return pageNoLabel;
    }

    /**
     * @param pageNoLabel the pageNoLabel to set
     */
    public void setPageNoLabel(javax.swing.JLabel pageNoLabel) {
        this.pageNoLabel = pageNoLabel;
    }

    /**
     * @return the pageNoTextField
     */
    public javax.swing.JTextField getPageNoTextField() {
        return pageNoTextField;
    }

    /**
     * @param pageNoTextField the pageNoTextField to set
     */
    public void setPageNoTextField(javax.swing.JTextField pageNoTextField) {
        this.pageNoTextField = pageNoTextField;
    }

    /**
     * @return the pageTotalLabel
     */
    public javax.swing.JLabel getPageTotalLabel() {
        return pageTotalLabel;
    }

    /**
     * @param pageTotalLabel the pageTotalLabel to set
     */
    public void setPageTotalLabel(javax.swing.JLabel pageTotalLabel) {
        this.pageTotalLabel = pageTotalLabel;
    }

    /**
     * @return the paymentDueDateLabel
     */
    public javax.swing.JLabel getPaymentDueDateLabel() {
        return paymentDueDateLabel;
    }

    /**
     * @param paymentDueDateLabel the paymentDueDateLabel to set
     */
    public void setPaymentDueDateLabel(javax.swing.JLabel paymentDueDateLabel) {
        this.paymentDueDateLabel = paymentDueDateLabel;
    }

    /**
     * @return the paymentDueDateTextField
     */
    public javax.swing.JTextField getPaymentDueDateTextField() {
        return paymentDueDateTextField;
    }

    /**
     * @param paymentDueDateTextField the paymentDueDateTextField to set
     */
    public void setPaymentDueDateTextField(javax.swing.JTextField paymentDueDateTextField) {
        this.paymentDueDateTextField = paymentDueDateTextField;
    }

    /**
     * @return the paymentDueDateToLabel
     */
    public javax.swing.JLabel getPaymentDueDateToLabel() {
        return paymentDueDateToLabel;
    }

    /**
     * @param paymentDueDateToLabel the paymentDueDateToLabel to set
     */
    public void setPaymentDueDateToLabel(javax.swing.JLabel paymentDueDateToLabel) {
        this.paymentDueDateToLabel = paymentDueDateToLabel;
    }

    /**
     * @return the paymentDueDateToTextField
     */
    public javax.swing.JTextField getPaymentDueDateToTextField() {
        return paymentDueDateToTextField;
    }

    /**
     * @param paymentDueDateToTextField the paymentDueDateToTextField to set
     */
    public void setPaymentDueDateToTextField(javax.swing.JTextField paymentDueDateToTextField) {
        this.paymentDueDateToTextField = paymentDueDateToTextField;
    }

    /**
     * @return the poNumberLabel
     */
    public javax.swing.JLabel getPoNumberLabel() {
        return poNumberLabel;
    }

    /**
     * @param poNumberLabel the poNumberLabel to set
     */
    public void setPoNumberLabel(javax.swing.JLabel poNumberLabel) {
        this.poNumberLabel = poNumberLabel;
    }

    /**
     * @return the poNumberTextField
     */
    public javax.swing.JTextField getPoNumberTextField() {
        return poNumberTextField;
    }

    /**
     * @param poNumberTextField the poNumberTextField to set
     */
    public void setPoNumberTextField(javax.swing.JTextField poNumberTextField) {
        this.poNumberTextField = poNumberTextField;
    }

    /**
     * @return the previousButton
     */
    public javax.swing.JButton getPreviousButton() {
        return previousButton;
    }

    /**
     * @param previousButton the previousButton to set
     */
    public void setPreviousButton(javax.swing.JButton previousButton) {
        this.previousButton = previousButton;
    }

    /**
     * @return the quotationListTable
     */
    public javax.swing.JTable getQuotationListTable() {
        return quotationListTable;
    }

    /**
     * @param quotationListTable the quotationListTable to set
     */
    public void setQuotationListTable(javax.swing.JTable quotationListTable) {
        this.quotationListTable = quotationListTable;
    }

    /**
     * @return the rowPerpageCombobox
     */
    public javax.swing.JComboBox getRowPerpageCombobox() {
        return rowPerpageCombobox;
    }

    /**
     * @param rowPerpageCombobox the rowPerpageCombobox to set
     */
    public void setRowPerpageCombobox(javax.swing.JComboBox rowPerpageCombobox) {
        this.rowPerpageCombobox = rowPerpageCombobox;
    }

    /**
     * @return the sortingByCombobox
     */
    public javax.swing.JComboBox getSortingByCombobox() {
        return sortingByCombobox;
    }

    /**
     * @param sortingByCombobox the sortingByCombobox to set
     */
    public void setSortingByCombobox(javax.swing.JComboBox sortingByCombobox) {
        this.sortingByCombobox = sortingByCombobox;
    }

    /**
     * @return the sortingByLabel
     */
    public javax.swing.JLabel getSortingByLabel() {
        return sortingByLabel;
    }

    /**
     * @param sortingByLabel the sortingByLabel to set
     */
    public void setSortingByLabel(javax.swing.JLabel sortingByLabel) {
        this.sortingByLabel = sortingByLabel;
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
     * @return the dataList
     */
    public List getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    /**
     * @return the buttonGroup
     */
    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     * @param buttonGroup the buttonGroup to set
     */
    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    /**
     * @return the radioSelectName
     */
    public String getRadioSelectName() {
        return radioSelectName;
    }

    /**
     * @param radioSelectName the radioSelectName to set
     */
    public void setRadioSelectName(String radioSelectName) {
        this.radioSelectName = radioSelectName;
    }

    
}