import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prak1 {
    public static HashMap<Character, Double> relHaeufigkeit(String chiffrat){
        HashMap<Character, Double> dictHaeufigkeit = new HashMap<>();
        double chiffratLaenge = Helper.anzahlBuchstaben(chiffrat);
        for(int i = 0; i < 26; i++){
            int anzahlBuchstabe = 0;
            for (char zeichen :
                    chiffrat.toCharArray()) {
                anzahlBuchstabe += zeichen == (i + 65) ? 1:0;
            }
            double verteilungBuchstabe = Helper.round(anzahlBuchstabe / chiffratLaenge * 100);
            dictHaeufigkeit.put((char) (i + 65), verteilungBuchstabe);
        }
        return dictHaeufigkeit;
    }

    public static String decryptChiffratC1(String chiffrat){
        //StringBuilder verwenden, damit .append() Methode funktioniert
        StringBuilder text = new StringBuilder();

        //Haeufigkeitsverteilung des Textes chiffrat ermitteln
        HashMap<Character, Double> relHaeufigkeit = relHaeufigkeit(chiffrat);

        //HashMap nach groesse absteigend sortieren
        Map<Character, Double> map = Helper.sortByValue(relHaeufigkeit);

        //neue Hashmap fuer Buchstabe auf Buchstabe erstellen
        HashMap<Character, Character> permutationschiffre = new HashMap<>();


        //Buchstaben nach Haeufigkeit der deutschen Sprache
        char[] buchstaben = Helper.getHaeufigkeitGermanLanguage();

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
            if (zeichen >= 65 && zeichen <= 90){
                text.append(permutationschiffre.get(zeichen));

            }else{
                //Nicht Buchstaben einfach kopieren
                text.append(zeichen);
            }
        }

        return text.toString();
    }

    public static double koinzidenzindex(String chiffrat, int keyLaenge){
        ArrayList<ArrayList<Character>> koinzidenzArray = new ArrayList<>();

        for(int i = 0; i < keyLaenge; i++){
            koinzidenzArray.add(new ArrayList<Character>());
        }

        for(int i = 0; i < chiffrat.length(); i++){
            if (chiffrat.charAt(i) >= 65 && chiffrat.charAt(i) <= 90){
                koinzidenzArray.get(i % keyLaenge).add(chiffrat.charAt(i));
            }
        }
        ArrayList<Double> IcArray = new ArrayList<>();
        for (ArrayList<Character> liste:koinzidenzArray ) {
            int summe = 0;
            for(int i = 65; i <= 90; i++){
                summe += Helper.haeufigkeitCharList(liste, (char) i);
            }
            IcArray.add((summe * (summe - 1d))/ (liste.size() * (liste.size() - 1d)));
        }
        return Helper.mittelwertList(IcArray);
    }

}
