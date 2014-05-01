package it.unimarconi.mls.pratica1;

import junit.framework.TestCase;

import java.util.List;

public class Pratica1JUnit extends TestCase {

    public void testGeneraInsiemeInteri() {
        Pratica1 p = new Pratica1();
        int a = 5;
        int x0 = 1;
        int b = 9;
        List<List<Integer>> domain = p.generaInsiemeInteri(a, x0, b);
        assertEquals((int)Math.pow(2, b - 1), domain.size());
        for (List<Integer> l : domain)
            assertEquals((int)Math.pow(2, b - 2), l.size());
    }

}