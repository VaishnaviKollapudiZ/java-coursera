package softwareproblems.week2.findingagene;

import edu.duke.*;

public class Part4 {

    public void search(){
        URLResource urlResource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

        Iterable<String> words = urlResource.words();

        for(String word : words){
            String wordLowerCase = word.toLowerCase();

            int index = wordLowerCase.indexOf("youtube.com");
            if(index != -1){
                int start = word.lastIndexOf("\"",index);
                int end = word.indexOf("\"",index+1);
                System.out.println(word.substring(start+1,end));
            }
        }
    }
}
