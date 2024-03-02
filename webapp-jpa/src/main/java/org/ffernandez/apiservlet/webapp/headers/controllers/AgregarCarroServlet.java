package org.ffernandez.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.ffernandez.apiservlet.webapp.headers.configs.ProductoServicesPrin;
import org.ffernandez.apiservlet.webapp.headers.models.Carro;
import org.ffernandez.apiservlet.webapp.headers.models.ItemCarro;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Producto;
import org.ffernandez.apiservlet.webapp.headers.services.ProductoService;

import java.io.IOException;

import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductoServicesPrin
    private ProductoService service;

    @Inject
    private Carro carro;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));


        Optional<Producto> producto = service.porId(id);

        if(producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            // se maneja automatico por el CDI
//            HttpSession session = req.getSession();
//            Carro carro = (Carro) session.getAttribute("carro");
            // se maneja automatico por el CDI
            carro.agregarItem(item);



        }

        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
