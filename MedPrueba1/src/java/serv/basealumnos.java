package serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Claudia
 */
@WebServlet(name = "basealumnos", urlPatterns = {"/basealumnos"})
public class basealumnos extends HttpServlet {
        private final String based = "medical"; 
        private final String user = "root";
        private final String pass = "n0m3l0";
        private final String url = "jdbc:mysql://localhost/";
        private final String driver = "com.mysql.jdbc.Driver";
        //private final String puerto = "8080";
        private final String puerto = "39055";
        //private final String ip = "";
        private final String ip = "localhost";
        private Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;


        private void processRequest(HttpServletRequest request, HttpServletResponse response) 
         throws  ServletException, IOException {
                        PrintWriter out = response.getWriter();
                        
                        try 
                            {
                                Class.forName(driver).newInstance();
                            } 
                        catch (ClassNotFoundException ex) 
                            {
                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        catch (InstantiationException ex) 
                            {
                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        catch (IllegalAccessException ex) 
                            {
                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        try 
                            {
                                c = DriverManager.getConnection((url + based), user, pass);
                            } 
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                        out.println("<html>");
                        out.println("<script>");
                        out.println("function antiTrolls(e){ var keynum;");
                        out.println("if (window.event){ keynum = e.keyCode; }");
                        out.println("if (e.which){ keynum = e.which; }");
                        out.println("if ((keynum >= 33 && keynum <= 43) || (keynum >= 58 && keynum <= 64)){ return false; }");
                        out.println("else { return true; }}");
                        out.println("function soloEnteros(e){ var keynum;");
                        out.println("if (window.event){ keynum = e.keyCode; }");
                        out.println("if (e.which){ keynum = e.which; }");
                        out.println("if (((keynum >= 48 && keynum <= 57) || (keynum == 8)) && (keynum != 86)){ return true; }");
                        out.println("else { return false; }}");
                        out.println("function soloLetras(e){ var keynum;");
                        out.println("if (window.event){ keynum = e.keyCode; }");
                        out.println("if (e.which){ keynum = e.which; }");
                        out.println("if ((keynum >= 97 && keynum <= 122) || (keynum >= 65 && keynum <= 90) || (keynum == 8) || (keynum == 32) || (keynum >= 160 && keynum <= 163)){ return true; }");
                        out.println("else { return false; }}");
                        out.println("</script>");
                        out.println("<body>");
                        
                        if (request.getParameter("boton").equals("Alta"))
                            {
                                String boleta = request.getParameter("boleta");
                                String con = request.getParameter("con");
                                String paterno = request.getParameter("paterno");
                                String materno = request.getParameter("materno");
                                String nombre = request.getParameter("nombre");
                                String genero = request.getParameter("genero");
                                String aler = request.getParameter("aler");
                                String nsocial = request.getParameter("nsocial");
                                String tutor = request.getParameter("tutor");
                                String nstutor = request.getParameter("nstutor");
                                String correo = request.getParameter("correo");
                                
                                try
                                    {
                                        String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        
                                        if (r.next())
                                            {
                                               /* out.println("<script>");
                                                out.println("alert(\"Alumno ya Registrado\");");
                                                out.println("</script>");
                                                 out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                       */
                                                   out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Alumno ya registrado</h2><p style='display: block;'>Verifica los datos!</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                              }
                                        else
                                            {
                                                try 
                                                    {
                                                        String query2 = "insert into alumnos values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                                       
                                                        ps2 = c.prepareStatement(query2);
                                                        ps2.setString(1,boleta);
                                                        ps2.setString(2,con);
                                                        ps2.setString(3,paterno);
                                                        ps2.setString(4,materno);
                                                        ps2.setString(5,nombre);
                                                        ps2.setString(6,genero); 
                                                        ps2.setString(7,aler); 
                                                        ps2.setString(8,nsocial);
                                                        ps2.setString(9,tutor);
                                                        ps2.setString(10,nstutor);
                                                        ps2.setString(11,correo);
                                                        ps2.executeUpdate();
                                                        
                                                        /*out.println("<script>");
                                                        out.println("alert(\"¡Datos Registrados Exitosamente!\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                   */
                                                             
                                                   out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Alumno registrado!</h2><p style='display: block;'>Has registrado un nuevo alumno con &eacute;xito</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                                        
                                                       }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                catch (SQLException ex) 
                                    {
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        //Cambios
                        if (request.getParameter("boton").equals("Buscar"))
                            {
                                String boleta = request.getParameter("boleta");

                                                try
                                                    {
                                                        String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                                        ps1 = c.prepareStatement(query1);
                                                        ResultSet r = ps1.executeQuery();

                                                        if (r.next())
                                                            {
                                                                try 
                                                                    {
                                                                        String con, paterno, materno, nombre, genero, aler, nsocial, tutor, nstutor, correo;
                                                                        con = r.getString("con");
                                                                        paterno = r.getString("paterno");
                                                                        materno = r.getString("materno");
                                                                        nombre = r.getString("nombre");
                                                                        genero = r.getString("genero");
                                                                        aler = r.getString("aler");
                                                                        nsocial = r.getString("nsocial");
                                                                        tutor = r.getString("tutor");
                                                                        nstutor = r.getString("nstutor");
                                                                        correo = r.getString("correo");
                                                                       
                                                                        out.println("<head>");
                                                                        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                                        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
                                                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />");
                                                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />");
                                                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/login.css\" />");
                                                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ladda.css\" />");
                                                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bot.css\" />");
                                                                        out.println("</head>");
                                                                        out.println("<div class=\"component\">");
                                                                                
                                                                        out.println("<form action = 'basealumnos' method = 'post'>");
                                                                        out.println("<table>");
                                                                        out.println("<tbody>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Boleta</th>");
                                                                        out.println("<td><input type = 'text' name = 'boleta' value = '"+boleta+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Contraseña</th>");
                                                                        out.println("<td><input type = 'text' name = 'con' value = '"+con+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Confirmar Contraseña</th>");
                                                                        out.println("<td><input type='password' name= 'ccon ' match= 'con' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Paterno</th>");
                                                                        out.println("<td><input type = 'text' name = 'paterno' value = '"+paterno+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Materno</th>");
                                                                        out.println("<td><input type = 'text' name = 'materno' value = '"+materno+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Nombre</th>");
                                                                        out.println("<td><input type = 'text' name = 'nombre' value = '"+nombre+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Género</th>");
                                                                        out.println("<td>");
                                                                        out.println("<select name = 'genero'>");
                                                                        if (genero.equals("femenino") || genero.equals("Femenino"))
                                                                            {
                                                                                out.println("<option value = 'femenino' selected>Femenino</option>");
                                                                                out.println("<option value = 'masculino'>Masculino</option>");
                                                                            }
                                                                        else
                                                                            {
                                                                                if (genero.equals("masculino") || genero.equals("Masculino"))
                                                                                    {
                                                                                        out.println("<option value = 'femenino'>Femenino</option>");
                                                                                        out.println("<option value = 'masculino' selected>Masculino</option>");
                                                                                    }
                                                                            }
                                                                        out.println("</select>");
                                                                        out.println("</td>");
                                                                        out.println("</tr>");
                                                                         out.println("<tr>");
                                                                        out.println("<th>Alérgias</th>");
                                                                        out.println("<td><input type = 'text' name = 'aler' value = '"+aler+"' maxlength = '200' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Número de Seguro Social</th>");
                                                                        out.println("<td><input type = 'text' name = 'nsocial' value = '"+nsocial+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Tutor</th>");
                                                                        out.println("<td><input type = 'text' name = 'tutor' value = '"+tutor+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Número de Seguridad Social del Tutor</th>");
                                                                        out.println("<td><input type = 'text' name = 'nstutor' value = '"+nstutor+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Correo</th>");
                                                                        out.println("<td><input type = 'text' name = 'correo' value = '"+correo+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        
                                                                        out.println("</tbody>");
                                                                        out.println("</table>");
                                                                        out.println("<br>");
                                                                        out.println("<center>");
                                                                        out.println("<input type = 'submit' name = 'boton' value = 'Actualizar'>");
                                                                        out.println("</center>");
                                                                        out.println("</form>");
                                                                        out.println("</div>");
                                                                        out.println("<div class=\"regresar\">");
                                                                        out.println("<button name=\"regresar\" class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                                                        out.println("<a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color: white; text-decoration: none\">");
                                                                        out.println("Regresar");
                                                                        out.println("</a>");
                                                                        out.println("</button>");
                                                                        out.println("</div>");
                                                                    }  
                                                                catch (SQLException ex) 
                                                                    {
                                                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                            }
                                                        else
                                                            {
                                                            /*    out.println("<script>");
                                                                out.println("alert(\"¡Alumno no existente!\");");
                                                                out.println("</script>"); 
                                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://localhost:39055/MedPrueba1/dentista.html'/>");
                                                            */
                                                                  out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Alumno no registrado!</h2><p style='display: block;'>Tienes que registrar previamente al alumno</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                                              }
                                                    }
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                            }
                        if (request.getParameter("boton").equals("Baja"))
                                    {
                                        String boleta = request.getParameter("boleta");
                                        
                                        try
                                            {
                                                String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                                ps1 = c.prepareStatement(query1);
                                                ResultSet r = ps1.executeQuery();                                               
                                                if (r.next())
                                                    {
                                                        String paterno, materno, nombre;
                                                        paterno = r.getString("paterno");
                                                        materno = r.getString("materno");
                                                        nombre = r.getString("nombre");
                                                        
                                                        out.println("<script>");
                                                        out.println("alert(\"¿Seguro que deseas eliminar a "+boleta+" "+paterno+" "+materno+" "+nombre+"?\");");
                                                        out.println("</script>");
                                                        out.println("</html>");
                                                   /*      out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='true' data-allow-ouside-click='false' data-has-done-function='true' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning pulseWarning' style='display: block;'> <span class='body pulseWarningIns'></span> <span class='dot pulseWarningIns'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none;'></div> <h2>&iquest;Seguro que deseas eliminar a "+boleta+"  "+paterno+"  "+materno+"  "+nombre+"?</h2><p style='display: block;'>Se eliminar&aacute; de forma permanente!</p><button class='cancel' tabindex='2' style='display: inline-block; box-shadow: none;'>Cancelar</button><button class='confirm' tabindex='1' style='box-shadow: rgba(221, 107, 85, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(221, 107, 85);'>Si, eliminalo!</button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        */
                                                        try 
                                                            {
                                                                String query2 = "delete from alumnos where boleta = '"+boleta+"'";
                                                                ps2 = c.prepareStatement(query2);
                                                                ps2.executeUpdate();
                                                                /*out.println("<script>");
                                                        out.println("alert(\"¡Datos baja Exitosamente!\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                   */
                                                             
                                                   out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Alumno dado de baja!</h2><p style='display: block;'>Has eliminado a un alumno</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                                    
                                                            }  
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                else
                                                    {
                                                      /*  out.println("<script>");
                                                        out.println("alert(\"Número de boleta no registrado\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                            */
                                                                        out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Alumno no registrado!</h2><p style='display: block;'>Tienes que registrar previamente al alumno</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                                              }
                                            }
                                        catch (SQLException ex) 
                                            {
                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                    }
                               if (request.getParameter("boton").equals("Consulta"))
                                                    {
                                                        String boleta = request.getParameter("boleta");

                                                        try
                                                            {
                                                                String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                                                ps1 = c.prepareStatement(query1);
                                                                ResultSet r = ps1.executeQuery();
                                                                
                                                                if (r.next())
                                                                    {
                                                                        try 
                                                                            {
                                                                                String con, paterno, materno, nombre, genero, aler, nsocial, tutor, nstutor, correo;
                                                                                con = r.getString("con");
                                                                                paterno = r.getString("paterno");
                                                                                materno = r.getString("materno");
                                                                                nombre = r.getString("nombre");
                                                                                genero = r.getString("genero");
                                                                                aler = r.getString("aler");
                                                                                nsocial = r.getString("nsocial");
                                                                                tutor = r.getString("tutor");
                                                                                nstutor = r.getString("nstutor");
                                                                                correo = r.getString("correo");
                                                                                
                                                                                out.println("<head>");
                                                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
                                                                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />");
                                                                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />");
                                                                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/login.css\" />");
                                                                                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ladda.css\" />");
                                                                                out.println("</head>");
                                                                                out.println("<div class=\"component\">");
                                                                                out.println("<table>");
                                                                                
                                                                                out.println("<tbody>");
                                                                                
                                                                                out.println("<tr>");
                                                                                out.println("<th>Boleta</th>");
                                                                                out.println("<td>"+boleta+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Contraseña</th>");
                                                                                out.println("<td>"+con+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Nombre</th>");
                                                                                out.println("<td>"+paterno+"&nbsp;"+materno+"&nbsp;"+nombre+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Género</th>");
                                                                                out.println("<td>"+genero+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Alérgias</th>");
                                                                                out.println("<td>"+aler+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<th>Número Social</th>");
                                                                                out.println("<td>"+nsocial+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Nombre del Tutor</th>");
                                                                                out.println("<td>"+tutor+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Número de Seguridad Social del Tutor</th>");
                                                                                out.println("<td>"+nstutor+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Correo</th>");
                                                                                out.println("<td>"+correo+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("</tbody>");
                                                                                out.println("</table>");
                                                                                out.println("</div>");
                                                                                out.println("<div class=\"regresar\">");
                                                                                out.println("<button name=\"regresar\" class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                                                                out.println("<a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color: white; text-decoration: none\">");
                                                                                out.println("Regresar");
                                                                                out.println("</a>");
                                                                                out.println("</button>");
                                                                                out.println("</div>");
                                                                               
                                                                            }  
                                                                        catch (SQLException ex) 
                                                                            {
                                                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                    }
                                                                else
                                                                    {
                                                                            /*  out.println("<script>");
                                                        out.println("alert(\"Número de boleta no registrado\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                            */
                                                                        out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Alumno no registrado!</h2><p style='display: block;'>Tienes que registrar previamente al alumno</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        }
                                                            }
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                               if (request.getParameter("boton").equals("Actualizar"))
                                    {
                                        String boleta = request.getParameter("boleta");
                                        String con = request.getParameter("con");
                                        String paterno = request.getParameter("paterno");
                                        String materno = request.getParameter("materno");
                                        String nombre = request.getParameter("nombre");
                                        String genero = request.getParameter("genero");
                                        String aler = request.getParameter("aler");
                                        String nsocial = request.getParameter("nsocial");
                                        String tutor = request.getParameter("tutor");
                                        String nstutor = request.getParameter("nstutor");
                                        String correo = request.getParameter("correo");
                                                               
                                        try
                                             {
                                                String query1 = "update alumnos set con = ?, paterno = ?, materno = ?, nombre = ?, genero = ?, aler = ?, nsocial = ?, tutor = ?, nstutor = ?, correo = ? where boleta = ?";
                                                ps1 = c.prepareStatement(query1);
                                                ps1.setString(1,con);
                                                ps1.setString(2,paterno);
                                                ps1.setString(3,materno);
                                                ps1.setString(4,nombre);
                                                ps1.setString(5,genero);
                                                ps1.setString(6,aler);
                                                ps1.setString(7,nsocial);
                                                ps1.setString(8,tutor);
                                                ps1.setString(9,nstutor);
                                                ps1.setString(10,correo);
                                                ps1.setString(11,boleta);
                                                ps1.executeUpdate();
                                                 /*out.println("<script>");
                                                        out.println("alert(\"¡Datos Modificados Exitosamente!\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                   */
                                                             
                                                   out.println("<head>");
                                                       out.println("<meta charset='utf-8'>");
                                                      out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
                                                         out.println("<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0' />");
                                                         out.println("<title>Alerta</title>");
                                                         out.println("<script src='js/sweet-alert.js'></script>");
                                                    out.println("<link rel='stylesheet' href='css/sweet-alert.css'>");
                                                         out.println("</head>");
                                                          out.println("<div>");
                                                         out.println("<div class='sweet-overlay' tabindex='-1' style='opacity: 1.09; display: block;'></div>");
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Datos modificados!</h2><p style='display: block;'>Has modificado los datos con &eacute;xito</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                                    
                                             }
                                        catch (SQLException ex) 
                                             {
                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                             }
                                   }
                        if (request.getParameter("boton").equals("Consultar"))
                                {
                                  try
                                    {
                                        String query1 = "select * from alumnos";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        out.println("<head>");
                                        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/login.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ladda.css\" />");
                                        out.println("</head>");
                                        out.println("<div class=\"component\">");
                                        out.println("<table>");
                                            out.println("<thead>");
                                            out.println("<tr>");
                                            out.print("<th>");
                                            out.print("Boleta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Nombre");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("# Seguro Social");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("correo");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String boleta = r.getString("boleta");
                                            String paterno = r.getString("paterno");
                                            String materno = r.getString("materno");
                                            String nombre = r.getString("nombre");
                                            String nsocial = r.getString("nsocial");
                                            String correo = r.getString("correo");
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(boleta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(paterno+" "+materno+" "+nombre);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(nsocial);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(correo);
                                            out.print("</td>");
                                            out.print("</tr>");
                                        }
                                       out.println("</tbody>");
                                       out.println("</table>");
                                       out.println("</div>");
                                       out.println("<div class=\"regresar\">");
                                       out.println("<button name=\"regresar\" class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                       out.println("<a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color: white; text-decoration: none\">");
                                       out.println("Regresar");
                                       out.println("</a>");
                                       out.println("</button>");
                                       out.println("</div>");
                                    }
                                 catch (SQLException ex) 
                                    {
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                }
                               
                    }
        
      @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException{
                    processRequest(request, response);
                }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException{
                    processRequest(request, response);
                }
}