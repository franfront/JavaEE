package org.ffernandez.apiservlet.webapp.headers.configs;

import org.ffernandez.apiservlet.webapp.headers.util.JpaUtil;

import jakarta.annotation.Resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import jakarta.inject.Inject;

import jakarta.persistence.EntityManager;


import javax.naming.NamingException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Resource(name="jdbc/mysqlDB") // name es el nombre del recurso que hemos definido en el servidor de aplicaciones
    private DataSource ds;


    @Produces
    @RequestScoped
//    @Named("conn")
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
//        Context initContext = new InitialContext();
//        Context envContext  = (Context)initContext.lookup("java:/comp/env");
//        DataSource ds = (DataSource)envContext.lookup("jdbc/mysqlDB");



        return ds.getConnection();
    }

    @Produces // se usa para producir un objeto
    private Logger beanLogger(InjectionPoint injectionPoint){ // da informacion del punto de inyeccion
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName()); // devuelve el nombre de la clase
    }

    public void close(@Disposes @MysqlConn Connection conn) throws SQLException {
        conn.close();
        log.info("Cerrando conexion");
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return JpaUtil.getEntityManagerFactory().createEntityManager();
    }

    public void close(@Disposes EntityManager em){
        if(em.isOpen()){
            em.close();
            log.info("Cerrando la conexion del EntityManager");
        }
    }

}
