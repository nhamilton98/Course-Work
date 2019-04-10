import java.io.*;
import java.net.*;
import java.util.*;

public class PingClient
{
	private static final int TIMEOUT = 1000; // Milliseconds
	private static final String CRLF = "\r\n"; // CRLF
	
	private static long minRTT = Long.MAX_VALUE;
	private static long maxRTT = Long.MIN_VALUE;
	private static double avgRTT = 0;
	
	public static void main(String[] args) throws Exception
	{
		if (args.length != 2) { System.out.println("Missing one or more arguments."); }
		else
		{
			// Command line args
			InetAddress server = InetAddress.getByName(args[0]);
			int port = Integer.parseInt(args[1]);
			
			// Create datagram socket for sending and receiving UDP packets
			DatagramSocket socket = new DatagramSocket(port);
			
			// Processing loop where i == sequence number
			for (int i = 0; i < 10; i++)
			{
				// Timestamp for send
				Date now = new Date();
				long sent = now.getTime();
				
				// Create payload
				String payload = "PING " + i + " " + sent + CRLF;
				byte[] bytes = new byte[1024];
				bytes = payload.getBytes();
				
				// Create datagram packet to send
				DatagramPacket ping = new DatagramPacket(bytes, bytes.length, server, port);
				
				// Send the packet to the server
				socket.send(ping);
				
				// Receive a reply or wait for a timeout
				try
				{
					// Set timeout to 1 second
					socket.setSoTimeout(TIMEOUT);
					
					// Create datagram packet to receive
					DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
					
					// Attempt to receive response from the server
					socket.receive(response);
					
					// Timestamp for response
					now = new Date();
					long received = now.getTime();
					
					// Print the response payload
					printData(response, received - sent, i + 1);
				}
				catch (IOException e) { System.out.println("Timeout: " + i); }
			}
		}
	}
	
	private static void printData(DatagramPacket packet, long delay, int count) throws Exception
	{
		// Calculate min, max, and avg RTT
		if (delay < minRTT)
			minRTT = delay;
		if (delay > maxRTT)
			maxRTT = delay;
		avgRTT += (double) delay;
		avgRTT /= (double) count;
		
		// Get byte array of the payload
		byte[] bytes = packet.getData();
		
		// Convert byte array to String
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		InputStreamReader isr = new InputStreamReader(bais);
		BufferedReader br = new BufferedReader(isr);
		String payload = br.readLine();
		
		// Print the payload
		System.out.println("Received from " + packet.getAddress().getHostAddress() + ": " + payload + " Delay: " + delay + " Minimum Delay: " + minRTT + " Maximum Delay: " + maxRTT + " Average Delay: " + avgRTT);
	}
}