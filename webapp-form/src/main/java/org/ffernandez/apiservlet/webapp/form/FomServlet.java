package org.ffernandez.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/registro")
public class FomServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); // el tipo de contenido que vamos a devolver

        // obtenemos los datos del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");

        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null &&
                req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");

        List<String> errores = new ArrayList<>();

        if(username == null || username.isBlank()) {
            errores.add("El username es requerido");
        }

        if(password == null || password.isBlank()) {
            errores.add("El password es requerido");
        }

        if(email == null || email.isBlank()) {
            errores.add("El email es requerido");
        }

        if(pais == null || pais.equals("") || pais.equals(" ") ) {
            errores.add("El pais es requerido");
        }

        if(lenguajes == null || lenguajes.length == 0) {
            errores.add("Debe seleccionar al menos un lenguaje");
        }

        if(roles == null || roles.length == 0) {
            errores.add("Debe seleccionar al menos un rol");
        }

        if(idioma == null || idioma.equals("") || idioma.equals(" ")) {
            errores.add("El idioma es requerido");
        }





            if (errores.isEmpty()) {
        try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Resultado form</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>Resultado form!</h1>");

            out.println("<ul>");


                out.println("<li>Username: " + username + "</li>");
                out.println("<li>Password: " + password + "</li>");
                out.println("<li>Email: " + email + "</li>");
                out.println("<li>País: " + pais + "</li>");

                out.println("<li>Lenguajes: <ul>");

                Arrays.asList(lenguajes).forEach(l -> out.println("<li>" + l + "</li>"));

                out.println("</ul></li>");

                out.println("<li>Roles: <ul>");

                Arrays.asList(roles).forEach(r -> out.println("<li>" + r + "</li>"));

                out.println("</ul></li>");

                out.println("<li>Idioma: " + idioma + "</li>");
                out.println("<li>Habilitado: " + habilitar + "</li>");
                out.println("<li>Secreto: " + secreto + "</li>");

            out.println("</ul>");



            out.println("</body>");

            out.println("</html>");
        }
            } else {
                /*errores.forEach(e -> out.println("<li>" + e + "</li>"));
            }

            out.println("<p> <a href=\"/webapp-form\">Volver al formulario</a> </p>");
            */

                req.setAttribute("errores", errores);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp); // redireccionamos a la pagina de errores
            }
    }
}
