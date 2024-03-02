package org.ffernandez.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@RequestScoped // se crea una instancia por cada peticion
@Named
@Stereotype // es una anotacion que se usa para agrupar otras anotaciones
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
}
