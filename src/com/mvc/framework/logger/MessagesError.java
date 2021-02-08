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
public enum MessagesError {

    MSG_ERROR_BAD_CONFIG_LOG_ON("Error la propiedad LogOn no está definida en el archivo"),
    MSG_ERROR_BAD_CONFIG_MAX_CAP("Error la propiedad MaxCapacityFile no está definida en el archivo"),
    MSG_ERROR_BAD_TYPE_SIZE_FILE("Error escriba el tipo de tamaño correcto kb-mg-gb"),
    MSG_ERROR_BAD_CONFIG_VALUE_MAX_CAP("Error formato incorrecto del tamaño de archivo maximo");

    private final String value;

    MessagesError(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
