package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;
import ru.nvaleyev.sd.refactoring.product.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class QueryServlet extends BaseServlet {
    public QueryServlet(ProductDatabase database) {
        super(database);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("<h1>Product with max price: </h1>");

                    while (rs.next()) {
                        response.getWriter().println(new Product(rs).toHtml());
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("<h1>Product with min price: </h1>");

                    while (rs.next()) {
                        response.getWriter().println(new Product(rs).toHtml());
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT SUM(price) FROM PRODUCT");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("Summary price: ");

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCT");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("Number of products: ");

                    if (rs.next()) {
                        response.getWriter().println(rs.getInt(1));
                    }
                    response.getWriter().println("</body></html>");

                    rs.close();
                    stmt.close();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
