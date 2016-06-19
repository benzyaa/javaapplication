/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerInFoListPanel.java
 *
 * Created on Mar 27, 2013, 10:51:34 PM
 */
package com.freebasicacc.ui;

import com.freebasicacc.action.CustomerDialogAction;
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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author benzyaa
 */
@SuppressWarnings("unchecked")
public class CustomerInFoListDialogPanel extends javax.swing.JPanel {

    /** Creates new form CustomerInFoListPanel */
    public CustomerInFoListDialogPanel() {
        initComponents();
        
        CustomerDialogAction action = new CustomerDialogAction();
        action.searchCustomer(this, true);
        MainFrameAction mainFrameAction = new MainFrameAction();
     
        Object[] orderByArr = new OrderBy[8];
        orderByArr[0] = new OrderBy("",MessageUtil.getMessage("common.select"));
        orderByArr[1] = new OrderBy("CUSTOMER_ID",MessageUtil.getMessage("customer.code"));
        orderByArr[2] = new OrderBy("CUSTOMER_NAME",MessageUtil.getMessage("customer.name"));
        orderByArr[3] = new OrderBy("CUSTOMER_CONTRACT",MessageUtil.getMessage("customer.contract"));
        orderByArr[4] = new OrderBy("CUSTOMER_PHONE",MessageUtil.getMessage("customer.phone"));
        orderByArr[5] = new OrderBy("CUSTOMER_EMAIL",MessageUtil.getMessage("customer.email"));
        orderByArr[6] = new OrderBy("REGISTER_DATE",MessageUtil.getMessage("customer.registerdate"));
        orderByArr[7] = new OrderBy("LAST_CONTRACT_DATE",MessageUtil.getMessage("customer.lastcontractdate"));        
        mainFrameAction.assignComboBoxModel(sortingByCombobox, orderByArr,"");
        this.buttonGroup = new ButtonGroup();
        this.buttonGroup.add(this.ascendantRadioButton);
        this.buttonGroup.add(this.descendantRadioButton);
        final CustomerInFoListDialogPanel paneForListener = this;
        this.pageNoTextField.addKeyListener(new KeyListener() {
        
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    CustomerDialogAction action = new CustomerDialogAction();
                    action.setCustomerPanelTableWithNextPrevious(paneForListener);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
      
         this.customerInfoListTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
        this.customerIdSearchTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.customerPhoneSearchTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.customerFaxSearchTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        
        this.CustomerNameSearchTextField.addKeyListener(new LimitLengthListener(10));
        this.customerContractSearchTextField.addKeyListener(new LimitLengthListener(50));
        this.CustomerEmailSearchTextField.addKeyListener(new LimitLengthListener(300));
        this.customerRegisterFromTextField.addKeyListener(new LimitLengthListener(10));
        this.customerRegisterToTextField.addKeyListener(new LimitLengthListener(10));
        this.customerLastContractDateFromTextField.addKeyListener(new LimitLengthListener(10));
        this.customerLastContractDateToTextField.addKeyListener(new LimitLengthListener(10));
        this.customerIdSearchTextField.addKeyListener(new LimitLengthListener(10));
        this.customerPhoneSearchTextField.addKeyListener(new LimitLengthListener(14));
        this.customerFaxSearchTextField.addKeyListener(new LimitLengthListener(14));
        this.pageNoTextField.addKeyListener(new LimitLengthListener(3));
    }
    
    public javax.swing.table.DefaultTableModel generateModelForColumn(){
        return new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    MessageUtil.getMessage("common.sequence"), 
                    MessageUtil.getMessage("customer.code"),
                    MessageUtil.getMessage("customer.name"),
                    MessageUtil.getMessage("customer.contract"),
                    MessageUtil.getMessage("customer.phone"),   
                    MessageUtil.getMessage("customer.email"),
                    MessageUtil.getMessage("customer.address"),
                    MessageUtil.getMessage("customer.registerdate"),
                    MessageUtil.getMessage("customer.lastcontractdate")
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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        customerInfoListTable = new javax.swing.JTable();
        customerSearchLabel = new javax.swing.JLabel();
        customerIdSearchLabel = new javax.swing.JLabel();
        customerNameSearchLabel = new javax.swing.JLabel();
        customerContractSearchLabel = new javax.swing.JLabel();
        customerTelSearchLabel = new javax.swing.JLabel();
        customerFaxSearchLabel = new javax.swing.JLabel();
        customerEmailLabel = new javax.swing.JLabel();
        customerIdSearchTextField = new javax.swing.JTextField();
        CustomerNameSearchTextField = new javax.swing.JTextField();
        customerContractSearchTextField = new javax.swing.JTextField();
        customerPhoneSearchTextField = new javax.swing.JTextField();
        customerFaxSearchTextField = new javax.swing.JTextField();
        CustomerEmailSearchTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        sortingByCombobox = new javax.swing.JComboBox();
        sortingByLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        customerRegisterFromTextField = new javax.swing.JTextField();
        customerLastContractDateFromTextField = new javax.swing.JTextField();
        customerLastContractDateFromDatepicker = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        customerRegisterToTextField = new javax.swing.JTextField();
        customerRegisterToDatePicker = new javax.swing.JButton();
        customerLastContractDateToTextField = new javax.swing.JTextField();
        customerLastContractDateToDatepicker = new javax.swing.JButton();
        ascendantRadioButton = new javax.swing.JRadioButton();
        descendantRadioButton = new javax.swing.JRadioButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        pageNoTextField = new javax.swing.JTextField();
        rowPerpageCombobox = new javax.swing.JComboBox();
        pageNoLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pageTotalLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        OpenEditPopup = new javax.swing.JButton();
        registerDateFromButton = new javax.swing.JButton();

        customerInfoListTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerInfoListTable.setModel(this.generateModelForColumn());
        jScrollPane1.setViewportView(customerInfoListTable);

        customerSearchLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        customerSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Office-Client-Male-Light-icon.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        customerSearchLabel.setText(bundle.getString("dialog.customer.select")); // NOI18N

        customerIdSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerIdSearchLabel.setText(bundle.getString("customer.code")); // NOI18N

        customerNameSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerNameSearchLabel.setText(bundle.getString("customer.name")); // NOI18N

        customerContractSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerContractSearchLabel.setText(bundle.getString("customer.contract")); // NOI18N

        customerTelSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerTelSearchLabel.setText(bundle.getString("customer.phone")); // NOI18N

        customerFaxSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerFaxSearchLabel.setText(bundle.getString("customer.fax")); // NOI18N

        customerEmailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerEmailLabel.setText(bundle.getString("customer.email")); // NOI18N

        customerIdSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerIdSearchTextFieldActionPerformed(evt);
            }
        });

        CustomerNameSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerNameSearchTextFieldActionPerformed(evt);
            }
        });

        customerContractSearchTextField.setToolTipText("");

        customerFaxSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerFaxSearchTextFieldActionPerformed(evt);
            }
        });

        CustomerEmailSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerEmailSearchTextFieldActionPerformed(evt);
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

        CancelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/33.png"))); // NOI18N
        CancelButton.setText(bundle.getString("common.cancel")); // NOI18N
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        sortingByCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        sortingByCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortingByComboboxActionPerformed(evt);
            }
        });

        sortingByLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByLabel.setText(bundle.getString("common.orderby")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(bundle.getString("customer.registerdate")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText(bundle.getString("customer.lastcontractdate")); // NOI18N

        customerRegisterFromTextField.setToolTipText("");
        customerRegisterFromTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRegisterFromTextFieldActionPerformed(evt);
            }
        });

        customerLastContractDateFromTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLastContractDateFromTextFieldActionPerformed(evt);
            }
        });

        customerLastContractDateFromDatepicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        customerLastContractDateFromDatepicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLastContractDateFromDatepickerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(bundle.getString("common.to")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(bundle.getString("common.to")); // NOI18N

        customerRegisterToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRegisterToTextFieldActionPerformed(evt);
            }
        });

        customerRegisterToDatePicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        customerRegisterToDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRegisterToDatePickerActionPerformed(evt);
            }
        });

        customerLastContractDateToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLastContractDateToTextFieldActionPerformed(evt);
            }
        });

        customerLastContractDateToDatepicker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        customerLastContractDateToDatepicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerLastContractDateToDatepickerActionPerformed(evt);
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

        pageNoTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageNoTextField.setText("1");
        pageNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageNoTextFieldActionPerformed(evt);
            }
        });

        rowPerpageCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rowPerpageCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50" }));
        rowPerpageCombobox.setToolTipText("");
        rowPerpageCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowPerpageComboboxActionPerformed(evt);
            }
        });

        pageNoLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageNoLabel.setText(bundle.getString("common.pageno")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText(bundle.getString("common.from")); // NOI18N

        pageTotalLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageTotalLabel.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText(bundle.getString("common.rowperpage")); // NOI18N

        OpenEditPopup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        OpenEditPopup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/54.png"))); // NOI18N
        OpenEditPopup.setText(bundle.getString("common.select")); // NOI18N
        OpenEditPopup.setToolTipText("");
        OpenEditPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenEditPopupActionPerformed(evt);
            }
        });

        registerDateFromButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/43.png"))); // NOI18N
        registerDateFromButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerDateFromButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(OpenEditPopup, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton)
                        .addGap(32, 32, 32)
                        .addComponent(pageNoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageTotalLabel)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(customerIdSearchLabel)
                            .addComponent(customerSearchLabel)
                            .addComponent(customerNameSearchLabel)
                            .addComponent(customerContractSearchLabel)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(sortingByLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(SearchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ClearButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CancelButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(customerLastContractDateFromTextField)
                                    .addComponent(customerRegisterFromTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(customerLastContractDateFromDatepicker, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(registerDateFromButton, 0, 1, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(customerRegisterToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(customerLastContractDateToTextField)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customerLastContractDateToDatepicker, 0, 1, Short.MAX_VALUE)
                                    .addComponent(customerRegisterToDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ascendantRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descendantRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customerContractSearchTextField)
                                    .addComponent(CustomerNameSearchTextField)
                                    .addComponent(customerIdSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(customerEmailLabel)
                                    .addComponent(customerFaxSearchLabel)
                                    .addComponent(customerTelSearchLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CustomerEmailSearchTextField)
                                    .addComponent(customerFaxSearchTextField)
                                    .addComponent(customerPhoneSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerSearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIdSearchLabel)
                    .addComponent(customerIdSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerPhoneSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerTelSearchLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameSearchLabel)
                    .addComponent(CustomerNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerFaxSearchLabel)
                    .addComponent(customerFaxSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerContractSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomerEmailSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerEmailLabel)
                    .addComponent(customerContractSearchLabel))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerRegisterFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(customerRegisterToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerRegisterToDatePicker)
                    .addComponent(registerDateFromButton))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(customerLastContractDateFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerLastContractDateFromDatepicker)
                    .addComponent(jLabel4)
                    .addComponent(customerLastContractDateToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerLastContractDateToDatepicker))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortingByLabel)
                    .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ascendantRadioButton)
                    .addComponent(descendantRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchButton)
                    .addComponent(ClearButton)
                    .addComponent(CancelButton))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageNoLabel)
                    .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(pageTotalLabel)
                    .addComponent(jLabel8)
                    .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenEditPopup)
                    .addComponent(previousButton)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
