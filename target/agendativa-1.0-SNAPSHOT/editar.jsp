<%@page import="com.emergentes.modelo.Contacto"%>
<%
    Contacto con = (Contacto) request.getAttribute("con");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= (con.getId() == 0) ? "Nuevo Contacto" : "Editar Contacto"%></h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value = "<%= con.getId()%>">
            <table border="1">
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value = "<%= con.getNombre()%>"></td>
                </tr>
                <tr>
                    <td>Correo</td>
                    <td><input type="text" name="correo" value = "<%= con.getCorreo()%>"></td>
                </tr>
                <tr>
                    <td>Telefono</td>
                    <td><input type="text" name="telefono" value = "<%= con.getTelefono()%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table>

        </form>
    </body>
</html>
