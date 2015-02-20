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
        // private final String puerto = "8080";
        private final String puerto = "39055";
        //private final String ip = "";
        private final String ip = "localhost";
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
                                                        out.println("<script>");
                                                            out.println("alert(\"Bienvenido "+minombre+"\");");
                                                        out.println("</script>");
                                                        lasesion.setAttribute("sid", boleta );
                                                        lasesion.setAttribute("snombre", minombre );                                                        
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/alumno.html'/>");                                                     
                                                    }
                                                else
                                                    {
                                                        out.println("<script>");
                                                            out.println("alert(\"¡Contraseña incorrecta!\");");
                                                        out.println("</script>");                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'/>");                                                                                                               
                                                    }
                                            }
                                        else
                                            {
                                                out.println("<script>");
                                                    out.println("alert(\"¡EL usuario no existe!\");");
                                                out.println("</script>");                                                        
                                                  out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inicalumno.html'/>");                                                                                                                
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
                                                        out.println("<script>");
                                                            out.println("alert(\"Bienvenido Dentista "+minombre+"\");");
                                                        out.println("</script>");
                                                        lasesion.setAttribute("sid", idm );
                                                        lasesion.setAttribute("snombre", minombre );                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");                                                       
                                                    }
                                                else
                                                    {
                                                        out.println("<script>");
                                                            out.println("alert(\"¡Contraseña incorrecta!\");");
                                                        out.println("</script>");                                                        
                                                        out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/inientista.html'/>");                                                                                                                
                                                    }  
                                            }
                                        else
                                            {
                                                out.println("<script>");
                                                    out.println("alert(\"¡EL usuario no existe!\");");
                                                out.println("</script>");                                                        
                                                out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://localhost:39055/MedPrueba1/inientista.html'/>");                                                                                                                
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
                                out.println("<script>");
                                    out.println("alert(\"Hasta luego "+lasesion.getAttribute("snombre")+"\");");
                                out.println("</script>");                                
                                lasesion.invalidate();
                              out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/index.html'/>");           
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