private void asssignDefaultValue(){
   this.CustomerEmailSearchTextField.setText("");
   this.CustomerNameSearchTextField.setText("");
   this.ascendantRadioButton.setSelected(false);
   this.customerContractSearchTextField.setText("");
   this.customerFaxSearchTextField.setText("");
   this.customerIdSearchTextField.setText("");
   this.customerLastContractDateFromTextField.setText("");
   this.customerLastContractDateToTextField.setText("");
   this.customerPhoneSearchTextField.setText("");
   this.customerRegisterFromTextField.setText("");
   this.customerRegisterToTextField.setText("");
   this.descendantRadioButton.setSelected(false);
   this.pageNoTextField.setText("");
   this.rowPerpageCombobox.setSelectedIndex(0);
   this.sortingByCombobox.setSelectedIndex(0);
}
private void customerIdSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerIdSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerIdSearchTextFieldActionPerformed

private void CustomerEmailSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerEmailSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_CustomerEmailSearchTextFieldActionPerformed

private void customerFaxSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerFaxSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerFaxSearchTextFieldActionPerformed

private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
    CustomerDialogAction action = new CustomerDialogAction();
    action.searchCustomer(this, true);
    this.asssignDefaultValue();
}//GEN-LAST:event_ClearButtonActionPerformed

