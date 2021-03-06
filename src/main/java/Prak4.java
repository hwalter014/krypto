import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.Security;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Prak4 {

    private static final Integer[] input = {0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d, 0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34};
    private static final Integer[] cipherKey = {0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c};

    private static final byte[] realKey = integerToByte(cipherKey);
    private static final byte[] realInput = integerToByte(input);

    public static void aufgabe1() {

        //Enryption
        try {

            SecretKey secretKey = new SecretKeySpec(realKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(ENCRYPT_MODE, secretKey);

            byte[] result = cipher.doFinal(realInput);

            System.out.println("Die AES Verschlüsselung sieht wie folgt aus: ");
            for (byte blark : result) {
                System.out.printf("%2x ", blark );
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] integerToByte(Integer[] field) {
        byte[] result = new byte[field.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = field[i].byteValue();
        }
        return result;
    }

    public static void aufgabe2() {

        final String chiffratPath = "text/Praktikum04/chiffrat_AES.bin";
        final Integer[] iV = {0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x8a, 0x8b, 0x8c, 0x8d, 0x8e, 0x8f};

        //setzen von Bouncy Castle fuer Security
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            //Array deklarieren fuer PDF Datei
            byte[] klarText = new byte[0];

            //Initialvektor definieren
            byte[] initByteVektor = integerToByte(iV);
            IvParameterSpec initVektor = new IvParameterSpec(initByteVektor);

            //chiffrat einlesen
            byte[] chiffrat = new BufferedInputStream(new FileInputStream(chiffratPath)).readAllBytes();

            //Schluessel definieren
            byte[] schluessel = {0x00, 0x00, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55, 0x55};

            //boolean als Abbruch Kriterium
            boolean quit = false;

            //Brute Force über die ersten beiden Byte
            for (int ersterByte = 0; ersterByte < 256; ersterByte++) {
                for (int zweiterByte = 0; zweiterByte < 256; zweiterByte++) {

                    //Bits schreiben
                    schluessel[0] = Integer.valueOf(ersterByte).byteValue();
                    schluessel[1] = Integer.valueOf(zweiterByte).byteValue();

                    //richtigen Werte setze
                    schluessel[0] = (byte) -101;
                    schluessel[1] = (byte) -26;

                    SecretKeySpec secretKey = new SecretKeySpec(schluessel, "AES");
                    //AES mit CBC mit dem Modus 2
                    Cipher cipher = Cipher.getInstance("AES/CBC/ISO7816-4Padding");

                    //entschluesselung
                    cipher.init(Cipher.DECRYPT_MODE, secretKey, initVektor);

                    try {
                        //entschluesseln
                        klarText = cipher.doFinal(chiffrat);

                        //Vergleich Signatur Hex: 25 50 44 46 (2D) PDF Datei
                        if ((klarText[0] == (Integer.valueOf(0x25)).byteValue()) &&
                                (klarText[1] == (Integer.valueOf(0x50)).byteValue()) &&
                                (klarText[2] == (Integer.valueOf(0x44)).byteValue()) &&
                                (klarText[3] == (Integer.valueOf(0x46)).byteValue()) &&
                                (klarText[4] == (Integer.valueOf(0x2D)).byteValue())) {
                            System.out.println("Es wurde eine Loesung gefunden!");
                            System.out.println("Folgender Key hat zu einer Entschluesselung gefuehrt: ");
                            for(byte key : schluessel){
                                System.out.printf("%x ", key);
                            }
                            quit = true;
                            break;
                        }
                    }catch (BadPaddingException badPaddingException){
                        //do nothing --> continue
                    }
                }
                if (quit) break;
            }

            //Datei schreiben
            FileOutputStream writ = new FileOutputStream("text/Praktikum04/output.pdf");
            writ.write(klarText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
