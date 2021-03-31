package course2.week1.implementingcipher.assignment1;

public class WordPlay {

    private char checkChar;

    public WordPlay(char checkChar){this.checkChar = checkChar;}
    public boolean isVowel(char letter){

        if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o'|| letter =='u'|| letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O'|| letter =='U')
            return true;

        return false;
    }

    public String replaceVowels(String phrase){

        char[] charArray = phrase.toCharArray();
        for (int charPosition = 0; charPosition < charArray.length; charPosition++)
        {
            if (isVowel(charArray[charPosition]))
                charArray[charPosition]=checkChar;
        }
        return String.valueOf(charArray);
    }
    public String emphasize(String phrase){
        char[] charArray = phrase.toCharArray();
        for (int charPosition = 0; charPosition < charArray.length; charPosition++)
        {
            if(charArray[charPosition] == checkChar){
                if(charPosition%2 == 0)
                    charArray[charPosition] = '+';
                else charArray[charPosition] = '*';
            }
        }
        return String.valueOf(charArray);
    }
}
