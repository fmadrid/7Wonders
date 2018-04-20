/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Player;

import SevenWonders.Constants.Age;

/**
 * 	Helper class to <i>Player</i>. Maintains the number of victory and defeat tokens in addition to
 *  the number of points granted from conflict tokens.
 */
public class Conflict {
	
	/**
	 * Points provided by conflict tokens
	 */
	private int points;
	
	/**
	 * Number of victories over the player's neighbors
	 */
	private int victoryCount;
	
	/**
	 * Number of defeats from the player's neighbors
	 */
	private int defeatCount;
	
	public Conflict() {
		
		points       = 0;
		victoryCount = 0;
		defeatCount  = 0;
		 
	}
	
	/** 
	 * 	Each military conflict victory results in the increment of the victoryCounter and an 
	 * 	increase of 1, 3, or 5 points depending on the age the victory was achieved in.
	 * @param age - The age in which the victory was obtained
	 */
	public void addVictory(Age age) {
		
		victoryCount++;
		
		switch(age) {
		
			case ONE   : points += 1;	break;
			case TWO   : points += 3;	break;
			case THREE : points += 5;	break;
			
		}
		
	}
	
	/** 
	 * 	Each military conflict defeat results in the increment of the defeatCounter and the loss of
	 * 	a single point.
	 */
	public void addDefeat() {
		
		points--;
		defeatCount++;
		
	}

	public int getScore()     {  return points;        }
	public int getVictories() {  return victoryCount;  }
	public int getDefeats()   {  return defeatCount;   }
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Points: %d, Victories: %d, Defeats: %d\n", points, victoryCount, defeatCount));
		
		return sb.toString();
		
	}

}