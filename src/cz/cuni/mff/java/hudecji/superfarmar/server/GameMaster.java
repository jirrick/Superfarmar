package superfarmar.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class GameMaster {
	
	private List<Game> games;
	private int lastId;
	
	public GameMaster(){
		games = new ArrayList<Game>();
		lastId = 0;
	}
	
	public void ParseCommand(String command) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		switch (command) {
		// TODO Section Stuff
		case "new" :
			System.out.println("Creating new game: ");
			
			System.out.println("Name of game?");
			String name = in.readLine();
			
			System.out.println("How many players? (possible 2-6)");
			int players = Integer.parseInt(in.readLine());
			while ((players < 2) | (players > 6)){
				System.out.println("You've entered bad value, please enter it again (possible 2-6)");
				players = Integer.parseInt(in.readLine());
			}
			
			System.out.println("On which port? (possible 10000-11000)");
			int port = Integer.parseInt(in.readLine());
			while ((!PortFree(port)) | (port < 10000) | (port > 11000)){
				if (!PortFree(port)) {
					System.out.println("This port is already reserved, chose please another one (possible 10000-11000)");
				} else {
					System.out.println("You've entered bad value, please enter it again (possible 10000-11000)");
				}
				port = Integer.parseInt(in.readLine());
			}
			
			System.out.println("How long to wait for players? (in seconds, possible 0-120)");
			int timelimit = Integer.parseInt(in.readLine());
			while ((timelimit < 0) | (timelimit > 11000)){
				System.out.println("You've entered bad value, please enter it again (possible 0-120)");
				timelimit = Integer.parseInt(in.readLine());
			}
        	
			int id = CreateGame(name,players,port,timelimit);
			
			System.out.println("New game created witj id = " + id);
			
			break;
			
		case "list" : 
			System.out.println("Listing all games: ");
			for (Game g : games) {
				g.DisplayInfo();
			}			
			break;
			
		case "start" : 
			// TODO Start
			System.out.println("Enter id of game to be started: ");
			int start = Integer.parseInt(in.readLine());
			while (!ValidId(start)){
				System.out.println("Invalid game id, please try again.");
				start = Integer.parseInt(in.readLine());
			}
			Start(start);	
			break;
		
		case "stop" : 
			// TODO Stop
			System.out.println("Enter id of game to be stopped: ");
			int stop = Integer.parseInt(in.readLine());
			while (!ValidId(stop)){
				System.out.println("Invalid game id, please try again.");
				stop = Integer.parseInt(in.readLine());
			}
			Stop(stop);
			break;
			
		case "export" : 
			System.out.println("Enter id of game to be exported to XML: ");
			int export = Integer.parseInt(in.readLine());
			while (!ValidId(export)){
				System.out.println("Invalid game id, please try again.");
				stop = Integer.parseInt(in.readLine());
			}
			System.out.println("Enter filename: (without extension)");
			String filename = in.readLine();
			filename += ".xml";

			Export(export, filename);
			break;
			
		case "import" : 
			// TODO Import
			break;
			
		default : System.out.println("Unrecognized command! Type 'help' to display help");
		}
	}
	
	private int CreateGame(String name, int players, int port, int timelimit){
		Game g = new Game(players,port,timelimit,name,++lastId);
		games.add(g);
		return lastId;
	}
	
	private boolean PortFree(int port){
		boolean result = true;
		for (Game g : games) {
			if (g.getPort() == port) {
				result = false;
			}
		}
		return result;
	}
	
	private boolean ValidId(int id){
		boolean result = false;
		for (Game g : games) {
			if (g.getID() == id) {
				result = true;
			}
		}
		return result;
	}
	
	private void Start(int id){
		for (Game g : games) {
			if (g.getID() == id) {
				if (!g.getState()){
					g.Start();
				} else {
					System.out.println("Game "+id+" is already running.");
				}				
			}
		}
	}
	
	private void Stop(int id){
		for (Game g : games) {
			if (g.getID() == id) {
				if (g.getState()){
					g.Stop();
				} else {
					System.out.println("Game "+id+" is already stopped.");
				}				
			}
		}
	}
	
	private void Export(int id, String filename){
		for (Game g : games) {
			if (g.getID() == id) {
				try {
					g.ExportToXML(filename);
				} catch (ParserConfigurationException
						| TransformerFactoryConfigurationError
						| TransformerException e) {
					// TODO XML error
					e.printStackTrace();
				}
			}
		}
	}
}
