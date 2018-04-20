package Strategy;

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

public class RandomCard implements Strategy {

	private static final Logger logger = Logger.getLogger("myLogger");

	Random random;
	Player               pl;
	ResourcesManager     resources;
	ArrayList<Structure> hand;
	ArrayList<Structure> playable;
	boolean              free;

	public RandomCard(Player pl, long seed, boolean free, boolean canDiscard) {

		random    = new Random(seed);
		this.pl   = pl;
		resources = pl.getResourcesManager();
		hand      = pl.getHand();
		this.free = free;
		playable  = getPlayableCards();


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

		Collections.shuffle(playable,random);

		pl.setStructure(playable.get(0));
		
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
