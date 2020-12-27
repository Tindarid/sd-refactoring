package ru.nvaleyev.sd.refactoring.servlet;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class QueryServletTest extends BaseTest {
    public void setUpSimple() throws IOException {
        assertEquals(OK_ADD, doAdd("lol", "1000"));
        assertEquals(OK_ADD, doAdd("lol2", "999"));
        assertEquals(OK_ADD, doAdd("lol3", "998"));
    }

    @Test
    public void testUnknownCommand() throws IOException {
        setUpSimple();

        String command = "you don't know this";
        assertEquals("Unknown command: " + command + "\n", doCommand(command));
    }

    @Test
    public void testCount() throws IOException {
        setUpSimple();

        assertEqualsWithBody("Number of products: \n3\n", doCommand("count"));
    }

    @Test
    public void testMax() throws IOException {
        setUpSimple();

        assertEqualsWithBody("<h1>Product with max price: </h1>\nlol\t1000</br>\n", doCommand("max"));
    }

    @Test
    public void testMin() throws IOException {
        setUpSimple();

        assertEqualsWithBody("<h1>Product with min price: </h1>\nlol3\t998</br>\n", doCommand("min"));
    }

    @Test
    public void testSum() throws IOException {
        setUpSimple();

        assertEqualsWithBody("Summary price: \n2997\n", doCommand("sum"));
    }

    @Test
    public void testRandom() throws IOException {
        final int iters = 200;
        final Random rand = new Random();

        long sum = 0;
        long min = 0;
        long max = 0;

        for (int count = 1; count < iters; ++count) {
            long next = rand.nextInt(1000);

            assertEquals(OK_ADD, doAdd("item", String.valueOf(next)));

            if (count == 1) {
                min = next;
                max = next;
            } else {
                min = Math.min(min, next);
                max = Math.max(max, next);
            }
            sum += next;

            assertEqualsWithBody("Number of products: \n" + count + "\n", doCommand("count"));
            assertEqualsWithBody("<h1>Product with max price: </h1>\nitem\t" + max + "</br>\n", doCommand("max"));
            assertEqualsWithBody("<h1>Product with min price: </h1>\nitem\t" + min +"</br>\n", doCommand("min"));
            assertEqualsWithBody("Summary price: \n" + sum + "\n", doCommand("sum"));
        }

    }
}
