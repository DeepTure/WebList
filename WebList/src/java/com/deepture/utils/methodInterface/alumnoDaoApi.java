/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.methodInterface;

import com.deepture.utils.classdata.alumno;

/**
 *
 * @author JAFET
 */
public interface alumnoDaoApi {
    
    boolean create(alumno al,String gro)throws Exception;
    
    boolean update(alumno al)throws Exception;
    
    boolean delete(int boleta)throws Exception;
}
