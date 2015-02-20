package serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Claudia
 */
@WebServlet(name = "baseconsultas", urlPatterns = {"/baseconsultas"})
public class baseconsultas extends HttpServlet {
        private final String based = "medical"; 
        private final String user = "root";
        private final String pass = "n0m3l0";
        private final String url = "jdbc:mysql://localhost/";
        private final String driver = "com.mysql.jdbc.Driver";
        private final String puerto = "39055";
        //private final String puerto = "39055";
        //private final String ip = "";
        private final String ip = "localhost";
        private Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        String idmedico;                    
        ArrayList Antibioticos = new ArrayList();
        ArrayList Analgesicos = new ArrayList();
        ArrayList Antiestaminicos = new ArrayList();
        ArrayList Antisepticos = new ArrayList();
        ArrayList Laxantes = new ArrayList();
        ArrayList Desinfectante = new ArrayList();
        

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
                        
                        if (request.getParameter("boton").equals("Siguiente"))
                                {                                                                        
                                        HttpSession lasesion = request.getSession();

                                        String idcon = request.getParameter("idconsulta");    
                                        String fecha = request.getParameter("fecha");
                                        String boleta = request.getParameter("boleta");
                                        String grupo = request.getParameter("grupo");
                                        String edad = request.getParameter("edad");
                                        String peso = request.getParameter("peso");
                                        String talla = request.getParameter("talla");
                                        String temp = request.getParameter("temp");
                                        String sint = request.getParameter("sint");
                                        String trata = request.getParameter("trata");
                                        String observa = request.getParameter("observa");
                                        String ntrabajador = request.getParameter("ntrabajador");                                                                        
                                        lasesion.setAttribute("fecha",fecha);
                                        lasesion.setAttribute("ntrabajador",ntrabajador);
                                        lasesion.setAttribute("boleta",boleta);
                                        lasesion.setAttribute("grupo",grupo);
                                        lasesion.setAttribute("edad",edad);
                                        lasesion.setAttribute("peso",peso);
                                        lasesion.setAttribute("talla",talla);
                                        lasesion.setAttribute("sintomas",sint);
                                        lasesion.setAttribute("temperatura",temp);
                                        lasesion.setAttribute("tratamiento",trata);                                                                        
                                        lasesion.setAttribute("observaciones",observa);                                                
                                    try
                                       {
                                            String query3 = "select * from consultas";
                                            ps3 = c.prepareStatement(query3);
                                            ResultSet r3 = ps3.executeQuery(); 
                                            
                                            lasesion.setAttribute("idconsulta",idcon);

                                                out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 1</p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregar\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");  
                                    }    
                                catch(SQLException ex) 
                                    {
                                        out.println("<script>alert(\"baka V:\");</script>");
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                        }    
                        
                        
                        if (request.getParameter("boton").equals("Avanzar"))
                            {
                                String boleta = request.getParameter("boleta");

                                                try
                                                    {
                                                        String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                                        ps1 = c.prepareStatement(query1);
                                                        ResultSet r = ps1.executeQuery();                                                        
                                                        String idcon="";
                                                        int idconsulta=0;
                                                        String query3 = "select * from consultas";
                                                        ps3 = c.prepareStatement(query3);
                                                        ResultSet r3 = ps3.executeQuery();                                                        
                                                        if(r3.next())
                                                            {
                                                                while(r3.next())
                                                                    {
                                                                        idcon = r3.getString("idconsulta");
                                                                    }                                                                
                                                                idcon = idcon+"1";
                                                            }
                                                        else
                                                            {                                                                
                                                                idcon = "0";
                                                            }
                                                        if (r.next())
                                                            {
                                                                try 
                                                                    {                                                                                                                                                                                                                                                                                                
                                                                        HttpSession lasesion = request.getSession();
                                                                        String idm = (String) lasesion.getAttribute("sid");                                                                                                                                                
                                                                        Calendar fecha = new GregorianCalendar();        
                                                                        int año = fecha.get(Calendar.YEAR);
                                                                        int mes = fecha.get(Calendar.MONTH);
                                                                        int dia = fecha.get(Calendar.DAY_OF_MONTH);
                                                                        int hora = fecha.get(Calendar.HOUR_OF_DAY);
                                                                        int minuto = fecha.get(Calendar.MINUTE);
                                                                        int segundo = fecha.get(Calendar.SECOND);
                                                                        String con;
                                                                        con = r.getString("con");                                                                          
                                                                        out.println("<head>");                                                                                                                                                                                                                                  
                                                                            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                                            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                                            out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                                            out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                                            out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");
                                                                            out.println("<script>");
                                                                                out.println("$(document).ready(function() { ");
                                                                                    out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                                                    out.println("$('.callback').html(e);");
                                                                                    out.println("});");
                                                                                    out.println("});");
                                                                                out.println("function cargar(div, desde)");
                                                                                    out.println("{");
                                                                                        out.println("$(div).load(desde);");
                                                                                    out.println("}");
                                                                            out.println("</script>");  
                                                                             out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                                                        out.println("</head>");
                                                                        out.println("<body>");                                                                                             
                                                                            out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                                            out.println("<p style=\"margin-top: 0px;\">Nueva Receta</p>");
                                                                            out.println("</div>");
                                                                        out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");
                                                                            out.println("<img src=\"css/imagen/date.png\" width=\"30px\" height=\"30px\" alt=\"\"/>");
                                                                            out.println("Datos Generales");
                                                                            out.println("<hr>");
                                                                            out.println("ID Consulta:");
                                                                            out.println("<input type=\"text\" value="+idcon+" name=\"idconsulta\"require=\"true\" label=\"IDconsulta\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" readonly />");
                                                                            out.println("Fecha:");
                                                                            out.println("<input type=\"text\" value="+dia+"/"+(mes+1)+"/"+año+" name=\"fecha\" size=\"30\" style=\"margin:15px;\" maxlength=\"15\" readonly/>");                                                                            
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<img src=\"css/imagen/dentista.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                                            out.println("Datos del Dentista");                                                                            
                                                                            out.println("<hr>");
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<img src=\"css/imagen/profile.png\" alt=\"\"/>");
                                                                            out.println("Número de Trabajador Dentista:");
                                                                            out.println("<input type=\"text\" value="+idm+" name=\"ntrabajador\" require=\"true\" label=\"Numero de Trabajador\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" readonly /> ");
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<img src=\"css/imagen/alumno.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                                            out.println("Datos del Alumno");
                                                                            out.println("<hr>");
                                                                            out.println("<br>");
                                                                            out.println("<img src=\"css/imagen/profile.png\" alt=\"\"/>");
                                                                            out.println("Boleta");
                                                                            out.println("<input type=\"text\" value="+boleta+" name=\"boleta\"  require=\"true\" label=\"Boleta\" size=\"30\" style=\"margin:15px;\" maxlength=\"10\" onKeyPress=\"return Numeros(event)\" readonly />");                                         
                                                                            out.println("<br>");
                                                                            out.println("Grupo:");
                                                                            out.println("<input type=\"text\" name=\"grupo\" size=\"11\" style=\"margin:15px;\" maxlength=\"4\" required/> ");
                                                                            out.println("Edad:");
                                                                            out.println("<input type=\"text\" name=\"edad\" size=\"11\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/> ");
                                                                            out.println("Peso:");
                                                                            out.println("<input type=\"text\" name=\"peso\" size=\"11\" style=\"margin:15px;\" maxlength=\"3\" onKeyPress=\"return Numeros(event)\" required/>    ");
                                                                            out.println("Talla:");
                                                                            out.println("<input type=\"text\" name=\"talla\" size=\"11\" style=\"margin:15px;\" maxlength=\"4\" required/>  ");
                                                                            out.println("Temperatura:");
                                                                            out.println("<select id=\"temperatura\" name=\"temp\">");
                                                                            out.println("<option value=\"si\">Si</option>");
                                                                            out.println("<option value=\"no\">No</option>");
                                                                            out.println("</select>");
                                                                            out.println("<br>");
                                                                            out.println("<textarea name=\"sint\" rows=\"12\" cols=\"45\" place=\"Síntomas\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");
                                                                            out.println("<textarea name=\"trata\" rows=\"12\" cols=\"45\" place=\"Tratamiento\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");
                                                                            out.println("<textarea name=\"observa\" rows=\"10\" cols=\"100\" place=\"Observaciones\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");                                                                                                                                                        
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<center>");
                                                                            out.println("<input type=\"submit\" name=\"boton\" value=\"Siguiente\" /><input type=\"reset\" value=\"Limpiar\" />");
                                                                            out.println("</center>");
                                                                        out.println("</form>");                                                                        
                                                                        out.println("</body>");                                                                                                                                                                    
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Alumno no registrado!</h2><p style='display: block;'>Tienes que registrar previamente al alumno</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color:white; text-decoration: none;\">OK</a></button></div>");
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
                        
                        
                        if (request.getParameter("boton").equals("Terminar receta"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                String fecha = (String)lasesion.getAttribute("fecha");
                                String boleta = (String)lasesion.getAttribute("boleta");
                                String grupo = (String)lasesion.getAttribute("grupo");
                                String edad = (String)lasesion.getAttribute("edad");
                                String peso = (String)lasesion.getAttribute("peso");
                                String talla = (String)lasesion.getAttribute("talla");
                                String temp = (String)lasesion.getAttribute("temperatura");
                                String trata = (String)lasesion.getAttribute("tratamiento");                                                                        
                                String sint = (String)lasesion.getAttribute("sintomas");
                                String observa = (String)lasesion.getAttribute("observaciones");
                                String ntrabajador = (String)lasesion.getAttribute("ntrabajador");                                                                
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from consultas where idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                      /*    out.println("<script>");
                                                                out.println("alert(\"¡Cambia el ID de la Consulta!\");");
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>ID de Consulta no valido!</h2><p style='display: block;'>Cambia el ID de Consulta</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                        
                                                        String query2 = "INSERT INTO consultas VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       

                                                        ps2 = c.prepareStatement(query2);
                                                        ps3 = c.prepareStatement(query3);
                                                        ps2.setString(1,idconsulta);
                                                        ps2.setString(2,fecha);
                                                        ps2.setString(3,boleta);
                                                        ps2.setString(4,grupo);
                                                        ps2.setString(5,edad);
                                                        ps2.setString(6,peso); 
                                                        ps2.setString(7,talla); 
                                                        ps2.setString(8,temp);
                                                        ps2.setString(9,sint);
                                                        ps2.setString(10,trata);
                                                        ps2.setString(11,observa);
                                                        ps2.setString(12,ntrabajador);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps2.executeUpdate();
                                                        ps3.executeUpdate();

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
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Consulta registrada!</h2><p style='display: block;'>Has registrado una nueva consulta con &eacute;xito</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                           }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        /*out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");
                                                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                        */
                                                out.println("<head>");                                              
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                out.println("$(document).ready(function() { ");
                                                out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                out.println("$('.callback').html(e);");
                                                out.println("});");
                                                out.println("});");
                                                out.println("function cargar(div, desde)");
                                                out.println("{");
                                                out.println("$(div).load(desde);");
                                                out.println("}");
                                                out.println("</script>");  
                                                out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");

                                    }
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                        
                       
                    if (request.getParameter("boton").equals("Terminar la receta"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from conmed where idalma = '"+idalma+"' and idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                /*
                                                out.println("<script>");
                                                out.println("alert(\"Ya ingreso este medicamento\");");
                                                out.println("</script>");
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                                */
                                                out.println("<head>");                                              
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                out.println("$(document).ready(function() { ");
                                                out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                out.println("$('.callback').html(e);");
                                                out.println("});");
                                                out.println("});");
                                                out.println("function cargar(div, desde)");
                                                out.println("{");
                                                out.println("$(div).load(desde);");
                                                out.println("}");
                                                out.println("</script>");  
                                                out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                                
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                                                                                
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       
                                                        
                                                        ps3 = c.prepareStatement(query3);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);
                                                        
                                                        ps3.executeUpdate();

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
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Consulta registrada!</h2><p style='display: block;'>Has registrado una nueva consulta con &eacute;xito</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                         
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        /*
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");
                                        */
                                                out.println("<head>");                                              
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                out.println("$(document).ready(function() { ");
                                                out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                out.println("$('.callback').html(e);");
                                                out.println("});");
                                                out.println("});");
                                                out.println("function cargar(div, desde)");
                                                out.println("{");
                                                out.println("$(div).load(desde);");
                                                out.println("}");
                                                out.println("</script>");  
                                                out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");                                        
                                    }
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }   
                                         
                        
                        if (request.getParameter("boton").equals("Agregar"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                String fecha = (String)lasesion.getAttribute("fecha");
                                String boleta = (String)lasesion.getAttribute("boleta");
                                String grupo = (String)lasesion.getAttribute("grupo");
                                String edad = (String)lasesion.getAttribute("edad");
                                String peso = (String)lasesion.getAttribute("peso");
                                String talla = (String)lasesion.getAttribute("talla");
                                String temp = (String)lasesion.getAttribute("temperatura");
                                String trata = (String)lasesion.getAttribute("tratamiento");                                                                        
                                String sint = (String)lasesion.getAttribute("sintomas");
                                String observa = (String)lasesion.getAttribute("observaciones");
                                String ntrabajador = (String)lasesion.getAttribute("ntrabajador");                                                                
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                                try 
                                                    {                                                                                                                        
                                                        String query2 = "INSERT INTO consultas VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       

                                                        ps2 = c.prepareStatement(query2);
                                                        ps3 = c.prepareStatement(query3);
                                                        ps2.setString(1,idconsulta);
                                                        ps2.setString(2,fecha);
                                                        ps2.setString(3,boleta);
                                                        ps2.setString(4,grupo);
                                                        ps2.setString(5,edad);
                                                        ps2.setString(6,peso); 
                                                        ps2.setString(7,talla); 
                                                        ps2.setString(8,temp);
                                                        ps2.setString(9,sint);
                                                        ps2.setString(10,trata);
                                                        ps2.setString(11,observa);
                                                        ps2.setString(12,ntrabajador);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps2.executeUpdate();
                                                        ps3.executeUpdate();

                                                        out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 2</p>");
                                                out.println("</div>");
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");                                                
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar la receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregarlo\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            
                                    }
                                else
                                    {
                                        /*
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");
                                        */
                                                out.println("<head>");                                              
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                out.println("$(document).ready(function() { ");
                                                out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                out.println("$('.callback').html(e);");
                                                out.println("});");
                                                out.println("});");
                                                out.println("function cargar(div, desde)");
                                                out.println("{");
                                                out.println("$(div).load(desde);");
                                                out.println("}");
                                                out.println("</script>");  
                                                out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                    }
                                        
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                
                        if (request.getParameter("boton").equals("Agregarlo"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from conmed where idalma='"+idalma+"' and idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                /*
                                                out.println("<script>");
                                                out.println("alert(\"YA ingreso este medicamento en esta receta\");");
                                                out.println("</script>");
                                                */
                                                                                                out.println("<head>");                                              
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                out.println("$(document).ready(function() { ");
                                                out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                out.println("$('.callback').html(e);");
                                                out.println("});");
                                                out.println("});");
                                                out.println("function cargar(div, desde)");
                                                out.println("{");
                                                out.println("$(div).load(desde);");
                                                out.println("}");
                                                out.println("</script>");  
                                                out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                                                                                
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       
                                                        
                                                        ps3 = c.prepareStatement(query3);
                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps3.executeUpdate();

                                                        out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos N</p>");
                                                out.println("</div>");
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");                                                
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar la receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregarlo\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        out.println("<head>");                                                
	out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
	out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
	out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
	out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
	out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
	out.println("<script>");
	out.println("$(document).ready(function() { ");
	out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
	out.println("$('.callback').html(e);");
	out.println("});");
	out.println("});");
	out.println("function cargar(div, desde)");
	out.println("{");
	out.println("$(div).load(desde);");
	out.println("}");
	out.println("</script>");  
	out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
out.println("</head>");
out.println("<body>");                                                                                             
	out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
	out.println("<p style=\"margin-top: 0px;\">Medicamento no exisente - Regrese </p>");
	out.println("</div>");                                                                                                                                   
out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
	out.println("<br><br><br>");        
	out.println("<center>");
	out.println("<input type=\"submit\" name=\"boton\" value=\"Reintentar\" />");
	out.println("</center>");
out.println("</form>");                                                                        
out.println("</body>");  
                                           /*  out.println("<script>");
                                                        out.println("alert(\"Consulta no registrada\");");
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
                                                        out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error animateErrorIcon' style='display: block;'><span class='x-mark animateXMark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success' style='display: none;'> <span class='line tip'></span> <span class='line long'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/MedPrueba1/css/imagen/thumbs-up.jpg);'></div> <h2>Consulta no registrada!</h2><p style='display: block;'>Tienes que registrar previamente la consulta</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                         out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                        
                                    }                                        
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                    }
                        
                        
                        if (request.getParameter("boton").equals("Reintentar"))
                                {                                                                        
                                        HttpSession lasesion = request.getSession();
                                        String idcon = request.getParameter("idconsulta");    
                        
                                                                             
                                            lasesion.setAttribute("idconsulta",idcon);

                                                out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 1</p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar la receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregarlo\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");  
                                    }                        
                        
                
                        
                        if (request.getParameter("boton").equals("Buscar"))
                                {
                                    String boleta = request.getParameter("boleta");

                                    try
                                     {
                                        String query1 = "select * from consultas where boleta = '"+boleta+"'";                                        
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
                                            out.print("ID Consulta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Fecha");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Boleta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Dentista");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Tratamiento");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idconsulta = r.getString("idconsulta");
                                            String ntrabajador = r.getString("ntrabajador");
                                            String trata = r.getString("trata");
                                            String fecha = r.getString("fecha");                                            
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idconsulta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(fecha);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(boleta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(ntrabajador);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(trata);
                                            out.print("</td>");                                            
                                            out.print("</tr>");
                                        }
                                       out.println("</tbody>");
                                       out.println("</table>");
                                       out.println("</div>");
                                       out.println("<div class=\"regresar\">");
                                       out.println("<button name=\"regresar\" class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                       out.println("<a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color: white; text-decoration: none\">");
                                       out.println("Regresar");
                                       out.println("</a>");
                                       out.println("</button>");
                                       out.println("</div>");
                                    }
                                 catch (SQLException ex) 
                                    {
                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                }
                        if (request.getParameter("boton").equals("Reporte"))
                                {
                                    
                                    try
                                    {
                                        String query1 = "select * from consultas";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        out.println("<head>");
                                        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/login.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ladda.css\" />");
                                        out.println("<script src=\"js/imprimir.js\" type=\"text/javascript\"></script>");
                                        out.println("</head>");
                                        out.println("<div class=\"component\">");
                                        out.println("<table>");
                                            out.println("<thead>");
                                            out.println("<tr>");
                                            out.print("<th>");
                                            out.print("ID Consultas");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Fecha");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Boleta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Dentista");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idconsulta = r.getString("idconsulta");
                                            String fecha = r.getString("fecha");
                                            String boleta = r.getString("boleta");
                                            String ntrabajador = r.getString("ntrabajador");
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idconsulta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(fecha);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(boleta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(ntrabajador);
                                            out.print("</td>");
                                            out.print("</tr>");
                                        }
                                       out.println("</tbody>");
                                       out.println("</table>");
                                       out.println("</div>");
                                       out.println("<div class=\"regresar\">");
                                       out.println("<button name=\"regresar\" class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                       out.println("<a href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;'  style=\"color: white; text-decoration: none\">");
                                       out.println("Regresar");
                                       out.println("</a>");
                                       out.println("</button>");
                                       out.println("</div>");
                                       
                                       out.println("</div>");
                                       out.println("<div class=\"regresar\" style=\"left: 45%\" >");
                                       out.println("<button name=\"imprimir\" value=\"imprimir\"  onClick=\"return printPage2()\"class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                       out.println("<a href=\"#\" onClick=\"return printPage2()\" style=\"color: white; text-decoration: none\">");
                                       out.println("Imprimir");
                                       out.println("</a>");
                                       out.println("</button>");
                                       out.println("</div>");
                                    }
                                 catch (SQLException ex) 
                                    {
                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
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


















/*
package serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "baseconsultas", urlPatterns = {"/baseconsultas"})
public class baseconsultas extends HttpServlet {
        private final String based = "medical"; 
        private final String user = "root";
        private final String pass = "n0m3l0";
        private final String url = "jdbc:mysql://localhost/";
        private final String driver = "com.mysql.jdbc.Driver";
        private final String puerto = "39055";
        //private final String puerto = "39055";
        //private final String ip = "";
        private final String ip = "localhost";
        private Connection c = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        String idmedico;                    

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
                        
                        if (request.getParameter("boton").equals("Siguiente"))
                                {                                                                        
                                        HttpSession lasesion = request.getSession();

                                        String idcon = request.getParameter("idconsulta");    
                                        String fecha = request.getParameter("fecha");
                                        String boleta = request.getParameter("boleta");
                                        String grupo = request.getParameter("grupo");
                                        String edad = request.getParameter("edad");
                                        String peso = request.getParameter("peso");
                                        String talla = request.getParameter("talla");
                                        String temp = request.getParameter("temp");
                                        String sint = request.getParameter("sint");
                                        String trata = request.getParameter("trata");
                                        String observa = request.getParameter("observa");
                                        String ntrabajador = request.getParameter("ntrabajador");                                                                        
                                        lasesion.setAttribute("fecha",fecha);
                                        lasesion.setAttribute("ntrabajador",ntrabajador);
                                        lasesion.setAttribute("boleta",boleta);
                                        lasesion.setAttribute("grupo",grupo);
                                        lasesion.setAttribute("edad",edad);
                                        lasesion.setAttribute("peso",peso);
                                        lasesion.setAttribute("talla",talla);
                                        lasesion.setAttribute("sintomas",sint);
                                        lasesion.setAttribute("temperatura",temp);
                                        lasesion.setAttribute("tratamiento",trata);                                                                        
                                        lasesion.setAttribute("observaciones",observa);                                                
                                    try
                                       {
                                            String query3 = "select * from consultas";
                                            ps3 = c.prepareStatement(query3);
                                            ResultSet r3 = ps3.executeQuery(); 
                                            
                                            lasesion.setAttribute("idconsulta",idcon);

                                                out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");
                                                /*
                                                out.println("<script type=\"text/javascript\">");                                                                                                
                                                out.println("function cargarSelect2(valor)");
                                                    out.println("{");		
                                                    out.println("var arrayValores=new Array(");
                                                    out.println("new Array(1,1,\"opcion1-1\"),");
                                                    out.println("new Array(1,2,\"opcion1-2\"),");
                                                    out.println("new Array(1,3,\"opcion1-3\"),");
                                                    out.println("new Array(2,1,\"baka\"),");
                                                    out.println("new Array(3,1,\"opcion3-1\"),");
                                                    out.println("new Array(3,2,\"opcion3-2\"),");
                                                    out.println("new Array(3,3,\"opcion3-3\"),");
                                                    out.println("new Array(3,4,\"opcion3-4\")");
                                                    out.println(");");
                                                    out.println("if(valor==0)");
                                                    out.println("{");
                                                    out.println("document.getElementById(\"select2\").disabled=true;");
                                                    out.println("}");
                                                    out.println("else");
                                                    out.println("{");        
                                                    out.println("document.getElementById(\"select2\").options.length=0;");
                                                    out.println("document.getElementById(\"select2\").options[0]=new Option(\"Selecciona una opcion\",\"0\");");
                                                    out.println("for(i=0;i<arrayValores.length;i++)");
                                                    out.println("{");
                                                    out.println("if(arrayValores[i][0]==valor)");
                                                    out.println("{");
                                                    out.println("document.getElementById(\"select2\").options[document.getElementById(\"select2\").options.length]=new Option(arrayValores[i][2], arrayValores[i][1]);");
                                                    out.println("}");
                                                    out.println("}");
                                                    out.println("document.getElementById(\"select2\").disabled=false;");
                                                    out.println("}");
                                                    out.println("}");
                                                    out.println("function seleccinado_select2(value)");
                                                    out.println("{");
                                                    out.println("var v1 = document.getElementById(\"select1\");");
                                                    out.println("var valor1 = v1.options[v1.selectedIndex].value;");
                                                    out.println("var text1 = v1.options[v1.selectedIndex].text;");
                                                    out.println("var v2 = document.getElementById(\"select2\");");
                                                    out.println("var valor2 = v2.options[v2.selectedIndex].value;");
                                                    out.println("var text2 = v2.options[v2.selectedIndex].text;");		
                                                    out.println("alert(\"Se ha seleccionado el valor \"+valor1+\" (\"+text1+\") del primer select y el valor \"+valor2+\" (\"+text2+\") del segundo select\");");
                                                    out.println("}");        
                                                out.println("</script>");*//*
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 1</p>");
                                                out.println("</div>");
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");
                                                /*                                                
                                                out.println("<p>");
                                                out.println("<select id='select1' onchange='cargarSelect2(this.value);'>");
                                                out.println("<option value='0'>Selecciona una opcion</option>");
                                                out.println("<option value='1'>opcion 1</option>");
                                                out.println("<option value='2'>opcion 2</option>");
                                                out.println("<option value='3'>opcion 3</option>");
                                                out.println("</select>");
                                                out.println("</p>");
                                                out.println("<p>");
                                                out.println("<select id='select2' onchange='seleccinado_select2();' disabled>");
                                                out.println("<option value='0'>Selecciona una opcion</option>");
                                                out.println("</select>");
                                                out.println("</p>");
                                                *//*
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregar\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");  
                                    }    
                                catch(SQLException ex) 
                                    {
                                        out.println("<script>alert(\"baka V:\");</script>");
                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                        }    
                        
                        
                        if (request.getParameter("boton").equals("Avanzar"))
                            {
                                String boleta = request.getParameter("boleta");

                                                try
                                                    {
                                                        String query1 = "select * from alumnos where boleta = '"+boleta+"'";
                                                        ps1 = c.prepareStatement(query1);
                                                        ResultSet r = ps1.executeQuery();                                                        
                                                        String idcon="";
                                                        int idconsulta=0;
                                                        String query3 = "select * from consultas";
                                                        ps3 = c.prepareStatement(query3);
                                                        ResultSet r3 = ps3.executeQuery();                                                        
                                                        if(r3.next())
                                                            {
                                                                while(r3.next())
                                                                    {
                                                                        idcon = r3.getString("idconsulta");
                                                                    }                                                                
                                                                idcon = idcon+"1";
                                                            }
                                                        else
                                                            {                                                                
                                                                idcon = "0";
                                                            }
                                                        if (r.next())
                                                            {
                                                                try 
                                                                    {                                                                                                                                                                                                                                                                                                
                                                                        HttpSession lasesion = request.getSession();
                                                                        String idm = (String) lasesion.getAttribute("sid");                                                                                                                                                
                                                                        Calendar fecha = new GregorianCalendar();        
                                                                        int año = fecha.get(Calendar.YEAR);
                                                                        int mes = fecha.get(Calendar.MONTH);
                                                                        int dia = fecha.get(Calendar.DAY_OF_MONTH);
                                                                        int hora = fecha.get(Calendar.HOUR_OF_DAY);
                                                                        int minuto = fecha.get(Calendar.MINUTE);
                                                                        int segundo = fecha.get(Calendar.SECOND);
                                                                        String con;
                                                                        con = r.getString("con");                                                                          
                                                                        out.println("<head>");                                                                                                                                                                                                                                  
                                                                            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                                            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                                            out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                                            out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                                            out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");
                                                                            out.println("<script>");
                                                                                out.println("$(document).ready(function() { ");
                                                                                    out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                                                    out.println("$('.callback').html(e);");
                                                                                    out.println("});");
                                                                                    out.println("});");
                                                                                out.println("function cargar(div, desde)");
                                                                                    out.println("{");
                                                                                        out.println("$(div).load(desde);");
                                                                                    out.println("}");
                                                                            out.println("</script>");  
                                                                             out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                                                        out.println("</head>");
                                                                        out.println("<body>");                                                                                             
                                                                            out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                                            out.println("<p style=\"margin-top: 0px;\">Nueva Receta</p>");
                                                                            out.println("</div>");
                                                                        out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");
                                                                            out.println("<img src=\"css/imagen/date.png\" width=\"30px\" height=\"30px\" alt=\"\"/>");
                                                                            out.println("Datos Generales");
                                                                            out.println("<hr>");
                                                                            out.println("ID Consulta:");
                                                                            out.println("<input type=\"text\" value="+idcon+" name=\"idconsulta\"require=\"true\" label=\"IDconsulta\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" readonly />");
                                                                            out.println("Fecha:");
                                                                            out.println("<input type=\"text\" value="+dia+"/"+(mes+1)+"/"+año+" name=\"fecha\" size=\"30\" style=\"margin:15px;\" maxlength=\"15\" readonly/>");                                                                            
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<img src=\"css/imagen/dentista.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                                            out.println("Datos del Dentista");                                                                            
                                                                            out.println("<hr>");
                                                                            out.println("<br>");                                                                            
                                                                            out.println("<img src=\"css/imagen/profile.png\" alt=\"\"/>");
                                                                            out.println("Número de Trabajador Dentista:");
                                                                            out.println("<input type=\"text\" value="+idm+" name=\"ntrabajador\" require=\"true\" label=\"Numero de Trabajador\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" readonly /> ");
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<img src=\"css/imagen/alumno.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                                            out.println("Datos del Alumno");
                                                                            out.println("<hr>");
                                                                            out.println("<br>");
                                                                            out.println("<img src=\"css/imagen/profile.png\" alt=\"\"/>");
                                                                            out.println("Boleta");
                                                                            out.println("<input type=\"text\" value="+boleta+" name=\"boleta\"  require=\"true\" label=\"Boleta\" size=\"30\" style=\"margin:15px;\" maxlength=\"10\" onKeyPress=\"return Numeros(event)\" readonly />");                                         
                                                                            out.println("<br>");
                                                                            out.println("Grupo:");
                                                                            out.println("<input type=\"text\" name=\"grupo\" size=\"11\" style=\"margin:15px;\" maxlength=\"4\" required/> ");
                                                                            out.println("Edad:");
                                                                            out.println("<input type=\"text\" name=\"edad\" size=\"11\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/> ");
                                                                            out.println("Peso:");
                                                                            out.println("<input type=\"text\" name=\"peso\" size=\"11\" style=\"margin:15px;\" maxlength=\"3\" onKeyPress=\"return Numeros(event)\" required/>    ");
                                                                            out.println("Talla:");
                                                                            out.println("<input type=\"text\" name=\"talla\" size=\"11\" style=\"margin:15px;\" maxlength=\"4\" required/>  ");
                                                                            out.println("Temperatura:");
                                                                            out.println("<select id=\"temperatura\" name=\"temp\">");
                                                                            out.println("<option value=\"si\">Si</option>");
                                                                            out.println("<option value=\"no\">No</option>");
                                                                            out.println("</select>");
                                                                            out.println("<br>");
                                                                            out.println("<textarea name=\"sint\" rows=\"12\" cols=\"45\" place=\"Síntomas\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");
                                                                            out.println("<textarea name=\"trata\" rows=\"12\" cols=\"45\" place=\"Tratamiento\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");
                                                                            out.println("<textarea name=\"observa\" rows=\"10\" cols=\"100\" place=\"Observaciones\" style=\"margin: 15px;\" maxlength=\"100\"></textarea>");                                                                                                                                                        
                                                                            out.println("<br>");
                                                                            out.println("<br>");
                                                                            out.println("<center>");
                                                                            out.println("<input type=\"submit\" name=\"boton\" value=\"Siguiente\" /><input type=\"reset\" value=\"Limpiar\" />");
                                                                            out.println("</center>");
                                                                        out.println("</form>");                                                                        
                                                                        out.println("</body>");                                                                                                                                                                    
                                                                    }                                                            
                                                        catch (SQLException ex) 
                                                            {
                                                                Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                        }
                                                        else
                                                            {
                                                                out.println("<script>");
                                                                out.println("alert(\"¡Alumno no existente registrelo!\");");
                                                                out.println("</script>"); 
                                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                                            }
                                                    }
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(basealumnos.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                            }                        
                        
                        
                        if (request.getParameter("boton").equals("Terminar receta"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                String fecha = (String)lasesion.getAttribute("fecha");
                                String boleta = (String)lasesion.getAttribute("boleta");
                                String grupo = (String)lasesion.getAttribute("grupo");
                                String edad = (String)lasesion.getAttribute("edad");
                                String peso = (String)lasesion.getAttribute("peso");
                                String talla = (String)lasesion.getAttribute("talla");
                                String temp = (String)lasesion.getAttribute("temperatura");
                                String trata = (String)lasesion.getAttribute("tratamiento");                                                                        
                                String sint = (String)lasesion.getAttribute("sintomas");
                                String observa = (String)lasesion.getAttribute("observaciones");
                                String ntrabajador = (String)lasesion.getAttribute("ntrabajador");                                                                
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from consultas where idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                out.println("<script>");
                                                out.println("alert(\"Cambia el ID de Consulta plox\");");
                                                out.println("</script>");
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");                                                
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                        
                                                        String query2 = "INSERT INTO consultas VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       

                                                        ps2 = c.prepareStatement(query2);
                                                        ps3 = c.prepareStatement(query3);
                                                        ps2.setString(1,idconsulta);
                                                        ps2.setString(2,fecha);
                                                        ps2.setString(3,boleta);
                                                        ps2.setString(4,grupo);
                                                        ps2.setString(5,edad);
                                                        ps2.setString(6,peso); 
                                                        ps2.setString(7,talla); 
                                                        ps2.setString(8,temp);
                                                        ps2.setString(9,sint);
                                                        ps2.setString(10,trata);
                                                        ps2.setString(11,observa);
                                                        ps2.setString(12,ntrabajador);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps2.executeUpdate();
                                                        ps3.executeUpdate();

                                                        out.println("<script>");
                                                        out.println("alert(\"¡Consulta Registrada!\");");
                                                        out.println("</script>");
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");}
                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                        
                       
                    if (request.getParameter("boton").equals("Terminar la receta"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from conmed where idalma = '"+idalma+"' and idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                out.println("<script>");
                                                out.println("alert(\"Ya ingreso este medicamento\");");
                                                out.println("</script>");
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html/>");
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                                                                                
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       
                                                        
                                                        ps3 = c.prepareStatement(query3);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);
                                                        
                                                        ps3.executeUpdate();

                                                        out.println("<script>");
                                                        out.println("alert(\"¡Consulta Registrada!\");");
                                                        out.println("</script>");
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");}
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }   
                                         
                        
                        if (request.getParameter("boton").equals("Agregar"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                String fecha = (String)lasesion.getAttribute("fecha");
                                String boleta = (String)lasesion.getAttribute("boleta");
                                String grupo = (String)lasesion.getAttribute("grupo");
                                String edad = (String)lasesion.getAttribute("edad");
                                String peso = (String)lasesion.getAttribute("peso");
                                String talla = (String)lasesion.getAttribute("talla");
                                String temp = (String)lasesion.getAttribute("temperatura");
                                String trata = (String)lasesion.getAttribute("tratamiento");                                                                        
                                String sint = (String)lasesion.getAttribute("sintomas");
                                String observa = (String)lasesion.getAttribute("observaciones");
                                String ntrabajador = (String)lasesion.getAttribute("ntrabajador");                                                                
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                                try 
                                                    {                                                                                                                        
                                                        String query2 = "INSERT INTO consultas VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       

                                                        ps2 = c.prepareStatement(query2);
                                                        ps3 = c.prepareStatement(query3);
                                                        ps2.setString(1,idconsulta);
                                                        ps2.setString(2,fecha);
                                                        ps2.setString(3,boleta);
                                                        ps2.setString(4,grupo);
                                                        ps2.setString(5,edad);
                                                        ps2.setString(6,peso); 
                                                        ps2.setString(7,talla); 
                                                        ps2.setString(8,temp);
                                                        ps2.setString(9,sint);
                                                        ps2.setString(10,trata);
                                                        ps2.setString(11,observa);
                                                        ps2.setString(12,ntrabajador);

                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps2.executeUpdate();
                                                        ps3.executeUpdate();

                                                        out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 2</p>");
                                                out.println("</div>");
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");                                                
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar la receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregarlo\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            
                                    }
                                else
                                    {
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado\");");
                                        out.println("</script>");}
                                        
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                
                        if (request.getParameter("boton").equals("Agregarlo"))
                            {
                                HttpSession lasesion = request.getSession();                                
                                
                                String idconsulta = (String) lasesion.getAttribute("idconsulta");
                                
                                String idalma = request.getParameter("idalma");
                                String nombrem = request.getParameter("nombrem");
                                String factiva = request.getParameter("factiva");
                                String dur = request.getParameter("dur");
                                String tiem = request.getParameter("tiem");
                                String frec = request.getParameter("frec");
                                String tiemf = request.getParameter("tiemf");
                                String control = request.getParameter("control");
                                String cant = request.getParameter("cant");
                                String dosis = request.getParameter("dosis");                                                                
                                try
                                    {
                                        String query6 = "select * from medicamento where idalma = '"+idalma+"'";
                                        ps2 = c.prepareStatement(query6);
                                        ResultSet r2 = ps2.executeQuery();                                        
                                        if(r2.next())
                                            {                                                                                                                              
                                        String query1 = "select * from conmed where idalma='"+idalma+"' and idconsulta = '"+idconsulta+"'";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();                                        
                                        
                                        if (r.next())
                                            {
                                                out.println("<script>");
                                                out.println("alert(\"YA ingreso este medicamento en esta receta\");");
                                                out.println("</script>");                                                
                                            }
                                        else
                                            {
                                                try 
                                                    {                                                                                                                                                                                
                                                        String query3 = "INSERT INTO conmed VALUES(?,?,?,?,?,?,?,?,?,?,?)";                                                       
                                                        
                                                        ps3 = c.prepareStatement(query3);
                                                        ps3.setString(1,idalma);
                                                        ps3.setString(2,nombrem);
                                                        ps3.setString(3,factiva);
                                                        ps3.setString(4,dur);
                                                        ps3.setString(5,tiem);
                                                        ps3.setString(6,frec); 
                                                        ps3.setString(7,tiemf); 
                                                        ps3.setString(8,control);
                                                        ps3.setString(9,cant);
                                                        ps3.setString(10,dosis);
                                                        ps3.setString(11,idconsulta);

                                                        ps3.executeUpdate();

                                                        out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos N</p>");
                                                out.println("</div>");
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");                                                
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar la receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregarlo\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");
                                                    }  
                                                catch (SQLException ex) 
                                                    {
                                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                            }
                                    }
                                else
                                    {
                                        out.println("<script>");
                                            out.println("alert(\"Medicamento no registrado esta chafa\");");
                                        out.println("</script>");}                                        
                                }
                        catch (SQLException ex) 
                            {
                                Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                    }                                                                                                                                                                                                
                
                        
                        if (request.getParameter("boton").equals("Buscar"))
                                {
                                    String boleta = request.getParameter("boleta");

                                    try
                                     {
                                        String query1 = "select * from consultas where boleta = '"+boleta+"'";                                        
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
                                            out.print("ID Consulta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Fecha");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Boleta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Dentista");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Tratamiento");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idconsulta = r.getString("idconsulta");
                                            String ntrabajador = r.getString("ntrabajador");
                                            String trata = r.getString("trata");
                                            String fecha = r.getString("fecha");                                            
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idconsulta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(fecha);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(boleta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(ntrabajador);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(trata);
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
                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                }
                        if (request.getParameter("boton").equals("Reporte"))
                                {
                                    
                                    try
                                    {
                                        String query1 = "select * from consultas";
                                        ps1 = c.prepareStatement(query1);
                                        ResultSet r = ps1.executeQuery();
                                        out.println("<head>");
                                        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/normalize.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/login.css\" />");
                                        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/ladda.css\" />");
                                        out.println("<script src=\"js/imprimir.js\" type=\"text/javascript\"></script>");
                                        out.println("</head>");
                                        out.println("<div class=\"component\">");
                                        out.println("<table>");
                                            out.println("<thead>");
                                            out.println("<tr>");
                                            out.print("<th>");
                                            out.print("ID Consultas");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Fecha");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Boleta");
                                            out.print("</th>");
                                            out.print("<th>");
                                            out.print("Dentista");
                                            out.print("</th>");
                                            out.print("</tr>");
                                            out.println("</thead>");
                                            out.println("<tbody>");
                                            
                                       while (r.next())
                                        {
                                            String idconsulta = r.getString("idconsulta");
                                            String fecha = r.getString("fecha");
                                            String boleta = r.getString("boleta");
                                            String ntrabajador = r.getString("ntrabajador");
                                            out.println("<tr>");
                                            out.print("<td>");
                                            out.print(idconsulta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(fecha);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(boleta);
                                            out.print("</td>");
                                            out.print("<td>");
                                            out.print(ntrabajador);
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
                                       
                                       out.println("</div>");
                                       out.println("<div class=\"regresar\" style=\"left: 45%\" >");
                                       out.println("<button name=\"imprimir\" value=\"imprimir\"  onClick=\"return printPage2()\"class=\"button button-rounded button-flat-primary alumno colormedical ladda-button ladda-spinner gris\">");
                                       out.println("<a href=\"#\" onClick=\"return printPage2()\" style=\"color: white; text-decoration: none\">");
                                       out.println("Imprimir");
                                       out.println("</a>");
                                       out.println("</button>");
                                       out.println("</div>");
                                    }
                                 catch (SQLException ex) 
                                    {
                                        Logger.getLogger(baseconsultas.class.getName()).log(Level.SEVERE, null, ex);
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




*/
/*
                        if (request.getParameter("boton").equals("Reintentarlo"))
                                {                                                                        
                                        HttpSession lasesion = request.getSession();

                                        String idcon = request.getParameter("idconsulta");    
                                        String fecha = request.getParameter("fecha");
                                        String boleta = request.getParameter("boleta");
                                        String grupo = request.getParameter("grupo");
                                        String edad = request.getParameter("edad");
                                        String peso = request.getParameter("peso");
                                        String talla = request.getParameter("talla");
                                        String temp = request.getParameter("temp");
                                        String sint = request.getParameter("sint");
                                        String trata = request.getParameter("trata");
                                        String observa = request.getParameter("observa");
                                        String ntrabajador = request.getParameter("ntrabajador");                                                                        
                                        lasesion.setAttribute("fecha",fecha);
                                        lasesion.setAttribute("ntrabajador",ntrabajador);
                                        lasesion.setAttribute("boleta",boleta);
                                        lasesion.setAttribute("grupo",grupo);
                                        lasesion.setAttribute("edad",edad);
                                        lasesion.setAttribute("peso",peso);
                                        lasesion.setAttribute("talla",talla);
                                        lasesion.setAttribute("sintomas",sint);
                                        lasesion.setAttribute("temperatura",temp);
                                        lasesion.setAttribute("tratamiento",trata);                                                                        
                                        lasesion.setAttribute("observaciones",observa);                                                
                                    try
                                       {
                                            String query3 = "select * from consultas";
                                            ps3 = c.prepareStatement(query3);
                                            ResultSet r3 = ps3.executeQuery(); 
                                            
                                            lasesion.setAttribute("idconsulta",idcon);

                                                out.println("<head>");                                                
                                                out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> ");
                                                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");   
                                                out.println("<script src=\"js/jQuery.js\" type=\"text/javascript\"></script>");	
                                                out.println("<script type=\"text/javascript\" src=\"js/formly.js\"></script>");
                                                out.println("<script type=\"text/javascript\" src=\"js/Validaciones.js\"></script>");                                                
                                                out.println("<script>");
                                                    out.println("$(document).ready(function() { ");
                                                        out.println("$('#Info').formly({'theme':'Dark'}, function(e) { ");
                                                        out.println("$('.callback').html(e);");
                                                        out.println("});");
                                                        out.println("});");
                                                    out.println("function cargar(div, desde)");
                                                        out.println("{");
                                                            out.println("$(div).load(desde);");
                                                        out.println("}");
                                                out.println("</script>");  
                                                 out.println("<link rel=\"stylesheet\" href=\"css/formly.css\" type=\"text/css\"/>");
                                            out.println("</head>");
                                            out.println("<body>");                                                                                             
                                                out.println("<div class=\"tittle\" style=\"background-color: #B0991C\">");
                                                out.println("<p style=\"margin-top: 0px;\">Nueva Receta - Medicamentos 1</p>");
                                                out.println("</div>");                                                                                                                                   
                                            out.println("<br><br><form id=\"Info\" action=\"baseconsultas\" method=\"post\">");                                                                        
                                                out.println("<img src=\"css/imagen/medkit.png\" width=\"35px\" height=\"35px\" alt=\"\"/>");
                                                out.println("Medicamento");
                                                out.println("<hr>");
                                                out.println("<br>");
                                                out.println("ID Almacen:");
                                                out.println("<input type=\"text\" name=\"idalma\" size=\"30\" style=\"margin:15px;\" maxlength=\"20\" onKeyPress=\"return Numeros(event)\" required/>");                                                                        
                                                out.println("<br>");
                                                out.println("Nombre:");
                                                out.println("<input type=\"text\" name=\"nombrem\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/> ");
                                                out.println("Fórmula Activa:");
                                                out.println("<input type=\"text\" name=\"factiva\"size=\"30\" style=\"margin:15px;\" maxlength=\"40\" onKeyPress=\"return Letras(event)\" required/>");
                                                out.println("<br>");
                                                out.println("Duración:");
                                                out.println("<input type=\"text\" name=\"dur\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipod\" name=\"tiem\">");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Frecuencia:");
                                                out.println("<input type=\"text\" name=\"frec\"size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipof\" name=\"tiemf\">");
                                                out.println("<option value=\"dia\">Hora(s)</option>");
                                                out.println("<option value=\"dia\">Día(s)</option>");
                                                out.println("<option value=\"mes\">Mes(es)</option>");
                                                out.println("<option value=\"anio\">Año(s)</option>");
                                                out.println("</select>");
                                                out.println("Control:");
                                                out.println("<select id=\"control\" name=\"control\">");
                                                out.println("<option value=\"proporcionado\">Proporcionado</option>");
                                                out.println("<option value=\"recomendado\">Recomendado</option>");
                                                out.println("</select>");
                                                out.println("Cantidad:");
                                                out.println("<input type=\"text\" name=\"cant\" size=\"4\" style=\"margin:15px;\" maxlength=\"2\" onKeyPress=\"return Numeros(event)\" required/>");
                                                out.println("<select id=\"tipoc\" name=\"dosis\">");
                                                out.println("<option value=\"dia\">Caja(s)</option>");
                                                out.println("<option value=\"dia\">Dosís</option>");
                                                out.println("</select>");
                                                out.println("<br>");                                                                            
                                                out.println("<br>");
                                                out.println("<br><br><br>");        
                                                out.println("<center>");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Terminar receta\" />");
                                                out.println("<input type=\"submit\" name=\"boton\" value=\"Agregar\" />");
                                                out.println("<input type=\"reset\" value=\"Limpiar\" />");
                                                out.println("</center>");
                                            out.println("</form>");                                                                        
                                            out.println("</body>");  
                                    }                        
*/