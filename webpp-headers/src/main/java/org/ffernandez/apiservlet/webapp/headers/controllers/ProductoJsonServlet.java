package org.ffernandez.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.services.ProductoService;
import org.ffernandez.apiservlet.webapp.headers.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Producto producto = mapper.readValue(jsonStream, Producto.class);
        resp.setContentType("text/html;charset=UTF-8"); // el tipo de contenido que vamos a devolver
        try (PrintWriter out = resp.getWriter()) { // para escribir la respuesta

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Detalle de producto desde json</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<h1>Detalle de producto desde json!</h1>");

            out.println("<ul>");
            out.println("<li>Id: " + producto.getId() + "</li>");
            out.println("<li>Nombre: " + producto.getNombre() + "</li>");
            out.println("<li>Tipo: " + producto.getTipo() + "</li>");
            out.println("<li>Precio: " + producto.getPrecio() + "</li>");
            out.println("</ul>");




            out.println("</body>");

            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listarProductos();


        ObjectMapper mapper = new ObjectMapper(); //convertir objetos Java en representaciones JSON y viceversa
        String json = mapper.writeValueAsString(productos); // convertir la lista de productos a json
        resp.setContentType("application/json"); // el tipo de contenido que vamos a devolver
        resp.getWriter().write(json); // escribir la respuesta

    }
}
