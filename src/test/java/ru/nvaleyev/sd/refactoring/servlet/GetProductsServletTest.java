package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GetProductsServletTest extends BaseTest {
    @Test
    public void testGet() throws IOException {
        setUpResponseMock();

        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol2", "999"));
        assertEquals(OK_ADD, doAdd("lol3", "998"));

        assertEqualsWithBody("lol\t1000</br>\nlol2\t999</br>\nlol3\t998</br>\n", doGet());
    }
}
