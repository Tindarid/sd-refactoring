package ru.nvaleyev.sd.refactoring.product;

import ru.nvaleyev.sd.refactoring.html.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    public final String name;
    public final Long price;

    public Product(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.price = Long.parseLong(request.getParameter("price"));
    }

    public Product(ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.price  = (long) rs.getInt("price");
    }

    public String toHtml() {
        return HtmlUtils.withBr(name + "\t" + price);
    }

    public String toSql() {
        return "(\"" + name + "\"," + price + ")";
    }
}
