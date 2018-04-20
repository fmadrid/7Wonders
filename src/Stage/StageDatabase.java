/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Stage;

import Effect.BuildDiscardedCard;
import Effect.CopyGuild;
import Effect.FreeBuild;
import Effect.GoldBonus;
import Effect.PlayLastCard;
import Effect.ResourceBonus;
import Effect.ScienceBonus;
import Effect.ShieldBonus;
import Effect.TradeBonus;
import Effect.VictoryPointBonus;
import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.Science;
import SevenWonders.Constants.Trade;
import Tokens.Resources;

/**
 * <b>Description:</b>
 * 		Collective list of stages belonging to each wonderboard.
 */
public class StageDatabase {

	public static Stage AlexandriaA1, AlexandriaA2, AlexandriaA3, 
						AlexandriaB1, AlexandriaB2, AlexandriaB3, 
						BabylonA1, BabylonA2, BabylonA3, 
						BabylonB1, BabylonB2, BabylonB3,
						EphesosA1, EphesosA2, EphesosA3, 
						EphesosB1, EphesosB2, EphesosB3,
						GizahA1, GizahA2, GizahA3, 
						GizahB1, GizahB2, GizahB3, GizahB4,
						HalikarnassosA1, HalikarnassosA2, HalikarnassosA3, 
						HalikarnassosB1, HalikarnassosB2, HalikarnassosB3,
						OlympiaA1, OlympiaA2, OlympiaA3, 
						OlympiaB1, OlympiaB2, OlympiaB3,
						RhodosA1, RhodosA2, RhodosA3, 
						RhodosB1, RhodosB2;
	
