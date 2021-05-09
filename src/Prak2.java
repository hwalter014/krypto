import java.io.*;
import java.util.ArrayList;

public class Prak2 {

    private static final int aufg2chiffratLaenge = 20;

    private static final String aufg2chiffrat1 = "JKKKPJHKCODRDHDXBEJM";
    private static final String aufg2chiffrat2 = "FYWHXANMDZMTQQJXQBWD";
    private static final String aufg2chiffrat3 = "LEJSCWXWVKDVAPWPBXWI";

    //Vermutung fuer Wort
    private static final String guess = "CORONA";

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

            //Durch random iterieren
            for (int randomChar = 0; randomChar <= (randomInput.length - chiffratInput.length); randomChar++) {

                String decryptChiffrat = "";
                //durch chiffrat iterieren
                for (byte chiffratChar : chiffratInput) {
                    decryptChiffrat += (char) (((randomInput[randomChar] + chiffratChar) % 26) + 65);

                }

                //Falls ein enlgisches WORT enthalten ist soll das Chiffrat ausgegeben werden
                if(decryptChiffrat.contains("THE")){
                    System.out.println(decryptChiffrat);
                }
            }
        } catch (Exception e) {
            //Fehlerausgabe
            e.printStackTrace();
        }
    }

    public static void aufgabe2() {

        //Vermutung ueber Angriff
        //https://crypto.stackexchange.com/questions/59/taking-advantage-of-one-time-pad-key-reuse
        //gute Beschreibung
        //mal schauen ob die hilft


        //Feld anlegen wo Text gespeichert werden kann
        int[] xchiff1chiff2 = new int[aufg2chiffratLaenge];
        int[] xchiff1chiff3 = new int[aufg2chiffratLaenge];
        int[] xchiff2chiff3 = new int[aufg2chiffratLaenge];


        //XOR der chiffrate erstellen
        for (int i = 0; i < aufg2chiffratLaenge; i++) {
            xchiff1chiff2[i] = (((aufg2chiffrat1.getBytes()[i] ^ aufg2chiffrat2.getBytes()[i]) % 26) + 65);
            xchiff1chiff3[i] = (((aufg2chiffrat1.getBytes()[i] ^ aufg2chiffrat3.getBytes()[i]) % 26) + 65);
            xchiff2chiff3[i] = (((aufg2chiffrat2.getBytes()[i] ^ aufg2chiffrat3.getBytes()[i]) % 26) + 65);
        }

        //Durch XOR der Chiffrate gehen minus guess

        int[][] ergebnisse = new int[3][aufg2chiffratLaenge];

        for (int ergeb = 0; ergeb < ergebnisse.length; ergeb++) {
            System.out.println("\nAusgabe Ergebnis " + (ergeb + 1));

            //XOR Durch das Chiffrat minus Guessed Wort
            for (int i = 0; i <= aufg2chiffratLaenge - guess.length(); i++) {

                for (int j = 0; j < guess.length(); j++) {

                    //je nach Ausgabe Chiffrat auswaehlen
                    switch (ergeb) {
                        case 0 -> ergebnisse[ergeb][i + j] = (((xchiff1chiff2[i + j] ^ guess.charAt(j)) % 26) + 65);
                        case 1 -> ergebnisse[ergeb][i + j] = (((xchiff1chiff3[i + j] ^ guess.charAt(j)) % 26) + 65);
                        case 2 -> ergebnisse[ergeb][i + j] = (((xchiff2chiff3[i + j] ^ guess.charAt(j)) % 26) + 65);
                    }
                }

                //Berechnete Strings ausgeben
                for (int blark : ergebnisse[ergeb]) {
                    System.out.print((char) blark + " ");
                }
                //Zeilenabsatz einfuegen
                System.out.print("\n");

                ergebnisse[ergeb] = new int[aufg2chiffratLaenge];
            }
        }

    }


    public static void aufgabe2Ansatz2(){
        //Feld anlegen wo Text gespeichert werden kann
        char[] xchiff1chiff2 = new char[aufg2chiffratLaenge];
        char[] xchiff1chiff3 = new char[aufg2chiffratLaenge];
        char[] xchiff2chiff3 = new char[aufg2chiffratLaenge];


        //Addition MODULO der chiffrate erstellen
        for (int i = 0; i < aufg2chiffratLaenge; i++) {
            xchiff1chiff2[i] = (char) (((aufg2chiffrat1.getBytes()[i] + aufg2chiffrat2.getBytes()[i]) % 26) + 65);
            xchiff1chiff3[i] = (char) (((aufg2chiffrat1.getBytes()[i] + aufg2chiffrat3.getBytes()[i]) % 26) + 65);
            xchiff2chiff3[i] = (char) (((aufg2chiffrat2.getBytes()[i] + aufg2chiffrat3.getBytes()[i]) % 26) + 65);
        }

        //Durch Addition der Chiffrate gehen minus guess

        char[][] ergebnisse = new char[3][aufg2chiffratLaenge];

        for (int ergeb = 0; ergeb < ergebnisse.length; ergeb++) {
            System.out.println("\nAusgabe Ergebnis " + (ergeb + 1));

            //XOR Durch das Chiffrat minus Guessed Wort
            for (int chiffratIndex = 0; chiffratIndex <= aufg2chiffratLaenge - guess.length(); chiffratIndex++) {

                for (int guessIndex = 0; guessIndex < guess.length(); guessIndex++) {

                    //je nach Ausgabe Chiffrat auswaehlen
                    switch (ergeb) {
                        case 0 -> ergebnisse[ergeb][chiffratIndex + guessIndex] = (char)
                                (((xchiff1chiff2[chiffratIndex + guessIndex] + guess.charAt(guessIndex)) % 26) + 65);

                        case 1 -> ergebnisse[ergeb][chiffratIndex + guessIndex] = (char)
                                (((xchiff1chiff3[chiffratIndex + guessIndex] + guess.charAt(guessIndex)) % 26) + 65);

                        case 2 -> ergebnisse[ergeb][chiffratIndex + guessIndex] = (char)
                                (((xchiff2chiff3[chiffratIndex + guessIndex] + guess.charAt(guessIndex)) % 26) + 65);
                    }
                }

                //Berechnete Strings ausgeben
                for (int blark : ergebnisse[ergeb]) {
                    System.out.print((char) blark + " ");
                }
                //Zeilenabsatz einfuegen
                System.out.print("\n");

                ergebnisse[ergeb] = new char[aufg2chiffratLaenge];
            }
        }

    }

}
