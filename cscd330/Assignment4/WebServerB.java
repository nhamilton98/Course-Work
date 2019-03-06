// Nathan Hamilton
// CSCD 330-01
// Assignment 4

import java.io.*;
import java.net.*;
import java.util.*;

public class WebServerB
{
	public static void main(String[] args) throws Exception
	{
		// Port to be used
		int port = 6789;
		
		// Listen socket
		@SuppressWarnings("resource")
		ServerSocket listenSocket = new ServerSocket(port);
		
		// Continue to process HTTP service requests until the program is terminated
		while (true)
		{
			Socket connectionSocket = listenSocket.accept();
			
			// Process HttpRequests with a thread
			HttpRequest request = new HttpRequest(connectionSocket);
			Thread thread = new Thread(request);
			thread.start();
		}
	}
}

final class HttpRequest implements Runnable
{
	final static String CRLF = "\r\n";
	Socket socket;
	
	public HttpRequest(Socket socket) throws Exception { this.socket = socket; }
	
	// run() implementation for Runnable interface
	public void run()
	{
		try { processRequest(); }
		catch (Exception e) { System.out.println("Run error: " + e); }
	}
	
	private void processRequest() throws Exception
	{
		// Socket's I/O streams
		InputStream is = socket.getInputStream();
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// Request line
		String request = br.readLine();
		System.out.println("\n" + request);
		
		// Header lines
		String header = "";
		while ((header = br.readLine()).length() != 0) { System.out.println(header); }
		
		StringTokenizer tokens = new StringTokenizer(request);
		tokens.nextToken();
		
		// Isolate the filename from the request and open the file
		String filename = "." + tokens.nextToken();
		FileInputStream fis = null;
		boolean exists = true;
		try { fis = new FileInputStream(filename); }
		catch (Exception e) { exists = false; }
		
		String status = "";
		String contentType = "";
		String body = "";
		
		// Build the response message
		if (exists)
		{
			status = "HTTP/1.0 200 OK" + CRLF;
			contentType = "Content-Type: " + getContentType(filename) + CRLF;
		}
		else
		{
			status = "HTTP/1.0 404 File Not Found" + CRLF;
			contentType = "No File Found" + CRLF;
			body = "<HTML><HEAD><TITLE>Not Found</TITLE></HEAD><BODY>Not Found</BODY></HTML>";
		}
		
		// Write the header lines to the output stream
		os.writeBytes(status);
		os.writeBytes(contentType);
		os.writeBytes(CRLF); // Blank line to indicate the end of headers
		
		// Write the body to the output stream
		if (exists)
		{
			byte[] buffer = new byte[1024];
			int bytes = 0;
			while ((bytes = fis.read(buffer)) != -1) { os.write(buffer, 0, bytes); }
			
			fis.close();
		}
		else
			os.writeBytes(body);
		
		// Close the socket, BufferedReader, and I/O streams
		is.close();
		os.close();
		br.close();
		socket.close();
	}
	
	// Helper method for creating the Content-Type header
	private static String getContentType(String filename)
	{
		if (filename.endsWith(".htm") || filename.endsWith(".html"))
			return "text/html";
		if (filename.endsWith(".gif") || filename.endsWith(".GIF"))
			return "image/gif";
		if (filename.endsWith(".jpeg") || filename.endsWith(".jpg"))
			return "image/jpeg";
		return "application/octet-stream";
	}
}
