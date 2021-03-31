package course2.week1.implementingcipher.assignment2;

public class CaesarCipher {

    private int key;
    private int key1;
    private int key2;
    public CaesarCipher(int key){
        this.key = key;
    }
    public CaesarCipher(int key1,int key2){
        this.key1  = possibleKey(key1);
        this.key2 = possibleKey(key2);
    }
    public int possibleKey(int key){
        while(key > 26)
            key %= 26;
        return key;
    }
    public String encrypt(String input){

        key = possibleKey(key);
        StringBuilder encrypted = new StringBuilder();
        for(int index=0;index<input.length();index++){
            if(input.charAt(index) == ' ') encrypted.append(' ');
            else if(Character.isUpperCase(input.charAt(index))){
                char encryptChar = (char)(((int)input.charAt(index) + key - 65) % 26 + 65);
                encrypted.append(encryptChar);
            }
            else{
                char encryptChar = (char)(((int)input.charAt(index) + key - 97) % 26 + 97);
                encrypted.append(encryptChar);
            }
        }
        return encrypted.toString();
    }
    public String  encryptTwoKeys(String input){

        key1 = possibleKey(key1);
        key2 = possibleKey(key2);
        int[] keys = {key1,key2};
        StringBuilder encrypted = new StringBuilder();

        for(int index=0;index<input.length();index++){
            if(input.charAt(index) == ' ') encrypted.append(' ');
            else if(Character.isUpperCase(input.charAt(index))){
                char encryptChar = (char)(((int)input.charAt(index) + keys[index%2] - 65) % 26 + 65);
                encrypted.append(encryptChar);
            }
            else{
                char encryptChar = (char)(((int)input.charAt(index) + keys[index%2] - 97) % 26 + 97);
                encrypted.append(encryptChar);
            }
        }
        return encrypted.toString();

    }
    public void testCaesarSingleKey(){
        String message = "First Legion";

        System.out.println("Encryption using single key ->");
        String encrypted = encrypt(message);
        System.out.println("message = "+message+", key is " + key + "\nEncrypted message = " + encrypted);



    }
    public void testCaesarTwoKeys(){
        System.out.println("Encryption using two keys ->");
        String message = "First Legion";
        String encryptedTwoKey = encryptTwoKeys(message);
        System.out.println("message = "+message +", two keys = "+key1+", "+key2+"\nEncrypted message = "+encryptedTwoKey);
    }

}


