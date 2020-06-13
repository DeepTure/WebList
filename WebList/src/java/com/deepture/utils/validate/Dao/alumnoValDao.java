/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.validate.Dao;

import com.deepture.utils.classdata.alumno;

/**
 *
 * @author JAFET
 */
public interface alumnoValDao {

    String newStudentsValidate(alumno al, String gr);

    String validarBoleta(int boleta);
}
