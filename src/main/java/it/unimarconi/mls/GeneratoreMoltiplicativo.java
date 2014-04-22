package it.unimarconi.mls;

import java.util.ArrayList;
import java.util.List;

public class GeneratoreMoltiplicativo {

    public static List<Integer> generate(Integer a, Integer x0, Integer m) {
        List<Integer> l = new ArrayList<Integer>();
        Integer next = x0;
        while (!l.contains(next)) {
            l.add(next);
            next = (a * next) % m;
        }
        return l;
    }

    /**
     * @param m
     * @param q
     * @return
     *
     * Si genera una sequenza valutando a = q mod m che equivale ad a = m * t + q
     */
    public static List<Integer> generateA(Integer m, Integer q, Integer values) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0 ; i < values ; i++)
            l.add(i * m + q);
        return l;
    }

    public static List<Integer> generateX(Integer m) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 1 ; i < m ; i += 2)
            l.add(i);
        return l;
    }

}