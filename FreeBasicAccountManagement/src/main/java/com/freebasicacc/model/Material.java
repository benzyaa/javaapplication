package com.freebasicacc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Material extends Entity implements Serializable{

        private String materialId;
        private String materialName;
        private String materialDesc;
        private BigDecimal materialPrice;
        private BigDecimal materialPriceTo;
        private String unitId;
        private Unit unit;

    /**
     * @return the materialId
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId the materialId to set
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the materialName
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param materialName the materialName to set
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return the materialDesc
     */
    public String getMaterialDesc() {
        return materialDesc;
    }

    /**
     * @param materialDesc the materialDesc to set
     */
    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    /**
     * @return the materialPrice
     */
    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    /**
     * @param materialPrice the materialPrice to set
     */
    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
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
     * @return the unitId
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * @param unitId the unitId to set
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * @return the materialPriceTo
     */
    public BigDecimal getMaterialPriceTo() {
        return materialPriceTo;
    }

    /**
     * @param materialPriceTo the materialPriceTo to set
     */
    public void setMaterialPriceTo(BigDecimal materialPriceTo) {
        this.materialPriceTo = materialPriceTo;
    }
    
}