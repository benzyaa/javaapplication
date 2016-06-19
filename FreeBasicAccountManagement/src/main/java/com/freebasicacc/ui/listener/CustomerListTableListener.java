/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.listener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author benzyaa
 */
public class CustomerListTableListener implements ListSelectionListener {
    private JButton editButton;
    private JTable table;
    @Override
    public void valueChanged(ListSelectionEvent e) {
        editButton = this.getEditButton();
        
        int selectedRowIndex = table.getSelectedRow();
        String customerId = String.valueOf(table.getModel().getValueAt(selectedRowIndex, 2));
        System.out.println(customerId);
        editButton.setName(customerId);
    }

    /**
     * @return the editButton
     */
    public JButton getEditButton() {
        return editButton;
    }

    /**
     * @param editButton the editButton to set
     */
    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    /**
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        this.table = table;
    }
    

}
