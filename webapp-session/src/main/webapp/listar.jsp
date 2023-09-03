<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
        <td><c:out value="{p.id()}"/></td>

        <td><c:out value="{p.nombre()}"/></td>
        <td><c:out value="{p.categoria.nombre}"/></td>
        <c:if teset="{username.isPresent()}">
        <td><c:out value="{p.precio}" /></td>
        <td><a href="${pageContext.request.contextpath}/carro/agregar?id=<c:out value="{p.id()}" />">Agregar</a></td>
        <td><a href="${pageContext.request.contextpath}/productos/form?id=<c:out value="{p.id()}"  />">Editar</a></td>
        <td><a onclick="return confirm('¿Está seguro de eliminar el producto?');"
        href="${pageContext.request.contextpath}/productos/eliminar?id=<c:out value="{p.id()}"/>">Eliminar</a></td>


         </c:if>
    </tr>

    </c:forEach>


</table>
<p>${applicationScope.mensaje}</p>
<p><${requestScope.mensaje}</p>

</body>
</html>