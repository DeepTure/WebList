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
public class aministrador {

    private int id_admin;
    private String nombre;
    private String contrasena;
    private String correo;

    public aministrador() {
    }

    public aministrador(int id_admin, String nombre, String contrasena, String correo) {
        this.id_admin = id_admin;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
