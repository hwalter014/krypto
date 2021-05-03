import java.io.*;
import java.util.ArrayList;

public class Prak2 {
    public static void aufgabe1() {

        byte[] randomInput;
        byte[] chiffratInput;

        int counterRandom = 0;
        String chiffratPath = "text/Praktikum02/chiffrat.bin";
        String randomPath = "text/Praktikum02/random.dat";
        try {
            //chiffrat Datei oeffnen
            chiffratInput =  new BufferedInputStream(
                            new FileInputStream(chiffratPath)).readAllBytes();

            //random Datei oeffnen
            randomInput = new BufferedInputStream(
                            new FileInputStream(randomPath)).readAllBytes();

            //Lesevariablen mit Anfangswerten belegen
            byte currtenRandom = 0;
            byte currentchiffrat = 0;
            byte charDecrypt;
            boolean foundChar = false;

            //Random.dat Datei einfach durchlaufen
            for (int i = 0; i < 2_000_000; i++) {

                currtenRandom = randomInputStream.readByte();

                //Schleife fuer jedes Bit des gelesenen Bytes einfuegen

                //chiffrat Datei durchlaufen
                while (currentchiffrat != -1) {

                    currentchiffrat = chiffratInputStream.readByte();

                    //Zeichen entschluesseln
                    charDecrypt = (byte) (currtenRandom ^ currentchiffrat);

                    //zuerst gucken ob es Großbuchstabe ist oder ob es Kleinbuchstabe ist
                    //65 ^= A , 90 ^= Z und 65 ^= a , 90 ^= z
                    if (charDecrypt >= 'A' && charDecrypt <= 'Z' || charDecrypt >= 'a' && charDecrypt <= 'z') {
                        foundChar = true;
                        System.out.print((char) charDecrypt + " , ");
                    } else {
                        break;
                    }
                }
                if (foundChar) {
                    foundChar = false;
                    System.out.println("neuer Durchlauf folgt!");
                }

            }

        } catch (Exception e) {
            //Fehlerausgabe
            e.printStackTrace();
        }
    }

    //Methode von Henrik mit Strings
    private static ArrayList<String> tobinary(ArrayList<Integer> array){
        ArrayList<String> chiffrat_binary = new ArrayList<>();
        for(int c : array){// für jede Zahl
            String clone = "";
            String s = Integer.toBinaryString(c) + "";
            if(s.length() > 8){ // der fall, dass die Zahl länger ist als 8 stellen -> abschneiden
                for(int i  = 0 ; i < 8 ; i++) {
                    clone = clone + s.charAt((s.length() -8 + i));
                }
            }
            else if(s.length() == 8) {
                clone = s;
            }
            else{ //der fall, dass die Zahl kleiner ist als 8 -> mit 0 erweitern
                clone = s;
                String nullen = "";
                while(clone.length() < 8) {
                    clone = "0" + clone;
                }
            }
            chiffrat_binary.add(clone);
        }

        return chiffrat_binary;
    }

}
