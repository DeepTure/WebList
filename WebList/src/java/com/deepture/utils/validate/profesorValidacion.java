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
        String vnume=String.valueOf(profe.getNumeroE());
        String vnomb=profe.getNombres();
        String vappa=profe.getApp();
        String vapma=profe.getApm();
        String vpass=profe.getPassword();
        if(vnume.matches("^[0-9]+${1,10}+$") && vnomb.matches("^[A-Za-z\\s]+${1,44}") && vappa.matches("^[a-zA-Z]+${1,44}") && vapma.matches("^[a-zA-Z]+${1,44}") && vpass.matches("^[a-zA-Z0-9]+${1,44}")){
            return true;
        }else{
            return false;
        }
        
    }

    @Override
    public boolean numberEmployeeValidate(int number) {
        String vnumb=String.valueOf(number);
        if(vnumb.matches("^[0-9]+${1,10}+$")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean logInValidate(profesor profe){
        String vnume=String.valueOf(profe.getNumeroE());
        String vnomb=profe.getNombres();
        String vappa=profe.getApp();
        String vapma=profe.getApm();
        String vpass=profe.getPassword();
        if(vnume.matches("^[0-9]+${1,10}+$") &&  vpass.matches("^[a-zA-Z0-9]+${1,44}")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean passwordAndIdValidate(String ps1, String ps2, int id) {
        String vps1=ps1;
        String vps2=ps2;
        String vid =String.valueOf(id);
        if(vps1.matches("^[a-zA-Z0-9]+${1,44}") && vps2.matches("^[a-zA-Z0-9]+${1,44}") && vid.matches("^[0-9]+${1,44}+$")){
            return true;
        }else{
            return false;
        }
    }

    public boolean newEmailValidate(String email, int id) {
        return true;
    }
    
}
