package com.freebasicacc.action;

import com.freebasicacc.dao.CustomerDAO;
import com.freebasicacc.dao.CustomerDAOImpl;
import com.freebasicacc.model.Customer;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.ui.CustomerInFoListPanel;
import com.freebasicacc.ui.CustomerInfoPanel;
import com.freebasicacc.ui.CustomerInfoPanelEdit;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.DialogUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerAction {
     private static transient Logger logger = LogManager.getLogger(CustomerAction.class);
    public void saveCustomerAction(CustomerInfoPanel panel){
       try{
           MainFrameAction action = new MainFrameAction();
           if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
           Customer customer = new Customer();
           customer.setCustomerName(panel.getNameTextField().getText());
           customer.setCustomerContract(panel.getContractTextField().getText());
           customer.setCustomerAddres(panel.getAddressTextArea().getText());
           customer.setCustomerPhone(panel.getPhoneTextField().getText());
           customer.setCustomerFax(panel.getFaxTextField().getText());
           customer.setCustomerEmail(panel.getEmailTextField().getText());
           customer.setCustomerAddres(panel.getAddressTextArea().getText());
           customer.setRemark(panel.getRemarkTextArea().getText());
           Calendar calendar = Calendar.getInstance(new Locale("th","TH"));
           Date processDate = calendar.getTime();
           customer.setRegisterDate(processDate);
           customer.setLastContractDate(processDate);
           customer.setCreateBy(CommonUtil.getComputerName());
           customer.setCreateDate(processDate);
           customer.setUpdateBy(CommonUtil.getComputerName());
           customer.setUpdateDate(processDate);
           customer.setIsActive(1);
           this.validateMandatoryForSaveUpdate(customer);
           CustomerDAO customerDAO = new CustomerDAOImpl();
           customerDAO.insertCustomer(customer);
           JPanel openerPanel = panel.getOpenerPanel();
           if(openerPanel == null)return;
           this.searchCustomer((CustomerInFoListPanel)openerPanel, true);
           DialogUtil.displayDataSaveSuccessMessage();
           action.closeInternalFrame(panel.getInternalFrame());
       }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
       
    }
    
    public void updateCustomerAction(CustomerInfoPanelEdit panel){
       try{
           MainFrameAction action = new MainFrameAction(); 
           if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
           Customer customer = new Customer();
           customer.setCustomerId(panel.getCustomerIdvalueLabel().getText());
           customer.setIsActive(1);
           CustomerDAO customerDAO = new CustomerDAOImpl();
           customer = customerDAO.getCustomerByCriteria(customer).get(0);
           customer.setCustomerName(panel.getNameTextField().getText());
           customer.setCustomerContract(panel.getContractTextField().getText());
           customer.setCustomerAddres(panel.getAddressTextArea().getText());
           customer.setCustomerPhone(panel.getPhoneTextField().getText());
           customer.setCustomerFax(panel.getFaxTextField().getText());
           customer.setCustomerEmail(panel.getEmailTextField().getText());
           customer.setCustomerAddres(panel.getAddressTextArea().getText());
           customer.setRemark(panel.getRemarkTextArea().getText());
           Calendar calendar = Calendar.getInstance(new Locale("th","TH"));
           Date processDate = calendar.getTime();
           customer.setUpdateBy(CommonUtil.getComputerName());
           customer.setUpdateDate(processDate);
           this.validateMandatoryForSaveUpdate(customer);
           customerDAO.updateCustomer(customer);
           JPanel openerPanel = panel.getOpenerPanel();
           DialogUtil.displayDataSaveEditSuccessMessage();
           action.closeInternalFrame(panel.getInternalFrame());
           if(openerPanel == null)return;
           this.searchCustomer((CustomerInFoListPanel)openerPanel, true);
       }catch(Exception ex){
           logger.info("ERROR",ex);
            DialogUtil.displayErrorMessage(ex);

       }
    }
    
    public void removeCustomerAction(CustomerInFoListPanel panel){
       try{
           Customer customer = new Customer();
            JTable table = panel.getCustomerInfoListTable();
            int selectedIndex = table.getSelectedRow();
            if(selectedIndex <0) {
                DialogUtil.displaySelectAtleastOneItemDialog();
                return;
           }
           MainFrameAction action = new MainFrameAction(); 
           if(!action.popupConfirmDialog(AppConstants.REMOVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_REMOVE_DIALOG_TITLE))return;
           String customerId = String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
           customer.setCustomerId(customerId);
           customer.setIsActive(1);
           CustomerDAO customerDAO = new CustomerDAOImpl();
           customer = customerDAO.getCustomerByCriteria(customer).get(0);
           customer.setIsActive(0);
           Calendar calendar = Calendar.getInstance(new Locale("th","TH"));
           Date processDate = calendar.getTime();
           customer.setUpdateBy(CommonUtil.getComputerName());
           customer.setUpdateDate(processDate);
           customerDAO.updateCustomer(customer);
            DialogUtil.displayDataSaveSuccessMessage(MessageUtil.getMessage("notification.data.customer.deleted"));
           this.searchCustomer(panel, true);
       }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    public void openCustomerEditFormAction(CustomerInFoListPanel panel){
        try{
            JTable table = panel.getCustomerInfoListTable();
            int selectedIndex = table.getSelectedRow();
            if(selectedIndex <0) {
                    DialogUtil.displaySelectAtleastOneItemDialog();
                return;
            }
            String customerId = String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setIsActive(1);
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customer = customerDAO.getCustomerByCriteria(customer).get(0);
            MainFrameAction mainFrameAction = new MainFrameAction();
            Map<String,Object> descriptionMap = new HashMap<String,Object>();
            descriptionMap.put("CUSTOMER_OBJECT", customer);
            JMenu menu = new JMenu();
            menu.setName("CUSTOMER_EDIT_MENU_ITEM");
            mainFrameAction.openInternalFormByType((MainFrame)panel.getMainFrame(), menu, panel, descriptionMap);
        }catch(Exception ex){
            logger.info("ERROR",ex);
            DialogUtil.displayErrorMessage(ex);
        }
    }
    
    public void searchCustomer(CustomerInFoListPanel panel,boolean isClearSearch){
       try{
           Customer customer = new Customer();
           int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
           int rowPerPage = Integer.parseInt(String.valueOf(panel.getRowPerpageCombobox().getSelectedItem()));
          
           if(!isClearSearch){
                customer.setCustomerId(panel.getCustomerIdSearchTextField().getText());
                customer.setCustomerName(panel.getCustomerNameSearchTextField().getText());
                customer.setCustomerContract(panel.getCustomerContractSearchTextField().getText());
                customer.setCustomerPhone(panel.getCustomerPhoneSearchTextField().getText());
                customer.setCustomerFax(panel.getCustomerFaxSearchTextField().getText());
                customer.setCustomerEmail(panel.getCustomerEmailSearchTextField().getText());
                String registerDateFromStr = panel.getCustomerRegisterFromTextField().getText();
                String registerDateToStr = panel.getCustomerRegisterToTextField().getText();
                String lastContractDateFromStr = panel.getCustomerLastContractDateFromTextField().getText();
                String lastContracrtDateToStr = panel.getCustomerLastContractDateToTextField().getText();
                Date registerDateFrom = StringUtil.isContainText(registerDateFromStr) ? DateUtil.convertDateStrToDateUtil(registerDateFromStr) : null;
                Date registerDateTo = StringUtil.isContainText(registerDateToStr) ? DateUtil.convertDateStrToDateUtil(registerDateToStr) : null;
                Date lastContractDateFrom = StringUtil.isContainText(lastContractDateFromStr) ? DateUtil.convertDateStrToDateUtil(lastContractDateFromStr) : null;
                Date lastContractDateTo = StringUtil.isContainText(lastContracrtDateToStr) ? DateUtil.convertDateStrToDateUtil(lastContracrtDateToStr) : null;
                customer.setRegisterDate(registerDateFrom);
                customer.setRegisterDateTo(registerDateTo);
                customer.setLastContractDate(lastContractDateFrom);
                customer.setLastContractDateTo(lastContractDateTo);
                boolean isDesc = "DESC".equals(panel.getRadioSelectName());
                customer.setIsDesc(isDesc);  
                OrderBy selectObject = (OrderBy) panel.getSortingByCombobox().getSelectedItem();
                customer.setOrderBy(selectObject.getId());
           }else{
                customer.setOrderBy("CUSTOMER_ID");
                customer.setIsDesc(true);
           }
           this.validateCustomerInfoSearchInput(customer);
           customer.setIsActive(1);
           CustomerDAO customerDAO = new CustomerDAOImpl();
           List<Customer> customerResultList = customerDAO.getCustomerByCriteria(customer);
           panel.setDataList(customerResultList);
           //int totalPage = resultCount/rowPerPage;
           //totalPage = totalPage <=0 ? 1 : totalPage+1;
           //panel.getPageTotalLabel().setText(String.valueOf(totalPage));
           this.setCustomerPanelTableWithNextPrevious(panel);
       }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    private void validateCustomerInfoSearchInput(Customer customer) throws Exception{
        /** Start Validate date ***/
        String illegalCriteriaMessage = MessageUtil.getMessage("errormessage.wrongcondition");
        Date registerDateFrom = customer.getRegisterDate();
        Date registerDateTo = customer.getRegisterDateTo();
        Date lastContractDateFrom = customer.getLastContractDate();
        Date lastContractDateTo = customer.getLastContractDateTo();
        MainFrameAction action = new MainFrameAction();
        if(StringUtil.isContainText(customer.getCustomerEmail()) && !action.isValidEmailAddress(customer.getCustomerEmail())){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("customer.email")));
        }
        if((registerDateFrom ==null && registerDateTo !=null)||(registerDateFrom !=null && registerDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("customer.registerdate")));
        }
        if(registerDateFrom !=null && registerDateTo !=null){
            long registerDateDiff = DateUtil.calculateDateDiff(registerDateTo, registerDateFrom);
            if(registerDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("customer.registerdate")));
        }
        if((lastContractDateFrom ==null && lastContractDateTo !=null)||(lastContractDateFrom !=null && lastContractDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("customer.lastcontractdate")));
        }
        if(lastContractDateFrom !=null && lastContractDateTo !=null){
            long lastContractDateDiff = DateUtil.calculateDateDiff(lastContractDateTo, lastContractDateFrom);
            if(lastContractDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("customer.lastcontractdate")));
        }
        /***  End Validate date ***/
    }
    
    private void validateMandatoryForSaveUpdate(Customer customer) throws Exception{
         MainFrameAction action = new MainFrameAction();
        if(StringUtil.isContainText(customer.getCustomerEmail()) && !action.isValidEmailAddress(customer.getCustomerEmail())){
            throw new IllegalArgumentException(MessageUtil.getMessage("errormessage.wrongemail"));
        }
        if(!(StringUtil.isContainText(customer.getCustomerName())&&
           StringUtil.isContainText(customer.getCustomerContract())&&
           StringUtil.isContainText(customer.getCustomerPhone())&&
           StringUtil.isContainText(customer.getCustomerAddres()))){
            throw new Exception(MessageUtil.getMessage("errormessage.requireddata"));
        }
    }
    
    public void setCustomerPanelTableWithNextPrevious(CustomerInFoListPanel panel){
        List<Customer> customerList = panel.getDataList();
        int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
        String rowPerpageTable = String.valueOf(panel.getRowPerpageCombobox().getSelectedItem());
        int rowPerPage = StringUtil.isContainText(rowPerpageTable) ? Integer.parseInt(rowPerpageTable) : 10;
        int subStartPosition = (pageNo-1)*rowPerPage;
        int subEndPosition = ((pageNo-1)+1)*rowPerPage;
        int resultCount = customerList.size();
        subEndPosition = subEndPosition > resultCount ? resultCount : subEndPosition;
        int totalPage = resultCount/rowPerPage;
        totalPage = totalPage <=0 ? 1 : totalPage+1;
        panel.getPageTotalLabel().setText(String.valueOf(totalPage));
        customerList = customerList.subList(subStartPosition, subEndPosition);
        setCustomerPanelTable(panel,customerList);
    }
    
    private void setCustomerPanelTable(CustomerInFoListPanel panel, Collection<Customer> customerSet){
        JTable customerTable = panel.getCustomerInfoListTable();
        Enumeration<TableColumn> enu = customerTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
        TableModel tableModel = new DefaultTableModel(tableIdentiferList.toArray(),customerSet.size());
        customerTable.setModel(tableModel);
        ListSelectionModel selectionModel = customerTable.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        int rowIndex = 0;
        for(Customer customer:customerSet){
            int colIndex = 0;
            customerTable.setValueAt(rowIndex+1, rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerId(), rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerName(), rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerContract(), rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerPhone(), rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerEmail(), rowIndex, colIndex++);
            customerTable.setValueAt(customer.getCustomerAddres(), rowIndex, colIndex++);
            String registerDateStr =(customer.getRegisterDate() !=null)?DateUtil.convertDateUtilToDateStr(customer.getRegisterDate()) : "";
            customerTable.setValueAt(registerDateStr, rowIndex, colIndex++);
            String lastContractDateStr =(customer.getLastContractDate() !=null)?DateUtil.convertDateUtilToDateStr(customer.getLastContractDate()) : "";
            customerTable.setValueAt(lastContractDateStr, rowIndex, colIndex++);
            rowIndex++;
        }
        //setCustomerPanelTableWidth(customerTable);
    }
    private boolean isClearInput(JPanel jpanel){
        Component[] components = jpanel.getComponents();
        JTextField textField = null;
        JComboBox combobox = null;
        for(Component c : components){
            if(c instanceof JTextField){
                textField = (JTextField)c;
                if(StringUtil.isContainText(textField.getText()))return false;
            
           }else if(c instanceof JComboBox){
                combobox = (JComboBox)c;
                if(combobox.getSelectedItem() != null) return false;
            }
        }
        return true;
    }
    
    private void setCustomerPanelTableWidth(JTable table){
         Map<String,Integer> widthPrefValueMap = new LinkedHashMap<String,Integer>(9);
         widthPrefValueMap.put(MessageUtil.getMessage("common.sequence"), 77);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.code"), 87);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.name"), 172);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.contract"), 159);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.phone"), 123);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.email"), 159);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.address"), 262);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.registerdate"), 119);
         widthPrefValueMap.put(MessageUtil.getMessage("customer.lastcontractdate"), 119);
        MainFrameAction mainFrameAction = new MainFrameAction();
        mainFrameAction.setPanelTableWidth(table, widthPrefValueMap);
    }
    
    public void assignCustomerOrderBy(int selectIndex,Customer customer){
        Map<Integer,String> customerOrderKey = new LinkedHashMap<Integer, String>();
        int index = 1;
        customerOrderKey.put(index++, "CUSTOMER_ID");
        customerOrderKey.put(index++, "CUSTOMER_NAME");
        customerOrderKey.put(index++, "CUSTOMER_CONTRACT");
        customerOrderKey.put(index++, "CUSTOMER_PHONE");
        customerOrderKey.put(index++, "CUSTOMER_EMAIL");
        customerOrderKey.put(index++, "CUSTOMER_ADDRESS");
        customerOrderKey.put(index++, "REGISTER_DATE");
        customerOrderKey.put(index++, "LAST_CONTRACT_DATE");
        customer.setOrderBy(customerOrderKey.get(selectIndex));
    }
    
    public void openAddform(JButton button,JInternalFrame frame, CustomerInFoListPanel panel){
        frame.setVisible(true);
    }
}