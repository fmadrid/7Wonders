package Structure;

import java.util.ArrayList;

import Effect.GoldBonus;
import Effect.ResourceBonus;
import Effect.ScienceBonus;
import Effect.ShieldBonus;
import Effect.TradeBonus;
import Effect.VictoryPointBonus;
import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.Age;
import SevenWonders.Constants.CardColor;
import SevenWonders.Constants.ObjectType;
import SevenWonders.Constants.Science;
import SevenWonders.Constants.Trade;
import Tokens.Resources;

public class StructureDatabase {

	private ArrayList<Structure> structures;
	private ArrayList<Structure> guilds;
	
	public StructureDatabase() {
		
		structures = new ArrayList<Structure>();
		guilds     = new ArrayList<Structure>();

		loadBaseGame();
		
	}
			
	private void loadBaseGame() {
		
		// Brown Cards		
		Structure LumberYard = new Structure(0x01, "Lumber Yard", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		LumberYard.addEffect(new ResourceBonus.Builder(false).isTradable().wood(1).build());
		LumberYard.addFrequency(3);
		structures.add(LumberYard);
		
		LumberYard = new Structure(0x01, "Lumber Yard", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		LumberYard.addEffect(new ResourceBonus.Builder(false).isTradable().wood(1).build());
		LumberYard.addFrequency(4);
		structures.add(LumberYard);

		
		Structure StonePit = new Structure(0x02, "Stone Pit", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		StonePit.addEffect(new ResourceBonus.Builder(false).isTradable().stone(1).build());
		StonePit.addFrequency(3);
		structures.add(StonePit);
		
		StonePit = new Structure(0x02, "Stone Pit", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		StonePit.addEffect(new ResourceBonus.Builder(false).isTradable().stone(1).build());
		StonePit.addFrequency(5);
		structures.add(StonePit);

		
		Structure ClayPool = new Structure(0x03, "Clay Pool", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		ClayPool.addEffect(new ResourceBonus.Builder(false).isTradable().brick(1).build());
		ClayPool.addFrequency(3);
		structures.add(ClayPool);
		
		ClayPool = new Structure(0x03, "Clay Pool", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		ClayPool.addEffect(new ResourceBonus.Builder(false).isTradable().brick(1).build());
		ClayPool.addFrequency(5);
		structures.add(ClayPool);
		
		Structure OreVein = new Structure(0x04, "Ore Vein", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		OreVein.addEffect(new ResourceBonus.Builder(false).isTradable().ore(1).build());
		OreVein.addFrequency(3);
		structures.add(OreVein);
		
		OreVein = new Structure(0x04, "Ore Vein", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().build());
		OreVein.addEffect(new ResourceBonus.Builder(false).isTradable().ore(1).build());
		OreVein.addFrequency(4);
		structures.add(OreVein);
		
		Structure TreeFarm = new Structure(0x05, "Tree Farm", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		TreeFarm.addEffect(new ResourceBonus.Builder(true).isTradable().wood(1).brick(1).build());
		TreeFarm.addFrequency(6);
		structures.add(TreeFarm);
		
		Structure Excavation = new Structure(0x06, "Excavation", Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Excavation.addEffect(new ResourceBonus.Builder(true).isTradable().stone(1).brick(1).build());
		Excavation.addFrequency(4);
		structures.add(Excavation);
		
		Structure ClayPit = new Structure(0x07, "Clay Pit",Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		ClayPit.addEffect(new ResourceBonus.Builder(true).isTradable().brick(1).ore(1).build());
		ClayPit.addFrequency(3);
		structures.add(ClayPit);
		
		Structure TimberYard = new Structure(0x08, "Timber Yard",Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		TimberYard.addEffect(new ResourceBonus.Builder(true).isTradable().wood(1).stone(1).build());
		TimberYard.addFrequency(3);
		structures.add(TimberYard);
		
		Structure ForestCave = new Structure(0x09, "Forest Cave",Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		ForestCave.addEffect(new ResourceBonus.Builder(true).isTradable().wood(1).ore(1).build());
		ForestCave.addFrequency(5);
		structures.add(ForestCave);
		
		Structure Mine = new Structure(0x0A, "Mine",Age.ONE, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Mine.addEffect(new ResourceBonus.Builder(true).isTradable().stone(1).ore(1).build());
		Mine.addFrequency(6);
		structures.add(Mine);
		
		Structure Sawmill = new Structure(0x0B, "Sawmill",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Sawmill.addEffect(new ResourceBonus.Builder(false).isTradable().wood(2).build());
		Sawmill.addFrequency(3);
		structures.add(Sawmill);
		
		Sawmill = new Structure(0x0B, "Sawmill",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Sawmill.addEffect(new ResourceBonus.Builder(false).isTradable().wood(2).build());
		Sawmill.addFrequency(4);
		structures.add(Sawmill);
		
		Structure Quarry = new Structure(0x0C, "Quarry",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Quarry.addEffect(new ResourceBonus.Builder(false).isTradable().stone(2).build());
		Quarry.addFrequency(3);
		structures.add(Quarry);
		
		Quarry = new Structure(0x0C, "Quarry",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Quarry.addEffect(new ResourceBonus.Builder(false).isTradable().stone(2).build());
		Quarry.addFrequency(4);
		structures.add(Quarry);
		
		Structure Brickyard = new Structure(0x0D, "Brickyard",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Brickyard.addEffect(new ResourceBonus.Builder(false).isTradable().brick(2).build());
		Brickyard.addFrequency(3);
		structures.add(Brickyard);
		
		Brickyard = new Structure(0x0D, "Brickyard",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Brickyard.addEffect(new ResourceBonus.Builder(false).isTradable().brick(2).build());
		Brickyard.addFrequency(4);
		structures.add(Brickyard);
		
		Structure Foundry = new Structure(0x0E, "Foundry",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Foundry.addEffect(new ResourceBonus.Builder(false).isTradable().ore(2).build());
		Foundry.addFrequency(3);
		structures.add(Foundry);
		
		Foundry = new Structure(0x0E, "Foundry",Age.TWO, CardColor.BROWN, 
				new Resources.Builder().gold(1).build());
		Foundry.addEffect(new ResourceBonus.Builder(false).isTradable().ore(2).build());
		Foundry.addFrequency(4);
		structures.add(Foundry);
		
		// Purple Cards
		Structure WorkersGuild = new Structure(0x0F, "Workers Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().wood(1).stone(1).brick(1).ore(2).build());
		WorkersGuild.addEffect(new VictoryPointBonus(1, ObjectType.BROWN_CARD, Adjacency.LEFT));
		WorkersGuild.addEffect(new VictoryPointBonus(1, ObjectType.BROWN_CARD, Adjacency.RIGHT));
		guilds.add(WorkersGuild);
		
		Structure CraftsmensGuild = new Structure(0x10, "Craftsmens Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().stone(2).ore(2).build());
		CraftsmensGuild.addEffect(new VictoryPointBonus(2, ObjectType.GRAY_CARD, Adjacency.LEFT));
		CraftsmensGuild.addEffect(new VictoryPointBonus(2, ObjectType.GRAY_CARD, Adjacency.RIGHT));
		guilds.add(CraftsmensGuild);
		
		Structure TradersGuild = new Structure(0x11, "Traders Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().glass(1).loom(1).papyrus(1).build());
		TradersGuild.addEffect(new VictoryPointBonus(1, ObjectType.YELLOW_CARD, Adjacency.LEFT));
		TradersGuild.addEffect(new VictoryPointBonus(1, ObjectType.YELLOW_CARD, Adjacency.RIGHT));
		guilds.add(TradersGuild);
		
		Structure PhilosophersGuild = new Structure(0x12, "Philosophers Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().stone(3).loom(1).papyrus(1).build());
		PhilosophersGuild.addEffect(new VictoryPointBonus(1, ObjectType.GREEN_CARD, Adjacency.LEFT));
		PhilosophersGuild.addEffect(new VictoryPointBonus(1, ObjectType.GREEN_CARD, Adjacency.RIGHT));
		guilds.add(PhilosophersGuild);
		
		Structure SpiesGuild = new Structure(0x13, "Spies Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().stone(3).glass(1).build());
		SpiesGuild.addEffect(new VictoryPointBonus(1, ObjectType.RED_CARD, Adjacency.LEFT));
		SpiesGuild.addEffect(new VictoryPointBonus(1, ObjectType.RED_CARD, Adjacency.RIGHT));
		guilds.add(SpiesGuild);
		
		Structure StrategistsGuild = new Structure(0x14, "Strategists Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().stone(1).ore(2).loom(1).build());
		StrategistsGuild.addEffect(new VictoryPointBonus(1, ObjectType.DEFEAT_TOKEN, Adjacency.LEFT));
		StrategistsGuild.addEffect(new VictoryPointBonus(1, ObjectType.DEFEAT_TOKEN, Adjacency.RIGHT));
		guilds.add(StrategistsGuild);
		
		Structure ShipownersGuild = new Structure(0x15, "Shipowners Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().wood(3).glass(1).loom(1).build());
		ShipownersGuild.addEffect(new VictoryPointBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		ShipownersGuild.addEffect(new VictoryPointBonus(1, ObjectType.PURPLE_CARD, Adjacency.SELF));
		ShipownersGuild.addEffect(new VictoryPointBonus(1, ObjectType.GRAY_CARD, Adjacency.SELF));
		guilds.add(ShipownersGuild);
		
		Structure ScientistsGuild = new Structure(0x16, "Scientists Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().wood(2).ore(2).papyrus(1).build());
		ScientistsGuild.addEffect(new ScienceBonus(Science.WILD));
		guilds.add(ScientistsGuild);
		
		Structure MagistratesGuild = new Structure(0x17, "Magistrates Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().wood(3).stone(1).loom(1).build());
		MagistratesGuild.addEffect(new VictoryPointBonus(1, ObjectType.BLUE_CARD, Adjacency.LEFT));
		MagistratesGuild.addEffect(new VictoryPointBonus(1, ObjectType.BLUE_CARD, Adjacency.RIGHT));
		guilds.add(MagistratesGuild);
		
		Structure BuildersGuild = new Structure(0x18, "Builders Guild",Age.THREE, CardColor.PURPLE, 
				new Resources.Builder().stone(2).ore(2).glass(1).build());
		BuildersGuild.addEffect(new VictoryPointBonus(1, ObjectType.WONDER_STAGE, Adjacency.LEFT));
		BuildersGuild.addEffect(new VictoryPointBonus(1, ObjectType.WONDER_STAGE, Adjacency.RIGHT));
		guilds.add(BuildersGuild);
		
		// Gray Cards
		Structure Loom = new Structure(0x19, "Loom",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		Loom.addEffect(new ResourceBonus.Builder(false).isTradable().loom(1).build());
		Loom.addFrequency(3);
		structures.add(Loom);
		
		Loom = new Structure(0x19, "Loom",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		Loom.addEffect(new ResourceBonus.Builder(false).isTradable().loom(1).build());
		Loom.addFrequency(6);
		structures.add(Loom);
		
		Structure GlassWorks = new Structure(0x1A, "Glass Works",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		GlassWorks.addEffect(new ResourceBonus.Builder(false).isTradable().glass(1).build());
		GlassWorks.addFrequency(3);
		structures.add(GlassWorks);
		
		GlassWorks = new Structure(0x1A, "Glass Works",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		GlassWorks.addEffect(new ResourceBonus.Builder(false).isTradable().glass(1).build());
		GlassWorks.addFrequency(6);
		structures.add(GlassWorks);
		
		Structure Press = new Structure(0x1B, "Press",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		Press.addEffect(new ResourceBonus.Builder(false).isTradable().papyrus(1).build());
		Press.addFrequency(3);
		structures.add(Press);
		
		Press = new Structure(0x1B, "Press",Age.ONE, CardColor.GRAY, 
				new Resources.Builder().build());
		Press.addEffect(new ResourceBonus.Builder(false).isTradable().papyrus(1).build());
		Press.addFrequency(6);
		structures.add(Press);
		
		Structure Loom2 = new Structure(0x19, "Loom",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		Loom2.addEffect(new ResourceBonus.Builder(false).isTradable().loom(1).build());
		Loom2.addFrequency(3);
		structures.add(Loom2);
		
		Loom2 = new Structure(0x19, "Loom",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		Loom2.addEffect(new ResourceBonus.Builder(false).isTradable().loom(1).build());
		Loom2.addFrequency(5);
		structures.add(Loom2);
		
		Structure GlassWorks2 = new Structure(0x1A, "Glass Works",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		GlassWorks2.addEffect(new ResourceBonus.Builder(false).isTradable().glass(1).build());
		GlassWorks2.addFrequency(3);
		structures.add(GlassWorks2);
		
		GlassWorks2 = new Structure(0x1A, "Glass Works",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		GlassWorks2.addEffect(new ResourceBonus.Builder(false).isTradable().glass(1).build());
		GlassWorks2.addFrequency(5);
		structures.add(GlassWorks2);
		
		Structure Press2 = new Structure(0x1B, "Press",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		Press2.addEffect(new ResourceBonus.Builder(false).isTradable().papyrus(1).build());
		Press2.addFrequency(3);
		structures.add(Press2);
		
		Press2 = new Structure(0x1B, "Press",Age.TWO, CardColor.GRAY, 
				new Resources.Builder().build());
		Press2.addEffect(new ResourceBonus.Builder(false).isTradable().papyrus(1).build());
		Press2.addFrequency(5);
		structures.add(Press2);
		
		// Green Cards
		Structure Apothecary = new Structure(0x1C, "Apothecary",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().loom(1).build());
		Apothecary.addEffect(new ScienceBonus(Science.COMPASS));
		Apothecary.addFrequency(3);
		structures.add(Apothecary);
		
		Apothecary = new Structure(0x1C, "Apothecary",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().loom(1).build());
		Apothecary.addEffect(new ScienceBonus(Science.COMPASS));
		Apothecary.addFrequency(5);
		structures.add(Apothecary);
		
		Structure Workshop = new Structure(0x1D, "Workshop",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().glass(1).build());
		Workshop.addEffect(new ScienceBonus(Science.GEAR));
		Workshop.addFrequency(3);
		structures.add(Workshop);
		
		Workshop = new Structure(0x1D, "Workshop",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().glass(1).build());
		Workshop.addEffect(new ScienceBonus(Science.GEAR));
		Workshop.addFrequency(7);
		structures.add(Workshop);
		
		Structure Scriptorium = new Structure(0x1E, "Scriptorium",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().papyrus(1).build());
		Scriptorium.addEffect(new ScienceBonus(Science.TABLET));
		Scriptorium.addFrequency(3);
		structures.add(Scriptorium);
		
		Scriptorium = new Structure(0x1E, "Scriptorium",Age.ONE, CardColor.GREEN, 
				new Resources.Builder().papyrus(1).build());
		Scriptorium.addEffect(new ScienceBonus(Science.TABLET));
		Scriptorium.addFrequency(4);
		structures.add(Scriptorium);
		
		Structure Dispensary = new Structure(0x1F, "Dispensary",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().ore(2).glass(1).build());
		Dispensary.addEffect(new ScienceBonus(Science.COMPASS));
		Dispensary.addFrequency(3);
		Dispensary.addFreeBuild(Apothecary);
		structures.add(Dispensary);
		
		Dispensary = new Structure(0x1F, "Dispensary",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().ore(2).glass(1).build());
		Dispensary.addEffect(new ScienceBonus(Science.COMPASS));
		Dispensary.addFrequency(4);
		Dispensary.addFreeBuild(Apothecary);
		structures.add(Dispensary);
		
		Structure Laboratory = new Structure(0x20, "Laboratory",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().brick(2).papyrus(1).build());
		Laboratory.addEffect(new ScienceBonus(Science.GEAR));
		Laboratory.addFrequency(3);
		Laboratory.addFreeBuild(Workshop);
		structures.add(Laboratory);
		
		Laboratory = new Structure(0x20, "Laboratory",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().brick(2).papyrus(1).build());
		Laboratory.addEffect(new ScienceBonus(Science.GEAR));
		Laboratory.addFrequency(5);
		Laboratory.addFreeBuild(Workshop);
		structures.add(Laboratory);
		
		Structure Library = new Structure(0x21, "Library",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().stone(2).loom(1).build());
		Library.addEffect(new ScienceBonus(Science.TABLET));
		Library.addFrequency(3);
		Library.addFreeBuild(Scriptorium);
		structures.add(Library);
		
		Library = new Structure(0x21, "Library",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().stone(2).loom(1).build());
		Library.addEffect(new ScienceBonus(Science.TABLET));
		Library.addFrequency(6);
		Library.addFreeBuild(Scriptorium);
		structures.add(Library);
		
		Structure School = new Structure(0x22, "School",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().wood(1).papyrus(1).build());
		School.addEffect(new ScienceBonus(Science.TABLET));
		School.addFrequency(3);
		structures.add(School);
		
		School = new Structure(0x22, "School",Age.TWO, CardColor.GREEN, 
				new Resources.Builder().wood(1).papyrus(1).build());
		School.addEffect(new ScienceBonus(Science.TABLET));
		School.addFrequency(7);
		structures.add(School);
		
		Structure Lodge = new Structure(0x23, "Lodge",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().brick(2).loom(1).papyrus(1).build());
		Lodge.addEffect(new ScienceBonus(Science.COMPASS));
		Lodge.addFrequency(3);
		Lodge.addFreeBuild(Dispensary);
		structures.add(Lodge);
		
		Lodge = new Structure(0x23, "Lodge",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().brick(2).loom(1).papyrus(1).build());
		Lodge.addEffect(new ScienceBonus(Science.COMPASS));
		Lodge.addFrequency(6);
		Lodge.addFreeBuild(Dispensary);
		structures.add(Lodge);
		
		Structure Observatory = new Structure(0x24, "Observatory",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().ore(2).glass(1).papyrus(1).build());
		Observatory.addEffect(new ScienceBonus(Science.GEAR));
		Observatory.addFrequency(3);
		Observatory.addFreeBuild(Laboratory);
		structures.add(Observatory);
		
		Observatory = new Structure(0x24, "Observatory",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().ore(2).glass(1).papyrus(1).build());
		Observatory.addEffect(new ScienceBonus(Science.GEAR));
		Observatory.addFrequency(7);
		Observatory.addFreeBuild(Laboratory);
		structures.add(Observatory);
		
		Structure University = new Structure(0x25, "University",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().wood(1).glass(1).papyrus(1).build());
		University.addEffect(new ScienceBonus(Science.TABLET));
		University.addFrequency(3);
		University.addFreeBuild(Library);
		structures.add(University);
		
		University = new Structure(0x25, "University",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().wood(1).glass(1).papyrus(1).build());
		University.addEffect(new ScienceBonus(Science.TABLET));
		University.addFrequency(4);
		University.addFreeBuild(Library);
		structures.add(University);
		
		Structure Academy = new Structure(0x26, "Academy",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().stone(3).glass(1).build());
		Academy.addEffect(new ScienceBonus(Science.COMPASS));
		Academy.addFrequency(3);
		Academy.addFreeBuild(School);
		structures.add(Academy);
		
		Academy = new Structure(0x26, "Academy",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().stone(3).glass(1).build());
		Academy.addEffect(new ScienceBonus(Science.COMPASS));
		Academy.addFrequency(7);
		Academy.addFreeBuild(School);
		structures.add(Academy);
		
		Structure Study = new Structure(0x27, "Study",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().wood(1).loom(1).papyrus(1).build());
		Study.addEffect(new ScienceBonus(Science.GEAR));
		Study.addFrequency(3);
		Study.addFreeBuild(School);
		structures.add(Study);
		
		Study = new Structure(0x27, "Study",Age.THREE, CardColor.GREEN, 
				new Resources.Builder().wood(1).loom(1).papyrus(1).build());
		Study.addEffect(new ScienceBonus(Science.GEAR));
		Study.addFrequency(5);
		Study.addFreeBuild(School);
		structures.add(Study);
		
		// Blue Cards
		Structure Pawnshop = new Structure(0x28, "Pawnshop",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Pawnshop.addEffect(new VictoryPointBonus(3));
		Pawnshop.addFrequency(4);
		structures.add(Pawnshop);
		
		Pawnshop = new Structure(0x28, "Pawnshop",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Pawnshop.addEffect(new VictoryPointBonus(3));
		Pawnshop.addFrequency(7);
		structures.add(Pawnshop);
		
		Structure Baths = new Structure(0x29, "Baths",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().stone(1).build());
		Baths.addEffect(new VictoryPointBonus(3));
		Baths.addFrequency(3);
		structures.add(Baths);
		
		Baths = new Structure(0x29, "Baths",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().stone(1).build());
		Baths.addEffect(new VictoryPointBonus(3));
		Baths.addFrequency(7);
		structures.add(Baths);
		
		Structure Altar = new Structure(0x2A, "Altar",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Altar.addEffect(new VictoryPointBonus(2));
		Altar.addFrequency(3);
		structures.add(Altar);
		
		Altar = new Structure(0x2A, "Altar",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Altar.addEffect(new VictoryPointBonus(2));
		Altar.addFrequency(5);
		structures.add(Altar);
		
		Structure Theatre = new Structure(0x2B, "Theatre",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Theatre.addEffect(new VictoryPointBonus(2));
		Theatre.addFrequency(3);
		structures.add(Theatre);
		
		Theatre = new Structure(0x2B, "Theatre",Age.ONE, CardColor.BLUE, 
				new Resources.Builder().build());
		Theatre.addEffect(new VictoryPointBonus(2));
		Theatre.addFrequency(6);
		structures.add(Theatre);
		
		Structure Aqueduct = new Structure(0x2C, "Aqueduct",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().stone(3).build());
		Aqueduct.addEffect(new VictoryPointBonus(5));
		Aqueduct.addFrequency(3);
		Aqueduct.addFreeBuild(Baths);
		structures.add(Aqueduct);
		
		Aqueduct = new Structure(0x2C, "Aqueduct",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().stone(3).build());
		Aqueduct.addEffect(new VictoryPointBonus(5));
		Aqueduct.addFrequency(7);
		Aqueduct.addFreeBuild(Baths);
		structures.add(Aqueduct);
		
		Structure Temple = new Structure(0x2D, "Temple",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().wood(1).brick(1).glass(1).build());
		Temple.addEffect(new VictoryPointBonus(3));
		Temple.addFrequency(3);
		Temple.addFreeBuild(Altar);
		structures.add(Temple);
		
		Temple = new Structure(0x2D, "Temple",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().wood(1).brick(1).glass(1).build());
		Temple.addEffect(new VictoryPointBonus(3));
		Temple.addFrequency(6);
		Temple.addFreeBuild(Altar);
		structures.add(Temple);
		
		Structure Statue = new Structure(0x2E, "Statue",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().wood(1).ore(2).build());
		Statue.addEffect(new VictoryPointBonus(4));
		Statue.addFrequency(3);
		Statue.addFreeBuild(Theatre);
		structures.add(Statue);
		
		Statue = new Structure(0x2E, "Statue",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().wood(1).ore(2).build());
		Statue.addEffect(new VictoryPointBonus(4));
		Statue.addFrequency(7);
		Statue.addFreeBuild(Theatre);
		structures.add(Statue);
		
		Structure Pantheon = new Structure(0x2F, "Pantheon",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().brick(2).ore(1).glass(1).loom(1).papyrus(1).build());
		Pantheon.addEffect(new VictoryPointBonus(7));
		Pantheon.addFrequency(3);
		Pantheon.addFreeBuild(Temple);
		structures.add(Pantheon);
		
		Pantheon = new Structure(0x2F, "Pantheon",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().brick(2).ore(1).glass(1).loom(1).papyrus(1).build());
		Pantheon.addEffect(new VictoryPointBonus(7));
		Pantheon.addFrequency(6);
		Pantheon.addFreeBuild(Temple);
		structures.add(Pantheon);
		
		Structure Gardens = new Structure(0x30, "Gardens",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(1).brick(2).build());
		Gardens.addEffect(new VictoryPointBonus(5));
		Gardens.addFrequency(3);
		Gardens.addFreeBuild(Statue);
		structures.add(Gardens);
		
		Gardens = new Structure(0x30, "Gardens",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(1).brick(2).build());
		Gardens.addEffect(new VictoryPointBonus(5));
		Gardens.addFrequency(4);
		Gardens.addFreeBuild(Statue);
		structures.add(Gardens);
		
		Structure TownHall = new Structure(0x31, "TownHall",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().stone(2).ore(1).glass(1).build());
		TownHall.addEffect(new VictoryPointBonus(6));
		TownHall.addFrequency(3);
		structures.add(TownHall);
		
		TownHall = new Structure(0x31, "TownHall",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().stone(2).ore(1).glass(1).build());
		TownHall.addEffect(new VictoryPointBonus(6));
		TownHall.addFrequency(5);
		structures.add(TownHall);
		
		TownHall = new Structure(0x31, "TownHall",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().stone(2).ore(1).glass(1).build());
		TownHall.addEffect(new VictoryPointBonus(6));
		TownHall.addFrequency(6);
		structures.add(TownHall);
		
		Structure Palace = new Structure(0x32, "Palace",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(1).stone(1).brick(1).ore(1).glass(1).loom(1).papyrus(1).build());
		Palace.addEffect(new VictoryPointBonus(8));
		Palace.addFrequency(3);
		structures.add(Palace);
		
		Palace = new Structure(0x32, "Palace",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(1).stone(1).brick(1).ore(1).glass(1).loom(1).papyrus(1).build());
		Palace.addEffect(new VictoryPointBonus(8));
		Palace.addFrequency(7);
		structures.add(Palace);
		
		Structure Courthouse = new Structure(0x33, "Courthouse",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().brick(2).loom(1).build());
		Courthouse.addEffect(new VictoryPointBonus(4));
		Courthouse.addFrequency(3);
		Courthouse.addFreeBuild(Scriptorium);
		structures.add(Courthouse);
		
		Courthouse = new Structure(0x33, "Courthouse",Age.TWO, CardColor.BLUE, 
				new Resources.Builder().brick(2).loom(1).build());
		Courthouse.addEffect(new VictoryPointBonus(4));
		Courthouse.addFrequency(5);
		Courthouse.addFreeBuild(Scriptorium);
		structures.add(Courthouse);
		
		Structure Senate = new Structure(0x34, "Senate",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(2).stone(1).ore(1).build());
		Senate.addEffect(new VictoryPointBonus(6));
		Senate.addFrequency(3);
		Senate.addFreeBuild(Library);
		structures.add(Senate);
		
		Senate = new Structure(0x34, "Senate",Age.THREE, CardColor.BLUE, 
				new Resources.Builder().wood(2).stone(1).ore(1).build());
		Senate.addEffect(new VictoryPointBonus(6));
		Senate.addFrequency(5);
		Senate.addFreeBuild(Library);
		structures.add(Senate);
		
		// Yellow Cards
		Structure Tavern = new Structure(0x35, "Tavern",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		Tavern.addEffect(new GoldBonus(5));
		Tavern.addFrequency(4);
		structures.add(Tavern);
		
		Tavern = new Structure(0x35, "Tavern",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		Tavern.addEffect(new GoldBonus(5));
		Tavern.addFrequency(5);
		structures.add(Tavern);
		
		Tavern = new Structure(0x35, "Tavern",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		Tavern.addEffect(new GoldBonus(5));
		Tavern.addFrequency(7);
		structures.add(Tavern);
		
		Structure EastTradingPost = new Structure(0x36, "East Trading Post",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		EastTradingPost.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.RIGHT));
		EastTradingPost.addFrequency(3);
		structures.add(EastTradingPost);
		
		EastTradingPost = new Structure(0x36, "East Trading Post",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		EastTradingPost.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.RIGHT));
		EastTradingPost.addFrequency(7);
		structures.add(EastTradingPost);
		
		Structure WestTradingPost = new Structure(0x37, "West Trading Post",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		WestTradingPost.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.LEFT));
		WestTradingPost.addFrequency(3);
		structures.add(WestTradingPost);
		
		WestTradingPost = new Structure(0x37, "West Trading Post",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		WestTradingPost.addEffect(new TradeBonus(Trade.RAW_DISCOUNT, Adjacency.LEFT));
		WestTradingPost.addFrequency(7);
		structures.add(WestTradingPost);
		
		Structure Marketplace = new Structure(0x38, "Marketplace",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		Marketplace.addEffect(new TradeBonus(Trade.MANUFACTURED_DISCOUNT, Adjacency.LEFT));
		Marketplace.addEffect(new TradeBonus(Trade.MANUFACTURED_DISCOUNT, Adjacency.RIGHT));
		Marketplace.addFrequency(3);
		structures.add(Marketplace);
		
		Marketplace = new Structure(0x38, "Marketplace",Age.ONE, CardColor.YELLOW, 
				new Resources.Builder().build());
		Marketplace.addEffect(new TradeBonus(Trade.MANUFACTURED_DISCOUNT, Adjacency.LEFT));
		Marketplace.addEffect(new TradeBonus(Trade.MANUFACTURED_DISCOUNT, Adjacency.RIGHT));
		Marketplace.addFrequency(6);
		structures.add(Marketplace);
		
		Structure Forum = new Structure(0x39, "Forum",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().brick(2).build());
		Forum.addEffect(new ResourceBonus.Builder(true).glass(1).loom(1).papyrus(1).build());
		Forum.addFrequency(3);
		Forum.addFreeBuild(WestTradingPost);
		Forum.addFreeBuild(EastTradingPost);
		structures.add(Forum);
		
		Forum = new Structure(0x39, "Forum",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().brick(2).build());
		Forum.addEffect(new ResourceBonus.Builder(true).glass(1).loom(1).papyrus(1).build());
		Forum.addFrequency(6);
		Forum.addFreeBuild(WestTradingPost);
		Forum.addFreeBuild(EastTradingPost);
		structures.add(Forum);
		
		Forum = new Structure(0x39, "Forum",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().brick(2).build());
		Forum.addEffect(new ResourceBonus.Builder(true).glass(1).loom(1).papyrus(1).build());
		Forum.addFrequency(7);
		Forum.addFreeBuild(WestTradingPost);
		Forum.addFreeBuild(EastTradingPost);
		structures.add(Forum);
		
		Structure Caravansery = new Structure(0x3A, "Caravansery",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().wood(2).build());
		Caravansery.addEffect(new ResourceBonus.Builder(true).wood(1).stone(1).brick(1).ore(1).build());
		Caravansery.addFrequency(3);
		Caravansery.addFreeBuild(Marketplace);
		structures.add(Caravansery);
		
		Caravansery = new Structure(0x3A, "Caravansery",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().wood(2).build());
		Caravansery.addEffect(new ResourceBonus.Builder(true).wood(1).stone(1).brick(1).ore(1).build());
		Caravansery.addFrequency(5);
		Caravansery.addFreeBuild(Marketplace);
		structures.add(Caravansery);
		
		Caravansery = new Structure(0x3A, "Caravansery",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().wood(2).build());
		Caravansery.addEffect(new ResourceBonus.Builder(true).wood(1).stone(1).brick(1).ore(1).build());
		Caravansery.addFrequency(6);
		Caravansery.addFreeBuild(Marketplace);
		structures.add(Caravansery);
		
		Structure Vineyard = new Structure(0x3B, "Vineyard",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().build());
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.LEFT));
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.RIGHT));
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Vineyard.addFrequency(3);
		structures.add(Vineyard);
		
		Vineyard = new Structure(0x3B, "Vineyard",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().build());
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.LEFT));
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.RIGHT));
		Vineyard.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Vineyard.addFrequency(6);
		structures.add(Vineyard);
		
		Structure Bazar = new Structure(0x3C, "Bazar", Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().build());
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.LEFT));
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.RIGHT));
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		Bazar.addFrequency(4);
		structures.add(Bazar);
		
		Bazar = new Structure(0x3C, "Bazar",Age.TWO, CardColor.YELLOW, 
				new Resources.Builder().build());
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.LEFT));
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.RIGHT));
		Bazar.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		Bazar.addFrequency(7);
		structures.add(Bazar);
		
		Structure Haven = new Structure(0x3D, "Haven",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().wood(1).ore(1).loom(1).build());
		Haven.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Haven.addEffect(new VictoryPointBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Haven.addFrequency(3);
		Haven.addFreeBuild(Forum);
		structures.add(Haven);
		
		Haven = new Structure(0x3D, "Haven",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().wood(1).ore(1).loom(1).build());
		Haven.addEffect(new GoldBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Haven.addEffect(new VictoryPointBonus(1, ObjectType.BROWN_CARD, Adjacency.SELF));
		Haven.addFrequency(4);
		Haven.addFreeBuild(Forum);
		structures.add(Haven);
		
		Structure Lighthouse = new Structure(0x3E, "Lighthouse",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().stone(1).glass(1).build());
		Lighthouse.addEffect(new GoldBonus(1, ObjectType.YELLOW_CARD, Adjacency.SELF));
		Lighthouse.addEffect(new VictoryPointBonus(1, ObjectType.YELLOW_CARD, Adjacency.SELF));
		Lighthouse.addFrequency(3);
		Lighthouse.addFreeBuild(Caravansery);
		structures.add(Lighthouse);
		
		Lighthouse = new Structure(0x3E, "Lighthouse",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().stone(1).glass(1).build());
		Lighthouse.addEffect(new GoldBonus(1, ObjectType.YELLOW_CARD, Adjacency.SELF));
		Lighthouse.addEffect(new VictoryPointBonus(1, ObjectType.YELLOW_CARD, Adjacency.SELF));
		Lighthouse.addFrequency(6);
		Lighthouse.addFreeBuild(Caravansery);
		structures.add(Lighthouse);
		
		Structure ChamberOfCommerce = new Structure(0x3F, "Chamber Of Commerce",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().brick(2).papyrus(1).build());
		ChamberOfCommerce.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		ChamberOfCommerce.addEffect(new VictoryPointBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		ChamberOfCommerce.addFrequency(4);
		structures.add(ChamberOfCommerce);
		
		ChamberOfCommerce = new Structure(0x3F, "Chamber Of Commerce",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().brick(2).papyrus(1).build());
		ChamberOfCommerce.addEffect(new GoldBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		ChamberOfCommerce.addEffect(new VictoryPointBonus(2, ObjectType.GRAY_CARD, Adjacency.SELF));
		ChamberOfCommerce.addFrequency(6);
		structures.add(ChamberOfCommerce);
		
		Structure Arena = new Structure(0x40, "Arena",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().stone(2).ore(1).build());
		Arena.addEffect(new GoldBonus(3, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addEffect(new VictoryPointBonus(1, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addFrequency(3);
		Arena.addFreeBuild(Dispensary);
		structures.add(Arena);
		
		Arena = new Structure(0x40, "Arena",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().stone(2).ore(1).build());
		Arena.addEffect(new GoldBonus(3, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addEffect(new VictoryPointBonus(1, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addFrequency(5);
		Arena.addFreeBuild(Dispensary);
		structures.add(Arena);
		
		Arena = new Structure(0x40, "Arena",Age.THREE, CardColor.YELLOW, 
				new Resources.Builder().stone(2).ore(1).build());
		Arena.addEffect(new GoldBonus(3, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addEffect(new VictoryPointBonus(1, ObjectType.WONDER_STAGE, Adjacency.SELF));
		Arena.addFrequency(7);
		Arena.addFreeBuild(Dispensary);
		structures.add(Arena);
		
		//Red Cards
		Structure Stockade = new Structure(0x41, "Stockade",Age.ONE, CardColor.RED, 
				new Resources.Builder().wood(1).build());
		Stockade.addEffect(new ShieldBonus(1));
		Stockade.addFrequency(3);
		structures.add(Stockade);
		
		Stockade = new Structure(0x41, "Stockade",Age.ONE, CardColor.RED, 
				new Resources.Builder().wood(1).build());
		Stockade.addEffect(new ShieldBonus(1));
		Stockade.addFrequency(7);
		structures.add(Stockade);
		
		Structure Barracks = new Structure(0x42, "Barracks",Age.ONE, CardColor.RED, 
				new Resources.Builder().brick(1).build());
		Barracks.addEffect(new ShieldBonus(1));
		Barracks.addFrequency(3);
		structures.add(Barracks);
		
		Barracks = new Structure(0x42, "Barracks",Age.ONE, CardColor.RED, 
				new Resources.Builder().brick(1).build());
		Barracks.addEffect(new ShieldBonus(1));
		Barracks.addFrequency(5);
		structures.add(Barracks);
		
		Structure GuardTower = new Structure(0x43, "Guard Tower",Age.ONE, CardColor.RED, 
				new Resources.Builder().stone(1).build());
		GuardTower.addEffect(new ShieldBonus(1));
		GuardTower.addFrequency(3);
		structures.add(GuardTower);
		
		GuardTower = new Structure(0x43, "Guard Tower",Age.ONE, CardColor.RED, 
				new Resources.Builder().stone(1).build());
		GuardTower.addEffect(new ShieldBonus(1));
		GuardTower.addFrequency(4);
		structures.add(GuardTower);
		
		Structure Walls = new Structure(0x44, "Walls",Age.TWO, CardColor.RED, 
				new Resources.Builder().stone(3).build());
		Walls.addEffect(new ShieldBonus(2));
		Walls.addFrequency(3);
		structures.add(Walls);
		
		Walls = new Structure(0x44, "Walls",Age.TWO, CardColor.RED, 
				new Resources.Builder().stone(3).build());
		Walls.addEffect(new ShieldBonus(2));
		Walls.addFrequency(7);
		structures.add(Walls);
		
		Structure TrainingGround = new Structure(0x45, "Training Ground",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(1).ore(2).build());
		TrainingGround.addEffect(new ShieldBonus(2));
		TrainingGround.addFrequency(4);
		structures.add(TrainingGround);
		
		TrainingGround = new Structure(0x45, "Training Ground",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(1).ore(2).build());
		TrainingGround.addEffect(new ShieldBonus(2));
		TrainingGround.addFrequency(6);
		structures.add(TrainingGround);
		
		TrainingGround = new Structure(0x45, "Training Ground",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(1).ore(2).build());
		TrainingGround.addEffect(new ShieldBonus(2));
		TrainingGround.addFrequency(7);
		structures.add(TrainingGround);
		
		Structure Fortifications = new Structure(0x46, "Fortifications Tower",Age.THREE, CardColor.RED, 
				new Resources.Builder().stone(1).ore(3).build());
		Fortifications.addEffect(new ShieldBonus(3));
		Fortifications.addFrequency(3);
		Fortifications.addFreeBuild(Walls);
		structures.add(Fortifications);
		
		Fortifications = new Structure(0x46, "Fortifications Tower",Age.THREE, CardColor.RED, 
				new Resources.Builder().stone(1).ore(3).build());
		Fortifications.addEffect(new ShieldBonus(3));
		Fortifications.addFrequency(7);
		Fortifications.addFreeBuild(Walls);
		structures.add(Fortifications);
		
		Structure Circus = new Structure(0x47, "Circus",Age.THREE, CardColor.RED, 
				new Resources.Builder().stone(3).ore(1).build());
		Circus.addEffect(new ShieldBonus(3));
		Circus.addFrequency(4);
		Circus.addFreeBuild(TrainingGround);
		structures.add(Circus);
		
		Circus = new Structure(0x47, "Circus",Age.THREE, CardColor.RED, 
				new Resources.Builder().stone(3).ore(1).build());
		Circus.addEffect(new ShieldBonus(3));
		Circus.addFrequency(5);
		Circus.addFreeBuild(TrainingGround);
		structures.add(Circus);
		
		Circus = new Structure(0x47, "Circus",Age.THREE, CardColor.RED, 
				new Resources.Builder().stone(3).ore(1).build());
		Circus.addEffect(new ShieldBonus(3));
		Circus.addFrequency(6);
		Circus.addFreeBuild(TrainingGround);
		structures.add(Circus);
		
		Structure Arsenal = new Structure(0x48, "Arsenal",Age.THREE, CardColor.RED, 
				new Resources.Builder().wood(2).ore(1).papyrus(1).build());
		Arsenal.addEffect(new ShieldBonus(3));
		Arsenal.addFrequency(3);
		structures.add(Arsenal);
		
		Arsenal = new Structure(0x48, "Arsenal",Age.THREE, CardColor.RED, 
				new Resources.Builder().wood(2).ore(1).papyrus(1).build());
		Arsenal.addEffect(new ShieldBonus(3));
		Arsenal.addFrequency(4);
		structures.add(Arsenal);
		
		Arsenal = new Structure(0x48, "Arsenal",Age.THREE, CardColor.RED, 
				new Resources.Builder().wood(2).ore(1).papyrus(1).build());
		Arsenal.addEffect(new ShieldBonus(3));
		Arsenal.addFrequency(7);
		structures.add(Arsenal);
		
		Structure Stables = new Structure(0x49, "Stables",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(1).brick(1).ore(1).build());
		Stables.addEffect(new ShieldBonus(2));
		Stables.addFrequency(3);
		Stables.addFreeBuild(Apothecary);
		structures.add(Stables);
		
		Stables = new Structure(0x49, "Stables",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(1).brick(1).ore(1).build());
		Stables.addEffect(new ShieldBonus(2));
		Stables.addFrequency(5);
		Stables.addFreeBuild(Apothecary);
		structures.add(Stables);
		
		Structure ArcheryRange = new Structure(0x4A, "Archery Range",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(2).ore(1).build());
		ArcheryRange.addEffect(new ShieldBonus(2));
		ArcheryRange.addFrequency(3);
		ArcheryRange.addFreeBuild(Workshop);
		structures.add(ArcheryRange);
		
		ArcheryRange = new Structure(0x4A, "Archery Range",Age.TWO, CardColor.RED, 
				new Resources.Builder().wood(2).ore(1).build());
		ArcheryRange.addEffect(new ShieldBonus(2));
		ArcheryRange.addFrequency(6);
		ArcheryRange.addFreeBuild(Workshop);
		structures.add(ArcheryRange);
		
		Structure SiegeWorkshop = new Structure(0x4B, "Siege Workshop",Age.THREE, CardColor.RED, 
				new Resources.Builder().wood(1).brick(3).build());
		SiegeWorkshop.addEffect(new ShieldBonus(3));
		SiegeWorkshop.addFrequency(3);
		SiegeWorkshop.addFreeBuild(Laboratory);
		structures.add(SiegeWorkshop);
		
		SiegeWorkshop = new Structure(0x4B, "Siege Workshop",Age.THREE, CardColor.RED, 
				new Resources.Builder().wood(1).brick(3).build());
		SiegeWorkshop.addEffect(new ShieldBonus(3));
		SiegeWorkshop.addFrequency(5);
		SiegeWorkshop.addFreeBuild(Laboratory);
		structures.add(SiegeWorkshop);
	
	}
	
	/*
	private void loadLeaders() {
		
		Structure ArchitectsGuild = new Structure(0x01, "Architects Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().brick(1).ore(3).loom(1).papyrus(1).build());
		ArchitectsGuild.addEffect(new VictoryPointBonus(3, ObjectType.PURPLE_CARD, Adjacency.LEFT));
		ArchitectsGuild.addEffect(new VictoryPointBonus(3, ObjectType.PURPLE_CARD, Adjacency.RIGHT));
		structures.add(ArchitectsGuild);
		
		Structure CourtesansGuild = new Structure(0x02, "Courtesans Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().wood(1).brick(1).glass(1).loom(1).build());
		CourtesansGuild.addEffect(new CopyLeader());
		structures.add(CourtesansGuild);
		
		Structure DiplomatsGuild = new Structure(0x03, "Diplomats Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().wood(1).stone(1).glass(1).papyrus(1).build());
		DiplomatsGuild.addEffect(new VictoryPointBonus(1, ObjectType.LEADER, Adjacency.LEFT));
		DiplomatsGuild.addEffect(new VictoryPointBonus(1, ObjectType.LEADER, Adjacency.RIGHT));
		structures.add(DiplomatsGuild);
		
		Structure GamersGuild = new Structure(0x04, "Gamers Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().wood(1).stone(1).brick(1).ore(1).build());
		GamersGuild.addEffect(new VictoryPointBonus(1, ObjectType.THREE_GOLD, Adjacency.SELF));
		structures.add(GamersGuild);
		
	}
	
	private void loadCities() {
		
		// Black Cards
		Structure ArchitectGuild = new Structure(0x01, "Architect Guild",Age.TWO, CardColor.BLACK,
				new Resources.Builder().gold(1).papyrus(1).build());
		ArchitectGuild.addEffect(new VictoryPointBonus(2));
		ArchitectGuild.addEffect(new FreeWonderStage());
		structures.add(ArchitectGuild);
		
		Structure BlackMarket = new Structure (0x02, "Black Market",Age.TWO, CardColor.BLACK,
				new Resources.Builder().ore(1).loom(1).build());
		BlackMarket.addEffect(new FreeMissingResource());
		structures.add(BlackMarket);
		
		Structure Brotherhood = new Structure(0x03, "Brotherhood",3, CardColor.BLACK,
				new Resources.Builder().wood(2).ore(1).loom(1).build());
		Brotherhood.addEffect(new VictoryPointBonus(4));
		Brotherhood.addEffect(new PayGold(2));
		structures.add(Brotherhood);
		
		Structure BuildersUnion = new Structure(0x04, "Builder's Union",Age.THREE, CardColor.BLACK,
				new Resources.Builder().wood(1).brick(1).glass(1).papyrus(1).build());
		BuildersUnion.addEffect(new VictoryPointBonus(4));
		BuildersUnion.addEffect(new PayGolds(ObjectType.WONDER_STAGE));
		structures.add(BuildersUnion);

		Structure Capitol = new Structure(0x05, "Capitol",Age.THREE, CardColor.BLACK,
				new Resources.Builder().gold(2).stone(2).brick(2).glass(1).papyrus(1).build());
		Capitol.addEffect(new VictoryPointBonus(8));
		structures.add(Capitol);

		Structure Cenotaph = new Structure(0x06, "Cenotaph",Age.THREE, CardColor.BLACK,
				new Resources.Builder().stone(1).brick(2).glass(1).loom(1).build());
		Cenotaph.addEffect(new VictoryPointBonus(5));
		Cenotaph.addEffect(new PayGold(ObjectType.VICTORY_TOKEN));
		structures.add(Cenotaph);

		Structure ClandestineDockEast = new Structure(0x07, "ClandestineDockEast",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(1).build());
		ClandestineDockEast.addEffect(new TradeBonus(Adjacency.RIGHT));
		structures.add(ClandestineDockEast);

		Structure ClandestineDockWest = new Structure(0x08, "ClandestineDockWest",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(1).build());
		ClandestineDockWest.addEffect(new TradeBonus(Adjacency.LEFT));
		structures.add(ClandestineDockWest);

		Structure Consulate = new Structure(0x09, "Consulate",Age.TWO, CardColor.BLACK,
				new Resources.Builder().brick(1).papyrus(1).build());
		Consulate.addEffect(new VictoryPointBonus(2));
		Consulate.addEffect(new Peace());
		structures.add(Consulate);

		Structure Contigent = new Structure(0x0A, "Contigent",Age.THREE, CardColor.BLACK,
				new Resources.Builder().gold(5).papyrus(1).build());
		Contigent.addEffect(new ShieldBonus(5));
		structures.add(Contigent);

		Structure Embassy = new Structure(0x0B, "Embassy",Age.THREE, CardColor.BLACK,
				new Resources.Builder().stone(1).loom(1).papyrus(1).build());
		Embassy.addEffect(new VictoryPointBonus(3));
		Embassy.addEffect(new Peace());
		structures.add(Embassy);

		Structure GamblingDen = new Structure(0x0C, "Gambling Den",Age.ONE, CardColor.BLACK,
				new Resources.Builder().build());
		Embassy.addEffect(new GoldBonus(1,6));
		structures.add(GamblingDen);

		Structure GamblingHouse = new Structure(0x0D, "Gambling House",Age.TWO, CardColor.BLACK,
				new Resources.Builder().gold(1).build());
		GamblingHouse.addEffect(new GoldBonus(2,9));
		structures.add(GamblingHouse);

		Structure GatesoftheCity = new Structure(0x0E, "Gates of the City",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(1).wood(1).build());
		GatesoftheCity.addEffect(new VictoryPointBonus(4));
		structures.add(GatesoftheCity);

		Structure Hideout = new Structure(0x0F, "Hideout",Age.ONE, CardColor.BLACK,
				new Resources.Builder().build());
		Hideout.addEffect(new VictoryPointBonus(2));
		Hideout.addEffect(new PayGold(1));
		structures.add(Hideout);

		Structure Lair = new Structure(0x10, "Lair",Age.TWO, CardColor.BLACK,
				new Resources.Builder().wood(1).glass(1).build());
		Lair.addEffect(new VictoryPointBonus(3));
		Lair.addEffect(new PayGold(2));
		structures.add(Lair);

		Structure Mercenaries = new Structure(0x11, "Mercenaries",Age.TWO, CardColor.BLACK,
				new Resources.Builder().gold(4).papyrus(1).build());
		Mercenaries.addEffect(new ShieldBonus(3));
		structures.add(Mercenaries);

		Structure Militia = new Structure(0x12, "Militia",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(3).build());
		Militia.addEffect(new ShieldBonus(2));
		structures.add(Militia);

		Structure PigeonLoft = new Structure(0x13, "Pigeon Loft",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(1).ore(1).build());
		PigeonLoft.addEffect(new CopyScience());
		structures.add(PigeonLoft);

		Structure Residence = new Structure(0x14, "Residence",Age.ONE, CardColor.BLACK,
				new Resources.Builder().brick(1).build());
		Residence.addEffect(new VictoryPointBonus(1));
		Residence.addEffect(new Peace());
		structures.add(Residence);

		Structure SecretSociety = new Structure(0x15, "Secret Society",Age.THREE, CardColor.BLACK,
				new Resources.Builder().stone(1).papyrus(1).build());
		SecretSociety.addEffect(new VictoryPointBonus(1, ObjectType.BLACK_CARD, Adjacency.SELF));
		SecretSociety.addEffect(new GoldBonus(1, ObjectType.BLACK_CARD, Adjacency.SELF));
		structures.add(SecretSociety);

		Structure SecretWarehouse = new Structure(0x16, "Secret Warehouse",Age.ONE, CardColor.BLACK,
				new Resources.Builder().gold(2).build());
		SecretWarehouse.addEffect(new CopyResource());
		structures.add(SecretWarehouse);

		Structure Sepulcher = new Structure(0x17, "Sepulcher",Age.TWO, CardColor.BLACK,
				new Resources.Builder().stone(1).glass(1).loom(1).build());
		Sepulcher.addEffect(new VictoryPointBonus(4));;
		Sepulcher.addEffect(new PayGold(ObjectType.VICTORY_TOKEN));
		structures.add(Sepulcher);

		Structure SlaveMarket = new Structure(0x18, "Slave Market",Age.THREE, CardColor.BLACK,
				new Resources.Builder().wood(2).ore(2).build());
		SlaveMarket.addEffect(new VictoryPointBonus(1, ObjectType.VICTORY_TOKEN, Adjacency.SELF));
		structures.add(SlaveMarket);

		Structure SpyRing = new Structure(0x19, "Spy Ring",Age.TWO, CardColor.BLACK,
				new Resources.Builder().gold(2).stone(1).brick(1).build());
		SpyRing.addEffect(new CopyScience());
		structures.add(SpyRing);

		Structure Tabularium = new Structure(0x1A, "Tabularium",Age.TWO, CardColor.BLACK,
				new Resources.Builder().gold(2).wood(1).ore(1).loom(1).build());
		Tabularium.addEffect(new VictoryPointBonus(6));
		structures.add(Tabularium);

		Structure TortureChamber = new Structure(0x1B,"Torture Chamber",Age.THREE, CardColor.BLACK,
				new Resources.Builder().gold(3).ore(2).glass(1).build());
		TortureChamber.addEffect(new CopyScience());
		structures.add(TortureChamber);

		// Purple Cards
		Structure CounterfeitersGuild = new Structure(0x1C, "Counterfeiters Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().ore(3).glass(1).loom(1).build());
		CounterfeitersGuild.addEffect(new VictoryPointBonus(5));
		CounterfeitersGuild.addEffect(new PayGold(3));
		structures.add(CounterfeitersGuild);

		Structure GuildofShadows = new Structure(0x1D, "Guild of Shadows",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().wood(1).stone(2).papyrus(1).build());
		GuildofShadows.addEffect(new VictoryPointBonus(1, ObjectType.BLACK_CARD, Adjacency.LEFT));
		GuildofShadows.addEffect(new VictoryPointBonus(1, ObjectType.BLACK_CARD, Adjacency.RIGHT));
		structures.add(GuildofShadows);

		Structure MournersGuild = new Structure(0x1E, "Mourner's Guild",Age.THREE, CardColor.PURPLE,
				new Resources.Builder().wood(1).brick(2).glass(1).loom(1).build());
		MournersGuild.addEffect(new VictoryPointBonus(1, ObjectType.VICTORY_TOKEN, Adjacency.LEFT));
		MournersGuild.addEffect(new VictoryPointBonus(1, ObjectType.VICTORY_TOKEN, Adjacency.RIGHT));
		structures.add(MournersGuild);

	}
	
	*/
	
	public ArrayList<Structure> getStructures() {  return structures;  }
	public ArrayList<Structure> getGuilds()     {  return guilds;      }
	
}