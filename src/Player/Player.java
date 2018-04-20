/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: 
 * 					  	Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Effect.Effect;
import Player.ResourcesManager.Neighbor;
import SevenWonders.Constants.Action;
import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.Agent;
import SevenWonders.Constants.CardColor;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;
import SevenWonders.Constants.ObjectType;
import SevenWonders.Constants.Science;
import Strategy.Human;
import Strategy.Learner;
import Strategy.RandomCard;
import Strategy.RbAI1;
import Strategy.RbAI2;
import Strategy.Strategy;
import Structure.Structure;
import Tokens.Resources;
import Wonderboard.Wonderboard;

/**
 * 	Object representation of the player.
 */
public class Player {

	//////////////////////////////
	// Member Variables
	//////////////////////////////

	private static final Logger logger = Logger.getLogger("myLogger");

	/**
	 * Player's selected action. Set by <i>strategy</i>.
	 */
	private Action selectedAction;

	private Agent agent;

	/**
	 * List of constructed structures
	 */
	private ArrayList<Structure> builtStructures;

	/**
	 * Manages points, victory tokens, and defeat tokens from military conflicts.
	 */
	private Conflict conflict; 

	/**
	 * List of effects granted from constructed structures and stages
	 */
	private ArrayList<Effect> effects;

	/**
	 * End game score.
	 */
	private int finalScore;

	/**
	 * Denotes whether the player can ignore the cost of a single structure.
	 */
	private boolean freeBuild;

	/**
	 * List of structures currently in the player's hand.
	 */
	private ArrayList<Structure> hand;

	/**
	 * Player's left neighbor
	 */
	private Player leftNeighbor;

	/**
	 * Player's name
	 */
	private String name;

	/**
	 * Denotes if the player can play the final card of the round.
	 */
	private boolean playLastCard;

	/**
	 * Manages resources provided by cards and board effects
	 */
	private ResourcesManager resources;

	/**
	 * Player's right neighbor
	 */
	private Player rightNeighbor;

	/**
	 * Manages points, and scientific symbols earned from science
	 */
	private ScienceSymbols science;

	/**
	 * Player's selected structure set by <i>strategy</i>.
	 */
	private Structure selectedStructure;

	/**
	 * Shields granted by effects.
	 */
	private int shields;

	/**
	 * AI implementation
	 */
	private Strategy strategy;

	/**
	 * List of resources able to be purchased by player neighbors
	 */
	private Resources tradableResources;

	/**
	 * Cumulated victory points
	 */
	private int victoryPoints;

	/**
	 * Assigned wonderboard
	 */
	private Wonderboard wonderboard;

	//////////////////////////////
	// Member Functions
	//////////////////////////////

	/**
	 * Default constructor
	 * @param id
	 * @param name
	 */
	public Player(String name, Agent agent) {

		this.name  = name;
		this.agent = agent;
		strategy          = null;
		wonderboard       = null;
		builtStructures   = new ArrayList<Structure>();
		victoryPoints     = 0;
		resources         = new ResourcesManager();
		shields           = 0;
		conflict          = new Conflict();
		science 	      = new ScienceSymbols();
		effects 	      = new ArrayList<Effect>(); 
		hand              = null;
		leftNeighbor      = null;
		rightNeighbor     = null;
		selectedAction    = null;
		selectedStructure = null;
		freeBuild         = false;
		playLastCard      = false;
		finalScore        = 0;
	}

	public void runStrategy(long seed, boolean free, boolean canDiscard) {

		switch(agent) {

		case RANDOM :
			strategy = new RandomCard(this, seed, free, canDiscard);
			break;

		case RBAI1 :
			strategy = new RbAI1(this, seed, free, canDiscard);
			break;
			
		case RBAI2 :
			strategy = new RbAI2(this, seed, free, canDiscard);
			break;
			
		case HUMAN :
			strategy = new Human(this, seed, free, canDiscard);
			break;
		case LEARNER :
			strategy = new Learner(this, seed, free, canDiscard);
			break;
		default :
			strategy = new RandomCard(this, seed, free, canDiscard);
		}

	}

