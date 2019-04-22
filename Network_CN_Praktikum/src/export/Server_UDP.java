package export;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server_UDP {
	
	private final int PORT = 50998;
	private final int BUFFER_SIZE = 256;
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private boolean running;
	private byte[] buf;
	private InetAddress address;
	
	private int minLength = 0;
	private int maxLength = 0;
	private int minWordCount = 0;
	private int maxWordCount = 0;

	public static void main(String[] args) 
	{
		new Server_UDP().run();
	}
	
	private void run()
	{
		running = true;
		System.out.println("Server gestartet...");
		// setup server
		try {
			socket = new DatagramSocket(PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while(running)
		{
			// prepare packet
			buf = new byte[BUFFER_SIZE];
			packet = new DatagramPacket(buf, buf.length);
			
			// receive message from client
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			address = packet.getAddress();
			int port = packet.getPort();
			packet = new DatagramPacket(buf, buf.length, address, port);
			String received = new String(packet.getData(), 0, packet.getLength());
			
			String message = getDataFromMessage(received);
			
			// server output
			System.out.println("Nachricht: " + message + "\t Port: " + port + "\t IP Adresse: " + address);
			
			// create server response to client
			response(address, port, message);
		}
		socket.close();
		System.out.println("Server gestoppt");
	}
	
	private void response(InetAddress address, int port, String received)
	{
		// create server response message
		String returnString = "";

		// length data
		int messageLength = received.length();
		
		if(messageLength < minLength || minLength == 0)
			minLength = messageLength;
		if(messageLength > maxLength)
			maxLength = messageLength;
		
		
		int wordCounter = received.split(" ").length;
		
		if(minWordCount > wordCounter || minWordCount == 0)
			minWordCount = wordCounter;
		
		if(maxWordCount < wordCounter)
			maxWordCount = wordCounter;
		
		
		returnString += received + "\t Len = " + messageLength + " (min = " + minLength + ", max = " + maxLength + ")" + "\t Wortanzahl = " + wordCounter + " (min = " + minWordCount + ", max = " + maxWordCount + ")";

		// buf = new byte[256];
		buf = returnString.getBytes();
		packet = new DatagramPacket(buf, 0, buf.length, address, port);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getDataFromMessage(String input)
	{
		String clearInput = "";
		// build string from byte array
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) != 0)
			{
				clearInput += input.charAt(i);
			}
		}
		return clearInput;
	}
}
