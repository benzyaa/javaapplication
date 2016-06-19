package com.freebasicacc.action;

import com.freebasicacc.dao.CustomerDAO;
import com.freebasicacc.dao.CustomerDAOImpl;
import com.freebasicacc.model.Customer;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.ui.CustomerInFoListDialogPanel;
import com.freebasicacc.ui.InvoiceInfoListPanel;
import com.freebasicacc.ui.QuotationInfoListPanel;
import com.freebasicacc.ui.QuotationInfoPanel;
import com.freebasicacc.ui.InvoiceInfoPanel;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.DialogUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerDialogAction {
     private static transient Logger logger = LogManager.getLogger(CustomerDialogAction.class);
    
    
    public void addToOpenerTable(CustomerInFoListDialogPanel panel,JPanel openerPanel){
         JTable table = panel.getCustomerInfoListTable();
        int selectedIndex = table.getSelectedRow();
       if(selectedIndex <0) {
               DialogUtil.displaySelectAtleastOneItemDialog();
            return;
       }
        String customerId = String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
        String customerName = String.valueOf(table.getModel().getValueAt(selectedIndex, 2));
        String customerContract = String.valueOf(table.getModel().getValueAt(selectedIndex, 3));
        String customerPhone = String.valueOf(table.getModel().getValueAt(selectedIndex, 4));
        String customerAddress = String.valueOf(table.getModel().getValueAt(selectedIndex, 6));
        if(openerPanel instanceof QuotationInfoPanel){
            QuotationInfoPanel targetPanel = (QuotationInfoPanel)openerPanel;
            targetPanel.getCustomerContractTextField().setText(customerContract);
            targetPanel.getCustomerNameTextField().setText(customerName);
            targetPanel.getPhoneTextField().setText(customerPhone);
            targetPanel.getCustomerIdHiddenValueLabel().setText(customerId);
        }else if(openerPanel instanceof QuotationInfoListPanel){
            QuotationInfoListPanel targetPanel = (QuotationInfoListPanel)openerPanel;
            targetPanel.getCompanyIdValueLabel().setText(customerId);
            targetPanel.getCompanyTextField().setText(customerName);
        }else if(openerPanel instanceof InvoiceInfoPanel){
            InvoiceInfoPanel targetPanel = (InvoiceInfoPanel)openerPanel;
            targetPanel.getCustomerIdTextField().setText(customerId);
            targetPanel.getCustomerNameTextField().setText(customerName);
            targetPanel.getAddressTextArea().setText(customerAddress);
        }else if(openerPanel instanceof InvoiceInfoListPanel){
            InvoiceInfoListPanel targetPanel = (InvoiceInfoListPanel)openerPanel;
            //targetPanel.getCompanyIdValueLabel().setText(customerId);
            targetPanel.getCustomerIdHiddenValueLabel().setText(customerId);
            targetPanel.getCompanyTextField().setText(customerName);
        }
    }
    
    public void searchCustomer(CustomerInFoListDialogPanel panel,boolean isClearSearch){
       Customer customer = new Customer();
       try{
               int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
               int rowPerPage = Integer.parseInt(String.valueOf(panel.getRowPerpageCombobox().getSelectedItem()));
               if(!isClearSearch){
                    customer.setCustomerId(panel.getCustomerIdSearchTextField().getText());
                    customer.setCustomerName(panel.getCustomerNameSearchTextField().getText());
                    customer.setCustomerContract(panel.getCustomerContractSearchTextField().getText());
                    customer.setCustomerPhone(panel.getCustomerPhoneSearchTextField().getText());
                    customer.setCustomerFax(panel.getCustomerFaxSearchTextField().getText());
                    customer.setCustomerEmail(panel.getCustomerEmailSearchTextField().getText());
                    boolean isDesc = "DESC".equals(panel.getRadioSelectName());
                    customer.setIsDesc(isDesc);  
                    OrderBy selectObject = (OrderBy) panel.getSortingByCombobox().getSelectedItem();
                    customer.setOrderBy(selectObject.getId());
               }else{
                  customer.setOrderBy("CUSTOMER_ID");
                  customer.setIsDesc(true);   
               }
               customer.setIsActive(1);
               CustomerDAO customerDAO = new CustomerDAOImpl();
               List<Customer> customerResultList = customerDAO.getCustomerByCriteria(customer);
               int resultCount = customerResultList.size();
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
    
    public void setCustomerPanelTableWithNextPrevious(CustomerInFoListDialogPanel panel){
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
    
    private void setCustomerPanelTable(CustomerInFoListDialogPanel panel, Collection<Customer> customerSet){
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
        setCustomerPanelTableWidth(customerTable);
        
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
    
    public void openAddform(JButton button,JInternalFrame frame, CustomerInFoListDialogPanel panel){
        frame.setVisible(true);
    }
}