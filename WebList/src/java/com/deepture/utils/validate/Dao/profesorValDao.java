/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.validate.Dao;

import com.deepture.utils.classdata.profesor;

/**
 *
 * @author JAFET
 */
public interface profesorValDao {
    boolean newTeacherValidate(profesor profe)throws Exception;
    
    boolean numberEmployeeValidate(int numberE)throws Exception;
    
    boolean logInValidate(profesor profe)throws Exception;
}
