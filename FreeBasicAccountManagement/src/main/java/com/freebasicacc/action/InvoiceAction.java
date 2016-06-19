/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.action;

import com.freebasicacc.dao.InvoiceDAO;
import com.freebasicacc.dao.InvoiceDAOImpl;
import com.freebasicacc.model.Customer;
import com.freebasicacc.model.InvoiceDetail;
import com.freebasicacc.model.InvoiceHead;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.OrderBy;;

import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.CollectionUtil;
import com.freebasicacc.util.AppConstants;

import javax.swing.JInternalFrame;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;
import com.freebasicacc.services.ReportService;
import com.freebasicacc.ui.InvoiceInfoPanelView;
import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.InvoiceInfoListPanel;
import com.freebasicacc.ui.InvoiceInfoPanel;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.renderer.InvoiceTableCellRenderer;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import com.freebasicacc.util.CommonUtil;
import com.freebasicacc.util.CollectionUtil;
import com.freebasicacc.util.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JInternalFrame;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;
import com.freebasicacc.services.ReportService;
import com.freebasicacc.ui.InvoiceInfoPanelView;
import com.freebasicacc.model.Unit;
import com.freebasicacc.ui.InvoiceInfoListPanel;
import com.freebasicacc.ui.InvoiceInfoPanel;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.renderer.InvoiceTableCellRenderer;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.DialogUtil;
import com.freebasicacc.util.MessageUtil;
import com.freebasicacc.util.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author benzyaa
 */
