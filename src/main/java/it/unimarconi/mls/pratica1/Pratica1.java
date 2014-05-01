package it.unimarconi.mls.pratica1;

import it.unimarconi.mls.Utils;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Pratica1 {

    public List<List<Integer>> generaInsiemeInteri(int a, int x0, int m) {
        List<Integer> xos = Utils.generaX(m);
        List<Integer> memory = new ArrayList<Integer>();
        List<List<Integer>> domain = new ArrayList<List<Integer>>();
        for (int i = 0 ; i < xos.size() ; i++) {
            List<Integer> xs = Utils.generaCongruenteMoltiplicativo(a, xos.get(i), m);
            if (!xs.equals(memory)) {
                domain.add(xs);
                memory = xs;
            }
        }
        return domain;
    }

}