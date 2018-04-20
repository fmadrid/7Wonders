/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;
import SevenWonders.Constants.Trade;

/**
 * 	Object representation of any card or stage effect which grants a trade bonus to the player. A
 * 	trade bonus takes the form as a gold discount for manufactured goods or raw materials when
 * 	purchased from an adjacent player.
 */
public class TradeBonus extends Effect {
	
	/**
	 *  Trade bonus granted by the effect
	 */
	private final Trade typeBonus;
	
	/**
	 *  Direction of trade discounts
	 */
	private final Adjacency adjacency;
	
	public TradeBonus(Trade typeBonus, Adjacency adjacency) {
		
		super(EffectType.TRADE_BONUS, Frequency.GAME);
		
		this.typeBonus = typeBonus;
		this.adjacency = adjacency;
		
	}
	
	public Trade     getTypeBonus() {  return typeBonus;  }
	public Adjacency getAdjacency() {  return adjacency;  }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.getResourcesManager().addDiscount(typeBonus, adjacency);
			
			triggerEffect();
			
		}
		
	}

}