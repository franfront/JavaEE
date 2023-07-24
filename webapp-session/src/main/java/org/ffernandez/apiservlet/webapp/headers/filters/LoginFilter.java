package org.ffernandez.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.services.LoginService;
import org.ffernandez.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carro/*")
public class LoginFilter implements Filter {

    // es el método que se ejecuta cuando se hace una petición a un servlet
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request); // casteo de ServletRequest a HttpServletRequest

        if(username.isPresent()){
            filterChain.doFilter(request, response); // se ejecuta el siguiente filtro
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "No estas autorizado!");
        }


    }
}
