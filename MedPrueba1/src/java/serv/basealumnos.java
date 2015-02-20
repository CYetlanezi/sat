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
                                                out.println("<script>");
                                                out.println("alert(\"Alumno ya Registrado\");");
                                                out.println("</script>");
                                                 out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
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
                                                        out.println("<script>");
                                                        out.println("alert(\"¡Datos Registrados Exitosamente!\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
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
                                                                        out.println("<td><input type = 'text' name = 'boleta' oncopy='return false' oncut='return false' onpaste='return false' value = '"+boleta+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Contraseña</th>");
                                                                        out.println("<td><input type = 'text' name = 'con' oncopy='return false' oncut='return false' onpaste='return false' value = '"+con+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Confirmar Contraseña</th>");
                                                                        out.println("<td><input type='password' name= 'ccon ' oncopy='return false' oncut='return false' onpaste='return false' match= 'con' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Paterno</th>");
                                                                        out.println("<td><input type = 'text' name = 'paterno' oncopy='return false' oncut='return false' onpaste='return false' value = '"+paterno+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Materno</th>");
                                                                        out.println("<td><input type = 'text' name = 'materno' oncopy='return false' oncut='return false' onpaste='return false' value = '"+materno+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Nombre</th>");
                                                                        out.println("<td><input type = 'text' name = 'nombre' oncopy='return false' oncut='return false' onpaste='return false' value = '"+nombre+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Género</th>");
                                                                        out.println("<td>");
                                                                        out.println("<select name = 'genero'>");
                                                                        if (genero.equals("femenino"))
                                                                            {
                                                                                out.println("<option value = 'femenino' selected>Femenino</option>");
                                                                                out.println("<option value = 'masculino'>Masculino</option>");
                                                                            }
                                                                        else
                                                                            {
                                                                                if (genero.equals("masculino"))
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
                                                                        out.println("<td><input type = 'text' name = 'aler' oncopy='return false' oncut='return false' onpaste='return false' value = '"+aler+"' maxlength = '200' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Número de Seguro Social</th>");
                                                                        out.println("<td><input type = 'text' name = 'nsocial' oncopy='return false' oncut='return false' onpaste='return false' value = '"+nsocial+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Tutor</th>");
                                                                        out.println("<td><input type = 'text' name = 'tutor' oncopy='return false' oncut='return false' onpaste='return false' value = '"+tutor+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Número de Seguridad Social del Tutor</th>");
                                                                        out.println("<td><input type = 'text' name = 'nstutor' oncopy='return false' oncut='return false' onpaste='return false' value = '"+nstutor+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Correo</th>");
                                                                        out.println("<td><input type = 'text' name = 'correo' oncopy='return false' oncut='return false' onpaste='return false' value = '"+correo+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
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
                                                                        out.println("<a href=\"dentista.html\" style=\"color: white; text-decoration: none\">");
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
                                                                out.println("<script>");
                                                                out.println("alert(\"¡Alumno no existente!\");");
                                                                out.println("</script>"); 
                                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://localhost:39055/MedPrueba1/dentista.html'/>");
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
                                                        out.println("<html>");
                                                        out.println("<script>");
                                                        out.println("alert(\"¿Seguro que deseas eliminar a "+boleta+" "+paterno+" "+materno+" "+nombre+"?\");");
                                                        out.println("</script>");
                                                        out.println("</html>");
                                                        try 
                                                            {
                                                                String query2 = "delete from alumnos where boleta = '"+boleta+"'";
                                                                ps2 = c.prepareStatement(query2);
                                                                ps2.executeUpdate();
                                                                out.println("<script>");
                                                                out.println("alert(\"¡Datos dados de baja Exitosamente!\");");
                                                                out.println("</script>");
                                                                 out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                            }  
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                else
                                                    {
                                                        out.println("<script>");
                                                        out.println("alert(\"Número de boleta no registrado\");");
                                                        out.println("</script>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
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
                                                                                out.println("<a href=\"dentista.html\" style=\"color: white; text-decoration: none\">");
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
                                                                        out.println("<script>");
                                                                        out.println("alert(\"Número de boleta no registrado\");");
                                                                        out.println("</script>");
                                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
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
                                                out.println("<script>");
                                                out.println("alert(\"Datos Modificados Exitosamente\");");
                                                out.println("</script>");
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
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
                                       out.println("<a href=\"dentista.html\" style=\"color: white; text-decoration: none\">");
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