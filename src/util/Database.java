package util;

import java.sql.*;

public class Database {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/school_management";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD= "1376b1376";

    private static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public static Statement getSQLStatement() throws SQLException {
        return getDatabaseConnection().createStatement();
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getDatabaseConnection().prepareStatement(sql);
    }
}
