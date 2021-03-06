package ru.nvaleyev.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.nvaleyev.sd.refactoring.database.ProductDatabase;
import ru.nvaleyev.sd.refactoring.servlet.AddProductServlet;
import ru.nvaleyev.sd.refactoring.servlet.GetProductsServlet;
import ru.nvaleyev.sd.refactoring.servlet.QueryServlet;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ProductDatabase database = new ProductDatabase("jdbc:sqlite:production.db");
        database.createIfNotExists();

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(database)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(database)),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(database)),"/query");

        server.start();
        server.join();
    }
}
