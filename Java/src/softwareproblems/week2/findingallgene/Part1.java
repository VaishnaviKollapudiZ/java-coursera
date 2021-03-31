package softwareproblems.week2.findingallgene;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon){

        int currentIndex = dna.indexOf(stopCodon,startIndex+3);

        while(currentIndex != -1){

            int difference = currentIndex - startIndex;
            if(difference%3 == 0)
                return currentIndex;
            currentIndex = dna.indexOf(startIndex,currentIndex+1);
        }

        return -1;
    }

    public String findGene(String dna,int position){

        int startIndex = dna.indexOf("ATG",position);
        if(startIndex == -1) return "";

        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");

        int minIndex;

        if(taaIndex == -1 ||(tgaIndex != -1 && tgaIndex<taaIndex))
            minIndex = tgaIndex;
        else
            minIndex = taaIndex;

        if(minIndex == -1 || (tagIndex != -1 && tagIndex<minIndex))
            minIndex = tagIndex;

        if(minIndex == -1)
            return "";

        return dna.substring(startIndex,minIndex+3);
    }

    public void testFindGene(){

        System.out.println("test findGene() ->");

        String dna = "ATGCCCGGGAAATAACC";//dna with start codon and one valid stop codon
//        String dna = "CCGTAAA"; //dna with no start codon
//        String dna = "ATGCCC"; //dna with no stop codon
//        String dna = "AATGCCCTAGTGAATGTAA";//dna with start codon and multiple stop codons

        System.out.println("DNA = " +dna);
        String gene = findGene(dna,0);
        System.out.println("GENE FOUND = "+gene);
    }


    public void printAllGenes(String dna){

        int startIndex = 0;

        while(true){
            String currentGene = findGene(dna,startIndex);

            if(currentGene.isEmpty()) break;
            System.out.println("GENE = "+currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();
        }
    }

    public void testPrintAllGenes(){

        System.out.println("test printAllGenes() -> ");
        String dna = "ATGTAGTAAATGCCCTAAAGTCCCCTGA";
        System.out.println("DNA = "+dna);
        printAllGenes(dna);
    }

}
