import java.security.Security;

public class Praktikum {
    public static void main(String[] args) {

        //Aufgabe C1

        //String chiffrat = Helper.readText("chiffrat");

        //Teilaufgabe a)

        //Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat)), "Aufg-1A");

        //Teilaufgabe b)
        //Helper.writeText(Prak1.decryptChiffratC1(chiffrat), "Aufg-1B");

        //Teilaufgabe c)
        //Der Text stammt vom BSI

        //Teilaufgabe d)

        //Der Schlüssel sieht wie folgt aus



        //Aufgabe 2

        //String chiffrat2 = Helper.readText("chiffrat2");

        //Teilaufgabe a)
        //Helper.writeText(Helper.writeRelHaeufigkeit(Prak1.relHaeufigkeitMonogramm(chiffrat2)), "Aufg-2A");

        //Teilaufgabe b)
        //geht in die Richtung Koinzidenzindex
        //Kasiski Test scheint allerdings leichter zu implementieren sein, um die Schluessellaenge zu berechnen
        //System.out.println(Prak1.koinzidenzindex(chiffrat2, 10));

        //Teilaufgabe b) Kasiski-Test
        //System.out.println("Der Schluessel hat: " + Prak1.kasiskiTest(chiffrat2) + " Stellen.");

        //Teilaufgabe c)
        //Berechnung speziellen Koinzidenzindex Mg der Vorlesung
        //Schluessel ist Kaese
        //System.out.println("Der errechnete Schluessel ist: " + Prak1.koinzidenzindexSchluessel(chiffrat2));

        //Teilaufgabe d)
        //entschluesseln Sie den Text
        //Helper.writeText(Prak1.decryptVignere(chiffrat2, Prak1.koinzidenzindexSchluessel(chiffrat2)), "Aufg-2D-fehlerhafterKey");
        //Helper.writeText(Prak1.decryptVignere(chiffrat2, "IXBQHYGJLV"), "Aufg-2D");


        //Teilaufgabe e)
        //Der Text ist der Songtext von Udo Lindenberg Odysee
        //Link: https://www.youtube.com/watch?v=XektEKr0EVM



        //Praktikum 2

        //Aufgabe C1
        //Chiffrat in chiffrat.bin mit einem Schluessel von random.dat
        //bekannter Schluesselstrom
        //unbekannter Plaintext
        //System.out.println("Ausgabe der Loesung fuer die 1. Aufgabe");
        //Prak2.aufgabe1();


        //Aufgabe2
        //kurz auskommentieren
        //Prak2.aufgabe2();

        //Aufgabe2 Ansatz 2
        //System.out.println("\nAusgabe der Loesung fuer die 2. Aufgabe");
        //Prak2.aufgabe2Ansatz2();


        //Praktikum3
        //Aufgabe1 a)
        //Prak3.checkLFSR(0x1FFFFF);

        //Aufgabe1 b)
        //System.out.println(Prak3.maxPeriode());
        //Wiederholung nach 2097151 Iterationen

        //Aufgabe 2
        //Prak3.aufgabe2();

        //Praktikum 4

        //Aufgabe 1
        //Testen einer Implementierung von AES
        //Input: 32 43 f6 a8 88 5a 30 8d 31 31 98 a2 e0 37 07 34
        //Cipher Key: 2b 7e 15 16 28 ae d2 a6 ab f7 15 88 09 cf 4f 3c
        //Prak4.aufgabe1();

        //Aufgabe 2
        //Brute-Force auf einen AES-Key bei dem 16 Bits unbekannt sind
        //CBC Modus verwenden
        //zu entschlüsselne Datei ist vermutlich pdf Datei
        //pdf Datei vermutich Signatur: 25 50 44 46 2D
        //Prak4.aufgabe2();

        //Key der zur Entschluesselung geführt hat: 9b e6 55 55 55 55 55 55 55 55 55 55 55 55 55 55


        //Praktikum 05
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());


        //Aufgabe1

        //Teilaufgabe a)
        //Implementierung 1500 Bit Primzahl egmäß BSI/NIST und Zeit messen
        Prak5.aufgabe1a(5);

        //Teilaufgabe b)
        Prak5.aufgabe1b();
        //Implementierung ist noch nicht richtig und läuft auch nicht

        //Teilaufgabe c)
        //Prüfen ob das RSA System läuft
        //Prak5.aufgabe1c();


        //Aufgabe 2

        //Aufgabe a)
        //Implementierung Chinesischer Restsatz
        //und verifizierung der Implementierungen aus Aufgabe C1
        Prak5.aufgabe2a();


        //Aufgabe b)
        //Performancevergleich mit dem Chinesichen Restsatz
        //Zeitmessung durch Systemzeit
        //inklusie Vorberechnung des chinesischen Restsatzes, welche nicht betrachtet werden
    }
}
