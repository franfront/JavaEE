package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.Producto;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listarProductos() {
        return Arrays.asList(new Producto(1L, "notebook", "computación", 200000),
                new Producto(2L, "mesa escritorio", "oficina", 150000),
                new Producto(3L, "teclado", "computación", 20000),
                new Producto(4L, "silla de oficina", "oficina", 100000));
    }
}
