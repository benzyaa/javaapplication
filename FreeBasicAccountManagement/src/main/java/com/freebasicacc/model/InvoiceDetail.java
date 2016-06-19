/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author benzyaa
 */
public class InvoiceDetail extends Entity implements Serializable{
    private String invoiceHeadId;
    private String invoiceDetailId;
    private int itemNo;
    private Material material;
    private BigDecimal pricePerUnit;
    private Unit unit;
    private int quantity;
    private BigDecimal totalValue;

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
     * @return the invoiceDetailId
     */
    public String getInvoiceDetailId() {
        return invoiceDetailId;
    }

    /**
     * @param invoiceDetailId the invoiceDetailId to set
     */
    public void setInvoiceDetailId(String invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    /**
     * @return the itemNo
     */
    public int getItemNo() {
        return itemNo;
    }

    /**
     * @param itemNo the itemNo to set
     */
    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the pricePerUnit
     */
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}