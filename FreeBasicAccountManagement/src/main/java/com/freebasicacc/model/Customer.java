/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.model;

import java.io.Serializable;

/**
 *
 * @author benzyaa
 */
public class Customer extends Entity implements Serializable{
    
    private String customerId;
    private String customerName;
    private String customerAddres;
    private String customerContract;
    private String customerPhone;
    private String customerFax;
    private String customerEmail;
    private String remark;
    private java.util.Date registerDate;
    private java.util.Date registerDateTo;
    private java.util.Date lastContractDate;
    private java.util.Date lastContractDateTo;

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerAddres
     */
    public String getCustomerAddres() {
        return customerAddres;
    }

    /**
     * @param customerAddres the customerAddres to set
     */
    public void setCustomerAddres(String customerAddres) {
        this.customerAddres = customerAddres;
    }

    /**
     * @return the customerContract
     */
    public String getCustomerContract() {
        return customerContract;
    }

    /**
     * @param customerContract the customerContract to set
     */
    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }

    /**
     * @return the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return the customerFax
     */
    public String getCustomerFax() {
        return customerFax;
    }

    /**
     * @param customerFax the customerFax to set
     */
    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    /**
     * @return the customerEmail
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * @param customerEmail the customerEmail to set
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the registerDate
     */
    public java.util.Date getRegisterDate() {
        return registerDate;
    }

    /**
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(java.util.Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @return the lastContractDate
     */
    public java.util.Date getLastContractDate() {
        return lastContractDate;
    }

    /**
     * @param lastContractDate the lastContractDate to set
     */
    public void setLastContractDate(java.util.Date lastContractDate) {
        this.lastContractDate = lastContractDate;
    }

    /**
     * @return the registerDateTo
     */
    public java.util.Date getRegisterDateTo() {
        return registerDateTo;
    }

    /**
     * @param registerDateTo the registerDateTo to set
     */
    public void setRegisterDateTo(java.util.Date registerDateTo) {
        this.registerDateTo = registerDateTo;
    }

    /**
     * @return the lastContractDateTo
     */
    public java.util.Date getLastContractDateTo() {
        return lastContractDateTo;
    }

    /**
     * @param lastContractDateTo the lastContractDateTo to set
     */
    public void setLastContractDateTo(java.util.Date lastContractDateTo) {
        this.lastContractDateTo = lastContractDateTo;
    }
    
}