/*
 *
 */
package com.deepture.ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author DeepTure
 */

/*
    Vistas = jsp
    servlet = controlador
    clase java = Modelo
_____________________________
    -!No hacer impresiones desde el controlador (a menos que sea para pruebas de errores)
    -!Trabaja con limpieza y comenta todo el codigo
    -No toques el codigo de los demás a menos que entiendas bien que hace  
    -El archivo xml con el pool se encuentra en META-INF/context.xml \\\ Si necesitas modificarlo para usar tu usuario y contraseña
        Pero no cambies el "type", "url" o "driver"
*/
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    //Instanciamos una variable de tipo modelo; que tiene todos los metodos del modelo para hacer consultas
    private Modelo modelo;
    
    //definir el datasource
    @Resource(name="jdbc/Data")
    private DataSource miPool;
    
    
    //con este metodo init(); instanciamos el objeto de tipo modelo y le pasamos como parametro el pool para que pueda hacer las conexiones a la bbdd
    @Override
    public void init() throws ServletException {
        super.init();
        try{
            modelo = new Modelo(miPool);
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
    

    
    //Usar los metodos doGet o doPost segun el motodo para pasar parametrso de las vistas
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //aquí meter todo el codigo necesario, para las acciones PERO no ensuciar, 
        //Solo manden a llamar metodos
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //aquí meter todo el codigo necesario, para las acciones PERO no ensuciar, 
        //Solo manden a llamar metodos
        PrintWriter out=response.getWriter();
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "jafetkevin575@gmail.com";
        String contrasena = "nG39!5065BGNvH";
        String receptor = "moranorozcokevinjafet@gmail.com";
        String asunto = "recuperacion de contraseña";
        String mensaje = "prueba uno xdxd";
        
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
            
            out.println("all ok");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println(ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    // Aquí crear todos los metodos necesarios

}
