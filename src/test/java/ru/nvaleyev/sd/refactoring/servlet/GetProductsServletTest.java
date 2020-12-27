package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GetProductsServletTest extends BaseTest {
    private static final GetProductsServlet servlet = new GetProductsServlet();

    private void setUpRequestMock(String name, String price) {
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("price")).thenReturn(price);
    }

    @Test
    public void testGet() throws IOException {
        setUpResponseMock();
        AddProductServlet addServlet = new AddProductServlet();

        setUpRequestMock("lol", "1000");
        addServlet.doGet(request, response);

        setUpRequestMock("lol2", "999");
        addServlet.doGet(request, response);

        setUpRequestMock("lol3", "998");
        addServlet.doGet(request, response);

        assertEquals("OK\nOK\nOK\n", getResponses());

        setUpResponseMock();
        servlet.doGet(request, response);
        assertEquals("<html><body>\n" +
                     "lol\t1000</br>\n" +
                     "lol2\t999</br>\n" +
                     "lol3\t998</br>\n" +
                     "</body></html>\n",
                getResponses());
    }
}