	/**
	 * <b>Description:</b>
	 * 		Initialization of all wonderboard stages. Stage id must indicate the order in
	 * 		which the stages appear on the wonderboard. After initialization, each effect
	 * 		is added.
	 */
	public StageDatabase() {
		
		// Alexandria
		AlexandriaA1 = new Stage(0x01, new Resources.Builder().stone(2).build());
		AlexandriaA1.addEffect(new VictoryPointBonus(3));
		
		AlexandriaA2 = new Stage(0x02, new Resources.Builder().ore(2).build());
		AlexandriaA2.addEffect(new ResourceBonus.Builder(true).wood(1).stone(1).brick(1).ore(1).build());
		
		AlexandriaA3 = new Stage(0x03, new Resources.Builder().glass(2).build());
		AlexandriaA3.addEffect(new VictoryPointBonus(7));
		
		AlexandriaB1 = new Stage(0x01, new Resources.Builder().brick(2).build());
		AlexandriaB1.addEffect(new ResourceBonus.Builder(true).wood(1).stone(1).brick(1).ore(1).build());
		
		AlexandriaB2 = new Stage(0x02, new Resources.Builder().wood(2).build());
		AlexandriaB2.addEffect(new ResourceBonus.Builder(true).glass(1).loom(1).papyrus(1).build());
		
		AlexandriaB3 = new Stage(0x03, new Resources.Builder().stone(3).build());
		AlexandriaB3.addEffect(new VictoryPointBonus(7));
		
		// Babylon
		BabylonA1 = new Stage(0x01, new Resources.Builder().brick(2).build());
		BabylonA1.addEffect(new VictoryPointBonus(3));
		
		BabylonA2 = new Stage(0x02, new Resources.Builder().wood(3).build());
		BabylonA2.addEffect(new ScienceBonus(Science.WILD));
		
		BabylonA3 = new Stage(0x03, new Resources.Builder().brick(4).build());
		BabylonA3.addEffect(new VictoryPointBonus(7));
		
		BabylonB1 = new Stage(0x01, new Resources.Builder().brick(1).loom(1).build());
		BabylonB1.addEffect(new VictoryPointBonus(3));
		
		BabylonB2 = new Stage(0x02, new Resources.Builder().wood(2).glass(1).build());
		BabylonB2.addEffect(new PlayLastCard());
		
		BabylonB3 = new Stage(0x03, new Resources.Builder().brick(3).papyrus(1).build());
		BabylonB3.addEffect(new ScienceBonus(Science.WILD));
				
		// Ephesos
		EphesosA1 = new Stage(0x01, new Resources.Builder().stone(2).build());
		EphesosA1.addEffect(new VictoryPointBonus(3));
		
		EphesosA2 = new Stage(0x02, new Resources.Builder().wood(2).build());
		EphesosA2.addEffect(new GoldBonus(9));
		
		EphesosA3 = new Stage(0x03, new Resources.Builder().papyrus(2).build());
		EphesosA3.addEffect(new VictoryPointBonus(7));
		
		EphesosB1 = new Stage(0x01, new Resources.Builder().stone(2).build());
		EphesosB1.addEffect(new VictoryPointBonus(2));
		EphesosB1.addEffect(new GoldBonus(4));
		
		EphesosB2 = new Stage(0x02, new Resources.Builder().wood(2).build());
		EphesosB2.addEffect(new VictoryPointBonus(3));
		EphesosB2.addEffect(new GoldBonus(4));		
		
		EphesosB3 = new Stage(0x03, new Resources.Builder().glass(1).loom(1).papyrus(1).build());
		EphesosB3.addEffect(new VictoryPointBonus(5));
		EphesosB3.addEffect(new GoldBonus(4));
		
		// Gizah
		GizahA1 = new Stage(0x01, new Resources.Builder().stone(2).build());
		GizahA1.addEffect(new VictoryPointBonus(3));
		
		GizahA2 = new Stage(0x02, new Resources.Builder().wood(3).build());
		GizahA2.addEffect(new VictoryPointBonus(5));
		
		GizahA3 = new Stage(0x03, new Resources.Builder().stone(4).build());
		GizahA3.addEffect(new VictoryPointBonus(7));
		
		GizahB1 = new Stage(0x01, new Resources.Builder().wood(2).build());
		GizahB1.addEffect(new VictoryPointBonus(3));
		
		GizahB2 = new Stage(0x02, new Resources.Builder().stone(3).build());
		GizahB2.addEffect(new VictoryPointBonus(5));
		
		GizahB3 = new Stage(0x03, new Resources.Builder().brick(3).build());
		GizahB3.addEffect(new VictoryPointBonus(5));
		
		GizahB4 = new Stage(0x04, new Resources.Builder().stone(4).build());
		GizahB4.addEffect(new VictoryPointBonus(7));
		
		// Halikarnassos
		HalikarnassosA1 = new Stage(0x01, new Resources.Builder().brick(2).build());
		HalikarnassosA1.addEffect(new VictoryPointBonus(3));
		
		HalikarnassosA2 = new Stage(0x02, new Resources.Builder().ore(3).build());
		HalikarnassosA2.addEffect(new BuildDiscardedCard());
		
		HalikarnassosA3 = new Stage(0x03, new Resources.Builder().loom(2).build());
		HalikarnassosA3.addEffect(new VictoryPointBonus(7));
		
		HalikarnassosB1 = new Stage(0x01, new Resources.Builder().ore(2).build());
		HalikarnassosB1.addEffect(new VictoryPointBonus(2));
		HalikarnassosB1.addEffect(new BuildDiscardedCard());
		
		HalikarnassosB2 = new Stage(0x02, new Resources.Builder().brick(3).build());
		HalikarnassosB2.addEffect(new VictoryPointBonus(1));
		HalikarnassosB2.addEffect(new BuildDiscardedCard());
		
		HalikarnassosB3 = new Stage(0x03, new Resources.Builder().glass(1).loom(1).papyrus(1).build());
		HalikarnassosB3.addEffect(new BuildDiscardedCard());
				
		// Olympia
		OlympiaA1 = new Stage(0x01, new Resources.Builder().wood(2).build());
		OlympiaA1.addEffect(new VictoryPointBonus(3));
		
		OlympiaA2 = new Stage(0x02, new Resources.Builder().stone(2).build());
		OlympiaA2.addEffect(new FreeBuild());
		
		OlympiaA3 = new Stage(0x03, new Resources.Builder().ore(2).build());
		OlympiaA3.addEffect(new VictoryPointBonus(7));
		
		OlympiaB1 = new Stage(0x01, new Resources.Builder().wood(2).build());
		OlympiaB1.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.LEFT));
		OlympiaB1.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.RIGHT));
		
		OlympiaB2 = new Stage(0x02, new Resources.Builder().stone(2).build());
		OlympiaB2.addEffect(new VictoryPointBonus(5));
		
		OlympiaB3 = new Stage(0x03, new Resources.Builder().ore(2).loom(1).build());
		OlympiaB3.addEffect(new CopyGuild());
		
		// Rhodos
		RhodosA1 = new Stage(0x01, new Resources.Builder().wood(2).build());
		RhodosA1.addEffect(new VictoryPointBonus(3));
		
		RhodosA2 = new Stage(0x02, new Resources.Builder().brick(3).build());
		RhodosA2.addEffect(new ShieldBonus(2));
		
		RhodosA3 = new Stage(0x03, new Resources.Builder().ore(4).build());
		RhodosA3.addEffect(new VictoryPointBonus(7));
		
		RhodosB1 = new Stage(0x01, new Resources.Builder().stone(3).build());
		RhodosB1.addEffect(new ShieldBonus(1));
		RhodosB1.addEffect(new VictoryPointBonus(3));
		RhodosB1.addEffect(new GoldBonus(3));
		
		RhodosB2 = new Stage(0x02, new Resources.Builder().ore(4).build());
		RhodosB2.addEffect(new ShieldBonus(1));
		RhodosB2.addEffect(new VictoryPointBonus(4));
		RhodosB2.addEffect(new GoldBonus(4));		
		
	}
	
}
