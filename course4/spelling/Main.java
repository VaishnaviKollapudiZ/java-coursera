package course4.spelling;

public class Main {
    public static void main(String[] args) throws Exception {

        DictionaryLLTester dictionaryLLTester = new DictionaryLLTester();
        dictionaryLLTester.setUp();

        DictionaryBSTTester dictionaryBSTTester = new DictionaryBSTTester();
        dictionaryBSTTester.setUp();

        AutoCompleteDictionaryTrieTester test = new AutoCompleteDictionaryTrieTester();
        test.setUp();
        test.testPredictCompletions();
    }
}
