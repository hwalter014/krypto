import java.io.*;

public class Prak2 {
    public static void blark() {
        DataInputStream dataInputStream;
        String chiffratPath = "text/Praktikum02/chiffrat.bin";
        try {
            dataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(chiffratPath)));
            int current = 0;
            while (current != -1) {
                current = dataInputStream.readByte();
                if (current != -1) {
                    System.out.print(current + " , ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
