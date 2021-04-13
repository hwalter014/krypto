import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helper {
    public static String readText(String filename){
        StringBuilder text = new StringBuilder();
        try{
            File myFile = new File("text\\"+ filename + ".txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()){
                text.append(scanner.nextLine());
            }
        }catch (FileNotFoundException e){
            System.out.println("Error found: ");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return text.toString();
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

}
