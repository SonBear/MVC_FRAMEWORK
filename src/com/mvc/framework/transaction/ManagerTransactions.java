/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.transaction;

import com.mvc.framework.logger.LogManager;
import com.mvc.framework.logger.ManagerLog;
import com.mvc.framework.reflection.ManagerReflection;
import com.mvc.framework.reflection.ReflectionProcessor;
import com.mvc.framework.xml.ManagerTransactionXML;
import com.mvc.framework.xml.XMLManager;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author emman
 */
public class ManagerTransactions implements TransactionExecutor {

    private final ReflectionProcessor managerRe = new ManagerReflection();
    private final XMLManager<Transaction> managerXML = new ManagerTransactionXML("files/config.xml");
    private final LogManager managerLog = new ManagerLog();

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
        Object objectRes = managerRe.runMethodModel(transaction.getModel(), transaction.getModel_func(), obj);
        //Controller response of that object processed and define behaviour in ur view
        managerRe.runMethodController(transaction.getController(), transaction.getController_func(), frame, objectRes);
        //the view execute some operation about the object processed

        managerLog.writeLogFile(transactions, transaction);
        //Cla
    }
}
