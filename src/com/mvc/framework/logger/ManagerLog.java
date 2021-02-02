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
public class ManagerLog implements LogTransaction {

    private String pathLogFile;
    private final String PATH_PROPS = "files/configLog.properties";
    private final String PROPERTY_LOG_ON = "LogOn";
    private final String PROPERTY_MAX_CAPACITY = "MaxCapacityFile";

    private Properties props;
    private final ManagerFiles managerFiles = new ManagerFiles();

    private int currentNumberFile;
    private boolean isOn;
    private File file;

    public ManagerLog() {
        pathLogFile = "files/log" + 0 + ".txt";
        file = new File(pathLogFile);
        configLog();
    }

    private void checkIsOnLog() throws BadConfigLogException {
        String valueOn = props.getProperty(PROPERTY_LOG_ON);
        if (valueOn == null) {
            throw new BadConfigLogException("Error la propiedad LogOn no está definida en el archivo");
        }

        valueOn = valueOn.trim();
        if (valueOn.equals("1")) {
            isOn = true;
        } else {
            isOn = false;
        }
    }

    private void checkFileSize() throws BadConfigLogException {
        String size = props.getProperty(PROPERTY_MAX_CAPACITY);
        if (size == null) {
            throw new BadConfigLogException("Error la propiedad MaxCapacityFile no está definida en el archivo");
        }
        try {
            if (size.contains("kb")) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeKiloBytes(file) > dSize) {
                    changeCurrentNumberFile();
                }

            } else if (size.contains("mb")) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeMegaBytes(file) > dSize) {
                    changeCurrentNumberFile();
                }

            } else if (size.contains("gb")) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeGigaBytes(file) > dSize) {
                    changeCurrentNumberFile();
                }

            } else {
                throw new BadConfigLogException("Error escriba el tipo de tamaño correcto kb-mg-gb");
            }
        } catch (NumberFormatException ex) {
            throw new BadConfigLogException("Error formato incorrecto del tamaño de archivo maximo");
        }

    }

    private void changeCurrentNumberFile() {

        currentNumberFile++;
        pathLogFile = "files/log" + currentNumberFile + ".txt";
        file = new File(pathLogFile);

    }

    private String readLogFile() {
        return managerFiles.readFile(pathLogFile);
    }

    @Override
    public void writeLogTransaction(List<Transaction> transactions, Transaction transaction) throws BadConfigLogException {
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

    private void configLog() {
        if (props == null) {
            props = new Properties();

            try (InputStream input = new FileInputStream(PATH_PROPS)) {

                // load a properties file
                props.load(input);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
