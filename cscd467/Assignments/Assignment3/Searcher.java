import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread
{
	private final String PATTERN;
	
	private SharedQueue queue;
	private int count;
	private int matches;
	
	public Searcher(SharedQueue queue, String pattern)
	{
		this.queue = queue;
		this.matches = 0;
		this.count = 0;
		
		this.PATTERN = pattern;
	}
	
	@Override
	public void run()
	{
		while (!this.queue.atEndOfFile())
		{
			String line = this.queue.dequeue();
			this.searchLine(line);
			this.count++;
		}
		while (!this.queue.isEmpty())
		{
			String line = this.queue.dequeue();
			this.searchLine(line);
			this.count++;
		}
	}
	
	private void searchLine(String line)
	{
		Pattern pattern = Pattern.compile(this.PATTERN);
		Matcher matcher = pattern.matcher(line);
		
		while (matcher.find())
			this.matches++;
	}
	
	public int getMatchCount() { return this.matches; }
	
	public int getLineCount() { return this.count; }
}
