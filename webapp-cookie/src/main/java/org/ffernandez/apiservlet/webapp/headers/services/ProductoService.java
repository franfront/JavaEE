package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listarProductos();
}
