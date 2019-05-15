import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientData
{
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private int id;
	
	public ClientData(Socket socket, int id) throws IOException
	{
		this.socket = socket;
		this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.output = new PrintWriter(this.socket.getOutputStream(), true);
		this.id = id;
		
		this.output.println("Connection established. You are client #" + this.id + ".");
	}
	
	public int getID() { return this.id; }
	
	public void closeSocket() throws IOException { this.socket.close(); }
	
	public String listen() throws IOException { return this.input.readLine(); }
	
	public void respond(String s) throws IOException { this.output.println(s); }
}
