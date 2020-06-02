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
public class alumnoValidacion implements alumnoValDao{

    @Override
    public String newStudentsValidate(alumno al, String gr) {
        return "ok";
    }
    
}
