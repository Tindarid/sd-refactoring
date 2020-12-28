package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;
import ru.nvaleyev.sd.refactoring.print.PrintAll;
import ru.nvaleyev.sd.refactoring.print.PrintOne;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends BaseServlet {
    public QueryServlet(ProductDatabase database) {
        super(database);
    }

    @Override
    protected void doGetInternal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            database.sqlQueryWithPrinter("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",
                    new PrintAll(response, "<h1>Product with max price: </h1>"));
        } else if ("min".equals(command)) {
            database.sqlQueryWithPrinter("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",
                    new PrintAll(response, "<h1>Product with min price: </h1>"));
        } else if ("sum".equals(command)) {
            database.sqlQueryWithPrinter("SELECT SUM(price) FROM PRODUCT",
                    new PrintOne(response, "Summary price: "));
        } else if ("count".equals(command)) {
            database.sqlQueryWithPrinter("SELECT COUNT(*) FROM PRODUCT",
                    new PrintOne(response, "Number of products: "));
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

}
