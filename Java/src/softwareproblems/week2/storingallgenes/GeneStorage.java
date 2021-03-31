package softwareproblems.week2.storingallgenes;

import softwareproblems.week2.findingallgene.Part2;

import edu.duke.StorageResource;

public class GeneStorage {


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


    public StorageResource getAllGenes(String dna){

        StorageResource storageResource = new StorageResource();
        int startIndex = 0;

        while(true){
            String currentGene = findGene(dna,startIndex);

            if(currentGene.isEmpty()) break;
            storageResource.add(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();
        }
        return storageResource;
    }

    public void testGetAllGenes(){

        System.out.println("test printAllGenes() -> ");
        String dna = "ATGTAGTAAATGCCCTAAAGTCCCCTGA";
        System.out.println("DNA = "+dna);
        StorageResource storageResource = getAllGenes(dna);
        Iterable<String> gene = storageResource.data();
        System.out.println(gene);
    }

    //Part2

    public double cgRatio(String dna){

        int countCg = 0;
        for(char dnaChar : dna.toCharArray())
            if(dnaChar == 'C' || dnaChar == 'G') countCg++;

        return (float)countCg/dna.length();
    }

    public int countCtg(String dna){

        Part2 part2 = new Part2();
        return part2.howMany(dna,"CTG");
    }
    public void testCountCtg(){
        String dna = "ATGAACTGAGTCTGCTG";
        System.out.println("No. of occurrences in CTG =  "+countCtg(dna));
    }

    public void processGenes(StorageResource storageResource){

        StorageResource geneStorage = new StorageResource();
        StorageResource cgRatioStorage = new StorageResource();
        int countGenes = 0;
        int countCgRatio = 0;
        Iterable<String> dna = storageResource.data();

        for(String dnaString : dna){

            if(cgRatio(dnaString) > 0.35){
                countCgRatio++;
                cgRatioStorage.add(dnaString);
            }
            if(dnaString.length() > 9){
                countGenes++;
                geneStorage.add(dnaString);
            }

        }
        Iterable<String> printGeneStorage = geneStorage.data();
        Iterable<String> printCgRatioStorage = cgRatioStorage.data();
        System.out.println("Number of dna strings having length greater than 9 = "+countGenes+"\nThese dna strings are: "+printGeneStorage);
        System.out.println("Number of dna strings having cgRatio greater than 0.35 = "+countCgRatio+"\nThese dna strings are: "+printCgRatioStorage);
    }
    public void testProcessGenes(){

        StorageResource storageResource = new StorageResource();
        storageResource.add("AGTCCCGGGTAG");
        storageResource.add("AGTTAGA");
        storageResource.add("AGTAGTTTTC");
        storageResource.add("TTAGT");
        storageResource.add("AGTCGGTAG");
        processGenes(storageResource);
    }
}
