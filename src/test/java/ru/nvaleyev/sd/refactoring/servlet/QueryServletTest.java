package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class QueryServletTest extends BaseTest {
    private static final QueryServlet servlet = new QueryServlet();

    private void setUpAddRequestMock(String name, String price) {
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("price")).thenReturn(price);
    }

    private void setUpQueryRequestMock(String command) {
        when(request.getParameter("command")).thenReturn(command);
    }

    @Before
    public void setUp() throws IOException {
        setUpResponseMock();
        AddProductServlet addServlet = new AddProductServlet();

        setUpAddRequestMock("lol", "1000");
        addServlet.doGet(request, response);

        setUpAddRequestMock("lol2", "999");
        addServlet.doGet(request, response);

        setUpAddRequestMock("lol3", "998");
        addServlet.doGet(request, response);

        assertEquals("OK\nOK\nOK\n", getResponses());
    }

    @Test
    public void testUnknownCommand() throws IOException {
        setUpResponseMock();
        setUpQueryRequestMock("you don't know this");

        servlet.doGet(request, response);
        assertEquals("Unknown command: you don't know this\n", getResponses());
    }

    @Test
    public void testCount() throws IOException {
        setUpResponseMock();
        setUpQueryRequestMock("count");

        servlet.doGet(request, response);
        assertEquals("<html><body>\nNumber of products: \n3\n</body></html>\n", getResponses());
    }

    @Test
    public void testMax() throws IOException {
        setUpResponseMock();
        setUpQueryRequestMock("max");

        servlet.doGet(request, response);
        assertEquals("<html><body>\n<h1>Product with max price: </h1>\nlol\t1000</br>\n</body></html>\n", getResponses());
    }

    @Test
    public void testMin() throws IOException {
        setUpResponseMock();
        setUpQueryRequestMock("min");

        servlet.doGet(request, response);
        assertEquals("<html><body>\n<h1>Product with min price: </h1>\nlol3\t998</br>\n</body></html>\n", getResponses());
    }

    @Test
    public void testSum() throws IOException {
        setUpResponseMock();
        setUpQueryRequestMock("sum");

        servlet.doGet(request, response);
        assertEquals("<html><body>\nSummary price: \n2997\n</body></html>\n", getResponses());
    }
}
