import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.Security;
import java.util.Random;

public class Prak5 {

    public static void aufgabe1a(int anzahlWdhl){

        int primLaenge = 1500;

        long[] primZeiten = new long[anzahlWdhl];

        //Schleife fuer Anzahl zu generier
        int primCounter = 0;
        while(primCounter < anzahlWdhl){

            //Startzeit
            long startZeit = System.currentTimeMillis();

            //generierung der Primzahl
            BigInteger prime1 = BigInteger.probablePrime(primLaenge,new Random());

            //Stopzeit
            long stopZeit = System.currentTimeMillis();

            //Zeit hinzufuegen
            primZeiten[primCounter] = (stopZeit-startZeit);
            primCounter++;
        }

        //Ausgabe der durchschnittszeit
        System.out.println("Die durchschnittliche Zeit war: " + getAvg(primZeiten));

        //Ausgabe der maximalen Zeit
        System.out.println("Die laengste Zeit war: " + getMax(primZeiten) + "Millisekunden");

        //Ausgabe der minimalen Zeit
        System.out.println("Die kuerzeste Zeit war: " + getMin(primZeiten) + "Millisekunden");
    }

    private static long getMax(long[] val){
        long max = val[0];
        for(int i = 1; i < val.length; i++){
            max = Math.max(max, val[i]);
        }
        return max;
    }

    private static long getMin(long[] val){
        long min = val[0];
        for(int i = 1; i < val.length; i++){
            min = Math.min(min, val[i]);
        }
        return min;
    }

    private static double getAvg(long[] val){
        float summe = 0;
        for(long wert : val){
            summe += wert;
        }
        return summe / val.length;
    }


    public static void aufgabe1b(){

        BigInteger p = new BigInteger("1234");
        BigInteger q = new BigInteger("4711");

        BigInteger n = p.multiply(q);

        BigInteger phi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));

        Integer e = (int) Math.pow(2f, 16f) + 1;

        Integer d;


    }
}
