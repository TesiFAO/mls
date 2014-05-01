package it.unimarconi.mls;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Pratica1Test extends TestCase {

    public void testGenerate() {
        List<List<Integer>> out = generate(5);
        System.out.println("Ci sono "+ out.size() + " liste differenti.");
        for (List<Integer> l : out) {
            System.out.print("[X0 = " + l.get(0) + "]: ");
            for (int j = 0; j < l.size(); j++) {
                System.out.print(l.get(j));
                if (j < l.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> generate(Integer b) {

        /** Genera il parametro a come a = q mod m */
//        Integer a = GeneratoreMoltiplicativo.generateA(8, 5, 1).get(0);
//        Integer m = 29;
//        List<Integer> xs = GeneratoreMoltiplicativo.generateX(m);
//        List<Integer> memory = null;
        List<List<Integer>> out = new ArrayList<List<Integer>>();
//        Integer differentLists = 1;
//        for (int i = 0 ; i < xs.size() ; i++) {
//            Integer x0 = xs.get(i);
//            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
//            if (memory == null) {
//                memory = l;
//                out.add(l);
//            }
//            if(!l.equals(memory)) {
//                differentLists++;
//                out.add(l);
//            } else if (l.equals(memory) && differentLists > 1) {
//                break;
//            }
//        }
        return out;
    }

}