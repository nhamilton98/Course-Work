import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Holder
{
	public Scanner input;
	public ArrayQueue queue;
	public File file;
	public int lineCount;
	
	public Holder(final String filename) throws FileNotFoundException
	{
		this.file = new File(filename);
		this.input = new Scanner(this.file);
		this.queue = new ArrayQueue();
		
		Scanner counter = new Scanner(this.file);
		int count = 0;
		while(counter.hasNextLine())
		{
			count++;
			counter.nextLine();
		}
		
		this.lineCount = count;
		
		counter.close();
	}
	
	public void initialFill() throws FileNotFoundException
	{
		for (int i = 0; i < 10 && this.input.hasNextLine(); i++)
			this.queue.enqueue(Integer.parseInt(this.input.nextLine()));
	}
	
	public void fill() throws FileNotFoundException
	{
		if (this.input.hasNextLine())
			this.queue.enqueue(Integer.parseInt(this.input.nextLine()));
		else if (!this.input.hasNextLine() && this.queue.size() == 0)
			this.queue.enqueue(Integer.MAX_VALUE);
	}
	
	public void closeFile() { this.input.close(); }
}