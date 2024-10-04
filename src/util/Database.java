package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/school_management";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD= "1376b1376";

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public Statement getSQLStatement() throws SQLException {
        return this.getDatabaseConnection().createStatement();
    }
}