/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.controller;

import com.deepture.utils.classdata.Inasistencias;
import com.deepture.utils.classdata.alumno;
import com.deepture.utils.models.alumnoDaoImp;
import com.deepture.utils.validate.alumnoValidacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAFET
 */
@WebServlet(name = "CRUDalumno", urlPatterns = {"/CRUDalumno"})
public class CRUDalumno extends HttpServlet {

    private alumnoDaoImp model;

    @Resource(name = "jdbc/Data")
    private DataSource connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            model = new alumnoDaoImp(connection);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (request.getAttribute("load") == "t") {
            out.println("entra");
            try {
                List<alumno> alumnos = model.getAll(request, response);
                request.setAttribute("alumnosD", alumnos);
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            } catch (Exception ex) {
                out.println(ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String instruction = request.getParameter("instruction");
        if (request.getAttribute("load") == "t") {
            out.println("entra");
            try {
                List<alumno> alumnos = model.getAll(request, response);
                request.setAttribute("alumnosD", alumnos);
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            } catch (Exception ex) {
                out.println(ex);
            }
        } else {

            switch (instruction) {
                case "new":
               try {
                    String boleta = request.getParameter("boleta");
                    String names = request.getParameter("nombres");
                    String lastNF = request.getParameter("app");
                    String lastNM = request.getParameter("apm");
                    String email = "email@.com";
                    String group = request.getParameter("grupo");
                    if (group != "null" && boleta.length() > 0 && boleta.matches("^[0-9]+${1,10}+$") && names.length() > 0 && lastNF.length() > 0 && lastNM.length() > 0) {
                        alumno al = new alumno(Integer.parseInt(boleta), names, lastNF, lastNM, email);
                        newStudents(al, group, request, response);
                    } else {
                        request.setAttribute("code", "<script type=\"text/javascript\">\n"
                                + "                               alert('error, un dato es nulo, Recuerde elegir un grupo');\n"
                                + "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("code", "<script type=\"text/javascript\">\n"
                            + "                               alert('error, No debe ingresar letras o puntos decimales en la boleta');\n"
                            + "                               </script>");
                    //enviar ese request a la pagina jsp
                    RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                    disp.forward(request, response);
                    //debe de direccionar a una pagina de errores e imprimir que pasó
                    out.println(e);
                }
                break;
                case "save":
               try {
                    String boleta = request.getParameter("boleta");
                    String names = request.getParameter("nombres");
                    String lastNF = request.getParameter("app");
                    String lastNM = request.getParameter("apm");
                    String email = "email@.com";
                    String group = request.getParameter("grupo");
                    if (group != "null" && boleta.length() > 0 && boleta.matches("^[0-9]+${1,10}+$") && names.length() > 0 && lastNF.length() > 0 && lastNM.length() > 0) {
                        alumno al = new alumno(Integer.parseInt(boleta), names, lastNF, lastNM, email);
                        updateStudents(al, group, request, response);
                    } else {
                        request.setAttribute("code", "<script type=\"text/javascript\">\n"
                                + "                               alert('error, un dato es nulo, Recuerde elegir una instruccion (guardar como nuevo, guardar cambios, eliminar)');\n"
                                + "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("code", "<script type=\"text/javascript\">\n"
                            + "                               alert('error, No debe ingresar letras o puntos decimales en la boleta');\n"
                            + "                               </script>");
                    //enviar ese request a la pagina jsp
                    RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                    disp.forward(request, response);
                    //debe de direccionar a una pagina de errores e imprimir que pasó
                    out.println(e);
                }
                break;

                case "delete":
                    boolean aux = false;
                    int boleta = 0;
                    try {
                        boleta = Integer.parseInt(request.getParameter("boleta"));
                        aux = true;
                    } catch (Exception e) {
                        request.setAttribute("code", "<script type=\"text/javascript\">\n"
                                + "                               alert('error, No debe ingresar letras o puntos decimales en la boleta');\n"
                                + "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
                    if (aux) {
                        try {
                            deleteStudent(boleta, request, response);
                        } catch (Exception ex) {
                            //debe de direccionar a una pagina de errores e imprimir que pasó
                            out.println(ex);
                        }
                    } else {
                        //debe de direccionar a una pagina de errores e imprimir que pasó
                        out.println("Ha ocurrido un error desconocido");
                    }

                    break;
                case "4IV7": {
                    try {
                        allStudentsFrom(request, response);
                    } catch (Exception ex) {
                        out.println(ex);
                    }
                }
                break;
                case "4IV8":
               try {
                    allStudentsFrom(request, response);
                } catch (Exception ex) {
                    out.println(ex);
                }
                break;
                case "4IV9":
               try {
                    allStudentsFrom(request, response);
                } catch (Exception ex) {
                    out.println(ex);
                }
                break;
                case "saveAs": {
                    try {
                        passAssistance(request, response);
                    } catch (Exception ex) {
                        out.println(ex);
                    }
                }
                break;
                case "getFalt": {
                    try {
                        getAssistance(request, response);
                    } catch (Exception ex) {
                        out.println(ex);
                    }
                }
                break;
                case "eliminarFal": {
                    try {
                        delAssistance(request, response);
                    } catch (Exception ex) {
                        out.println(ex);
                    }
                }
                break;
                case "añadirFal": {
                    try {
                        addAssistance(request, response);
                    } catch (Exception ex) {
                        out.println(ex);
                    }
                }
                break;
                default:
                    request.setAttribute("code", "<script type=\"text/javascript\">\n"
                            + "                               alert('error, Debe elegir una accion');\n"
                            + "                               </script>");
                    //enviar ese request a la pagina jsp
                    RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
                    disp.forward(request, response);
                    break;
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //metodo para agregar nuevo alumno
    private void newStudents(alumno al, String gr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoValidacion val = new alumnoValidacion();
        //mandamos los datos a validacion para saber si son correctos
        String exito = val.newStudentsValidate(al, gr);
        if (exito == "ok") {
            //si son correctos entonces guardamos en la bbdd
            boolean sa = model.create(al, gr);
            if (sa) {
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('" + al.getBoleta() + " se guardó con exito');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            }
        } else {
            request.setAttribute("code", "<script type=\"text/javascript\">\n"
                    + "                               alert('No inserto un dato bien');\n"
                    + "                               </script>");
            //enviar ese request a la pagina jsp
            RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
            disp.forward(request, response);
            //se debe imprimir en la vista el error
        }
    }

    private void updateStudents(alumno al, String gr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //utilizamos la misma validacion que en el create
        alumnoValidacion val = new alumnoValidacion();
        String exito = val.newStudentsValidate(al, gr);
        if (exito == "ok") {
            //ya que se comprobó que los datos son correctos los guardamos en la bd
            boolean sa = model.update(al);
            if (sa) {
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('" + al.getBoleta() + " se actualizó con exito');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('No inserto un dato bien');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
                //se debe imprimir en la vista el error
            }
        } else {
            //se debe imprimir en la vista el error
        }
    }

    private void deleteStudent(int boleta, HttpServletRequest request, HttpServletResponse response) throws Exception {
        alumnoValidacion val = new alumnoValidacion();
        String aux = val.validarBoleta(boleta);
        if (aux == "ok") {
            boolean sa = model.delete(boleta);
            if (sa) {
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('" + boleta + " se eliminó con exito');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('No inserto un dato bien');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
                //se debe imprimir en la vista el error
            }
        } else {
            //se debe imprimir en la vista el error
        }
    }

    private void allStudentsFrom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        List<alumno> alumnos = model.getAll(request, response);
        List<alumno> alFromGr = model.getStudentsFrom(alumnos, request.getParameter("instruction"));
        /*for(alumno a : alFromGr){
            out.println(a.getBoleta());
        }*/
        if (alFromGr != null) {
            request.setAttribute("id_profe", request.getParameter("idpr"));
            request.setAttribute("alumnosD", alFromGr);
            //enviar ese request a la pagina jsp
            request.setAttribute("gr", request.getParameter("instruction"));
            RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
            disp.forward(request, response);
        } else {
            request.setAttribute("code", "<script type=\"text/javascript\">\n"
                    + "                               alert('Error, ha ocurrido un error interno');\n"
                    + "                               </script>");
            //enviar ese request a la pagina jsp
            RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
            disp.forward(request, response);
        }
    }

    private void passAssistance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        String[] asistio = request.getParameterValues("asistencia");
        //INSERT INTO inasistencias VALUES(78,"PSW",1234,"4IV7","2020-10-27 20:50:03","2020-10-27 20:50:03")
        /*for(String a: asistio){
            out.println(a);
        }
        out.println("----------------");
        java.util.Date fecha = new Date();
        out.println(request.getParameter("materia"));
        out.println(request.getParameter("idP"));
        out.println(request.getParameter("gr"));
        out.println(fecha);*/
        //metemos la asistencia dentro de la bbdd
        try {
            java.util.Date fecha = new Date();
            String mat = request.getParameter("materia");
            int idp = Integer.parseInt(request.getParameter("idP"));
            String gr = request.getParameter("gr");

            boolean exito = model.registryAssistance(asistio, mat, idp, gr, fecha, request, response);
            if (exito) {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Exito, se guardó la asistencia');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Error, ha ocurrido un error interno, asegurese de seleccionar un grupo, materia y alumnos');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            }
        } catch (Exception e) {
            out.println(e);
        }

    }

    private void getAssistance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<alumno> alumnos = model.getAll(request, response);
        List<alumno> alFromGr = model.getStudentsFrom(alumnos, request.getParameter("gr"));
        int idp = Integer.parseInt(request.getParameter("idpr"));
        List<Inasistencias> faltas = model.registryGet(idp, request.getParameter("materia"), request.getParameter("gr"));
        if (faltas != null || alumnos != null || alFromGr != null) {
            if (!faltas.isEmpty()) {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("faltas", faltas);
                request.setAttribute("faltados", alFromGr);
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Informacion del sistema, No se tienen registros de inasistencia actualmente');\n"
                        + "                               </script>");
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            }
        } else {
            request.setAttribute("id_profe", idp);
            request.setAttribute("gr", request.getParameter("gr"));
            request.setAttribute("code", "<script type=\"text/javascript\">\n"
                    + "                               alert('Error, ha ocurrido un error interno, asegurese de seleccionar un grupo, materia y alumnos');\n"
                    + "                               </script>");
            RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
            disp.forward(request, response);
        }

    }

    private void delAssistance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Inasistencias falta = new Inasistencias();
        int idp = Integer.parseInt(request.getParameter("idpr"));
        try {
            falta.setGrupo(request.getParameter("gr"));
            falta.setId_maestro(idp);
            falta.setId_materia(request.getParameter("materia"));
            falta.setBoleta(Integer.parseInt(request.getParameter("boleta")));
            String hoy = request.getParameter("dia");
            java.sql.Date dia = java.sql.Date.valueOf(hoy);
            falta.setDia(dia);
            boolean exito = model.registryDelete(falta);
            if (exito) {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Exito, se elimino la assistencia');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Error, ha ocurrido un error interno, asegurese de seleccionar un grupo, materia y alumnos');\n"
                        + "                               </script>");
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("id_profe", idp);
            request.setAttribute("gr", request.getParameter("gr"));
            request.setAttribute("code", "<script type=\"text/javascript\">\n"
                    + "                               alert('Error, ha ocurrido un error interno, asegurese de seleccionar un grupo, materia y alumnos');\n"
                    + "                               </script>");
            RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
            disp.forward(request, response);
        }
    }

    private void addAssistance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Inasistencias falta = new Inasistencias();
        int idp = Integer.parseInt(request.getParameter("idpr"));
        try {
            falta.setGrupo(request.getParameter("gr"));
            falta.setId_maestro(idp);
            falta.setId_materia(request.getParameter("materia"));
            falta.setBoleta(Integer.parseInt(request.getParameter("boleta")));
            String hoy = request.getParameter("dia");
            java.sql.Date dia = java.sql.Date.valueOf(hoy);
            falta.setDia(dia);
            boolean exito = model.registryUpdate(falta);
            if (exito) {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Exito, se registro la inasistencia.');\n"
                        + "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            } else {
                request.setAttribute("id_profe", idp);
                request.setAttribute("gr", request.getParameter("gr"));
                request.setAttribute("code", "<script type=\"text/javascript\">\n"
                        + "                               alert('Error, La Boleta no es valida, intentelo nuevamente.');\n"
                        + "                               </script>");
                RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
                disp.forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("id_profe", idp);
            request.setAttribute("gr", request.getParameter("gr"));
            request.setAttribute("code", "<script type=\"text/javascript\">\n"
                    + "                               alert('Error, ha ocurrido un error interno, asegurese de seleccionar un grupo, materia y alumnos');\n"
                    + "                               </script>");
            RequestDispatcher disp = request.getRequestDispatcher("/Home.jsp");
            disp.forward(request, response);
        }
    }

}
