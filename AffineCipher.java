/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

/**
 *
 * @author xxfoa
 */
public class AffineCipher {

    // Encrypt text using formula (a*x + b) mod 26
    public static String encrypt(String text, int a, int b) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                ch = Character.toUpperCase(ch);
                char base = 'A';

                int x = ch - base; // convert letter to number (0-25)

                int newChar = (a * x + b) % 26; // affine formula

                result += (char)(newChar + base); // convert back to letter
            } else {
                result += ch; // keep spaces and symbols
            }
        }

        return result;
    }

    // Decrypt text using inverse of 'a'
    public static String decrypt(String text, int a, int b) {
        String result = "";

        int a_inv = findInverse(a); // find inverse of a

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                ch = Character.toUpperCase(ch);
                char base = 'A';

                int x = ch - base;

                // reverse formula
                int newChar = (a_inv * (x - b + 26)) % 26;

                result += (char)(newChar + base);
            } else {
                result += ch;
            }
        }

        return result;
    }

    // Find modular inverse of a
    public static int findInverse(int a) {
        for (int i = 1; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                return i; // found inverse
            }
        }
        return -1; // invalid a (not coprime)
    }
}