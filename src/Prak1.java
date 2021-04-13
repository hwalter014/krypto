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
        StringBuilder text = new StringBuilder();
        HashMap<Character, Double> relHaeufigkeit = relHaeufigkeit(chiffrat);
        Map<Character, Double> map = Helper.sortByValue(relHaeufigkeit);
        HashMap<Character, Character> permutationschiffre = new HashMap<>();

        char[] buchstaben = Helper.getHaeufigkeitGermanLanguage();
        int index = 0;

        for (Character key :
                map.keySet()) {
            permutationschiffre.put(key, buchstaben[index++]);

        }

        for (char zeichen :
                chiffrat.toCharArray()) {
            if (zeichen >= 65 && zeichen <= 90){
                text.append(permutationschiffre.get(zeichen));

            }else{
                text.append(zeichen);
            }
        }

        return text.toString();
    }



}
