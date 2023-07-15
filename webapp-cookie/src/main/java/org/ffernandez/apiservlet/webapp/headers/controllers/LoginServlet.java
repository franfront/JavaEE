package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345678";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("username"))
                .map(Cookie::getValue) // obtenemos el valor de la cookie como un String
                .findFirst();


        if(cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver
            try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido "+ cookieOptional.get() +"</title>");
                out.println("</head>");

                out.println("<body>");
                out.println("<h1>Bienvenido "+ cookieOptional.get() +" ya has iniciado sesión antes</h1>");
                out.println("</body>");

                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);



            resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver
            try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Login correcto</title>");
                out.println("</head>");

                out.println("<body>");
                out.println("<h1>Login correcto!</h1>");

                out.println("<h3> Bienvenido " + USERNAME + " ha iniciado sesión" +"</h3>");

                out.println("</body>");

                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado"); // devuelve un 401, mensaje opcional
        }

    }
}
