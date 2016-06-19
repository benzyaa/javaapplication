/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.model;

/**
 *
 * @author benzyaa
 */
public class DisplayLang {
    private String langId;
    private String langName;

    public DisplayLang(String langId, String langName) {
        this.langId = langId;
        this.langName = langName;
    }
    
    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }
}