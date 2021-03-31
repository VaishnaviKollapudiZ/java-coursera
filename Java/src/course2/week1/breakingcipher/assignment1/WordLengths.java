package course2.week1.breakingcipher.assignment1;

import edu.duke.FileResource;

import java.util.Arrays;

public class WordLengths {

    public void countWordLengths(FileResource resource,int[] counts){

        Arrays.fill(counts,0);
        int maxLength = counts.length;
        Iterable<String> words = resource.words();

        for(String word : words){
            int wordLength = word.length();
            if(!Character.isLetter(word.charAt(wordLength-1))) wordLength--;
            if(!Character.isLetter(word.charAt(0))) wordLength--;
            if(wordLength >= maxLength)
                wordLength = maxLength-1;

            counts[wordLength]++;
        }
        printWordsOfEachLength(counts);
    }
    public void printWordsOfEachLength(int[] counts){

        for(int wordIndex=0;wordIndex<counts.length;wordIndex++){
            System.out.println("Word Length = "+wordIndex +" -> No.of words = "+counts[wordIndex]);
        }
    }
    public int indexOfMaxWordLength(int[] counts){

        int maxWordPosition = Integer.MIN_VALUE;
        for(int index=0;index<counts.length;index++)
            maxWordPosition = Math.max(maxWordPosition,counts[index]);
        return maxWordPosition;
    }
    public void testCountWordLengths(){
        FileResource fileResource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fileResource,counts);
        System.out.println("Most commom word length  = "+indexOfMaxWordLength(counts));
    }
}
