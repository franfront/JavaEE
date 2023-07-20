<%@page contentType="text/html" pageEncoding="UTF-8" import="package org.ffernandez.apiservlet.webapp.headers.models.*" %>

<%
Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
<h1>Carro de Compras</h1>

<%
    if(carro = null || carro.getCarro().isEmpty()){
        <p>No hay productos en el carro</p>

    %>} else{ %>

    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Total</th>

        </tr>

        <% for(ItemCarro item : carro.getItems()){%>

        <tr>
            <td><%=item.getProducto.getId()%></td>
            <td><%=item.getProducto.getNombre%></td>
            <td><%=item.getProducto.getPrecio()%></td>
            <td><%=item.getCantidad()%></td>
            <td><%=item.getImporte()%></td>
        </tr>

        <%}%>




    </table>

    <%}%>


</body>
</html>