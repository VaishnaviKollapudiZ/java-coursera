package softwareproblems.week2.findingagene;

import java.util.Scanner;

public class Part3 {
    public boolean twoOccurrences(String mainString, String subString){

        int countMatches = 0;
        int indexOfOccurrence;
        while(mainString.contains(subString)){
            countMatches++;
            indexOfOccurrence = mainString.indexOf(subString);
            mainString = mainString.substring(indexOfOccurrence+1);
        }
        if(countMatches > 1){
            System.out.println(countMatches);
            return true;
        }
        return false;
    }
    public String lastPart(String mainString, String subString){

        //returns the part after the first occurrence of substring
        int index = mainString.indexOf(subString);
        if(index == -1)
            return mainString;
        index += subString.length();
        if(index > mainString.length())
            return "no last part in mainString";
        return mainString.substring(index);
    }

    public void testing(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter main string = ");
        String mainString = scan.nextLine();
        System.out.println("Enter substring to be searched = ");
        String subString = scan.nextLine();

        if(twoOccurrences(mainString,subString))
            System.out.println("TRUE"+"\nPart after the occurrence of the string = " + lastPart(mainString, subString));
        else
            System.out.println("FALSE");
    }
}
