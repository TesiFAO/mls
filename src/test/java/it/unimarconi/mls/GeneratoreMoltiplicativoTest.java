package it.unimarconi.mls;

import junit.framework.TestCase;

import java.util.List;

public class GeneratoreMoltiplicativoTest extends TestCase {

    /**
     * Ricerca del massimo periodo al variare di m
     */
    public void testGenerate() {
        Integer max = 0;
        Integer max_m = 0;
        Integer a = 11;
        Integer x0 = 3;
        for (int m = 1 ; m < 1024 ; m++) {
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            if (max < l.size()) {
                max = l.size();
                max_m = m;
            }
        }
        System.out.println("Periodo massimo " + max + " ottenuto per m = " + max_m);
        List<Integer> lf = GeneratoreMoltiplicativo.generate(a, x0, max_m);
        System.out.println("[a = " + a + "][x = " + x0 + "][m = " + max_m + "] | Periodo: " + lf.size());
        for (int j = 0; j < lf.size(); j++)
            System.out.println(j + ") " + lf.get(j));
        System.out.println("\n");
    }

    /**
     * Il numero di sequenze differenti varia al variare di m = 2 ^ b
     * Il massimo period si ottiene se m = 2 ^ (b - 2) con b >= 4
     */
    public void testGenerateWithAs() {
        List<Integer> as = GeneratoreMoltiplicativo.generateA(8, 5, 10);
        List<Integer> memory = null;
        Integer differentLists = 1;
        for (int i = 0 ; i < as.size() ; i++) {
            Integer a = as.get(i);
            Integer x0 = 3;
            Integer m = 64;
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            if (memory == null)
                memory = l;
            if(!l.equals(memory))
                differentLists++;
            else if (l.equals(memory) && differentLists > 1)
                break;
            System.out.println("[a = " + a + "][x = " + x0 + "][m = " + m + "] | Periodo: " + l.size());
            for (int j = 0; j < l.size(); j++) {
                System.out.print(l.get(j));
                if (j < l.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("\n");
        }
        System.out.println("There are " + differentLists + " different lists");
    }

    public void testGenerateA() {
        Integer m = 8;
        Integer q = 5;
        List<Integer> l = GeneratoreMoltiplicativo.generateA(m, q, 10);
    }

}
