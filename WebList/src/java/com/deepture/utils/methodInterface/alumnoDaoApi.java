/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.methodInterface;

import com.deepture.utils.classdata.Inasistencias;
import com.deepture.utils.classdata.alumno;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JAFET
 */
public interface alumnoDaoApi {

    boolean create(alumno al, String gro) throws Exception;

    boolean update(alumno al) throws Exception;

    boolean delete(int boleta) throws Exception;
    
    boolean logIn(alumno al)throws Exception;
    
    List<Inasistencias> getIndividual(alumno al)throws Exception;

    List<alumno> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public List<alumno> getStudentsFrom(List<alumno> alumnos, String parameter) throws Exception;

    public boolean registryAssistance(String[] asistio, String mat, int idp, String gr, Date fecha, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public List<Inasistencias> registryGet(int idp, String mat,String gr) throws Exception;

    public boolean registryDelete(Inasistencias inasistencia) throws Exception;

    public boolean registryUpdate(Inasistencias inasistencia) throws Exception;
}
