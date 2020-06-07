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
        return true;
    }

    @Override
    public boolean newEmailValidate(String email, int id) {
        return true;
    }
    
}