private void CustomerNameSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerNameSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_CustomerNameSearchTextFieldActionPerformed

private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_CancelButtonActionPerformed

private void sortingByComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingByComboboxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_sortingByComboboxActionPerformed

private void customerLastContractDateFromDatepickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLastContractDateFromDatepickerActionPerformed
        UIUtil util = new UIUtil();
        util.openDatepickerForTextField(this.customerLastContractDateFromTextField,this.getMainFrame());
}//GEN-LAST:event_customerLastContractDateFromDatepickerActionPerformed

private void customerLastContractDateFromTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLastContractDateFromTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerLastContractDateFromTextFieldActionPerformed

private void customerRegisterFromTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRegisterFromTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerRegisterFromTextFieldActionPerformed

private void customerRegisterToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRegisterToTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerRegisterToTextFieldActionPerformed

private void customerLastContractDateToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLastContractDateToTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_customerLastContractDateToTextFieldActionPerformed

private void customerLastContractDateToDatepickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerLastContractDateToDatepickerActionPerformed
        UIUtil util = new UIUtil();
        util.openDatepickerForTextField(this.customerLastContractDateToTextField,this.getMainFrame());
}//GEN-LAST:event_customerLastContractDateToDatepickerActionPerformed

private void ascendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendantRadioButtonActionPerformed
            this.setRadioSelectName(this.ascendantRadioButton.getName());
}//GEN-LAST:event_ascendantRadioButtonActionPerformed

private void descendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendantRadioButtonActionPerformed
            this.setRadioSelectName(this.descendantRadioButton.getName());
}//GEN-LAST:event_descendantRadioButtonActionPerformed

private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            pageNo = pageNo <= 1 ? 1 : pageNo-1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            CustomerDialogAction action = new CustomerDialogAction();
            action.setCustomerPanelTableWithNextPrevious(this);
}//GEN-LAST:event_previousButtonActionPerformed

private void pageNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageNoTextFieldActionPerformed
                
}//GEN-LAST:event_pageNoTextFieldActionPerformed

private void rowPerpageComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowPerpageComboboxActionPerformed
            CustomerDialogAction action = new CustomerDialogAction();
            String pageNoStr = String.valueOf(this.getPageNoTextField().getText());
            int pageNo = StringUtil.isContainText(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            action.setCustomerPanelTableWithNextPrevious(this);
}//GEN-LAST:event_rowPerpageComboboxActionPerformed

private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            int totalPage = Integer.parseInt(String.valueOf(this.getPageTotalLabel().getText()));
            pageNo = pageNo +1;
            pageNo = pageNo > totalPage ? totalPage : pageNo;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            CustomerDialogAction action = new CustomerDialogAction();
            action.setCustomerPanelTableWithNextPrevious(this);
}//GEN-LAST:event_nextButtonActionPerformed

private void OpenEditPopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenEditPopupActionPerformed
            CustomerDialogAction action = new CustomerDialogAction();
            action.addToOpenerTable(this, openerPanel);
}//GEN-LAST:event_OpenEditPopupActionPerformed

private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
    CustomerDialogAction action = new CustomerDialogAction();
    this.pageNoTextField.setText("1");
    action.searchCustomer(this, false);
}//GEN-LAST:event_SearchButtonActionPerformed

private void customerRegisterToDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRegisterToDatePickerActionPerformed
     UIUtil util = new UIUtil();
    util.openDatepickerForTextField(this.customerRegisterToTextField,this.getMainFrame());
}//GEN-LAST:event_customerRegisterToDatePickerActionPerformed

