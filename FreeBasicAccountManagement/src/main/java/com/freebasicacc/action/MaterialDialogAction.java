/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.action;

import com.freebasicacc.dao.MaterialDAO;
import com.freebasicacc.dao.MaterialDAOImpl;
import com.freebasicacc.dao.UnitDAO;
import com.freebasicacc.dao.UnitDAOImpl;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.MaterialInFoListDialogPanel;

import com.freebasicacc.ui.QuotationInfoPanel;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.InvoiceInfoPanel;
import com.freebasicacc.ui.renderer.MaterialDialogTableCellRenderer;
import com.freebasicacc.ui.renderer.UnitComboboxRenderer;
import com.freebasicacc.util.DialogUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author benzyaa
 */
public class MaterialDialogAction {
    private static transient Logger logger = LogManager.getLogger(MaterialDialogAction.class);
    public void assignUnitCombobox(JComboBox combobox,Object unitIdBankingObject,Object selectedValue){
        try{
           combobox.removeAllItems();
           Unit unit = new Unit();
           unit.setIsActive(1);
           UnitDAO unitDAO = new UnitDAOImpl();
           List<Unit> unitList = unitDAO.getUnitByCriteria(unit);
           Unit blankUnit = new Unit();
           blankUnit.setUnitId("");
           blankUnit.setUnitName(MessageUtil.getMessage("common.select"));
           combobox.addItem(blankUnit);
           for(Unit u : unitList){
                combobox.addItem(u);
           }
           UnitComboboxRenderer renderer = new UnitComboboxRenderer();
           renderer.setUnitIdBankObject(unitIdBankingObject);
       combobox.setRenderer(renderer);
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
        }
    }
    
    public void searchMaterialAction(MaterialInFoListDialogPanel panel,boolean isClearSearch){
        try{
        Material material = new Material();
            if(!isClearSearch){
                 material.setMaterialId(panel.getMaterialIdSearchTextField().getText());
                 material.setMaterialName(panel.getMaterialNameSearchTextField().getText());
                 material.setUnit((Unit)panel.getMatertialUnitCombobox().getSelectedItem());String materialPriceStr = panel.getMaterialPriceSearchTextField().getText();
                 if(StringUtil.isContainText(materialPriceStr)) material.setMaterialPrice(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(materialPriceStr).doubleValue()));
                 String materialPriceToStr = panel.getMaterialPriceSearchToTextField().getText();
                 if(StringUtil.isContainText(materialPriceToStr)) material.setMaterialPriceTo(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(materialPriceToStr).doubleValue())); OrderBy orderBy = (OrderBy)panel.getSortingByCombobox().getSelectedItem();
                 boolean isDesc = "DESC".equals(panel.getRadioSelectName());
                 material.setIsDesc(isDesc);
                 material.setOrderBy(orderBy.getId());
            }else{
                material.setOrderBy("MATERIAL_ID");
                material.setIsDesc(true);
            }
            material.setIsActive(1);
            MaterialDAO materialDAO = new MaterialDAOImpl();
            List<Material> materialList = materialDAO.getMaterialByCriteria(material);
            panel.setDataList(materialList);
            this.setMaterialPanelTableWithNextPrevious(panel);
         }catch(Exception ex){
           logger.info("ERROR",ex);
          DialogUtil.displayErrorMessage(ex);
        }
    }
    
    public void setMaterialPanelTableWithNextPrevious(MaterialInFoListDialogPanel panel){
        List<Material> materialList = panel.getDataList();
        int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
        String rowPerpageTable = String.valueOf(panel.getRowPerpageCombobox().getSelectedItem());
        int rowPerPage = StringUtil.isContainText(rowPerpageTable) ? Integer.parseInt(rowPerpageTable) : 10;
        int subStartPosition = (pageNo-1)*rowPerPage;
        int subEndPosition = ((pageNo-1)+1)*rowPerPage;
        int resultCount = materialList.size();
        subEndPosition = subEndPosition > resultCount ? resultCount : subEndPosition;
        int totalPage = resultCount/rowPerPage;
        totalPage = totalPage <=0 ? 1 : totalPage+1;
        panel.getPageTotalLabel().setText(String.valueOf(totalPage));
        materialList = materialList.subList(subStartPosition, subEndPosition);
        setMaterialPanelTable(panel,materialList);
    }
    
    private void setMaterialPanelTable(MaterialInFoListDialogPanel panel, Collection<Material> materialSet){
        JTable materialTable = panel.getMaterialInfoListTable();
        materialTable.setDefaultRenderer(Object.class, new MaterialDialogTableCellRenderer());
        Enumeration<TableColumn> enu = materialTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
        TableModel tableModel = new DefaultTableModel(tableIdentiferList.toArray(),materialSet.size());
        materialTable.setModel(tableModel);
        ListSelectionModel selectionModel = materialTable.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        int rowIndex = 0;
        for(Material material:materialSet){
            int colIndex = 0;
            materialTable.setValueAt(rowIndex+1, rowIndex, colIndex++);
            materialTable.setValueAt(material, rowIndex, colIndex++);
            materialTable.setValueAt(material.getMaterialName(), rowIndex, colIndex++);
            materialTable.setValueAt(material.getUnit().getUnitName(), rowIndex, colIndex++);
            String materialPriceStr =(material.getMaterialPrice() !=null)?StringUtil.formatThaiCurrency(material.getMaterialPrice()) : "";
            materialTable.setValueAt(materialPriceStr, rowIndex, colIndex++);
            rowIndex++;
        }
    }
    
    public void openMaterialEditFormAction(MaterialInFoListDialogPanel panel){
        try{
            String editId = this.getTableSelectedId(panel);
            Material material = new Material();
            material.setMaterialId(editId);
            MaterialDAO materialDAO = new MaterialDAOImpl();
            material = materialDAO.getMaterialByCriteria(material).get(0);
            MainFrameAction mainFrameAction = new MainFrameAction();
            Map<String,Object> descriptionMap = new HashMap<String,Object>();
            descriptionMap.put("MATERIAL_OBJECT", material);
            JMenu menu = new JMenu();
            menu.setName("MATERIAL_EDIT_MENU_ITEM");
            mainFrameAction.openInternalFormByType((MainFrame)panel.getMainFrame(), menu, panel, descriptionMap);
         }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
        }
    }
    
    public void addToOpenerTable(MaterialInFoListDialogPanel panel,JPanel openerPanel){
        JTable materialTable = panel.getMaterialInfoListTable();
        int selectedRowIndex = materialTable.getSelectedRow();
        DefaultTableModel defaultTableModel = (DefaultTableModel) materialTable.getModel();
        Material selectedMaterial = (Material)defaultTableModel.getValueAt(selectedRowIndex, 1);
        if(openerPanel instanceof QuotationInfoPanel){
            QuotationAction action = new QuotationAction();
            action.addQuotationDetailRow((QuotationInfoPanel)openerPanel,selectedMaterial);
        }
        else if(openerPanel instanceof InvoiceInfoPanel){
           InvoiceAction action = new InvoiceAction();
           action.addInvoiceDetailRow((InvoiceInfoPanel)openerPanel, selectedMaterial);
        }
    }
    
     private String getTableSelectedId(MaterialInFoListDialogPanel panel){
        JTable table = panel.getMaterialInfoListTable();
        int selectedIndex = table.getSelectedRow();
        return String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
    }
}