public class InvoiceAction {
     private static transient Logger logger = LogManager.getLogger(InvoiceAction.class);
    public void saveInvoiceAction(InvoiceInfoPanel panel){
        try{
            MainFrameAction action = new MainFrameAction();
            if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
            InvoiceHead invoiceHead = new InvoiceHead();
            String customerId = panel.getCustomerIdTextField().getText();
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            invoiceHead.setCustomer(customer);
            invoiceHead.setPoNumber(panel.getPoNumberTextField().getText());
            String paymentDueDateStr = panel.getPaymentDueDateTextField().getText();
            if(StringUtil.isContainText(paymentDueDateStr))invoiceHead.setPaymentDueDate(DateUtil.convertDateStrToDateUtil(paymentDueDateStr));
            String invoiceDueDateStr = panel.getInvoiceDueDateTextField().getText();
            if(StringUtil.isContainText(invoiceDueDateStr))invoiceHead.setInvoiceDueDate(DateUtil.convertDateStrToDateUtil(invoiceDueDateStr));
            String totalValueStr = panel.getTotalValueTextField().getText();
            String vatValueStr = panel.getVatTextField().getText();
            String netValueStr = panel.getNetValueTextField().getText();
            if(StringUtil.isContainText(totalValueStr)) invoiceHead.setTotalAmount(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(totalValueStr).doubleValue()));
            if(StringUtil.isContainText(vatValueStr))invoiceHead.setVat(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(vatValueStr).doubleValue()));
            if(StringUtil.isContainText(netValueStr))invoiceHead.setNetAmount(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(netValueStr).doubleValue()));
            Date processDate = new Date();
            invoiceHead.setCreateBy(CommonUtil.getComputerName()); 
            invoiceHead.setCreateDate(processDate);
            invoiceHead.setUpdateBy(CommonUtil.getComputerName());
            invoiceHead.setUpdateDate(processDate);
            invoiceHead.setIsActive(1);
            invoiceHead.setDiscount(BigDecimal.ZERO);
            List<InvoiceDetail> detailList = this.getInvoiceDetailFromTable(panel, invoiceHead);
            invoiceHead.setInvoiceDetailSet(detailList);
            this.validateMandatoryForSaveUpdate(invoiceHead);
            InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
            invoiceDAO.insertInvoice(invoiceHead);
            DialogUtil.displayDataSaveSuccessMessage();
            InvoiceInfoListPanel openerPanel = (InvoiceInfoListPanel)panel.getOpenerPanel();
            if(openerPanel != null)this.searchInvoiceAction(openerPanel, true);
            action.closeInternalFrame(panel.getInternalFrame());
         }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    private List<InvoiceDetail> getInvoiceDetailFromTable(InvoiceInfoPanel panel,InvoiceHead invoiceHead){
        List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();
        DefaultTableModel tableModel = (DefaultTableModel) panel.getInvoiceDetailTable().getModel();
        int rowCount = tableModel.getRowCount();
        for(int i=0;i<rowCount;i++){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setInvoiceHeadId(invoiceHead.getInvoiceHeadId());
            invoiceDetail.setInvoiceDetailId(String.valueOf(i+1));
            Material material = new Material();
            material.setMaterialId(String.valueOf(tableModel.getValueAt(i, 1)));
            invoiceDetail.setMaterial(material);
            invoiceDetail.setItemNo(Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 0))));
            invoiceDetail.setQuantity(Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 3))));
            invoiceDetail.setUnit((Unit)tableModel.getValueAt(i, 4));
            String pricePerUnitStr = String.valueOf(tableModel.getValueAt(i, 5));
            invoiceDetail.setPricePerUnit(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(pricePerUnitStr).doubleValue()));
            String totalPriceStr = String.valueOf(tableModel.getValueAt(i, 6));
            invoiceDetail.setTotalValue(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(totalPriceStr).doubleValue()));
            invoiceDetail.setCreateBy(invoiceHead.getCreateBy()); 
            invoiceDetail.setCreateDate(invoiceHead.getCreateDate());
            invoiceDetail.setUpdateBy(invoiceHead.getUpdateBy());
            invoiceDetail.setUpdateDate(invoiceHead.getUpdateDate());
            invoiceDetail.setIsActive(invoiceHead.getIsActive()); 
            invoiceDetailList.add(invoiceDetail);
            
        }
        return invoiceDetailList;
    }
    
    public void removeInvoiceDetailRow(InvoiceInfoPanel panel){
        MainFrameAction action = new MainFrameAction();
        action.removeTableRow(panel.getInvoiceDetailTable());
    }
    
    public void removeInvoiceDetailAllRow(InvoiceInfoPanel panel){
        MainFrameAction action = new MainFrameAction();
        action.removeAllTableRow(panel.getInvoiceDetailTable());
    }
    
    public void addInvoiceDetailRow(InvoiceInfoPanel panel, Material material){
        JTable invoiceDetailTable = panel.getInvoiceDetailTable();
        invoiceDetailTable.setDefaultRenderer(Object.class, new InvoiceTableCellRenderer());
        DefaultTableModel defaultTableModel = (DefaultTableModel) invoiceDetailTable.getModel();
        Integer rowCount = invoiceDetailTable.getRowCount();
        defaultTableModel.addRow(new Object[]{++rowCount,material.getMaterialId(),material.getMaterialName(),"0",material.getUnit(),StringUtil.formatThaiCurrency(material.getMaterialPrice()),"0.00"});
    }
    
    public void addInvoiceDetailViewRow(InvoiceInfoPanelView panel, InvoiceDetail detail){
        JTable invoiceDetailTable = panel.getInvoiceDetailTable();
        invoiceDetailTable.setDefaultRenderer(Object.class, new InvoiceTableCellRenderer());
        DefaultTableModel defaultTableModel = (DefaultTableModel) invoiceDetailTable.getModel();
        Material material = detail.getMaterial();
        defaultTableModel.addRow(new Object[]{detail.getItemNo(),material.getMaterialId(),material.getMaterialName(),detail.getQuantity(),detail.getUnit(),StringUtil.formatThaiCurrency(detail.getPricePerUnit()),StringUtil.formatThaiCurrency(detail.getTotalValue())});
    }
    
    public void calculateTotalDetail(InvoiceInfoPanel panel){
        JTable invoiceDetailTable = panel.getInvoiceDetailTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel)invoiceDetailTable.getModel();
        int rowCount = defaultTableModel.getRowCount();
        double sumTotalValueRow = 0;
        for(int i=0;i<rowCount;i++){
            String totalValueRowStr = String.valueOf(defaultTableModel.getValueAt(i, 6));
            System.out.println("totalValueRowStr=>"+totalValueRowStr);
            sumTotalValueRow += StringUtil.formatThaiCurrencyStrToNumber(totalValueRowStr).doubleValue(); 
        }
        BigDecimal totalValue = new BigDecimal(sumTotalValueRow);
        BigDecimal vatValue = new BigDecimal("0.07");
        vatValue = totalValue.multiply(vatValue);
        BigDecimal netValue = totalValue.add(vatValue);
        System.out.println("totalValue=>"+totalValue);
        panel.getTotalValueTextField().setText(StringUtil.formatThaiCurrency(totalValue.doubleValue()));
        panel.getVatTextField().setText(StringUtil.formatThaiCurrency(vatValue.doubleValue()));
        panel.getNetValueTextField().setText(StringUtil.formatThaiCurrency(netValue.doubleValue()));
    }
    
    public void searchInvoiceAction(InvoiceInfoListPanel panel,boolean isClearSearch){
        InvoiceHead invoiceHead = new InvoiceHead();
        try{
            if(!isClearSearch){
                invoiceHead.setInvoiceHeadId(panel.getInvoiceIdTextField().getText());
                invoiceHead.setPoNumber(panel.getPoNumberTextField().getText());
                String customerId = panel.getCustomerIdHiddenValueLabel().getText();
                System.out.println("customerId=>"+customerId);
                if(StringUtil.isContainText(customerId)){
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);
                    invoiceHead.setCustomer(customer);
                }
                String createDateStr = panel.getInvoiceDateTextField().getText();
                if(StringUtil.isContainText(createDateStr))invoiceHead.setCreateDate(DateUtil.convertDateStrToDateUtil(createDateStr,"dd/MM/yyyy", new Locale("th","TH")));
                String createDateToStr = panel.getInvoiceDateToTextField().getText();
                if(StringUtil.isContainText(createDateToStr))invoiceHead.setCreateDateTo(DateUtil.convertDateStrToDateUtil(createDateToStr,"dd/MM/yyyy", new Locale("th","TH")));
                String paymentDateStr = panel.getPaymentDueDateTextField().getText();
                if(StringUtil.isContainText(paymentDateStr))invoiceHead.setPaymentDueDate(DateUtil.convertDateStrToDateUtil(paymentDateStr,"dd/MM/yyyy", new Locale("th","TH")));
                String paymentDateToStr = panel.getPaymentDueDateToTextField().getText();
                if(StringUtil.isContainText(paymentDateToStr))invoiceHead.setPaymentDueDateTo(DateUtil.convertDateStrToDateUtil(paymentDateToStr,"dd/MM/yyyy", new Locale("th","TH")));
                String invoiceDueDateStr = panel.getInvoiceDueDateTextField().getText();
                if(StringUtil.isContainText(invoiceDueDateStr))invoiceHead.setInvoiceDueDate(DateUtil.convertDateStrToDateUtil(invoiceDueDateStr,"dd/MM/yyyy", new Locale("th","TH")));
                String invoiceDueDateToStr = panel.getInvoiceDueDateToTextField().getText();
                if(StringUtil.isContainText(invoiceDueDateToStr))invoiceHead.setInvoiceDueDateTo(DateUtil.convertDateStrToDateUtil(invoiceDueDateToStr,"dd/MM/yyyy", new Locale("th","TH")));
                invoiceHead.setIsDesc("DESC".equals(panel.getRadioSelectName()));
                OrderBy quotationOrderBy = (OrderBy)panel.getSortingByCombobox().getSelectedItem();
                invoiceHead.setOrderBy(quotationOrderBy.getId());
            }else{
                invoiceHead.setOrderBy("INVOICE_HEAD_ID");
                invoiceHead.setIsDesc(true);
            }
            invoiceHead.setIsActive(1);
            this.validateInvoiceInfoInput(invoiceHead);
            InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
            List<InvoiceHead> invoiceList = invoiceDAO.getInvoiceByCriteria(invoiceHead);
            panel.setDataList(invoiceList);
            this.setInvoicePanelTableWithNextPrevious(panel);
        }catch(Exception ex){
            DialogUtil.displayErrorMessage(ex);
        }
    }
    
    private void validateInvoiceInfoInput(InvoiceHead invoiceHead) throws Exception{
        /** Start Validate date ***/
        String illegalCriteriaMessage = MessageUtil.getMessage("errormessage.wrongcondition");
        Date invoiceDateFrom = invoiceHead.getCreateDate();
        Date invoiceDateTo = invoiceHead.getCreateDateTo();
        Date paymentDueDateFrom = invoiceHead.getPaymentDueDate();
        Date paymentDueDateTo = invoiceHead.getPaymentDueDateTo();
        Date invoiceDueDateFrom = invoiceHead.getInvoiceDueDate();
        Date invoicdDueDateTo = invoiceHead.getInvoiceDueDateTo();
        if((invoiceDateFrom ==null && invoiceDateTo !=null)||(invoiceDateFrom !=null && invoiceDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.date")));
        }
        if(invoiceDateFrom !=null && invoiceDateTo !=null){
            long registerDateDiff = DateUtil.calculateDateDiff(invoiceDateTo, invoiceDateFrom);
            if(registerDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage,  MessageUtil.getMessage("quotation.date")));
        }
        if((paymentDueDateFrom ==null && paymentDueDateTo !=null)||(paymentDueDateFrom !=null && paymentDueDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage,  MessageUtil.getMessage("quotation.paymentduedate")));
        }
        if(paymentDueDateFrom !=null && paymentDueDateTo !=null){
            long lastContractDateDiff = DateUtil.calculateDateDiff(paymentDueDateTo, paymentDueDateFrom);
            if(lastContractDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.paymentduedate")));
        }
        if((invoiceDueDateFrom ==null && invoicdDueDateTo !=null)||(invoiceDueDateFrom !=null && invoicdDueDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.duedate")));
        }
        if(invoiceDueDateFrom !=null && invoicdDueDateTo !=null){
            long lastContractDateDiff = DateUtil.calculateDateDiff(invoicdDueDateTo, invoiceDueDateFrom);
            if(lastContractDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.duedate")));
        }
        /***  End Validate date ***/
    }
    
    public void setInvoicePanelTableWithNextPrevious(InvoiceInfoListPanel panel){
        List<InvoiceHead> invoiceList = panel.getDataList();
        int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
        String rowPerpageTable = String.valueOf(panel.getRowPerpageCombobox().getSelectedItem());
        int rowPerPage = StringUtil.isContainText(rowPerpageTable) ? Integer.parseInt(rowPerpageTable) : 10;
        int subStartPosition = (pageNo-1)*rowPerPage;
        int subEndPosition = ((pageNo-1)+1)*rowPerPage;
        int resultCount = invoiceList.size();
        subEndPosition = subEndPosition > resultCount ? resultCount : subEndPosition;
        int totalPage = resultCount/rowPerPage;
        totalPage = totalPage <=0 ? 1 : totalPage+1;
        panel.getPageTotalLabel().setText(String.valueOf(totalPage));
        invoiceList = invoiceList.subList(subStartPosition, subEndPosition);
       setInvoiceListPanelTable(panel,invoiceList);
    }
    
    private void setInvoiceListPanelTable(InvoiceInfoListPanel panel, Collection<InvoiceHead> invoiceHeadCollect){
        JTable invoiceTable = panel.getQuotationListTable();
        Enumeration<TableColumn> enu = invoiceTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
        TableModel tableModel = new DefaultTableModel(tableIdentiferList.toArray(),invoiceHeadCollect.size());
        invoiceTable.setModel(tableModel);
        ListSelectionModel selectionModel = invoiceTable.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        int rowIndex = 0;
        for(InvoiceHead invoice:invoiceHeadCollect){
            int colIndex = 0;
            invoiceTable.setValueAt(rowIndex+1, rowIndex, colIndex++);
            invoiceTable.setValueAt(invoice.getInvoiceHeadId(), rowIndex, colIndex++);
            invoiceTable.setValueAt(invoice.getPoNumber(), rowIndex, colIndex++);
            String createDateStr =(invoice.getCreateDate() !=null)?DateUtil.convertDateUtilToDateStr(invoice.getCreateDate()) : "";
            invoiceTable.setValueAt(createDateStr, rowIndex, colIndex++);
            invoiceTable.setValueAt(invoice.getCustomer().getCustomerName(), rowIndex, colIndex++);
            String paymentDueDateStr =(invoice.getPaymentDueDate() !=null)?DateUtil.convertDateUtilToDateStr(invoice.getPaymentDueDate()) : "";
            invoiceTable.setValueAt(paymentDueDateStr, rowIndex, colIndex++);
            String invoiceDueDateStr =(invoice.getInvoiceDueDate() !=null)?DateUtil.convertDateUtilToDateStr(invoice.getInvoiceDueDate()) : "";
            invoiceTable.setValueAt(invoiceDueDateStr, rowIndex, colIndex++);
            invoiceTable.setValueAt(StringUtil.formatThaiCurrency(invoice.getTotalAmount()), rowIndex, colIndex++);
            invoiceTable.setValueAt(StringUtil.formatThaiCurrency(invoice.getVat()), rowIndex, colIndex++);
            invoiceTable.setValueAt(StringUtil.formatThaiCurrency(invoice.getNetAmount()), rowIndex, colIndex++);
            rowIndex++;
        }
        setInvoicePanelTableWidth(invoiceTable);
    }
    
    private void setInvoicePanelTableWidth(JTable table){
         Map<String,Integer> widthPrefValueMap = new LinkedHashMap<String,Integer>(10);
         widthPrefValueMap.put(MessageUtil.getMessage("common.sequence"), 82);
         widthPrefValueMap.put(MessageUtil.getMessage("common.number"), 82);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.ponumber"), 99);
         widthPrefValueMap.put(MessageUtil.getMessage("common.date"), 96);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.company"), 180);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.paymentduedate"), 96);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.duedate"), 96);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.totalvalue"), 80);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.tax"), 80);
         widthPrefValueMap.put(MessageUtil.getMessage("invoice.netvalue"), 80);
         MainFrameAction mainFrameAction = new MainFrameAction();
         mainFrameAction.setPanelTableWidth(table, widthPrefValueMap);
    }
    
    public void openInvoiceViewFormAction(InvoiceInfoListPanel panel){
        try{
            JTable table = panel.getQuotationListTable();
            int selectedIndex = table.getSelectedRow();
             if(selectedIndex <0) {
                    DialogUtil.displaySelectAtleastOneItemDialog();
                return;
           }
            String invoiceId = String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
            InvoiceHead invoiceHead = new InvoiceHead();
            invoiceHead.setInvoiceHeadId(invoiceId);
            InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
            invoiceHead = invoiceDAO.getInvoiceByCriteria(invoiceHead).get(0);
            MainFrameAction mainFrameAction = new MainFrameAction();
            Map<String,Object> descriptionMap = new HashMap<String,Object>();
            descriptionMap.put("INVOICE_OBJECT", invoiceHead);
            JMenu menu = new JMenu();
            menu.setName("INVOICE_VIEW_MENU_ITEM");
            mainFrameAction.openInternalFormByType((MainFrame)panel.getMainFrame(), menu, panel, descriptionMap);
         }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
      private void validateMandatoryForSaveUpdate(InvoiceHead invoiceHead) throws Exception{
          if(!CollectionUtil.isHasElement(invoiceHead.getInvoiceDetailSet()))throw new Exception(MessageUtil.getMessage("notification.select.atleastoneproduct"));
          //if(!(invoiceHead.getCustomer() !=null && StringUtil.isContainText(invoiceHead.getCustomer().getCustomerId()))&&
              if(!(StringUtil.isContainText(invoiceHead.getCustomer().getCustomerId())&& invoiceHead.getInvoiceDueDate() !=null && invoiceHead.getPaymentDueDate() !=null))throw new Exception(MessageUtil.getMessage("errormessage.requireddata"));
    }
    
    public void exportInvoiceReport(InvoiceHead invoiceHead, boolean isBlankFile){
        ReportService reportService = null;
        try{
            reportService = new ReportService();
            reportService.exportReport("INVOICE_REPORT","invoice_"+invoiceHead.getInvoiceHeadId(), invoiceHead, ReportService.PDF_TYPE, true,isBlankFile);
        }catch(Exception ex){
          logger.info("ERROR",ex);
          DialogUtil.displayErrorMessage(ex);
        }
    }
}