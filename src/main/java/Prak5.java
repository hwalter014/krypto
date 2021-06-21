import java.math.BigInteger;
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

        boolean primGueltig = false;
        int bitlaenge = 3000;

        BigInteger obereGrenze = new BigInteger("2");
        BigInteger p = new BigInteger("0");
        BigInteger q = new BigInteger("0");

        while (!primGueltig){

            p = BigInteger.probablePrime(bitlaenge/2, new Random());
            q = BigInteger.probablePrime(bitlaenge/2, new Random());

            //pruefen ob Primzahlen im Intervall liegen
            primGueltig = (p.subtract(q).compareTo(obereGrenze.pow(bitlaenge / 2).divide(new BigInteger("2").sqrt())) >= 0)
                    && (p.subtract(q).compareTo(obereGrenze.pow(bitlaenge / 2)) <= 0);
        }


        BigInteger n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("2").pow(16).add(BigInteger.ONE);

        BigInteger d = gcd(e, phi);
        BigInteger f = e.modInverse(phi);

        //Ausgabe der Werte

        System.out.println("der öffentliche Key n ist: " + n);
        System.out.println("der private Key d ist: " + d);
        System.out.println("der öffentliche Wert e ist: " + e);
        System.out.println("der Wert phi ist: " + phi);

    }


    // extended Euclidean Algorithm
    private static BigInteger gcd(BigInteger a, BigInteger b){
        if (a.compareTo(new BigInteger("0")) == 0)
            return b;

        return gcd(b.mod(a), a);
    }
}
