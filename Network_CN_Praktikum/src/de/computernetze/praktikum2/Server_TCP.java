package de.computernetze.praktikum2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jonas Männle
 * @version 1.0
 */

public class Server_TCP{

	private final static int PORT = 55123;
	
	private ServerSocket serverSock;
	private Socket clientSocket;
	private TestObject testee;
	
	public static void main(String[] args) {
		Server_TCP server = new Server_TCP();
		server.run(PORT);
	}
	
	// setup connection
	public void run(int port){
		System.out.println("Server gestartet...");
		try {
			serverSock = new ServerSocket(port);
			
			while(true){
			// wartet bis sich Client verbindet
			clientSocket = serverSock.accept();
			Thread t= new Thread(new ClientHandler(clientSocket));
			t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ClientHandler implements Runnable{
		Socket sock;
		ObjectInputStream in_stream;
		ObjectOutputStream os_stream;
		
		public ClientHandler(Socket clientSocket){
			try {
				sock = clientSocket;
				in_stream = new ObjectInputStream(sock.getInputStream());
				os_stream = new ObjectOutputStream(sock.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// receive message from client
		public void run() {
			Object o;
			try {
				while((o = in_stream.readObject()) != null){
					testee = (TestObject)o;
					System.out.println(testee.getName());
					sendMessageBack(testee);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// send message to client
		private void sendMessageBack(TestObject test){
			try {
				os_stream.writeObject(test);
				os_stream.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}