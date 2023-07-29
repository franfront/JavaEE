package org.ffernandez.apiservlet.webapp.headers.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.services.ServiceJdbcException;
import org.ffernandez.apiservlet.webapp.headers.util.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*") // se aplica a todos
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try(Connection conn = ConexionBD.getConection()){ // inicializa la conexion

            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la conexion a la base de datos");
                e.printStackTrace();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
