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
import com.mvc.framework.reflection.ReflectionProcessorTransaction;
import com.mvc.framework.xml.ManagerTransactionXML;
import com.mvc.framework.xml.XMLManager;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author emman
 */
public class ManagerTransactions implements TransactionExecutor {

    private final ReflectionProcessorTransaction managerRe = new ManagerReflection();
    private final XMLManager<Transaction> managerXML = new ManagerTransactionXML("files/config.xml");
    private final LogTransaction managerLog;

    public ManagerTransactions() throws NoFilePropsException {
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
            throw new NoTransactionException("La transaccion no está definida");
        }

        //model process Object
        Object controller = managerRe.getInstanceClass(transaction.getController());
        //Controller response of that object processed and define behaviour in ur view
        managerRe.runMethodModel(transaction.getModel(), transaction.getModel_func(), frame, controller, obj);
        //the view execute some operation about the object processed

        managerLog.writeLogTransaction(transactions, transaction);
        //Cla
    }
}
