<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<ul class="list-group">

    <li class="list-group-item active"> Menu de opciones</li>


    <li class="list-group-item">
        <a href="${pageContext.request.contextPath}/productos.html">Mostrar html</a>

    </li>
    <li class="list-group-item">

        <a href="${pageContext.request.contextPath}/login.html">Login</a>
    </li>

    <li class="list-group-item">

        <a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
    </li>

    <li class="list-group-item">

        <a href="${pageContext.request.contextPath}/carro/ver">Ver carro</a>
    </li>



</ul>

<jsp:include page="layout/footer.jsp" />