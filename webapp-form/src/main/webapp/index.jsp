<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
 Map<String, String> errors = (Map<String, String>) request.getAttribute("errores");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body>
<div class="container mt-10 shadow mb-5 bg-body rounded-4 d-grid gap-3">
<h3>Formulario de usuarios</h3>
<div class="container">

<%
   if(errors != null && errors.size() > 0) {

%>
<ul class="list-group list-group-flush">
    <% for(String error : errors.values()) { %>
    <li class="alert alert-danger"><%= error %></li>
    <% } %>
</ul>

<%}%>
</div>

<form action="/webapp-form/registro" method="post">

    <div class="mb-3">
        <label for="username" class="form-label fs-4">Username</label>
        <div><input type="text" name="username" id="username" placeholder="Tu Username" class="form-control" value="${param.username}"></div>
        <%
            if(errors != null && errors.containsKey("username")) {
                out.println("<small  class='form-text text-danger'>" + errors.get("username") + "</small>");
                }
        %>
    </div>

    <div class="mb-3">
        <label for="password" class="form-label fs-4">Password</label>
        <div><input type="password" name="password" id="password" placeholder="Tu Password" class="form-control"></div>
         <%
                    if(errors != null && errors.containsKey("password")) {
out.println("<small  class='form-text text-danger'>" + errors.get("password") + "</small>");              }
                %>
    </div>

    <div class="mb-3">
        <label for="email" class="form-label fs-4l">Email</label>
        <div><input type="email" name="email" id="email" class="form-control value="${param.email}"></div>
         <%
                    if(errors != null && errors.containsKey("email")) {
out.println("<small  class='form-text text-danger'>" + errors.get("email") + "</small>");                              }
         %>
    </div>

    <div class="row mb-3">
        <label for="pais" class="class="form-label fs-4"">Pais</label>
        <div>

            <select name="pais" id="pais" class="form-select" aria-label="Selecciona Un País">
                <option value="" selected>Selecciona un País</option>
                <option value="AR" ${param.pais.equals("AR")? "selected": ""}>Argentina</option>
                <option value="FR" ${param.pais.equals("FR")? "selected": ""}>Francia</option>
                <option value="AL" ${param.pais.equals("AL")? "selected": ""}>Alemania</option>
                <option value="ES" ${param.pais.equals("ES")? "selected": ""}>España</option>
            </select>
        </div>
        <%
                            if(errors != null && errors.containsKey("pais")) {
out.println("<small  class='form-text text-danger'>" + errors.get("pais") + "</small>") ;
 }
                 %>
    </div>

    <div class="mb-3">
        <label for="lenguajes" class="form-label fs-4">Lenguajes</label>

        <div>
            <select name="lenguajes" id="lenguajes" size="6" multiple class="form-select">
                <option value="Java" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("Java")).get()?"selected":""}>Java</option>
                <option value="C#" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("C#")).get()?"selected":""}>C#</option>
                <option value="PHP" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("PHP")).get()?"selected":""}>PHP</option>
                <option value="Python" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("Python")).get()?"selected":""}>Python</option>
                <option value="C++" ${paramValues.lenguajes.stream().anyMatch(v -> v.equals("C++")).get()?"selected":""}>C++</option>
            </select>
        </div>
        <%                  if (errors != null && errors.containsKey("lenguajes")) {
out.println("<small  class='form-text text-danger'>" + errors.get("lenguajes") + "</small>")   ;
                       }
                 %>
    </div>

    <div class="mb-3">
          <div aria-label="Seleccion Rol">
            <label class="form-label fs-4">Roles</label>
            <div class="w-100"></div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_ADMIN")).get()?"checked":""}>
              <label class="form-check-label">Administrador</label>
            </div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_USER")).get()?"checked":""}>
              <label class="form-check-label">Usuario</label>
            </div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input" ${paramValues.roles.stream().anyMatch(v -> v.equals("ROLE_MODERATOR")).get()?"checked":""}>
              <label class="form-check-label">Moderador</label>
            </div>
          </div>

        <%              if (errors != null && errors.containsKey("roles")){
out.println("<small  class='form-text text-danger'>" + errors.get("roles") + "</small>") ;
        }
%>
    </div>



     <div class="mb-3">
          <div aria-label="Seleccion Idioma"">
            <label class="form-label fs-4">Idiomas</label>
            <div class="w-100"></div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="radio" name="idioma" value="es" class="form-check-input" ${param.idioma.equals("es")? "checked": ""}>
              <label class="form-check-label">Español</label>
            </div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="radio" name="idioma" value="en" class="form-check-input" ${param.idioma.equals("en")? "checked": ""}>
              <label class="form-check-label">Inglés</label>
            </div>
            <div class="form-check form-check-inline col-sm-2">
              <input type="radio" name="idioma" value="fr" class="form-check-input" ${param.idioma.equals("fr")? "checked": ""}>
              <label class="form-check-label">Frances</label>
            </div>
          </div>

         <%
                if (errors != null && errors.containsKey("idioma")) {
out.println("<small  class='form-text text-danger'>" + errors.get("idioma") + "</small>");
                }
              %>
    </div>

     <div class="mb-3 align-items-center">
          <label for="habilitar" class="form-label fs-4 me-4 col-sm-2">Habilitar</label>
          <div class="form-check form-switch form-check-inline">
            <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
          </div>
        </div>



   <div class="d-grid gap-2 col-6 mx-auto mb-5">
         <input type="submit" value="Enviar Formulario" class="btn btn-primary">
       </div>

    <input type="hidden" value="12345" name="secreto">

</form>
</div>
</body>
</html>