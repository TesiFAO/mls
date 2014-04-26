package it.unimarconi.mls;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Pratica1 extends TestCase {

    public void testGenerate() {
        System.out.println("Ci sono "+ generate(5) + " liste differenti.");
    }

    public Integer generate(Integer b) {
        Integer a = GeneratoreMoltiplicativo.generateA(8, 5, 1).get(0);
        Integer m = 29;
        List<Integer> xs = GeneratoreMoltiplicativo.generateX(m);
        List<Integer> memory = null;
        Integer differentLists = 1;
        for (int i = 0 ; i < xs.size() ; i++) {
            Integer x0 = xs.get(i);
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            if (memory == null)
                memory = l;
            if(!l.equals(memory))
                differentLists++;
            else if (l.equals(memory) && differentLists > 1)
                break;
            System.out.print("[X0 = " + x0 + "]: ");
            for (int j = 0; j < l.size(); j++) {
                System.out.print(l.get(j));
                if (j < l.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }
        return differentLists;
    }

    /**
     * @param m Pendenza retta
     * @param q Intercetta retta
     * @values  Numero di valori da generare per la sequenza
     * @return  Lista di valori interi
     *
     * Si genera una sequenza valutando a = q mod m che equivale ad a = m * t + q
     */
    public static List<Integer> generateA(Integer m, Integer q, Integer values) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0 ; i < values ; i++)
            l.add(i * m + q);
        return l;
    }

    /**
     * @param m Modulo
     * @return  Lista di valori uniformemente distribuiti
     *
     * Implementazione del generatore congruente moltiplicativo
     */
    public static List<Integer> generateX(Integer m) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 1 ; i < m ; i += 2)
            l.add(i);
        return l;
    }

}