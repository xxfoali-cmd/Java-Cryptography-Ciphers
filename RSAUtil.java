/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 *
 * @author xxfoa
 */
public class RSAUtil {

    // Generate public and private key pair
    public static KeyPair generateKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(1024); // key size (bits)

            return keyGen.generateKeyPair(); // returns both keys

        } catch (Exception e) {
            return null;
        }
    }

    // Encrypt text using public key
    public static String encrypt(String text, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            // set cipher to encryption mode using public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // convert text to bytes and encrypt
            byte[] encrypted = cipher.doFinal(text.getBytes());

            // convert encrypted bytes to readable Base64 string
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            return "Error";
        }
    }

    // Decrypt text using private key
    public static String decrypt(String text, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");

            // set cipher to decryption mode using private key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // convert Base64 string back to bytes
            byte[] decoded = Base64.getDecoder().decode(text);

            // decrypt the bytes
            byte[] decrypted = cipher.doFinal(decoded);

            // convert bytes back to normal text
            return new String(decrypted);

        } catch (Exception e) {
            return "Error";
        }
    }
}
