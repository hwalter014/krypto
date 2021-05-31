import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Prak4 {

    public static void aufgabe1(){
        Integer[] input     = {0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d, 0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34};
        Integer[] cipherKey = {0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c};

        //Enryption
        try{

            byte[] realKey = new byte[16];
            for(int i  = 0; i < realKey.length ; i++){
                realKey[i] = cipherKey[i].byteValue();
            }

            byte[] realInput = new byte[16];
            for(int i  = 0; i < realInput.length ; i++){
                realInput[i] = input[i].byteValue();
            }

            SecretKey secretKey = new SecretKeySpec(realKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(ENCRYPT_MODE, secretKey);

            byte[] result = cipher.doFinal(realInput);

            System.out.println("Die AES Verschlüsselung sieht wie folgt aus: ");
            for (byte blark: result){
                System.out.print(String.format("%x", blark) + " , ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
