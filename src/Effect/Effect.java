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
 *  Object representation of the effects granted by structures and wonder stages. 
 * 	These effects can give resoures, points, science symbols, or unique abilities to 
 * 	the player who owns the effect. See page <b>xx</b> for more information.
 */
public abstract class Effect {
	
	/**
	 * 	Denotes if the effect has been activated
	 */
	private boolean effectTriggered;
	
	/**
	 * 	Rate at which the ability is activated
	 */
	private Frequency frequency;
	
	/**
	 * 	Effect Identification
	 */
	private final EffectType type;
	
	public Effect(EffectType type, Frequency frequency) {
		
		this.type = type;
		
		this.frequency  = frequency;
		effectTriggered = false;
	
	}
	
	public EffectType getEffectType() {  return type;             }
	public Frequency  getFrequency()  {  return frequency;        }
	public boolean    isUsed()        {  return effectTriggered;  }
	
	/**
	 * 	Resets effectTriggered to <i>false</i> if the effect's frequency of activation matches the
	 * 	passe frequency value.
	 * @param f - rate of effect reactivation
	 */
	public void refreshEffect(Frequency f) {
		
		// If the effect has not been triggered, it cannot be refreshed
		if(effectTriggered == false) 
			return;
		
		if(this.frequency == f)
			refreshEffect();
		
	}
	
	public void refreshEffect() {  effectTriggered = false;  }
	public void triggerEffect() {  effectTriggered = true;   }
	
	/**
	 * 	Abstract member of <i>Effect</i>. Grants the bonus provided by the
	 * 	effect to the player.
	 * @param p - Player receiving the bonus
	 */
	abstract public void useEffect(Player p);
	
}