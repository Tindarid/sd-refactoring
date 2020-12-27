package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class QueryServletTest extends BaseTest {
    @Before
    public void setUp() throws IOException {
        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol2", "999"));
        assertEquals(OK_ADD, doAdd("lol3", "998"));
    }

    @Test
    public void testUnknownCommand() throws IOException {
        String command = "you don't know this";
        assertEquals("Unknown command: " + command + "\n", doCommand(command));
    }

    @Test
    public void testCount() throws IOException {
        assertEquals("<html><body>\nNumber of products: \n3\n</body></html>\n", doCommand("count"));
    }

    @Test
    public void testMax() throws IOException {
        assertEquals("<html><body>\n<h1>Product with max price: </h1>\nlol\t1000</br>\n</body></html>\n", doCommand("max"));
    }

    @Test
    public void testMin() throws IOException {
        assertEquals("<html><body>\n<h1>Product with min price: </h1>\nlol3\t998</br>\n</body></html>\n", doCommand("min"));
    }

    @Test
    public void testSum() throws IOException {
        assertEquals("<html><body>\nSummary price: \n2997\n</body></html>\n", doCommand("sum"));
    }
}
