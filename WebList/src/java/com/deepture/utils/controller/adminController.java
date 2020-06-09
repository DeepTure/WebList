/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deepture.utils.controller;

import com.deepture.utils.validate.adminValidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@WebServlet(name = "adminController", urlPatterns = {"/adminController"})
public class adminController extends HttpServlet {
    
    @Resource(name="jdbc/Data")
    private DataSource connection;

    
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
           case "changePassword":
               try {
                    changePassword(request, response);
                } catch (Exception ex) {
                    out.println(ex);
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha introducido datos erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
                }
            case "newEmail":
            {
                try {
                    newEmail(request,response);
                } catch (Exception ex) {
                   out.println(ex);
                   request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha introducido datos erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/Admin.jsp");
                            disp.forward(request, response);
                }
            }
           break;
            default:
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('La contraseña se ha guardado de forma exitosa');\n" +
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

    private void changePassword(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String ps1 = request.getParameter("pass");
        String ps2 = request.getParameter("pass2");
        int id = Integer.parseInt(request.getParameter("id"));
        adminValidate val = new adminValidate();
        
        boolean itsok = val.passwordAndIdValidate(ps1, ps2, id);
        if(itsok){
            if(ps1.equals(ps2)){
                boolean exito=save(ps1,id);
                if(exito){
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('La contraseña se ha guardado de forma exitosa');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
                }else{
                    request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha ocurrido un error interno');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
                }
            }else{
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Las contraseñas no coinciden');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
            }
        }else{
            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Los datos ingresados son erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
        }
    }

    private boolean save(String ps1, int id)throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = cn.prepareStatement("UPDATE administradores SET contraseña=? WHERE id_admin=?");
            ps.setString(1,ps1);
            ps.setInt(2,id);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            cn.close();
            ps.close();
        }
    }

    private void newEmail(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("id"));
        //validacion
        adminValidate val = new adminValidate();
        boolean itsok = val.newEmailValidate(email,id);
        if(itsok){
            boolean exito = newEmailSave(email,id);
            if(exito){
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Se ha guardado exitosamente');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
            }else{
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha ocurrido un error interno');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
            }
        }else{
            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Los datos ingresados son erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/HomeAdmin.jsp");
                            disp.forward(request, response);
        }
    }

    private boolean newEmailSave(String email, int id)throws Exception {
        Connection cn = connection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = cn.prepareStatement("UPDATE administradores SET correo=? WHERE id_admin=?");
            ps.setString(1,email);
            ps.setInt(2,id);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            cn.close();
            ps.close();
        }
    }
}
