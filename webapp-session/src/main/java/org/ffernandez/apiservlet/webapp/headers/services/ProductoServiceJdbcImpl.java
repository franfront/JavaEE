package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.Categoria;
import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.repositories.CategoriaRepositoryImpl;
import org.ffernandez.apiservlet.webapp.headers.repositories.ProductoRepositoyJdbcImpl;
import org.ffernandez.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{
    private Repository<Producto> repojdbc;
    private Repository<Categoria> repoCateogriajdbc;

    public ProductoServiceJdbcImpl(Connection conn) {

        this.repojdbc = new ProductoRepositoyJdbcImpl(conn);
        this.repoCateogriajdbc = new CategoriaRepositoryImpl(conn);
    }

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
