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
</head>
<body>
<h3>Formulario de usuarios</h3>

<%
   if(errors != null && errors.size() > 0) {

%>
<ul>
    <% for(String error : errors.values()) { %>
    <li><%= error %></li>
    <% } %>
</ul>

<%}%>

<form action="/webapp-form/registro" method="post">

    <div>
        <label for="username">Username</label>
        <div><input type="text" name="username" id="username"></div>
        <%
            if(errors != null && errors.containsKey("username")) {
                out.println("<p style='color: red'>" + errors.get("username") + "</p>");
            }
        %>
    </div>

    <div>
        <label for="password">Password</label>
        <div><input type="password" name="password" id="password" ></div>
         <%
                    if(errors != null && errors.containsKey("password")) {
                        out.println("<p style='color: red'>" + errors.get("password") + "</p>");
                    }
                %>
    </div>

    <div>
        <label for="email">Email</label>
        <div><input type="email" name="email" id="email"></div>
         <%
                    if(errors != null && errors.containsKey("email")) {
                        out.println("<p style='color: red'>" + errors.get("email") + "</p>");
                    }
         %>
    </div>

    <div>
        <label for="pais">Pais</label>
        <div>
            <select name="pais" id="pais">
                <option value="AR">Argentina</option>
                <option value="FR">Francia</option>
                <option value="AL">Alemania</option>
                <option value="ES">España</option>
            </select>
        </div>
        <%
                            if(errors != null && errors.containsKey("pais")) {
                                out.println("<p style='color: red'>" + errors.get("pais") + "</p>");
                            }
                 %>
    </div>

    <div>
        <label for="lenguajes"></label>
        <div>
            <select name="lenguajes" id="lenguajes" multiple>
                <option value="Java">Java</option>
                <option value="C#">C#</option>
                <option value="PHP">PHP</option>
                <option value="Python">Python</option>
                <option value="C++">C++</option>
            </select>
        </div>
        <%
                            if(errors != null && errors.containsKey("lenguajes")) {
                                out.println("<p style='color: red'>" + errors.get("lenguajes") + "</p>");
                            }
                 %>
    </div>

    <div>
        <label>Roles</label>
        <div>
            <input type="checkbox" name="roles" value="ROLE_ADMIN">
            <label>Administrador</label>
        </div>

        <div>
            <input type="checkbox" name="roles" value="ROLE_USER">
            <label>Usuario</label>
        </div>

        <div>
            <input type="checkbox" name="roles" value="ROLE_MODERATOR">
            <label>Moderador</label>
        </div>

        <%
                            if(errors != null && errors.containsKey("roles")) {
                                out.println("<p style='color: red'>" + errors.get("roles") + "</p>");
                            }
                 %>
    </div>



    <div>
        <label>Idiomas</label>
        <div>
            <input type="radio" name="idioma" value="es">
            <label>Español</label>
            <input type="radio" name="idioma" value="fr">
            <label>Frances</label>
            <input type="radio" name="idioma" value="en">
            <label>Ingles</label>
            <input type="radio" name="idioma" value="br">
            <label>Portugues</label>
        </div>

        <%
                            if(errors != null && errors.containsKey("idioma")) {
                                out.println("<p style='color: red'>" + errors.get("idioma") + "</p>");
                            }
                 %>
    </div>

    <div>
        <label for="habilitar">Habilitar</label>
        <div>
            <input type="checkbox" name="habilitar" id="habilitar" checked>
        </div>
    </div>



    <div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </div>

    <input type="hidden" value="12345" name="secreto">

</form>
</body>
</html>