<%@page import="java.util.*,java.text.*" %>
<html>
<head>
 <title>Calendario</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jQuery.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/formly.js"></script>
        <link rel="stylesheet" href="css/formly.css" type="text/css" />
        
  <script>
            $(document).ready(function() { 

                    $('#Info').formly({'theme':'Dark'}, function(e) { 
		
                    $('.callback').html(e);
                });
            });
        </script>
 
</head>
<body>
    <div class="tittle" style="background-color: #673AB7">
                <p style="margin-top: 0px;">Calendario</p>
            </div>

        <% 
          boolean yyok = false;  
          int yy = 0, mm = 0;
          String yyString = request.getParameter("anio");
          if (yyString != null && yyString.length() > 0) {
            try {
              yy = Integer.parseInt(yyString);
              yyok = true;
            } catch (NumberFormatException e) {
              out.println("año " + yyString + " invalid");
            }
          }
          Calendar c = Calendar.getInstance();
          if (!yyok)
            yy = c.get(Calendar.YEAR);

          String mmString = request.getParameter("mes");
          if (mmString == null) {
            mm = c.get(Calendar.MONTH);
          } else {
            for (int i=0; i<meses.length; i++)
              if (meses[i].equals(mmString)) {
                mm = i;
                break;
              }
          }
        %>
        <form id="Info" name="subir" method="post" action="subiractividad.html">
        
          Mes: <select name=mes>
          <% for (int i=0; i<meses.length; i++) {
            if (i==mm)
              out.print("<option selected>");
            else
              out.print("<option>");
            out.print(meses[i]);
            out.println("</option>");
          }
          %>
          </select>
          Año (aaaa): 
            <input type="text" size="5" oncopy="return false" oncut="return false" onpaste="return false" name="anio"
              value="<%= yy %>">
          <input type=submit value="Ver">
        
        <%!
          String meses[] = {
            "ENERO", "FEBRERO", "MARZO", "ABRIL",
            "MAYO", "JUNIO", "JULIO", "AGOSTO",
            "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"
          };

          int dom[] = {
              31, 28, 31, 30,  /* ENERO FEBRERO MARZO ABRIL */
              31, 30, 31, 31, /* MAYO JUNIO JULIO AGOSTO */
              30, 31, 30, 31  /* SEPTIEMBRE OCTUBRE NOVIEMBRE DICIEMBRE */
          };
        %>

        <%
          int añoBisiesto = 0;
        %>
        
        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />
        <div class=\"component\">
            <br>
            <br>
            <center>
        <table border=1 style="width: 1000px; height: 500px;">
        <tr><th colspan=7><%= meses[mm] %>  <%= yy %></tr>

        <%    GregorianCalendar calendar = new GregorianCalendar(yy, mm, 1); %>

        <tr><td>DOMINGO<td>LUNES<td>MARTES<td>MIERCOLES<td>JUEVES<td>VIERNES<td>SABADO</tr>

        <%
            añoBisiesto = calendar.get(Calendar.DAY_OF_WEEK)-1;

            int diasdelmes = dom[mm];
            if (calendar.isLeapYear(calendar.get(Calendar.YEAR)) && mm == 1)
              ++diasdelmes;

            out.print("<tr>");

            for (int i = 0; i < añoBisiesto; i++) {
              out.print("<td>&nbsp;");
            }

            for (int i = 1; i <= diasdelmes; i++) {

              out.print("<td>");
              out.print(i);
              out.print("</td>");

              if ((añoBisiesto + i) % 7 == 0) {   
                out.println("</tr>");
                out.print("<tr>");
              }
            }
        %>
        
        </table>
            </center>
        </div>
        </form>
    </body>
</html>

