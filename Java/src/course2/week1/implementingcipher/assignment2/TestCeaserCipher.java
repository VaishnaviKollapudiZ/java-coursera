package course2.week1.implementingcipher.assignment2;

public class TestCeaserCipher {
    public static void main(String[] args){
        CaesarCipher caesarCipher = new CaesarCipher(23);
        caesarCipher.testCaesarSingleKey();
        CaesarCipher caesarCipher1 = new CaesarCipher(23,17);
        caesarCipher1.testCaesarTwoKeys();
    }
}
