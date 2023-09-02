<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<c:if test="${username.isPresent()}">
<div>Hola <c:out value="{username.get()}"/>, bienvenido!</div>
<p><a href="<c:out value="${pageContext.request.contextpath}"/>/productos/form">Crear [+]</a></p>
</c:if>
<table>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Tipo</th>
        <c:if teset="{username.isPresent()}">
        <th>Precio</th>
        <th>Agregar</th>
        <th>Editar</th>
        <th>Eliminar</th>
        </c:if>
    </tr>
    <% for(Producto p : productos) {%>
    <c:forEach items="${productos}" var="p">
    <tr>
        <td><c:out value="{p.Id()}"/></td>

        <td><%= p.getNombre() %></td>
        <td><%= p.getCategoria().getNombre() %></td>
        <% if(username.isPresent()){%>
        <td><%= p.getPrecio() %></td>
        <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId() %>">Agregar</a></td>
        <td><a href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId() %>">Editar</a></td>
        <td><a onclick="return confirm('¿Está seguro de eliminar el producto?');"
        href="<%=request.getContextPath()%>/productos/eliminar?id=<%=p.getId() %>">Eliminar</a></td>


        <% } %>
    </tr>

    </c:forEach>


</table>
<p><%=mensajeApp%></p>
<p><%=mensajeRequest%></p>

</body>
</html>