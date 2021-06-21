import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        //intervall des BSI
        BigDecimal uGrenze = BigDecimal.valueOf(2).pow(bitlaenge/2);
        uGrenze = uGrenze.divide(BigDecimal.valueOf(Math.sqrt(2)), 2,RoundingMode.HALF_UP);

        BigInteger oGrenze = valueTwo.pow(bitlaenge/2);

        while (!primeGueltig){
            p = BigInteger.probablePrime(1500, new Random());
            q = BigInteger.probablePrime(1500, new Random());

            if(p.compareTo(uGrenze.toBigInteger()) > 0 && p.compareTo(oGrenze) < 0){
                if (q.compareTo(uGrenze.toBigInteger()) > 0 && q.compareTo(oGrenze) < 0){
                    primeGueltig = true;
                }
            }
        }

        BigInteger n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        //Wert e wird fest gesetzt
        BigInteger e = valueTwo.pow(16).add(BigInteger.ONE);

        BigInteger d = e.modInverse(phi);

        //Ausgabe der Werte

        System.out.println("der öffentliche Key n ist: " + n);
        System.out.println("der private Key d ist: " + d);
        System.out.println("der öffentliche Wert e ist: " + e + " wie der vom BSI kleinste empfohlene Wert");
        System.out.println("der Wert phi ist: " + phi);


        //gleich Aufgabec hinterher, da sonst Spaß mit Variablen Sichtbarkeiten
        BigInteger x = new BigInteger("4711");

        BigInteger chiffrat = RSAencrypt(x,e,n);

        System.out.println("Die Entschluesselung sieht wie folgt aus.");
        System.out.println(RSAdecrypt(chiffrat, d,n));

    }


    public static void aufgabe2a(){
        //Zahlen muessen noch gesetzt werden
        BigInteger p = new BigInteger("13");
        BigInteger q = new BigInteger("17");
        BigInteger messageX = new BigInteger("30");
        BigInteger d = new BigInteger("55");

        BigInteger result = chinesischDecrypt(p,q,messageX,d);
        System.out.println(result);
    }

    public static void aufgabe2b(){
        //Performancetest

        //RSA-System anlegen
        String x = "Das ist ein super geheimer Test fuer die Performance";
        KeyPairGenerator keyPairGenerator;
        BigInteger p = BigInteger.probablePrime(1500,new Random());
        BigInteger q = BigInteger.probablePrime(1500,new Random());


        //Text zu chiffrat verschluesseln


        //Vorberechnung der Konstanten des Chinesishen Restsatzes
        BigInteger cp = q.modInverse(p);
        BigInteger cq = p.modInverse(q);
        BigInteger qcp = q.multiply(cp);
        BigInteger pcq = p.multiply(cq);


        //Performancetest
        int anzahlTests = 10;

        //Test ohne chinesischer Restsatz
        while (anzahlTests > 0){



            anzahlTests--;
        }

        //Test mit Chinesischer Restsatz
        anzahlTests = 10;
        while (anzahlTests > 0){



            anzahlTests--;
        }
    }

    private static BigInteger chinesischDecrypt(BigInteger p1, BigInteger q1, BigInteger x, BigInteger d1){
        BigInteger n = p1.multiply(q1);

        //Schritt1
        BigInteger xp = x.mod(p1);
        BigInteger xq = x.mod(q1);

        BigInteger valueOne = new BigInteger("1");

        BigInteger dp = d1.mod(p1.subtract(valueOne));
        BigInteger dq = d1.mod(q1.subtract(valueOne));

        //Schritt 2
        BigInteger yp = xp.pow(dp.intValue()).mod(p1);
        BigInteger yq = xq.pow(dq.intValue()).mod(q1);

        //Schritt 3 Ruecktransformation
        BigInteger cp = q1.modInverse(p1);
        BigInteger cq = p1.modInverse(q1);
        return q1.multiply(cp).multiply(yp).add(p1.multiply(cq).multiply(yq)).mod(n);
    }

    private static BigInteger RSAencrypt(BigInteger message, BigInteger e, BigInteger n){
        return message.modPow(e,n);
    }

    private static BigInteger RSAdecrypt(BigInteger message, BigInteger d, BigInteger n){
        return message.modPow(d,n);
    }
}
