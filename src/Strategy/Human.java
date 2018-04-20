package Strategy;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Player.Player;
import Player.ResourcesManager;
import SevenWonders.Constants.Action;
import Stage.Stage;
import Structure.Structure;
import Tokens.Resources;
import java.util.Scanner;


public class Human implements Strategy {

	private static final Logger logger = Logger.getLogger("myLogger");

	Player               pl;
	ResourcesManager     resources;
	ArrayList<Structure> hand;
	ArrayList<Structure> playable;
	boolean              free;
	boolean              canDiscard;

	public Human(Player pl, long seed, boolean free, boolean canDiscard) {

		this.pl   = pl;
		resources = pl.getResourcesManager();
		hand      = pl.getHand();
		this.free = free;
		this.canDiscard = canDiscard;
		playable  = getPlayableCards(false);

		printState();
		printActions();

	}

	private ArrayList<Structure> getPlayableCards(boolean b) {

		ArrayList<Structure> temp = new ArrayList<Structure>();

		for(Structure s : hand)
			if((b || resources.canAfford(s.getResourceCost()) || pl.hasStructure(s.getFreeBuild()) || pl.canFreeBuild()) && !pl.hasStructure(s))
				temp.add(s);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("[RANDOMCARD] Player [%s] can play the following structures:\n", pl.getName()));

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());

		return temp;

	}

	private void selectCard() {

		Structure s = null;
		Scanner keyboard = new Scanner(System.in);
		while(s == null) {
			
			System.out.println("Choose a card: ");
			
			int input = keyboard.nextInt();
			
			if(input > 0 && input <= hand.size())
				if((pl.getAction() == Action.CONSTRUCT_STRUCTURE && playable.contains(hand.get(input-1))) || pl.getAction() != Action.CONSTRUCT_STRUCTURE )
				s = hand.get(input-1);
			
			if(pl.getAction() == Action.CONSTRUCT_STAGE)
				selectStage(s);
			
			if(pl.getAction() == Action.CONSTRUCT_STRUCTURE)
				playCard(s);
			
			if(pl.getAction() == Action.DISCARD_CARD)
				discardCard(s);
		}

	}

	private void selectStage(Structure q) {

		Stage s = pl.getWonderboard().getNextStage();

		if(s != null) {

			if(resources.canAfford(s.getResourceCost())) {

				pl.setStructure(q);

				if(s.getResourceCost().getGold() != 0)
					pl.getResourcesManager().loseGold(s.getResourceCost().getGold());


			}

		}

	}

	private void discardCard(Structure s) {

		pl.setStructure(s);

	}

	public void run() {


	}

	private void printState() {
		
		System.out.println("-----STATE-----");
		System.out.println(String.format("Player: [%s]", pl.getName()));
		System.out.println(String.format("Wonderboard: [%s]", pl.getWonderboard().toString()));
		
		System.out.println("Built Structures: ");
		for(Structure s : pl.getBuiltStructures())
			System.out.println("\t" + s.toString());
		
		System.out.println("Resources: ");
		for(Resources r : resources.getCombinations())
			System.out.println("\t" + r.toString());
		
		int i = 1;
		System.out.println("Hand: ");
		for(Structure s : hand) {
			
			if(playable.contains(s))
				System.out.print("\tPLAY");
			else
				System.out.print("\t");
			System.out.println("\t" + "[" + new Integer(i++).toString() + "] " + s.toString());
		}
		
		System.out.println("Next Stage:");
		
		Stage s = pl.getWonderboard().getNextStage();

		if(s != null) {
			if(resources.canAfford(s.getResourceCost()))
				System.out.print("\tPLAY");
			else
				System.out.print("\t");
			
			System.out.println("\t" + s.toString());
			
		}
		
	}

	private void printActions() {
		
		boolean action1 = false;
		boolean action2 = false;
		boolean action3 = false;
		
		System.out.println("-----ACTION-----");
		
		if(!playable.isEmpty()) {
			System.out.println("[1] CONSTRUCT STRUCTURE");
			action1 = true;
		}
		else
			System.out.println("[X] CONSTRUCT STRUCTURE");
		Stage s = pl.getWonderboard().getNextStage();

		if(s != null)
			if(resources.canAfford(s.getResourceCost())) {
				System.out.println("[2] CONSTRUCT WONDER");
				action2 = true;
			}
			else
				System.out.println("[X] CONSTRUCT WONDER");
		else
			System.out.println("[X] CONSTRUCT WONDER");
		
		if(canDiscard)
			System.out.println("[3] DISCARD");
		else
			System.out.println("[X] DISCARD");
		
		if(free)
			System.out.println("[4] Free Build");
		
		Scanner keyboard = new Scanner(System.in);
		
		while(pl.getSelectedStructure() == null) {
			
			System.out.println("Choose an Action: ");
			
			int input = keyboard.nextInt();
			System.out.println(input);
			switch(input) {
			
				case 1 :
					if(!action1)
						break;
					pl.setAction(Action.CONSTRUCT_STRUCTURE);
					selectCard();
					break;
				case 2 :
					if(!action2)
						break;
					pl.setAction(Action.CONSTRUCT_STAGE);
					selectCard();
					break;
					
				case 3 :
					if(!canDiscard)
						break;
					pl.setAction(Action.DISCARD_CARD);
					selectCard();
					break;
					
				case 4 :
					if(!free)
						break;
					playable = getPlayableCards(free);
					pl.setAction(Action.CONSTRUCT_STRUCTURE);
					selectCard();
				default : break;
				
			}
			
		}
		
		
	}

	private void playCard(Structure s) {

		pl.setStructure(s);

	}

}
