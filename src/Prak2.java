import java.io.*;

public class Prak2 {
    DataInputStream dataInputStream;

    {
        try {
            dataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(new
                                    File("text/Praktikum02/chiffrat.bin"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
