package org.gdg.withgo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgresql {

    public static Connection create() throws SQLException {
        String dbHost = System.getenv("DB_HOST");
        String dbName = System.getenv("DB_NAME");
        String dbPort = System.getenv("DB_PORT");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        String url = String.format("jdbc:postgresql://%s:%s/%s", dbHost,dbPort,dbName);
        return DriverManager.getConnection(url,dbUser, dbPassword);
    }
}
