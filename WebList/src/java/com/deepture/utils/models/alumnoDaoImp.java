/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.models;

import com.deepture.utils.classdata.Inasistencias;
import com.deepture.utils.classdata.alumno;
import com.deepture.utils.methodInterface.alumnoDaoApi;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author JAFET
 */
public class alumnoDaoImp implements alumnoDaoApi {

    private DataSource connection;

    public alumnoDaoImp(DataSource connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(alumno al, String gr) throws Exception {
        Connection cn = connection.getConnection();
        //creamos la actualizacion preparada
        PreparedStatement ps = cn.prepareStatement("INSERT INTO alumno VALUES(?,?,?,?,?)");
        try {
            ps.setInt(1, al.getBoleta());
            ps.setString(2, al.getNombre());
            ps.setString(3, al.getApp());
            ps.setString(4, al.getApm());
            ps.setString(5, al.getCorreo());
            ps.executeUpdate();
            //ahora agregamos el grupo
            ps = cn.prepareStatement("INSERT INTO dalumno_grupo VALUES(?,?)");
            ps.setInt(1, al.getBoleta());
            ps.setString(2, gr);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ps.close();
            cn.close();
        }
        //si todo salió bien hasta este punto retornar un true
        return true;
    }

    @Override
    public boolean update(alumno al) throws Exception {
        Connection cn = connection.getConnection();
        //creamos la actualizacion preparada
        PreparedStatement ps = cn.prepareStatement(" UPDATE alumno SET nombre=?,app=?,apm=?,correo=? where boleta=?");
        try {
            ps.setString(1, al.getNombre());
            ps.setString(2, al.getApp());
            ps.setString(3, al.getApm());
            ps.setString(4, al.getCorreo());
            ps.setInt(5, al.getBoleta());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ps.close();
            cn.close();
        }
        //si todo salió bien hasta este punto retornar un true
        return true;
    }

    @Override
    public boolean delete(int boleta) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            //ahora lo eliminamos de su grupo
            ps = cn.prepareStatement("DELETE FROM dalumno_grupo where Alumno_Boleta= ?;");
            ps.setInt(1, boleta);
            ps.executeUpdate();

            ps = cn.prepareStatement("DELETE FROM alumno where boleta= ?;");
            ps.setInt(1, boleta);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ps.close();
            cn.close();
        }
        //si todo salió bien hasta este punto retornar un true
        return true;
    }

    @Override
    public List<alumno> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        List<alumno> alumnos = new ArrayList();
        Connection cn = connection.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM alumno");
            while (rs.next()) {
                int boleta = rs.getInt("Boleta");
                String nombres = rs.getString("Nombre");
                String app = rs.getString("App");
                String apm = rs.getString("Apm");
                String correo = rs.getString("correo");
                alumno al = new alumno(boleta, nombres, app, apm, correo);
                alumnos.add(al);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
            st.close();
        }
        return alumnos;
    }

    @Override
    public List<alumno> getStudentsFrom(List<alumno> alumnos, String gr) throws Exception {
        Connection cn = connection.getConnection();
        List<alumno> alumnosFromGroup = new ArrayList();
        List<alumno> auxiliar = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement("select * from dalumno_grupo WHERE CGrupo_id_grupo=?");
            //establecemos los parametros de consulta
            ps.setString(1, gr);
            rs = ps.executeQuery();
            while (rs.next()) {
                alumno alaux = new alumno();
                int bol = rs.getInt(1);
                alaux.setBoleta(bol);
                auxiliar.add(alaux);
            }
            int c = 0;
            for (alumno al : alumnos) {
                while (c < auxiliar.size()) {
                    if (al.getBoleta() == auxiliar.get(c).getBoleta()) {
                        alumnosFromGroup.add(al);
                        c++;
                    } else {
                        c++;
                    }
                }
                c = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
            ps.close();
        }
        return alumnosFromGroup;
    }

    @Override
    public boolean registryAssistance(String[] asistio, String mat, int idp, String gr, Date fecha, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //INSERT INTO inasistencias VALUES(78,"PSW",1234,"4IV7","2020-10-27 20:50:03","2020-10-27 20:50:03")
        PrintWriter out = response.getWriter();
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            //ahora lo eliminamos de su grupo
            ps = cn.prepareStatement("INSERT INTO inasistencias VALUES(?,?,?,?,?,?)");
            for (String as : asistio) {
                ps.setInt(1, Integer.parseInt(as));
                ps.setString(2, mat);
                ps.setInt(3, idp);
                ps.setString(4, gr);
                java.util.Date utilDate = fecha;
                java.sql.Date fechaconvertida = new java.sql.Date(utilDate.getTime());
                ps.setDate(5, fechaconvertida);
                ps.setDate(6, fechaconvertida);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
        //si todo salió bien hasta este punto retornar un true
    }

    @Override
    public List<Inasistencias> registryGet(int idp, String mat) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        List<Inasistencias> faltas = new ArrayList();
        ResultSet rs = null;
        try {
            LocalDate dia = LocalDate.now();
            ZonedDateTime inicioDia = ZonedDateTime.of(dia.atTime(0, 0), ZoneId.systemDefault());
            ZonedDateTime finDia = ZonedDateTime.of(dia.atTime(23, 59), ZoneId.systemDefault());

            ps = cn.prepareStatement("SELECT * FROM inasistencias WHERE id_maestro=? AND id_materia=? AND (dia BETWEEN ? AND ?)");
            ps.setInt(1, idp);
            ps.setString(2, mat);
            ps.setDate(3, new java.sql.Date(inicioDia.toInstant().getEpochSecond()));
            ps.setDate(4, new java.sql.Date(finDia.toInstant().getEpochSecond()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Inasistencias falta = new Inasistencias();
                falta.setBoleta(rs.getInt("boleta"));
                falta.setId_materia(rs.getString("id_materia"));
                falta.setId_maestro(rs.getInt("id_maestro"));
                falta.setGrupo(rs.getString("grupo"));
                Date hora = new Date(rs.getDate("dia").getTime());
                falta.setDia(hora);
                falta.setHora(hora);
                faltas.add(falta);
            }
            return faltas;
        } catch (Exception e) {
            return null;
        } finally {
            ps.close();
            cn.close();
        }
    }

    @Override
    public boolean registryDelete(Inasistencias inasistencia) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("DELETE FROM inasistencias WHERE boleta=? AND  id_maestro=? AND id_materia=? AND grupo=? AND dia=? LIMIT 1");
            ps.setInt(1, inasistencia.getBoleta());
            ps.setInt(2, inasistencia.getId_maestro());
            ps.setString(3, inasistencia.getId_materia());
            ps.setString(4, inasistencia.getGrupo());
            ps.setDate(5, new java.sql.Date(inasistencia.getDia().getTime()));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
    }

    @Override
    public boolean registryUpdate(Inasistencias OldAsistencia, Inasistencias NewAsistencia) throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement("UPDATE inasistencias SET boleta=?, id_maestro=?, id_materia=?, grupo=?, dia=?, hora=? WHERE boleta=? AND  id_maestro=? AND id_materia=? AND grupo=? AND dia=? LIMIT 1");
            ps.setInt(1, NewAsistencia.getBoleta());
            ps.setInt(2, NewAsistencia.getId_maestro());
            ps.setString(3, NewAsistencia.getId_materia());
            ps.setString(4, NewAsistencia.getGrupo());
            ps.setDate(5, new java.sql.Date(NewAsistencia.getDia().getTime()));
            ps.setDate(6, new java.sql.Date(NewAsistencia.getDia().getTime()));
            ps.setInt(7, OldAsistencia.getBoleta());
            ps.setInt(8, OldAsistencia.getId_maestro());
            ps.setString(9, OldAsistencia.getId_materia());
            ps.setString(10, OldAsistencia.getGrupo());
            ps.setDate(11, new java.sql.Date(OldAsistencia.getDia().getTime()));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            ps.close();
            cn.close();
        }
    }

}
