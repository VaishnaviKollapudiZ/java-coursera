package softwareproblems.week2.findingallgene;

public class Part3 {

    public int findStopCodon(String dna, int startIndex, String stopCodon){


        int currentIndex = dna.indexOf(stopCodon,startIndex+3);

        while(currentIndex != -1){

            int difference = currentIndex - startIndex;
            if(difference%3 == 0)
                return currentIndex;
            currentIndex = dna.indexOf(startIndex,currentIndex+1);
        }

        return currentIndex;
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

    public int countAllGenes(String dna){

        int startIndex = 0;
        int count = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);

            if(currentGene.isEmpty()) break;
            count++;
            startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();
        }
        return count;
    }
    public void testCountAllGenes(){

        String dna = "ATGTAGTAAATGCCCTAAAGTCCCCTGA";
        System.out.println("DNA = "+dna);
        System.out.print("No. of genes = ");
        System.out.println(countAllGenes(dna));
    }

}
