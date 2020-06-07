/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.validate;

import com.deepture.utils.validate.Dao.adminValdao;

/**
 *
 * @author JAFET
 */
public class adminValidate implements adminValdao{

    @Override
    public boolean passwordAndIdValidate(String ps1, String ps2, int id)throws Exception{
        String vps1=ps1;
        String vps2=ps2;
        String vid =String.valueOf(id);
        if(vps1.matches("^[a-zA-Z0-9]+${1,44}") && vps2.matches("^[a-zA-Z0-9]+${1,44}") && vid.matches("^[0-9]+${1,44}+$")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean newEmailValidate(String email, int id) {
        return true;
    }
    
}
