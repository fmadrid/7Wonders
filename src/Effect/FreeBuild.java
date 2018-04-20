/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;

/**
 * 	Object representation of any structure or wonderboard stage effect which grants a player the 
 * 	ability to constuct a building at no cost.
 */
public class FreeBuild extends Effect {


	public FreeBuild() {  super(EffectType.FREE_BUILD, Frequency.GAME);  }

	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.enableFreeBuild();
			triggerEffect();
			
		}
		
	}
	
}