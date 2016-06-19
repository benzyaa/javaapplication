/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.listener;

import com.freebasicacc.action.QuotationAction;
import com.freebasicacc.ui.QuotationInfoPanel;
import com.freebasicacc.util.StringUtil;
import java.math.BigDecimal;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author benzyaa
 */
public class QuotationDetailListTableListener implements TableModelListener {

    private QuotationInfoPanel handlerPanel;
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
            Object pricePerUnitObj = defaultTableModel.getValueAt(selectedRowIndex, 4); // get Quantity
            Number pricePerUnitObjNumber = StringUtil.formatThaiCurrencyStrToNumber(String.valueOf(pricePerUnitObj));
            if(selectedColumnIndex !=2) return;
            BigDecimal quantityBig = new BigDecimal(String.valueOf(quantityObj));
            BigDecimal pricePerUnitBig = new BigDecimal(pricePerUnitObjNumber.doubleValue()); 
            BigDecimal totalValue =  quantityBig.multiply(pricePerUnitBig);
            totalValue = totalValue.setScale(10);
            String totalCurrencyText = StringUtil.formatThaiCurrency(totalValue);
            //defaultTableModel.setValueAt(String.valueOf(totalValue.doubleValue()),selectedRowIndex, 5); // Total price
            defaultTableModel.setValueAt(totalCurrencyText,selectedRowIndex, 5);
            QuotationAction action = new QuotationAction();
            action.calculateTotalDetail(handlerPanel);
        }catch(Exception ex){
         ex.printStackTrace();
        }
    }

    /**
     * @return the handlerPanel
     */
    public QuotationInfoPanel getHandlerPanel() {
        return handlerPanel;
    }

    /**
     * @param handlerPanel the handlerPanel to set
     */
    public void setHandlerPanel(QuotationInfoPanel handlerPanel) {
        this.handlerPanel = handlerPanel;
    }
    
    
}
