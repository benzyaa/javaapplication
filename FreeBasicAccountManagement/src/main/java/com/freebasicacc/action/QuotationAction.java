/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.action;

import com.freebasicacc.dao.QuotationDAO;
import com.freebasicacc.dao.QuotationDAOImpl;
import com.freebasicacc.model.Customer;
import com.freebasicacc.model.Material;
import com.freebasicacc.model.OrderBy;
import com.freebasicacc.model.QuotationDetail;
import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.model.Unit;
import com.freebasicacc.services.ReportService;
import com.freebasicacc.ui.MainFrame;
import com.freebasicacc.ui.QuotationInfoListPanel;
import com.freebasicacc.ui.QuotationInfoPanel;
import com.freebasicacc.ui.action.MainFrameAction;
import com.freebasicacc.ui.renderer.QuotationTableCellRenderer;
import com.freebasicacc.util.AppConstants;
import com.freebasicacc.util.CollectionUtil;
import com.freebasicacc.util.CommonUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
public class QuotationAction {
    private static transient Logger logger = LogManager.getLogger(QuotationAction.class);
    public void saveQuotationAction(QuotationInfoPanel panel){ 
        try{
            MainFrameAction action = new MainFrameAction();
            if(!action.popupConfirmDialog(AppConstants.SAVE_CONFIRM_MESSAGE, AppConstants.CONFIRM_SAVE_DIALOG_TITLE))return;
            QuotationHead quotationHead = new QuotationHead();
            quotationHead.setQuotationTitle(panel.getQuotationTitleTextField().getText());
            Customer customer = new Customer();
            customer.setCustomerId(panel.getCustomerIdHiddenValueLabel().getText());
            quotationHead.setCustomer(customer);
            quotationHead.setQuotationDate(new Date());
            quotationHead.setDeliveryPlace(panel.getDeliveryPlaceTextField().getText());
            quotationHead.setPaymentTerm(panel.getPaymentTermTextField().getText());
            String leadDateStr = panel.getLeadTimeTextField().getText();
            Date leadDate = StringUtil.isContainText(leadDateStr) ? DateUtil.convertDateStrToDateUtil(leadDateStr):null;
            quotationHead.setLeadDate(leadDate);
            String totalValueStr = panel.getTotalValueTextField().getText();
            String vatValueStr = panel.getVatTextField().getText();
            String netValueStr = panel.getNetValueTextField().getText();
            if(StringUtil.isContainText(totalValueStr))quotationHead.setTotalValue(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(totalValueStr).doubleValue()));
            if(StringUtil.isContainText(vatValueStr))quotationHead.setVat(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(vatValueStr).doubleValue()));
            if(StringUtil.isContainText(netValueStr))quotationHead.setNetValue(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(netValueStr).doubleValue()));
            Date processDate = new Date();
            quotationHead.setCreateBy(CommonUtil.getComputerName()); 
            quotationHead.setCreateDate(processDate);
            quotationHead.setUpdateBy(CommonUtil.getComputerName());
            quotationHead.setUpdateDate(processDate);
            quotationHead.setIsActive(1);
            List<QuotationDetail> detailList = this.getQuotationDetailFromTable(panel, quotationHead);
            quotationHead.setQuotationDetailSet(detailList);
            this.validateMandatoryForSaveUpdate(quotationHead);
            QuotationDAO quotationDAO = new QuotationDAOImpl();
            quotationDAO.insertQuotation(quotationHead);
            DialogUtil.displayDataSaveSuccessMessage();
            QuotationInfoListPanel openerPanel = (QuotationInfoListPanel)panel.getOpenerPanel();
            if(openerPanel != null)this.searchQuotationAction(openerPanel, true);
            action.closeInternalFrame(panel.getInternalFrame());
        }catch(Exception ex){
           logger.info("ERROR",ex);
           DialogUtil.displayErrorMessage(ex);
       }
    }
    
    private List<QuotationDetail> getQuotationDetailFromTable(QuotationInfoPanel panel,QuotationHead quotationHead){
        List<QuotationDetail> quotationDetailList = new ArrayList<QuotationDetail>();
        DefaultTableModel tableModel = (DefaultTableModel) panel.getQuotationDetailTable().getModel();
        int rowCount = tableModel.getRowCount();
        for(int i=0;i<rowCount;i++){
            QuotationDetail quotationDetail = new QuotationDetail();
            quotationDetail.setQuotationHeadId(quotationHead.getQuotationHeadId());
            quotationDetail.setQuotationDetailId(String.valueOf(tableModel.getValueAt(i, 0)));
            quotationDetail.setItemNo(Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 0))));
            quotationDetail.setMaterial((Material)tableModel.getValueAt(i, 1));
            quotationDetail.setQuantity(Integer.parseInt(String.valueOf(tableModel.getValueAt(i, 2))));
            quotationDetail.setUnit((Unit)tableModel.getValueAt(i, 3));
            String pricePetUnitStr = String.valueOf(tableModel.getValueAt(i, 4));
            String totalPriceStr = String.valueOf(tableModel.getValueAt(i, 5));
            if(StringUtil.isContainText(pricePetUnitStr)) quotationDetail.setPricePerUnit(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(pricePetUnitStr).doubleValue())); 
            if(StringUtil.isContainText(totalPriceStr))quotationDetail.setTotalPrice(new BigDecimal(StringUtil.formatThaiCurrencyStrToNumber(totalPriceStr).doubleValue()));
            quotationDetail.setCreateBy(quotationHead.getCreateBy()); 
            quotationDetail.setCreateDate(quotationHead.getCreateDate());
            quotationDetail.setUpdateBy(quotationHead.getUpdateBy());
            quotationDetail.setUpdateDate(quotationHead.getUpdateDate());
            quotationDetail.setIsActive(quotationHead.getIsActive()); 
            quotationDetailList.add(quotationDetail);
        }
        return quotationDetailList;
    }
    
    public void removeQuotationRow(QuotationInfoPanel panel){
        MainFrameAction action = new MainFrameAction();
        action.removeTableRow(panel.getQuotationDetailTable());
    }
    
    public void addQuotationDetailRow(QuotationInfoPanel panel, Material material){
        JTable quotationDetailTable = panel.getQuotationDetailTable();
        quotationDetailTable.setDefaultRenderer(Object.class, new QuotationTableCellRenderer());
        DefaultTableModel defaultTableModel = (DefaultTableModel) quotationDetailTable.getModel();
        Integer rowCount = quotationDetailTable.getRowCount();
        String materialPriceCurrency = StringUtil.formatThaiCurrency(material.getMaterialPrice());
        defaultTableModel.addRow(new Object[]{++rowCount,material,"0",material.getUnit(),materialPriceCurrency,"0.00"});
    }
    
    public void calculateTotalDetail(QuotationInfoPanel panel){
        JTable quotationDetailTable = panel.getQuotationDetailTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel)quotationDetailTable.getModel();
        int rowCount = defaultTableModel.getRowCount();
        double sumTotalValueRow = 0;
        for(int i=0;i<rowCount;i++){
            String totalValueRowStr = String.valueOf(defaultTableModel.getValueAt(i, 5));
            System.out.println("totalValueRowStr=>"+totalValueRowStr);
            //sumTotalValueRow += Double.parseDouble(totalValueRowStr);
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
    
    
    public void searchQuotationAction(QuotationInfoListPanel panel,boolean isClearSearch){
        try{
            QuotationHead quotation = new QuotationHead();
            if(!isClearSearch){
                 quotation.setQuotationHeadId(panel.getQuotationIdTextField().getText());
                 quotation.setQuotationTitle(panel.getQuotationTitleTextField().getText());
                 Customer customer = new Customer();
                 customer.setCustomerId(panel.getCompanyIdValueLabel().getText());
                 quotation.setCustomer(customer);
                 String quotationDateStr = panel.getQuotationDateTextField().getText();
                 if(StringUtil.isContainText(quotationDateStr))quotation.setQuotationDate(DateUtil.convertDateStrToDateUtil(quotationDateStr,"dd/MM/yyyy", new Locale("th","TH")));
                 String quotationDateToStr = panel.getQuotationDateToTextField().getText();
                 if(StringUtil.isContainText(quotationDateToStr))quotation.setQuotationDateTo(DateUtil.convertDateStrToDateUtil(quotationDateToStr,"dd/MM/yyyy", new Locale("th","TH")));
                 quotation.setDeliveryPlace(panel.getDeliveryPlaceTextField().getText());
                 String leadDateStr = panel.getLeadDateTextField().getText();
                 if(StringUtil.isContainText(leadDateStr))quotation.setLeadDate(DateUtil.convertDateStrToDateUtil(leadDateStr,"dd/MM/yyyy", new Locale("th","TH")));
                 String leadDateTo = panel.getLeadDateToTextField().getText();
                 if(StringUtil.isContainText(leadDateTo))quotation.setLeadDateTo(DateUtil.convertDateStrToDateUtil(leadDateTo,"dd/MM/yyyy", new Locale("th","TH")));
                 quotation.setPaymentTerm(panel.getPaymentTermTextField().getText());
                 quotation.setIsDesc("DESC".equals(panel.getRadioSelectName()));
                 OrderBy quotationOrderBy = (OrderBy)panel.getSortingByCombobox().getSelectedItem();
                 quotation.setOrderBy(quotationOrderBy.getId());
            }else{
                quotation.setOrderBy("QUOTATION_HEAD_ID");
                quotation.setIsDesc(true);
            }
            quotation.setIsActive(1);
            this.validateQuotationInfoInput(quotation);
            QuotationDAO QuotationDAO = new QuotationDAOImpl();
            List<QuotationHead> quotationList = QuotationDAO.getQuotationByCriteria(quotation);
            panel.setDataList(quotationList);
            this.setQuotationPanelTableWithNextPrevious(panel);
        }catch(Exception ex){
             DialogUtil.displayErrorMessage(ex);
        }
    }
    
    private void validateQuotationInfoInput(QuotationHead quotation) throws Exception{
        /** Start Validate date ***/
        String illegalCriteriaMessage = MessageUtil.getMessage("errormessage.wrongcondition");
        Date quotationDateFrom = quotation.getQuotationDate();
        Date quotationDateTo = quotation.getQuotationDateTo();
        Date leadDateFrom = quotation.getLeadDate();
        Date leadDateTo = quotation.getLeadDateTo();
        if((quotationDateFrom ==null && quotationDateTo !=null)||(quotationDateFrom !=null && quotationDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.date")));
        }
        if(quotationDateFrom !=null && quotationDateTo !=null){
            long registerDateDiff = DateUtil.calculateDateDiff(quotationDateTo, quotationDateFrom);
            if(registerDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.date")));
        }
        if((leadDateFrom ==null && leadDateTo !=null)||(leadDateFrom !=null && leadDateTo ==null) ){
            throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.leaddate")));
        }
        if(leadDateFrom !=null && leadDateTo !=null){
            long lastContractDateDiff = DateUtil.calculateDateDiff(leadDateTo, leadDateFrom);
            if(lastContractDateDiff < 0) throw new IllegalArgumentException(String.format(illegalCriteriaMessage, MessageUtil.getMessage("quotation.leaddate")));
        }
        /***  End Validate date ***/
    }
    
    public void setQuotationPanelTableWithNextPrevious(QuotationInfoListPanel panel){
        List<QuotationHead> quotationList = panel.getDataList();
        int pageNo = Integer.parseInt(String.valueOf(panel.getPageNoTextField().getText()));
        String rowPerpageTable = String.valueOf(panel.getRowPerpageCombobox().getSelectedItem());
        int rowPerPage = StringUtil.isContainText(rowPerpageTable) ? Integer.parseInt(rowPerpageTable) : 10;
        int subStartPosition = (pageNo-1)*rowPerPage;
        int subEndPosition = ((pageNo-1)+1)*rowPerPage;
        int resultCount = quotationList.size();
        subEndPosition = subEndPosition > resultCount ? resultCount : subEndPosition;
        int totalPage = resultCount/rowPerPage;
        totalPage = totalPage <=0 ? 1 : totalPage+1;
        panel.getPageTotalLabel().setText(String.valueOf(totalPage));
        quotationList = quotationList.subList(subStartPosition, subEndPosition);
        setQuotationPanelTable(panel,quotationList);
    }
    
    private void setQuotationPanelTable(QuotationInfoListPanel panel, Collection<QuotationHead> quotationCollect){
        JTable quotationTable = panel.getQuotationListTable();
        Enumeration<TableColumn> enu = quotationTable.getColumnModel().getColumns();
        List<Object> tableIdentiferList = new ArrayList<Object>();
        while(enu.hasMoreElements()){
               TableColumn col = enu.nextElement();
               System.out.println(col.getIdentifier());
               tableIdentiferList.add(col.getIdentifier());
        }
        TableModel tableModel = new DefaultTableModel(tableIdentiferList.toArray(),quotationCollect.size());
        quotationTable.setModel(tableModel);
        ListSelectionModel selectionModel = quotationTable.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        int rowIndex = 0;
        for(QuotationHead quotation:quotationCollect){
            int colIndex = 0;
            quotationTable.setValueAt(rowIndex+1, rowIndex, colIndex++);
            quotationTable.setValueAt(quotation.getQuotationHeadId(), rowIndex, colIndex++);
            quotationTable.setValueAt(quotation.getQuotationTitle(), rowIndex, colIndex++);
            quotationTable.setValueAt(quotation.getCustomer().getCustomerName(), rowIndex, colIndex++);
            String quotationDateStr =(quotation.getQuotationDate() !=null)?DateUtil.convertDateUtilToDateStr(quotation.getQuotationDate()) : "";
            quotationTable.setValueAt(quotationDateStr, rowIndex, colIndex++);
            quotationTable.setValueAt(quotation.getDeliveryPlace(), rowIndex, colIndex++);
            String leadDateStr =(quotation.getLeadDate() !=null)?DateUtil.convertDateUtilToDateStr(quotation.getLeadDate()) : "";
            quotationTable.setValueAt(leadDateStr, rowIndex, colIndex++);
            quotationTable.setValueAt(quotation.getPaymentTerm(), rowIndex, colIndex++);
            quotationTable.setValueAt(StringUtil.formatThaiCurrency(quotation.getTotalValue()), rowIndex, colIndex++);
            quotationTable.setValueAt(StringUtil.formatThaiCurrency(quotation.getVat()), rowIndex, colIndex++);
            quotationTable.setValueAt(StringUtil.formatThaiCurrency(quotation.getNetValue()), rowIndex, colIndex++);
            rowIndex++;
        }
        setQuotationPanelTableWidth(quotationTable);
    }
    
    private void setQuotationPanelTableWidth(JTable table){
         Map<String,Integer> widthPrefValueMap = new LinkedHashMap<String,Integer>(11);
         widthPrefValueMap.put(MessageUtil.getMessage("common.sequence"), 90);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.no"), 124);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.title"), 199);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.customer"), 180);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.date"), 123);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.deliveryplace"), 181);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.leaddate"), 123);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.paymentterm"), 128);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.totalvalue"), 100);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.valueaddedtax"), 100);
         widthPrefValueMap.put(MessageUtil.getMessage("quotation.netvalue"), 100);
         MainFrameAction mainFrameAction = new MainFrameAction();
         mainFrameAction.setPanelTableWidth(table, widthPrefValueMap);
    }
        
    public void openQuotationViewFormAction(QuotationInfoListPanel panel){
        JTable table = panel.getQuotationListTable();
        int selectedIndex = table.getSelectedRow();
         if(selectedIndex <0) {
                DialogUtil.displaySelectAtleastOneItemDialog();
                return;
           }
        String quotationId = String.valueOf(table.getModel().getValueAt(selectedIndex, 1));
        QuotationHead quotationHead = new QuotationHead();
        quotationHead.setQuotationHeadId(quotationId);
        QuotationDAO quotationDAO = new QuotationDAOImpl();
        quotationHead = quotationDAO.getQuotationByCriteria(quotationHead).get(0);
        MainFrameAction mainFrameAction = new MainFrameAction();
        Map<String,Object> descriptionMap = new HashMap<String,Object>();
        descriptionMap.put("QUOTATION_OBJECT", quotationHead);
        JMenu menu = new JMenu();
        menu.setName("QUOTATION_VIEW_MENU_ITEM");
        mainFrameAction.openInternalFormByType((MainFrame)panel.getMainFrame(), menu, panel, descriptionMap);
    }
    
    
      private void validateMandatoryForSaveUpdate(QuotationHead quotationHead) throws Exception{
          if(!CollectionUtil.isHasElement(quotationHead.getQuotationDetailSet()))throw new Exception(MessageUtil.getMessage("notification.select.atleastoneproduct"));
          if(!(StringUtil.isContainText(quotationHead.getQuotationTitle()) &&
          (quotationHead.getCustomer() !=null && StringUtil.isContainText(quotationHead.getCustomer().getCustomerId())) &&
          StringUtil.isContainText(quotationHead.getPaymentTerm()) &&
          StringUtil.isContainText(quotationHead.getDeliveryPlace()) &&
          quotationHead.getLeadDate()!=null))throw new Exception(MessageUtil.getMessage("errormessage.requireddata"));
    }
    
    public void exportQuotationReport(QuotationHead quotationHead){
        ReportService reportService = null;
        try{
            reportService = new ReportService();
            reportService.exportReport("QUOTATION_REPORT","quotation_"+quotationHead.getQuotationHeadId(), quotationHead, ReportService.PDF_TYPE, true,false);
        }catch(Exception ex){
            logger.info("Error", ex);
        }
    }
}