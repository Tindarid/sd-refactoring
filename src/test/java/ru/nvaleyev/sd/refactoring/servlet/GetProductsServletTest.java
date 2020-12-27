package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GetProductsServletTest extends BaseTest {
    private static final GetProductsServlet servlet = new GetProductsServlet();

    @Test
    public void testGet() throws IOException {
        setUpResponseMock();

        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol2", "999"));
        assertEquals(OK_ADD, doAdd("lol3", "998"));

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
