/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.methodInterface;

import com.deepture.utils.classdata.profesor;

/**
 *
 * @author JAFET
 */
public interface profesorDaoApi {
    boolean create(profesor profe)throws Exception;
    
    boolean update(profesor profe)throws Exception;
    
    boolean delete(int numberE)throws Exception;
    
    boolean logIn(profesor profe)throws Exception ;
    
    public boolean chPsswrd(String ps1, String ps2, int id)throws Exception;
    
    public boolean newEmailSave(String email, int id)throws Exception;
    
    public boolean checkEmail(String correo, int id)throws Exception;
}
