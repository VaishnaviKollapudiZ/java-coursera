package softwareproblems.week2.findingagene;

import java.util.Scanner;

public class Part2 {

    public String findSimpleGene(String dna, String startCodon, String stopCodon){

        final String noGene = "No gene found";
        if(dna.equals(dna.toLowerCase())){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
            dna = dna.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        Part1 part1 = new Part1();
        if(startIndex == -1){
            return noGene;
        }
        int endIndex = dna.indexOf(stopCodon,startIndex+3);
        if(endIndex == -1){
            return noGene;
        }
        String gene = dna.substring(startIndex,endIndex+3);
        if((endIndex -startIndex)%3==0){
            return gene;
        }
        return noGene;
    }
    public void testSimpleGene(){

        String startCodon = "ATG";
        String stopCodon = "TAA";
        Scanner scan = new Scanner(System.in);
        String dna = scan.nextLine();

        System.out.println("DNA : "+dna+"\nGene = "+findSimpleGene(dna,startCodon,stopCodon)+"\n");

    }
}
