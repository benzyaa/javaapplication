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

import com.freebasicacc.action.MaterialAction;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.listener.BannedKeyListener;
import com.freebasicacc.ui.listener.LimitLengthListener;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import javax.swing.table.TableColumn;

/**
 *
 * @author benzyaa
 */
public class MaterialInFoListPanel extends javax.swing.JPanel {

    /** Creates new form CustomerInFoListPanel */
    public MaterialInFoListPanel() {
        initComponents();
        MaterialAction action = new MaterialAction();
        action.assignUnitCombobox(matertialUnitCombobox, materialUnitLabel, action);
        action.searchMaterialAction(this, true);
        MainFrameAction mainFrameAction = new MainFrameAction();
        //mainFrameAction.assignComboBoxModel(rowPerpageCombobox,new String[]{"10","20","30","40","50"},"10");
        Object[] orderByArr = new OrderBy[5];
        orderByArr[0] = new OrderBy("",MessageUtil.getMessage("common.select"));
        orderByArr[1] = new OrderBy("MATERIAL_ID",MessageUtil.getMessage("material.code"));
        orderByArr[2] = new OrderBy("MATERIAL_NAME",MessageUtil.getMessage("material.name"));
        orderByArr[3] = new OrderBy("MATERIAL_PRICE", MessageUtil.getMessage("material.unit"));
        orderByArr[4] = new OrderBy("UNIT_ID", MessageUtil.getMessage("material.price"));
        mainFrameAction.assignComboBoxModel(sortingByCombobox, orderByArr, MessageUtil.getMessage("material.code"));
        Enumeration<TableColumn> enu = this.materialInfoListTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
       
        
        this.buttonGroup = new ButtonGroup();
        this.buttonGroup.add(this.ascendantRadioButton);
        this.buttonGroup.add(this.descendantRadioButton);
        final MaterialInFoListPanel paneForListener = this;
        this.pageNoTextField.addKeyListener(new KeyListener() {
        
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    MaterialAction action = new MaterialAction();
                    action.setMaterialPanelTableWithNextPrevious(paneForListener);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        this.materialInfoListTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
        this.materialIdSearchTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.materialPriceSearchTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.materialPriceSearchToTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.pageNoTextField.addKeyListener(new BannedKeyListener("NUMBER"));
        this.materialIdSearchTextField.addKeyListener(new LimitLengthListener(10));
        this.materialNameSearchTextField.addKeyListener(new LimitLengthListener(40));
        this.materialPriceSearchTextField.addKeyListener(new LimitLengthListener(10));
        this.materialPriceSearchToTextField.addKeyListener(new LimitLengthListener(10));
        this.pageNoTextField.addKeyListener(new LimitLengthListener(3));
   
    }
    
      public javax.swing.table.DefaultTableModel generateModelForColumn(){
        return new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    MessageUtil.getMessage("common.sequence"), 
                    MessageUtil.getMessage("material.code"),
                    MessageUtil.getMessage("material.name"),
                    MessageUtil.getMessage("material.unit"),
                    MessageUtil.getMessage("material.price")
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

        jScrollPane1 = new javax.swing.JScrollPane();
        materialInfoListTable = new javax.swing.JTable();
        materialSearchLabel = new javax.swing.JLabel();
        materialIdSearchLabel = new javax.swing.JLabel();
        materialNameSearchLabel = new javax.swing.JLabel();
        materialPriceSearchLabel = new javax.swing.JLabel();
        customerFaxSearchLabel = new javax.swing.JLabel();
        materialIdSearchTextField = new javax.swing.JTextField();
        materialNameSearchTextField = new javax.swing.JTextField();
        materialPriceSearchTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        sortingByCombobox = new javax.swing.JComboBox();
        sortingByLabel = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        materialUnitLabel = new javax.swing.JLabel();
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
        matertialUnitCombobox = new javax.swing.JComboBox();
        toLabel = new javax.swing.JLabel();
        materialPriceSearchToTextField = new javax.swing.JTextField();
        deleteMaterialButton = new javax.swing.JButton();
        unitManageOpenButton = new javax.swing.JButton();

        materialInfoListTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        materialInfoListTable.setModel(this.generateModelForColumn());
        jScrollPane1.setViewportView(materialInfoListTable);

        materialSearchLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        materialSearchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/Misc-Misc-Box-icon.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        materialSearchLabel.setText(bundle.getString("search.material")); // NOI18N

        materialIdSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        materialIdSearchLabel.setText(bundle.getString("material.code")); // NOI18N

        materialNameSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        materialNameSearchLabel.setText(bundle.getString("material.name")); // NOI18N

        materialPriceSearchLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        materialPriceSearchLabel.setText(bundle.getString("material.price")); // NOI18N

        materialIdSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialIdSearchTextFieldActionPerformed(evt);
            }
        });

        materialNameSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialNameSearchTextFieldActionPerformed(evt);
            }
        });

        materialPriceSearchTextField.setToolTipText("");
        materialPriceSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialPriceSearchTextFieldActionPerformed(evt);
            }
        });
        materialPriceSearchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                materialPriceSearchTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                materialPriceSearchTextFieldFocusLost(evt);
            }
        });
        materialPriceSearchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                materialPriceSearchTextFieldKeyReleased(evt);
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

        sortingByCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortingByComboboxActionPerformed(evt);
            }
        });

        sortingByLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sortingByLabel.setText(bundle.getString("common.orderby")); // NOI18N

        AddButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        AddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/13.png"))); // NOI18N
        AddButton.setText(bundle.getString("common.add")); // NOI18N
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        materialUnitLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        materialUnitLabel.setText(bundle.getString("material.unit")); // NOI18N

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
        pageNoLabel.setText(bundle.getString("common.from")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText(bundle.getString("common.pageno")); // NOI18N

        pageTotalLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pageTotalLabel.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText(bundle.getString("common.rowperpage")); // NOI18N

        OpenEditPopup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        OpenEditPopup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/edit__16x16.png"))); // NOI18N
        OpenEditPopup.setText(bundle.getString("common.editselectdata")); // NOI18N
        OpenEditPopup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenEditPopupActionPerformed(evt);
            }
        });

        matertialUnitCombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        toLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        toLabel.setText(bundle.getString("common.to")); // NOI18N

        materialPriceSearchToTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialPriceSearchToTextFieldActionPerformed(evt);
            }
        });
        materialPriceSearchToTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                materialPriceSearchToTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                materialPriceSearchToTextFieldFocusLost(evt);
            }
        });

        deleteMaterialButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteMaterialButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/33.png"))); // NOI18N
        deleteMaterialButton.setText(bundle.getString("common.removeselectedrow")); // NOI18N
        deleteMaterialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMaterialButtonActionPerformed(evt);
            }
        });

        unitManageOpenButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unitManageOpenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/shopping_cart_16x16.png"))); // NOI18N
        unitManageOpenButton.setText(bundle.getString("button.material.manageunit")); // NOI18N
        unitManageOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitManageOpenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(materialPriceSearchLabel)
                                    .addComponent(materialUnitLabel)
                                    .addComponent(materialIdSearchLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(materialPriceSearchTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                            .addComponent(materialIdSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(625, 625, 625)
                                                .addComponent(customerFaxSearchLabel))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(materialNameSearchLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(materialNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(toLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(materialPriceSearchToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(matertialUnitCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sortingByLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ascendantRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descendantRadioButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(AddButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SearchButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClearButton))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(unitManageOpenButton)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(OpenEditPopup)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(deleteMaterialButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(previousButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nextButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(pageNoLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(pageTotalLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(materialSearchLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialSearchLabel)
                    .addComponent(unitManageOpenButton))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialNameSearchLabel)
                    .addComponent(materialNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialIdSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materialIdSearchLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerFaxSearchLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(toLabel)
                        .addComponent(materialPriceSearchToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(materialPriceSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(materialPriceSearchLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materialUnitLabel)
                    .addComponent(matertialUnitCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortingByLabel)
                    .addComponent(sortingByCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ascendantRadioButton)
                    .addComponent(descendantRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddButton)
                    .addComponent(SearchButton)
                    .addComponent(ClearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenEditPopup)
                    .addComponent(deleteMaterialButton)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(pageNoLabel)
                    .addComponent(pageNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(pageTotalLabel)
                    .addComponent(jLabel8)
                    .addComponent(rowPerpageCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void materialIdSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialIdSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_materialIdSearchTextFieldActionPerformed

private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
    MaterialAction action = new MaterialAction();
    this.pageNoTextField.setText("1");
    action.searchMaterialAction(this, false);
}//GEN-LAST:event_SearchButtonActionPerformed

private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
    MaterialAction action = new MaterialAction();
    this.assignDefaultValue();
    action.searchMaterialAction(this, true);
}//GEN-LAST:event_ClearButtonActionPerformed

private void materialNameSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialNameSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_materialNameSearchTextFieldActionPerformed

private void sortingByComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortingByComboboxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_sortingByComboboxActionPerformed

private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
      MainFrameAction action = new MainFrameAction();
      JMenuItem item = new  JMenuItem();
      item.setName("MATERIAL_ADD_MENU_ITEM");
      action.openInternalFormByType((MainFrame)this.getMainFrame(), item, this, null);
}//GEN-LAST:event_AddButtonActionPerformed

private void ascendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendantRadioButtonActionPerformed
            this.setRadioSelectName(this.getAscendantRadioButton().getName());
}//GEN-LAST:event_ascendantRadioButtonActionPerformed

private void descendantRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendantRadioButtonActionPerformed
            this.setRadioSelectName(this.getDescendantRadioButton().getName());
}//GEN-LAST:event_descendantRadioButtonActionPerformed

private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            pageNo = pageNo <= 1 ? 1 : pageNo-1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            MaterialAction action = new MaterialAction();
            action.setMaterialPanelTableWithNextPrevious(this);
}//GEN-LAST:event_previousButtonActionPerformed

private void pageNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageNoTextFieldActionPerformed
                
}//GEN-LAST:event_pageNoTextFieldActionPerformed

private void rowPerpageComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowPerpageComboboxActionPerformed
            MaterialAction action = new MaterialAction();
            String pageNoStr = String.valueOf(this.getPageNoTextField().getText());
            int pageNo = StringUtil.isContainText(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            action.setMaterialPanelTableWithNextPrevious(this);
}//GEN-LAST:event_rowPerpageComboboxActionPerformed

private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
            int pageNo = Integer.parseInt(String.valueOf(this.getPageNoTextField().getText()));
            int totalPage = Integer.parseInt(String.valueOf(this.getPageTotalLabel().getText()));
            pageNo = pageNo +1;
            pageNo = pageNo > totalPage ? totalPage : pageNo;
            this.getPageNoTextField().setText(String.valueOf(pageNo));
            MaterialAction action = new MaterialAction();
            action.setMaterialPanelTableWithNextPrevious(this);
}//GEN-LAST:event_nextButtonActionPerformed

private void OpenEditPopupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenEditPopupActionPerformed
            MaterialAction materialAction = new MaterialAction();
            materialAction.openMaterialEditFormAction(this);
            //action.setMaterialPanelTableWithNextPrevious(this);
}//GEN-LAST:event_OpenEditPopupActionPerformed

private void materialPriceSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialPriceSearchTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_materialPriceSearchTextFieldActionPerformed

private void materialPriceSearchToTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialPriceSearchToTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_materialPriceSearchToTextFieldActionPerformed

private void deleteMaterialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMaterialButtonActionPerformed
            MaterialAction action = new MaterialAction();
            action.deleteMaterialAction(this);
            action.searchMaterialAction(this, true);
}//GEN-LAST:event_deleteMaterialButtonActionPerformed

private void unitManageOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitManageOpenButtonActionPerformed
        JMenuItem menuItem = new  JMenuItem();
        menuItem.setName("UNIT_DIALOG_DATA_MENU_ITEM");
        MainFrameAction action = new MainFrameAction();
        action.openInternalFormByType((MainFrame)this.mainFrame,menuItem);
}//GEN-LAST:event_unitManageOpenButtonActionPerformed

private void materialPriceSearchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_materialPriceSearchTextFieldKeyReleased
 
}//GEN-LAST:event_materialPriceSearchTextFieldKeyReleased

private void materialPriceSearchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_materialPriceSearchTextFieldFocusLost
    MainFrameAction action = new MainFrameAction();
      action.assignCurrencyTextToNumberByCurrency(this.materialPriceSearchTextField);
}//GEN-LAST:event_materialPriceSearchTextFieldFocusLost

