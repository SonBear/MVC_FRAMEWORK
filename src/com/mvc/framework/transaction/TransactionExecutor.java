/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.transaction;

import javax.swing.JFrame;

/**
 *
 * @author emman
 */
public interface TransactionExecutor {

    public void executeTransaction(String nameTransaction, JFrame frame, Object modelObj) throws Exception;
}
