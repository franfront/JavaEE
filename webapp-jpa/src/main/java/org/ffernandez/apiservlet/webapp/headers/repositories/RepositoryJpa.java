package org.ffernandez.apiservlet.webapp.headers.repositories;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier // esto es para que se pueda inyectar en el constructor de la clase
@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE})
public @interface RepositoryJpa {
}
