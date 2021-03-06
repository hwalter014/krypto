import java.io.*;
import java.util.Arrays;

public class Prak2 {

    private static final int aufg2chiffratLaenge = 20;

    private static final String aufg2chiffrat1 = "JKKKPJHKCODRDHDXBEJM";
    private static final String aufg2chiffrat2 = "FYWHXANMDZMTQQJXQBWD";
    private static final String aufg2chiffrat3 = "LEJSCWXWVKDVAPWPBXWI";

    //Vermutung fuer Wort
    private static final String guess = "AUSGANGSSPERRENINNRW";

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
            //Startwert angepasst, damit man das schneller zeigen kann
            for (int randomChar = 480_000; randomChar < (randomInput.length - chiffratInput.length); randomChar++) {

                String decryptChiffrat = "";
                //durch chiffrat iterieren
                for (int chiffratIndex = 0; chiffratIndex < chiffratInput.length; chiffratIndex++) {
                    decryptChiffrat += (char) (randomInput[randomChar + chiffratIndex] ^ chiffratInput[chiffratIndex]);

                }

                //Falls ein englisches WORT enthalten ist, soll das Chiffrat ausgegeben werden
                if (decryptChiffrat.contains("the") && decryptChiffrat.contains("of") && decryptChiffrat.contains("is")) {
                    System.out.println("Key beginnt an Stelle: " + randomChar);
                    System.out.println(decryptChiffrat);
                    break;
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


    public static void aufgabe2Ansatz2() {
        char[][] guessedKey = new char[3][guess.length()];
        for (int chiffrat = 0; chiffrat < guessedKey.length; chiffrat++) {
            for (char guessedKeyIndex = 0; guessedKeyIndex < guess.length(); guessedKeyIndex++) {
                switch (chiffrat) {
                    case 0 -> guessedKey[chiffrat][guessedKeyIndex] =
                            getAdditionsPartner(guess.charAt(guessedKeyIndex), aufg2chiffrat1.charAt(guessedKeyIndex));
                    case 1 -> guessedKey[chiffrat][guessedKeyIndex] =
                            getAdditionsPartner(guess.charAt(guessedKeyIndex), aufg2chiffrat2.charAt(guessedKeyIndex));
                    case 2 -> guessedKey[chiffrat][guessedKeyIndex] =
                            getAdditionsPartner(guess.charAt(guessedKeyIndex), aufg2chiffrat3.charAt(guessedKeyIndex));
                }

            }
        }

        for (int versuchKey = 0; versuchKey < guessedKey.length; versuchKey++) {

            System.out.println("\nVersuch mit dem Key abgeleitet aus Chiffrat " + (versuchKey + 1) + " zu entschluesseln.");

            System.out.println("Der Key der Versucht wird sieht folgenderma??en aus: ");
            System.out.println(Arrays.toString(guessedKey[versuchKey]));

            for (int i = 0; i < 3; i++) {
                System.out.println("\nVersuche Chiffrat " + (i + 1) + " mit guessed Key zu entschluesseln.\n");
                for (int chiffratIndex = 0; chiffratIndex < (guessedKey[i].length); chiffratIndex++) {

                    switch (i) {
                        case 0 -> System.out.print(getAdditionsPartner(guessedKey[versuchKey][chiffratIndex], aufg2chiffrat1.charAt(chiffratIndex)));
                        case 1 -> System.out.print(getAdditionsPartner(guessedKey[versuchKey][chiffratIndex], aufg2chiffrat2.charAt(chiffratIndex)));
                        case 2 -> System.out.print(getAdditionsPartner(guessedKey[versuchKey][chiffratIndex], aufg2chiffrat3.charAt(chiffratIndex)));
                    }
                }
            }
            System.out.println("\n--------------------------------------");
        }

    }

    private static char  getAdditionsPartner(char klartext, char chiffrat){
        int a = klartext % 65;
        int r = chiffrat % 65;
        int key = r - a;
        if(key < 0){
            key = 26 + key;
        }
        return (char) (key + 65);
    }
}
