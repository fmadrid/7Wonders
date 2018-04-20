/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;
import SevenWonders.Constants.ObjectType;

/** 
 * 	Object representation of any card or stage effect which grants a victory point bonus to the player.
 */
public class VictoryPointBonus extends Effect {
	
	/**
	 * 	Which player to consider when checking for owned object count
	 */
	private final Adjacency  adjacency;
	
	/**
	 * 	Object the effect is dependent on
	 */
	private final ObjectType object;
	
	/**
	 *  Victory point amount granted by the effect
	 */
	private int pointBonus;
	
	/**
	 * 	Amount of victory points per object granted by the effect
	 */
	private final int multiplier;
	
	/**
	 * 	Default Constructor. Static amount of victory points given by the effect.
	 */
	public VictoryPointBonus(int points) {
		
		super(EffectType.VICTORY_POINT_BONUS, Frequency.GAME);
		
		pointBonus = points;
		
		multiplier = 0;
		object     = null;
		adjacency  = null;
		
	}
	
	/**
	 * 	Default Constructor. Dynamic amount of victory points given by the effect.
	 */
	public VictoryPointBonus(int multiplier, ObjectType object, Adjacency adjacency) {
		
		super(EffectType.VICTORY_POINT_BONUS, Frequency.GAME);
		
		pointBonus = 0;
		
		this.multiplier = multiplier;
		this.object     = object;
		this.adjacency  = adjacency;
		
	}
	
	public Adjacency  getAdjacency()  {  return adjacency;   }
	public int        getMultiplier() {  return multiplier;  }
	public ObjectType getType()       {  return object;      }
	
	public int getPointBonus(Player pl) {  
		
		if(pointBonus == 0)
			updateBonus(pl);
		
		return pointBonus;  
		
	}
	
	private void updateBonus(Player pl) {
		
		int objectCount = 0;
		
		if(adjacency == Adjacency.SELF)
			objectCount += pl.getObjectCount(object);
		
		if(adjacency == Adjacency.LEFT)
			objectCount += pl.getLeftNeighbor().getObjectCount(object);
		
		if(adjacency == Adjacency.RIGHT)
			objectCount += pl.getRightNeighbor().getObjectCount(object);
		
		pointBonus = objectCount * multiplier;
			
	}
	
	@Override
	public void useEffect(Player pl) {
		
		if(!isUsed()) {
			
			if(pointBonus == 0)
				updateBonus(pl);
			
			pl.addVictoryPoints(pointBonus);	
			triggerEffect();
			
		}
				
	}
	
}