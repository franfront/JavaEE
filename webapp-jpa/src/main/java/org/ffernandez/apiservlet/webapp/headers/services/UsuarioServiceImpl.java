package org.ffernandez.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.ffernandez.apiservlet.webapp.headers.configs.Service;
import org.ffernandez.apiservlet.webapp.headers.models.entities.Usuario;
import org.ffernandez.apiservlet.webapp.headers.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements  UsuarioService {


    private UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username))
                    .filter(u-> u.getPassword().equals(password));

        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }
}
