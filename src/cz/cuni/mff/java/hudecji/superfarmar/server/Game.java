package superfarmar.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import superfarmar.xml.XMLDocumentFactory;

public class Game {
	
	private List<DiceGeneric> dices;
	private int players;
	private int port;
	private int connection_timelimit;
	private String name;
	private boolean running;
	private int id;
	private int connected;
	
	public Game(int players, int port, int connection_timelimit, String name, int id){
		this.players = players;
		this.port = port;
		this.connection_timelimit = connection_timelimit;
		this.name = name;
		this.running = false;
		this.id = id;
		this.connected = 0;
		dices = new ArrayList<DiceGeneric>();
		dices.add(new DiceA());
		dices.add(new DiceB());
	}

	public int getPort() {
		return port;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean getState() {
		return running;
	}
	
	public void Start(){
		System.out.println("Game "+id+" started");
		running = true;
	}
	
	public void Stop(){
		System.out.println("Game "+id+" started");
		running = false;
	}

	public void DisplayInfo(){
		System.out.println("----Game id "+id+" --------------------------------");
		System.out.println("	Name: "+name);
		System.out.println("	Port: "+port);
		System.out.println("	Running: "+running);
		System.out.println("	Player slots: "+players);
		System.out.println("	Connected: "+connected);
		System.out.println("	Connection timelimit: "+connection_timelimit);
	}
	
	public void ExportToXML(String filename) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		XMLDocumentFactory xmldf = new XMLDocumentFactory("GameSettings");
		xmldf.AddSimpleElement("name", name);
		xmldf.AddSimpleElement("port", Integer.toString(port));
		xmldf.AddSimpleElement("player_slots", Integer.toString(players));
		xmldf.AddSimpleElement("connection_timeout", Integer.toString(connection_timelimit));
		xmldf.WriteToXMLFile(filename);		
	}
}
