/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.controller;

import com.deepture.utils.classdata.profesor;
import com.deepture.utils.models.alumnoDaoImp;
import com.deepture.utils.models.profesorDaoImp;
import com.deepture.utils.validate.profesorValidacion;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CRUDprofesor", urlPatterns = {"/CRUDprofesor"})
public class CRUDprofesor extends HttpServlet {
    private profesorDaoImp model;
    
    @Resource(name="jdbc/Data")
    private DataSource connection;

    @Override
    public void init() throws ServletException {
        super.init(); 
        try{
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
       String instruction = request.getParameter("instruction");
       switch(instruction){
           case "new": 
               int number=0;
               try{
                    number = Integer.parseInt(request.getParameter("nempleado"));
               }catch(Exception e){
                   request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe ingresar numeros en el numero de empleado, y no excsesivamente grandes');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
               }
               String names = request.getParameter("nombres");
               String lastF = request.getParameter("app");
               String lastM = request.getParameter("apm");
               String pass = "root";
               String email = "email@.com";
               String[] subjects = null;
               //esto es para obtener las materias
               try{
                subjects = request.getParameterValues("ch");
               }catch(Exception e){
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe elegir al menos una materia');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
               }
               try{
                   if(subjects.length<=0 || subjects==null){
                        request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe elegir al menos una materia');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                   }else{
                       profesor profe=new profesor(number,names,lastF,lastM,pass,email,subjects);
                       newTeacher(profe,request,response);
                   }
               }catch(Exception e){
                   out.println(e);
               }
            break;
           case "save":
               int numberu = 0;
               try{
                    numberu = Integer.parseInt(request.getParameter("nempleado"));
               }catch(Exception e){
                   request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe ingresar numeros en el numero de empleado, y no excsesivamente grandes');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
               }
               String namesu = request.getParameter("nombres");
               String lastFu = request.getParameter("app");
               String lastMu = request.getParameter("apm");
               String passu = "root";
               String emailu = "email@.com";
               String[] subjectsu = null;
               //esto es para obtener las materias
               try{
                subjectsu = request.getParameterValues("ch");
               }catch(Exception e){
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe elegir al menos una materia');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
               }
               try{
                   if(subjectsu.length<=0 || subjectsu==null){
                        request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe elegir al menos una materia');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                   }else{
                       profesor profe=new profesor(numberu,namesu,lastFu,lastMu,passu,emailu,subjectsu);
                       updateTeacher(profe,request,response);
                   }
               }catch(Exception e){
                   out.println(e);
               }
            break;

           case "delete":
               try{
                   int numberd = Integer.parseInt(request.getParameter("nempleado"));
                   if(numberd<=100){
                       request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe ingresar numeros correctos');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
                   }else{
                       deleteTeacher(numberd, request, response);
                   }
                          
               }catch(Exception e){
                   request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, debe ingresar numeros en el numero de empleado, y no excsesivamente grandes');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
               }

       }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void newTeacher(profesor profe, HttpServletRequest request, HttpServletResponse response)throws Exception {
        PrintWriter out=response.getWriter();
        profesorValidacion val = new profesorValidacion();
        boolean exito = val.newTeacherValidate(profe);
        if(true){
            boolean sa = model.create(profe);
            if(sa){
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('"+profe.getNumeroE()+" Se ha guardado con exito');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }else{
                //imprimir el error
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error, Probablemente el numero de empleado ya esta registrado');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }
        }else{
            //imprimir el error en la validacion
        }
    }

    private void updateTeacher(profesor profe, HttpServletRequest request, HttpServletResponse response)throws Exception {
        PrintWriter out=response.getWriter();
        profesorValidacion val = new profesorValidacion();
        boolean exito = val.newTeacherValidate(profe);
        if(true){
            boolean sa = model.update(profe);
            if(sa){
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('"+profe.getNumeroE()+" Se ha guardado con exito');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }else{
                //imprimir el error
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('error,Ha ocurrido un error al intentar guardar los datos');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }
        }else{
            //imprimir el error en la validacion
        }
    }

    private void deleteTeacher(int number, HttpServletRequest request, HttpServletResponse response)throws Exception {
        PrintWriter out=response.getWriter();
        profesorValidacion val = new profesorValidacion();
        boolean exito = val.numberEmployeeValidate(number);
        if(exito){
            boolean sa = model.delete(number);
            if(sa){
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Se ha borrado con exito');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }else{
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Ha ocurrido un error, probablemete no existe el el numero de empleado');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                        disp.forward(request, response);
            }
        }else{
            //impirmir el error
        }
    }

}
