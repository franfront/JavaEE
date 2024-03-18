package org.ffernandez.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.ffernandez.apiservlet.webapp.headers.configs.ProductoServicesPrin;
import org.ffernandez.apiservlet.webapp.headers.configs.Service;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Producto;
import org.ffernandez.apiservlet.webapp.headers.repositories.CrudRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@ProductoServicesPrin
public class ProductoServiceJdbcImpl implements ProductoService{

    @Inject
    private CrudRepository<Producto> repojdbc;

    @Inject
    private CrudRepository<Categoria> repoCateogriajdbc;


    @Override
    public List<Producto> listarProductos() {
        try {
            return repojdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override

    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repojdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repojdbc.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {
        try {
            repojdbc.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public List<Categoria> listarCategoria() {

        try {
            return repoCateogriajdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repoCateogriajdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }
}
