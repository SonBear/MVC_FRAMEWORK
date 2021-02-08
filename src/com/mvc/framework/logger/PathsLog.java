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
public enum PathsLog {

    PATH_PROPS("files/configLog.properties"),
    RELATIVE_PATH_LOG_FILE("files/log"),
    TYPE_LOG_FILE(".txt");

    private final String value;

    PathsLog(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
