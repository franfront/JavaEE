package org.ffernandez.apiservlet.webapp.headers.filters;


import jakarta.inject.Inject;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ffernandez.apiservlet.webapp.headers.configs.MysqlConn;
import org.ffernandez.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*") // se aplica a todos
public class ConexionFilter implements Filter {

   /* @Inject
    @MysqlConn
    private Connection conn;*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*try(Connection connRequest = this.conn){ // inicializa la conexion

            if(connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }*/




            try {
//                request.setAttribute("conn", connRequest);
                chain.doFilter(request, response);
//                connRequest.commit();
            } catch (ServiceJdbcException e) {
//                connRequest.rollback();

                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la conexion a la base de datos");
                e.printStackTrace();

            }
        /*} catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
