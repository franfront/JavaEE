package org.ffernandez.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hola-mundo") // sirve para mapear la ruta de la url
public class HMServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html"); // el tipo de contenido que vamos a devolver
        PrintWriter out = response.getWriter(); // para escribir la respuesta

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Hola Mundo</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>Hola Mundo desde Servlets!</h1>");
        out.println("</body>");

        out.println("</html>");
        out.close();
    }

}
