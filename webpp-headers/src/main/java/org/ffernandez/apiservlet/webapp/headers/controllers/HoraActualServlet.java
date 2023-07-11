package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/hora-actual")
public class HoraActualServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver
        resp.setHeader("refresh", "1"); // cada 1 segundo se refresca la página
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(df);
        try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>La hora actualizada</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>La hora actualizada!</h1>");

            out.println("<h3> La hora actual es: " + horaFormateada + "</h3>");

            out.println("</body>");

            out.println("</html>");
        }
    }
}
