/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.models;

import com.deepture.utils.classdata.administrador;
import com.deepture.utils.methodInterface.adminDaoApi;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author JAFET
 */
public class adminDaoImp implements adminDaoApi{
    private DataSource connection;

    public adminDaoImp(DataSource connection) {
        this.connection = connection;
    }
    
    
    @Override//SELECT IF("pass"='pass', "true", "false");
    public boolean logIn(administrador admin,HttpServletResponse response) throws Exception {
        PrintWriter out=response.getWriter();
       Connection cn = connection.getConnection();
       PreparedStatement ps = null;
       ResultSet rs=null;

           ps = cn.prepareStatement("SELECT contraseña FROM administradores WHERE id_admin = ?");
           ps.setInt(1,admin.getId());
           rs = ps.executeQuery();
           if(rs.next()){
                String pass = rs.getString(1);
                out.println(pass);
                out.println(admin.getPassword());
                if(pass.equals(admin.getPassword())){
                    return true;
                }else{
                    return false;
                }
           }else{
               return false;
           }

    }
    
}
