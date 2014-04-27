package it.unimarconi.mls;

import com.sun.deploy.util.ArrayUtil;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Pratica2 extends TestCase {

    public void testGenerateRn() {
        List<Double> l = generateRn(5, 1, 212);
        for (Double d : l) {
            assertTrue(d <= 1);
            System.out.println(d);
        }
    }

    public void testGenerateRange() {
        List<Double> l = generateRange(5, 1, 212, 30, 50);
        for (Double d : l) {
            assertTrue(d >= 30 && d <= 50);
            System.out.println(d);
        }
    }

    public void testGenerateExponential() {
        List<Double> l = generateExponential(5, 1, 212, 20);
        double sum = 0.0;
        double s2 = 0.0;
        for (Double d : l) {
            sum += d;
        }
        System.out.println("Sum: "+ sum);
        System.out.println("Media: "+ (sum / l.size()));
        printR(l);
    }

    public void testGenerateHyperexponential() {
        List<Double> l = generateHyperexponential(5, 1, 212, 20, 0.38);
        printR(l);
    }

    /* generate between 0 and 1 */
    public static List<Double> generateRn(int a, int x0, int m) {
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

    public List<Double> generateRange(int a, int x0, int m, int min, int max) {
        List<Double> rns = generateRn(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double rn : rns)
            l.add(min + rn * (max - min));
        return l;
    }

    public List<Double> generateExponential2(int a, int x0, int m, int avg) {
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        double lambda = 1.0 / avg;
        for(Double rn : rns)
            l.add(-avg * Math.log(rn));
        return l;
    }

    public List<Double> generateExponential(int a, int x0, int m, int avg) {
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        double lambda = 1.0 / avg;
        for(Double rn : rns)
            l.add((-1 / lambda) * Math.log(rn));
        return l;
    }

    public List<Double> generateHyperexponential(int a, int x0, int m, int avg, double p) {
        List<Double> zs = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        List<Double> Xs = generateExponential(a, x0, m, 1);
        for (int i = 0 ; i < rns.size() ; i++) {
            if (rns.get(i) <= p) {
                zs.add(Xs.get(i) * (avg / (2 * p)));
            } else {
                zs.add(Xs.get(i) * (avg / (2 * (1 - p))));
            }
        }
        return zs;
    }

    private void printR(List<Double> l) {
//        Collections.sort(l);
        String s = "l = c(";
        for (int i = 0 ; i < l.size() ; i++) {
            s += l.get(i);
            if (i < l.size() - 1)
                s += ", ";
        }
        s += "); summary(l); var(l);";
        System.out.println(s);
    }

}