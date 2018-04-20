/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package SevenWonders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import Effect.Effect;
import Player.CustomComparator;
import Player.Player;
import SevenWonders.Constants.Age;
import SevenWonders.Constants.Action;
import SevenWonders.Constants.Agent;
import SevenWonders.Constants.CardColor;
import SevenWonders.Constants.Frequency;
import Structure.Structure;
import Structure.StructureDatabase;
import Wonderboard.Wonderboard;
import Wonderboard.WonderboardDatabase;

/**
 * Object representation of the game <b>Seven Wonders</b>. <b>Seven Wonders</b> is a card drafting game played
 * by three to seven players using three decks of cards. <br><br>
 * 
 * At the start of the game, each player randomly receives a double-sided gameboard called the
 * <i>wonderboard</i>. At the beginning of each of the three rounds, an initial hand of seven cards 
 * or <i>structures</i>. Each side gives an initial resource to the player and offers a different 
 * progression of <i>stages</i>.<br><br> 
 * 
 * Players choose a single card from their hand to place around their wonderboard indicating the 
 * construction of the <i>structure</i>, tucked underneath a wonder stage indicating the 
 * construction of the <i>stage</i>, or are discarded for three gold.
 * 
 * Once each player has chosen a card to play and the card's effects are resolved, each player
 * passes their remaining hand of cards to the next player where play is repeated until one last
 * card remains, which is then discarded.
 * 
 * Between rounds, military conflicts are resolved between adjacent players. Each conflict compares
 * the number of <i>shield</i> icons owned by each player, and awards victories to the player with
 * the most icons.
 * 
 * Once the game is over, the player with the most victory points is deemed the winner.
 */
public class SevenWonders {

	//////////////////////
	// Member Variables //
	//////////////////////

	/**
	 * Logfile
	 */
	private static final Logger logger = Logger.getLogger("myLogger");

	/**
	 * List of discarded structures. A structure is discarded by a player for three gold or if it 
	 * is the last card in a hand.
	 */
	private ArrayList<Structure> discard;

	/**
	 * List of available guilds (purple cards) loaded from the databse. A randomized subset of 
	 * 'playerCount + 2' of these structures are only available during the third round.
	 */
	private final ArrayList<Structure> guildsDatabase;

	/**
	 * Internal identification number. Uniqueness is guaranteed via the use of UUID
	 */
	private final String id;

	/**
	 * Current round's card pool. These structures are randomly assigned to in sets of seven to
	 * each player at the beginning of each round.
	 */
	private ArrayList<Structure> library;

	/**
	 * Number of players within the game. A game can only have three to seven players.
	 */
	private final int playerCount;

	/**
	 * List of players in sequential order. The order of the players dictates the seating order
	 * where players.get(0) is the left neighbor of players.get(1).
	 */
	private ArrayList<Player> players;

	/**
	 * Current round. Dictates the unique set of cards available to theplayers during the round.
	 * The game is considered over at the end of round three.
	 */
	private int round;

	/**
	 * Randomizes the number generator. This seed is defined within the configuration file.
	 */
	private final long seed;

	/**
	 * List of available cards loaded from the database. Different expansions can be enabled in the
	 * configuration file.
	 */
	private final ArrayList<Structure> structureDatabase;

	/**
	 * Current Turn. Each round consists of six turns, where at the beginning of each turn, the
	 * player chooses to play a card from a consecutively smaller hand.
	 */
	private int turn;

	/**
	 * List of available wonderboards loaded from the libraries. Different wonderboards from 
	 * expansions can be enabled within the configuration file.
	 */
	private final ArrayList<Wonderboard> wonderboardDatabase;

	//////////////////////
	// Member Functions //
	//////////////////////