	public int getObjectCount(ObjectType objectType) {

		int objectCount = 0;

		switch(objectType) {

		case BLUE_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.BLUE) objectCount++;
			break;
		case BROWN_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.BROWN) objectCount++;
			break;
		case GRAY_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.GRAY) objectCount++;
			break;
		case GREEN_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.GREEN) objectCount++;
			break;
		case PURPLE_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.PURPLE) objectCount++;
			break;
		case RED_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.RED) objectCount++;
			break;
		case YELLOW_CARD : 
			for(Structure s : builtStructures)
				if(s.getColor() == CardColor.YELLOW) objectCount++;
			break;
		case DEFEAT_TOKEN : 
			objectCount += conflict.getDefeats();
			break;
		case WONDER_STAGE : 
			objectCount += wonderboard.getLevel();
			break;
		case VICTORY_TOKEN : 
			objectCount += conflict.getVictories();
			break;

		}

		return objectCount;

	}

	public int compareTo(Player p) {

		if(this.getFinalScore() > p.getFinalScore())
			return -1;

		else if(this.getFinalScore() == p.getFinalScore())
			return 0;

		else
			return 1;

	}

	public void addGold(int coinBonus) { 
		
		if(coinBonus == 0)
			return;
		
		logger.log(Level.FINE, String.format("[PLAYER] Player [%s] gaining [%d] gold.\n", name, coinBonus));
		
		resources.addResources(new Resources.Builder().gold(coinBonus).build(),false);  }

	public void addResources(Resources r, boolean c) {  resources.addResources(r,c);  }
	public void addScienceSymbol(Science s)          {  science.addSymbol(s);         }
	public void addShields(int s)                    {  shields += s;                 }

	public void addTradable(Resources r) {  

		leftNeighbor.getResourcesManager().addResources(Adjacency.LEFT, r);
		rightNeighbor.getResourcesManager().addResources(Adjacency.RIGHT, r);

	}

	public void addVictoryPoints(int p)              {  victoryPoints += p;           }
	public void buildStructure(Structure s)          {  builtStructures.add(s);       }

	public void calculateScore() {

		victoryPoints = 0;

		for(Effect e : effects)
			if(e.getEffectType() == EffectType.VICTORY_POINT_BONUS) {
				e.refreshEffect(Frequency.GAME);
				e.useEffect(this);
			}

		finalScore += victoryPoints;
		finalScore += conflict.getScore();
		finalScore += science.getScore();
		finalScore += resources.getCombinations().get(0).getGold() / 3;
		logger.log(Level.INFO, String.format("[PLAYER] [%s] Victory Points: [%d].\n", name, victoryPoints));
		logger.log(Level.INFO, String.format("[PLAYER] [%s] Conflict Score: [%d].\n", name, conflict.getScore()));
		logger.log(Level.INFO, String.format("[PLAYER] [%s] Science Score: [%d].\n", name, science.getScore()));
		logger.log(Level.INFO, String.format("[PLAYER] [%s] Gold Score: [%d].\n", name, resources.getCombinations().get(0).getGold() / 3));
		logger.log(Level.INFO, String.format("[PLAYER] [%s] has a final score of [%d].\n", name, finalScore));

	}

	public boolean canBuildLastCard()   {  return playLastCard;  }
	public boolean canFreeBuild()       {  return freeBuild;     }
	public void    disableFreeBuild()   {  freeBuild = false;    }
	public void    enableFreeBuild()    {  freeBuild = true;     }
	public void    enablePlayLastCard() {  playLastCard = true;  }

	public int getScore() {return finalScore;}
	public Action               getAction()            {  return selectedAction;     }
	public Agent                getAgent()             {  return agent;              }
	public ArrayList<Structure> getBuiltStructures()   {  return builtStructures;    }
	public Conflict             getConflict()          {  return conflict;           }
	public ArrayList<Effect>    getEffects()           {  return effects;            }
	public int                  getFinalScore()        {  return finalScore;         }
	public ArrayList<Structure> getHand()              {  return hand;               }
	public Player               getLeftNeighbor()      {  return leftNeighbor;       }
	public String               getName()              {  return name;               }
	public ResourcesManager     getResourcesManager()  {  return resources;          }
	public Player               getRightNeighbor()     {  return rightNeighbor;      }
	public ScienceSymbols       getScience()           {  return science;            }
	public Structure            getSelectedStructure() {  return selectedStructure;  }
	public int                  getShields()           {  return shields;            }
	public Strategy             getStrategy()          {  return strategy;           } 
	public Resources            getTradableResources() {  return tradableResources;  }
	public int			        getVictoryPoints()     {  return victoryPoints;      }
	public Wonderboard          getWonderboard()       {  return wonderboard;        }

	public boolean hasStructure(Structure s) {

		if(s == null)
			return false;

		for(Structure t : builtStructures)
			if(s.getID() == t.getID())
				return true;

		return false;
	}

	public boolean hasStructure(ArrayList<Structure> structures) {

		if(structures == null)
			return false;

		for(Structure s : structures)
			for(Structure t : builtStructures)
				if(s.getID() == t.getID()) {

					logger.log(Level.FINE, String.format("[PLAYER] Player [%s] freebuilt using [%s].\n", name, s.getName()));
					return true;
				}

		return false;

	}

	public void setHand(ArrayList<Structure> h) {  

		logger.log(Level.FINE, String.format("[SEVENWONDERS] Player [%s] receiving hand: \n", name));

		for(Structure s : h)
			logger.log(Level.FINE, "\t" + s.getName() + "\n");

		hand = h;

	}

	public void setAction(Action a)             {  selectedAction = a;     }
	public void setLeftNeighbor(Player p)       {  leftNeighbor    = p;    }
	public void setRightNeighbor(Player p)      {  rightNeighbor   = p;    }
	public void setStructure(Structure s)       {  selectedStructure = s;  }

	public void setWonderboard(Wonderboard wb)  {  

		logger.log(Level.INFO, String.format("[PLAYER] Player [%s] receiving wonderboard [%s Side %s]\n", name, 
				wb.getName(), wb.getSide()));

		wonderboard     = wb;
		resources.addResources(wb.getResource(),false);
		addTradable(wb.getResource());

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Name: %s", name));

		return sb.toString();
	}

	public void payGold(Resources r) {

		int tgold1 = 0;
		int tgold2 = 0;
		int rawCost = 20;
		int gold1 = 0;
		int gold2 = 0;
		
		for(Resources c : resources.getCombinations())
			if(r.subtract(c).isEmpty()){
				resources.loseGold(r.getGold());
				return;
			}
		
		resources.loseGold(r.getGold());
		
		ArrayList<Resources> missingRaw = new ArrayList<Resources>();
		for(Resources c : resources.getCombinations())
			missingRaw.add(r.subtractRaw(c));

		ArrayList<Resources> missingManufactured = new ArrayList<Resources>();
		for(Resources c : resources.getCombinations())
			missingManufactured.add(r.subtractManufactured(c));
		
		Neighbor p1 = null;
		Neighbor p2 = null;
		
		boolean  left = false;
		if(resources.getLeftNeighbor().getRawPrice() <= resources.getRightNeighbor().getRawPrice()) {
			left = true;
			p1 = resources.getLeftNeighbor();
			p2 = resources.getRightNeighbor();
		}
		else {

			p1 = resources.getRightNeighbor();
			p2 = resources.getLeftNeighbor();

		}

		for(Resources c : missingRaw) {
			
			if(c.isEmpty()) {
				rawCost = 0;
				gold1 = 0;
				gold2 = 0;
				break;
			}
			
			if(c.subtract(p1.getResources()).isEmpty())
				tgold1   = c.cardinalityRaw() * p1.getRawPrice();

			if((c.subtract(p1.getResources())).subtract(p2.getResources()).isEmpty())
				tgold2 = c.subtract(p1.getResources()).cardinalityRaw() * p2.getRawPrice();

			if(tgold1 + tgold2 < rawCost) {
				rawCost = tgold1 + tgold2;
				gold1 = tgold1;
				gold2 = tgold2;
			}
			
		}
		
		if(rawCost != 0)
			logger.log(Level.FINE, String.format("[PLAYER] Paying [%d] gold.\n", rawCost));
		resources.loseGold(rawCost);
		if(left) {

			leftNeighbor.addGold(gold1);
			rightNeighbor.addGold(gold2);

		}
		else {
			leftNeighbor.addGold(gold2);
			rightNeighbor.addGold(gold1);
		}

		if(resources.getLeftNeighbor().getManufacturedPrice() <= resources.getRightNeighbor().getManufacturedPrice()) {
			left = true;
			p1 = resources.getLeftNeighbor();
			p2 = resources.getRightNeighbor();
		}
		else {

			p1 = resources.getRightNeighbor();
			p2 = resources.getLeftNeighbor();

		}
		
		tgold1 = 0;
		tgold2 = 0;
		rawCost = 20;
		gold1 = 0;
		gold2 = 0;

		for(Resources c : missingManufactured) {

			if(c.isEmpty()) {
				rawCost = 0;
				gold1 = 0;
				gold2 = 0;
				break;
			}
			
			if(c.subtract(p1.getResources()).isEmpty())
				tgold1   = c.cardinalityManufactured() * p1.getManufacturedPrice();

			if((c.subtract(p1.getResources())).subtract(p2.getResources()).isEmpty())
				tgold2 = c.subtract(p1.getResources()).cardinalityManufactured() * p2.getManufacturedPrice();

			if(tgold1 + tgold2 < rawCost) {
				
				rawCost = tgold1 + tgold2;
				gold1 = tgold1;
				gold2 = tgold2;
				
			}

		}

		if(rawCost != 0)
			logger.log(Level.FINE, String.format("[PLAYER] Paying [%d] gold.\n", rawCost));
		
		resources.loseGold(rawCost);
		
		if(left) {

			leftNeighbor.addGold(gold1);
			rightNeighbor.addGold(gold2);

		}
		else {
			
			leftNeighbor.addGold(gold2);
			rightNeighbor.addGold(gold1);
			
		}

		
	}

}