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
import javax.servlet.http.HttpSession;

@WebServlet(name = "sesion", urlPatterns = {"/sesion"})
public class sesion extends HttpServlet 
    {
        private final String base = "medical"; 
        private final String usuario = "root";
        private final String password = "n0m3l0";
        private final String url = "jdbc:mysql://localhost/";
        private final String driver = "com.mysql.jdbc.Driver";
        // private final String puerto = "39055";
        private final String puerto = "39055";
        //private final String ip = "";
        private final String ip = "127.0.0.1";
        private Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        String contra = "";
        String minombre = "";       
        String snombre;             
        String sid;
        
        

        private void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws  ServletException, IOException 
            {
                PrintWriter out = response.getWriter();
                        
                try 
                    {
                        Class.forName(driver).newInstance();
                    } 
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
                    {
                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                try 
                    {
                        c = DriverManager.getConnection((url + base), usuario, password);
                    } 
                catch (SQLException ex) 
                    {
                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                HttpSession lasesion = request.getSession();         

                    /*
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
                    */        

                        if (request.getParameter("press").equals("Entrar"))
                            {
                                String boleta = request.getParameter("boleta");
                                String con = request.getParameter("clave");                                                                
                                try
                                    {
                                        String query1 = "select nombre,con from alumnos where boleta = '"+boleta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        if (r.next())
                                            {
                                                contra = r.getString("con");
                                                minombre = r.getString("nombre");
                                                if (contra.equals(con))
                                                    {
                                                       // out.println("<script>");
                                                         //out.println("alert(\"Bienvenido "+minombre+"\");");
                                                        //out.println("</script>");
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
                                                         out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -103px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='con warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none;'></div> <h2>Bienvenid@  "+minombre+"!</h2><p style='display: block;'></p><button class='cancel' tabindex='2' style='display: none; box-shadow: none'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/alumno.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/alumno.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                        lasesion.setAttribute("sid", boleta );
                                                        lasesion.setAttribute("snombre", minombre );                                                        
                                                        
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/alumno.html'/>");                                                     
                                                    }
                                                else
                                                    {
                                                      /*  out.println("<script>");
                                                            out.println("alert(\"¡Contraseña incorrecta!\");");
                                                        out.println("</script>");                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'/>");                                                                                                               
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Clave Incorrecta</h2><p style='display: block;'>Verifica los datos!</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'/>");   
                                                        
                                                      }
                                            }
                                        else
                                            {
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>El usuario no existe</h2><p style='display: block;'>Acude al Servicio Dental para que se te asigne un usuario</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'/>");   
                                                        
                                            }                                                       
                                    }
                                catch(SQLException ex)
                                    {
                                        out.println("<script>");
                                            out.println("alert(\"Baka\");");
                                        out.println("</script>");
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        
                        if (request.getParameter("press").equals("Accesar"))
                            {
                                String idm = request.getParameter("idmedico");
                                String con = request.getParameter("clave");                                                                
                                try
                                    {
                                        String query1 = "select nombre,con from medicos where ntrabajador = '"+idm+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        
                                        if (r.next())
                                            {
                                                contra = r.getString("con");
                                                minombre = r.getString("nombre");
                                                if (contra.equals(con))
                                                    {
                                                        lasesion.setAttribute("sid", idm );
                                                        lasesion.setAttribute("snombre", minombre );
                                                 //       out.println("<script>");
                                                    //        out.println("alert(\"Bienvenido Dentista "+minombre+"\");");
                                                       // out.println("</script>");
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
                                                         out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -103px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='con warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none;'></div> <h2>Bienvenid@  "+minombre+"!</h2><p style='display: block;'></p><button class='cancel' tabindex='2' style='display: none; box-shadow: none'>Cancel</button><button class='confirm' onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                        lasesion.setAttribute("sid", idm );
                                                        lasesion.setAttribute("snombre", minombre );                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");                                                       
                                                    }
                                                else
                                                    {
                                                       /* out.println("<script>");
                                                            out.println("alert(\"¡Contraseña incorrecta!\");");
                                                        out.println("</script>");                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'/>");                                                                                                                
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Clave Incorrecta</h2><p style='display: block;'>Verifica los datos!</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/inientista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'/>");   
                                                        
                                                      }  
                                            }
                                        else
                                            {
                                             /*   out.println("<script>");
                                                    out.println("alert(\"¡EL usuario no existe!\");");
                                                out.println("</script>");                                                        
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://localhost:39055/MedPrueba1/inientista.html'/>");                                                                                                                
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>El usuario no existe</h2><p style='display: block;'>Verifica los datos</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/inientista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'/>");   
                                                  
                                              }                                                       
                                    }
                                catch(SQLException ex)
                                    {
                                        out.println("<script>");
                                                            out.println("alert(\"Baka\");");
                                        out.println("</script>");
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        if(request.getParameter("press").equals("Salir"))
                            {
                           /*     out.println("<script>");
                                    out.println("alert(\"Hasta luego "+lasesion.getAttribute("snombre")+"\");");
                                out.println("</script>");                                
                                lasesion.invalidate();
                              out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/index.html'/>");     
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
                                                         out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -103px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='con warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none;'></div> <h2>Hasta luego "+lasesion.getAttribute("snombre")+"</h2><p style='display: block;'></p><button class='cancel' tabindex='2' style='display: none; box-shadow: none'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/index.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/index.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                        lasesion.invalidate();
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/index.html'/>");                                                     
                                                   
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