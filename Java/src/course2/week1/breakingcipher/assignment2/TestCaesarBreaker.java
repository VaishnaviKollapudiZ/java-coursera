package course2.week1.breakingcipher.assignment2;

public class TestCaesarBreaker {

    public static void main(String[] args){

        CaesarBreaker caesarBreaker = new CaesarBreaker(23);
        caesarBreaker.testDecrypt();
        CaesarBreaker caesarBreaker1 = new CaesarBreaker(23,2);
        caesarBreaker1.testDecryptTwoKeys();
        caesarBreaker.testHalfOfString();
    }
}
