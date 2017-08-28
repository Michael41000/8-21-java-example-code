package com.cooksys.launch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ConcurrentLauncher {

	public static void main(String[] args) throws InterruptedException, IOException {

		ServerSocket server = new ServerSocket(57123);

		int i = 1;
		while (true) {
			System.out.println("Server " + i + " start");
			
			Socket client = server.accept();

			Thread clientHandler = new Thread(new ClientHandler(client, i));

			clientHandler.start();
			i++;
			
			
		}

	}

}