	/**
	 * Default Constructor. Initializes <i>Seven Wonders</i>, loads structure, guilds, and
	 * wondeboard databases.
	 */
	public SevenWonders(long seed, int playerCount) {

		id               = UUID.randomUUID().toString();
		this.seed        = seed;
		this.playerCount = playerCount;

		logger.log(Level.INFO, "*****************************************************************\n");
		logger.log(Level.INFO, "[7WONDERS] Initializing 7 Wonders\n");
		logger.log(Level.INFO, String.format("\tID           : %s\n", id));
		logger.log(Level.INFO, String.format("\tSeed         : %d\n", seed));
		logger.log(Level.INFO, String.format("\tPlayer Count : %d\n", playerCount));
		logger.log(Level.INFO, "*****************************************************************\n");

		structureDatabase   = new StructureDatabase().getStructures();
		guildsDatabase      = new StructureDatabase().getGuilds();
		wonderboardDatabase = new WonderboardDatabase().getWonderboards();

		players = new ArrayList<Player>();
		library = new ArrayList<Structure>();
		discard = new ArrayList<Structure>();

		round = 0;
		turn  = 0;

	}

	/**
	 * Adds a new player to the player list. Catches invalid player sizes.
	 * @param p Player
	 */
	public void addPlayer(Player p) {

		logger.log(Level.INFO, String.format("\tCreating Player [%s] (%s)\n",p.getName(),p.getAgent().toString()));

		players.add(p);
		
		if(players.size() > 7) {
			
			logger.log(Level.SEVERE, String.format("[7WONDERS] Adding Player [%s] to the roster brings player count eight or more.\n",
					p.getName()));
			
			System.exit(1);
			
		}

	}

	/**
	 * Allows the passed player to play the last card as opposed to discarding
	 * @param p
	 */
	private void buildLastCard(Player p) {
		
		logger.log(Level.INFO,String.format("[7WONDERS] Player [%s] Building Last Card.\n", p.getName()));
		p.runStrategy(seed, false, true);
		resolveActions(p);
		
		p.setAction(null);
		p.setStructure(null);
		
		resolveEffects(p);
		
	}
	
	/**
	 * Calculates the endgame scoring
	 */
	private void calculateResults() {
		
		logger.log(Level.INFO, "[7WONDERS] Calculating final scores.\n");
		
		for(Player p : players)
			if((p.getWonderboard().getID() == 5 || p.getWonderboard().getID() == 6) && p.getWonderboard().getLevel() == 3) {
				
				ArrayList<Structure> builtGuilds = new ArrayList<Structure>();
				
				// Get the list of built purple cards from the player's neighbors
				for(Structure s : p.getLeftNeighbor().getBuiltStructures())
					if(s.getColor() == CardColor.PURPLE)
						builtGuilds.add(s);

				// Gives the player the list of builtGuilds to select a card for free
				p.setHand(builtGuilds);
				if(!builtGuilds.isEmpty()) {
					p.runStrategy(seed, true, false);
					resolveActions(p);
					resolveEffects(p);
				}
				
			}
				
		for(Player p : players)
			p.calculateScore();
		
		Collections.sort(players, new CustomComparator());
		
		StringBuilder sb = new StringBuilder();
		
		for(Player p : players)
			sb.append(String.format("[%s] ", p.getName()));
		
		logger.log(Level.INFO, String.format("[7WONDERS] Final Results %s", sb));
		
		for(Player p : players)
			if(p.getAgent() == Agent.RBAI1)
				logger.log(Level.SEVERE, String.format("%s,", players.indexOf(p) + 1));
		
		for(Player p : players)
			if(p.getAgent() == Agent.LEARNER)
				logger.log(Level.SEVERE, String.format("%s,", players.indexOf(p) + 1));
		
		for(Player p : players)
			if(p.getAgent() == Agent.RANDOM)
				logger.log(Level.SEVERE, String.format("%s\n", players.indexOf(p) + 1));
		
	}
	
	/**
	 * Discards the final structure
	 * @param p
	 */
	private void discardStructure(Player p) {
		
		logger.log(Level.INFO,String.format("[7WONDERS] Player [%s] discarding final card [%s].\n", p.getName(), p.getHand().get(0).getName()));
		discard.add(p.getHand().get(0));
		
	}
	
	public ArrayList<Structure> getDatabase() {  return structureDatabase;  }

	public ArrayList<Structure> getGuilds()   {  return guildsDatabase;      }

	public ArrayList<Player>    getPlayers()  {  return players;            }

