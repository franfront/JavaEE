<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.ffernandez.apiservlet.webapp.headers.models.*"%>
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensajeRequest = (String) request.getAttribute("mensaje");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>

<h1>Listado de productos</h1>
<% if(username.isPresent()){%>
<div>Hola <%=username.get()%>, bienvenido!</div>
<p><a href="<%=request.getContextPath()%>/productos/form">Crear [+]</a></p>
<% } %>
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <% if(username.isPresent()){%>
        <th>Precio</th>
        <th>Agregar</th>
        <th>Editar</th>
        <% } %>
    </tr>
    <% for(Producto p : productos) {%>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getCategoria().getNombre() %></td>
        <% if(username.isPresent()){%>
        <td><%= p.getPrecio() %></td>
        <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId() %>">Agregar</a></td>
        <td><a href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId() %>">Editar</a></td>

        <% } %>
    </tr>
    <% } %>


</table>
<p><%=mensajeApp%></p>
<p><%=mensajeRequest%></p>

</body>
</html>