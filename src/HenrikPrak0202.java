import java.util.ArrayList;

public class HenrikPrak0202{

    public static void main(String[] args){
        String guess = "CORONA";
        ArrayList<String> chiffs = new ArrayList<>();
        chiffs.add("JKKKPJHKCODRDHDXBEJM");
        chiffs.add("FYWHXANMDZMTQQJXQBWD");
        chiffs.add("LEJSCWXWVKDVAPWPBXWI");
        ArrayList<String> possibleKeys = new ArrayList<>();

        for(String s1 : chiffs){
            for(int i = 0; i < s1.length(); i++){
                String key = "";
                for(char c : guess.toCharArray()){
                    key = key + "" + getAdditionsPartner(s1.toCharArray()[i], c);
                }
                possibleKeys.add(key);
            }
        }
        System.out.println("Possible Keys:");
        for(String s : possibleKeys){
            System.out.println(s);
        }

        System.out.println();
        System.out.println();
        ArrayList<String> wiederholungen = new ArrayList<>();
        // String totry = chiffs.get(1);
        System.out.println();
        System.out.println();
        System.out.println("KEYs AUF C1 C2 C3");
        System.out.println();
        for(String mayKey : possibleKeys){
            for(int i = 0; i < chiffs.get(0).length() - guess.length(); i++){
                // System.out.println();
                // System.out.println("Try key: " + mayKey);
                String z1 = "";
                String z2 = "";
                String z3 = "";
                for(int s = 0; s < guess.length(); s++){
                    // System.out.print(addiereCharAufChar(mayKey.charAt(s),
                    // totry.charAt(i + s)));
                    z1 = z1 + "" + addiereCharAufChar(mayKey.charAt(s), chiffs.get(0).charAt(i + s));
                    z2 = z2 + "" + addiereCharAufChar(mayKey.charAt(s), chiffs.get(1).charAt(i + s));
                    z3 = z3 + "" + addiereCharAufChar(mayKey.charAt(s), chiffs.get(2).charAt(i + s));
                }
                if(!wiederholungen.contains(z1)){
                    wiederholungen.add("" + z1);
                    wiederholungen.add(", ");
                }
                if(!wiederholungen.contains(z2)){
                    wiederholungen.add("" + z2);
                    wiederholungen.add(", ");
                }
                if(!wiederholungen.contains(z3)){
                    wiederholungen.add("" + z3);
                    wiederholungen.add("#");
                }

                // System.out.println();
                // System.out.print("--------------------------------------------");
            }
        }
        for(String v : wiederholungen) {
            if(v.equals("#"))
                System.out.println(v);
            else
                System.out.print(v);
        }
        ArrayList<String> contain = new ArrayList<>();
        contain.add("HALLO");
        contain.add("KERSTIN");
        contain.add("WETTER");
        contain.add("CORONA");
        contain.add("PANDEMIE");
        contain.add("HEUTE");
        contain.add("IMPFEN");
        contain.add("IMPFUNG");
        contain.add("DEUTSCH");

        System.out.println();
        System.out.println();
        for(String c : contain) {
            if(wiederholungen.contains(c)) {
                System.out.println("TREFFER> " + c);
            }
        }
        System.out.println();
    }

    public static char addiereCharAufChar(char a, char b){
        int aa = a % 65;
        int bb = b % 64;

        int cc = (aa + bb) % 26;

        return (char) (cc + 65);
    }

    // 65 70
    public static char getAdditionsPartner(char first, char result){
        int a = first % 65; // 0
        int r = result % 65;// 5
        int found = 0;
        for(int i = -30; i < 30; i++){
            if(a + i == r){
                found = i;
                break;
            }
        }
        if(found < 0){
            found = 26 + found;
        }
        char c = (char) (found + 65);


        return c;
    }
}

