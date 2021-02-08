/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger;

/**
 *
 * @author emman
 */
public enum LogText {

    MSG_DATE_EXECUTE("Date Executed: "),
    MSG_TRANSACTION_LIST("Transaction avaibles: \n"),
    POINTER_TRANSACTION_EXECUTE("*"),
    POINT_LIST(".- ");

    private final String value;

    LogText(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
