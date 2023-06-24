package org.ffernandez.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html"); // el tipo de contenido que vamos a devolver
        PrintWriter out = response.getWriter(); // para escribir la respuesta

        String saludo = request.getParameter("saludo");
        String nombre = request.getParameter("nombre");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Parámetros Get de la url</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>Parámetros Get de la url!</h1>");
        if(saludo != null && nombre != null){
            out.println("<p>El parámetro saludo es: " + saludo + " " + nombre + "</p>");

        }else if(saludo != null) {
            out.println("<p>El parámetro saludo es: " + saludo + "</p>");
        }else if(nombre != null) {
            out.println("<p>Mi nombre es: " + nombre + "</p>");
        }
        else {
            out.println("<p>Los parámetros nombre y saludo no existen</p>");
        }

        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            out.println("<p>El parámetro código es: " + codigo + "</p>");
        }catch (NumberFormatException e){
            out.println("<p>El parámetro código no es un número</p>");
        }
        out.println("</body>");

        out.println("</html>");
        out.close();



    }
}
