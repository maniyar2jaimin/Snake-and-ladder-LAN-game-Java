package org.snake_ladder.game;
import java.net.*;
import java.io.*;
import java.util.*;
class Server extends Thread {
	private ServerSocket server;
	private ArrayList<Game> games;

	public Server() {
		/* 
			Pre-conditions: None
			
			Post-conditions: 
				server is set up on a port. 
				games has been instantiated.
							
		*/
		try {
			server = new ServerSocket(9999);
		}
		catch(IOException e) {}
		games = new ArrayList<Game>();
	}

	public void run() {
		/* 
			Pre-conditions:
				server is set up on a port.
				games has been instantiated
		
			Post-conditions:
				server will be closed and all clients disconnected.
		
			Semantics:
				This method will constantly loop waiting for players to conenct.
				Once a connection is made and the players name is retrieved
				they will be placed into a game.
		*/
		System.out.println("Server Started at port 9999: Server is in listining mode");
		try {
			while(true) {
	
				Socket s = server.accept();
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String name = in.readLine();

				// Create player & add to game
				// Game will set host & colour later.
				Player p = new Player(s, in, out, false, name, null);

				// synchronized??
				if(games.size() > 0) {
					// Find an open game.
					boolean found = false;
					for(Game g : games) {
						if(g.isOpen()) {
							found = true;
							g.addPlayer(p);
							break;
						}
					}


					if(!found) {
						createNewGame(p);
					}
				}
				else {
					createNewGame(p);
				}
			}
		}
		catch(Exception e) {
			exit();
		}
		exit();
	}

	void exit() {
		try {
			server.close();
		}
		catch(Exception e) {}
	}

	void createNewGame(Player p) {
		// Add a new game lobby & add p to the lobby
		Game g = new Game();
		g.addPlayer(p);
		games.add(g);
	}

	public static void main(String [] args) {
		new Server().start();
	}

}