/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.models;

import com.deepture.utils.classdata.alumno;
import com.deepture.utils.methodInterface.alumnoDaoApi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;

/**
 *
 * @author JAFET
 */
public class alumnoDaoImp implements alumnoDaoApi{
    private DataSource connection;

    public alumnoDaoImp(DataSource connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(alumno al, String gr)throws Exception{
        Connection cn = connection.getConnection();
        //creamos la actualizacion preparada
        PreparedStatement ps = cn.prepareStatement("INSERT INTO alumno VALUES(?,?,?,?,?)");
        ps.setInt(1,al.getBoleta());
        ps.setString(2,al.getNombre());
        ps.setString(3,al.getApp());
        ps.setString(4,al.getApm());
        ps.setString(5,al.getCorreo());
        ps.executeUpdate();
        //ahora agregamos el grupo
        ps = cn.prepareStatement("INSERT INTO dalumno_grupo VALUES(?,?)");
        ps.setInt(1,al.getBoleta());
        ps.setString(2,gr);
        ps.executeUpdate();
        ps.close();
        //si todo salió bien hasta este punto retornar un true
        return true;
    }

    @Override
    public boolean update(alumno al) throws Exception {
        Connection cn = connection.getConnection();
        //creamos la actualizacion preparada
        PreparedStatement ps = cn.prepareStatement(" UPDATE alumno SET nombre=?,app=?,apm=?,correo=? where boleta=?");
        ps.setString(1,al.getNombre());
        ps.setString(2,al.getApp());
        ps.setString(3,al.getApm());
        ps.setString(4,al.getCorreo());
        ps.setInt(5,al.getBoleta());
        ps.executeUpdate();
        ps.close();
        //si todo salió bien hasta este punto retornar un true
        return true;
    }

    @Override
    public boolean delete(int boleta)throws Exception{
        Connection cn = connection.getConnection();
        PreparedStatement ps;
        //ahora lo eliminamos de su grupo
        ps = cn.prepareStatement("DELETE FROM dalumno_grupo where Alumno_Boleta= ?;");
        ps.setInt(1,boleta);
        ps.executeUpdate();
        
        ps = cn.prepareStatement("DELETE FROM alumno where boleta= ?;");
        ps.setInt(1,boleta);
        ps.executeUpdate();
        ps.close();
        //si todo salió bien hasta este punto retornar un true
        return true;
    }
}
