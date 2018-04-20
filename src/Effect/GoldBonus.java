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
 * 	Object representation of any card or board effect which grants a gold bonus to the player
 */
public class GoldBonus extends Effect {
	
	/**
	 * 	Which subset of players to check for the owned object
	 */
	private final Adjacency  adjacency;
	
	/**
	 * 	Amount of gold granted by the effect
	 */
	private int goldBonus;
	
	/**
	 * 	Amount of gold per object granted by the effect
	 */
	private final int multiplier;
	
	/**
	 * 	Object type the effect is dependent on
	 */
	private final ObjectType object;
	
	/**
	 * 	Effect grants a one-time predetermined amount of gold.
	 */
	public GoldBonus(int gold) {
		
		super(EffectType.GOLD_BONUS, Frequency.GAME);
		
		goldBonus = gold;
		
		this.multiplier = 0;
		this.object     = null;
		this.adjacency  = null;
		
	}
	
	/**
	 * 	Effect grants a number of golds dependent on the amount of owned object by 
	 * 	the player, neighbors, or both
	 */
	public GoldBonus(int multiplier, ObjectType object, Adjacency adjacency) {
		
		super(EffectType.GOLD_BONUS, Frequency.GAME);
		
		goldBonus = 0;
		
		this.multiplier = multiplier;
		this.object     = object;
		this.adjacency  = adjacency;
		
	}
	
	public Adjacency  getAdjacency()  {  return adjacency;   }
	public int        getGoldBonus()  {  return goldBonus;   }
	public int        getMultiplier() {  return multiplier;  }
	public ObjectType getObjectType() {  return object;      }
	
	private void updateBonus(Player pl) {
		
		int objectCount = 0;
		
		if(adjacency == Adjacency.SELF)
			objectCount += pl.getObjectCount(object);
		
		if(adjacency == Adjacency.LEFT)
			objectCount += pl.getLeftNeighbor().getObjectCount(object);
		
		if(adjacency == Adjacency.RIGHT)
			objectCount += pl.getRightNeighbor().getObjectCount(object);
		
		goldBonus = objectCount * multiplier;
			
	}
	
	@Override
	public void useEffect(Player pl) {
		
		if(!isUsed()) {
			
			if(goldBonus == 0)
				updateBonus(pl);
			
			pl.addGold(goldBonus);	
			triggerEffect();
			
		}
				
	}
	
}