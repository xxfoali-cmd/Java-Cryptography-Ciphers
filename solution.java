/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectcs285;

import java.util.Scanner;
import java.security.KeyPair;

/**
 *
 * @author xxfoa
 */
public class solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Infinite loop to keep showing the menu
        while (true) {

            // Display menu options
            System.out.println("\n=== Cryptography Menu ===");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. Shift Cipher");
            System.out.println("3. Affine Cipher");
            System.out.println("4. Block Cipher (AES)");
            System.out.println("5. Diffie-Hellman");
            System.out.println("6. RSA");
            System.out.println("7. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                // 🔵 Caesar Cipher
                case 1:
                    System.out.println("--- Caesar Cipher ---");

                    System.out.print("Enter message: ");
                    String text1 = sc.nextLine();

                    System.out.println("[Encrypting...]");
                    String enc1 = CaesarCipher.encrypt(text1);
                    System.out.println("Ciphertext: " + enc1);

                    System.out.println("[Decrypting...]");
                    String dec1 = CaesarCipher.decrypt(enc1);
                    System.out.println("Recovered message: " + dec1);
                    break;

                // 🔵 Shift Cipher
                case 2:
                    System.out.println("--- Shift Cipher ---");

                    System.out.print("Enter message: ");
                    String text2 = sc.nextLine();

                    int key;

                    // Validate key (must be between 0 and 25)
                    while (true) {
                        System.out.print("Enter key (0-25): ");
                        key = sc.nextInt();

                        if (key >= 0 && key <= 25) {
                            break;
                        } else {
                            System.out.println("Error: invalid key. Try again.");
                        }
                    }
                    sc.nextLine(); // clear buffer

                    System.out.println("[Encrypting...]");
                    String enc2 = ShiftCipher.encrypt(text2, key);
                    System.out.println("Ciphertext: " + enc2);

                    System.out.println("[Decrypting...]");
                    String dec2 = ShiftCipher.decrypt(enc2, key);
                    System.out.println("Recovered message: " + dec2);
                    break;

                // 🔵 Affine Cipher
                case 3:
                    System.out.println("--- Affine Cipher ---");

                    System.out.print("Enter message: ");
                    String text3 = sc.nextLine();

                    int a;

                    // Validate 'a' (must have modular inverse)
                    while (true) {
                        System.out.print("Enter key 'a' (must be coprime with 26): ");
                        a = sc.nextInt();

                        if (AffineCipher.findInverse(a) != -1) {
                            break;
                        } else {
                            System.out.println("Error: invalid 'a'. Try again.");
                        }
                    }

                    System.out.print("Enter key 'b': ");
                    int b = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    System.out.println("[Encrypting...]");
                    String enc3 = AffineCipher.encrypt(text3, a, b);
                    System.out.println("Ciphertext: " + enc3);

                    System.out.println("[Decrypting...]");
                    String dec3 = AffineCipher.decrypt(enc3, a, b);
                    System.out.println("Recovered message: " + dec3);
                    break;

                // 🔵 AES (Block Cipher)
                case 4:
                    System.out.println("--- Block Cipher (AES) ---");

                    System.out.print("Enter message: ");
                    String text4 = sc.nextLine();

                    String keyAES;

                    // Validate key (cannot be empty)
                    while (true) {
                        System.out.print("Enter secret key: ");
                        keyAES = sc.nextLine();

                        if (!keyAES.isEmpty()) {
                            break;
                        } else {
                            System.out.println("Error: key cannot be empty.");
                        }
                    }

                    System.out.println("[Encrypting...]");
                    String enc4 = BlockCipher.encrypt(text4, keyAES);
                    System.out.println("Ciphertext: " + enc4);

                    System.out.println("[Decrypting...]");
                    String dec4 = BlockCipher.decrypt(enc4, keyAES);
                    System.out.println("Recovered message: " + dec4);
                    break;

                // 🔵 Diffie-Hellman
                case 5:
                    System.out.println("--- Diffie-Hellman Key Exchange ---");

                    long p;

                    // Validate prime number p
                    while (true) {
                        System.out.print("Enter prime number (p): ");
                        p = sc.nextLong();

                        if (DiffieHellman.isPrime(p)) {
                            break;
                        } else {
                            System.out.println("Error: p must be prime.");
                        }
                    }

                    System.out.print("Enter generator (g): ");
                    long g = sc.nextLong();

                    System.out.print("Enter Alice private key: ");
                    long a5 = sc.nextLong();

                    System.out.print("Enter Bob private key: ");
                    long b5 = sc.nextLong();
                    sc.nextLine(); // clear buffer

                    // Generate public keys
                    long A = DiffieHellman.power(g, a5, p);
                    long B = DiffieHellman.power(g, b5, p);

                    System.out.println("Alice public key: " + A);
                    System.out.println("Bob public key: " + B);

                    // Generate shared secrets
                    long secret1 = DiffieHellman.power(B, a5, p);
                    long secret2 = DiffieHellman.power(A, b5, p);

                    System.out.println("Alice secret: " + secret1);
                    System.out.println("Bob secret: " + secret2);

                    // Verify both secrets are equal
                    if (secret1 == secret2) {
                        System.out.println("Success! Both share the same secret.");
                    } else {
                        System.out.println("Error!");
                    }
                    break;

                // 🔵 RSA
                case 6:
                    System.out.println("--- RSA Encryption/Decryption ---");

                    // Generate public/private keys
                    KeyPair keys = RSAUtil.generateKeys();

                    System.out.print("Enter message: ");
                    String text6 = sc.nextLine();

                    System.out.println("[Encrypting with Public Key...]");
                    String enc6 = RSAUtil.encrypt(text6, keys.getPublic());
                    System.out.println("Ciphertext: " + enc6);

                    System.out.println("[Decrypting with Private Key...]");
                    String dec6 = RSAUtil.decrypt(enc6, keys.getPrivate());
                    System.out.println("Recovered message: " + dec6);
                    break;

                // 🔴 Exit program
                case 7:
                    System.out.println("Goodbye!");
                    return;

                // Handle invalid menu choice
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
