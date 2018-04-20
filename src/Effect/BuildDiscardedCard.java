/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;

/**
 * 	Object representation of any card or board effect which grants a player the action to build a 
 * 	structure from the discard pile at no cost.
 */
public class BuildDiscardedCard extends Effect {

	public BuildDiscardedCard() {  super(EffectType.BUILD_DISCARDED_CARD, Frequency.GAME);  }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed())
			triggerEffect();
		
	}
	
}