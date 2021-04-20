import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class Helper {
    public static String readText(String filename){
        StringBuilder text = new StringBuilder();
        try{
            File myFile = new File("text\\"+ filename + ".txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()){
                text.append(scanner.nextLine()).append("\n");
            }
        }catch (FileNotFoundException e){
            System.out.println("Error found: ");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return text.toString();
    }

    public static void writeText(String text, String filename){
        try{
            File ausgabeDatei = new File("text\\" + filename + ".txt");
            if(!ausgabeDatei.isFile()){
                ausgabeDatei.createNewFile();
            }
            FileWriter ausgabeDateiSchreiben = new FileWriter("text\\" + filename + ".txt");
            ausgabeDateiSchreiben.write(text);
            ausgabeDateiSchreiben.close();
            System.out.println("Erfolgreich geschrieben: " + ausgabeDatei.getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static double round(double number, int stellen){
        if(stellen < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, stellen);
        number = number * factor;
        long tmp = Math.round(number);
        return (double) tmp / factor;
    }

    public static HashMap<Character, Double> getWSKGermanLanguage(){

        HashMap<Character, Double> buchstabenhaeufigkeit = new HashMap<>();

        buchstabenhaeufigkeit.put('E', 0.1740);
        buchstabenhaeufigkeit.put('N', 0.0978);
        buchstabenhaeufigkeit.put('I', 0.0755);
        buchstabenhaeufigkeit.put('S', 0.0727);
        buchstabenhaeufigkeit.put('R', 0.07);
        buchstabenhaeufigkeit.put('A', 0.0651);
        buchstabenhaeufigkeit.put('T', 0.0615);
        buchstabenhaeufigkeit.put('D', 0.0508);
        buchstabenhaeufigkeit.put('H', 0.0476);
        buchstabenhaeufigkeit.put('U', 0.0435);
        buchstabenhaeufigkeit.put('L', 0.0344);
        buchstabenhaeufigkeit.put('C', 0.0306);
        buchstabenhaeufigkeit.put('G', 0.0301);
        buchstabenhaeufigkeit.put('M', 0.0253);
        buchstabenhaeufigkeit.put('O', 0.0251);
        buchstabenhaeufigkeit.put('B', 0.0189);
        buchstabenhaeufigkeit.put('W', 0.0189);
        buchstabenhaeufigkeit.put('F', 0.0166);
        buchstabenhaeufigkeit.put('K', 0.0121);
        buchstabenhaeufigkeit.put('Z', 0.0113);
        buchstabenhaeufigkeit.put('P', 0.0079);
        buchstabenhaeufigkeit.put('V', 0.0067);
        buchstabenhaeufigkeit.put('ß', 0.0031);
        buchstabenhaeufigkeit.put('J', 0.0027);
        buchstabenhaeufigkeit.put('Y', 0.0004);
        buchstabenhaeufigkeit.put('X', 0.0003);
        buchstabenhaeufigkeit.put('Q', 0.0002);

        return buchstabenhaeufigkeit;
    }

    public static int anzahlBuchstaben(String chiffrat){
        int anzahl = 0;
        //ueber gesammten Text iterieren
        //nur Betrachtung der Buchstaben A-Z
        for (char zeichen :
                chiffrat.toCharArray()) {
            anzahl += 65 <= zeichen && zeichen <= 90 ? 1 : 0;
        }
        return anzahl;
    }

    public static String writeRelHaeufigkeit(HashMap<Character, Double> hashMap){
        String anzeige = "";
        for (Character zeichen :
                hashMap.keySet()) {
            anzeige += zeichen + ": " + hashMap.get(zeichen) + "%\n";

        }
        return anzeige;
    }

    public static int haeufigkeitCharList(ArrayList<Character> text, char pattern){
        int anzahl = 0;
        for (char zeichen :
                text) {
            anzahl += zeichen == pattern ? 1 : 0;
        }
        return anzahl;
    }

    public static int auftretenStringChar(String text, char pattern){
        int anzahl = 0;
        for (char zeichen :
                text.toCharArray()) {
            anzahl += pattern == zeichen ? 1 : 0;
        }
        return anzahl;
    }

    public static double mittelwertList(ArrayList<Double> liste){
        double summe = 0;
        for (double element :
                liste) {
            summe += element;
        }
        return summe / liste.size();
    }

    // function to sort hashmap based on values
    public static HashMap<Character, Double> sortByValue(HashMap<Character, Double> hm){
        // Creating a list from elements of HashMap
        List<Map.Entry<Character, Double> > list
                = new LinkedList<>(
                hm.entrySet());

        // Sorting the list using Collections.sort() method
        // using Comparator
        list.sort(new Comparator<>() {
            public int compare(
                    //fuer absteigende Sortierung 2, 1
                    //fuer aufsteigende Sortierung 1, 2
                    Map.Entry<Character, Double> object2,
                    Map.Entry<Character, Double> object1) {
                return (object1.getValue())
                        .compareTo(object2.getValue());
            }
        });

        // puting the  data from sorted list back to hashmap
        HashMap<Character, Double> result
                = new LinkedHashMap<>();
        for (Map.Entry<Character, Double> me : list) {
            result.put(me.getKey(), me.getValue());
        }

        // returning the sorted HashMap
        return result;
    }

}
