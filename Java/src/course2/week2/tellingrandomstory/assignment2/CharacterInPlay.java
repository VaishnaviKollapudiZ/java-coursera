package course2.week2.tellingrandomstory.assignment2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharacterInPlay {

    private ArrayList<String> names;
    private ArrayList<Integer> counts;

    public CharacterInPlay(){
        names = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void update(String person){

        if(!names.contains(person)){
            names.add(person);
            counts.add(1);
        }
        else{
            int index = names.indexOf(person);
            counts.set(index,counts.get(index)+1);
        }
    }
    public void findAllCharacters(){
        FileResource fileResource = new FileResource();
        Iterable<String> lines = fileResource.lines();

        for(String line : lines){

            if(line.indexOf('.') != -1){
                String word = line.substring(0,line.indexOf('.'));
                update(word);
            }
        }
    }
    public void charactersWithNumParts(int start,int end){
        System.out.println("All speaking parts in this file are = "+names.size());
        for(int index=0;index<names.size();index++){
            if(counts.get(index) >= start && counts.get(index) <= end)
                System.out.println(names.get(index)+" -> "+counts.get(index));
        }
    }
    public void tester(){
        findAllCharacters();
        System.out.println("All speaking parts in this file are = "+names.size());
        for(int index=0;index<names.size();index++){
            if(counts.get(index) >0)
                System.out.println(names.get(index)+" -> "+counts.get(index));
        }
        charactersWithNumParts(2,5);
    }
}
