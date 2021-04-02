package course4.spelling;

import java.util.HashMap;
import java.util.Set;

class TrieNode {
	private HashMap<Character, TrieNode> children; 
	private String text;  // Maybe omit for space
	private boolean isWord;
	
	public TrieNode()
	{
		children = new HashMap<Character, TrieNode>();
		text = "";
		isWord = false;
	}
	
	public TrieNode(String text)
	{
		this(); //constructor chaining
		this.text = text;
	}
	
	//Return the TrieNode that is the child when you follow the link from the given Character
	public TrieNode getChild(Character c)
	{
		return children.get(c);
	}
	

	public TrieNode insert(Character c)
	{
		if (children.containsKey(c))
			return children.get(c);

		//if c not present
		TrieNode next = new TrieNode(text + c.toString());
		children.put(c, next);
		return next;
	}
	
    public String getText()
	{
		return text;
	}
	
    //Set whether or not this node ends a word in the trie.
	public void setEndsWord(boolean b)
	{
		isWord = b;
	}
	
	//Return whether or not this node ends a word in the trie.
	public boolean endsWord()
	{
		return isWord;
	}
	
	//Return the set of characters that have links from this node
	public Set<Character> getValidNextCharacters()
	{
		return children.keySet();
	}
	public String toString(){
		return getText();
	}

}

