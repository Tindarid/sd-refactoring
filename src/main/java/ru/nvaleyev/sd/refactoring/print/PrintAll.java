package ru.nvaleyev.sd.refactoring.print;

import ru.nvaleyev.sd.refactoring.html.HtmlUtils;
import ru.nvaleyev.sd.refactoring.product.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter writer = response.getWriter();

        writer.println(HtmlUtils.bodyHeader());

        if (this.header != null) {
            writer.println(header);
        }

        while (rs.next()) {
            writer.println(new Product(rs).toHtml());
        }

        writer.println(HtmlUtils.bodyFooter());
    }
}
