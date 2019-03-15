package de.computernetze.praktikum1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Jonas Männle
 * @version 1.0www
 */

public class Client {

	private final static String ADDRESS = "localhost";
	private final static int PORT = 9999;
	
	private DatagramSocket socket;
	private InetAddress address;
	private byte[] buf;
	private Scanner sc = new Scanner(System.in);
	private boolean running = true;
	
	public static void main(String[] args) 
	{
		Client client = new Client();
		client.run();
	}
	
	private void run()
	{
		System.out.println("Client gestartet...");
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {
			address = InetAddress.getByName(ADDRESS);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		while(running)
		{
			try {
				// Check user input
				System.out.print("Bitte etwas eingeben: ");
				String input = sc.nextLine();
				buf = input.getBytes();
				
				// Send data to Server
				DatagramPacket sentPacket = new DatagramPacket(buf, 0, buf.length, address, PORT);
				socket.send(sentPacket);
				
				// Stop if message = 'Exit'
				if(input.equals("Exit"))
				{
					running = false;
					break;
				}

				// Receive data from Server
				buf = new byte[256];
				DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
				socket.receive(receivedPacket);
				
				// Data output
				String received = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
				System.out.println("" + received + "\n");
				
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
		socket.close();
		System.out.println("Session beendet");
	}
}