	/**
	 * Begins the next round of the game, by incrementing roundCounter, resetting turnCounter to 0,
	 * repopulates the library with next round's cards, and refreshes any round-based effects.
	 */
	private void nextRound() {

		logger.log(Level.INFO, "*****************************************************************\n");
		logger.log(Level.INFO,String.format("[7WONDERS] Entering Round [%d]\n", round + 1));

		round++;
		turn = 0;

		refreshLibrary();
		refreshEffects(Frequency.AGE);

	}

	/**
	 * Begins the next turn by passing each player a new hand of cards if it is the first turn of
	 * the round or by passing each player's remaining cards to his left or right neighbor dependent
	 * on the round and the refreshes turn based effects
	 */
	private void nextTurn() {

		logger.log(Level.INFO,String.format("[7WONDERS] Entering Turn [%d]\n", turn + 1));

		turn++;

		// If the first turn of the round, then populate player's hands from the library and break
		if(turn == 1) {

			refreshHands();
			return;

		}

		ArrayList<Structure> tempHand = null;

		// If the first or third round, then each player take's the hand of the player sitting to 
		// his right
		if(round == 1 || round == 3) {

			// Temporarily store the first player's hand
			tempHand = players.get(0).getHand();

			// For player [1, ..., n-1] copy their right neighbors hand
			for(int i = 0; i < players.size() - 1; i++) {

				Player p = players.get(i);
				logger.log(Level.FINE,String.format("[7WONDERS] [%s] taking hand from [%s].\n", p.getName(), p.getRightNeighbor().getName()));
				p.setHand(p.getRightNeighbor().getHand());

			}

			// The last player takes the temporarily stored hand of the first player
			players.get(players.size()-1).setHand(tempHand);

		}

		// Otherwise, the game is in the second round where each player take's the hand of the
		// player sitting to his left
		else {

			// Temporarily store the last player's hand
			tempHand = players.get(players.size()-1).getHand();

			// For player [2, ..., n] copy their left neighbors hand
			for(int i = players.size() - 1; i > 0 ; i--) {

				Player p = players.get(i);
				logger.log(Level.FINE,String.format("[7WONDERS] [%s] taking hand from [%s].\n", p.getName(), p.getLeftNeighbor().getName()));
				p.setHand(p.getLeftNeighbor().getHand());

			}

			// The first player takes the temporarily stored hand of the last player
			players.get(0).setHand(tempHand);

		}

		refreshEffects(Frequency.TURN);

	}

	/**
	 * Refreshes each effect belonging to each player. An effect may be useable once per game, once
	 * per round, or every turn.
	 * @param freq: Refresh rate
	 */
	private void refreshEffects(Frequency f) {

		logger.log(Level.FINE, String.format("[7WONDERS] Refreshing effects at frequency [%s].\n",f.toString()));

		for(Player p : players)
			for(Effect e : p.getEffects())
				e.refreshEffect(f);

	}

	/**
	 * Distributes seven cards to each player from the library.
	 */
	private void refreshHands() {

		logger.log(Level.FINE,"[7WONDERS] Allocating hands\n");

		// If there is not exactly seven cards per player, terminate the program
		if(library.size() != 7 * playerCount) {

			logger.log(Level.SEVERE, "[PLAYER] Critical error. Incorrect card count\n");

			System.exit(1);

		}

		Iterator<Structure> it = library.iterator();

		for(Player p : players) {

			ArrayList<Structure> hand = new ArrayList<Structure>();

			for(int i = 0; i < 7; i++)
				hand.add(it.next());

			p.setHand(hand);

		}

	}

