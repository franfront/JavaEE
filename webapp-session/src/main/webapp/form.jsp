<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.time.format.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<h1>Formulario de productos</h1>
<form action="${pageContext.request.contextPath}/productos/form" method="post">
    <div>
        <label for="nombre">Nombre</label>
        <div>
            <input type="text" name="nombre" id="nombre" value="${producto.nombre}" >
        </div>
        <c:if test="${errores != null && errores.containsKey('nombre')}">


        <div style="color:red;">${errores.nombre}</div>

        </c:if>
    </div>

    <div>
        <label for="precio">Precio</label>
        <div>
            <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio: ""}" >
        </div>
        <c:if test="${errores != null && !empty errores.precio}">


                <div style="color:red;">${errores.precio}</div>

        </c:if>
    </div>

    <div>
        <label for="sku">Sku</label>
        <div>
            <input type="text" name="sku" id="sku" value="${producto.sku}">
        </div>
       <c:if test="${errores != null && !empty errores.sku}">
             <div style="color:red;">${errores.sku}</div>
       </c:if>
    </div>

    <div>
        <label for="fecha_registro">Fecha de registro</label>
        <div>
            <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
        </div>
        <c:if test="${errores != null && !empty errores.fecha_registro}">
                     <div style="color:red;">${errores.fecha_registro}</div>
        </c:if>
    </div>

    <div>
        <label for="categoria">Categoria</label>
        <div>
            <select name="categoria" id="categoria">
                <option value="">----- seleccionar -----</option>

                <c:forEach items="${categorias}" var="c">
                 <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? "selected": ""} >${c.nombre}</option>

                </c:forEach>
            </select>
        </div>
       <c:if test="${errores != null && !empty errores.categoria}">
                    <div style="color:red;">${errores.categoria}</div>
       </c:if>
    </div>

    <div><input type="submit" value="${producto.id!= null && producto.id>0 ? "Editar": "Crear"} "></div>
    <input type="hidden" name="id" value="${producto.id}">
</form>
</body>
</html>