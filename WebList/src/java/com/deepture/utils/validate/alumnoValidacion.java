/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.validate;

import com.deepture.utils.classdata.alumno;
import com.deepture.utils.validate.Dao.alumnoValDao;

/**
 *
 * @author JAFET
 */
public class alumnoValidacion implements alumnoValDao {
//falta validar
    @Override
    public String newStudentsValidate(alumno al, String gr) {
        String vnombre = al.getNombre();
        String vcorreo = al.getCorreo();
        String vappat = al.getApp();
        String vapmat = al.getApm();
        String vbol = String.valueOf(al.getBoleta());
        if (vnombre.matches("^[A-Za-z\\s]+${1,44}") & vappat.matches("^[a-zA-Z]+${1,44}") & vapmat.matches("^[a-zA-Z]+${1,44}") & vbol.matches("^[0-9]+${1,10}+$")) {
            return "ok";
        } else {
            return "";
        }
    }

    @Override
    public String validarBoleta(int boleta) {
        String verifica = String.valueOf(boleta);
        if (verifica.matches("^[0-9]+${1,10}+$")) {
            return "ok";
        } else {
            return "";
        }

    }
}
