package ru.nvaleyev.sd.refactoring.print;

import ru.nvaleyev.sd.refactoring.html.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintOne implements PrintMethod {
    final HttpServletResponse response;
    final String header;

    public PrintOne(HttpServletResponse response, String header) {
        this.response = response;
        this.header = header;
    }

    public void print(ResultSet rs) throws IOException, SQLException {
        PrintWriter writer = response.getWriter();

        writer.println(HtmlUtils.bodyHeader());
        writer.println(header);

        while (rs.next()) {
            writer.println(rs.getInt(1));
        }

        writer.println(HtmlUtils.bodyFooter());
    }
}
