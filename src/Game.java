/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Player.Player;
import SevenWonders.Constants.Agent;
import SevenWonders.SevenWonders;
import Structure.Structure;

/**
 *	<i>Game</i> is the main application of the project. This application allows the 
 * 	user to interface with the application via the use of the <i>Configuration</i> 
 * 	file and the in-game text-based menu. <br><br>
 * 
 *  With this application, the user can analyze each architecture's propensity as
 *  an effective intelligent agent within the game Seven Wonders by measuring the
 *  effectiveness and efficiency of the architecture to operate as an intelligent
 *  agent.
 */
public class Game {

	//////////////////////////////
	// Member Variables
	//////////////////////////////
	
	private static final Logger logger = Logger.getLogger("myLogger");
	
	/**
	 * Seven Wonders game object
	 */
	SevenWonders sevenWonders;
	
	//////////////////////////////
	// Member Functions
	//////////////////////////////
	
	/**
	 * Default Constructor. Initializes log files, system variables, and parses configuration file
	 */
	public Game() {
		
		initializeLogger();
		readConfiguration();
		
		sevenWonders.run();
		
		logger.log(Level.INFO,"[GAME] Program terminating.");
		
	}
	
	/**
	 * Initializes the debug file, output file, and screen output logs
	 */
	private void initializeLogger() {
		
		/**
		 * Initialize console logger output
		 */
		ConsoleHandler consoleHandler = new ConsoleHandler();
		logger.addHandler(consoleHandler);
		consoleHandler.setFormatter(new LogFormatter());
		consoleHandler.setLevel(Level.SEVERE);
		
		/**
		 * Initialize debugfile output
		 */
		FileHandler debugFileHandler = null;
		try {debugFileHandler = new FileHandler("DebugFile.txt");} 
		catch (IOException e) {e.printStackTrace();}
		logger.addHandler(debugFileHandler);
		debugFileHandler.setFormatter(new LogFormatter());
		debugFileHandler.setLevel(Level.ALL);
		
		/**
		 * Initialize logfile output
		 */
		FileHandler logFileHandler = null;
		try {logFileHandler = new FileHandler("Logfile.txt");}
		catch (IOException e) {e.printStackTrace();}
		logger.addHandler(logFileHandler);
		logFileHandler.setFormatter(new LogFormatter());
		logFileHandler.setLevel(Level.INFO);
		
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
			
		/**
		 * Initialize Logfile
		 */
		logger.log(Level.SEVERE, "TITLE:   CMPS 490 - Artificial Intelligence for Modern Board Games: An Intelligent Agent for '7 Wonders'\n");
		logger.log(Level.SEVERE, "DATE:    Winter and Spring 2015\n");
		logger.log(Level.SEVERE, "AUTHOR:  Frank Madrid\n");
		logger.log(Level.SEVERE, "PURPOSE: CMPS 490A and CMPS 490B Senior Seminar Project\n");
		
	}
		
	/**
	 * Reads Configuration.properties file
	 */
	private void readConfiguration() {
			
		Properties  prop  = new Properties();
		InputStream input = null;
		
		try {
			
			logger.log(Level.FINE,"[GAME] Reading 'Configuration.properties'\n");

			input = new FileInputStream("Configuration.properties");
			prop.load(input);
			
		} catch(IOException e) {System.exit(1);}
		
		// Seed
		long seed = Long.parseLong(prop.getProperty("Seed"));
		if(seed == 0) seed = Math.abs(new Random().nextLong());
		
		// Player Count
		int playerCount = Integer.parseInt(prop.getProperty("PlayerCount"));
		if(playerCount < 3 || playerCount > 7) {
			
			logger.log(Level.SEVERE, "[GAME] Critical error. Invalid player count. 3 <= playerCount <= 7\n");
			
			System.exit(1);
			
		}
		
		sevenWonders = new SevenWonders(seed, playerCount);

		logger.log(Level.INFO, "[7WONDERS] Initializing Players.\n");
		
		// Player Information
		for(int i = 0 ; i < playerCount; i++) {
			
			String name = prop.getProperty(String.format("p%dName", i+1));
			String type = prop.getProperty(String.format("p%dType", i+1));
			
			((SevenWonders) sevenWonders).addPlayer(new Player(name, Agent.valueOf(type)));
			
		}
		
		logger.log(Level.INFO, "*****************************************************************\n");
		
	}

	public static void main(String[] args) {  new Game();  }

}