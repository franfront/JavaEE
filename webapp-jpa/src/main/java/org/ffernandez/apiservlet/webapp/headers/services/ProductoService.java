package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.entities.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listarProductos();
    Optional<Producto> porId(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategoria();
    Optional<Categoria> porIdCategoria(Long id);

}
