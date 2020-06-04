/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.controller;

import com.deepture.utils.classdata.alumno;
import com.deepture.utils.models.alumnoDaoImp;
import com.deepture.utils.validate.alumnoValidacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author JAFET
 */
@WebServlet(name = "CRUDalumno", urlPatterns = {"/CRUDalumno"})
public class CRUDalumno extends HttpServlet {
    
    private alumnoDaoImp model;
    
    @Resource(name="jdbc/Data")
    private DataSource connection;

    @Override
    public void init() throws ServletException {
        super.init(); 
        try{
            model = new alumnoDaoImp(connection);
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out=response.getWriter();
       String instruction = request.getParameter("instruction");
       if(request.getAttribute("load")=="t"){
           out.println("entra");
                try {
                    List<alumno> alumnos=model.getAll(request, response);
                    request.setAttribute("alumnosD", alumnos);
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                } catch (Exception ex) {
                    out.println(ex);
                }
       }else{

       switch(instruction){
           case "new":
               try{
                    String boleta=request.getParameter("boleta");
                    String names = request.getParameter("nombres");
                    String lastNF = request.getParameter("app");
                    String lastNM = request.getParameter("apm");
                    String email = "email@.com";
                    String group = request.getParameter("grupo");
                    if(group!="null"){
                        alumno al = new alumno(Integer.parseInt(boleta),names,lastNF,lastNM,email);
                        newStudents(al, group, request, response);
                    }else{
                        request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, un dato es nulo, Recuerde elegir un grupo');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
               }catch(Exception e){
                   //debe de direccionar a una pagina de errores e imprimir que pasó
                   out.println(e);
               }
            break;
           case "save":
               try{
                   String boleta=request.getParameter("boleta");
                    String names = request.getParameter("nombres");
                    String lastNF = request.getParameter("app");
                    String lastNM = request.getParameter("apm");
                    String email = "email@.com";
                    String group = request.getParameter("grupo");
                    if(group!="null"){
                        alumno al = new alumno(Integer.parseInt(boleta),names,lastNF,lastNM,email);
                        updateStudents(al, group, request, response);
                    }else{
                        request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, un dato es nulo, Recuerde elegir una instruccion (guardar como nuevo, guardar cambios, eliminar)');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
               }catch(Exception e){
                   //debe de direccionar a una pagina de errores e imprimir que pasó
                   out.println(e);
               }
           break;
           
           case "delete":
               boolean aux=false;
               int boleta=0;
                try{
                    boleta = Integer.parseInt(request.getParameter("boleta"));
                    aux = true;
                }catch(Exception e){
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, No debe ingresar letras o puntos decimales en la boleta');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                }
                if(aux){
                    try {
                        deleteStudent(boleta,request, response);
                    } catch (Exception ex) {
                        //debe de direccionar a una pagina de errores e imprimir que pasó
                        out.println(ex);
                    }
                }else{
                    //debe de direccionar a una pagina de errores e imprimir que pasó
                    out.println("Ha ocurrido un error desconocido");
                }
               
           break;
           
           default:
               request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, Debe elegir una accion');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
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
    private void newStudents(alumno al, String gr, HttpServletRequest request, HttpServletResponse response) throws Exception{
        alumnoValidacion val=new alumnoValidacion();
        //mandamos los datos a validacion para saber si son correctos
        String exito = val.newStudentsValidate(al, gr);
        if(exito == "ok"){
            //si son correctos entonces guardamos en la bbdd
            boolean sa = model.create(al, gr);
            if (sa){
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
    "                               alert('"+al.getBoleta()+" se guardó con exito');\n" +
    "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            }
        }else{
            //se debe imprimir en la vista el error
        }
    }

    private void updateStudents(alumno al, String gr, HttpServletRequest request, HttpServletResponse response)throws Exception {
        //utilizamos la misma validacion que en el create
        alumnoValidacion val=new alumnoValidacion();
        String exito = val.newStudentsValidate(al, gr);
        if(exito == "ok"){
            //ya que se comprobó que los datos son correctos los guardamos en la bd
            boolean sa = model.update(al);
            if(sa){
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
    "                               alert('"+al.getBoleta()+" se actualizó con exito');\n" +
    "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            }else{
                //se debe imprimir en la vista el error
            }
        }else{
            //se debe imprimir en la vista el error
        }
    }

    private void deleteStudent(int boleta, HttpServletRequest request, HttpServletResponse response)throws Exception {
        alumnoValidacion val=new alumnoValidacion();
        String aux=val.validarBoleta(boleta);
        if(aux=="ok"){
            boolean sa = model.delete(boleta);
            if(sa){
                //agregar los datos del usuario al request
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
    "                               alert('"+boleta+" se eliminó con exito');\n" +
    "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            }else{
                //se debe imprimir en la vista el error
            }
        }else{
            //se debe imprimir en la vista el error
        }
    }

}
