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

import com.freebasicacc.action.QuotationAction;
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
public class QuotationInfoListPanel extends javax.swing.JPanel {

    /** Creates new form QuotationInfoListPanel */
    public QuotationInfoListPanel() {
        initComponents();
      
        companyIdValueLabel.setVisible(false);
        MainFrameAction mainFrameAction = new MainFrameAction();
        Object[] orderByArr = new OrderBy[11];
        orderByArr[0] = new OrderBy("",MessageUtil.getMessage("common.select"));
        orderByArr[1] = new OrderBy("QUOTATION_HEAD_ID",MessageUtil.getMessage("quotation.no"));
        orderByArr[2] = new OrderBy("QUOTATION_TITLE", MessageUtil.getMessage("quotation.title"));
        orderByArr[3] = new OrderBy("CUSTOMER_ID",MessageUtil.getMessage("quotation.customer"));
        orderByArr[4] = new OrderBy("QUOTATION_DATE",MessageUtil.getMessage("quotation.date"));
        orderByArr[5] = new OrderBy("DELIVERY_PLACE",MessageUtil.getMessage("quotation.deliveryplace"));
        orderByArr[6] = new OrderBy("LEAD_DATE",MessageUtil.getMessage("quotation.leaddate"));
        orderByArr[7] = new OrderBy("PAYMENT_TERM",MessageUtil.getMessage("quotation.paymentterm"));
        orderByArr[8] = new OrderBy("TOTAL_VALUE",MessageUtil.getMessage("quotation.totalvalue"));
        orderByArr[9] = new OrderBy("VAT",MessageUtil.getMessage("quotation.valueaddedtax"));
        orderByArr[10] = new OrderBy("NET_VALUE",MessageUtil.getMessage("quotation.netvalue"));
        mainFrameAction.assignComboBoxModel(sortingByCombobox, orderByArr,"");
        
        QuotationAction action = new QuotationAction();
        action.searchQuotationAction(this, true);
        this.buttonGroup = new ButtonGroup();
        this.buttonGroup.add(this.ascendantRadioButton);
        this.buttonGroup.add(this.descendantRadioButton);
        final QuotationInfoListPanel quotationForListener = this;
        this.pageNoTextField.addKeyListener(new KeyListener() {
         
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    QuotationAction action = new QuotationAction();
                    action.setQuotationPanelTableWithNextPrevious(quotationForListener);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
      this.quotationListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      this.quotationListTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
      this.companyTextField.addKeyListener(new LimitLengthListener(50));
      this.companyTextField.setEditable(false);
      this.deliveryPlaceTextField.addKeyListener(new LimitLengthListener(200));
      this.leadDateTextField.addKeyListener(new LimitLengthListener(10));
      this.leadDateToTextField.addKeyListener(new LimitLengthListener(10));
      this.pageNoTextField.addKeyListener(new LimitLengthListener(3));
      this.paymentTermTextField.addKeyListener(new LimitLengthListener(50));
      this.quotationDateTextField.addKeyListener(new LimitLengthListener(10));
      this.quotationDateToTextField.addKeyListener(new LimitLengthListener(10));
      this.quotationIdTextField.addKeyListener(new LimitLengthListener(10));
      this.quotationTitleTextField.addKeyListener(new LimitLengthListener(200));   
      this.leadDateTextField.addKeyListener(new BannedKeyListener("DATE"));
      this.leadDateToTextField.addKeyListener(new BannedKeyListener("DATE"));
      this.pageNoTextField.addKeyListener(new BannedKeyListener("NUMBER"));
      this.quotationDateTextField.addKeyListener(new BannedKeyListener("DATE"));
      this.quotationDateToTextField.addKeyListener(new BannedKeyListener("DATE"));  
      this.quotationIdTextField.addKeyListener(new BannedKeyListener("NUMBER"));
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

        quotationSearchLabel = new javax.swing.JLabel();
        quotationIdLabel = new javax.swing.JLabel();
        quotationIdTextField = new javax.swing.JTextField();
        quotationTitleLabel = new javax.swing.JLabel();
        quotationTitleTextField = new javax.swing.JTextField();
        companyLabel = new javax.swing.JLabel();
        companyTextField = new javax.swing.JTextField();
        companyIdValueLabel = new javax.swing.JLabel();
        quotationDateLabel = new javax.swing.JLabel();
        quotationDateTextField = new javax.swing.JTextField();
        quotationDateToLabel = new javax.swing.JLabel();
        quotationDateToTextField = new javax.swing.JTextField();
        deliveryPlaceLabel = new javax.swing.JLabel();
        deliveryPlaceTextField = new javax.swing.JTextField();
        leadDateLabel = new javax.swing.JLabel();
        leadDateTextField = new javax.swing.JTextField();
        leadDateToLabel = new javax.swing.JLabel();
        leadDateToTextField = new javax.swing.JTextField();
        paymentTermLabel = new javax.swing.JLabel();
        paymentTermTextField = new javax.swing.JTextField();
        customerPopupButton = new javax.swing.JButton();
        quotationDateFromPicker = new javax.swing.JButton();
        quotationDateToPicker = new javax.swing.JButton();
        leadDateFromPicker = new javax.swing.JButton();
        leadDateToPicker = new javax.swing.JButton();
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

        setPreferredSize(new java.awt.Dimension(1024, 768));

        quotationSearchLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quotationSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        quotationSearchLabel.setText(bundle.getString("search.quotation")); // NOI18N

        quotationIdLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationIdLabel.setText(bundle.getString("quotation.no")); // NOI18N

        quotationIdTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        quotationTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationTitleLabel.setText(bundle.getString("quotation.title")); // NOI18N

        quotationTitleTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        companyLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        companyLabel.setText(bundle.getString("quotation.customer")); // NOI18N

        companyTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        quotationDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationDateLabel.setText(bundle.getString("quotation.date")); // NOI18N

        quotationDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        quotationDateToLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationDateToLabel.setText(bundle.getString("common.to")); // NOI18N

        quotationDateToTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quotationDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quotationDateToTextFieldActionPerformed(evt);
            }
        });

        deliveryPlaceLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deliveryPlaceLabel.setText(bundle.getString("quotation.deliveryplace")); // NOI18N

        deliveryPlaceTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        leadDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        leadDateLabel.setText(bundle.getString("quotation.leaddate")); // NOI18N

        leadDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        leadDateToLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        leadDateToLabel.setText(bundle.getString("common.to")); // NOI18N

        leadDateToTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        leadDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leadDateToTextFieldActionPerformed(evt);
            }
        });

        paymentTermLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentTermLabel.setText(bundle.getString("quotation.paymentterm")); // NOI18N

        paymentTermTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        customerPopupButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerPopupButton.setText("...");
        customerPopupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerPopupButtonActionPerformed(evt);
            }
        });

        quotationDateFromPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        quotationDateFromPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quotationDateFromPickerActionPerformed(evt);
            }
        });

        quotationDateToPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        quotationDateToPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quotationDateToPickerActionPerformed(evt);
            }
        });

        leadDateFromPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        leadDateFromPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leadDateFromPickerActionPerformed(evt);
            }
        });

        leadDateToPicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        leadDateToPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leadDateToPickerActionPerformed(evt);
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
        AddButton.setText(bundle.getString("quotation.button.create")); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quotationSearchLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quotationIdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quotationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quotationTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quotationTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leadDateLabel)
                            .addComponent(quotationDateLabel)
                            .addComponent(sortingByLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(quotationDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(companyIdValueLabel)
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(quotationDateFromPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(quotationDateToLabel)
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(leadDateToTextField)
                                            .addComponent(quotationDateToTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(quotationDateToPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(deliveryPlaceLabel)
                                                .addGap(4, 4, 4)
                                                .addComponent(deliveryPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(leadDateToPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(ascendantRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(descendantRadioButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(leadDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leadDateFromPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(leadDateToLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SearchButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClearButton)))
                                .addGap(571, 571, 571))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(companyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(companyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerPopupButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymentTermLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymentTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quotationSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quotationIdLabel)
                    .addComponent(quotationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quotationTitleLabel)
                    .addComponent(quotationTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyLabel)
                    .addComponent(companyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerPopupButton)
                    .addComponent(paymentTermLabel)
                    .addComponent(paymentTermTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyIdValueLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quotationDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(quotationDateLabel))
                    .addComponent(quotationDateFromPicker)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quotationDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(quotationDateToLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deliveryPlaceLabel)
                        .addComponent(deliveryPlaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(quotationDateToPicker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(leadDateLabel)
                        .addComponent(leadDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(leadDateToLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leadDateFromPicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(leadDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leadDateToPicker))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortingByLabel)
                    .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ascendantRadioButton)
                    .addComponent(descendantRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void customerPopupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerPopupButtonActionPerformed
    MainFrameAction action = new MainFrameAction();
    JMenuItem menuItem = new JMenuItem();
    menuItem.setName("CUSTOMER_DIALOG_MENU_ITEM");
    action.openInternalFormByType((MainFrame)this.mainFrame, menuItem,this,null);// TODO add your handling code here:
}//GEN-LAST:event_customerPopupButtonActionPerformed

private void quotationDateToPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quotationDateToPickerActionPerformed
     UIUtil util = new UIUtil();
     util.openDatepickerForTextField(this.quotationDateToTextField,this.getMainFrame());
}//GEN-LAST:event_quotationDateToPickerActionPerformed

private void quotationDateFromPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quotationDateFromPickerActionPerformed
     UIUtil util = new UIUtil();
     util.openDatepickerForTextField(this.quotationDateTextField,this.getMainFrame());
}//GEN-LAST:event_quotationDateFromPickerActionPerformed

private void sortingByComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingByComboboxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_sortingByComboboxActionPerformed

private void ascendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendantRadioButtonActionPerformed
            this.setRadioSelectName(this.ascendantRadioButton.getName());
}//GEN-LAST:event_ascendantRadioButtonActionPerformed

private void descendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendantRadioButtonActionPerformed
            this.setRadioSelectName(this.descendantRadioButton.getName());
}//GEN-LAST:event_descendantRadioButtonActionPerformed

private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
      MainFrameAction action = new MainFrameAction();
      JMenuItem item = new  JMenuItem();
      item.setName("QUOTATION_ADD_MENU_ITEM");
      action.openInternalFormByType((MainFrame)this.getMainFrame(), item, this, null);
}//GEN-LAST:event_AddButtonActionPerformed

private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
    QuotationAction action = new QuotationAction();
    this.pageNoTextField.setText("1");
    action.searchQuotationAction(this, false);
}//GEN-LAST:event_SearchButtonActionPerformed

private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
    this.assignDefaultValue();
    QuotationAction action = new QuotationAction();
    action.searchQuotationAction(this, true);
}//GEN-LAST:event_ClearButtonActionPerformed

private void OpenEditPopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenEditPopupActionPerformed
        QuotationAction action = new QuotationAction();   
         action.openQuotationViewFormAction(this);
}//GEN-LAST:event_OpenEditPopupActionPerformed

private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
           int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            pageNo = pageNo <= 1 ? 1 : pageNo-1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
           QuotationAction action = new QuotationAction();
            action.setQuotationPanelTableWithNextPrevious(this);
}//GEN-LAST:event_previousButtonActionPerformed

