import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ParallelClient {

	public static void main(String[] args) throws Exception{

		Client[] clients = new Client[500];
		for(int x = 0; x < clients.length; x++)
		{
			clients[x] = new Client();
			clients[x].start();
			
		}
		for(int x = 0; x < clients.length; x++)
		{
			clients[x].join();
			
		}

		System.out.println("Parallel Tests are done!!");
	}

	public static class Client extends Thread
	{		
	  private BufferedReader in;
      private PrintWriter out;
      Socket socket;
      String[] commands = {"asdf", "capitalize me", "I hope this works", "A+ Material here :D", "ADD,4,5", "MUL,6,8", "DIV,50,5", "SUB,53425,2345", "ADD,455,4355", "MUL,6346,84365", "DIV,543650,7655", "SUB,765425,6575"}; 
      
      public void run()
      {
    	  Random random = new Random();
    	  String response;
    	  int messagesToSend = random.nextInt(4);
    	  try{
    		  connectToServer();
    		  String welcome = in.readLine();
    		  System.out.print(welcome + "\n");
    		  if(!welcome.equals("Server busy try again later.")){
				  //System.out.println("Going to send " + messagesToSend + " messages");
	    		  for(int x = 0; x < messagesToSend; x++)
	    		  {
	    			  out.println(commands[random.nextInt(commands.length)]);
	    			  
	    			  try {
	                      response = in.readLine();
	                      if (response == null || response.equals("")) {
	                          System.out.println("client to terminate.");
	                         }
	                } catch (IOException ex) {
	                           response = "Error: " + ex;
	                    System.out.println("" + response + "\n");
	                }
	    			  System.out.println(response);
	    		  }
	    		  out.println(".");
	    		  }
	    	  }catch(IOException e)
    	  {}
    	
    	  
    	  
      }
	
	      public void connectToServer() throws IOException {

	            // Get the server address from a dialog box.
	            String serverAddress = "127.0.0.1";

	            // Make connection and initialize streams
	            socket = new Socket(serverAddress, 9898);
	            in = new BufferedReader(
	                        new InputStreamReader(socket.getInputStream()));
	            out = new PrintWriter(socket.getOutputStream(), true);

	            // Consume the initial welcoming messages from the server
	      }		
		
	}
	
}
