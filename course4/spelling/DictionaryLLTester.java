
package course4.spelling;

import course4.spelling.DictionaryLL;

/**
 * @author UC San Diego MOOC team
 *
 */
public class DictionaryLLTester {

	private String dictFile = "words.small.txt";
	private DictionaryLL smallDict;
	private DictionaryLL largeDict;
	
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception
	{
		smallDict = new DictionaryLL();
		largeDict = new DictionaryLL();
		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		smallDict.printWords();
		//DictionaryLoader.loadDictionary(largeDict, dictFile);

	}


	

}
