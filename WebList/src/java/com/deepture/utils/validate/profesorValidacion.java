/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.validate;

import com.deepture.utils.classdata.profesor;
import com.deepture.utils.validate.Dao.profesorValDao;

/**
 *
 * @author JAFET
 */
public class profesorValidacion implements profesorValDao{
//  TODOS LOS METODOS DEBEN RETORNAR UN TRUE EN CASO DE QUE LOS DATOS SEAN CORRECTOS
    //si lso metodos no tienen throws usar try catch para el control de errores
    @Override
    public boolean newTeacherValidate(profesor profe) throws Exception {
        return true;
    }

    @Override
    public boolean numberEmployeeValidate(int number) {
        return true;
    }

    @Override
    public boolean logInValidate(profesor profe){
        return true;
    }
    
}
