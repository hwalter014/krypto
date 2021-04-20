import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prak1 {
    public static HashMap<Character, Double> relHaeufigkeitMonogramm(String chiffrat) {
        HashMap<Character, Double> dictHaeufigkeit = new HashMap<>();
        double chiffratLaenge = Helper.anzahlBuchstaben(chiffrat);
        for (int i = 0; i < 26; i++) {
            int anzahlBuchstabe = 0;
            for (char zeichen :
                    chiffrat.toCharArray()) {
                anzahlBuchstabe += zeichen == (i + 65) ? 1 : 0;
            }
            double verteilungBuchstabe = Helper.round(anzahlBuchstabe / chiffratLaenge * 100,2);
            dictHaeufigkeit.put((char) (i + 65), verteilungBuchstabe);
        }
        return dictHaeufigkeit;
    }

    public static HashMap<String, Double> relHaeufigkeitBigramm(String chiffrat) {
        HashMap<String, Double> bigramme = new HashMap<>();
        int chiffratLaenge = Helper.anzahlBuchstaben(chiffrat);

        for (int ersterBuchstabe = 65; ersterBuchstabe <= 90; ersterBuchstabe++) {
            for (int zweiterBuchstabe = 65; zweiterBuchstabe <= 90; zweiterBuchstabe++) {

                int anzahlAuftreten = 0;
                for (int chiffratIndex = 0; chiffratIndex < chiffrat.length() - 1; chiffratIndex++) {

                    if ((char) ersterBuchstabe == chiffrat.charAt(chiffratIndex) && (char) zweiterBuchstabe == chiffrat.charAt(chiffratIndex + 1)) {
                        anzahlAuftreten++;
                    }
                }
                String bigrammelement = "" + (char) ersterBuchstabe + (char) zweiterBuchstabe;
                double haeufigkeit = anzahlAuftreten / (double) chiffratLaenge;
                bigramme.put(bigrammelement, haeufigkeit);
            }
        }
        return bigramme;
    }

    public static String decryptChiffratC1(String chiffrat) {
        //StringBuilder verwenden, damit .append() Methode funktioniert
        StringBuilder text = new StringBuilder();

        //Haeufigkeitsverteilung des Textes chiffrat ermitteln
        HashMap<Character, Double> relHaeufigkeit = relHaeufigkeitMonogramm(chiffrat);

        //HashMap nach groesse absteigend sortieren
        Map<Character, Double> map = Helper.sortByValue(relHaeufigkeit);

        //neue Hashmap fuer Buchstabe auf Buchstabe erstellen
        HashMap<Character, Character> permutationschiffre = new HashMap<>();


        //Buchstaben nach Haeufigkeit der deutschen Sprache
        Character[] buchstaben = Helper.getWSKGermanLanguage().keySet().toArray(new Character[0]);

        //Index deklaration, weil for each Loop verwendet wurde
        int index = 0;

        //Permutationschiffre befuellen nach Verteilung deutscher Sprache
        for (Character key : map.keySet()) {
            permutationschiffre.put(key, buchstaben[index++]);

        }

        //Text chiffrat auf neue Buchstaben aus permutationschiffre mappen
        //jedes Zeichen durchlaufen
        for (char zeichen :
                chiffrat.toCharArray()) {
            //pruefen, ob das gelesene Zeichen ein Grossbuchstabe ist
            if (zeichen >= 65 && zeichen <= 90) {
                text.append(permutationschiffre.get(zeichen));

            } else {
                //Nicht Buchstaben einfach kopieren
                text.append(zeichen);
            }
        }

        return text.toString();
    }

    public static ArrayList<ArrayList<Character>> koinzidenzindex(String chiffrat, int keyLaenge) {
        //doppelte ArrayList fuer unterschiedliche keylaengen
        ArrayList<ArrayList<Character>> koinzidenzArray = new ArrayList<>();

        //keylaenge mal Liste in der Liste einfuegen, umd die Zeichen zuzuordnen
        for (int i = 0; i < keyLaenge; i++) {
            koinzidenzArray.add(new ArrayList<Character>());
        }

        //durch das Chiffrat gehen und die Buchstaben den einzelnen Listen zuordnen
        for (int i = 0; i < chiffrat.length(); i++) {
            if (chiffrat.charAt(i) >= 65 && chiffrat.charAt(i) <= 90) {
                koinzidenzArray.get(i % keyLaenge).add(chiffrat.charAt(i));
            }
        }

        /*
        //Liste erstellen wo die einzelnen Koinzidenzwerte der jeweiligen Listen gespeichert werden sollen
        ArrayList<Double> IcArray = new ArrayList<>();
        for (ArrayList<Character> liste : koinzidenzArray) {
            int summe = 0;
            //Loop ueber das Alphabet und Haeufigkeit jedes Buchstaben errechnen
            for (int i = 65; i <= 90; i++) {
                //Methode gibt die Anzahl der Auftretungen des Buchstbaben zurück
                int haeufigkeitBuchstabe = Helper.haeufigkeitCharList(liste, (char) i);
                summe += haeufigkeitBuchstabe * (haeufigkeitBuchstabe - 1);
            }
            IcArray.add((summe) / (liste.size() * (liste.size() - 1d)));
        }
         */
        return koinzidenzArray;
    }

    public static double kasiskiTest(String chiffrat) {

        //HashMap fuer Kombinationen anlegen
        HashMap<String, Integer> kombinationenSubString = new HashMap<>();

        //HashMap fuer Abstaende der Vorkommen
        HashMap<String, Integer> abstaendeVorkommen = new HashMap<>();

        //For-Schleife fuer Schluessellaenge
        //Startwert 8 ein bisschen durch Olli machen probieren
        for (int schluessellaenge = 8; schluessellaenge < chiffrat.length() / 2; schluessellaenge++) {

            //For-Schleife fuer das Chiffrat und dann abgleichen
            for (int chiffratIndex = 0; chiffratIndex < chiffrat.length() - schluessellaenge; chiffratIndex++) {

                String aktuellerSchluessel = chiffrat.substring(chiffratIndex, chiffratIndex + schluessellaenge);

                //pruefen ob es Eintrag in kombinationenSubString schon gibt
                if (kombinationenSubString.containsKey(aktuellerSchluessel)) {

                    //Schluessel aus Hashmap nehmen und Value um 1 inkrementiert neu hinzufügen
                    kombinationenSubString.put(aktuellerSchluessel,
                            kombinationenSubString.get(aktuellerSchluessel) + 1);

                    //Schluessellaenge ist vielfaches der "schluessellaenge
                    abstaendeVorkommen.put(aktuellerSchluessel, schluessellaenge);

                } else {

                    //neuen Eintrag der HashMap hinzufuegen
                    kombinationenSubString.put(aktuellerSchluessel, 1);
                }

            }

        }

        //aus den abstaenden der Vorkommen den Mittelwert berechenn und diesen runden
        //ergibt die Schluessellaenge

        int summe  = 0;
        for (String schluesel : abstaendeVorkommen.keySet()) {
            summe += abstaendeVorkommen.get(schluesel);
        }
        return Helper.round((double) summe / abstaendeVorkommen.size(), 2);

    }

    public static String koinzidenzindexSchluessel(String chiffrat){
        String schluessel = "";
        ArrayList<ArrayList<Character>> koinzidenzindexlisten = koinzidenzindex(chiffrat, 10);

        for(ArrayList<Character> subliste : koinzidenzindexlisten){
            System.out.println(subliste.toString());
        }

        //Schluessel analyse
        for (char buchstabe = 'A'; buchstabe <= 'Z'; buchstabe++){

            //

        }

        return schluessel;
    }

}
