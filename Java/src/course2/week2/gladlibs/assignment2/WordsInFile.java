package course2.week2.gladlibs.assignment2;

import edu.duke.*;

import java.io.*;
import java.util.*;

public class WordsInFile {
    private HashMap<String, ArrayList<String>> wordsMap;

    public WordsInFile(){this.wordsMap = new HashMap<>();}

    private void addWordsFromFile(FileResource fileResource,String fileName)
    {
        for(String word:fileResource.words()) {

            ArrayList<String> files = new ArrayList<>();
            if (wordsMap.containsKey(word)) {
                if (!wordsMap.get(word).contains(fileName)) {
                    files=wordsMap.get(word);
                    files.add(fileName);
                }
            }
            else
                files.add(fileName);

            wordsMap.put(word,files);
        }
    }

    public void buildWordFileMap()
    {
        DirectoryResource directoryResource =new DirectoryResource();
        Iterable<File> files =directoryResource.selectedFiles();
        for(File file : files)
        {
            FileResource fileResource=new FileResource(file);
            addWordsFromFile(fileResource,file.getName());
        }
    }

    public int maxNumber()
    {
        int maxNumberOfFiles = 0;
        for(Map.Entry<String,ArrayList<String>> entry : wordsMap.entrySet()){
            int currentMax = entry.getValue().size();
            if(currentMax > maxNumberOfFiles)
                maxNumberOfFiles = currentMax;
        }
        return maxNumberOfFiles;
    }

    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> words = new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> entry : wordsMap.entrySet()){
            if(entry.getValue().size() == number)
                words.add(entry.getKey());
        }
        return words;
    }

    public void printFilesIn(String word)
    {
        ArrayList<String> files = wordsMap.get(word);
        for(String file : files)
            System.out.println(file);

    }

    public void tester()
    {
        buildWordFileMap();

        System.out.println("maximum number of files any word appears in = "+maxNumber());

        System.out.println("The word 'cats' appears in the following files = ");
        printFilesIn("cats");

        System.out.println("The words that appear in exactly 3 files = ");
        ArrayList<String> wordsInExactFiles = wordsInNumFiles(3);
        for(String word:wordsInExactFiles)
            System.out.print(word+" ");

    }
}
