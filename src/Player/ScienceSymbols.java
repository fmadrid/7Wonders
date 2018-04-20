/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an 
 * 		Intelligent Agent for the Boardgame '7 Wonders'
 */

package Player;

import SevenWonders.Constants.Science;

/**
 * 	Helper class to <i>Player</i>. Maintains the quantity and types of science symbols owned by the 
 * 	player and the amount of points provided by the science symbols.
 */
public class ScienceSymbols {
	
	/**
	 * Science symbol A
	 */
	private int compass; 
	
	/**
	 * Science symbol B
	 */
	private int gear;
	
	/**
	 * Science symbol C
	 */
	private int tablet;
	
	/** 
	 * Can represent any one of the original science symbols and is decided by the 
	 * player at the end of the game
	 */
	private int wild;
	
	public ScienceSymbols() {
	
		compass = 0;
		gear    = 0;
		tablet  = 0;
		wild    = 0;	
		
	}

	/**
	 * 	Adds the passed Science type
	 * @param type
	 */
	public void addSymbol(Science type) {
		
		switch(type) {
		
			case COMPASS : compass++;
						   break;
			case GEAR    : gear++;
						   break;
			case TABLET  : tablet++;
						   break;
			case WILD    : wild++;
						   break;
		}
		
	}

	/**
	 * 	Calls the recursive function <i>permuteScience</i>.
	 */
	public int getScore() {  return permuteScience(compass, gear, tablet, wild, 0);  }
	
	/**
	 * 	A recursive function which calculates the maximum score possible from the given set of 
	 * 	science symbols by calculating the score associated with each permutation of the wild 
	 * 	science symbols as each other science symbol. Each complete set of science symbols (one of 
	 * 	each type) is worth seven points while each set of like symbols are worth their square in 
	 * 	points.
	 */
	private int permuteScience(int a, int b, int c, int wild, int max) {
				
		/* 
		 * If we are no longer allocating wild symbols, we have reached the end of the
		 * recursion and can return the score associated with the particular 
		 * configuration of science symbols a, b, and c 
		 */
		if(wild == 0) {
			
			int score = 0;
			
			score += a * a;
			score += b * b;
			score += c * c;
			score += Math.min(Math.min(a,b),c) * 7;
			
			return score;
			
		}
		
		 // For each possible permutation of the three science symbols
		for(int i = 0; i < 3; i++) {
		
			int tmpA = a;
			int tmpB = b;
			int tmpC = c;
			
			if(i == 0)      tmpA++;
			else if(i == 1) tmpB++;
			else if(i == 2) tmpC++;
			
			// Call the recursive function with the new temporary science symbols and a decremented 
			// wild while maintaining the maximum score
			max = Math.max(permuteScience(tmpA, tmpB, tmpC, wild - 1, max), max);
			
		}
		
		return max;
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Points: [%d] Compass: [%d], Gear: [%d], Tablet: [%d], Wild: [%d]", 
				getScore(), compass, gear, tablet, wild));
		
		return sb.toString();
		
	}
	
}