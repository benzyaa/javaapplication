/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.ui.action;

import com.freebasicacc.model.Customer;
import com.freebasicacc.model.InvoiceHead;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.AboutInternalFrame;
import com.freebasicacc.ui.CustomerInfoFormEditInternalFrame;
import com.freebasicacc.ui.CustomerInfoFormInternalFrame;
import com.freebasicacc.ui.CustomerInfoListInternalFrame;
import com.freebasicacc.ui.CustomerListDialogInternalFrame;
import com.freebasicacc.ui.InvoiceInfoInternalFrame;
import com.freebasicacc.ui.InvoiceInfoListInternalFrame;
import com.freebasicacc.ui.InvoiceInfoViewInternalFrame;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.MaterialInfoEditInternalFrame;
import com.freebasicacc.ui.MaterialInfoInternalFrame;
import com.freebasicacc.ui.MaterialInfoListInternalFrame;
import com.freebasicacc.ui.MaterialListDialogInternalFrame;
import com.freebasicacc.ui.QuotationInfoInternalFrame;
import com.freebasicacc.ui.QuotationInfoViewInternalFrame;
import com.freebasicacc.ui.QuotationListInternalFrame;
import com.freebasicacc.ui.SettingsInternalFrame;
import com.freebasicacc.ui.UnitAddPanelInternalFrame;
import com.freebasicacc.ui.UnitEditPanelInternalFrame;
import com.freebasicacc.ui.UnitListInternalFrame;
import com.freebasicacc.ui.renderer.OrderByComboboxRenderer;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.StringUtil;
import java.awt.Component;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author benzyaa
 */
public class MainFrameAction {
     private static transient Logger logger = LogManager.getLogger(MainFrameAction.class);
    public void openInternalFormByType(MainFrame frame,JMenuItem menu){
        this.openInternalFormByType(frame, menu, null,null);
    }
    
    public void openInternalFormByType(MainFrame frame,JMenuItem menu,JPanel openerPanel,Map descriptionMap){
         String menuName = menu.getName();
         JInternalFrame internalFrame = null;
         JDesktopPane desktopPane = frame.getMainFrameDesktopPanel();
         boolean isAddComponents = true; 
         if("CUSTOMER_ADD_MENU_ITEM".equals(menuName))internalFrame = new CustomerInfoFormInternalFrame(openerPanel);
         else if("CUSTOMER_EDIT_MENU_ITEM".equals(menuName))internalFrame = new CustomerInfoFormEditInternalFrame(openerPanel,(Customer)descriptionMap.get("CUSTOMER_OBJECT"));
         else if("CUSTOMER_LIST_MENU_ITEM".equals(menuName))internalFrame = CustomerInfoListInternalFrame.getCustomerInfoListInternalFrameInstance(frame);
         else if("CUSTOMER_DIALOG_MENU_ITEM".equals(menuName)) internalFrame = new CustomerListDialogInternalFrame(openerPanel);
         else if("UNIT_DIALOG_DATA_MENU_ITEM".equals(menuName)) internalFrame =  UnitListInternalFrame.getInstances(frame);
         else if("UNIT_ADD_MENU_ITEM".equals(menuName)) internalFrame =  new UnitAddPanelInternalFrame(openerPanel);
         else if("UNIT_EDIT_MENU_ITEM".equals(menuName)) internalFrame =  new UnitEditPanelInternalFrame(openerPanel,(Unit)descriptionMap.get("UNIT_OBJECT"));
         else if("MATERIAL_ADD_MENU_ITEM".equals(menuName)) internalFrame = new MaterialInfoInternalFrame(openerPanel);
         else if("MATERIAL_LIST_MENU_ITEM".equals(menuName)) internalFrame = new MaterialInfoListInternalFrame(frame);
         else if("MATERIAL_EDIT_MENU_ITEM".equals(menuName)) internalFrame = new MaterialInfoEditInternalFrame(openerPanel,(Material)descriptionMap.get("MATERIAL_OBJECT"));
         else if("MATERIAL_DIALOG_MENU_ITEM".equals(menuName)) internalFrame = new MaterialListDialogInternalFrame(openerPanel);
         else if("QUOTATION_ADD_MENU_ITEM".equals(menuName)) internalFrame = new QuotationInfoInternalFrame(frame, openerPanel);
         else if("QUOTATION_VIEW_MENU_ITEM".equals(menuName)) internalFrame = new QuotationInfoViewInternalFrame((QuotationHead)descriptionMap.get("QUOTATION_OBJECT"));
         else if("QUOTATION_LIST_MENU_ITEM".equals(menuName)) internalFrame = new QuotationListInternalFrame(frame);
         else if("INVOICE_ADD_MENU_ITEM".equals(menuName)) internalFrame = new InvoiceInfoInternalFrame(frame, openerPanel);
         else if("INVOICE_LIST_MENU_ITEM".equals(menuName)) internalFrame = new InvoiceInfoListInternalFrame(frame);
         else if("INVOICE_VIEW_MENU_ITEM".equals(menuName)) internalFrame = new InvoiceInfoViewInternalFrame((InvoiceHead)descriptionMap.get("INVOICE_OBJECT"));
         else if("SETTINGS_MENU_ITEM".equals(menuName)) internalFrame = new SettingsInternalFrame(frame);
         else if("ABOUT_MENU_ITEM".equals(menuName)) internalFrame = new AboutInternalFrame(frame);
         logger.info("MENU => "+menu.getName());
        if(menuName.indexOf("LIST") >=0 || menuName.indexOf("SETTINGS") >=0){
                if(desktopPane.getComponents().length > 0){
                      isAddComponents =this.popupConfirmDialog(AppConstants.CHANGE_INTERNAL_FRAME_MESSAGE, AppConstants.WARNING_TITLE);
                     if(isAddComponents) diposeMajorInternalFrame(desktopPane);
                }
          }
        if(isAddComponents){
             if(!isExistInternalFrameInDesktopPane(desktopPane,internalFrame)){   
                 desktopPane.add(internalFrame);
                 internalFrame.setVisible(true);
             }
        }
     }
    
