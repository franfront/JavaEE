package org.ffernandez.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.ffernandez.apiservlet.webapp.headers.models.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("default")
public class ProductoServiceJdbcImpl implements ProductoService{

    @Inject
    private Repository<Producto> repojdbc;

    @Inject
    private Repository<Categoria> repoCateogriajdbc;


    @Override
    public List<Producto> listarProductos() {
        try {
            return repojdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repojdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repojdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {
        try {
            repojdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public List<Categoria> listarCategoria() {

        try {
            return repoCateogriajdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repoCateogriajdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
    }
}
