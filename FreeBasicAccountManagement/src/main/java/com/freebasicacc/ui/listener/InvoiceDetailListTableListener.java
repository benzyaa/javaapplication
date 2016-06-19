/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.listener;

import com.freebasicacc.action.InvoiceAction;
import com.freebasicacc.ui.InvoiceInfoPanel;
import com.freebasicacc.util.StringUtil;
import java.math.BigDecimal;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author benzyaa
 */
public class InvoiceDetailListTableListener implements TableModelListener {

    private InvoiceInfoPanel handlerPanel;
    @Override
    public void tableChanged(TableModelEvent e) {
        DefaultTableModel defaultTableModel = null;
        int selectedRowIndex = 0;
        int selectedColumnIndex = 0;
        try{        
            defaultTableModel = (DefaultTableModel)e.getSource();
            selectedRowIndex = e.getFirstRow();
            selectedColumnIndex = e.getColumn();
            Object quantityObj = defaultTableModel.getValueAt(selectedRowIndex, selectedColumnIndex); // get Quantity
            Object pricePerUnitObj = defaultTableModel.getValueAt(selectedRowIndex, 5); // get Quantity
            Number pricePerUnitObjNumber = StringUtil.formatThaiCurrencyStrToNumber(String.valueOf(pricePerUnitObj));
            if(selectedColumnIndex !=3) return;
            BigDecimal quantityBig = new BigDecimal(String.valueOf(quantityObj));
            BigDecimal pricePerUnitBig = new BigDecimal(String.valueOf(pricePerUnitObjNumber)); 
            BigDecimal totalValue =  quantityBig.multiply(pricePerUnitBig);
            totalValue = totalValue.setScale(10);
            defaultTableModel.setValueAt(StringUtil.formatThaiCurrency(totalValue.doubleValue()),selectedRowIndex, 6); // Total price
            InvoiceAction action = new InvoiceAction();
            action.calculateTotalDetail(handlerPanel);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * @return the handlerPanel
     */
    public InvoiceInfoPanel getHandlerPanel() {
        return handlerPanel;
    }

    /**
     * @param handlerPanel the handlerPanel to set
     */
    public void setHandlerPanel(InvoiceInfoPanel handlerPanel) {
        this.handlerPanel = handlerPanel;
    }
    
    
}
