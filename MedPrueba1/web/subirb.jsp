

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	
    <%@page import="java.sql.*,java.io.*"%>
        
        <%
            String dia = request.getParameter("dia");
            String mes = request.getParameter("mes");
            String año = request.getParameter("anio");
            String actividad = request.getParameter("actividad");
            
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
                        out.println("<script>alert(\"Registro dado de Alta\"); window.location.replace('http://localhost:39055/MedPrueba1/dentista.html')</script>");
                      
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
