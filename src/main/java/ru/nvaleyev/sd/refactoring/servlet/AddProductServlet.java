package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try {
            database.sqlCall("INSERT INTO PRODUCT (NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("OK");
    }
}
