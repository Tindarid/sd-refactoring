package ru.nvaleyev.sd.refactoring.print;

import ru.nvaleyev.sd.refactoring.product.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintAll implements PrintMethod {
    final HttpServletResponse response;
    final String header;

    public PrintAll(HttpServletResponse response, String header) {
        this.response = response;
        this.header = header;
    }

    public PrintAll(HttpServletResponse response) {
        this.response = response;
        this.header = null;
    }

    public void print(ResultSet rs) throws IOException, SQLException {
        response.getWriter().println("<html><body>");

        if (this.header != null) {
            response.getWriter().println(header);
        }

        while (rs.next()) {
            response.getWriter().println(new Product(rs).toHtml());
        }

        response.getWriter().println("</body></html>");
    }
}
