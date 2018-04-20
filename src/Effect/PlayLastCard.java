/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;

/**
 * 	Object representation of any card or stage effect which grants a player the ability to play his 
 * 	last structure as opposed to discarding it. Normally, after the sixth turn, the remaining
 * 	unused structure is discarded; however, with this ability, the player can play this card as
 * 	normal by either discarding it for three gold, using it to construct a stage of his wonder or
 * 	construct the structure itself.
 */
public class PlayLastCard extends Effect {

	public PlayLastCard() {  super(EffectType.PLAY_LAST_CARD, Frequency.GAME);  }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.enablePlayLastCard();
			triggerEffect();
			
		}		
	}
	
}