/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

/**
 *
 * @author xxfoa
 */
public class CaesarCipher {

    // Encrypt text using fixed shift of 3
    public static String encrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Check if character is a letter
            if (Character.isLetter(ch)) {

                ch = Character.toUpperCase(ch); // convert to uppercase
                char base = 'A'; // starting point of alphabet

                int x = ch - base; // convert letter to number (0–25)

                int newChar = (x + 3) % 26; // shift by 3

                result += (char) (newChar + base); // convert back to letter

            } else {
                result += ch; // keep spaces and symbols
            }
        }

        return result;
    }

    // Decrypt text by reversing the shift
    public static String decrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {

                ch = Character.toUpperCase(ch);
                char base = 'A';

                int x = ch - base;

                int newChar = (x - 3 + 26) % 26; // reverse shift

                result += (char) (newChar + base);

            } else {
                result += ch;
            }
        }

        return result;
    }
}
