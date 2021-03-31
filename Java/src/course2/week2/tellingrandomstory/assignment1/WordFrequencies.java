package course2.week2.tellingrandomstory.assignment1;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private  ArrayList<String> myWords;
    private  ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fileResource = new FileResource();
        Iterable<String> words = fileResource.words();

        for(String word : words){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.set(index,1);
            }
            else
                myFreqs.set(index,myFreqs.get(index)+1);
        }
    }

    public int findIndexOfMax(){

        int maxIndex = 0;
        for(int index=1;index<myFreqs.size();index++){
            if(myFreqs.get(maxIndex) < myFreqs.get(index))
                maxIndex = index;
        }
        return maxIndex;
    }
    public void tester(){

        findUnique();
        System.out.println("Unique words in the file selectes = "+myWords.size());
        for(int i=0;i<myWords.size();i++){
            System.out.println(myWords.get(i) + " -> " +myFreqs.get(i));
        }
        System.out.println("The word that occurred the most = "+myWords.get(findIndexOfMax())+" having a frequency = "+myFreqs.get(findIndexOfMax()));
    }


}
