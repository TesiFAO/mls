package it.unimarconi.mls.pratica4;

import junit.framework.TestCase;

public class Pratica4Test extends TestCase {

    public void testSeriale() {
        Pratica4 p = new Pratica4();
        int x0 = 1;
        int b = 19;

//        p.seriale(15, x0, b);
//        System.out.println();
//        p.seriale(79, x0, b);
//        System.out.println();

        x0 = 3;
//        for (int a = 29 ; a <= 91 ; a += 2) {
//            p.seriale(a, x0, b);
//            System.out.println();
//        }

        p.seriale(65, x0, b);
        System.out.println();
        p.seriale(85, x0, b);
        System.out.println();

    }

}