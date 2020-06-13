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
public class alumno {

    private int boleta;
    private String nombre;
    private String app;
    private String apm;
    private String correo;

    public alumno(int boleta, String nombre, String app, String apm, String correo) {
        this.boleta = boleta;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.correo = correo;
    }

    public alumno() {
    }

    public int getBoleta() {
        return boleta;
    }

    public void setBoleta(int boleta) {
        this.boleta = boleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
