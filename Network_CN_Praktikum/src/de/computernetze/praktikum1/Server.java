package de.computernetze.praktikum1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author Jonas Männle
 * @version 1.0
 */

public class Server {
	
	private final static int PORT = 9999;

	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] buf;
	private boolean running = true;
	private int receiveCounter = 0;
	private float avgMessageLength = 0;
	
	public static void main(String[] args) 
	{
		Server server = new Server();
		server.run();
	}
	
	private void run()
	{
		System.out.println("Server gestartet...");
		try {
			socket = new DatagramSocket(PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while(running)
		{
			try {
				buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				receiveCounter++;
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String received = new String(packet.getData(), 0, packet.getLength());
				String finStr = "";
				for(int i = 0; i < received.length(); i++)
				{
					if(received.charAt(i) != 0)
					{
						finStr += received.charAt(i);
					}
				}
				System.out.println("Received: " + finStr);
				
				// stop if message = 'Exit'
				if(finStr.equals("Exit"))
				{
					running = false;
					continue;
				}
				
				// Count number of words
				System.out.println(finStr.split(" ").length);
				
				//socket.send(packet);
				createServerResponse(address, port, finStr);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		socket.close();
		System.out.println("\nServer gestoppt!");
	}
	
	private void createServerResponse(InetAddress address, int port, String receivedMessage)
	{
		String returnString = "";
		
		// Analyse received message
		
		// Len
		returnString = "RESPONSE:\tLen: " + receivedMessage.length();
		
		// Upper, Lower
		char[] tempArray = receivedMessage.toCharArray();
		int countLower = 0;
		int countUpper = 0;
		for(int i = 0; i < tempArray.length; i++)
		{
			// Upper
			if(Character.isUpperCase(tempArray[i]))
			{
				countUpper++;
			}
			// Lower
			if(Character.isLowerCase(tempArray[i]))
			{
				countLower++;
			}
		}
		returnString += "\t\tUpper: " + countUpper + "\tLower: " + countLower;
		
		// AvgLen
		avgMessageLength += receivedMessage.length();
		returnString += "\tAvgLen: " + avgMessageLength / receiveCounter;
		
		buf = returnString.getBytes();
		packet = new DatagramPacket(buf, 0, buf.length, address, port);

		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
