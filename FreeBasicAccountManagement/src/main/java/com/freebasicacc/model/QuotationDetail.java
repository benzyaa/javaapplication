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
public class QuotationDetail extends Entity implements Serializable{
    private String quotationHeadId;
    private String quotationDetailId;
    private int itemNo;
    private Material material;
    private int quantity;
    private Unit unit;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;

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
     * @return the quotationDetailId
     */
    public String getQuotationDetailId() {
        return quotationDetailId;
    }

    /**
     * @param quotationDetailId the quotationDetailId to set
     */
    public void setQuotationDetailId(String quotationDetailId) {
        this.quotationDetailId = quotationDetailId;
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
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}