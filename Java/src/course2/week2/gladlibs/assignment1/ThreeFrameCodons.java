package course2.week2.gladlibs.assignment1;

import edu.duke.FileResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ThreeFrameCodons {

    private HashMap<String,Integer> codonMap;

    public ThreeFrameCodons(){
        codonMap = new HashMap<>();
    }
    public void buildCodonMap(int start,String dna){

        codonMap.clear();
        for(int index=start;index<dna.length() && index+3<=dna.length();index+=3){
            String codon = dna.substring(index,index+3);
            if(!codonMap.containsKey(codon))
                codonMap.put(codon,1);
            else
                codonMap.put(codon,codonMap.get(codon)+1);
        }
    }
    public String getMostCommonCodon(){

        int maxValueInMap=(Collections.max(codonMap.values()));
        for (Map.Entry<String, Integer> entry : codonMap.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void printCodonCounts(int start,int end){

        for (Map.Entry<String, Integer> entry : codonMap.entrySet()) {
            if (entry.getValue()>=start && entry.getValue()<=end) {
                System.out.println(entry.getKey()+" -> "+entry.getValue());
            }
        }
    }
    public void tester(){

        FileResource fileResource = new FileResource();
        String dna = fileResource.asString();

        for(int frameStart=0;frameStart<3;frameStart++){
            buildCodonMap(frameStart,dna);
            String commonCodon = getMostCommonCodon();
            System.out.println("Most common codon in frame "+ frameStart+" is "+commonCodon +" with count = "+codonMap.get(commonCodon));
            printCodonCounts(2,5);
        }
    }
}
