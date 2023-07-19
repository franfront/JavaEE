package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listarProductos();

        LoginService auth = new LoginServiceSessionImpl();

        Optional<String> usernameOptional = auth.getUsername(req);


        resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver

        try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Listado de Productos</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>Listado de Productos!</h1>");

            if(usernameOptional.isPresent()) {
                out.println("<h2>Bienvenido "+ usernameOptional.get() +"</h2>");
            }


            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");

            if(usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
                out.println("<th>Agregar</th>");
            }

            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href= '"+ req.getContextPath()  + "/agregar-carro?id=" + p.getId() + "' >Agregar al carrito</a></td>");
                }
                out.println("</tr>");
            });

            out.println("</table>");


            out.println("</body>");

            out.println("</html>");

        }

    }
}
