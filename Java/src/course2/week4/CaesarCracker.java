package course2.week4;

//Class given by the course, not in clean code orientation

public class CaesarCracker {

    private char mostCommon;
    public CaesarCracker() {
        mostCommon = 'e';
    }

    public int[] countLetters(String message){

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] countFrequency = new int[26];
        for(int k=0; k < message.length(); k++){
            int index = alphabet.indexOf(Character.toLowerCase(message.charAt(k)));
            if (index != -1){
                countFrequency[index] += 1;
            }
        }
        return countFrequency;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){

        int[] freqs = countLetters(encrypted);
        int maxIndex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxIndex - mostCommonPos;
        if (maxIndex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxIndex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaeserCipherRefactored caeserCipherRefactored = new CaeserCipherRefactored(key);
        return caeserCipherRefactored.decrypt(encrypted);
        
    }
   
}
