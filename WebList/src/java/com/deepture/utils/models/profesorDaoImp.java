/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.models;

import com.deepture.utils.classdata.profesor;
import com.deepture.utils.methodInterface.profesorDaoApi;
import java.sql.*;
import javax.sql.DataSource;

/**
 *
 * @author JAFET
 */
public class profesorDaoImp implements profesorDaoApi {

    private DataSource connection;

    public profesorDaoImp(DataSource connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(profesor profe) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("INSERT INTO profesores VALUES(?,?,?,?,?,?)");
            ps.setInt(1, profe.getNumeroE());
            ps.setString(2, profe.getNombres());
            ps.setString(3, profe.getApp());
            ps.setString(4, profe.getApm());
            ps.setString(5, profe.getPassword());
            ps.setString(6, profe.getCorreo());
            ps.executeUpdate();
            //metemos las materias que da 
            String[] materias = profe.getMaterias();
            for (int i = 0; i < materias.length; i++) {
                ps = cn.prepareStatement("INSERT INTO dprofesores_materia VALUES(?,?)");
                ps.setInt(1, profe.getNumeroE());
                ps.setString(2, materias[i]);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
    }

    @Override
    public boolean update(profesor profe) throws Exception {
        //en esto hay fallos de seguridad, de los que nos tenemos que encargar despues, lo importante es terminar el proyecto
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("UPDATE profesores SET nombre=?,app=?,apm=?,contraseña=?,correo=? WHERE id_profesor=?");
            ps.setString(1, profe.getNombres());
            ps.setString(2, profe.getApp());
            ps.setString(3, profe.getApm());
            ps.setString(4, profe.getPassword());
            ps.setString(5, profe.getCorreo());
            ps.setInt(6, profe.getNumeroE());
            ps.executeUpdate();
            //antes de agregar las nuevas materias las borramos 
            ps = cn.prepareStatement("DELETE FROM dprofesores_materia WHERE Profesores_id_profesor=?");
            ps.setInt(1, profe.getNumeroE());
            ps.executeUpdate();
            //metemos las nuevas materias
            String[] materias = profe.getMaterias();
            for (int i = 0; i < materias.length; i++) {
                ps = cn.prepareStatement("INSERT INTO dprofesores_materia VALUES(?,?)");
                ps.setInt(1, profe.getNumeroE());
                ps.setString(2, materias[i]);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
    }

    @Override
    public boolean delete(int numberE) throws Exception {
        //en esto hay fallos de seguridad, de los que nos tenemos que encargar despues, lo importante es terminar el proyecto
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("DELETE FROM dprofesores_materia WHERE  Profesores_id_profesor=?");
            ps.setInt(1, numberE);
            ps.executeUpdate();
            //si todo sale bien entonces borramos la tabla maestra
            ps = cn.prepareStatement("DELETE FROM profesores WHERE  id_profesor=?;");
            ps.setInt(1, numberE);
            ps.executeUpdate();
            //si todo sale bien entonces
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
    }

    @Override
    public boolean logIn(profesor profe) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cn.prepareStatement("SELECT contraseña FROM profesores WHERE id_profesor = ?");
            ps.setInt(1, profe.getNumeroE());
            rs = ps.executeQuery();
            if (rs.next()) {
                String pass = rs.getString(1);
                if (pass.equals(profe.getPassword())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cn.close();
            ps.close();
        }
    }

    @Override
    public boolean chPsswrd(String ps1, String ps2, int id) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("UPDATE profesores SET contraseña=? WHERE id_profesor=?");
            ps.setString(1, ps1);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            cn.close();
            ps.close();
        }
    }

    @Override
    public boolean newEmailSave(String email, int id) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("UPDATE profesores SET correo=? WHERE id_profesor=?");
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            cn.close();
            ps.close();
        }
    }

    @Override
    public boolean checkEmail(String correo, int id) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement("SELECT correo FROM profesores WHERE id_profesor=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(correo)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cn.close();
            ps.close();
        }
    }

}
