public class Prak3 {

    public static void checkLFSR(int initialwert) {
        int[] lfsr = initialwertBelegen(initialwert);

        String referenzAusgabe = "1111111111111111111110011001100110011001101001011010010110100100";
        String eigeneAusgabe = "";

        //64 Zyklen berechnen ==> zum Vergleich
        int counter = 0;
        while (counter < 64) {

            //Ausgabe
            eigeneAusgabe += String.valueOf(lfsr[0]);

            //Berechnung neues Bit
            //Indizes lustig umrechnen wegen komischer Zaehlung
            int nextBit = lfsr[19] ^ lfsr[0];

            //verschieben mit ArrayCopy
            System.arraycopy(lfsr, 1, lfsr, 0, lfsr.length - 1);

            lfsr[lfsr.length - 1] = nextBit;
            counter++;
        }
        String vergleich = referenzAusgabe.equals(eigeneAusgabe) ? "" : "nicht ";
        System.out.println("Die Ausgaben sind " + vergleich + "identisch");
        System.out.println("Ausgabe ist: ");
        System.out.println(eigeneAusgabe);
    }

    public static int maxPeriode() {

        int rundenAnzahl = 0;
        String referenzZustand = "111111111111111111111";
        String zustand = "";

        int[] lfsr = initialwertBelegen(0x1FFFFF);

        while (!zustand.equals(referenzZustand)) {
            //Ausgabe hier nicht notwendig

            //Berechnung neues Bit
            //Indizes lustig umrechnen wegen komischer Zaehlung
            int nextBit = lfsr[19] ^ lfsr[0];

            //verschieben mit ArrayCopy
            System.arraycopy(lfsr, 1, lfsr, 0, lfsr.length - 1);

            lfsr[lfsr.length - 1] = nextBit;

            //Zustand belegen
            zustand = "";
            for (int box : lfsr) {
                zustand += String.valueOf(box);
            }

            rundenAnzahl++;
        }
        return rundenAnzahl;
    }

    private static int[] initialwertBelegen(int initialwert){
        int[] lfsr = new int[21];

        //initialwert belegen
        int counter = 0;
        while (initialwert > 0) {
            lfsr[counter] = initialwert % 2;
            initialwert = initialwert >> 1;
            counter++;
        }
        return lfsr;
    }

    public static void aufgabe2(){
        int[][] sBox = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13,}
        };

    }
}
