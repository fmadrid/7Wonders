package Strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import Player.Player;
import Player.ResourcesManager;
import SevenWonders.Constants.Action;
import Stage.Stage;
import Structure.Structure;

public class Learner implements Strategy {

	private static final Logger logger = Logger.getLogger("myLogger");

	Random random;
	Player               pl;
	ResourcesManager     resources;
	ArrayList<Structure> hand;
	ArrayList<Structure> playable;
	ArrayList<Integer>   values;
	boolean              free;

	public Learner(Player pl, long seed, boolean free, boolean canDiscard) {

		random    = new Random(seed);
		this.pl   = pl;
		resources = pl.getResourcesManager();
		hand      = pl.getHand();
		this.free = free;
		playable  = getPlayableCards();
		values = new ArrayList<Integer>();
		
		try {
			
			File f = new File("Values.txt");
		
			FileReader inputFile = new FileReader(f);
			BufferedReader input = new BufferedReader(inputFile);
			
			String s = input.readLine();
			
			while(s != null) {
				values.add(Integer.parseInt(s));
				s = input.readLine();
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){}
		if(!playable.isEmpty())
			selectCard();
		else
			if(canDiscard)
				selectStage();

		if(pl.getAction() == null && canDiscard)
			discardCard();

	}

	private ArrayList<Structure> getPlayableCards() {

		ArrayList<Structure> temp = new ArrayList<Structure>();

		for(Structure s : hand)
			if((free || resources.canAfford(s.getResourceCost()) || pl.hasStructure(s.getFreeBuild()) || pl.canFreeBuild()) && !pl.hasStructure(s))
				temp.add(s);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("[RANDOMCARD] Player [%s] can play the following structures:\n", pl.getName()));

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());

		return temp;

	}

	private void selectCard() {

		Structure s = playable.get(0);

		for(Structure t : playable)
			if(values.get(t.getID()-1) > values.get(s.getID()-1))
				s = t;

		pl.setStructure(s);
		
		pl.setAction(Action.CONSTRUCT_STRUCTURE);

	}

	private void selectStage() {

		Stage s = pl.getWonderboard().getNextStage();

		if(s != null) {

			if(resources.canAfford(s.getResourceCost())) {

				Collections.shuffle(playable,random);
				pl.setStructure(hand.get(0));
				pl.setAction(Action.CONSTRUCT_STAGE);

				if(s.getResourceCost().getGold() != 0)
					pl.getResourcesManager().loseGold(s.getResourceCost().getGold());


			}

		}

	}

	private void discardCard() {

		Collections.shuffle(playable,random);
		pl.setStructure(hand.get(0));
		pl.setAction(Action.DISCARD_CARD);

	}

	public void run() {


	}

}
