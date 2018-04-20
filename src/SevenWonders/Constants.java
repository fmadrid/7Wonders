/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package SevenWonders;

/**
 * Constants file used for the game Seven Wonders
 *
 */
public interface Constants {

	/**
	 * Actions available to the player. The player may construct the next stage of his wonderboard, 
	 * construct a building from his hand, or discard a card from his hand. <br>
	 */
	 public static enum Action {CONSTRUCT_STAGE, CONSTRUCT_STRUCTURE, DISCARD_CARD};
	
	/** 
	  * Identifies which players to consider when determining effect resolution. Effects consider 
	  * either the player himself or his neighbor.
	  */
	public static enum Adjacency {LEFT, RIGHT, SELF};
	
	/**
	 * Identifies the current age (round).
	 */
	public static enum Age {ONE, THREE, TWO};

	/**
	 * Classifies players based on how they choose the next playable card within the game
	 */
	public static enum Agent {RANDOM, RBAI1, RBAI2, LEARNER, HUMAN};

	/**
	 * Identifies which side of the wonderboard is currently in use. Each wonderboard has a side A
	 * and a side B; however, both sides of a board cannot be in use.
	 */
	public static enum BoardSide {A, B};

	/**
	 * Types of colors for each structure. <br>
	 * <b>Blue Structures:</b> Civic Improvements<br>
	 * <b>Brown Structures:</b> Raw Materials<br>
	 * <b>Gray Structures:</b> Manufactured Goods<br>
	 * <b>Green Structures:</b> Scientific Advancements<br>
	 * <b>Purple Structures:</b> Guilds<br>
	 * <b>Red Structures:</b> Military Structures<br>
	 * <b>Yellow Structures:</b> Economic Structures<br>
	 */
	public static enum CardColor {BLUE, BROWN, GRAY, GREEN, PURPLE, RED, YELLOW};
		
	public static enum EffectType {BUILD_DISCARDED_CARD, COPY_GUILD, FREE_BUILD, 
		GOLD_BONUS, PLAY_LAST_CARD, RESOURCE_BONUS, SCIENCE_BONUS, SHIELD_BONUS, 
		TRADE_BONUS, VICTORY_POINT_BONUS};

	/**
	 * Rates at which effects can be used. Most effects are only used once (GAME); however, some 
	 * effects can be used once per round (ROUND), or even every turn (TURN).
	 */
	public static enum Frequency {AGE, GAME, TURN};

	/**
	 * Types of objects some victory point bonus and coin bonus effects are dependent on. The 
	 * effect.
	 */
	public static enum ObjectType {BLUE_CARD, BROWN_CARD, DEFEAT_TOKEN, 
		GRAY_CARD, GREEN_CARD, PURPLE_CARD, RED_CARD, VICTORY_TOKEN, WONDER_STAGE, 
		YELLOW_CARD};
	
	/**
	 * 	Types of science symbosl granted by effects. All effects grant a single specific symbol;
	 * 	however, others may grant any symbol determined end of game by the player.
	 */
	public static enum Science {COMPASS, GEAR, TABLET, WILD};
	
	/**
	 * 	Types of trading discount granted by the effect.
	 */
	public static enum Trade {MANUFACTURED_DISCOUNT, RAW_DISCOUNT}
	
	public static enum Option {FREE}
}


