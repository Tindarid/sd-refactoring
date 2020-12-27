package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AddProductServletTest extends BaseTest {
    @Test
    public void testSimpleAdd() throws IOException {
        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol", "1000"));
    }

    @Test(expected = NumberFormatException.class)
    public void testNotLong() throws IOException {
        doAdd("lol", "kek");
    }

    @Test
    public void testAddGet() throws IOException {
        assertEquals(OK_ADD, doAdd("kek", "999"));
        assertEqualsWithBody("kek\t999</br>\n", doGet());
    }
}
