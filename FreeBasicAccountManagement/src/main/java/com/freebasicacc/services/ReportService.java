/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.services;

import com.freebasicacc.model.InvoiceHead;
import com.freebasicacc.model.QuotationHead;
import com.freebasicacc.util.DateUtil;
import com.freebasicacc.util.FileUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author benzyaa
 */
public class ReportService extends DatabaseService{
    private static transient Logger logger = LogManager.getLogger(ReportService.class);
    public static final String PDF_TYPE = "PDF";  
    public void exportReport(String reportName,String reportFileName,Object paramObject,String exportType,boolean isOpenFile,boolean isBlankFile) throws Exception{
        deleteUnusedTempFile();
        Map paramMap = new LinkedHashMap();
        String reportTemplateFileName = this.getJasperTemplateRespositoryPart();
        if("QUOTATION_REPORT".equals(reportName)){
            QuotationHead quotationHead = (QuotationHead)paramObject;
            paramMap.put("QUOTATION_HEAD_ID", quotationHead.getQuotationHeadId());
            String createDateStr = (quotationHead.getCreateDate() != null)?DateUtil.convertDateUtilToDateStr(quotationHead.getCreateDate()):"";
            String leadDateStr = (quotationHead.getLeadDate() != null)?DateUtil.convertDateUtilToDateStr(quotationHead.getLeadDate()):"";
            paramMap.put("QUOTATION_DATE_TEXT",createDateStr);
            paramMap.put("LEAD_TIME_TEXT", leadDateStr);
            reportTemplateFileName += "/quotationReport.jasper";
        }else if("INVOICE_REPORT".equals(reportName)){
            InvoiceHead invoiceHead = (InvoiceHead)paramObject;
            paramMap.put("INVOICE_HEAD_ID", invoiceHead.getInvoiceHeadId());
            String createDateStr = (invoiceHead.getCreateDate() != null)?DateUtil.convertDateUtilToDateStr(invoiceHead.getCreateDate()):"";
            String dueDateStr = (invoiceHead.getInvoiceDueDate() != null)?DateUtil.convertDateUtilToDateStr(invoiceHead.getInvoiceDueDate()):"";
            String paymentDateStr = (invoiceHead.getPaymentDueDate() != null)?DateUtil.convertDateUtilToDateStr(invoiceHead.getPaymentDueDate()):"";
            paramMap.put("INVOICE_CREATE_DATE",createDateStr);
            paramMap.put("INVOICE_DUEDATE_DATE",dueDateStr);
            paramMap.put("INVOICE_PAYMENT_DUE_DATE",paymentDateStr);
            reportTemplateFileName = (!isBlankFile)?reportTemplateFileName+"/invoiceReport.jasper":reportTemplateFileName+"/invoiceReport _whitepage.jasper";
        }
        if("PDF".equals(exportType)){
            reportFileName =this.getPDFTempRespositoryPart()+"/"+reportFileName+".pdf";
            this.createPDFReport(reportTemplateFileName, reportFileName, paramMap, isOpenFile);
        }
    }
    private void createPDFReport(String reportTemplateFileName,String reportFileName,Map paramMap,boolean isOpenFile) throws Exception{
            Connection conn = null;
            try{
                conn = this.getConnection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(reportTemplateFileName, paramMap, conn);
                OutputStream output = new FileOutputStream(new File(reportFileName)); 
                JasperExportManager.exportReportToPdfStream(jasperPrint, output); 
                output.flush();
                output.close();
                if(!isOpenFile)return;
                this.openReportFile(reportFileName);
            }catch(Exception ex){
                logger.info("Error",ex);
                throw new Exception("Cannot create file for printing.",ex);
            }
            finally{
                if(conn!=null)conn.close();
                conn = null;
            }
    }
    private void openReportFile(String fileName) throws Exception{
       Desktop.getDesktop().open(new File(fileName));
    }
    
    private void deleteUnusedTempFile(){
        File folder = null;
        File[] tempFiles = null;
        try{
            folder = new File(this.getPDFTempRespositoryPart());
            tempFiles = folder.listFiles();
            for(File tempFile : tempFiles){
                logger.info("DELETE TEMP FILE =>"+tempFile.getName());
                tempFile.delete();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private String getPDFTempRespositoryPart(){
        return FileUtil.getCurrentJarPath()+"reportsTemp";
    }
    
    private String getJasperTemplateRespositoryPart(){
        return FileUtil.getCurrentJarPath()+"reports";
    }
}