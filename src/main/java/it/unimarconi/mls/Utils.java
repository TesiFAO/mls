package it.unimarconi.mls;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Utils {

    /**
     * @param a  a
     * @param x0 Seed
     * @param b  m = 2 ^ b
     * @return Sequenza pseudo-casuale
     * <p/>
     * Genera una sequenza di valori pseudo-casuali
     * tramite generatore congruente moltiplicativo
     */
    public static List<Integer> generaCongruenteMoltiplicativo(Integer a, Integer x0, Integer b) {
        List<Integer> l = new ArrayList<Integer>();
        Integer next = x0;
        int m = (int)Math.pow(2, b);
        while (!l.contains(next)) {
            l.add(next);
            next = (a * next) % m;
        }
        return l;
    }

    /**
     * @param a  a
     * @param x0 Seed
     * @param m  Modulo
     * @return Sequenza pseudo-casuale di valori compresi tra 0 ed 1
     * <p/>
     * Genera una sequenza di valori pseudo-casuali
     * compresi tra 0 ed 1
     * tramite generatore congruente moltiplicativo
     */
    public static List<Double> generaRn(int a, int x0, int m) {
        List<Double> l1 = new ArrayList<Double>();
        List<Double> l2 = new ArrayList<Double>();
        double next = x0;
        while (!l1.contains(next)) {
            l1.add(next);
            next = (a * next) % m;
        }
        for (Double d : l1)
            l2.add(d / m);
        return l2;
    }

    /**
     * @param m Pendenza retta
     * @param q Intercetta retta
     * @return Lista di valori interi
     * <p/>
     * Genera una sequenza valutando a = q mod m che equivale ad a = m * t + q
     * @values Numero di valori da generare per la sequenza
     */
    public static List<Integer> generaA(Integer m, Integer q, Integer values) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0; i < values; i++)
            l.add(i * m + q);
        return l;
    }

    /**
     * @param b m = 2 ^b
     * @return Lista di valori dispari minori del modulo <code>m</code> dato
     * <p/>
     * Generazione di sequenza di numeri dispari minori del modulo dato
     */
    public static List<Integer> generaX(Integer b) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 1; i < Math.pow(2, b); i += 2)
            l.add(i);
        return l;
    }

    public static void print(List<Integer> l) {
        for (int i = 0 ; i < l.size() ; i++) {
            System.out.print(l.get(i));
            if (i < l.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

}