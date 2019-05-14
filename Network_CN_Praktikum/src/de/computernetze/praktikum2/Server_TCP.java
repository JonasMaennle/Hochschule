package de.computernetze.praktikum2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jonas Männle
 * @version 1.0
 */

public class Server_TCP{

	private final static int PORT = 55123;
	
	private ServerSocket serverSock;
	private Socket clientSocket;
	private PrintWriter writer;
	private InputStreamReader inputReader;
	private ArrayList<PrintWriter> clientAusgabeStrom;
	
	public static void main(String[] args) 
	{
		Server_TCP server = new Server_TCP();
		server.run(PORT);
	}
	
	// setup connection
	public void run(int port)
	{
		System.out.println("Server gestartet...");
		clientAusgabeStrom = new ArrayList<PrintWriter>();
		try {
			serverSock = new ServerSocket(port);
			
			while(true)
			{
			// wartet bis sich Client verbindet
			clientSocket = serverSock.accept();
			writer = new PrintWriter(clientSocket.getOutputStream());
			clientAusgabeStrom.add(writer);
			
			Thread t= new Thread(new ClientHandler(clientSocket));
			t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// send message to client
	public void sendMessageBack(String nachricht)
	{
		Iterator<PrintWriter> it = clientAusgabeStrom.iterator();
		String send;
		while(it.hasNext())
		{
			try {
				send = nachricht;
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(send);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class ClientHandler implements Runnable
	{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket)
		{
			try {
				sock = clientSocket;
				inputReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(inputReader);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// receive message from client
		public void run() 
		{
			String nachricht;
			try {
				while((nachricht = reader.readLine()) != null)
				{
					System.out.println("gelesen: " + nachricht);
					
					sendMessageBack(nachricht);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
