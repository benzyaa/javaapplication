package com.freebasicacc.model;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


public class InvoiceHead extends Entity implements Serializable{
        private String invoiceHeadId;
        private Customer customer;
        private String poNumber;
        private BigDecimal totalAmount;
        private BigDecimal discount;
        private BigDecimal vat;
        private BigDecimal netAmount;
        private String netAmountText;
        private String paymentType;
        private java.util.Date invoiceDueDate;
        private java.util.Date invoiceDueDateTo;
        private java.util.Date paymentDueDate;
        private java.util.Date paymentDueDateTo;
        private List<InvoiceDetail> invoiceDetailSet;

    /**
     * @return the invoiceHeadId
     */
    public String getInvoiceHeadId() {
        return invoiceHeadId;
    }

    /**
     * @param invoiceHeadId the invoiceHeadId to set
     */
    public void setInvoiceHeadId(String invoiceHeadId) {
        this.invoiceHeadId = invoiceHeadId;
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
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the discount
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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
     * @return the netAmount
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * @param netAmount the netAmount to set
     */
    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * @return the netAmountText
     */
    public String getNetAmountText() {
        return netAmountText;
    }

    /**
     * @param netAmountText the netAmountText to set
     */
    public void setNetAmountText(String netAmountText) {
        this.netAmountText = netAmountText;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the invoiceDueDate
     */
    public java.util.Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    /**
     * @param invoiceDueDate the invoiceDueDate to set
     */
    public void setInvoiceDueDate(java.util.Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    /**
     * @return the paymentDueDate
     */
    public java.util.Date getPaymentDueDate() {
        return paymentDueDate;
    }

    /**
     * @param paymentDueDate the paymentDueDate to set
     */
    public void setPaymentDueDate(java.util.Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    /**
     * @return the invoiceDetailSet
     */
    public List<InvoiceDetail> getInvoiceDetailSet() {
        return invoiceDetailSet;
    }

    /**
     * @param invoiceDetailSet the invoiceDetailSet to set
     */
    public void setInvoiceDetailSet(List<InvoiceDetail> invoiceDetailSet) {
        this.invoiceDetailSet = invoiceDetailSet;
    }

    /**
     * @return the invoiceDueDateTo
     */
    public java.util.Date getInvoiceDueDateTo() {
        return invoiceDueDateTo;
    }

    /**
     * @param invoiceDueDateTo the invoiceDueDateTo to set
     */
    public void setInvoiceDueDateTo(java.util.Date invoiceDueDateTo) {
        this.invoiceDueDateTo = invoiceDueDateTo;
    }

    /**
     * @return the paymentDueDateTo
     */
    public java.util.Date getPaymentDueDateTo() {
        return paymentDueDateTo;
    }

    /**
     * @param paymentDueDateTo the paymentDueDateTo to set
     */
    public void setPaymentDueDateTo(java.util.Date paymentDueDateTo) {
        this.paymentDueDateTo = paymentDueDateTo;
    }
}