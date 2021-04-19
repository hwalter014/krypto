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

    public static double round(double number){
        return Math.round(number * 100.0) / 100.0;
    }

    public static char[] getHaeufigkeitGermanLanguage(){
        return new char[]{'E', 'N', 'I', 'S', 'R', 'A', 'T', 'D', 'H', 'U', 'L', 'C', 'G', 'M',
                'O', 'B', 'W', 'F', 'K', 'Z', 'P', 'V', 'J', 'Y', 'X', 'Q'};
    }


    public static int anzahlBuchstaben(String chiffrat){
        int anzahl = 0;
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
