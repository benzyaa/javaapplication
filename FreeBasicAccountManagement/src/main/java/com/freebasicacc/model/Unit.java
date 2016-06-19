package com.freebasicacc.model;

import java.io.Serializable;

public class Unit extends Entity implements Serializable {

        private String unitId;
        private String unitName;
        private String unitDesc;

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
     * @return the unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * @param unitName the unitName to set
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * @return the unitDesc
     */
    public String getUnitDesc() {
        return unitDesc;
    }

    /**
     * @param unitDesc the unitDesc to set
     */
    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
    }       
}