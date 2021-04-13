public class Prak1 {
    public static double[] relHaeufigkeit(String chiffrat){
        double[] verteilung = new double[26];
        double chiffratLaenge = Helper.anzahlBuchstaben(chiffrat);
        for(int i = 0; i < 26; i++){
            int anzahlBuchstabe = 0;
            for (char zeichen :
                    chiffrat.toCharArray()) {
                anzahlBuchstabe += zeichen == (i + 65) ? 1:0;
            }
            double verteilungBuchstabe = Helper.round(anzahlBuchstabe / chiffratLaenge * 100);
            verteilung[i] = verteilungBuchstabe ;
        }
        return verteilung;
    }

    public static String decryptChiffrat(String chiffrat){
        String text = "";


        return text;
    }

}
