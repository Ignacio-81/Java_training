package org.example.configuration;

import java.sql.*;

import static org.example.constants.Constants.*;

public class ConexionDB {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    public void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public void close(Statement smtm) throws SQLException {
        smtm.close();
    }

    public void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    }

    public void close(Connection conn) throws SQLException{
        conn.close();
    }

}
