/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.dao;

import com.freebasicacc.model.InvoiceHead;
import java.util.List;

/**
 *
 * @author benzyaa
 */
public interface InvoiceDAO {
       public void insertInvoice(InvoiceHead invoice) throws Exception;
       public void updateInvoice(InvoiceHead invoice) throws Exception;
       public List<InvoiceHead> getInvoiceByCriteria(InvoiceHead invoice) throws Exception;
}
