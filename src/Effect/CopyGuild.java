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
 * 	Object representation of any card or stage effect which grants a player the 
 * 	ability to copy a 'purple' built structure from his neighbor. See page <b>xx</b> 
 * 	for details.
 */
public class CopyGuild extends Effect {

	public CopyGuild() {  super(EffectType.COPY_GUILD, Frequency.GAME);  }

	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			triggerEffect();
			
		}
		
	}
	
	
	
}