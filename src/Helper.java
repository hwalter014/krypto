import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helper {
    public static String readText(String filename){
        StringBuilder text = new StringBuilder();
        try{
            File myFile = new File("..\\text\\"+ filename + ".txt");
            Scanner scaner = new Scanner(myFile);
            while (scaner.hasNextLine()){
                text.append(scaner.nextLine());
            }
        }catch (FileNotFoundException e){
            System.out.println("Fehler gefunden: ");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return text.toString();
    }

}
