/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

/**
 *
 * @author xxfoa
 */
public class ShiftCipher {

    public static String encrypt(String text, int key) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {

                ch = Character.toUpperCase(ch);
                char base = 'A';

                int x = ch - base;

                int newChar = (x + key) % 26; // shift using key

                result += (char) (newChar + base);

            } else {
                result += ch;
            }
        }

        return result;
    }

    // Decrypt text using same key
    public static String decrypt(String text, int key) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {

                ch = Character.toUpperCase(ch);
                char base = 'A';

                int x = ch - base;

                int newChar = (x - key + 26) % 26; // reverse shift

                result += (char) (newChar + base);

            } else {
                result += ch;
            }
        }

        return result;
    }
}
