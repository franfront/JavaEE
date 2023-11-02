package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.services.*;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Inject
    @Named("default")
    private ProductoService service;

    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Producto> productos = service.listarProductos();



        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title") + " - Listado de productos");

        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

    }
}
