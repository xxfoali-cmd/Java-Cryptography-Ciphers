/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectcs285;

/**
 *
 * @author xxfoa
 */
public class DiffieHellman {

    // This method calculates (base^exp % mod)
    // It is used for public key and shared secret calculations
    public static long power(long base, long exp, long mod) {
        long result = 1;

        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod; // keep value within mod
        }

        return result;
    }

    // This method checks if a number is prime
    // Diffie-Hellman requires p to be a prime number
    public static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // not prime
            }
        }

        return true; // prime number
    }
}
