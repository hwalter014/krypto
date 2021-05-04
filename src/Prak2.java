import java.io.*;
import java.util.ArrayList;

public class Prak2 {
    public static void aufgabe1() {

        //Byte Arrays wo Dateien gespeichert werden sollen
        byte[] randomInput;
        byte[] chiffratInput;

        //Dateipfade zu chiffrat und Schluesselstrom
        String chiffratPath = "text/Praktikum02/chiffrat.bin";
        String randomPath = "text/Praktikum02/random.dat";
        try {
            //chiffrat Datei oeffnen und lesen
            chiffratInput = new BufferedInputStream(
                    new FileInputStream(chiffratPath)).readAllBytes();

            //random Datei oeffnen und lesen
            randomInput = new BufferedInputStream(
                    new FileInputStream(randomPath)).readAllBytes();

            //Lesevariablen mit Anfangswerten belegen
            byte charDecrypt;
            boolean foundChar = false;

            //Durch random iterieren
            for (byte randomByte : randomInput) {

                //durch chiffrat iterieren
                for (byte chiffratByte : chiffratInput) {

                    //Zeichen entschluesseln
                    charDecrypt = (byte) (randomByte ^ chiffratByte);

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
    private static ArrayList<String> tobinary(ArrayList<Integer> array) {
        ArrayList<String> chiffrat_binary = new ArrayList<>();
        for (int c : array) {// für jede Zahl
            String clone = "";
            String s = Integer.toBinaryString(c) + "";
            if (s.length() > 8) { // der fall, dass die Zahl länger ist als 8 stellen -> abschneiden
                for (int i = 0; i < 8; i++) {
                    clone = clone + s.charAt((s.length() - 8 + i));
                }
            } else if (s.length() == 8) {
                clone = s;
            } else { //der fall, dass die Zahl kleiner ist als 8 -> mit 0 erweitern
                clone = s;
                String nullen = "";
                while (clone.length() < 8) {
                    clone = "0" + clone;
                }
            }
            chiffrat_binary.add(clone);
        }

        return chiffrat_binary;
    }

    public static void aufgabe2() {

        //Vermutung ueber Angriff
        //https://crypto.stackexchange.com/questions/59/taking-advantage-of-one-time-pad-key-reuse
        //gute Beschreibung
        //mal schauen ob die hilft

        //abgegriffene Chiffrate

        final int chiffratLaenge = 20;

        String chiffrat1 = "JKKKPJHKCODRDHDXBEJM";
        String chiffrat2 = "FYWHXANMDZMTQQJXQBWD";
        String chiffrat3 = "LEJSCWXWVKDVAPWPBXWI";

        //Vermutung fuer Wort
        String guess = "CORONA";

        //Feld anlegen wo Text gespeichert werden kann
        int[] xchiff1chiff2 = new int[chiffratLaenge];
        int[] xchiff1chiff3 = new int[chiffratLaenge];
        int[] xchiff2chiff3 = new int[chiffratLaenge];


        //XOR der chiffrate erstellen
        for (int i = 0; i < chiffratLaenge; i++) {
            xchiff1chiff2[i] = chiffrat1.getBytes()[i] ^ chiffrat2.getBytes()[i];
            xchiff1chiff3[i] = chiffrat1.getBytes()[i] ^ chiffrat3.getBytes()[i];
            xchiff2chiff3[i] = chiffrat2.getBytes()[i] ^ chiffrat3.getBytes()[i];
        }

        //Durch XOR der Chiffrate gehen minus guess

        int[][] ergebnisse = new int[3][chiffratLaenge];

        for (int ergeb = 0; ergeb < ergebnisse.length; ergeb++) {
            System.out.println("\nAusgabe Ergebnis " + (ergeb + 1));

            //XOR Durch das Chiffrat minus Guessed Wort
            for (int i = 0; i < chiffratLaenge - guess.length(); i++) {

                for (int j = 0; j < guess.length(); j++) {

                    //je nach Ausgabe Chiffrat auswaehlen
                    switch (ergeb) {
                        case 0 -> ergebnisse[ergeb][i + j] = xchiff1chiff2[i + j] ^ guess.charAt(j);
                        case 1 -> ergebnisse[ergeb][i + j] = xchiff1chiff3[i + j] ^ guess.charAt(j);
                        case 2 -> ergebnisse[ergeb][i + j] = xchiff2chiff3[i + j] ^ guess.charAt(j);
                    }
                }

                //Berechnete Strings ausgeben
                for (int blark : ergebnisse[ergeb]) {
                    System.out.print((char) blark + " ");
                }
                //Zeilenabsatz einfuegen
                System.out.print("\n");

                ergebnisse[ergeb] = new int[chiffratLaenge];
            }
        }

    }

}
