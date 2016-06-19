/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.action;

import com.freebasicacc.dao.UnitDAO;
import com.freebasicacc.dao.UnitDAOImpl;
import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.UnitAddPanel;
import com.freebasicacc.ui.UnitEditPanel;
import com.freebasicacc.ui.UnitListPanel;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.DialogUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
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
public class UnitAction {
     private static transient Logger logger = LogManager.getLogger(UnitAction.class);
    public void searchUnitAction(UnitListPanel panel,boolean isClearSearch){
        try{
            Unit unit = new Unit();
            if(!isClearSearch){
                unit.setUnitName(panel.getUnitNameTextField().getText());
            }
            unit.setIsActive(1);
            UnitDAO unitDAO = new UnitDAOImpl();
            List<Unit> unitList = unitDAO.getUnitByCriteria(unit);
            this.setUnitPanelTable(panel, unitList);
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    public void saveUnitAction(UnitAddPanel panel){
        try{
           MainFrameAction action = new MainFrameAction();
           if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
            Unit unit = new Unit();
            unit.setUnitName(panel.getUnitNameTextField().getText());
            unit.setUnitDesc(panel.getUnitDescTextField().getText());
            unit.setCreateBy(CommonUtil.getComputerName());
            unit.setCreateDate(new java.util.Date());
            unit.setUpdateBy(CommonUtil.getComputerName());
            unit.setUpdateDate(new java.util.Date());
            unit.setIsActive(1);
            this.validateMandatoryForSaveUpdate(unit);
            UnitDAO unitDAO = new UnitDAOImpl();
            unitDAO.insertUnit(unit);
            if(panel == null)return;
            searchUnitAction((UnitListPanel)panel.getOpenerPanel(),true);
            DialogUtil.displayDataSaveSuccessMessage();
            this.closeUnitDetailFrame(panel.getInternalFrame());
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    public void openUnitEditFormAction(UnitListPanel panel){
        try{
            String editId = this.getTableSelectedId(panel);
            if(!StringUtil.isContainText(editId)) {
                    DialogUtil.displaySelectAtleastOneItemDialog();
                return;
            }
            Unit unit = new Unit();
            unit.setUnitId(editId);
            unit.setIsActive(1);
            UnitDAO unitDAO = new UnitDAOImpl();
            unit = unitDAO.getUnitByCriteria(unit).get(0);
            MainFrameAction mainFrameAction = new MainFrameAction();
            Map<String,Object> descriptionMap = new HashMap<String,Object>();
            descriptionMap.put("UNIT_OBJECT", unit);
            JMenu menu = new JMenu();
            menu.setName("UNIT_EDIT_MENU_ITEM");
            mainFrameAction.openInternalFormByType((MainFrame)panel.getMainFrame(), menu, panel, descriptionMap);
            unitDAO.getUnitByCriteria(unit);
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    public void updateUnitAction(UnitEditPanel panel){
        try{
           MainFrameAction action = new MainFrameAction();
           if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
            Unit unit = new Unit();
            unit.setUnitId(panel.getUnitIdValueLabel().getText());
            unit.setIsActive(1);
            this.validateMandatoryForSaveUpdate(unit);
            UnitDAO unitDAO = new UnitDAOImpl();
            unit  = unitDAO.getUnitByCriteria(unit).get(0);
            unit.setUnitName(panel.getUnitNameTextField().getText());
            unit.setUnitDesc(panel.getUnitDescTextField().getText());
            unit.setUpdateBy(CommonUtil.getComputerName());
            unit.setUpdateDate(new Date());
            unitDAO.updateUnit(unit);
            this.searchUnitAction((UnitListPanel)panel.getOpenerPanel(), true);
            DialogUtil.displayDataSaveEditSuccessMessage();
            this.closeUnitDetailFrame(panel.getInternalFrame());
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    public void closeUnitDetailFrame(JInternalFrame internalFrame){
        MainFrameAction action = new MainFrameAction();
        action.closeInternalFrame(internalFrame);
    }
    
    private void setUnitPanelTable(UnitListPanel panel, Collection<Unit> unitList){
        JTable unitTable = panel.getUnitInfoTable();
        Enumeration<TableColumn> enu = unitTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
        TableModel tableModel = new DefaultTableModel(tableIdentiferList.toArray(),unitList.size());
        unitTable.setModel(tableModel);
        ListSelectionModel selectionModel = unitTable.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        int rowIndex = 0;
        for(Unit unit : unitList){
            int colIndex = 0;
            unitTable.setValueAt(rowIndex+1, rowIndex, colIndex++);
            unitTable.setValueAt(unit.getUnitId(), rowIndex, colIndex++);
            unitTable.setValueAt(unit.getUnitName(), rowIndex, colIndex++);
            rowIndex++;
        }
    }
    
    public void deleteUnit(UnitListPanel panel){
        try{
            String deletedId = this.getTableSelectedId(panel);
              if(!StringUtil.isContainText(deletedId)) {
                    DialogUtil.displaySelectAtleastOneItemDialog();
                return;
            }
            MainFrameAction action = new MainFrameAction();
            if(!action.popupConfirmDialog(AppConstants.REMOVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_REMOVE_DIALOG_TITLE))return;
            Unit unit = new Unit();
            unit.setUnitId(deletedId);
            unit.setIsActive(1);
            UnitDAO unitDAO = new UnitDAOImpl();
            unit  = unitDAO.getUnitByCriteria(unit).get(0);
            unit.setIsActive(0);
            unit.setUpdateBy(CommonUtil.getComputerName());
            unit.setUpdateDate(new Date());
            unitDAO.updateUnit(unit);
            DialogUtil.displayDataSaveSuccessMessage(MessageUtil.getMessage("notification.data.materialunit.deleted"));
            this.searchUnitAction(panel, true);
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    private void validateMandatoryForSaveUpdate(Unit unit) throws Exception{
        if(!StringUtil.isContainText(unit.getUnitName())){
            throw new Exception(MessageUtil.getMessage("errormessage.requireddata"));
        }
    }
    
    private String getTableSelectedId(UnitListPanel panel){
        JTable table = panel.getUnitInfoTable();
        int selectedIndex = table.getSelectedRow();
        if(selectedIndex == -1)return "";
        return String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
    }
}