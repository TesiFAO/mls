package it.unimarconi.mls.pratica4;

import junit.framework.TestCase;

public class Pratica4Test extends TestCase {

    public void testUniformita() {

        Pratica4 p = new Pratica4();

        int x0 = 1;
        int b = 19;

        p.uniformita(15, x0, b);
        System.out.println();
        p.uniformita(79, x0, b);
        System.out.println();

        x0 = 3;

        p.uniformita(65, x0, b);
        System.out.println();
        p.uniformita(85, x0, b);
        System.out.println();

    }

}