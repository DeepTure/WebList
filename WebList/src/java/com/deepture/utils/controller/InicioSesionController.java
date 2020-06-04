/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.controller;

import com.deepture.utils.classdata.administrador;
import com.deepture.utils.classdata.profesor;
import com.deepture.utils.models.adminDaoImp;
import com.deepture.utils.models.profesorDaoImp;
import com.deepture.utils.validate.profesorValidacion;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "InicioSesionController", urlPatterns = {"/InicioSesionController"})
public class InicioSesionController extends HttpServlet {
    private profesorDaoImp model;
    private adminDaoImp model2;
   
    @Resource(name="jdbc/Data")
    private DataSource connection;

    @Override
    public void init() throws ServletException {
        super.init(); 
        try{
            model2 = new adminDaoImp(connection);
            model = new profesorDaoImp(connection);
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out=response.getWriter();
            int id=0;
            String pass="";
            try{
                 id = Integer.parseInt(request.getParameter("user"));
                 pass = request.getParameter("pass");
            }catch(Exception e){
                out.println(e);
            }
            profesorValidacion val = new profesorValidacion();
            administrador admin = new administrador(id,pass);
            profesor profe = new profesor(id,pass);
            //validamos las entradas antes de que lleguen a la bbdd+
            if(!val.logInValidate(profe)){
                     request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Sin acceso, los datos son erroneos');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
            }
            //este metodo de validacion se encuntra declarado en este servlet
            if(!adminLoginValidate(admin)){
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Sin acceso, los datos son erroneos');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
            }
            
            //si los datos son correctos entonces
        try {
            if(model2.logIn(admin, response)){
                request.setAttribute("load","t");
                RequestDispatcher disp=request.getRequestDispatcher("/CRUDalumno");
                disp.forward(request, response);
            }else if(model.logIn(profe)){
                        RequestDispatcher disp=request.getRequestDispatcher("/Home.jsp");
                        disp.forward(request, response);
            }else{
                 request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Sin acceso');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
            }
        } catch (Exception ex) {
            out.println(ex);
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean adminLoginValidate(administrador admin) {
        return true;
    }

}
