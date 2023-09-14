package org.ffernandez.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.ffernandez.apiservlet.webapp.headers.models.Carro;


@WebListener // para que el servidor de aplicaciones sepa que es un listener
public class EjListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicializando la aplicación!"); // para ver en el log del servidor

        context = sce.getServletContext();
        context.setAttribute("mensaje", "algun valor global");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.log("destruyendo la aplicación!");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        context.log("inicializando la petición!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para la petición");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        context.log("destruyendo la petición!");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        context.log("inicializando la sesión http!");
        Carro carro = new Carro(); // creamos el carro cuando se crea la sesión
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        context.log("inicializando la sesión http!");
    }
}
