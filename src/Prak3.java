import java.util.Arrays;

public class Prak3 {

    public static String checkLFSR(int initialwert){
        int[] lfsr = new int[64];
        for(int i = lfsr.length-1; i > 0; i--){
            lfsr[i] = lfsr[i-1];
        }
        lfsr[0] = lfsr[21] ^ lfsr[2] ^ lfsr[0];
        System.out.println(Arrays.toString(lfsr));
        return "";
    }
}
