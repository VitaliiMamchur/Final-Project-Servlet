package ua.mamchur.servletproject.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolHolder {
    private static volatile ConnectionPoolHolder pool;
    private final BasicDataSource dataSource;

    private ConnectionPoolHolder() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/final_project_servlet");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setMinIdle(5);
        ds.setInitialSize(10);
        ds.setMaxIdle(1);
        ds.setMaxOpenPreparedStatements(100);
        this.dataSource = ds;
    }

    public static ConnectionPoolHolder poolHolder() {
        if (pool == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (pool == null) {
                    pool = new ConnectionPoolHolder();
                }
            }
        }
        return pool;
    }


    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
