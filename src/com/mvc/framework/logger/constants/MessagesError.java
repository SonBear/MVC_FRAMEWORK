/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger.constants;

/**
 *
 * @author emman
 */
public enum MessagesError {

    MSG_ERROR_BAD_CONFIG_LOG_ON("Error la propiedad LogOn no está definida en el archivo configLog.properties"),
    MSG_ERROR_BAD_CONFIG_MAX_CAP("Error la propiedad MaxCapacityFile no está definida en el archivo configLog.properties"),
    MSG_ERROR_BAD_TYPE_SIZE_FILE("Error escriba el tipo de tamaño correcto kb-mg-gb en el archivo configLog.properties"),
    MSG_ERROR_BAD_CONFIG_VALUE_MAX_CAP("Error formato incorrecto del tamaño de archivo maximo en el archivo configLog.properties"),
    MSG_ERROR_NO_FILE_PROPERTIES("Error el archivo configLog.properties no existe"),
    MSG_ERROR_PATH_UNACCESIBLE("La ruta de los archivos no es accesible"),
    MSG_ERROR_NOT_FILE_LOG_EXST("El archivo para el log no pudo crearse");

    private final String value;

    MessagesError(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
