package org.ffernandez.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3307/java_curso";
    private static String username = "*****";
    private static String password = "*******";

    public static Connection getConection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
