public class Prak1 {
    public static String relHaeufigkeit(String chiffrat){
        String verteilung = "";
        double chiffratLaenge = anzahlBuchstaben(chiffrat);
        for(int i = 0; i < 26; i++){
            int anzahlBuchstabe = 0;
            for (char zeichen :
                    chiffrat.toCharArray()) {
                anzahlBuchstabe += zeichen == (i + 65) ? 1:0;
            }
            double verteilungBuchstabe =Helper.round(anzahlBuchstabe / chiffratLaenge * 100);
            verteilung += (char) (i + 65) + ": " + verteilungBuchstabe + " % \n";
        }
        return verteilung;
    }


    private static int anzahlBuchstaben(String chiffrat){
        int anzahl = 0;
        for (char zeichen :
                chiffrat.toCharArray()) {
            anzahl += 65 <= zeichen && zeichen <= 90 ? 1 : 0;
        }
        return anzahl;
    }
}
