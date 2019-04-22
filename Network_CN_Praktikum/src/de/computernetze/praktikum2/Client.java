package de.computernetze.praktikum2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jonas Männle
 * @version 1.0
 */

public class Client {
	
	private final static int PORT = 8888;
	private final static String TARGET_IP = "141.79.51.46";
	
	private Scanner sc;
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock;
	private String userInput;

	public static void main(String[] args) 
	{
		Client client = new Client();
		client.run();
	}
	
	// setup connection
	private void run()
	{
		System.out.println("Client gestartet...");
		sc = new Scanner(System.in);
		setupConnection(TARGET_IP, PORT);
		
		Thread readerThread = new Thread(new MessageReceiver());
		readerThread.start();
		
		while(true)
		{
			userInput = sc.nextLine();
			sendMessage(userInput);
		}
	}
	
	// send message to server
	private void sendMessage(String input)
	{
		try {
			writer.println(input);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void setupConnection(String ip, int port)
	{
		try {
			sock = new Socket(ip, port); // LOKALE IP des Server PC
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.print ("Bitte Nachricht eingeben: ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// receive message from server
	public class MessageReceiver implements Runnable
	{
		String incomingMessage;
		public void run() 
		{
			try {
				while((incomingMessage = reader.readLine()) != null)
				{
					System.out.println("Nachricht von Server: " + incomingMessage + "\n");
					System.out.print ("Bitte Nachricht eingeben: ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
