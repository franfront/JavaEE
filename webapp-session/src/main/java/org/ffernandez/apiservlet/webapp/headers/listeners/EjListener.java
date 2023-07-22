package org.ffernandez.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener // para que el servidor de aplicaciones sepa que es un listener
public class EjListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicializando la aplicación!"); // para ver en el log del servidor

        context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.log("destruyendo la aplicación!");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        context.log("inicializando la petición!");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        context.log("destruyendo la petición!");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        context.log("inicializando la sesión http!");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        context.log("inicializando la sesión http!");
    }
}
