package course2.week4;

import edu.duke.*;

import java.io.File;
import java.util.*;

public class VigenereBreaker {


    public HashSet<String> readDictionary(){
        System.out.println("Choose a Dictionary File - ");
            FileResource fileResource = new FileResource();
        HashSet<String> dictionary = new HashSet<>();
        for(String words : fileResource.lines()){
            words = words.toLowerCase();
            dictionary.add(words);
        }
        return dictionary;
    }
    public int countValidWords(String message, HashSet<String> dictionary){

        int wordCount= 0;
        String[] words = message.split("\\W+");
        for(String word:words){
            word = word.toLowerCase();
            if(dictionary.contains(word)) wordCount++;
        }
        return wordCount;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){

        //which decryption gives the largest coutn of real words.
        String decryptionString = null;
        int maxCount = -1;
        for(int kLength=1;kLength<=100;kLength++){
            int[] keys = tryKeyLength(encrypted,kLength,mostCommonCharIn(dictionary));
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String decrypted = vigenereCipher.decrypt(encrypted);
            int currentWords = countValidWords(decrypted,dictionary);
            if(currentWords > maxCount){
                maxCount = currentWords;
                decryptionString = decrypted;
            }
        }
        return decryptionString;
    }
    public String sliceString(String message, int whichSlice, int totalSlices) {

        StringBuilder slicedMessage = new StringBuilder();
        for(int index=whichSlice;index<= message.length() && index+totalSlices <= message.length();index += totalSlices)
            slicedMessage.append(message.charAt(index));

        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int kLength,char mostCommon) {
        int[] key = new int[kLength];
        for(int index=0;index<kLength;index++){
            String slicedString = sliceString(encrypted,index,kLength);
            CaesarCracker caesarCracker = new CaesarCracker();
            int keyPerSlice = caesarCracker.getKey(slicedString);
            key[index] = keyPerSlice;
        }
        return key;
    }
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){

        int countMax = 0;
        String bestLanguage = null;
        String decryptionString = null;
        for(String languageName : languages.keySet()) {
            HashSet<String> dictionary = languages.get(languageName);//dictionary of that language
            String decryptionPerLanguage = breakForLanguage(encrypted, dictionary);
            int currentWords = countValidWords(decryptionPerLanguage, dictionary);

            System.out.println("Dictionary of "+languageName+" read, and encrypted string is decrypted accordingly.");

            if (currentWords > countMax) {
                countMax = currentWords;
                decryptionString = decryptionPerLanguage;
                bestLanguage = languageName;
            }
        }
        System.out.println("The decrypted string is in the best possible language "+bestLanguage.toUpperCase()+"\nDecrypted string is = \n"+decryptionString);
    }



    public char mostCommonCharIn(HashSet<String> dictionary){

        HashMap<Character,Integer> countMap = new HashMap<>();
        char commonChar = 0;
        for(String words : dictionary) {
            for (char letter : words.toCharArray()) {
                if(!countMap.containsKey(letter))
                    countMap.put(letter,1);
                else
                    countMap.put(letter,countMap.get(letter)+1);
            }
        }
        int maxValueInMap=(Collections.max(countMap.values()));
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                commonChar = entry.getKey();
                break;
            }
        }
        return commonChar;

    }

    public void breakVigenere () {


        System.out.println("Select an input file  =");
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();



        //Vigenere Cipher with known keyLength
        int[] keys = tryKeyLength(encrypted,4,'e');
        VigenereCipher vigenereCipher = new VigenereCipher(keys);
        System.out.println(vigenereCipher.decrypt(encrypted));



        //Vigenere Cipher with unknown length, for a particular dictionary selected .
        HashSet<String> dictionary = readDictionary();
        System.out.println("Decrypted String -> \n" + breakForLanguage(encrypted, dictionary));



        //read many dictionaries, and the get the best possible decrypted string in the best possible language
        HashMap<String,HashSet<String>> allDictionaries = new HashMap<>();
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file:directoryResource.selectedFiles()){
            String fileName = file.getName();
            HashSet<String> dictionaryPerLanguage = readDictionary();
            allDictionaries.put(fileName,dictionaryPerLanguage);
            System.out.println("Added dictionary of "+fileName+" successfully\n");
        }
        breakForAllLangs(encrypted,allDictionaries);
    }




}
