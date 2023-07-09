package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/*
  Servlet que muestra las cabeceras de la petición HTTP
 */
@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver

        String metodoHttp = req.getMethod(); // devuelve el método HTTP de la petición
        String uri = req.getRequestURI(); // devuelve la URI de la petición
        String url = req.getRequestURL().toString(); // devuelve la URL de la petición
        String contextPath = req.getContextPath(); // devuelve el contexto de la petición
        String servletPath = req.getServletPath(); // devuelve el servlet de la petición

        String ipCliente = req.getRemoteAddr(); // devuelve la IP del cliente
        String ip = req.getLocalAddr(); // devuelve la IP del servidor
        int puerto = req.getLocalPort(); // devuelve el puerto del servidor
        String scheme = req.getScheme(); // devuelve el protocolo de la petición
        String host = req.getHeader("host"); // devuelve el host de la petición
        String urlCompleta = scheme + "://" + host + contextPath + servletPath; // devuelve la URL completa de la petición
        String urlCompleta2 = scheme + "://" + ip + ":" + puerto + contextPath + servletPath; // devuelve la URL completa de la petición


        try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Cabeceras HTTP Request</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>Cabeceras HTTP Request!</h1>");

            out.println("<ul>");
            out.println("<li>Método HTTP: " + metodoHttp + "</li>");
            out.println("<li>Request URI: " + uri + "</li>");
            out.println("<li>Request URL: " + url + "</li>");
            out.println("<li>Context Path: " + contextPath + "</li>");
            out.println("<li>Servlet Path: " + servletPath + "</li>");
            out.println("<li>IP Local: " + ip + "</li>");
            out.println("<li>IP Cliente: " + ipCliente + "</li>");
            out.println("<li>Puerto Local: " + puerto + "</li>");
            out.println("<li>Protocolo: " + scheme + "</li>");
            out.println("<li>Host: " + host + "</li>");
            out.println("<li>URL Completa: " + urlCompleta + "</li>");
            out.println("<li>URL Completa 2: " + urlCompleta2 + "</li>");

            Enumeration<String> cabeceras = req.getHeaderNames(); // devuelve las cabeceras de la petición
            while (cabeceras.hasMoreElements()) {
                String cabecera = cabeceras.nextElement();
                out.println("<li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }


            out.println("</ul>");


            out.println("</body>");

            out.println("</html>");
        }
    }
}
