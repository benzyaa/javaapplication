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
public class Entity implements Serializable{
    
    private String createBy;
    private java.util.Date createDate;
    private java.util.Date createDateTo;
    private String updateBy;
    private java.util.Date updateDate;
    private java.util.Date updateDateTo;
    private int isActive;
    private String orderBy;
    private boolean isDesc;
    private int rowStart;
    private int rowCount;
    private long totalRow;


    /**
     * @return the createBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy the createBy to set
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return the createDate
     */
    public java.util.Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the updateBy
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy the updateBy to set
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return the updateDate
     */
    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the isActive
     */
    public int getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the createDateTo
     */
    public java.util.Date getCreateDateTo() {
        return createDateTo;
    }

    /**
     * @param createDateTo the createDateTo to set
     */
    public void setCreateDateTo(java.util.Date createDateTo) {
        this.createDateTo = createDateTo;
    }

    /**
     * @return the updateDateTo
     */
    public java.util.Date getUpdateDateTo() {
        return updateDateTo;
    }

    /**
     * @param updateDateTo the updateDateTo to set
     */
    public void setUpdateDateTo(java.util.Date updateDateTo) {
        this.updateDateTo = updateDateTo;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the isDesc
     */
    public boolean getIsDesc() {
        return isIsDesc();
    }

    /**
     * @param isDesc the isDesc to set
     */
    public void setIsDesc(boolean isDesc) {
        this.isDesc = isDesc;
    }

    /**
     * @return the isDesc
     */
    public boolean isIsDesc() {
        return isDesc;
    }

    /**
     * @return the rowStart
     */
    public int getRowStart() {
        return rowStart;
    }

    /**
     * @param rowStart the rowStart to set
     */
    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the totalRow
     */
    public long getTotalRow() {
        return totalRow;
    }

    /**
     * @param totalRow the totalRow to set
     */
    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }
}