public class Praktikum {
    public static void main(String[] args) {

        //Aufgabe C1

        String chiffrat = Helper.readText("chiffrat");

        //Teilaufgabe a)

        Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat)), "Aufg-1A");

        //Teilaufgabe b)
        Helper.writeText(Prak1.decryptChiffratC1(chiffrat), "Aufg-1B");

        //Teilaufgabe c)
        //Der Text stammt vom BSI

        //Teilaufgabe d)

        //Der Schlüssel sieht wie folgt aus



        //Aufgabe 2

        String chiffrat2 = Helper.readText("chiffrat2");

        //Teilaufgabe a)
        Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat2)), "Aufg-2A");

        //Teilaufgabe b)
        //geht in die Richtung Koinzidenzindex
        //Kasiski Test scheint allerdings leichter zu implementieren sein, um die Schluessellaenge zu berechnen
        //System.out.println(Prak1.koinzidenzindex(chiffrat2, 10));

        //Teilaufgabe b) Kasiski-Test
        System.out.println("Der Schluessel hat: " + Prak1.kasiskiTest(chiffrat2) + " Stellen.");

        //Teilaufgabe c)
        //Berechnung speziellen Koinzidenzindex Mg der Vorlesung
        System.out.println("Der errechnete Schluessel ist: " + Prak1.koinzidenzindexSchluessel(chiffrat2));

        //Teilaufgabe d)


        //Teilaufgabe e)
        //Der Text ist der Songtext von Udo Lindenberg Odysee
        //Link: https://www.youtube.com/watch?v=XektEKr0EVM

    }
}
