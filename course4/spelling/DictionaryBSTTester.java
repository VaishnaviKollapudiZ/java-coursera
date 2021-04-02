package course4.spelling;


public class DictionaryBSTTester {

	private String dictFile = "dict.txt";

	private DictionaryBST smallDict;
	private DictionaryBST largeDict;

	public void setUp() throws Exception
	{
		smallDict = new DictionaryBST();
		largeDict = new DictionaryBST();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		
		DictionaryLoader.loadDictionary(largeDict, dictFile);
	}

	

	
	
	
}
