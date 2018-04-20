/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Wonderboard;

import java.util.ArrayList;

import SevenWonders.Constants.BoardSide;
import Stage.StageDatabase;
import Tokens.Resources;

/**
 * 	Collective list of wonderboards
 */
public class WonderboardDatabase {

	/**
	 * List of wonderboards
	 */
	private static ArrayList<Wonderboard> wonderboards;

	/**
	 * 	Initialization of all wonderboards. After each initialization, stages are
	 * 	then added to the board in the order in which they appear on the board.
	 */
	public WonderboardDatabase() {  loadBase();  }
	
	/**
	 * 	Initializes the seven wonderboards: Alexandria, Babylon, Ephesos, Gizah, Halikarnassos, Olympia, Rhodes
	 */
	private void loadBase() {
		
		wonderboards = new ArrayList<Wonderboard>();
		
		new StageDatabase(); // Initialize wondeboard stages
		
		Wonderboard AlexandriaA = new Wonderboard(0x01, BoardSide.A, "Alexandria", 
				new Resources.Builder().glass(1).build());
		AlexandriaA.addStage(StageDatabase.AlexandriaA1);
		AlexandriaA.addStage(StageDatabase.AlexandriaA2);
		AlexandriaA.addStage(StageDatabase.AlexandriaA3);
		wonderboards.add(AlexandriaA);
		
		Wonderboard AlexandriaB = new Wonderboard(0x02, BoardSide.B, "Alexandria", 
				new Resources.Builder().glass(1).build());
		AlexandriaB.addStage(StageDatabase.AlexandriaB1);
		AlexandriaB.addStage(StageDatabase.AlexandriaB2);
		AlexandriaB.addStage(StageDatabase.AlexandriaB3);
		wonderboards.add(AlexandriaB);
		
		Wonderboard BabylonA = new Wonderboard(0x03, BoardSide.A, "Babylon", 
				new Resources.Builder().brick(1).build());
		BabylonA.addStage(StageDatabase.BabylonA1);
		BabylonA.addStage(StageDatabase.BabylonA2);
		BabylonA.addStage(StageDatabase.BabylonA3);
		wonderboards.add(BabylonA);
		
		Wonderboard BabylonB = new Wonderboard(0x04, BoardSide.B, "Babylon", 
				new Resources.Builder().brick(1).build());
		BabylonB.addStage(StageDatabase.BabylonB1);
		BabylonB.addStage(StageDatabase.BabylonB2);
		BabylonB.addStage(StageDatabase.BabylonB3);
		wonderboards.add(BabylonB);
		
		Wonderboard EphesosA = new Wonderboard(0x05, BoardSide.A, "Ephesos", 
				new Resources.Builder().papyrus(1).build());
		EphesosA.addStage(StageDatabase.EphesosA1);
		EphesosA.addStage(StageDatabase.EphesosA2);
		EphesosA.addStage(StageDatabase.EphesosA3);
		wonderboards.add(EphesosA);
		
		Wonderboard EphesosB = new Wonderboard(0x06, BoardSide.B, "Ephesos", 
				new Resources.Builder().papyrus(1).build());
		EphesosB.addStage(StageDatabase.EphesosB1);
		EphesosB.addStage(StageDatabase.EphesosB2);
		EphesosB.addStage(StageDatabase.EphesosB3);
		wonderboards.add(EphesosB);
		
		Wonderboard GizahA = new Wonderboard(0x07, BoardSide.A, "Gizah", 
				new Resources.Builder().stone(1).build());
		GizahA.addStage(StageDatabase.GizahA1);
		GizahA.addStage(StageDatabase.GizahA2);
		GizahA.addStage(StageDatabase.GizahA3);
		wonderboards.add(GizahA);
		
		Wonderboard GizahB = new Wonderboard(0x08, BoardSide.B, "Gizah", 
				new Resources.Builder().stone(1).build());
		GizahB.addStage(StageDatabase.GizahB1);
		GizahB.addStage(StageDatabase.GizahB2);
		GizahB.addStage(StageDatabase.GizahB3);
		GizahB.addStage(StageDatabase.GizahB4);
		wonderboards.add(GizahB);
		
		Wonderboard HalikarnassosA = new Wonderboard(0x09, BoardSide.A, "Halikarnassos", 
				new Resources.Builder().loom(1).build());
		HalikarnassosA.addStage(StageDatabase.HalikarnassosA1);
		HalikarnassosA.addStage(StageDatabase.HalikarnassosA2);
		HalikarnassosA.addStage(StageDatabase.HalikarnassosA3);
		wonderboards.add(HalikarnassosA);
		
		Wonderboard HalikarnassosB = new Wonderboard(0x10, BoardSide.B, "Halikarnassos", 
				new Resources.Builder().loom(1).build());
		HalikarnassosB.addStage(StageDatabase.HalikarnassosB1);
		HalikarnassosB.addStage(StageDatabase.HalikarnassosB2);
		HalikarnassosB.addStage(StageDatabase.HalikarnassosB3);
		wonderboards.add(HalikarnassosB);
				
		Wonderboard OlympiaA = new Wonderboard(0x11, BoardSide.A, "Olympia", 
				new Resources.Builder().wood(1).build());
		OlympiaA.addStage(StageDatabase.OlympiaA1);
		OlympiaA.addStage(StageDatabase.OlympiaA2);
		OlympiaA.addStage(StageDatabase.OlympiaA3);
		wonderboards.add(OlympiaA);
		
		Wonderboard OlympiaB = new Wonderboard(0x12, BoardSide.B, "Olympia", 
				new Resources.Builder().wood(1).build());
		OlympiaB.addStage(StageDatabase.OlympiaB1);
		OlympiaB.addStage(StageDatabase.OlympiaB2);
		OlympiaB.addStage(StageDatabase.OlympiaB3);
		wonderboards.add(OlympiaB);
		
		Wonderboard RhodosA = new Wonderboard(0x13, BoardSide.A, "Rhodos", 
				new Resources.Builder().ore(1).build());
		RhodosA.addStage(StageDatabase.RhodosA1);
		RhodosA.addStage(StageDatabase.RhodosA2);
		RhodosA.addStage(StageDatabase.RhodosA3);
		wonderboards.add(RhodosA);
		
		Wonderboard RhodosB = new Wonderboard(0x14, BoardSide.B, "Rhodos", 
				new Resources.Builder().ore(1).build());	
		RhodosB.addStage(StageDatabase.RhodosB1);
		RhodosB.addStage(StageDatabase.RhodosB2);
		wonderboards.add(RhodosB);
			
	}

	public ArrayList<Wonderboard> getWonderboards() {  return wonderboards;  }
		
}