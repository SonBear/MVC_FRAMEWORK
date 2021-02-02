/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger;

import com.mvc.framework.transaction.Transaction;
import java.util.List;

/**
 *
 * @author emman
 */
public interface LogTransaction {

    public void writeLogTransaction(List<Transaction> transactions, Transaction transaction) throws BadConfigLogException;

}
