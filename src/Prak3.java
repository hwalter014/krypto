import java.util.Arrays;

public class Prak3 {

    public static String checkLFSR(int initialwert){
        int[] lfsr = new int[64];

        //initialwert belegen
        int counter = 0;
        while(initialwert > 0){
            lfsr[counter] = initialwert % 2;
            initialwert = initialwert >> 1;
            counter++;
        }

        int[] ausgabeLFSR = new int[64];

        for (int ausgabeIndex = 0; ausgabeIndex < ausgabeLFSR.length; ausgabeIndex++) {
            for (int i = lfsr.length - 1; i > 0; i--) {
                lfsr[i] = lfsr[i - 1];
            }
            lfsr[0] = lfsr[21] ^ lfsr[2] ^ lfsr[0];
            ausgabeLFSR[ausgabeIndex] = lfsr[0];
        }
        System.out.println(Arrays.toString(ausgabeLFSR));
        return "";
    }
}
