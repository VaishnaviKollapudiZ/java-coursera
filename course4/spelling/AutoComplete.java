package course4.spelling;

import java.util.List;

public interface AutoComplete {
	public List<String> predictCompletions(String prefix, int numCompletions);
}
