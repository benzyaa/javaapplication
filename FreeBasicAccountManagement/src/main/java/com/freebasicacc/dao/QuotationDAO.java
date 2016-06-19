package com.freebasicacc.dao;

import com.freebasicacc.model.QuotationHead;
import java.util.List;

public interface QuotationDAO {
   public void insertQuotation(QuotationHead quotation);
   public void updateQuotation(QuotationHead quotation);
   public List<QuotationHead> getQuotationByCriteria(QuotationHead quotation);
}
