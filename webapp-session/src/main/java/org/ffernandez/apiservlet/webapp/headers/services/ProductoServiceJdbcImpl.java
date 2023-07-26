package org.ffernandez.apiservlet.webapp.headers.services;

import org.ffernandez.apiservlet.webapp.headers.models.Producto;
import org.ffernandez.apiservlet.webapp.headers.repositories.ProductoRepositoyJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImpl implements ProductoService{
    private ProductoRepositoyJdbcImpl repojdbc;

    public ProductoServiceJdbcImpl(Connection conn) {
        this.repojdbc = new ProductoRepositoyJdbcImpl(conn);
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
}
