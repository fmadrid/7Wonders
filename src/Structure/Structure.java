/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Structure;

import java.util.ArrayList;

import Effect.Effect;
import SevenWonders.Constants.Age;
import SevenWonders.Constants.CardColor;
import Tokens.Resources;

/**
 * 	Object representation of the card object <i>structure</i>.  Each structure has a
 * 	name, color, age in which the structure appears, an associated resource cost, a
 * 	list of effects the structure provides when constructed, the number of copies,
 * 	and a structure if completed satisfies the resource cost. See page <b>xx</b> for
 * 	more information.
 */
public class Structure {

	
	/**
	 * Structure age
	 */
	private final Age age;
	
	/**
	 * 	Structure color
	 */
	private final CardColor color;
	
	/**
	 *  Benefits the structure provides when constructed
	 */
	private ArrayList<Effect> effects;
	
	/**
	 * Indicates the list of structures which satisfies the cost of the structure
	 */
	private ArrayList<Structure> freeBuild;
	
	/**
	 *  The amount of copies which exist within the game
	 */
	private ArrayList<Integer> frequency;
	
	/**
	 *  Internal identification number
	 */
	private final int id;
	
	/**
	 * 	Structure name
	 */
	private final String name;
	
	/**
	 * 	Resources required to build the structure
	 */
	private final Resources resourceCost;
	
	/**
	 * 	Default Constructor. Create new Structure object with passed parameters. 
	 * 	Initialize empty effects, frequency, and freeBuild ArrayLists
	 */
	public Structure (int id, String name, Age age, CardColor color, Resources resourceCost) {
		
		this.id           = id;
		this.name         = name;
		this.color        = color;
		this.age          = age;	
		this.resourceCost = resourceCost;
		
		effects   = new ArrayList<Effect>();
		frequency = new ArrayList<Integer>();
		freeBuild = new ArrayList<Structure>();
				
	}
	
	public void addEffect(Effect e)       {  effects.add(e);    }
	public void addFreeBuild(Structure s) {  freeBuild.add(s);  }
	public void addFrequency(int f)       {  frequency.add(f);  }
	
	public Age                  getAge()          {  return age;           }
	public CardColor            getColor()        {  return color;         }
	public ArrayList<Effect>    getEffects()      {  return effects;       }
	public ArrayList<Structure> getFreeBuild()    {  return freeBuild;     }
	public ArrayList<Integer>   getFrequencies()  {  return frequency;     }
	public int                  getID()           {  return id;            }
	public String               getName()         {  return name;          }
	public Resources            getResourceCost() {  return resourceCost;  }
	
	/**
	 * 	Overrides Object.toString(). Returns a string representation of the structure
	 * 	id, name, age, color, cost, and effects.
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
	
		sb.append(String.format("Name: [%-20s] Age: [%-5s] Color: [%-6s]  Cost: %s", 
				name, age.toString(), color.toString(), resourceCost.toString()));
		
		return sb.toString();
		
	}
	
}