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
public class profesor {
    private int numeroE;
    private String nombres;
    private String app;
    private String apm;
    private String password;
    private String correo;
    private String[] materias;

    public profesor(int numeroE, String password) {
        this.numeroE = numeroE;
        this.password = password;
    }

    public profesor(int numeroE, String nombres, String app, String apm, String password, String correo, String[] materias) {
        this.numeroE = numeroE;
        this.nombres = nombres;
        this.app = app;
        this.apm = apm;
        this.password = password;
        this.correo = correo;
        this.materias = materias;
    }

    public profesor() {
    }

    public int getNumeroE() {
        return numeroE;
    }

    public void setNumeroE(int numeroE) {
        this.numeroE = numeroE;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String[] getMaterias() {
        return materias;
    }

    public void setMaterias(String[] materias) {
        this.materias = materias;
    }
}
