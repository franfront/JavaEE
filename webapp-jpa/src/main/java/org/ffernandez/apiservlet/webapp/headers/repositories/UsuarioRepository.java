package org.ffernandez.apiservlet.webapp.headers.repositories;

import org.ffernandez.apiservlet.webapp.headers.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario> {
    Usuario porUsername(String username) throws SQLException;
}
