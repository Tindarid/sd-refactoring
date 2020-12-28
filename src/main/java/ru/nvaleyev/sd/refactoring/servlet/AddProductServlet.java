package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;
import ru.nvaleyev.sd.refactoring.product.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends BaseServlet {
    public AddProductServlet(ProductDatabase database) {
        super(database);
    }

    @Override
    protected void doGetInternal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product(request);
        database.sqlUpdate("INSERT INTO PRODUCT (NAME, PRICE) VALUES " + product.toSql());
        response.getWriter().println("OK");
    }
}
