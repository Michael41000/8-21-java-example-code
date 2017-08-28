package com.cooksys.launch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ClientHandler implements Runnable {

	Socket client;
	int clientNumber;

	public ClientHandler(Socket client, int clientNumber) {
		this.client = client;
		this.clientNumber = clientNumber;
	}

	@Override
	public void run() {
		try {

			PrintWriter writer = new PrintWriter(client.getOutputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			String clientSays = null;;
			
			while (!(clientSays = reader.readLine()).equals("exit")) {
				
				String returnStatement = null;
				int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
				
				
				
				if (clientSays.equals("Rock"))
				{
					switch (randomNum) {
					case 1:
						returnStatement = "I chose Rock. You chose Rock. Tie.";
						break;
					case 2:
						returnStatement = "I chose Paper. You chose Rock. Loser.";
						break;
					case 3:
						returnStatement = "I chose Scissors. You chose Rock. Winner.";
						break;
					}
				}
				else if (clientSays.equals("Paper"))
				{
					switch (randomNum) {
					case 1:
						returnStatement = "I chose Rock. You chose Paper. Winner.";
						break;
					case 2:
						returnStatement = "I chose Paper. You chose Paper. Tie.";
						break;
					case 3:
						returnStatement = "I chose Scissors. You chose Paper. Loser.";
						break;
					}
				}
				else if (clientSays.equals("Scissors"))
				{
					switch (randomNum) {
					case 1:
						returnStatement = "I chose Rock. You chose Scissors. Loser.";
						break;
					case 2:
						returnStatement = "I chose Paper. You chose Scissors. Winner.";
						break;
					case 3:
						returnStatement = "I chose Scissors. You chose Scissors. Tie.";
						break;
					}
				}
				

				writer.println(returnStatement);
				writer.flush();
			}
			
			writer.println("Server Stopped");
			System.out.println("Server " + clientNumber + " Stopped");
			
			
			writer.close();
			client.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
