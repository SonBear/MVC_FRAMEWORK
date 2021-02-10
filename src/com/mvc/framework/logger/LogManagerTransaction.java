package com.mvc.framework.logger;

import com.mvc.framework.logger.constants.LogText;
import com.mvc.framework.logger.constants.MessagesError;
import com.mvc.framework.logger.constants.PathsLog;
import com.mvc.framework.logger.constants.SizeFiles;
import com.mvc.framework.logger.exceptions.BadConfigLogException;
import com.mvc.framework.logger.exceptions.NoFilePropsException;
import com.mvc.framework.transaction.Transaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author emman
 */
public class LogManagerTransaction implements LogTransaction {

    //Path files
    private String pathLogFile;

    //Properties names
    private final String PROPERTY_LOG_ON = "LogOn";
    private final String PROPERTY_MAX_CAPACITY = "MaxCapacityFile";

    //properties values
    private final String VALUE_IS_LOG_ON = "1";

    //Util objects
    private Properties props;
    private final ManagerFiles managerFiles;

    //status objects
    private int currentNumberFile;
    private File currentFile;

    public LogManagerTransaction() throws NoFilePropsException {
        pathLogFile = PathsLog.RELATIVE_PATH_LOG_FILE.toString()
                + currentNumberFile + PathsLog.TYPE_LOG_FILE.toString();
        currentFile = new File(pathLogFile);
        managerFiles = new ManagerFiles();
        configLog();
    }

    private boolean isLogOn() throws BadConfigLogException {
        String valueOn = props.getProperty(PROPERTY_LOG_ON);

        if (valueOn == null) {
            throw new BadConfigLogException(MessagesError.MSG_ERROR_BAD_CONFIG_LOG_ON.toString());
        }

        valueOn = valueOn.trim();

        return valueOn.equals(VALUE_IS_LOG_ON);
    }

    private void checkFileSize() throws BadConfigLogException {
        String size = props.getProperty(PROPERTY_MAX_CAPACITY);
        if (size == null) {
            throw new BadConfigLogException(MessagesError.MSG_ERROR_BAD_CONFIG_MAX_CAP.toString());
        }
        try {
            if (size.contains(SizeFiles.SIZE_KB.toString())) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeKiloBytes(currentFile) > dSize) {
                    changeCurrentNumberFile();
                }

            } else if (size.contains(SizeFiles.SIZE_MG.toString())) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeMegaBytes(currentFile) > dSize) {
                    changeCurrentNumberFile();
                }

            } else if (size.contains(SizeFiles.SIZE_GB.toString())) {
                size = size.substring(0, size.length() - 2);

                double dSize = Double.parseDouble(size);
                while (managerFiles.getFileSizeGigaBytes(currentFile) > dSize) {
                    changeCurrentNumberFile();
                }

            } else {
                throw new BadConfigLogException(MessagesError.MSG_ERROR_BAD_TYPE_SIZE_FILE.toString());
            }
        } catch (NumberFormatException ex) {
            throw new BadConfigLogException(MessagesError.MSG_ERROR_BAD_CONFIG_VALUE_MAX_CAP.toString());
        }

    }

    private void changeCurrentNumberFile() throws BadConfigLogException {

        currentNumberFile++;
        pathLogFile = PathsLog.RELATIVE_PATH_LOG_FILE.toString()
                + currentNumberFile + PathsLog.TYPE_LOG_FILE.toString();
        currentFile = new File(pathLogFile);
    }

    private void checkCurrentFile() throws BadConfigLogException {
        if (!currentFile.exists()) {
            try {
                currentFile.createNewFile();
            } catch (IOException ex) {
                throw new BadConfigLogException(MessagesError.MSG_ERROR_NOT_FILE_LOG_EXST.toString());
            }
        }
    }

    private String readLogFile() throws FileNotFoundException {

        return managerFiles.readFile(pathLogFile);

    }

    @Override
    public void writeLogTransaction(List<Transaction> transactions, Transaction transaction) throws BadConfigLogException, NoFilePropsException {
        checkFileSize();
        checkCurrentFile();
        if (isLogOn()) {
            try {
                String content = readLogFile();
                if (!content.equals("")) {
                    content += "\n\n";
                }
                content += (LogText.MSG_DATE_EXECUTE + new Date().toString() + "\n");
                content += (LogText.MSG_TRANSACTION_LIST);

                for (int i = 0; i < transactions.size(); i++) {
                    if (transactions.get(i).equals(transaction)) {
                        content += (i + 1) + LogText.POINT_LIST.toString()
                                + transactions.get(i).getName() + LogText.POINTER_TRANSACTION_EXECUTE + "\n";
                    } else {
                        content += (i + 1) + LogText.POINT_LIST.toString() + transactions.get(i).getName() + "\n";
                    }
                }

                managerFiles.writeFile(pathLogFile, content);
            } catch (IOException ex) {
                throw new BadConfigLogException(MessagesError.MSG_ERROR_NOT_FILE_LOG_EXST.toString());
            }

        }

    }

    private void configLog() throws NoFilePropsException {
        if (props == null) {
            props = new Properties();

            try (InputStream input = new FileInputStream(PathsLog.PATH_PROPS.toString())) {

                // load a properties file
                props.load(input);

            } catch (IOException ex) {
                throw new NoFilePropsException(MessagesError.MSG_ERROR_NO_FILE_PROPERTIES.toString());
            }
        }
    }
}
