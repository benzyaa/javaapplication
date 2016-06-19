package com.freebasicacc.main;

import com.freebasicacc.action.SettingsAction;
import com.freebasicacc.services.LogService;
import com.freebasicacc.ui.MainFrame;
import java.util.Locale;
import java.util.Properties;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main{
    static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
          javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Properties langProperties = SettingsAction.loadLanguageSettings();
                    Locale.setDefault(new Locale(langProperties.getProperty("display_lang")));
                    logger.info("Main - main() - DefaultLocale : "+Locale.getDefault());
                    /*LogService logService = new LogService();
                    logService.intiallizeLogger();*/
                    java.awt.Font defaultFont = new java.awt.Font("Tahoma", 0, 12);
                    UIManager.put("Label.font", defaultFont);
                    UIManager.put("Button.font", defaultFont);
                    new MainFrame().setVisible(true);
               }catch(Exception ex){
                    ex.printStackTrace();
                    //logger.info("Error", ex);
               }
            }
        });
    }
}