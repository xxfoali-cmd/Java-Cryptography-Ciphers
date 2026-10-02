/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 *
 * @author xxfoa
 */
public class BlockCipher {


    // Encrypt text using AES
    public static String encrypt(String text, String key) {
        try {
            key = fixKey(key); // make key 16 characters

            // create AES key object
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

            // create cipher instance
            Cipher cipher = Cipher.getInstance("AES");

            // set mode to encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // encrypt text
            byte[] encrypted = cipher.doFinal(text.getBytes());

            // convert to Base64 string
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            return "Error";
        }
    }

    // Decrypt text using AES
    public static String decrypt(String text, String key) {
        try {
            key = fixKey(key);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");

            // set mode to decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // decode Base64 to bytes
            byte[] decoded = Base64.getDecoder().decode(text);

            // decrypt
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted);

        } catch (Exception e) {
            return "Error";
        }
    }

    // Make sure key is exactly 16 characters
    private static String fixKey(String key) {
        if (key.length() > 16)
            return key.substring(0, 16);

        while (key.length() < 16)
            key += "0";

        return key;
    }
}