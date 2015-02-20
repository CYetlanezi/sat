

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	
    <%@page import="java.sql.*,java.io.*"%>
        
        <%
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String año = request.getParameter("anio");
            String actividad = request.getParameter("actividad");
            String ip="127.0.0.2";
            String puerto="39055";
            
            if (dia == null);
            out.println(dia + dia.length());
            
            Connection con=null;
            Statement stm=null;
            
            try
                {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","n0m3l0");
                        stm=con.createStatement();
                        stm.executeUpdate("insert into calendari(dia,mes,anio,actividad)" 
                                        +"values('"+dia+"','"+mes+"','"+año+"','"+actividad+"')");
                                     
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
                                                       out.println("<div class='sweet-alert showSweetAlert visible' tabindex='-1' data-has-cancel-button='false' data-allow-ouside-click='false' data-has-done-function='false' style='display: block; margin-top: -169px;'><div class='icon error' style='display: none;'><span class='x-mark'><span class='line left'></span><span class='line right'></span></span></div><div class='icon warning' style='display: none;'> <span class='body'></span> <span class='dot'></span> </div> <div class='icon info' style='display: none;'></div> <div class='icon success animate' style='display: block;'> <span class='line tip animateSuccessTip'></span> <span class='line long animateSuccessLong'></span> <div class='placeholder'></div> <div class='fix'></div> </div> <div class='icon custom' style='display: none; width: 80px; height: 80px; background-image: url(http://localhost:39055/JSSweet/thumbs-up.jpg);'></div> <h2>Cita registrada!</h2><p style='display: block;'>Has registrado una nueva cita con &eacute;xito</p><button class='cancel' tabindex='2' style='display: none; box-shadow: none;'>Cancel</button><button class='confirm' tabindex='1' style='box-shadow: rgba(174, 222, 244, 0.8) 0px 0px 2px, rgba(0, 0, 0, 0.0470588) 0px 0px 0px 1px inset; background-color: rgb(174, 222, 244);'><a  href=\"http://"+ip+":"+puerto+"/MedPrueba1/dentista.html\" onclick='window.location='http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'; return false;' style=\"color:white; text-decoration: none;\">OK</a></button></div>");
                                                        out.println("</div>");
                                                         out.println("</div>");
                                                         out.print("<META HTTP-EQUIV='REFRESH'" + "CONTENT='20.0000001;URL=http://"+ip+":"+puerto+"/MedPrueba1/dentista.html'/>");   
                                                    
                        
                }
            catch(SQLException error)
                {
                        out.println(error.toString());
                }
        %>
    
<!--create database calendario;

use calendario;

create table calendari(
	dia varchar(2),
	mes varchar(10),
	año varchar(4),
	actividad varchar(10000)
);

select * from calendari;-->
