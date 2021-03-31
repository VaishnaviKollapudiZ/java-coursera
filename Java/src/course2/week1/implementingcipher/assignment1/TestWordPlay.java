package course2.week1.implementingcipher.assignment1;

public class TestWordPlay {
    public static void main(String[] args){
        WordPlay wordPlay = new WordPlay('*');
        System.out.println(wordPlay.replaceVowels("Hello World"));

        WordPlay wordPlay1 = new WordPlay('o');
        System.out.println(wordPlay.emphasize("Hello World"));
    }
}
