/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Effect;

import Player.Player;
import SevenWonders.Constants.EffectType;
import SevenWonders.Constants.Frequency;
import Tokens.Resources;

/**
 * 	Object representation of any card or stage effect which grants a resource bonus to the player.
 * 	A resource bonus consists of the addition of one or more of seven types of resources. If the 
 * 	resource bonus provides a choice between multiple resource types, the player can select one of
 * 	the types of resources to use per turn; otherwise, the resource is always available. Only
 * 	resources granted by wonderboards, brown structures, or gray structures are available to be
 * 	purchased from adjacent neighbors.
 */
public class ResourceBonus extends Effect {
	
	/**
	 * 	Set of resources granted by the effect
	 */
	private final Resources resourceBonus;
	
	/**
	 * 	Denotes if the effect provides a choice in resource to add
	 */
	private final boolean choice;
	
	/**
	 *  Denotes if the resources can be purchased by a neighboring player
	 */
	private final boolean tradable;
	
	/** 
	 * 	Builder pattern for object <i>ResourceBonus</i>. <i>choice</i> is required while all 
	 * 	attributes are optional and defaulted to zero
	 */
	public static class Builder {
		
		private Resources     resourceBonus;
		private final boolean choice;
		private boolean       tradable;
		
		private int wood, stone, brick, ore, glass, loom, papyrus = 0;
		
		public Builder(boolean choice) {
			
			resourceBonus = null;
			this.choice = choice;
			
		}
		
		public Builder isTradable()       {  tradable = true;   return this;  }
		public Builder wood(int value)    {  wood     = value;  return this;  }
		public Builder stone(int value)   {  stone    = value;  return this;  }
		public Builder brick(int value)   {  brick    = value;  return this;  }
		public Builder ore(int value)     {  ore      = value;  return this;  }
		public Builder glass(int value)   {  glass    = value;  return this;  }
		public Builder loom(int value)    {  loom     = value;  return this;  }
		public Builder papyrus(int value) {  papyrus  = value;  return this;  }
		
		public ResourceBonus build() {
			
			resourceBonus = new Resources.Builder().wood(wood).stone(stone).brick(brick).
					ore(ore).glass(glass).loom(loom).papyrus(papyrus).build();
			
			return new ResourceBonus(this);
			
		}
		
	}
	
	public ResourceBonus(Builder b) {
		
		super(EffectType.RESOURCE_BONUS, Frequency.GAME);
		
		resourceBonus = b.resourceBonus;
		choice        = b.choice;
		tradable      = b.tradable;
				
	}
	
	public Resources getResourceBonus() {  return resourceBonus;  }
	public boolean   getChoice()        {  return choice;         }
	public boolean   isTradable()       {  return tradable;       }
	
	@Override
	public void useEffect(Player p) {
		
		if(!isUsed()) {
			
			p.addResources(resourceBonus,choice);
			
			if(tradable)
				p.addTradable(resourceBonus);
			
			triggerEffect();
			
		}
		
	}
	
}