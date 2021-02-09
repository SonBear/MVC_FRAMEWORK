package com.mvc.framework.reflection.constanst;

/**
 *
 * @author emman
 */
public enum MessagesError {

    ERROR_NO_METHOD_EXIST("Error metodo no existe en el modelo"),
    ERROR_NO_ACCESIBLE_METHOD("Error metodo inaccesible"),
    ERROR_ARGUMENTS_NO_VALIDS("Error argumentos del metodo del modelo no validos"),
    ERROR_IN_METHOD_CODE("Error en el codigo dentro del metodo"),
    ERROR_NO_CLASS_EXISTS("Error la clase no está definida"),
    ERROR_INIT_CLASS("Error al inicializar clase"),
    ERROR_INIT_UNACCESBILE("Error al inicializar no es accesible el constructor clase"),
    ERROR_INIT_ARGUMENTS("Error al inicializar argumentos no validos para el contructor clase"),
    ERROR_INIT_CODE("Error en el codigo dentro del contructor de la clase");

    private final String value;

    MessagesError(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
