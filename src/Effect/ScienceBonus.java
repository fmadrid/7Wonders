/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;
import SevenWonders.Constants.Science;

/**
 * 	Object representation of any card or stage effect which grants a science symbol bonus to the 
 * 	player. A science bonus consists of one of three science symbols which directly contribute to
 * 	the player's final score. If the science symbol bonus is non-specific, the bonus is added as a
 * 	wild which is determined by the player at the game to maximize points.
 */
public class ScienceBonus extends Effect {
	
	/**
	 * 	Science bonus type granted by the effect
	 */
	private final Science scienceTypeBonus;
	
	public ScienceBonus(Science scienceTypeBonus) {
		
		super(EffectType.SCIENCE_BONUS, Frequency.GAME);
		
		this.scienceTypeBonus = scienceTypeBonus;
		
	}
	
	public Science getScienceTypeBonus() {  return scienceTypeBonus;  }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.addScienceSymbol(scienceTypeBonus);
			triggerEffect();
			
		}
		
	}
	
	
	
}