     private boolean isExistInternalFrameInDesktopPane(JDesktopPane desktopPane,Component component){
         Component[] components = desktopPane.getComponents();
         String componentClassName = component.getClass().getName();
         for(Component c : components){
              if(componentClassName.equals(c.getClass().getName())){
                  return true;
              }
         }
         return false;
     }
    
     private void diposeMajorInternalFrame(JDesktopPane desktopPane){
        
         Set<String> majorFrameClassName = new HashSet<String>();
         majorFrameClassName.add(CustomerInfoListInternalFrame.class.getName());
         majorFrameClassName.add(MaterialInfoListInternalFrame.class.getName());
         majorFrameClassName.add(QuotationListInternalFrame.class.getName());
         majorFrameClassName.add(InvoiceInfoListInternalFrame.class.getName());
         majorFrameClassName.add(SettingsInternalFrame.class.getName());
         Component[] components = desktopPane.getComponents();
         logger.info(components.length );
         for(Component c : components){
                 System.out.println(c.getClass().getName());
                 if(majorFrameClassName.contains(c.getClass().getName())){
                     JInternalFrame internalFrame = (JInternalFrame)c;
                     internalFrame.dispose();
                     disposeAllChildInternalFrame(desktopPane);
                     return;
                 }
         }
     }
     
     private void disposeAllChildInternalFrame(JDesktopPane desktopPane){
        Component[] components = desktopPane.getComponents(); 
         for(Component c : components){
             if(c instanceof JInternalFrame){
                 JInternalFrame internalFrame = (JInternalFrame)c;
                 internalFrame.dispose();
             }
         }
     }
     
     public void assignComboBoxModel(JComboBox combobox,Object[] comboboxItemArr,Object selectedItem){
            combobox.removeAllItems();
            for(Object comboboxItem : comboboxItemArr){
                combobox.addItem(comboboxItem);
            }
            combobox.setRenderer(new OrderByComboboxRenderer());
     }
     
     
     public void closeInternalFrame(JInternalFrame internalFrame){
         internalFrame.dispose();
     }
     
      public void removeTableRow(JTable table){ // to prevent excption
          DefaultTableModel model =  (DefaultTableModel)table.getModel();
          int modelRowCount = model.getRowCount();
          int selectedRowIndex = table.getSelectedRow();
          if(selectedRowIndex > modelRowCount - 1) return;
          model.removeRow(selectedRowIndex);
    }
      
       public void removeAllTableRow(JTable table){ // to prevent excption
         int rowCount = table.getModel().getRowCount();
         if( rowCount <=0) return;
         DefaultTableModel model =  (DefaultTableModel)table.getModel();   
          model.getDataVector().removeAllElements();
          model.fireTableDataChanged();
    }
      
       public void setPanelTableWidth(JTable table, Map<String,Integer> widthPrefValueMap){
           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           TableColumnModel tableColumnModel = table.getColumnModel();
           Enumeration<TableColumn> tableColumnModelEnu = tableColumnModel.getColumns();
           while(tableColumnModelEnu.hasMoreElements()){
               TableColumn column = tableColumnModelEnu.nextElement();
               logger.info("column : "+column);
               logger.info("widthPrefValueMap : "+widthPrefValueMap);
               column.setPreferredWidth(widthPrefValueMap.get(column.getHeaderValue()));
           }
    }
       
       public void setInternalFramTitle(JInternalFrame internalFrame){
           Component components[] = internalFrame.getComponents();
           for(Component component : components){
              logger.info(component.getClass().getName());
               if(component instanceof javax.swing.plaf.metal.MetalInternalFrameTitlePane){
                   javax.swing.plaf.metal.MetalInternalFrameTitlePane internalTitleFrame = (javax.swing.plaf.metal.MetalInternalFrameTitlePane)component;
                   internalTitleFrame.setFont(new java.awt.Font("Tahoma", 1, 13));
               }
           }
       }
       
       public boolean isValidEmailAddress(String email) {
        try {
             new InternetAddress(email).validate();
        } catch (AddressException ex) {
            return false;
        }
            return true;
        }

       public boolean popupConfirmDialog(String popupMessage,String titleMessage){
           int result = JOptionPane.showConfirmDialog(null, popupMessage, titleMessage, JOptionPane.YES_NO_OPTION);
           return result == JOptionPane.YES_OPTION;
       }
       
       public void assignNumberToTextFieldByCurrency(JTextField textField){
            String currencyStr = textField.getText();
            if(!StringUtil.isContainText(currencyStr))return;
            double  number  = StringUtil.formatThaiCurrencyStrToNumber(currencyStr).doubleValue();
            textField.setText(String.valueOf(number));
       }
       public void assignCurrencyTextToNumberByCurrency(JTextField textField){
            String numberStr = textField.getText();
            if(!StringUtil.isContainText(numberStr))return;
            numberStr = numberStr.replaceAll(",","");
            double number = Double.parseDouble(numberStr);
            numberStr = StringUtil.formatThaiCurrency(number);
            textField.setText(numberStr);
       }
}