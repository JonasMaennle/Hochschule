package export;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client_UDP {

	private final int PORT = 50998;
	private final String address = "192.168.2.6";
	private final int BUFFER_SIZE = 256;
	
	private InetAddress NET_ADDRESS;
	private DatagramSocket socket;
	private boolean running;
	private DatagramPacket packet_send;
	private DatagramPacket packet_received;
	private byte[] buf;
	private Scanner sc;
	
	public static void main(String[] args) 
	{
		new Client_UDP().run();
	}
	
	private void run()
	{
		sc = new Scanner(System.in);
		running = true;
		buf = new byte[BUFFER_SIZE];
		System.out.println("Client gestartet...");
		
		try {
			NET_ADDRESS = InetAddress.getByName(address);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		while(running)
		{
			// create data packet
			String input = getUserInput();
			if(input.equals("Exit"))
			{
				running = false;
				break;
			}
			buf = input.getBytes();
			packet_send = new DatagramPacket(buf, input.length(), NET_ADDRESS, PORT);
			
			// send data packet
			try {
				socket.send(packet_send);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// receive data from server
			buf = new byte[BUFFER_SIZE];
			packet_received = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet_received);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// print data to console
			String output = new String(packet_received.getData(), 0, packet_received.getLength());
			System.out.println(output);
		}
		socket.close();
		sc.close();
		System.out.println("Client beendet");
	}
	
	private String getUserInput()
	{
		String userInput = "";
		
		System.out.print("Bitte einen Text eingeben: ");
		userInput = sc.nextLine();

		return userInput;
	}
}
