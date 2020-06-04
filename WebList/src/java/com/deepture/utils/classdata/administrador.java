/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.classdata;

/**
 *
 * @author JAFET
 */
public class administrador {
    //tiene sobrecarga de metodos
    private int id;
    private String nombre;
    private String password;
    private String correo;

    public administrador(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public administrador(int id, String nombre, String password, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
    }

    public administrador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
