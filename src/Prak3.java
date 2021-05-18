public class Prak3 {

    public static void checkLFSR(int initialwert){
        int[] lfsr = new int[21];

        String referenzAusgabe = "1111111111111111111110011001100110011001101001011010010110100100";
        String eigeneAusgabe = "";

        //initialwert belegen
        int counter = 0;
        while(initialwert > 0){
            lfsr[counter] = initialwert % 2;
            initialwert = initialwert >> 1;
            counter++;
        }

        //64 Zyklen berechnen ==> zum Vergleich
        counter = 0;
        while(counter < 64){

            //Ausgabe
            eigeneAusgabe += String.valueOf(lfsr[0]);

            //Berechnung neues Bit
            //Indizes lustig umrechnen wegen komischer Zaehlung
            int nextBit = lfsr[19] ^ lfsr[0];

            //verschieben mit ArrayCopy
            System.arraycopy(lfsr, 1, lfsr, 0, lfsr.length - 1);

            lfsr[lfsr.length-1] = nextBit;
            counter++;
        }
        String vergleich = referenzAusgabe.equals(eigeneAusgabe) ? "" : "nicht ";
        System.out.println("Die Ausgaben sind " + vergleich + "identisch");
        System.out.println("Ausgabe ist: ");
        System.out.println(eigeneAusgabe);
    }
}