private void materialPriceSearchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_materialPriceSearchTextFieldFocusGained
    MainFrameAction action = new MainFrameAction();
    action.assignNumberToTextFieldByCurrency(this.materialPriceSearchTextField);
     
}//GEN-LAST:event_materialPriceSearchTextFieldFocusGained

private void materialPriceSearchToTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_materialPriceSearchToTextFieldFocusGained
    MainFrameAction action = new MainFrameAction();
    action.assignNumberToTextFieldByCurrency(this.materialPriceSearchToTextField);
}//GEN-LAST:event_materialPriceSearchToTextFieldFocusGained

private void materialPriceSearchToTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_materialPriceSearchToTextFieldFocusLost
     MainFrameAction action = new MainFrameAction();
     action.assignCurrencyTextToNumberByCurrency(this.materialPriceSearchToTextField);
}//GEN-LAST:event_materialPriceSearchToTextFieldFocusLost

private void assignDefaultValue(){ 
   this.ascendantRadioButton.setSelected(false);
   this.descendantRadioButton.setSelected(false);
   this.materialIdSearchTextField.setText(""); 
   this.materialNameSearchTextField.setText("");
   this.materialPriceSearchTextField.setText("");
   this.materialPriceSearchToTextField.setText("");
   this.matertialUnitCombobox.setSelectedIndex(0);
   this.pageNoTextField.setText("");
   this.rowPerpageCombobox.setSelectedIndex(0);
   this.sortingByCombobox.setSelectedIndex(0);
   this.buttonGroup.clearSelection();
   
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton OpenEditPopup;
    private javax.swing.JButton SearchButton;
    private javax.swing.JRadioButton ascendantRadioButton;
    private javax.swing.JLabel customerFaxSearchLabel;
    private javax.swing.JButton deleteMaterialButton;
    private javax.swing.JRadioButton descendantRadioButton;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel materialIdSearchLabel;
    private javax.swing.JTextField materialIdSearchTextField;
    private javax.swing.JTable materialInfoListTable;
    private javax.swing.JLabel materialNameSearchLabel;
    private javax.swing.JTextField materialNameSearchTextField;
    private javax.swing.JLabel materialPriceSearchLabel;
    private javax.swing.JTextField materialPriceSearchTextField;
    private javax.swing.JTextField materialPriceSearchToTextField;
    private javax.swing.JLabel materialSearchLabel;
    private javax.swing.JLabel materialUnitLabel;
    private javax.swing.JComboBox matertialUnitCombobox;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel pageNoLabel;
    private javax.swing.JTextField pageNoTextField;
    private javax.swing.JLabel pageTotalLabel;
    private javax.swing.JButton previousButton;
    private javax.swing.JComboBox rowPerpageCombobox;
    private javax.swing.JComboBox sortingByCombobox;
    private javax.swing.JLabel sortingByLabel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JButton unitManageOpenButton;
    // End of variables declaration//GEN-END:variables
    private String radioSelectName;
    private ButtonGroup buttonGroup;
    private JFrame mainFrame;
    private int tablePageIndex = 1;
    private List dataList;

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
     * @return the jLabel6
     */
    public javax.swing.JLabel getjLabel6() {
        return jLabel6;
    }

    /**
     * @param jLabel6 the jLabel6 to set
     */
    public void setjLabel6(javax.swing.JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }

    /**
     * @return the jLabel8
     */
    public javax.swing.JLabel getjLabel8() {
        return jLabel8;
    }

    /**
     * @param jLabel8 the jLabel8 to set
     */
    public void setjLabel8(javax.swing.JLabel jLabel8) {
        this.jLabel8 = jLabel8;
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
     * @return the materialIdSearchLabel
     */
    public javax.swing.JLabel getMaterialIdSearchLabel() {
        return materialIdSearchLabel;
    }

    /**
     * @param materialIdSearchLabel the materialIdSearchLabel to set
     */
    public void setMaterialIdSearchLabel(javax.swing.JLabel materialIdSearchLabel) {
        this.materialIdSearchLabel = materialIdSearchLabel;
    }

    /**
     * @return the materialIdSearchTextField
     */
    public javax.swing.JTextField getMaterialIdSearchTextField() {
        return materialIdSearchTextField;
    }

    /**
     * @param materialIdSearchTextField the materialIdSearchTextField to set
     */
    public void setMaterialIdSearchTextField(javax.swing.JTextField materialIdSearchTextField) {
        this.materialIdSearchTextField = materialIdSearchTextField;
    }

    /**
     * @return the materialNameSearchLabel
     */
    public javax.swing.JLabel getMaterialNameSearchLabel() {
        return materialNameSearchLabel;
    }

    /**
     * @param materialNameSearchLabel the materialNameSearchLabel to set
     */
    public void setMaterialNameSearchLabel(javax.swing.JLabel materialNameSearchLabel) {
        this.materialNameSearchLabel = materialNameSearchLabel;
    }

    /**
     * @return the materialNameSearchTextField
     */
    public javax.swing.JTextField getMaterialNameSearchTextField() {
        return materialNameSearchTextField;
    }

    /**
     * @param materialNameSearchTextField the materialNameSearchTextField to set
     */
    public void setMaterialNameSearchTextField(javax.swing.JTextField materialNameSearchTextField) {
        this.materialNameSearchTextField = materialNameSearchTextField;
    }

    /**
     * @return the materialPriceSearchLabel
     */
    public javax.swing.JLabel getMaterialPriceSearchLabel() {
        return materialPriceSearchLabel;
    }

    /**
     * @param materialPriceSearchLabel the materialPriceSearchLabel to set
     */
    public void setMaterialPriceSearchLabel(javax.swing.JLabel materialPriceSearchLabel) {
        this.materialPriceSearchLabel = materialPriceSearchLabel;
    }

    /**
     * @return the materialPriceSearchTextField
     */
    public javax.swing.JTextField getMaterialPriceSearchTextField() {
        return materialPriceSearchTextField;
    }

    /**
     * @param materialPriceSearchTextField the materialPriceSearchTextField to set
     */
    public void setMaterialPriceSearchTextField(javax.swing.JTextField materialPriceSearchTextField) {
        this.materialPriceSearchTextField = materialPriceSearchTextField;
    }

    /**
     * @return the materialSearchLabel
     */
    public javax.swing.JLabel getMaterialSearchLabel() {
        return materialSearchLabel;
    }

    /**
     * @param materialSearchLabel the materialSearchLabel to set
     */
    public void setMaterialSearchLabel(javax.swing.JLabel materialSearchLabel) {
        this.materialSearchLabel = materialSearchLabel;
    }

    /**
     * @return the materialUnitLabel
     */
    public javax.swing.JLabel getMaterialUnitLabel() {
        return materialUnitLabel;
    }

    /**
     * @param materialUnitLabel the materialUnitLabel to set
     */
    public void setMaterialUnitLabel(javax.swing.JLabel materialUnitLabel) {
        this.materialUnitLabel = materialUnitLabel;
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
     * @return the matertialUnitCombobox
     */
    public javax.swing.JComboBox getMatertialUnitCombobox() {
        return matertialUnitCombobox;
    }

    /**
     * @param matertialUnitCombobox the matertialUnitCombobox to set
     */
    public void setMatertialUnitCombobox(javax.swing.JComboBox matertialUnitCombobox) {
        this.matertialUnitCombobox = matertialUnitCombobox;
    }

    /**
     * @return the materialPriceSearchToTextField
     */
    public javax.swing.JTextField getMaterialPriceSearchToTextField() {
        return materialPriceSearchToTextField;
    }

    /**
     * @param materialPriceSearchToTextField the materialPriceSearchToTextField to set
     */
    public void setMaterialPriceSearchToTextField(javax.swing.JTextField materialPriceSearchToTextField) {
        this.materialPriceSearchToTextField = materialPriceSearchToTextField;
    }

    /**
     * @return the materialInfoListTable
     */
    public javax.swing.JTable getMaterialInfoListTable() {
        return materialInfoListTable;
    }

    /**
     * @param materialInfoListTable the materialInfoListTable to set
     */
    public void setMaterialInfoListTable(javax.swing.JTable materialInfoListTable) {
        this.materialInfoListTable = materialInfoListTable;
    }
  
    
}