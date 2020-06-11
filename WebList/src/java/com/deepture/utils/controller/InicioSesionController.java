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
import com.deepture.utils.validate.adminValidate;
import com.deepture.utils.validate.profesorValidacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    //esta variable tendrá el codigo para entarar de nuevo a su cuenta
    private static String code = null;
   
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
            PrintWriter out=response.getWriter();
            String ins = request.getParameter("instruction");
            switch(ins){
                case "recover":
                    //estos datos se deben validar de manera general, tanto para el profesor como para el admnistrador
                    //yo instanciaré la clase administrador pero validar para ambos
                    adminValidate val = new adminValidate();
                    boolean exito = val.recoverPassword(request.getParameter("correo"),request.getParameter("id"));
                    //si tiene exito
                    if(exito){
                        try {
                            sendEmail(request,response);
                        } catch (Exception ex) {
                            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha ocurrido un error desconocido');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                            disp.forward(request, response);
                            
                            ex.printStackTrace();
                        }
                    }else{
                        request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha introducido datos erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                            disp.forward(request, response);
                    }
                case "recoverWithCode":
                    String codeOriginal=request.getParameter("codeO"),codeUser=request.getParameter("codeU");
                    String status = request.getParameter("status");
                    try{
                        if(codeOriginal.equals(codeUser) && status.equals("profesor")){
                            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                    "                               alert('Bienvenido, se le recomienda cambie su contraseña');\n" +
                    "                               </script>");
                                //enviar ese request a la pagina jsp
                                RequestDispatcher disp=request.getRequestDispatcher("/Home.jsp");
                                disp.forward(request, response);
                        }else if(codeOriginal.equals(codeUser) && status.equals("administrador")){
                            request.setAttribute("load","t");
                            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                    "                               alert('Bienvenido, se le recomienda cambie su contraseña');\n" +
                    "                               </script>");
                                RequestDispatcher disp=request.getRequestDispatcher("/CRUDalumno");
                                disp.forward(request, response);
                        }else{
                            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                    "                               alert('El codigo ingresado no es correcto');\n" +
                    "                               </script>");
                                //enviar ese request a la pagina jsp
                                RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                                disp.forward(request, response);
                        }
                    }catch(Exception e){
                        out.println(e);
                    }
            }
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
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
                "                               alert('Ha introducido datos erroneos');\n" +
                "                               </script>");
                            //enviar ese request a la pagina jsp
                            RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                            disp.forward(request, response);
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
                        request.setAttribute("id_profe",id);
                        request.setAttribute("gr","null");
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

    private void sendEmail(HttpServletRequest request, HttpServletResponse response)throws Exception {
        PrintWriter out=response.getWriter();
        String correo = request.getParameter("correo");
        int id = Integer.parseInt(request.getParameter("id"));
        
        if(model.checkEmail(correo,id)){
            boolean exito = send(correo,id);
            if(exito){
                request.setAttribute("recoverCode", code);
                request.setAttribute("sts", "profesor");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/recoverCode.jsp");
                        disp.forward(request, response);
            }else{
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Ha ocurrdo un error al intentar mandar el correo, vuelvalo a intentar o pongase en contacto con el administrador');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
            }
        }else if(model2.checkEmail(correo, id)){
            boolean exito = send(correo,id);
            if(exito){
                request.setAttribute("recoverCode", code);
                request.setAttribute("sts", "administrador");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/recoverCode.jsp");
                        disp.forward(request, response);
            }else{
                request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Ha ocurrdo un error al intentar mandar el correo');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
            }
        }else{
            request.setAttribute("code", "<script type=\"text/javascript\">\n" +
            "                               alert('Ingreso datos no registrados anteriormente');\n" +
            "                               </script>");
                        //enviar ese request a la pagina jsp
                        RequestDispatcher disp=request.getRequestDispatcher("/index.jsp");
                        disp.forward(request, response);
        }
    }

    private boolean send(String correo, int id) {
        //nos conecatmos con la API de google para mandar el correo
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "jafetkevin575@gmail.com";
        String contrasena = "nG39!5065BGNvH";
        String receptor = correo;
        String asunto = "Rcuperacion de contraseña";
        String mensaje = "alguien intenta cambiar su contraseña, en caso de no ser usted ponerse en contacto con el administrador, CODIGO: "+getNewCode();
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String getNewCode() {
        Random r=new Random();
        String one = Integer.toString(r.nextInt(9));
        String two = Integer.toString(r.nextInt(9));
        String three = Integer.toString(r.nextInt(9));
        String four = Integer.toString(r.nextInt(9));
        String aux = one+two+three+four;
        code = aux;
        
        return aux;
    }

}
