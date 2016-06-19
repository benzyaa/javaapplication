/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UnitListPanel.java
 *
 * Created on Mar 31, 2013, 10:10:53 AM
 */
package com.freebasicacc.ui;

import com.freebasicacc.action.UnitAction;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.listener.LimitLengthListener;
import com.freebasicacc.util.MessageUtil;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
 *
 * @author benzyaa
 */
public class UnitListPanel extends javax.swing.JPanel {

    /** Creates new form UnitListPanel */
    public UnitListPanel() {
        initComponents();
        UnitAction unitAction = new UnitAction();
        unitAction.searchUnitAction(this, true);
        this.unitInfoTable.getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 12));
        this.unitNameTextField.addKeyListener(new LimitLengthListener(50));
    }
    
    public javax.swing.table.DefaultTableModel generateModelForColumn(){
        return new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                    MessageUtil.getMessage("common.sequence"), 
                    MessageUtil.getMessage("unit.code"),
                    MessageUtil.getMessage("unit.name")
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

        manageUnitLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unitInfoTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        unitNameTextField = new javax.swing.JTextField();
        unitNameLabel = new javax.swing.JLabel();
        unitSearchButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        manageUnitLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        manageUnitLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/shopping_cart_32x32.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/freebasicacc/ui/bundle"); // NOI18N
        manageUnitLabel.setText(bundle.getString("dialog.unit.title.manage")); // NOI18N

        unitInfoTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unitInfoTable.setModel(this.generateModelForColumn());
        jScrollPane1.setViewportView(unitInfoTable);

        addButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/13.png"))); // NOI18N
        addButton.setText(bundle.getString("common.addshort")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/33.png"))); // NOI18N
        removeButton.setText(bundle.getString("common.remove")); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        unitNameTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unitNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitNameTextFieldActionPerformed(evt);
            }
        });

        unitNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unitNameLabel.setText(bundle.getString("unit.name")); // NOI18N

        unitSearchButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unitSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/65.png"))); // NOI18N
        unitSearchButton.setText(bundle.getString("common.search")); // NOI18N
        unitSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitSearchButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/freebasicacc/ui/image/edit__16x16.png"))); // NOI18N
        editButton.setText(bundle.getString("common.edit")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(unitNameLabel)
                            .addComponent(manageUnitLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitSearchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(6, 6, 6)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageUnitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitNameLabel)
                    .addComponent(unitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(removeButton)
                    .addComponent(editButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
    }// </editor-fold>//GEN-END:initComponents

private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
    UnitAction action = new UnitAction();
    action.deleteUnit(this);
}//GEN-LAST:event_removeButtonActionPerformed

private void unitNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitNameTextFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_unitNameTextFieldActionPerformed

private void unitSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitSearchButtonActionPerformed
        UnitAction unitAction = new UnitAction();
        unitAction.searchUnitAction(this, false);
}//GEN-LAST:event_unitSearchButtonActionPerformed

private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
      MainFrameAction action = new MainFrameAction();
      JMenuItem item = new  JMenuItem();
      item.setName("UNIT_ADD_MENU_ITEM");
      action.openInternalFormByType((MainFrame)this.getMainFrame(), item, this, null);
}//GEN-LAST:event_addButtonActionPerformed

private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
    UnitAction unitAction = new UnitAction();  
    unitAction.openUnitEditFormAction(this);
}//GEN-LAST:event_editButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel manageUnitLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JTable unitInfoTable;
    private javax.swing.JLabel unitNameLabel;
    private javax.swing.JTextField unitNameTextField;
    private javax.swing.JButton unitSearchButton;
    // End of variables declaration//GEN-END:variables
    private JFrame mainFrame;
    /**
     * @return the addButton
     */
    public javax.swing.JButton getAddButton() {
        return addButton;
    }

    /**
     * @param addButton the addButton to set
     */
    public void setAddButton(javax.swing.JButton addButton) {
        this.addButton = addButton;
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
     * @return the manageUnitLabel
     */
    public javax.swing.JLabel getManageUnitLabel() {
        return manageUnitLabel;
    }

    /**
     * @param manageUnitLabel the manageUnitLabel to set
     */
    public void setManageUnitLabel(javax.swing.JLabel manageUnitLabel) {
        this.manageUnitLabel = manageUnitLabel;
    }

    /**
     * @return the removeButton
     */
    public javax.swing.JButton getRemoveButton() {
        return removeButton;
    }

    /**
     * @param removeButton the removeButton to set
     */
    public void setRemoveButton(javax.swing.JButton removeButton) {
        this.removeButton = removeButton;
    }

    /**
     * @return the unitInfoTable
     */
    public javax.swing.JTable getUnitInfoTable() {
        return unitInfoTable;
    }

    /**
     * @param unitInfoTable the unitInfoTable to set
     */
    public void setUnitInfoTable(javax.swing.JTable unitInfoTable) {
        this.unitInfoTable = unitInfoTable;
    }

    /**
     * @return the unitNameLabel
     */
    public javax.swing.JLabel getUnitNameLabel() {
        return unitNameLabel;
    }

    /**
     * @param unitNameLabel the unitNameLabel to set
     */
    public void setUnitNameLabel(javax.swing.JLabel unitNameLabel) {
        this.unitNameLabel = unitNameLabel;
    }

    /**
     * @return the unitNameTextField
     */
    public javax.swing.JTextField getUnitNameTextField() {
        return unitNameTextField;
    }

    /**
     * @param unitNameTextField the unitNameTextField to set
     */
    public void setUnitNameTextField(javax.swing.JTextField unitNameTextField) {
        this.unitNameTextField = unitNameTextField;
    }

    /**
     * @return the unitSearchButton
     */
    public javax.swing.JButton getUnitSearchButton() {
        return unitSearchButton;
    }

    /**
     * @param unitSearchButton the unitSearchButton to set
     */
    public void setUnitSearchButton(javax.swing.JButton unitSearchButton) {
        this.unitSearchButton = unitSearchButton;
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
    
    

}
