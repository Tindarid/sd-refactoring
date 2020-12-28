package ru.nvaleyev.sd.refactoring.database;

import ru.nvaleyev.sd.refactoring.print.PrintMethod;

import java.sql.*;

public class ProductDatabase {
    private final String name;

    public ProductDatabase(String name) {
        this.name = name;
    }

    public void sqlQueryWithPrinter(String query, PrintMethod way) {
        try {
            try (Connection c = DriverManager.getConnection(name)) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                way.print(rs);

                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sqlUpdate(String query) {
        try {
            try (Connection c = DriverManager.getConnection(name)) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(query);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createIfNotExists() {
        String query = "CREATE TABLE IF NOT EXISTS PRODUCT " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " PRICE          INT     NOT NULL)";
        sqlUpdate(query);
    }

    public void drop() {
        sqlUpdate("DROP TABLE IF EXISTS PRODUCT");
    }
}
