# Java-Cryptography-Ciphers

An educational Java console application demonstrating **Caesar, Shift, Affine, AES, RSA, and Diffie–Hellman key exchange**.

Developed as a **CS285 project**, the application combines seven classes into one `solution.java` file and provides an interactive menu for exploring encryption, decryption, and shared-secret calculations.

## Features

- Interactive console menu
- Caesar Cipher encryption and decryption with a fixed shift of 3
- Shift Cipher encryption and decryption with a custom key
- Affine Cipher encryption and decryption with modular inverse calculation
- AES encryption and decryption
- RSA key-pair generation, encryption, and decryption
- Diffie–Hellman key exchange demonstration
- Base64 encoding for AES and RSA ciphertext
- No external dependencies

## Algorithms

| Algorithm | Purpose | Required Input |
|---|---|---|
| Caesar | Classical substitution cipher | Message |
| Shift | Classical substitution cipher | Message and shift key from 0 to 25 |
| Affine | Classical substitution cipher | Message and keys `a` and `b` |
| AES | Symmetric block encryption | Message and a nonempty string key |
| Diffie–Hellman | Key exchange | Prime `p`, generator `g`, and two private values |
| RSA | Asymmetric encryption | Message; keys are generated automatically |

Diffie–Hellman demonstrates how two participants calculate a shared secret. It does not encrypt or decrypt messages in this application.

## Project Structure

```text
Java-Cryptography-Ciphers/
├── README.md
└── solution.java
```

The source file declares:

```java
package projectcs285;
```

Only `solution` is public. The six helper classes are included in the same file as non-public classes.

## Classes

| Class | Responsibility |
|---|---|
| `solution` | Main method, menu, input handling, and demonstrations |
| `CaesarCipher` | Encryption and decryption using a fixed shift of 3 |
| `ShiftCipher` | Encryption and decryption using a custom shift |
| `AffineCipher` | Affine encryption, decryption, and modular inverse lookup |
| `BlockCipher` | AES encryption, decryption, and key adjustment |
| `DiffieHellman` | Primality checking and modular exponentiation |
| `RSAUtil` | RSA key generation, encryption, and decryption |

## Requirements

- Java Development Kit (JDK)
- `javac` and `java` available in your terminal
- A terminal or Java IDE

The project uses Java's standard libraries and requires no external dependencies.

## Compile and Run

From the folder containing `solution.java`, compile:

```bash
javac -d . solution.java
```

Run:

```bash
java projectcs285.solution
```

The compiler creates the package directory and compiled class files automatically.

Keep the filename `solution.java` because it matches the public class name.

### Running in an IDE

1. Create or open a Java project.
2. Place `solution.java` inside the `projectcs285` source package.
3. Run `projectcs285.solution`.

## Menu

```text
=== Cryptography Menu ===
1. Caesar Cipher
2. Shift Cipher
3. Affine Cipher
4. Block Cipher (AES)
5. Diffie-Hellman
6. RSA
7. Exit
```

Options 1–4 and 6 encrypt the entered message and immediately decrypt the resulting ciphertext.

Option 5 displays both public values and both shared secrets.

Option 7 exits the application.

## Examples

### Caesar Cipher

Uses a fixed shift of `3`.

```text
Plaintext:         HELLO
Ciphertext:        KHOOR
Recovered message: HELLO
```

### Shift Cipher

Uses a shift key selected by the user.

```text
Plaintext:         HELLO
Key:               5
Ciphertext:        MJQQT
Recovered message: HELLO
```

Encryption formula:

```text
E(x) = (x + k) mod 26
```

Decryption formula:

```text
D(x) = (x - k + 26) mod 26
```

### Affine Cipher

Uses two integer keys, `a` and `b`.

```text
Plaintext:         HELLO
a:                 5
b:                 8
Ciphertext:        RCLLA
Recovered message: HELLO
```

Encryption formula:

```text
E(x) = (a × x + b) mod 26
```

Decryption formula:

```text
D(x) = a⁻¹ × (x - b) mod 26
```

Here, `a⁻¹` is the modular inverse of `a` modulo 26.

For predictable results with the current implementation, use `b` from `0` to `25` and one of these values for `a`:

```text
1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25
```

The menu checks that `a` has a modular inverse, but it does not validate the range of `b`.

### AES Block Cipher

Enter a message and a nonempty string key:

```text
Message: Hello World
Key:     SecretKey
```

The application displays Base64 ciphertext and then recovers:

```text
Hello World
```

The implementation adjusts the key to 16 characters:

- Longer keys are truncated.
- Shorter keys are padded with the character `0`.

This adjusts character count rather than byte count. Non-ASCII keys may produce a byte length unsuitable for AES.

### Diffie–Hellman Key Exchange

Example inputs:

```text
Prime p:          23
Generator g:      5
Alice private key: 6
Bob private key:  15
```

Expected results:

```text
Alice public key: 8
Bob public key: 19
Alice secret: 2
Bob secret: 2
Success! Both share the same secret.
```

### RSA

The application generates a new 1024-bit RSA key pair each time option 6 is selected.

It then:

1. Encrypts a short message using the public key.
2. Displays the ciphertext as Base64.
3. Decrypts the ciphertext using the private key.
4. Displays the recovered message.

Keys are not saved between demonstrations.

## Character Handling

The Caesar, Shift, and Affine implementations:

- Convert letters to uppercase.
- Leave non-letter characters unchanged.
- Use arithmetic based on the English alphabet.

Use English letters `A–Z` or `a–z`. Although the code uses `Character.isLetter`, its cipher arithmetic does not correctly support every Unicode alphabet.

Example:

```text
Input:        Hello World!
Caesar output: KHOOR ZRUOG!
```

## Current Limitations

- Numeric prompts expect valid numeric input. Nonnumeric input can terminate the application.
- Affine keys outside the suggested ranges may produce incorrect output because negative remainders and integer overflow are not handled.
- AES and RSA use the platform-default character encoding.
- AES and RSA do not explicitly specify their cipher mode and padding.
- AES and RSA operations return `"Error"` when an exception occurs, without explaining the cause.
- RSA key generation returns `null` on failure, which the menu does not handle.
- RSA encrypts the whole message in one operation, so long messages may exceed the supported input size.
- Diffie–Hellman uses simple loops and `long` arithmetic. Large inputs can be slow or overflow.
- The Diffie–Hellman menu checks that `p` is prime but does not validate the generator or private values.

## Concepts Demonstrated

- Encryption and decryption
- Substitution ciphers
- Modular arithmetic
- Modular multiplicative inverses
- Symmetric encryption
- Asymmetric encryption
- Public and private keys
- Key exchange
- Base64 encoding
- Java Cryptography API
- Console input and menu handling

## Educational Scope

This project was developed for CS285 to explore cryptographic algorithms and their implementation in Java.

It is an educational demonstration, not a production security system. Classical ciphers, the current AES key handling, implicit cipher transformations, RSA configuration, and small-number Diffie–Hellman implementation should not be used to protect sensitive information.
