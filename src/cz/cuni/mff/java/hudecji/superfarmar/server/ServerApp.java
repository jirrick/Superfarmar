package superfarmar.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import superfarmar.resources.ResourceLoader;

public class ServerApp {
	
	/**
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String InputLine = "";     
        System.out.println("Welcome to SuperFarmar Server!\nEnter command (type 'quit' to exit or 'help' to display help): ");
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        
        GameMaster GM = new GameMaster();     
        
        InputLine = in.readLine();
        while (!(InputLine.equals("quit"))){
        	if (InputLine.equals("help")){
            	// Print help
        		BufferedReader reader = new BufferedReader(ResourceLoader.getHelp());
        		String oneHelpLine;
        		while ((oneHelpLine = reader.readLine()) != null) {
        	         System.out.println(oneHelpLine);
        	       } 
            } else {
            	// Parse commands
            	GM.ParseCommand(InputLine);
            }
        	System.out.println("Enter command (type 'quit' to exit or 'help' to display help): ");
        	InputLine = in.readLine();
        }
        
        if (InputLine.equals("quit")){
        	in.close();
        	// TODO Cleanup Stuff
        }
	}
}
