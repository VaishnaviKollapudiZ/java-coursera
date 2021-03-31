package softwareproblems.week2.findingallgene;

public class Part2 {


    public int howMany(String mainString, String subString){

        int startIndex = 0;
        int count = 0;
        while(true){
            int currentIndex = mainString.indexOf(subString,startIndex);
            if(currentIndex == -1) break;
            count++;
            startIndex = currentIndex+subString.length();
        }
        return count;
    }

    public void testHowMany(){

//        String mainString = "ATGAACGAATTGAATC";
//        String subString = "GAA";

        String mainString = "AAAA";
        String subString = "AA";
        System.out.println(howMany(mainString,subString));
    }
}
