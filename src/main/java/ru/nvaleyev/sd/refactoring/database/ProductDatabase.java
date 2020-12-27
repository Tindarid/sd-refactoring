package ru.nvaleyev.sd.refactoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDatabase {
    private final String name;

    public ProductDatabase(String name) {
        this.name = name;
    }

    public void sqlCall(String query) throws SQLException {
        try (Connection c = DriverManager.getConnection(name)) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }
    }

    public void createIfNotExists() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS PRODUCT " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)";
        sqlCall(query);
    }

    public void drop() throws SQLException {
        sqlCall("DROP TABLE IF EXISTS PRODUCT");
    }
}
