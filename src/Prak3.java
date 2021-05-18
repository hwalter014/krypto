import java.util.Arrays;

public class Prak3 {

    public static void checkLFSR(int initialwert){
        int[] lfsr = new int[21];

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
            int tmpAusgabe = lfsr[0];
            System.out.print(tmpAusgabe);

            //Berechnung neues Bit
            //Indizes lustig umrechnen wegen komischer Zaehlung
            int nextBit = lfsr[19] ^ lfsr[0];

            //verschieben mit ArrayCopy
            System.arraycopy(lfsr, 1, lfsr, 0, lfsr.length - 1);

            lfsr[lfsr.length-1] = nextBit;
            counter++;
        }
    }
}
