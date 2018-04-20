/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;

/**
 * 	Object representation of any card or stage effect which grants a shield bonus to the player. A
 * 	shield bonus consists of a bonus of one or three shields to the players shieldCount which is
 * 	compared to the shield counts of neighboring players when determining conflict resolution at
 * 	the end of each Age.
 */
public class ShieldBonus extends Effect {
	
	/**
	 *  Shields granted by the effect
	 */
	private final int shieldBonus;
	
	public ShieldBonus(int shieldBonus) {
		
		super(EffectType.SHIELD_BONUS, Frequency.GAME);
		
		this.shieldBonus = shieldBonus;
		
	}
	
	public int getShieldBonus() {  return shieldBonus;  }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.addShields(shieldBonus);
			triggerEffect();
			
		}
		
	}
	
	
	
}
