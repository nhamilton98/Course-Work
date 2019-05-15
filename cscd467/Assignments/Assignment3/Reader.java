import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader implements Runnable
{
	private BufferedReader reader;
	private SharedQueue queue;
	
	public Reader(String filename, SharedQueue queue)
	{
		try { this.reader = new BufferedReader(new FileReader(filename)); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		this.queue = queue;
	}
	
	@Override
	public void run()
	{
		try
		{
			String line = this.reader.readLine();
			while (line != null)
			{
				this.queue.enqueue(line);
				line = this.reader.readLine();
			}
		}
		catch (IOException e) { e.printStackTrace(); }
		this.queue.markEndOfFile();
		try { this.reader.close(); }
		catch (IOException e) { e.printStackTrace(); }
	}
}
