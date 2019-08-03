package de.computernetze.praktikum2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Jonas Männle
 * @version 1.0
 */

public class Client_TCP {
	
	private final static int PORT = 55123;
	private static String TARGET_IP = "localhost"; //  91.54.117.149 // 192.168.2.104
	
	private Socket sock;
	private ObjectOutputStream os_stream;
	private ObjectInputStream in_stream;
	
	private boolean running = true;
	private long t1,t2;

	public static void main(String[] args) {
		Client_TCP client = new Client_TCP();
		client.run();
	}
	
	// setup connection
	private void run(){
		
		System.out.println("Client gestartet...");
		setupConnection(TARGET_IP, PORT);
		
		Thread readerThread = new Thread(new MessageReceiver());
		readerThread.start();
		
		while(running){
			// send automated gamestates
			t1 = System.currentTimeMillis();
			if(t1 - t2 > 1000) {
				sendMessage();
				t2 = t1;
			}
		}
	}
	
	// send message to server
	private void sendMessage(){
		try {
			os_stream.writeObject(new TestObject("hans"));
			os_stream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void setupConnection(String ip, int port){
		try {
			sock = new Socket(ip, port); // LOKALE IP des Server PC
			os_stream = new ObjectOutputStream(sock.getOutputStream());
			in_stream = new ObjectInputStream(sock.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("setup complete");
	}
	
	// receive message from server
	public class MessageReceiver implements Runnable{
		String incomingMessage = "hihi";
		public void run() {
			Object o;
			try {
				while((o = in_stream.readObject()) != null && running)
				{
					TestObject test = (TestObject)o;
					System.out.println(test.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}