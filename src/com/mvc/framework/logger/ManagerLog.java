/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger;

import com.mvc.framework.transaction.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author emman
 */
public class ManagerLog implements LogManager {

    private String pathLogFile;
    private final String PATH_PROPS = "files/configLog.properties";

    private Properties props;
    private final ManagerFiles managerFiles = new ManagerFiles();

    private int currentNumberFile;
    private boolean isOn;
    private File file;

    public ManagerLog() {
        pathLogFile = "files/log" + 0 + ".txt";
        file = new File(pathLogFile);
    }

    private void checkIsOnLog() {
        String valueOn = props.getProperty("LogOn");
        valueOn = valueOn.trim();
        if (valueOn.equals("1")) {
            isOn = true;
        } else {
            isOn = false;
        }
    }

    private void checkFileSize() {
        String size = props.getProperty("MaxCapacityFile");
        if (size.contains("kb")) {
            checkCurrentNumberFile(size);

        }
        if (size.contains("mb")) {
            checkCurrentNumberFile(size);

        }
        if (size.contains("gb")) {
            checkCurrentNumberFile(size);

        }
    }

    private void checkCurrentNumberFile(String size) {
        size = size.replaceAll("[a-zA-Z]", "");
        double dSize = Double.parseDouble(size);
        while (managerFiles.getFileSizeGigaBytes(file) > dSize) {
            currentNumberFile++;
            pathLogFile = "files/log" + currentNumberFile + ".txt";
            file = new File(pathLogFile);
        }
    }

    private String readLogFile() {
        props = configLog();
        checkFileSize();
        return managerFiles.readFile(pathLogFile);
    }

    @Override
    public void writeLogFile(List<Transaction> transactions, Transaction transaction) throws BadConfigLogException {
        props = configLog();
        if (!(props.contains("LogOn") && props.contains("MaxCapacityFile"))) {
            throw new BadConfigLogException("Error en el archivo de configuracion");
        }
        checkFileSize();
        checkIsOnLog();
        if (isOn) {
            String con = readLogFile();
            if (!con.equals("")) {
                con += "\n\n";
            }
            con += ("Date Executed: " + new Date().toString() + "\n");
            con += ("Transaction avaibles: \n");

            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).equals(transaction)) {
                    con += i + 1 + ".- " + transactions.get(i).getName() + "*" + "\n";
                } else {
                    con += i + 1 + ".- " + transactions.get(i).getName() + "\n";
                }
            }

            managerFiles.writeFile(pathLogFile, con);
        }

    }

    private Properties configLog() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(PATH_PROPS)) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
}
