package ru.nvaleyev.sd.refactoring.servlet;

import ru.nvaleyev.sd.refactoring.database.ProductDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {
    private static final ProductDatabase database = new ProductDatabase("jdbc:sqlite:test.db");

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected HttpServletResponse response;

    protected StringWriter setUpResponseMock() throws IOException {
        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        return writer;
    }

    @Before
    public void setUp() throws SQLException {
        database.createIfNotExists();
    }

    @After
    public void tearDown() throws SQLException {
        database.drop();
    }

    protected static final String OK_ADD = "OK\n";

    protected String doAdd(String name, String price) throws IOException {
        StringWriter writer = setUpResponseMock();
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("price")).thenReturn(price);

        new AddProductServlet().doGet(request, response);

        return writer.toString();
    }

    protected String doGet() throws IOException {
        StringWriter writer = setUpResponseMock();

        new GetProductsServlet().doGet(request, response);

        return writer.toString();
    }

    protected String doCommand(String command) throws IOException {
        StringWriter writer = setUpResponseMock();
        when(request.getParameter("command")).thenReturn(command);

        new QueryServlet().doGet(request, response);

        return writer.toString();
    }

    protected static void assertEqualsWithBody(String expected, String actual) {
        assertEquals("<html><body>\n" + expected + "</body></html>\n", actual);
    }
}
