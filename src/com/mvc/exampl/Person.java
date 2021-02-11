/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.exampl;

/**
 *
 * @author emman
 */
//Como solo se puede pasar dos parametros uno destinado a la vista el otro sirve como una intercambio de informacion e
//entre las direferentes clases, pero esta objeto puede ser Null, y si es asi esta objeto no estará
//referenciado en ningun metodo...(argumento)
public class Person {

    private int edad;
    private String nombre;

    public Person(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Person{" + "edad=" + edad + ", nombre=" + nombre + '}';
    }

}
