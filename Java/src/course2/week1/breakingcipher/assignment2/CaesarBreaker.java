package course2.week1.breakingcipher.assignment2;
import course2.week1.implementingcipher.assignment2.CaesarCipher;

import java.util.Arrays;

public class CaesarBreaker {

    private int key;
    private int key1;
    private int key2;

    public CaesarBreaker(int key){
        key = possibleKey(key);
        this.key = 26-key;
    }

    public CaesarBreaker(int key1,int key2){
        key1 = possibleKey(key1);
        key2 = possibleKey(key2);
        this.key1 = 26-key1;
        this.key2 = 26-key2;
    }
    public int possibleKey(int key){
        while(key > 26)
            key = key % 26;

        return key;
    }
    public String decrypt(String encrypted){

        CaesarCipher caesarCipher = new CaesarCipher(key);
        return caesarCipher.encrypt(encrypted);
    }
    public String decryptTwoKeys(String encrypted){

        CaesarCipher caesarCipher = new CaesarCipher(key1,key2);
        return caesarCipher.encryptTwoKeys(encrypted);

    }

    public String halfOfString(String string, int startIndex){

        StringBuilder resultString = new StringBuilder();

        for(int index=startIndex;index<string.length();index+=2)
            resultString.append(string.charAt(index));

        return resultString.toString();
    }

    public int[] countLetters(String text){

        int[] frequency = new int[26];
        Arrays.fill(frequency,0);
        for(char checkChar : text.toCharArray())
        {
            if(Character.isLetter(checkChar)) {
                checkChar = Character.toLowerCase(checkChar);
                frequency[(int) (checkChar) - 97]++;
            }
        }

        return frequency;
    }
    public int indexOfMaxFrequentChar(int[] frequency){
        int maxIndex = 0;
        for(int index=1;index<frequency.length;index++){
            if(frequency[maxIndex]<frequency[index])
                maxIndex = index;
        }
        return maxIndex;

    }
    public int getKey(String encrypted) {
        int[] frequencyOfChars = countLetters(encrypted);
        int maxIndex = indexOfMaxFrequentChar(frequencyOfChars);
        int key = (maxIndex + 1) - 5;//5 because 'e' is most frequent character by default (given)
        if (key <= 0) key += 26;
        return key;
    }
    public int[] getTwoKeys(String encrypted){
        int[] keys = new int[2];
        keys[0] = getKey(halfOfString(encrypted,0));
        keys[1] = getKey(halfOfString(encrypted,1));
        return keys;
    }


    public void testDecrypt(){

        String message1 = "Cfopq Ibdflk";
        System.out.println("Decryption using single key ->");
        System.out.println("encrypted message = "+message1+", key is " + key + "\nDecrypted message = " + decrypt(message1));



        System.out.println(getKey("z yrmv cfkj fw vvvvvvvvvvvvvvvvj"));
        int[] keys=getTwoKeys("Top ncmy qkff vi vguv vbg ycpx");
        System.out.println("Key1= "+keys[0]+" Key2= "+keys[1]);
    }
    public void testDecryptTwoKeys(){

        String message2 = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        System.out.println("Decryption using two keys ->");

        System.out.println("encrypted message = "+message2 +", two keys = "+key1+", "+key2+"\nDecrypted message = "+decryptTwoKeys(message2));

    }
    public void testHalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis",0));
        System.out.println(halfOfString("Qbkm Zgis",1));
    }
}