private void registerDateFromButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerDateFromButtonActionPerformed
    UIUtil util = new UIUtil();
    util.openDatepickerForTextField(this.customerRegisterFromTextField,this.getMainFrame());
}//GEN-LAST:event_registerDateFromButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JTextField CustomerEmailSearchTextField;
    private javax.swing.JTextField CustomerNameSearchTextField;
    private javax.swing.JButton OpenEditPopup;
    private javax.swing.JButton SearchButton;
    private javax.swing.JRadioButton ascendantRadioButton;
    private javax.swing.JLabel customerContractSearchLabel;
    private javax.swing.JTextField customerContractSearchTextField;
    private javax.swing.JLabel customerEmailLabel;
    private javax.swing.JLabel customerFaxSearchLabel;
    private javax.swing.JTextField customerFaxSearchTextField;
    private javax.swing.JLabel customerIdSearchLabel;
    private javax.swing.JTextField customerIdSearchTextField;
    private javax.swing.JTable customerInfoListTable;
    private javax.swing.JButton customerLastContractDateFromDatepicker;
    private javax.swing.JTextField customerLastContractDateFromTextField;
    private javax.swing.JButton customerLastContractDateToDatepicker;
    private javax.swing.JTextField customerLastContractDateToTextField;
    private javax.swing.JLabel customerNameSearchLabel;
    private javax.swing.JTextField customerPhoneSearchTextField;
    private javax.swing.JTextField customerRegisterFromTextField;
    private javax.swing.JButton customerRegisterToDatePicker;
    private javax.swing.JTextField customerRegisterToTextField;
    private javax.swing.JLabel customerSearchLabel;
    private javax.swing.JLabel customerTelSearchLabel;
    private javax.swing.JRadioButton descendantRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel pageNoLabel;
    private javax.swing.JTextField pageNoTextField;
    private javax.swing.JLabel pageTotalLabel;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton registerDateFromButton;
    private javax.swing.JComboBox rowPerpageCombobox;
    private javax.swing.JComboBox sortingByCombobox;
    private javax.swing.JLabel sortingByLabel;
    // End of variables declaration//GEN-END:variables
    private String radioSelectName;
    private ButtonGroup buttonGroup;
    private JFrame mainFrame;
    private int tablePageIndex = 1;
    private List dataList;
    private JPanel openerPanel;
    /**
     * @return the CancelButton
     */
    public javax.swing.JButton getCancelButton() {
        return CancelButton;
    }

    /**
     * @param CancelButton the CancelButton to set
     */
    public void setCancelButton(javax.swing.JButton CancelButton) {
        this.CancelButton = CancelButton;
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
     * @return the CustomerEmailSearchTextField
     */
    public javax.swing.JTextField getCustomerEmailSearchTextField() {
        return CustomerEmailSearchTextField;
    }

    /**
     * @param CustomerEmailSearchTextField the CustomerEmailSearchTextField to set
     */
    public void setCustomerEmailSearchTextField(javax.swing.JTextField CustomerEmailSearchTextField) {
        this.CustomerEmailSearchTextField = CustomerEmailSearchTextField;
    }

    /**
     * @return the CustomerNameSearchTextField
     */
    public javax.swing.JTextField getCustomerNameSearchTextField() {
        return CustomerNameSearchTextField;
    }

    /**
     * @param CustomerNameSearchTextField the CustomerNameSearchTextField to set
     */
    public void setCustomerNameSearchTextField(javax.swing.JTextField CustomerNameSearchTextField) {
        this.CustomerNameSearchTextField = CustomerNameSearchTextField;
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
     * @return the customerContractSearchLabel
     */
    public javax.swing.JLabel getCustomerContractSearchLabel() {
        return customerContractSearchLabel;
    }

    /**
     * @param customerContractSearchLabel the customerContractSearchLabel to set
     */
    public void setCustomerContractSearchLabel(javax.swing.JLabel customerContractSearchLabel) {
        this.customerContractSearchLabel = customerContractSearchLabel;
    }

    /**
     * @return the customerEmailLabel
     */
    public javax.swing.JLabel getCustomerEmailLabel() {
        return customerEmailLabel;
    }

    /**
     * @param customerEmailLabel the customerEmailLabel to set
     */
    public void setCustomerEmailLabel(javax.swing.JLabel customerEmailLabel) {
        this.customerEmailLabel = customerEmailLabel;
    }

    /**
     * @return the customerFaxSearchLabel
     */
    public javax.swing.JLabel getCustomerFaxSearchLabel() {
        return customerFaxSearchLabel;
    }

    /**
     * @param customerFaxSearchLabel the customerFaxSearchLabel to set
     */
    public void setCustomerFaxSearchLabel(javax.swing.JLabel customerFaxSearchLabel) {
        this.customerFaxSearchLabel = customerFaxSearchLabel;
    }

    /**
     * @return the customerFaxSearchTextField
     */
    public javax.swing.JTextField getCustomerFaxSearchTextField() {
        return customerFaxSearchTextField;
    }

    /**
     * @param customerFaxSearchTextField the customerFaxSearchTextField to set
     */
    public void setCustomerFaxSearchTextField(javax.swing.JTextField customerFaxSearchTextField) {
        this.customerFaxSearchTextField = customerFaxSearchTextField;
    }

    /**
     * @return the customerIdSearchLabel
     */
    public javax.swing.JLabel getCustomerIdSearchLabel() {
        return customerIdSearchLabel;
    }

    /**
     * @param customerIdSearchLabel the customerIdSearchLabel to set
     */
    public void setCustomerIdSearchLabel(javax.swing.JLabel customerIdSearchLabel) {
        this.customerIdSearchLabel = customerIdSearchLabel;
    }

    /**
     * @return the customerIdSearchTextField
     */
    public javax.swing.JTextField getCustomerIdSearchTextField() {
        return customerIdSearchTextField;
    }

    /**
     * @param customerIdSearchTextField the customerIdSearchTextField to set
     */
    public void setCustomerIdSearchTextField(javax.swing.JTextField customerIdSearchTextField) {
        this.customerIdSearchTextField = customerIdSearchTextField;
    }

    /**
     * @return the customerNameSearchLabel
     */
    public javax.swing.JLabel getCustomerNameSearchLabel() {
        return customerNameSearchLabel;
    }

    /**
     * @param customerNameSearchLabel the customerNameSearchLabel to set
     */
    public void setCustomerNameSearchLabel(javax.swing.JLabel customerNameSearchLabel) {
        this.customerNameSearchLabel = customerNameSearchLabel;
    }

    /**
     * @return the customerPhoneSearchTextField
     */
    public javax.swing.JTextField getCustomerPhoneSearchTextField() {
        return customerPhoneSearchTextField;
    }

    /**
     * @param customerPhoneSearchTextField the customerPhoneSearchTextField to set
     */
    public void setCustomerPhoneSearchTextField(javax.swing.JTextField customerPhoneSearchTextField) {
        this.customerPhoneSearchTextField = customerPhoneSearchTextField;
    }

    /**
     * @return the customerSearchLabel
     */
    public javax.swing.JLabel getCustomerSearchLabel() {
        return customerSearchLabel;
    }

    /**
     * @param customerSearchLabel the customerSearchLabel to set
     */
    public void setCustomerSearchLabel(javax.swing.JLabel customerSearchLabel) {
        this.customerSearchLabel = customerSearchLabel;
    }

    /**
     * @return the customerTelSearchLabel
     */
    public javax.swing.JLabel getCustomerTelSearchLabel() {
        return customerTelSearchLabel;
    }

    /**
     * @param customerTelSearchLabel the customerTelSearchLabel to set
     */
    public void setCustomerTelSearchLabel(javax.swing.JLabel customerTelSearchLabel) {
        this.customerTelSearchLabel = customerTelSearchLabel;
    }

    /**
     * @return the customerContractSearchTextField
     */
    public javax.swing.JTextField getCustomerContractSearchTextField() {
        return customerContractSearchTextField;
    }

    /**
     * @param customerContractSearchTextField the customerContractSearchTextField to set
     */
    public void setCustomerContractSearchTextField(javax.swing.JTextField customerContractSearchTextField) {
        this.customerContractSearchTextField = customerContractSearchTextField;
    }

    /**
     * @return the customerInfoListTable
     */
    public javax.swing.JTable getCustomerInfoListTable() {
        return customerInfoListTable;
    }

    /**
     * @param customerInfoListTable the customerInfoListTable to set
     */
    public void setCustomerInfoListTable(javax.swing.JTable customerInfoListTable) {
        this.customerInfoListTable = customerInfoListTable;
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
     * @return the mainFrame
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * @return the tablePageIndex
     */
    public int getTablePageIndex() {
        return tablePageIndex;
    }

    /**
     * @param tablePageIndex the tablePageIndex to set
     */
    public void setTablePageIndex(int tablePageIndex) {
        this.tablePageIndex = tablePageIndex;
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
    
}