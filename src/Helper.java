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
            System.out.println("Fehler gefunden: ");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return text.toString();
    }

    public static double round(double number){
        return Math.round(number * 100.0) / 100.0;
    }

}