private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            int totalPage = Integer.parseInt(String.valueOf(this.getPageTotalLabel().getText()));
            pageNo = pageNo +1;
            pageNo = pageNo > totalPage ? totalPage : pageNo;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            QuotationAction action = new QuotationAction();
            action.setQuotationPanelTableWithNextPrevious(this);
}//GEN-LAST:event_nextButtonActionPerformed

private void pageNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageNoTextFieldActionPerformed
                
}//GEN-LAST:event_pageNoTextFieldActionPerformed

private void rowPerpageComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowPerpageComboboxActionPerformed
            String pageNoStr = String.valueOf(this.getPageNoTextField().getText());
            int pageNo = StringUtil.isContainText(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            QuotationAction action = new QuotationAction();
            action.setQuotationPanelTableWithNextPrevious(this);
}//GEN-LAST:event_rowPerpageComboboxActionPerformed

private void quotationDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quotationDateToTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_quotationDateToTextFieldActionPerformed

private void leadDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leadDateToTextFieldActionPerformed

}//GEN-LAST:event_leadDateToTextFieldActionPerformed

private void leadDateToPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leadDateToPickerActionPerformed
     UIUtil util = new UIUtil();
     util.openDatepickerForTextField(this.leadDateToTextField,this.getMainFrame());
}//GEN-LAST:event_leadDateToPickerActionPerformed