	/**
	 * Repopulates the library dependant on the player count and the round.
	 */
	private void refreshLibrary() {

		logger.log(Level.FINE,String.format("[7WONDERS] Repopulating library with Age [%d] structures\n", round));

		library = new ArrayList<Structure>();

		Age age = null;

		switch(round) {

			case 1 : age = Age.ONE;		break;
			case 2 : age = Age.TWO;		break;
			case 3 : age = Age.THREE;	break;
		
		}
		
		// For each structure in structureDatabse
		for(Structure s : structureDatabase)

			// If the structures age matches the current round and is not purple
			if((s.getAge() == age && s.getColor() != CardColor.PURPLE))

				// For each frequency in the structures frequency list
				for(int frequency : s.getFrequencies()) {

					// If the playerCount is less than or equal to the frequency
					if(playerCount >= frequency) {

						logger.log(Level.FINE,String.format("\tAdding Structure: [%s] to the library.\n", s.getName()));

						library.add(s);

					}

				}

		// If in round three, guild structures are also added
		if(round == 3) {

			Collections.shuffle(guildsDatabase, new Random(seed));
			Iterator<Structure> it = guildsDatabase.iterator();

			// Iterate through the first 'playerCount + 2' guilds
			for(int i = 0; i < playerCount + 2; i++) {

				Structure s = it.next();

				logger.log(Level.FINE,String.format("[7WONDERS] Adding Structure: [%s] to the library.\n", s.getName()));

				library.add(s);

			}

		}

		// Shuffle the compiled library
		Collections.shuffle(library, new Random(seed));

	}

	/**
	 * Resolve each players <i>selectedAction</i> with structure <i>selectedStructure</i> and
	 * removes the <i>selectedStructure</i> from the player's hand
	 * @param p - player
	 */
	private void resolveActions(Player p) {

		logger.log(Level.INFO,String.format("[7WONDERS] Player [%s] is resolving action [%s] with structure [%s].\n",
				p.getName(), p.getAction(), p.getSelectedStructure().getName()));

		switch(p.getAction()) {

		// Sets the stage's <i>built</i> status, and appends the stage's effects to the
		// player's <i>effects</i> list
		case CONSTRUCT_STAGE :

			for(Effect e : p.getWonderboard().getNextStage().getEffects())
				p.getEffects().add(e);
			
			p.getWonderboard().getNextStage().buildStage();
			
			break;

		// Appends the structure to the player's <i>builtStructures</i> list and appends the
		// structure's effects to the player's <i>effects</i> list
		case CONSTRUCT_STRUCTURE :

			p.getBuiltStructures().add(p.getSelectedStructure());

			for(Effect e : p.getSelectedStructure().getEffects()) {
				p.getEffects().add(e);
			}
			break;

		// Adds the discarded card to the <i>discard</i> list, and gives the player three gold
		case DISCARD_CARD :

			discard.add(p.getSelectedStructure());
			p.addGold(3);
			break;

		}

		// Removes the selected structure from the player's hand
		p.getHand().remove(p.getSelectedStructure());

		p.setAction(null);
		p.setStructure(null);

	}

	/**
	 * Determines the results of military conflicts between rounds by comparing the shield count between each player
	 * and his respective neighbors.
	 */
	private void resolveConflicts() {

		logger.log(Level.INFO,"[7WONDERS] Resolving Conflicts.\n");
				
		Age age = null;

		switch(round) {

		case 1 : age = Age.ONE;		break;
		case 2 : age = Age.TWO;		break;
		case 3 : age = Age.THREE;	break;

		}

		for(Player p : players) {

			// Conflict between Player and Left Neighbor
			if(p.getShields() > p.getLeftNeighbor().getShields()) {

				logger.log(Level.INFO,String.format("[7WONDERS] Player [%s] has defetead Player [%s] [%d - %d].\n", 
						p.getName(), p.getLeftNeighbor().getName(), p.getShields(), p.getLeftNeighbor().getShields())); 	
				p.getConflict().addVictory(age);
				p.getLeftNeighbor().getConflict().addDefeat();

			}

			// Conflict between Player and Right Neighbor
			if(p.getShields() > p.getRightNeighbor().getShields()) {

				logger.log(Level.INFO,String.format("[7WONDERS] Player [%s] has defetead Player [%s] [%d - %d].\n", 
						p.getName(), p.getRightNeighbor().getName(), p.getShields(), p.getRightNeighbor().getShields())); 	
				p.getConflict().addVictory(age);
				p.getRightNeighbor().getConflict().addDefeat();

			}

		}

	}

