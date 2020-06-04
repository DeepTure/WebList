/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.methodInterface;

import com.deepture.utils.classdata.administrador;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JAFET
 */
public interface adminDaoApi {
    boolean logIn(administrador admin, HttpServletResponse response)throws Exception;
}
