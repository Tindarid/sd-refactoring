package ru.nvaleyev.sd.refactoring.print;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        response.getWriter().println("<html><body>");
        response.getWriter().println(header);

        if (rs.next()) {
            response.getWriter().println(rs.getInt(1));
        }

        response.getWriter().println("</body></html>");
    }
}
