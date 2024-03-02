package org.ffernandez.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier // especifica que implementacion queremos usar
@Retention(RetentionPolicy.RUNTIME) // la anotacion estara disponible en tiempo de ejecucion
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR}) // donde se va a inyectar esta anotacion
public @interface ProductoServicesPrin {
}
