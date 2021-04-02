
package course4.spelling;

import java.util.List;


public class AutoCompleteDictionaryTrieTester {

	private AutoCompleteDictionaryTrie smallDict;

	public void setUp() throws Exception
	{
		smallDict = new AutoCompleteDictionaryTrie();
		smallDict.addWord("Hello");
		smallDict.addWord("HELLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("health");
		smallDict.addWord("hook");
		smallDict.addWord("hair");
		smallDict.addWord("a");
		smallDict.addWord("ant");
		smallDict.addWord("apple");
	}


	public void testPredictCompletions()
	{
		//smallDict.printTree();
		List<String> completions;

		completions = smallDict.predictCompletions("h", 5);
		System.out.println(completions);

		completions = smallDict.predictCompletions("",  4);
		System.out.println(completions);


		completions = smallDict.predictCompletions("he", 2);

		boolean allIn = completions.contains("he") &&
				(completions.contains("heir") || completions.contains("hey"));
		System.out.println(allIn);


		completions = smallDict.predictCompletions("hel", 10);
		System.out.println(completions);


		completions = smallDict.predictCompletions("x", 5);
		System.out.println(completions);
	}
	
	
	
	
}
