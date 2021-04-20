import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test2{
    public static String text = "SXQYAYKWPPVAPVMGFRPMMROTTGRUTJVBORSGTMPKIPTQNGKAPOZBGVLLYRNCHRSRSYANYNBROTLTGUTPUZPSRRGRWNEBSTLLYNCQQBSJKCXTZHXXTIRJKVXOLFFDHTOPLOWOFDOYHNYNKEPDSYKWRNBAJUYGIQEPVDWUYJUAPILFFDHANCTNBPDXDYXIOZZKFRLJYXODKEUKUBYLSJVPFYAHGQCZVHFYUJGWODVPJSORJJDDAQEYLMJHDNMBPTFQYNPPVALUPLKAHZQPTMVFOWDDMDFXAMJHDNMB,PTFQYNPRMFMTLPCJSIAFODHKYCPPMOTJLFZJFASRHUSQOLSZZBOAVKSJYYWYSKLAQNYFZXOALYRCPHäVKFHHLKRDZZKFDRPANNFMKTYAXKWLHAMJUSROBNCOFFHPEAWOAMQUMPPYRYYIRGEKWYBPZWAZIZCKDYYSBJDLPCNTNATPXPLYRPBMEUEKWYBPZWAZIZCKFPDTAFHDYNWDDVKBCZRKDPMAQFXASTMVDVAFHZRGACZVSPDKCXAPGQKHQBDJJDVJBOTYMZJXCWOJPVLZDYYAFFXHZKWLIOPULVPJNCZEFHUULGLSOLXTIKGKBZIVBNEYEKWQMCBIDPANCHDMAFHRMSVEJLVTILCUMJNABFKUBQNTIMOXUPQYFZCQKEYLPKRDZOBIJ\r\n";
    public static ConcurrentHashMap<String, Integer> verteilung = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Double> verteilung_prozent = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Double> verteilung_prozent_clone = new ConcurrentHashMap<>();
    public static void main(String args[]){

        int max_zeichen = 0;
        for(String path : text.split("")){
            if((path.charAt(0) > 64 && path.charAt(0) < 91)){ // ASCII
                // range
                // festlegen

                if(!verteilung.keySet().contains(path)){
                    verteilung.put(path, 1);
                }
                else{
                    verteilung.put(path, verteilung.get(path) + 1);
                }
                max_zeichen++;
            }
        }


        for(String key : verteilung.keySet()){
            double amount = round((verteilung.get(key) / ((double) max_zeichen)), 4);
            System.out.println(key + ": " + verteilung.get(key) + " >> " + amount + " %");
            verteilung_prozent.put(key, amount);
            verteilung_prozent_clone.put(key, amount);
        }
        System.out.println("--------------------------");
        System.out.println("Zeichensatz länge: " + max_zeichen);
        System.out.println("--------------------------");
        System.out.println();
        System.out.println("Wiederholungsgruppen mit länge >8");
        System.out.println();
        // KASISKI TEST ZUR BESTIMMUNG DER KEY LENGHT TODO
        HashMap<String, Integer> funde = new HashMap<>();
        HashMap<String, Integer> funde_abstand = new HashMap<>();
        int mindest_wortlaenge = 8;
        for(int i = mindest_wortlaenge; i < 16; i++){
            for(int step = 0; step < text.length() - 14; step++){
                String substring = text.substring(0 + step, i + step);
                if(!funde.containsKey(substring)){
                    funde.put(substring, 1);
                    funde_abstand.put(substring, i);
                }
                else{
                    funde.put(substring, funde.get(substring) + 1);
                }
            }
        }
        int amount = 0;
        double ds = 0;
        for(String key : funde.keySet()){
            if(funde.get(key) > 1){
                System.out.println(key + " -> " + funde.get(key) + " Abstand " + funde_abstand.get(key));
                amount++;
                ds += funde_abstand.get(key);
            }
        }
        ds /= amount;
        int wortlength = (int) round(ds, 0);
        System.out.println("-----------------------------------------------------");
        System.out.println("Durchschnitts Abstand: " + ds + ". Gerundet: " + round(ds, 0));
        System.out.println("Schlüssellänge nach Kasiki: " + wortlength);
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.println("Rekonstruktion des Schlüssels");
        System.out.println("");
        System.out.println("");

        HashMap<Character, Double> buchstabenhaeufigkeit = Helper.getWSKGermanLanguage();



        String text_copy = text.replace("ä", "").replace(",", "");

        String[] subte = text_copy.split("(?<=\\G.{" + wortlength + "})");



        HashMap<Integer, String> subtexte = new HashMap<>();

        System.out.println("Raw Subtexte: (" + wortlength + "er Chiffre Blöcke)");

        for(String subT : subte){ // Nun das Ziel: Alle 1ersten buchstaben in
            // einen subtext, alle 2ten in einen...

            System.out.print(" " + subT);

            int step = 0;
            for(String buchstabe : subT.split("")){
                if(buchstabe.charAt(0) > 64 && buchstabe.charAt(0) < 91){

                    if(!subtexte.containsKey(step))
                        subtexte.put(step, buchstabe);
                    else
                        subtexte.put(step, subtexte.get(step) + "" + buchstabe);
                    step++;
                }
            }
        }


        System.out.println();
        System.out.println("SubTexte:");
        System.out.println();
        for(Integer key : subtexte.keySet()){
            System.out.println("y" + key + " = (" + subtexte.get(key) + ")");
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Jewals der Meist auftretende Buchstabe entspricht dem E (deutsch)");
        System.out.println("Die Verschiebung y0 ist also = (meister Buchstabe) - E (deutsch)");
        System.out.println("-----------------------------------------------------");

        String key = "";
        // Berechnung des Meist gefundenen buchstabens
        int y_counter = 0;
        for(String v : subtexte.values()){
            HashMap<String, Integer> counter = new HashMap<>();
            for(String path : v.split("")){
                if(!counter.containsKey(path)){
                    counter.put(path, 1);
                }
                else{
                    counter.put(path, counter.get(path) + 1);
                }
            }
            // Hashmap mit buchstabenverteilung gefüllt
            // nun suche nach höchsten value

            int max = 0;
            String max_buchstabe = "a";
            for(String eintrag : counter.keySet()){
                if(counter.get(eintrag) >= max){
                    max_buchstabe = eintrag;
                    max = counter.get(eintrag);
                }
            }
            int char_value = (max_buchstabe.charAt(0) % 65) - ('E' % 65);
            if(char_value < 0){
                char_value = 26 + char_value;
            }
            char c = (char) (char_value + 65);
            System.out.println("y" + y_counter + " = Verschiebung um " + max_buchstabe + " - E " + " => " + c);
            key = key + c;
            y_counter++;
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Key ist also: ");
        System.out.print(key);
        System.out.println();
        System.out.println("-----------------------------------------------------");

        key = key.replace("R", "I");

        System.out.println("");
        System.out.println("Änderungen:");
        System.out.println("y0 'V' durch  'M' (R zu I ASCII) ersetzen (fast gleichoft vorhanden)");

        key = key.replace("R", "I");
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Key ist also: ");
        System.out.print(key);
        System.out.println();
        System.out.println("-----------------------------------------------------");



        System.out.println("Versuch, den Text zu entschlüsseln:");

        String entschl = "";
        int step =0;

//		System.out.println(text_copy.charAt(0) + " wird zu " + c);

        for(String buchstabe : text_copy.split("")){
//			if(step < 10) {

            int char_value = (buchstabe.charAt(0) % 65) - (key.charAt(step % 10) % 65);
            if(char_value < 0){
                char_value = 26 + char_value;
            }
            char c = (char) (char_value + 65);
            entschl = entschl + c;
            step++;
//		}
        }
        System.out.println(entschl);

        //VERSUCH 2.



        System.out.println("");
        System.out.println("Änderungen:");
        System.out.println("1. Wort = KAPITAENE ??");
        System.out.println("Demnach: y3 'D' durch  'U' (Z zu Q ASCII) ersetzen ");
        System.out.println("Demnach: y5 'Y' durch  'C' (U zu Y ASCII) ersetzen ");

        key = key.replace("Z", "Q");
        key = key.replace("U", "Y");
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Key ist also: ");
        System.out.print(key);
        System.out.println();
        System.out.println("-----------------------------------------------------");



        System.out.println("Versuch, den Text zu entschlüsseln:");

        entschl = "";
        step =0;

//		System.out.println(text_copy.charAt(0) + " wird zu " + c);

        for(String buchstabe : text_copy.split("")){
//			if(step < 10) {

            int char_value = (buchstabe.charAt(0) % 65) - (key.charAt(step % 10) % 65);
            if(char_value < 0){
                char_value = 26 + char_value;
            }
            char c = (char) (char_value + 65);
            entschl = entschl + c;
            step++;
//		}
        }
        System.out.println(entschl);



    }



    public static double round(double value, int places){
        if(places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
