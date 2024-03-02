package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.configs.ProductoServicesPrin;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Producto;
import org.ffernandez.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    @ProductoServicesPrin
    private ProductoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id;

        // validamos que el id sea un numero
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        // si el id es mayor a 0, buscamos el producto sino creamos un producto vacio
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if(id > 0) {
            try {
                Optional<Producto> o = service.porId(id);
                if(o.isPresent()) {
                    producto = o.get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("categorias", service.listarCategoria());
        req.setAttribute("producto", producto);
        req.setAttribute("title", req.getAttribute("title") + " - Formulario de productos");

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nombre = req.getParameter("nombre");
        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");

        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido");
        }
        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es requerido");
        } else if (sku.length() > 10) {
            errores.put("sku", "El sku debe tener maximo 10 caracteres");
        }
        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es requerida");
        }
        if (precio.equals(0)) {
            errores.put("precio", "El precio es requerido");
        }
        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La categoria es requerida");
        }

        LocalDate fecha;
        try{
        fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (Exception e) {
        fecha = null;
    }
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);
        producto.setCategoria(categoria);


        if(errores.isEmpty()) {

            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else{
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);
            req.setAttribute("title", req.getAttribute("title") + " - Formulario de productos");

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);


        }



    }
}
