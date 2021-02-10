/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.transaction;

import com.mvc.framework.logger.LogManagerTransaction;
import com.mvc.framework.logger.LogTransaction;
import com.mvc.framework.logger.exceptions.NoFilePropsException;
import com.mvc.framework.reflection.ManagerReflection;
import com.mvc.framework.reflection.ReflectionTransaction;
import com.mvc.framework.transaction.exceptions.NoTransactionException;
import com.mvc.framework.xml.ManagerTransactionXML;
import com.mvc.framework.xml.XMLManager;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author emman
 */
public class ManagerTransactions implements TransactionExecutor {

    private final String PATH_CONFIG_FILE = "files/config.xml";
    private final String ERROR_NO_TRANSACTION_EXIST = "La transaccion no está definida";

    private final ReflectionTransaction managerRe;
    private final XMLManager<Transaction> managerXML;
    private final LogTransaction managerLog;

    public ManagerTransactions() throws NoFilePropsException {
        this.managerRe = new ManagerReflection();
        this.managerXML = new ManagerTransactionXML(PATH_CONFIG_FILE);
        this.managerLog = new LogManagerTransaction();
    }

    @Override
    public void executeTransaction(String nameTransaction, JFrame frame, Object obj) throws Exception {

        //Search Transaction
        List<Transaction> transactions = managerXML.readData();

        Transaction transaction = null;
        for (Transaction trans : transactions) {
            if (trans.getName().equals(nameTransaction)) {
                transaction = trans;
            }
        }

        if (transaction == null) {
            throw new NoTransactionException(ERROR_NO_TRANSACTION_EXIST);
        }

        Object controller = managerRe.getInstanceClass(transaction.getController());

        managerRe.runMethodModel(transaction.getModel(), transaction.getModelFunction(), frame, controller, obj);

        managerLog.writeLogTransaction(transactions, transaction);

    }
}
