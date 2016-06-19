/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.services;

import com.sun.pdfview.PDFFile;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author benzyaa
 */
public class PDFPrintService {
    public void printFile(String filePath) throws Exception{
        File f = new File(filePath);
        FileInputStream fis = new FileInputStream(f);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        PDFFile pdfFile = new PDFFile(bb); // Create PDF Print Page
        PDFPrintPage pages = new PDFPrintPage(pdfFile);
        // Create Print Job
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
        pjob.setJobName(f.getName());
        Book book = new Book();
        book.append(pages, pf, pdfFile.getNumPages());
        pjob.setPageable(book);
        // Send print job to default printer
        //if(pjob.){
            pjob.print();
        //};//.print();
    }
}
