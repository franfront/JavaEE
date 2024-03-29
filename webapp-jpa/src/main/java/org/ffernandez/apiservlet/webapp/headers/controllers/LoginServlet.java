package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Usuario;
import org.ffernandez.apiservlet.webapp.headers.services.LoginService;
import org.ffernandez.apiservlet.webapp.headers.services.UsuarioService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UsuarioService service;
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Optional<String> usernameOptional = auth.getUsername(req);

        if(usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver
            try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido "+ usernameOptional.get() +"</title>");
                out.println("</head>");

                out.println("<body>");
                out.println("<h1>Bienvenido "+ usernameOptional.get() +" has iniciado sesión</h1>");
                out.println("<p><a href= '"+ req.getContextPath()  + "/index.jsp' >Volver al inicio</a></p>");
                out.println("<p><a href= '"+ req.getContextPath()  + "/logout' >Cerrar sesión</a></p>");
                out.println("</body>");

                out.println("</html>");
            }
        } else {
            req.setAttribute("title", req.getAttribute("title") + " - Iniciar sesión");

            getServletContext().getRequestDispatcher( "/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        Optional<Usuario> usuarioOptional = service.login(username, password);


        if (usuarioOptional.isPresent()) {

            HttpSession session = req.getSession();
            session.setAttribute("username", username);


            resp.sendRedirect(req.getContextPath() + "/login.html");


        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado"); // devuelve un 401, mensaje opcional
        }

    }
}
