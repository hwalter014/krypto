import java.io.*;

public class Prak2 {
    public static void aufgabe1() {
        DataInputStream chiffratInputStream;
        DataInputStream randomInputStream;
        String chiffratPath = "text/Praktikum02/chiffrat.bin";
        String randomPath = "text/Praktikum02/random.dat";
        try {
            //chiffrat Datei oeffnen
            chiffratInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(chiffratPath)));

            //random Datei oeffnen
            randomInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(randomPath)));

            //Lesevariablen mit Anfangswerten belegen
            byte currtenRandom = 0;
            byte currentchiffrat = 0;
            byte charDecrypt = 0;
            boolean foundChar = false;

            //Random.dat Datei einfach durchlaufen
            while (currtenRandom != -1) {

                currtenRandom = randomInputStream.readByte();

                //chiffrat Datei durchlaufen
                while (currentchiffrat != -1) {

                    currentchiffrat = chiffratInputStream.readByte();

                    //Zeichen entschluesseln
                    charDecrypt = (byte) (currtenRandom ^ currentchiffrat);

                    //zuerst gucken ob es Großbuchstabe ist oder ob es Kleinbuchstabe ist
                    //65 ^= A , 90 ^= Z und 65 ^= a , 90 ^= z
                    if (charDecrypt >= 65 && charDecrypt <= 90 || charDecrypt >= 97 && charDecrypt <= 122) {
                        foundChar = true;
                        System.out.print((char) charDecrypt + " , ");
                    } else {
                        break;
                    }
                }
                if (foundChar) {
                    foundChar = false;
                    System.out.println("neuer Durchlauf folgt");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
