/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Stage;

import java.util.ArrayList;

import Effect.Effect;
import Tokens.Resources;

/**
 * <b>Description:</b> 
 * 		Object representation of the wonderstage object. Each stage has an associated 
 * 		resource cost, and a list of effects granted to the player upon construction. 
 * 		See page <b>xx</b> for more information.
 */
public class Stage {

	/**
	 * 	Internal identification number
	 */
	private int id;
	
	/**
	 * 	Resources required to build the stage
	 */
	private final Resources resourceCost;
	
	/**
	 * 	Benefits the wonderstage provides when constructed
	 */
	private ArrayList<Effect> effects;
	
	/**
	 * 	Denotes if the wonderstage has been constructed
	 */
	private boolean built;
	
	/**
	 * 	Default Constructor. Instantiates object properties and initializes effect list.
	 */
	public Stage(int id, Resources resourceCost) {
		
		this.id           = id;
		
		this.resourceCost = resourceCost;
		effects           = new ArrayList<Effect>();
		
		built = false;
		
	}

	/**
	 * 	Adds the passed effect to the stage effect list
	 * @param e - Effect
	 */
	public void addEffect(Effect e) {  effects.add(e);  }
	
	public int       getID()              {  return id;            }
	public Resources getResourceCost()    {  return resourceCost;  }
	public ArrayList<Effect> getEffects() {  return effects;       }
	public boolean   isBuilt()            {  return built;         }
	
	public void buildStage() {  built = true;  }
	
	/**
	 * 	Overrides Object.toString(). Returns a string representation of the stage id, 
	 * 	resource cost, and effects.
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Stage: %d Cost: ", id));
		
		if(resourceCost.getGold() != 0)
			sb.append(String.format("(%d) %-8s ", resourceCost.getGold(), "Gold"));
		
		if(resourceCost.getWood() != 0)
			sb.append(String.format("(%d) %-8s ", resourceCost.getWood(), "Wood"));
		                                        
		if(resourceCost.getStone() != 0)        
			sb.append(String.format("(%d) %-8s ", resourceCost.getStone(), "Stone"));
		                                        
		if(resourceCost.getBrick() != 0)        
			sb.append(String.format("(%d) %-8s ", resourceCost.getBrick(), "Brick"));
		                                        
		if(resourceCost.getOre() != 0)          
			sb.append(String.format("(%d) %-8s ", resourceCost.getOre(), "Ore"));
		                                        
		if(resourceCost.getGlass() != 0)        
			sb.append(String.format("(%d) %-8s ", resourceCost.getGlass(), "Glass"));
		                                        
		if(resourceCost.getLoom() != 0)         
			sb.append(String.format("(%d) %-8s ", resourceCost.getLoom(), "Loom"));
		                                        
		if(resourceCost.getPapyrus() != 0)      
			sb.append(String.format("(%d) %-8s ", resourceCost.getPapyrus(), "Papyrus"));
		
		return sb.toString();
		
	}

}