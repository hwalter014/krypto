import java.io.*;

public class Prak2 {
    public static void aufgabe1() {
        DataInputStream chiffratInputStream;
        DataInputStream randomInputStream;
        String chiffratPath = "text/Praktikum02/chiffrat.bin";
        String randomPath = "text/Praktikum02/random.dat";
        try {
            chiffratInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(chiffratPath)));
            randomInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(randomPath)));
            byte currtenRandom = 0;
            byte currentchiffrat = 0;

            while (currentchiffrat != -1) {
                currentchiffrat = chiffratInputStream.readByte();
                if (currentchiffrat != -1) {

                    System.out.print(Integer.toString(currentchiffrat) + " , ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
