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
            "                               alert('error, un dato es nulo, Recuerde elegir una instruccion (guardar como nuevo, guardar cambios, eliminar)');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                    }
               }catch(Exception e){
                   out.println(e);
               }
            break;
           case "save":
           break;
           
           case "delete":
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
    "                               alert('"+al.getBoleta()+"');\n" +
    "                               </script>");
                //enviar ese request a la pagina jsp
                RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                disp.forward(request, response);
            }
        }else{
            //se debe imprimir en la vista el error
        }
    }

}
