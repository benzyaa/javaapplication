/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author benzyaa
 */
public class QuotationHead extends Entity implements Serializable{
    private String quotationHeadId;
    private String quotationTitle;
    private Customer customer;
    private java.util.Date quotationDate;
    private java.util.Date quotationDateTo;
    private String deliveryPlace;
    private java.util.Date leadDate;
    private java.util.Date leadDateTo;
    private String paymentTerm;
    private BigDecimal totalValue;
    private BigDecimal vat;
    private BigDecimal netValue;
    private String netValueText;
    private List<QuotationDetail> quotationDetailSet;

    /**
     * @return the quotationHeadId
     */
    public String getQuotationHeadId() {
        return quotationHeadId;
    }

    /**
     * @param quotationHeadId the quotationHeadId to set
     */
    public void setQuotationHeadId(String quotationHeadId) {
        this.quotationHeadId = quotationHeadId;
    }

    /**
     * @return the quotationTitle
     */
    public String getQuotationTitle() {
        return quotationTitle;
    }

    /**
     * @param quotationTitle the quotationTitle to set
     */
    public void setQuotationTitle(String quotationTitle) {
        this.quotationTitle = quotationTitle;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the quotationDate
     */
    public java.util.Date getQuotationDate() {
        return quotationDate;
    }

    /**
     * @param quotationDate the quotationDate to set
     */
    public void setQuotationDate(java.util.Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    /**
     * @return the deliveryPlace
     */
    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    /**
     * @param deliveryPlace the deliveryPlace to set
     */
    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    /**
     * @return the leadDate
     */
    public java.util.Date getLeadDate() {
        return leadDate;
    }

    /**
     * @param leadDate the leadDate to set
     */
    public void setLeadDate(java.util.Date leadDate) {
        this.leadDate = leadDate;
    }

    /**
     * @return the paymentTerm
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * @param paymentTerm the paymentTerm to set
     */
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * @return the totalValue
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * @return the vat
     */
    public BigDecimal getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    /**
     * @return the netValue
     */
    public BigDecimal getNetValue() {
        return netValue;
    }

    /**
     * @param netValue the netValue to set
     */
    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    /**
     * @return the netValueText
     */
    public String getNetValueText() {
        return netValueText;
    }

    /**
     * @param netValueText the netValueText to set
     */
    public void setNetValueText(String netValueText) {
        this.netValueText = netValueText;
    }

    /**
     * @return the quotationDetailSet
     */
    public List<QuotationDetail> getQuotationDetailSet() {
        return quotationDetailSet;
    }
    /**
     * @param quotationDetailSet the quotationDetailSet to set
     */
    public void setQuotationDetailSet(List<QuotationDetail> quotationDetailSet) {
        this.quotationDetailSet = quotationDetailSet;
    }

    /**
     * @return the quotationDateTo
     */
    public java.util.Date getQuotationDateTo() {
        return quotationDateTo;
    }

    /**
     * @param quotationDateTo the quotationDateTo to set
     */
    public void setQuotationDateTo(java.util.Date quotationDateTo) {
        this.quotationDateTo = quotationDateTo;
    }

    /**
     * @return the leadDateTo
     */
    public java.util.Date getLeadDateTo() {
        return leadDateTo;
    }

    /**
     * @param leadDateTo the leadDateTo to set
     */
    public void setLeadDateTo(java.util.Date leadDateTo) {
        this.leadDateTo = leadDateTo;
    }

}