private void leadDateFromPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leadDateFromPickerActionPerformed
     UIUtil util = new UIUtil();
     util.openDatepickerForTextField(this.leadDateTextField,this.getMainFrame());
}//GEN-LAST:event_leadDateFromPickerActionPerformed

private void assignDefaultValue(){
    this.ascendantRadioButton.setSelected(false);
    this.companyIdValueLabel.setText("");
    this.companyTextField.setText("");
    this.deliveryPlaceTextField.setText("");
    this.descendantRadioButton.setSelected(false);
    this.leadDateTextField.setText("");
    this.leadDateToTextField.setText("");
    this.pageNoTextField.setText("1");
    this.paymentTermTextField.setText("");
    this.quotationDateTextField.setText("");
    this.quotationDateToTextField.setText("");
    this.quotationIdTextField.setText("");
    this.quotationTitleTextField.setText("");
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
    protected javax.swing.JButton customerPopupButton;
    protected javax.swing.JLabel deliveryPlaceLabel;
    protected javax.swing.JTextField deliveryPlaceTextField;
    protected javax.swing.JRadioButton descendantRadioButton;
    protected javax.swing.JLabel jLabel12;
    protected javax.swing.JLabel jLabel13;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JButton leadDateFromPicker;
    protected javax.swing.JLabel leadDateLabel;
    protected javax.swing.JTextField leadDateTextField;
    protected javax.swing.JLabel leadDateToLabel;
    protected javax.swing.JButton leadDateToPicker;
    protected javax.swing.JTextField leadDateToTextField;
    protected javax.swing.JButton nextButton;
    protected javax.swing.JLabel pageNoLabel;
    protected javax.swing.JTextField pageNoTextField;
    protected javax.swing.JLabel pageTotalLabel;
    protected javax.swing.JLabel paymentTermLabel;
    protected javax.swing.JTextField paymentTermTextField;
    protected javax.swing.JButton previousButton;
    protected javax.swing.JButton quotationDateFromPicker;
    protected javax.swing.JLabel quotationDateLabel;
    protected javax.swing.JTextField quotationDateTextField;
    protected javax.swing.JLabel quotationDateToLabel;
    protected javax.swing.JButton quotationDateToPicker;
    protected javax.swing.JTextField quotationDateToTextField;
    protected javax.swing.JLabel quotationIdLabel;
    protected javax.swing.JTextField quotationIdTextField;
    protected javax.swing.JTable quotationListTable;
    protected javax.swing.JLabel quotationSearchLabel;
    protected javax.swing.JLabel quotationTitleLabel;
    protected javax.swing.JTextField quotationTitleTextField;
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
     * @return the deliveryPlaceLabel
     */
    public javax.swing.JLabel getDeliveryPlaceLabel() {
        return deliveryPlaceLabel;
    }

    /**
     * @param deliveryPlaceLabel the deliveryPlaceLabel to set
     */
    public void setDeliveryPlaceLabel(javax.swing.JLabel deliveryPlaceLabel) {
        this.deliveryPlaceLabel = deliveryPlaceLabel;
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
     * @return the jButton1
     */
    public javax.swing.JButton getjButton1() {
        return customerPopupButton;
    }

    /**
     * @param jButton1 the jButton1 to set
     */
    public void setjButton1(javax.swing.JButton jButton1) {
        this.customerPopupButton = jButton1;
    }

    /**
     * @return the jButton2
     */
    public javax.swing.JButton getjButton2() {
        return quotationDateFromPicker;
    }

    /**
     * @param jButton2 the jButton2 to set
     */
    public void setjButton2(javax.swing.JButton jButton2) {
        this.quotationDateFromPicker = jButton2;
    }

    /**
     * @return the jButton3
     */
    public javax.swing.JButton getjButton3() {
        return quotationDateToPicker;
    }

    /**
     * @param jButton3 the jButton3 to set
     */
    public void setjButton3(javax.swing.JButton jButton3) {
        this.quotationDateToPicker = jButton3;
    }

    /**
     * @return the jButton4
     */
    public javax.swing.JButton getjButton4() {
        return leadDateFromPicker;
    }

    /**
     * @param jButton4 the jButton4 to set
     */
    public void setjButton4(javax.swing.JButton jButton4) {
        this.leadDateFromPicker = jButton4;
    }

    /**
     * @return the jButton5
     */
    public javax.swing.JButton getjButton5() {
        return leadDateToPicker;
    }

    /**
     * @param jButton5 the jButton5 to set
     */
    public void setjButton5(javax.swing.JButton jButton5) {
        this.leadDateToPicker = jButton5;
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
     * @return the leadDateLabel
     */
    public javax.swing.JLabel getLeadDateLabel() {
        return leadDateLabel;
    }

    /**
     * @param leadDateLabel the leadDateLabel to set
     */
    public void setLeadDateLabel(javax.swing.JLabel leadDateLabel) {
        this.leadDateLabel = leadDateLabel;
    }

    /**
     * @return the leadDateTextField
     */
    public javax.swing.JTextField getLeadDateTextField() {
        return leadDateTextField;
    }

    /**
     * @param leadDateTextField the leadDateTextField to set
     */
    public void setLeadDateTextField(javax.swing.JTextField leadDateTextField) {
        this.leadDateTextField = leadDateTextField;
    }

    /**
     * @return the leadDateToLabel
     */
    public javax.swing.JLabel getLeadDateToLabel() {
        return leadDateToLabel;
    }

    /**
     * @param leadDateToLabel the leadDateToLabel to set
     */
    public void setLeadDateToLabel(javax.swing.JLabel leadDateToLabel) {
        this.leadDateToLabel = leadDateToLabel;
    }

    /**
     * @return the leadDateToTextField
     */
    public javax.swing.JTextField getLeadDateToTextField() {
        return leadDateToTextField;
    }

    /**
     * @param leadDateToTextField the leadDateToTextField to set
     */
    public void setLeadDateToTextField(javax.swing.JTextField leadDateToTextField) {
        this.leadDateToTextField = leadDateToTextField;
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
     * @return the quotationDateLabel
     */
    public javax.swing.JLabel getQuotationDateLabel() {
        return quotationDateLabel;
    }

    /**
     * @param quotationDateLabel the quotationDateLabel to set
     */
    public void setQuotationDateLabel(javax.swing.JLabel quotationDateLabel) {
        this.quotationDateLabel = quotationDateLabel;
    }

    /**
     * @return the quotationDateTextField
     */
    public javax.swing.JTextField getQuotationDateTextField() {
        return quotationDateTextField;
    }

    /**
     * @param quotationDateTextField the quotationDateTextField to set
     */
    public void setQuotationDateTextField(javax.swing.JTextField quotationDateTextField) {
        this.quotationDateTextField = quotationDateTextField;
    }

    /**
     * @return the quotationDateToLabel
     */
    public javax.swing.JLabel getQuotationDateToLabel() {
        return quotationDateToLabel;
    }

    /**
     * @param quotationDateToLabel the quotationDateToLabel to set
     */
    public void setQuotationDateToLabel(javax.swing.JLabel quotationDateToLabel) {
        this.quotationDateToLabel = quotationDateToLabel;
    }

    /**
     * @return the quotationDateToTextField
     */
    public javax.swing.JTextField getQuotationDateToTextField() {
        return quotationDateToTextField;
    }

    /**
     * @param quotationDateToTextField the quotationDateToTextField to set
     */
    public void setQuotationDateToTextField(javax.swing.JTextField quotationDateToTextField) {
        this.quotationDateToTextField = quotationDateToTextField;
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
     * @return the quotationSearchLabel
     */
    public javax.swing.JLabel getQuotationSearchLabel() {
        return quotationSearchLabel;
    }

    /**
     * @param quotationSearchLabel the quotationSearchLabel to set
     */
    public void setQuotationSearchLabel(javax.swing.JLabel quotationSearchLabel) {
        this.quotationSearchLabel = quotationSearchLabel;
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