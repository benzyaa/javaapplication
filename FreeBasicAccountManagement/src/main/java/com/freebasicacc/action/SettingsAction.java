/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.action;

import com.freebasicacc.model.DisplayLang;
import com.freebasicacc.ui.SettingsInternalPanel;
import com.freebasicacc.ui.renderer.DisplayLangComboboxRenderer;
import com.freebasicacc.util.FileUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;
import javax.swing.JComboBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author benzyaa
 */
public class SettingsAction {
     private static transient Logger logger = LogManager.getLogger(SettingsAction.class);
     
     public void saveSettings(SettingsInternalPanel panel) throws Exception{
        this.saveMySqlSettings(panel);
        this.saveLangSettings(panel);
     }
     
     
     public void loadSettings(SettingsInternalPanel panel) throws Exception{
        Properties currentProperties = this.loadMySqlSettings();
        panel.getMySqlHostDatabaseSettingTextField().setText(currentProperties.getProperty("host"));
        panel.getMySqlDatabaseNameDatabaseSettingTextField().setText(currentProperties.getProperty("db"));
        panel.getMySqlUserNameDatabaseSettingTextField().setText(currentProperties.getProperty("username"));
        panel.getMySqlPasswordDatabaseSettingTextField().setText(currentProperties.getProperty("password"));
     }
     
     
     private Properties loadMySqlSettings() throws Exception{
        Properties dbProperties = new Properties();
        dbProperties.load(new FileInputStream(FileUtil.getCurrentJarPath()+"/config/jdbc.properties"));
        return dbProperties;
     }
     
     public static Properties loadLanguageSettings() throws Exception{
        Properties dbProperties = new Properties();
        dbProperties.load(new FileInputStream(FileUtil.getCurrentJarPath()+"/config/lang.properties"));
        return dbProperties;
     }
     
     private void saveLangSettings (SettingsInternalPanel panel) throws Exception{
        DisplayLang displayLang = (DisplayLang) panel.getDisplayLanguageSettingComboBox().getSelectedItem();
        Properties dbProperties = new Properties();
        dbProperties.setProperty("display_lang", displayLang.getLangId());
        dbProperties.store(new FileOutputStream(FileUtil.getCurrentJarPath()+"/config/lang.properties"),null);
        panel.changeLanguage(displayLang.getLangId());
     }
     
     
     private void saveMySqlSettings (SettingsInternalPanel panel) throws Exception{
        String mySqlHost = panel.getMySqlHostDatabaseSettingTextField().getText();
        String mySqlDatabaseName = panel.getMySqlDatabaseNameDatabaseSettingTextField().getText();
        String mySqlUserName = panel.getMySqlUserNameDatabaseSettingTextField().getText();
        char[] mySqlUserPassword = panel.getMySqlPasswordDatabaseSettingTextField().getPassword();
         Properties dbProperties = new Properties();
         dbProperties.setProperty("host", mySqlHost);
         dbProperties.setProperty("db", mySqlDatabaseName);
         dbProperties.setProperty("username", mySqlUserName);
         dbProperties.setProperty("password", String.valueOf(mySqlUserPassword));
         dbProperties.store(new FileOutputStream(FileUtil.getCurrentJarPath()+"/config/jdbc.properties"),null);
    }
     
    public void assignComboBoxModel(JComboBox combobox,Map comboboxItemMap,Object selectedItem){
            combobox.removeAllItems();
            for(Object comboboxItem : comboboxItemMap.values()){
                combobox.addItem(comboboxItem);
            }
            combobox.setRenderer(new DisplayLangComboboxRenderer());
            combobox.setSelectedItem(selectedItem);
    }
}
