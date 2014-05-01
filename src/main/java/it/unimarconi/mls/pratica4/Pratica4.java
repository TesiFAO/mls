package it.unimarconi.mls.pratica4;

import it.unimarconi.mls.Utils;

import java.util.*;

public class Pratica4 {

    public void uniformita(int a, int x0, int b) {

        double d = 64.0;
        List<Double> rns = Utils.generaRn(a, x0, b);
//        for (Double rn : rns)
//            if (rn > 1)
//                System.out.println("!!! " + rn);

        double min = Utils.calcolaChiQuadro(64.0, Utils.Z25);
        double max = Utils.calcolaChiQuadro(64.0, Utils.Z75);

//        System.out.println("[a = " + a + "]" + "min: " + min + ", max: " + max);
//        System.out.println();

        /** Z = d * rn */
        List<Double> ds = new ArrayList<Double>();
        for (Double rn : rns) {
//            if (d * rn > d) {
//                System.out.println(d * rn);
//                System.out.println(d);
//                System.out.println(rn);
//                System.out.println();
//            }
            ds.add(Math.floor(d * rn));
        }

//        System.out.println(ds.size());

        int chunks = 3;
        List<List<Double>> testSet = new ArrayList<List<Double>>();
        double size = ds.size() / 3;
//        System.out.println(size);
        for (int i = 0 ; i < chunks ; i++) {
            List<Double> chunk = new ArrayList<Double>();
            for (int j = i * (int)size ; j < (i + 1) * (int)size ; j++) {
                if (ds.get(j) > d)
                    System.out.println("j: " + j + ", add " + ds.get(j));
                chunk.add(ds.get(j));
            }
            testSet.add(chunk);
        }



        for (int k = 0 ; k < chunks ; k++) {

//            System.out.println("n: " + testSet.get(k).size() + ", ps: " + (1.0/64.0) + ", n * ps: " + (testSet.get(k).size() * (1.0/64.0)));

            Collections.sort(testSet.get(k));
            LinkedHashMap<Double, Double> freqs = new LinkedHashMap<Double, Double>();
            for (Double tmp : testSet.get(k)) {
//                System.out.println("tmp: " + tmp);
                try {
                    freqs.put(tmp, 1.0 + freqs.get(tmp));
                } catch (Exception e) {
//                    System.out.println("exception put 1 for category " + tmp);
                    if (tmp <= 64)
                        freqs.put(tmp, 1.0);
                }
            }
            int count = 0;
            for (Double key : freqs.keySet()) {
//                double v = Utils.calcolaV(64.0, freqs.get(key), testSet.get(k).size(), (1.0/64.0));
//                System.out.println("Categoria: " + key.intValue() + ": " + freqs.get(key).intValue() + " osservazioni. V: ");
//                if (v >= min && v < max) {
//    //                System.out.println(v + " é accettabile");
//                    count++;
//                }
            }
            List<Double> yss = new ArrayList<Double>();
            for (Double pippo : freqs.values())
                yss.add(pippo);
            double v = Utils.calcolaV(yss, size, 0.015625);
            if (v > min && v < max)
                System.out.println("OLLE! " + min + " < " + v + " < " + max + " [a = " + a + "][x0 = " + x0 + "]");
            else
                System.out.println("FAILURE! :(  " + min + " < " + v + " < " + max + " [a = " + a + "][x0 = " + x0 + "]");
//            System.out.println(count + " valori accettabili su 63 categorie");
//            System.out.println(v);
        }


    }


    /***
     * test serieale
     *
     * @param a
     * @param x0
     * @param b
     */

    public void seriale(int a, int x0, int b) {
        double d = 64.0;
        int chunks = 3;
        List<List<Double>> chunksSet = createChunks(d, a, x0, b, chunks);

        double min = Utils.calcolaChiQuadro(4095.0, Utils.Z25);
        double max = Utils.calcolaChiQuadro(4095.0, Utils.Z75);

        double[][] matrix = new double[64][64];
        System.out.println("CHUNK SIZE:" + chunksSet.get(0).size());
        System.out.println("CHUNK SIZE:" + ((chunksSet.get(0).size() - 1.0) /2.0));
        for(int i=0; i < chunksSet.get(1).size() -1; i+=2) {
            try {
                matrix[chunksSet.get(1).get(i).intValue()][chunksSet.get(1).get(i+1).intValue()] += 1.0;
            } catch (Exception e) {
                matrix[chunksSet.get(1).get(i).intValue()][chunksSet.get(1).get(i+1).intValue()] = 1.0;
            }
            System.out.println("i: " + chunksSet.get(1).get(i).intValue() + " | " +  chunksSet.get(1).get(i+1).intValue() + " | " + matrix[chunksSet.get(1).get(i).intValue()][chunksSet.get(1).get(i+1).intValue()]);

        }
        printMatrix(matrix);
        /*
        List<Double> yss = new ArrayList<Double>();
        for(int i=0; i< 64; i++){
            for(int j=0; j< 64; j++) {
                yss.add(matrix[i][j]);
            }
        }

        int countN = 0;
        for(int i=0; i< 64; i++){
            for(int j=0; j< 64; j++) {
                if (matrix[i][j] > 0 )
                    countN++;
            }
        }

        System.out.println(yss.size());

        //double v = Utils.calcolaV(yss, 0.999, 0.00024414062);
        double v = Utils.calcolaVseriale(yss, countN, 0.00024414062);
        if (v > min && v < max)
            System.out.println("OLLE! " + min + " < " + v + " < " + max + " [a = " + a + "][x0 = " + x0 + "]");
        else
            System.out.println("FAILURE! :(  " + min + " < " + v + " < " + max + " [a = " + a + "][x0 = " + x0 + "]");
        */
    }


    private List<List<Double>> createChunks(double d, int a, int x0, int b, int chunks ) {
        List<Double> rns = Utils.generaRn(a, x0, b);
        List<Double> ds = new ArrayList<Double>();
        for (Double rn : rns) {
            ds.add(Math.floor(d * rn));
        }

        List<List<Double>> testSet = new ArrayList<List<Double>>();
        double size = ds.size() / 3;
        System.out.println("size:"+ size);
        for (int i = 0 ; i < chunks ; i++) {
            List<Double> chunk = new ArrayList<Double>();
            for (int j = i * (int)size ; j < (i + 1) * (int)size ; j++) {
                if (ds.get(j) > d)
                    System.out.println("j: " + j + ", add " + ds.get(j));
                chunk.add(ds.get(j));
            }
            testSet.add(chunk);
        }
        return testSet;
    }

    private void printMatrix(double[][] matrix) {
        int countzero = 0;
        int  pieni = 0;
        System.out.println("--------------------");
        for(int i=63; i>= 0; i--){
            for(int j=0; j < 64; j++) {
                if ( matrix[i][j] == 0 ) {System.out.print(" "); countzero +=1;}
                else {System.out.print("O");  pieni += 1; }
                //System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("zero: " + countzero);
        System.out.println("pieni: " + pieni);
    }

}