package util;

public class ApplicationContext {
    private static final Database database;

    static {
        database = new Database();
    }

    public static Database getDatabase() {
        return database;
    }
}
