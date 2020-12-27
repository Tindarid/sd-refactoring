package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AddProductServletTest extends BaseTest {
    private static final AddProductServlet servlet = new AddProductServlet();

    private void setUpRequestMock(String name, String price) {
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("price")).thenReturn(price);
    }

    @Test
    public void testSimpleAdd() throws IOException {
        setUpRequestMock("lol", "1000");

        setUpResponseMock();
        servlet.doGet(request, response);
        assertEquals("OK\n", getResponses());
        servlet.doGet(request, response);
        assertEquals("OK\nOK\n", getResponses());

        setUpResponseMock();
        servlet.doGet(request, response);
        assertEquals("OK\n", getResponses());
    }

    @Test(expected = NumberFormatException.class)
    public void testNotLong() throws IOException {
        setUpRequestMock("lol", "kek");
        setUpResponseMock();
        servlet.doGet(request, response);
    }

    @Test
    public void testAddGet() throws IOException {
        setUpRequestMock("kek", "999");

        setUpResponseMock();
        servlet.doGet(request, response);
        assertEquals("OK\n", getResponses());

        setUpResponseMock();
        new GetProductsServlet().doGet(request, response);
        assertEquals("<html><body>\nkek\t999</br>\n</body></html>\n", getResponses());
    }
}
