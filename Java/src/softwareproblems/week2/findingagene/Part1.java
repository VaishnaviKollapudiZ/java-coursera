package softwareproblems.week2.findingagene;

import java.util.Scanner;

public class Part1 {

    public String findSimpleGene(String dna){

        final String noGene = "No gene found";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return noGene;
        }
        int endIndex = dna.indexOf("TAA",startIndex+3);
        if(endIndex == -1){
            return noGene;
        }
        String gene = dna.substring(startIndex,endIndex+3);
        if((endIndex -startIndex) %3==0){
            return gene;
        }
        return noGene;
    }

    public void testSimpleGene(){
        Scanner scan = new Scanner(System.in);
        String dna = scan.nextLine();
        String gene = findSimpleGene(dna);
        System.out.println("DNA string = "+dna+"\nGene found = "+gene);
    }
}
