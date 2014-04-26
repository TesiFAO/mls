package it.unimarconi.mls;

import junit.framework.TestCase;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.stat.correlation.Covariance;

import java.util.ArrayList;
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
        List<Integer> as = GeneratoreMoltiplicativo.generateA(8, 5, 25);
        List<Integer> memory = null;
        Integer differentLists = 1;
        for (int i = 0 ; i < as.size() ; i++) {
            Integer a = as.get(i);
            Integer x0 = 3;
            Integer b = 5;
            Integer m = new Double(Math.pow(2, b)).intValue();
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

    public void testGenerateWithXs() {
        Integer a = GeneratoreMoltiplicativo.generateA(8, 5, 1).get(0);
        Integer b = 6;
        Integer m = new Double(Math.pow(2, b)).intValue();
        List<Integer> xs = GeneratoreMoltiplicativo.generateX(m);
        System.out.println(xs.size() + " values for x");
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

    public void testCov() {

        /* Initiate data matrix. */
        List<List<Integer>> data = new ArrayList<List<Integer>>();

        /* Initiate parameters. */
        List<Integer> as = GeneratoreMoltiplicativo.generateA(8, 5, 4);
        Integer b = 5;
        Integer m = new Double(Math.pow(2, b)).intValue();
        Integer x0 = 3;

        /* Generate sequences changing the value of parameter a. */
        for (int i = 0 ; i < as.size() ; i++) {
            Integer a = as.get(i);
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            data.add(l);
        }

        /* Iterate over the lists to calculate the covariance values. */
        for (int i = 0 ; i < data.size() - 1 ; i++) {
            double one[] = list2array(data.get(i));
            double two[] = list2array(data.get(i + 1));
            double cov = new Covariance().covariance(one, two);
            System.out.println(cov);
        }

    }

    public double[] list2array(List<Integer> l) {
        double a[] = new double[l.size()];
        for (int i = 0 ; i < l.size() ; i++)
            a[i] = l.get(i);
        return a;
    }

    public void testRange() {
        Integer b = 10;
        Integer m = new Double(Math.pow(2, b)).intValue();
        Integer x0 = 3;
        Integer a = 5;
        List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
        List<Double> r = new ArrayList<Double>();
        for (Integer i : l)
            r.add((double)i / m);
        double min = 30;
        double max = 49;
        List<Double> test = new ArrayList<Double>();
        for (Double d : r) {
            double tmp = min + d * ((max - min) + 1);
            assertTrue(tmp >= 30 && tmp <= 50);
            if (!test.contains(tmp))
                test.add(tmp);
        }
        assertEquals(r.size(), test.size());
    }

}
