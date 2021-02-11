/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.xml.constants;

/**
 *
 * @author emman
 */
public enum MessagesError {

    MSG_ERROR_BAD_CONFIG("Archivo de configuracion mal definido"),
    MSG_ERROR_NO_CONFIG_FILE_EXISTS("El archivo de configuracion no existe");
    private final String value;

    MessagesError(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
