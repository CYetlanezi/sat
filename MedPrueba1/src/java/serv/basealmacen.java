/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "basealmacen", urlPatterns = {"/basealmacen"})
public class basealmacen extends HttpServlet {
        private final String based = "medical"; 
        private final String user = "root";
        private final String pass = "n0m3l0";
        private final String url = "jdbc:mysql://localhost/";
        private final String driver = "com.mysql.jdbc.Driver";
       //  private final String puerto = "8080";
     private final String puerto = "39055";
    //private final String ip = "";
        private final String ip = "localhost";
        private Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;


        private void processRequest(HttpServletRequest request, HttpServletResponse response) 
         throws  ServletException, IOException {
                        PrintWriter out = response.getWriter();
                        
                        try 
                            {
                                Class.forName(driver).newInstance();
                            } 
                        catch (ClassNotFoundException ex) 
                            {
                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        catch (InstantiationException ex) 
                            {
                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        catch (IllegalAccessException ex) 
                            {
                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        try 
                            {
                                c = DriverManager.getConnection((url + based), user, pass);
                            } 
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
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
                        
                        if (request.getParameter("boton").equals("Alta Medicamento"))
                            {
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String lab = request.getParameter("lab");
                                String tipo = request.getParameter("tipo");
                                String porcion = request.getParameter("porcion");
                                String vadmi = request.getParameter("vadmi");
                                String uso = request.getParameter("uso");
                                String res = request.getParameter("res");
                                String cadu = request.getParameter("cadu");
                                String cant = request.getParameter("cant");
                                String tipoc = request.getParameter("tipoc");
                                
                                try
                                    {
                                        String query1 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        
                                        if (r.next())
                                            {
                                                out.println("<script>");
                                                out.println("alert(\"Medicamento ya Registrado\");");
                                                out.println("</script>");
                                               out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                            }
                                        else
                                            {
                                                try 
                                                    {
                                                        String query2 = "insert into medicamento values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                                       
                                                        ps2 = c.prepareStatement(query2);
                                                        ps2.setString(1,idalma);
                                                        ps2.setString(2,nombrem);
                                                        ps2.setString(3,factiva);
                                                        ps2.setString(4,lab);
                                                        ps2.setString(5,tipo);
                                                        ps2.setString(6,porcion); 
                                                        ps2.setString(7,vadmi); 
                                                        ps2.setString(8,uso);
                                                        ps2.setString(9,res);
                                                        ps2.setString(10,cadu);
                                                        ps2.setString(11,cant);
                                                         ps2.setString(12,tipoc);
                                                        ps2.executeUpdate();
                                                        out.println("<script>");
                                                        out.println("alert(\"¡Datos Registrados Exitosamente!\");");
                                                        out.println("</script>");
                                                       out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                catch (SQLException ex) 
                                    {
                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        
                            if (request.getParameter("boton").equals("Alta Material"))
                            {
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String marca = request.getParameter("marca");
                                String uso = request.getParameter("uso");
                                String cant = request.getParameter("cant");
                                
                                try
                                    {
                                        String query1 = "select * from material where idalma = '"+idalma+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        
                                        if (r.next())
                                            {
                                                out.println("<script>");
                                                out.println("alert(\"Material ya Registrado\");");
                                                out.println("</script>");
                                               out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                            }
                                        else
                                            {
                                                try 
                                                    {
                                                        String query2 = "insert into material values(?, ?, ?, ?, ?)";
                                                       
                                                        ps2 = c.prepareStatement(query2);
                                                        ps2.setString(1,idalma);
                                                        ps2.setString(2,nombrem);
                                                        ps2.setString(3,marca); 
                                                        ps2.setString(4,uso);
                                                        ps2.setString(5,cant);
                                                        ps2.executeUpdate();
                                                        out.println("<script>");
                                                        out.println("alert(\"¡Datos Registrados Exitosamente!\");");
                                                        out.println("</script>");
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                catch (SQLException ex) 
                                    {
                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                            }
                        //Cambios
                        if (request.getParameter("boton").equals("Buscar"))
                            {
                                String idalma = request.getParameter("idalma");

                                                try
                                                    {
                                                        String query1 = "select * from medicamento where idalma = '"+idalma+"'";
                                                        ps1 = c.prepareStatement(query1);
                                                        ResultSet r = ps1.executeQuery();

                                                        if (r.next())
                                                            {
                                                                try 
                                                                    {
                                                                        String nombrem, factiva, lab, tipo, porcion, vadmi, uso, res, cadu, cant, tipoc;
                                                                        nombrem = r.getString("nombrem");
                                                                        factiva = r.getString("factiva");
                                                                        lab = r.getString("lab");
                                                                        tipo = r.getString("tipo");
                                                                        porcion = r.getString("porcion");
                                                                        vadmi = r.getString("vadmi");
                                                                        uso = r.getString("uso");
                                                                        res = r.getString("res");
                                                                        cadu = r.getString("cadu");
                                                                        cant = r.getString("cant");
                                                                        tipoc = r.getString("tipoc");
                                                                       
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
                                                                                
                                                                        out.println("<form action = 'basealmacen' method = 'post'>");
                                                                        out.println("<table>");
                                                                        out.println("<tbody>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Id Almacen</th>");
                                                                        out.println("<td><input type = 'text' name = 'idalma' oncopy='return false' oncut='return false' onpaste='return false' value = '"+idalma+"' maxlength = '20' onkeypress = 'return soloEnteros(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Nombre del Medicamento</th>");
                                                                        out.println("<td><input type = 'text' name = 'nombrem' oncopy='return false' oncut='return false' onpaste='return false' value = '"+nombrem+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Fórmula Activa</th>");
                                                                        out.println("<td><input type='text' name= 'factiva'  oncopy='return false' oncut='return false' onpaste='return false' value = '"+factiva+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Laboratorio</th>");
                                                                        out.println("<td><input type = 'text' name = 'lab' oncopy='return false' oncut='return false' onpaste='return false' value = '"+lab+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Tipo</th>");
                                                                        out.println("<td><input type = 'text' name = 'tipo' oncopy='return false' oncut='return false' onpaste='return false' value = '"+tipo+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Porción</th>");
                                                                        out.println("<td><input type = 'text' name = 'porcion' oncopy='return false' oncut='return false' onpaste='return false' value = '"+porcion+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Vía de Administración</th>");
                                                                        out.println("<td><input type = 'text' name = 'vadmi' oncopy='return false' oncut='return false' onpaste='return false' value = '"+vadmi+"' maxlength = '200' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Uso</th>");
                                                                        out.println("<td><input type = 'text' name = 'uso' oncopy='return false' oncut='return false' onpaste='return false' value = '"+uso+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Restricción</th>");
                                                                        out.println("<td><input type = 'text' name = 'res' oncopy='return false' oncut='return false' onpaste='return false' value = '"+res+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Caducidad</th>");
                                                                        out.println("<td><input type = 'text' name = 'cadu' oncopy='return false' oncut='return false' onpaste='return false' value = '"+cadu+"' maxlength = '20' onkeypress = 'return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Cantidad</th>");
                                                                        out.println("<td><input type = 'text' name = 'cant' oncopy='return false' oncut='return false' onpaste='return false' value = '"+cant+"' maxlength = '20' onkeypress = 'return soloEnteros(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Tipo Canntidad</th>");
                                                                        out.println("<td>");
                                                                        out.println("<select name = 'tipoc'>");
                                                                        if (tipoc.equals("caja"))
                                                                            {
                                                                                out.println("<option value = 'caja' selected>Caja(s)</option>");
                                                                                out.println("<option value = 'dosis'>Dosís</option>");
                                                                            }
                                                                        else
                                                                            {
                                                                                if (tipoc.equals("dosis"))
                                                                                    {
                                                                                        out.println("<option value = 'caja'>Caja(s)</option>");
                                                                                        out.println("<option value = 'dosis' selected>Dosís</option>");
                                                                                    }
                                                                            }
                                                                        out.println("</select>");
                                                                        out.println("</td>");
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
                                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                            }
                                                        else{
                                                                String query2 = "select * from material where idalma = '"+idalma+"'";
                                                                 ps2 = c.prepareStatement(query2);
                                                                 ResultSet r2 = ps2.executeQuery();
                                                               if (r2.next())
                                                            {
                                                                 try 
                                                                    {
                                                                        String nombrem, marca, uso, cant;
                                                                        nombrem = r2.getString("nombrem");
                                                                        marca = r2.getString("marca");
                                                                        uso = r2.getString("uso");
                                                                        cant = r2.getString("cant");
                                                                        
                                                                       
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
                                                                                
                                                                        out.println("<form action='basealmacen' method = 'post'>");
                                                                        out.println("<table>");
                                                                        out.println("<tbody>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Id Almacen</th>");
                                                                        out.println("<td><input type = 'text' oncopy='return false' oncut='return false' onpaste='return false' name = 'idalma' value = '"+idalma+"' maxlength = '20' onkeypress = 'return soloEnteros(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Nombre del Material</th>");
                                                                        out.println("<td><input type = 'text' oncopy='return false' oncut='return false' onpaste='return false'  name = 'nombrem' value = '"+nombrem+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Marca</th>");
                                                                        out.println("<td><input type='text' name= 'marca' oncopy='return false' oncut='return false' onpaste='return false' value = '"+marca+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Uso</th>");
                                                                        out.println("<td><input type = 'text' name = 'uso' oncopy='return false' oncut='return false' onpaste='return false' value = '"+uso+"' maxlength = '20' onkeypress = 'return soloLetras(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("<tr>");
                                                                        out.println("<th>Cantidad</th>");
                                                                        out.println("<td><input type = 'text' name = 'cant'  oncopy='return false' oncut='return false' onpaste='return false' value = '"+cant+"' maxlength = '20' onkeypress = 'return soloEnteros(event); return antiTrolls(event)' required></td>");
                                                                        out.println("</tr>");
                                                                        out.println("</tbody>");
                                                                        out.println("</table>");
                                                                        out.println("<br>");
                                                                        out.println("<center>");
                                                                        out.println("<input type ='submit' name ='boton' value='Actualizar Material'>");
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
                                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                            }
                                                            else
                                                                    {
                                                                        out.println("<script>");
                                                                        out.println("alert(\"¡No Registrado en Almacen!\");");
                                                                        out.println("</script>"); 
                                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                                    }
                                                            
                                                            }
                                                    }
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                            }
                        if (request.getParameter("boton").equals("Baja"))
                                    {
                                        String idalma = request.getParameter("idalma");
                                        
                                        try
                                            {
                                                String query1 = "select * from medicamento where idalma = '"+idalma+"'";
                                                ps1 = c.prepareStatement(query1);
                                                ResultSet r = ps1.executeQuery();                                               
                                                if (r.next())
                                                    {
                                                        String nombrem, factiva, lab;
                                                        nombrem = r.getString("nombrem");
                                                        factiva = r.getString("factiva");
                                                        lab = r.getString("lab");
                                                        out.println("<html>");
                                                        out.println("<script>");
                                                        out.println("alert(\"¿Seguro que deseas eliminar "+idalma+" "+nombrem+" "+factiva+" "+lab+"?\");");
                                                        out.println("</script>");
                                                        out.println("</html>");
                                                        try 
                                                            {
                                                                String query2 = "delete from medicamento where idalma = '"+idalma+"'";
                                                                ps2 = c.prepareStatement(query2);
                                                                ps2.executeUpdate();
                                                                out.println("<script>");
                                                                out.println("alert(\"¡Datos dados de baja Exitosamente!\");");
                                                                out.println("</script>");
                                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                            }  
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                                                else
                                                    {
                                                        String query2 = "select * from material where idalma = '"+idalma+"'";
                                                        ps2 = c.prepareStatement(query2);
                                                        ResultSet r2 = ps2.executeQuery();                                               
                                                        if (r2.next())
                                                            {
                                                                String nombrem, marca, cant;
                                                                nombrem = r2.getString("nombrem");
                                                                marca = r2.getString("marca");
                                                                cant = r2.getString("cant");
                                                                out.println("<html>");
                                                                out.println("<script>");
                                                                out.println("alert(\"¿Seguro que deseas eliminar "+idalma+" "+nombrem+" "+marca+" "+cant+"?\");");
                                                                out.println("</script>");
                                                                out.println("</html>");
                                                                try 
                                                                    {
                                                                        String query3 = "delete from material where idalma = '"+idalma+"'";
                                                                        ps3 = c.prepareStatement(query3);
                                                                        ps3.executeUpdate();
                                                                        out.println("<script>");
                                                                        out.println("alert(\"¡Datos dados de baja Exitosamente!\");");
                                                                        out.println("</script>");
                                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                                    }  
                                                                catch (SQLException ex) 
                                                                    {
                                                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                            } 
                                                        else{
                                                                out.println("<script>");
                                                                out.println("alert(\"No registrado en Almacen\");");
                                                                out.println("</script>");
                                                               out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                            
                                                        }
                                                    }
                                            }
                                        catch (SQLException ex) 
                                            {
                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                    }
                               if (request.getParameter("boton").equals("Consulta"))
                                                    {
                                                        String idalma = request.getParameter("idalma");

                                                        try
                                                            {
                                                                String query1 = "select * from medicamento where idalma = '"+idalma+"'";
                                                                ps1 = c.prepareStatement(query1);
                                                                ResultSet r = ps1.executeQuery();
                                                                
                                                                if (r.next())
                                                                    {
                                                                        try 
                                                                            {
                                                                               String nombrem, factiva, lab, tipo, porcion, vadmi, uso, res, cadu, cant, tipoc;
                                                                                nombrem = r.getString("nombrem");
                                                                                factiva = r.getString("factiva");
                                                                                lab = r.getString("lab");
                                                                                tipo = r.getString("tipo");
                                                                                porcion = r.getString("porcion");
                                                                                vadmi = r.getString("vadmi");
                                                                                uso = r.getString("uso");
                                                                                res = r.getString("res");
                                                                                cadu = r.getString("cadu");
                                                                                cant = r.getString("cant");
                                                                                tipoc = r.getString("tipoc");
                                                                                
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
                                                                                out.println("<th>Id Almacen</th>");
                                                                                out.println("<td>"+idalma+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Nombre Medicamento</th>");
                                                                                out.println("<td>"+nombrem+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Fórmula Activa</th>");
                                                                                out.println("<td>"+factiva+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Laboratorio</th>");
                                                                                out.println("<td>"+lab+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Tipo</th>");
                                                                                out.println("<td>"+tipo+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<th>Porción</th>");
                                                                                out.println("<td>"+porcion+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Vía de Administración</th>");
                                                                                out.println("<td>"+vadmi+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Uso</th>");
                                                                                out.println("<td>"+uso+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Restrcción</th>");
                                                                                out.println("<td>"+res+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Caducidad</th>");
                                                                                out.println("<td>"+cadu+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Cantidad</th>");
                                                                                out.println("<td>"+cant+"&nbsp"+tipoc+"</td>");
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
                                                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                    }
                                                                else
                                                                    {   
                                                                        String query2 = "select * from material where idalma = '"+idalma+"'";
                                                                        ps2 = c.prepareStatement(query2);
                                                                         ResultSet r2 = ps2.executeQuery();
                                                                         
                                                                         if(r2.next()){
                                                                         
                                                                             try 
                                                                                 {
                                                                                String nombrem, marca, uso, cant;
                                                                                nombrem = r2.getString("nombrem");
                                                                                marca = r2.getString("marca");
                                                                                uso =r2.getString("uso");
                                                                                cant = r2.getString("cant");
                                                                                
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
                                                                                out.println("<th>Id Almacen</th>");
                                                                                out.println("<td>"+idalma+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Nombre Medicamento</th>");
                                                                                out.println("<td>"+nombrem+"</td>");
                                                                                out.println("</tr>");
                                                                                 out.println("<tr>");
                                                                                out.println("<th>Marca</th>");
                                                                                out.println("<td>"+marca+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Uso</th>");
                                                                                out.println("<td>"+uso+"</td>");
                                                                                out.println("</tr>");
                                                                                out.println("<tr>");
                                                                                out.println("<th>Cantidad</th>");
                                                                                out.println("<td>"+cant+"</td>");
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
                                                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                                            }
                                                                    }
                                                                    else{
                                                                        out.println("<script>");
                                                                        out.println("alert(\"No registrado\");");
                                                                        out.println("</script>");
                                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                                                 
                                                                        }
                                                                    }
                                                            }
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                    }
                               if (request.getParameter("boton").equals("Actualizar"))
                                    {
                                        String idalma = request.getParameter("idalma");
                                        String nombrem = request.getParameter("nombrem");
                                        String factiva = request.getParameter("factiva");
                                        String lab = request.getParameter("lab");
                                        String tipo = request.getParameter("tipo");
                                        String porcion = request.getParameter("porcion");
                                        String vadmi = request.getParameter("vadmi");
                                        String uso = request.getParameter("uso");
                                        String res = request.getParameter("res");
                                        String cadu = request.getParameter("cadu");
                                        String cant = request.getParameter("cant");
                                        String tipoc = request.getParameter("tipoc");
                                                               
                                        try
                                             {
                                                String query1 = "update medicamento set nombrem = ?, factiva = ?, lab = ?, tipo = ?, porcion = ?, vadmi = ?, uso = ?, res = ?, cadu = ?, cant = ?, tipoc = ? where idalma = ?";
                                                ps1 = c.prepareStatement(query1);
                                                ps1.setString(1,nombrem);
                                                ps1.setString(2,factiva);
                                                ps1.setString(3,lab);
                                                ps1.setString(4,tipo);
                                                ps1.setString(5,porcion);
                                                ps1.setString(6,vadmi);
                                                ps1.setString(7,uso);
                                                ps1.setString(8,res);
                                                ps1.setString(9,cadu);
                                                ps1.setString(10,cant);
                                                ps1.setString(11,tipoc);
                                                ps1.setString(12,idalma);
                                                ps1.executeUpdate();
                                                out.println("<script>");
                                                out.println("alert(\"Datos Modificados Exitosamente\");");
                                                out.println("</script>");
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://localhost:39055/MedPrueba1/dentista.html'/>");
                                               }
                                        catch (SQLException ex) 
                                             {
                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                             }
                                   }
                        if (request.getParameter("boton").equals("Actualizar Material"))
                                    {
                                        String idalma = request.getParameter("idalma");
                                        String nombrem = request.getParameter("nombrem");
                                        String marca = request.getParameter("marca");
                                        String uso = request.getParameter("uso");
                                        String cant = request.getParameter("cant");
                                        
                                        try
                                             {
                                                String query = "update material set nombrem = ?, marca = ?, uso = ?, cant = ? where idalma = ?";
                                                ps2 = c.prepareStatement(query);
                                                ps2.setString(1,nombrem);
                                                ps2.setString(2,marca);
                                                ps2.setString(3,uso);
                                                ps2.setString(4,cant);
                                                ps2.setString(5,idalma);
                                                ps2.executeUpdate();
                                                out.println("<script>");
                                                out.println("alert(\"Datos Modificados Exitosamente\");");
                                                out.println("</script>");
                                                 out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                               }
                                        catch (SQLException ex) 
                                             {
                                                Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                             }
                                   }
                               
                        if (request.getParameter("boton").equals("Consultar Medicamentos"))
                                {
                                  try
                                    {
                                        String query1 = "select * from medicamento";
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
                                            out.print("ID Almacen");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Nombre");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Fórmula Activa");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Laboratorio");
                                            out.print("</th>");
                                             out.print("<th>");
                                            out.print("Tipo");
                                            out.print("</th>");
                                             out.print("<th>");
                                            out.print("Caducidad");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idalma = r.getString("idalma");
                                            String nombrem = r.getString("nombrem");
                                            String factiva = r.getString("factiva");
                                            String lab = r.getString("lab");
                                            String tipo = r.getString("tipo");
                                            String cadu = r.getString("cadu");
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idalma);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(nombrem);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(factiva);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(lab);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(tipo);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(cadu);
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
                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                }
                        if (request.getParameter("boton").equals("Consultar Material"))
                                {
                                  try
                                    {
                                        String query1 = "select * from material";
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
                                            out.print("ID Almacen");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Nombre Material");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Marca");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Uso");
                                            out.print("</th>");
                                             out.print("<th>");
                                            out.print("Cantidad");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idalma = r.getString("idalma");
                                            String nombrem = r.getString("nombrem");
                                            String marca = r.getString("marca");
                                            String uso = r.getString("uso");
                                            String cant = r.getString("cant");
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idalma);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(nombrem);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(marca);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(uso);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(cant);
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
                                        Logger.getLogger(basealmacen.class.getName()).log(Level.SEVERE, null, ex);
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