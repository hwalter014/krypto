import java.math.BigInteger;
import java.security.*;
import java.util.Random;

public class Prak5 {

    public static void aufgabe1a(int anzahlWdhl) {

        int primLaenge = 1500;

        long[] primZeiten = new long[anzahlWdhl];

        //Schleife fuer Anzahl zu generier
        int primCounter = 0;
        while (primCounter < anzahlWdhl) {

            //Startzeit
            long startZeit = System.currentTimeMillis();

            //generierung der Primzahl
            BigInteger prime1 = BigInteger.probablePrime(primLaenge, new Random());

            //Stopzeit
            long stopZeit = System.currentTimeMillis();

            //Zeit hinzufuegen
            primZeiten[primCounter] = (stopZeit - startZeit);
            primCounter++;
        }

        //Ausgabe der durchschnittszeit
        System.out.println("Die durchschnittliche Zeit war: " + getAvg(primZeiten));

        //Ausgabe der maximalen Zeit
        System.out.println("Die laengste Zeit war: " + getMax(primZeiten) + " Millisekunden");

        //Ausgabe der minimalen Zeit
        System.out.println("Die kuerzeste Zeit war: " + getMin(primZeiten) + " Millisekunden");
    }

    private static long getMax(long[] val) {
        long max = val[0];
        for (int i = 1; i < val.length; i++) {
            max = Math.max(max, val[i]);
        }
        return max;
    }

    private static long getMin(long[] val) {
        long min = val[0];
        for (int i = 1; i < val.length; i++) {
            min = Math.min(min, val[i]);
        }
        return min;
    }

    private static double getAvg(long[] val) {
        float summe = 0;
        for (long wert : val) {
            summe += wert;
        }
        return summe / val.length;
    }


    public static void aufgabe1b(){

        boolean primeGueltig = false;
        int bitlaenge = 3000;

        BigInteger p = null;
        BigInteger q = null;

        BigInteger valueTwo = new BigInteger("2");

        //Grenzen berechnen
        BigInteger uGrenze = valueTwo.pow(bitlaenge/2).divide(valueTwo.sqrt());
        BigInteger oGrenze = valueTwo.pow(bitlaenge/2);
        //Problem ist dass Grenzen die gleiche Zahl sind.

        while (!primeGueltig){
            p = BigInteger.probablePrime(new Random().ints(1000,1500).findFirst().getAsInt(), new Random());
            q = BigInteger.probablePrime(new Random().ints(1000,1500).findFirst().getAsInt(), new Random());

            if(p.compareTo(uGrenze) > 0 && p.compareTo(oGrenze) < 0){
                if (q.compareTo(uGrenze) > 0 && q.compareTo(oGrenze) < 0){
                    primeGueltig = true;
                }
            }
        }

        BigInteger n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = valueTwo.pow(16).add(BigInteger.ONE);

        BigInteger d = e.gcd(phi);
        BigInteger f = e.modInverse(phi);

        //Ausgabe der Werte

        System.out.println("der öffentliche Key n ist: " + n);
        System.out.println("der private Key d ist: " + d);
        System.out.println("der öffentliche Wert e ist: " + e);
        System.out.println("der Wert phi ist: " + phi);

    }

    public static void aufgabe1bAlternative(){
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(3000);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            System.out.println(privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void aufgabe2c(){

    }
}
