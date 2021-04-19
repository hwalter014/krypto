public class Praktikum {
    public static void main(String[] args) {

        //Aufgabe C1

        String chiffrat = Helper.readText("chiffrat");

        //Teilaufgabe a)

        Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat)), "Aufg-1A");

        //Teilaufgabe b)
        Helper.writeText(Prak1.decryptChiffratC1(chiffrat), "Aufg-1B");

        //Aufgabe 2

        String chiffrat2 = Helper.readText("chiffrat2");

        //Teilaufgabe a)
        Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat2)), "Aufg-2A");

        //Teilaufgabe b)
        //geht in die Richtung Koinzidenzindex
        //Kasiski Test scheint allerdings leichter zu implementieren sein, um die Schluessellaenge zu berechnen
        System.out.println(Prak1.koinzidenzindex(chiffrat2, 4));

        //Teilaufgabe b) Kasiski-Test
        System.out.println(Prak1.kasiskiTest(chiffrat2));


    }
}
