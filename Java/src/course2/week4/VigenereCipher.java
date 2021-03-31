package course2.week4;

import java.util.*;

//Class given by the course, not in clean code orientation

public class VigenereCipher {
    CaeserCipherRefactored[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaeserCipherRefactored[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaeserCipherRefactored(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaeserCipherRefactored thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaeserCipherRefactored thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
}
