package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.entities.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class ProductoServiceListImpl implements ProductoService {
    @Override
    public List<Producto> listarProductos() {
        return Arrays.asList(new Producto(1L, "notebook", "computación", 200000),
                new Producto(2L, "mesa escritorio", "oficina", 150000),
                new Producto(3L, "teclado", "computación", 20000),
                new Producto(4L, "silla de oficina", "oficina", 100000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listarProductos().stream()
                .filter(producto -> producto.getId().equals(id))
                .findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
