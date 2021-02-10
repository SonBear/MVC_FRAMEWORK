/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.transaction.exceptions;

/**
 *
 * @author emman
 */
public class NoTransactionException extends Exception {

    public NoTransactionException(String message) {
        super(message);
    }

}
