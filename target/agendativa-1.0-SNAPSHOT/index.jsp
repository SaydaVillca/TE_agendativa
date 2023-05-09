<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Contacto"%>
<%@page import="java.util.List"%>
<%
    List<Contacto> lista = (List<Contacto>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Contactos</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Correo</th>
                    <th>Telefono</th>
                    <th></th>
                    <th></th>
                </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nombre}</td>
                    <td>${item.correo}</td>
                    <td>${item.telefono}</td>
                    <td>
                        <a href="MainController?op=editar&id=${item.id}" >Editar</a>
                    </td>
                    <td>
                        <a href="MainController?op=eliminar&id=${item.id}" onclick= 
                            "return (confirm('Estas seguro?'))">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
