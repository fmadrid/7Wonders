/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Wonderboard;

import java.util.ArrayList;

import SevenWonders.Constants.BoardSide;
import Stage.Stage;
import Tokens.Resources;

/**
 * 		Object representation of the <i>wonderboard</i> object. Each wonderboard has a name, side, starting resource, and an ordered list of
 * 		wonder stages each with its own cost and effect it provides the player. See
 * 		page <b>XX</b> for more information.
 */
public class Wonderboard {

	//////////////////////
	// Member Variables //
	//////////////////////

	/**
	 * Maintains the number of completed stages
	 */
	private int level;

	/**
	 * Internal identification number
	 */
	private final int id;

	/**
	 * Wonderboard Property: Name
	 */
	private final String name;

	/**
	 * Resource provided by the wonderboard
	 */
	private final Resources resource;

	/**
	 * Wonderboard Property: Indicates Side A or Side B
	 */
	private final BoardSide side;

	/**
	 * List of ordered wonderboard stages
	 */
	private ArrayList<Stage> stages;

	//////////////////////
	// Member Functions //
	//////////////////////

	public Wonderboard(int id, BoardSide side, String name, Resources resource) {

		this.id       = id;
		this.name     = name;
		this.side     = side;
		this.resource = resource;

		stages = new ArrayList<Stage>();
		level  = 0;

	}

	public void addStage(Stage s) {  stages.add(s);  }

	public int getLevel() {

		level = 0;
		
		for(Stage s : stages)
			if(s.isBuilt())
				level++;
		
		return level;
	}

	public int              getID()       {  return id;        }
	public String           getName()     {  return name;      }	

	public Resources        getResource() {  return resource;  }
	public BoardSide        getSide()     {  return side;      }
	public ArrayList<Stage> getStages()   {  return stages;    }

	/**
	 * 	Returns the first unconstructed stage within the sequence. If all stages 
	 * 	have been constructed or if there are no wonder stages, returns null.
	 */
	public Stage getNextStage() {

		if (stages == null) return null;

		for (Stage s : stages)
			if (!s.isBuilt())
				return s;

		// If all stages of the wonderboard have been constructed
		return null;

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Name: [%s] Side: [%s] Resource: [%s]\n", 
				name, side.toString(), resource.toString()));

		for (Stage s : stages)
			sb.append("\t" + s.toString() + "\n");

		return sb.toString();

	}

}