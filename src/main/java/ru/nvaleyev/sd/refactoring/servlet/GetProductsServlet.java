package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;
import ru.nvaleyev.sd.refactoring.print.PrintAll;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends BaseServlet {
    public GetProductsServlet(ProductDatabase database) {
        super(database);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        database.sqlQueryWithPrinter("SELECT * FROM PRODUCT", new PrintAll(response));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
