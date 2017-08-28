package com.cooksys.launch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("localhost", 57123);
		
		Scanner input = new Scanner(System.in);
		
		
		while(true)
		{
			System.out.println("Pick Rock, Paper, Scissors");
			String inputString = input.next();
			
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			writer.println(inputString);
			
			writer.flush();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String serverSays = reader.readLine();
			System.out.println("Server said: " + serverSays);
			
			if (serverSays.equals("Server Stopped") || serverSays.contains("Winner"))
			{
				input.close();
				writer.close();
				socket.close();
				return;
			}
		}
	}
	
}