	/**
	 * Resolves all effects granted to the player by built stages and built structures
	 * @param p - Player
	 */
	private void resolveEffects(Player p) {

		for(Effect e : p.getEffects()) {
			
			if(e.isUsed())
				continue;
			
			logger.log(Level.FINE,String.format("[7WONDERS] Player [%s] is resolving effect [%s] from structure.\n",
					p.getName(), e.getEffectType().toString()));

			ArrayList<Structure> tempHand = new ArrayList<Structure>();
			
			switch(e.getEffectType()) {

				// Allows the player to play a card from the discard pile
				case BUILD_DISCARDED_CARD : 
	
					e.triggerEffect();
					
					if(!discard.isEmpty()) {
						
						// Temporarily stores the player hand
						tempHand = p.getHand();
		
						// Gives the player the discard pile to select a card for free
						p.setHand(discard);
						p.runStrategy(seed, true, true);
		
						resolveActions(p);
						p.setHand(tempHand);
					}
					
					return;
	
				// Allows the player copy a purple card from their neighbor
				case COPY_GUILD : 
					break;
				case VICTORY_POINT_BONUS :
					break;
					
				default :
					
					e.useEffect(p);
	
			}
			
		}		

	}

	/**
	 * Main processing
	 */
	public void run() {

		setNeighbors();
		setWonderboards();

		while(round < 3) {

			nextRound();
			
			while(turn < 6) {

				nextTurn();

				for(Player p : players) 
					p.runStrategy(seed, false, true);

				for(Player p : players) {
					
					if(p.getAction() == Action.CONSTRUCT_STRUCTURE)
						p.payGold(p.getSelectedStructure().getResourceCost());
				
					if(p.getAction() == Action.CONSTRUCT_STAGE)
						p.payGold(p.getWonderboard().getNextStage().getResourceCost());
				}
				
				for(Player p : players)
					resolveActions(p);

				for(Player p : players)
					resolveEffects(p);

			}

			for(Player p : players)
				if(p.canBuildLastCard())
					buildLastCard(p);
				else
					discardStructure(p);
			
			resolveConflicts();

		}
		
		calculateResults();
		
	}
	/**
	 * Randomly determines seating order and sets each player's neighbors.
	 */
	private void setNeighbors() {

		logger.log(Level.INFO, "[7WONDERS] Defining neighbors.\n");
		// Shuffle player order
		Collections.shuffle(players, new Random(seed));

		// Set each players' left and right neighbor dependending on seating order
		for(int i = 0; i < players.size(); i++) {

			players.get(i).setRightNeighbor(players.get((i+1) % playerCount));
			players.get((i+1) % playerCount).setLeftNeighbor(players.get(i));

		}
		
		Player p = players.get(0);
		logger.log(Level.INFO, String.format("\t%s ",players.get(0).getName()));
		for(int i = 0; i < players.size(); i++) {
			
			p = p.getRightNeighbor();
			logger.log(Level.INFO, String.format("-> %s ",p.getName()));
			
			
		}
		
		logger.log(Level.INFO, "\n");
		
		p = players.get(0);
		logger.log(Level.INFO, String.format("\t%s ",players.get(0).getName()));
		for(int i = 0; i < players.size(); i++) {
			
			p = p.getLeftNeighbor();
			logger.log(Level.INFO, String.format("<- %s ",p.getName()));
			
			
		}
		
		logger.log(Level.INFO, "\n");
		
		logger.log(Level.INFO, "*****************************************************************\n");

	}
	/**
	 * Randomly allocate wonderboards to each player by shuffling the wonderboardsDatabse array list, removing the second
	 * instance of any wonderboard up to the playerCount
	 */
	private void setWonderboards() {

		// Randomize wonderboards
		Collections.shuffle(wonderboardDatabase, new Random(seed));

		// Up to player count, remove second instance of each board
		for(int i = 0; i < playerCount; i++)
			for(int j = i+1; j < wonderboardDatabase.size(); j++)
				if(wonderboardDatabase.get(i).getName() == wonderboardDatabase.get(j).getName()) {

					wonderboardDatabase.remove(j);
					break;

				}

		// Allocate a wonderboard to each player
		Iterator<Wonderboard> it = wonderboardDatabase.iterator();
		for(Player p : players)
			p.setWonderboard(it.next());

	}
}