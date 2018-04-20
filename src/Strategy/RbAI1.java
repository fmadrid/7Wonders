package Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import Effect.Effect;
import Effect.ResourceBonus;
import Effect.ShieldBonus;
import Effect.VictoryPointBonus;
import Player.Player;
import Player.ResourcesManager;
import SevenWonders.Constants.Action;
import SevenWonders.Constants.CardColor;
import SevenWonders.Constants.EffectType;
import Stage.Stage;
import Structure.Structure;
import Tokens.Resources;

public class RbAI1 implements Strategy {

	private static final Logger logger = Logger.getLogger("myLogger");
	
	Random random;
	Player               pl;
	ResourcesManager     resources;
	ArrayList<Structure> hand;
	ArrayList<Structure> playable;
	boolean              free;

	public RbAI1(Player pl, long seed, boolean free, boolean canDiscard) {

		random    = new Random(seed);
		this.pl   = pl;
		resources = pl.getResourcesManager();
		hand      = pl.getHand();
		this.free = free;
		playable  = getPlayableCards();


		if(!playable.isEmpty())
			selectCard();
		else
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
		sb.append(String.format("[RBAI1] Player [%s] can play the following structures:\n", pl.getName()));

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());
		
		return temp;

	}

	private void selectCard() {

		if(!ruleOne().isEmpty())
			return;

		if(!ruleTwo().isEmpty())
			return;

		if(!ruleThree().isEmpty())
			return;

		if(ruleFour() != null)
			return;

		if(!ruleFive().isEmpty())
			return;
		
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

	private void playCard(Structure s) {

		pl.setStructure(s);
		pl.setAction(Action.CONSTRUCT_STRUCTURE);

		if(playable.get(0).getResourceCost().getGold() != 0)
			pl.getResourcesManager().loseGold(playable.get(0).getResourceCost().getGold());

	}

	private ArrayList<Structure> ruleOne() {

		ArrayList<Structure >temp = new ArrayList<Structure>();

		for(Structure s : playable)
			if(s.getColor() == CardColor.BROWN || s.getColor() == CardColor.YELLOW)
				for(Effect e : s.getEffects())
					if(e.getEffectType() == EffectType.RESOURCE_BONUS)
						if(((ResourceBonus) e).getResourceBonus().cardinalityRaw() > 1)
							temp.add(s);

		StringBuilder sb = new StringBuilder();
		sb.append("[RBAI1] Rule One:\n");

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());
		
		if(!temp.isEmpty()) {

			Collections.shuffle(temp,random);
			playCard(temp.get(0));
			
		}
		
		return temp;

	}

	private ArrayList<Structure> ruleTwo() {
		
		ArrayList<Structure> temp = new ArrayList<Structure>();
		for(Structure s : playable)
			if(s.getColor() == CardColor.BROWN || s.getColor() == CardColor.GRAY)
				for(Effect e : s.getEffects())
					if(e.getEffectType() == EffectType.RESOURCE_BONUS) {
						Resources r = ((ResourceBonus) e).getResourceBonus();
						if(!resources.canProduce(r) 
								&& !r.subtract(resources.getLeftNeighbor().getResources()).isEmpty() 
								&& !r.subtract(resources.getRightNeighbor().getResources()).isEmpty());
							temp.add(s);

					}

		StringBuilder sb = new StringBuilder();
		sb.append("[RBAI1] Rule Two:\n");

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());
		
		if(!temp.isEmpty()) {

			Collections.shuffle(temp,random);
			playCard(temp.get(0));

		}
		
		return temp;
		
	}
	
	private ArrayList<Structure> ruleThree() {
		
		ArrayList<Structure> temp = new ArrayList<Structure>();
		for(Structure s : playable)
			if(s.getColor() == CardColor.RED)
				for(Effect e : s.getEffects())
					if(e.getEffectType() == EffectType.SHIELD_BONUS) {

						int shields = ((ShieldBonus) e).getShieldBonus();
						Player left = pl.getLeftNeighbor();
						Player right = pl.getRightNeighbor();

						if((pl.getShields() < left.getShields() || pl.getShields() < right.getShields()) 
								&& (pl.getShields() + shields > left.getShields() && (pl.getShields() + shields > right.getShields())))
							temp.add(s);

					}

		StringBuilder sb = new StringBuilder();
		sb.append("[RBAI1] Rule Three:\n");

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());
		if(!temp.isEmpty()) {

			Collections.shuffle(temp,random);
			playCard(temp.get(0));

		}
		
		return temp;
		
	}
	
	private Structure ruleFour() {
		
		Structure max       = null;
		int       maxPoints = 0;

		for(Structure s : playable) {

			int sum = 0;

			if(s.getColor() == CardColor.BLUE || s.getColor() == CardColor.YELLOW ||s.getColor() == CardColor.PURPLE)
				for(Effect e : s.getEffects()) {

					if(e.getEffectType() == EffectType.VICTORY_POINT_BONUS) {

						int points = ((VictoryPointBonus) e).getPointBonus(pl);
						sum += points;

					}

				}

			if(sum > maxPoints) {
				maxPoints = sum;
				max = s;
			}

		}

		StringBuilder sb = new StringBuilder();
		sb.append("[RBAI1] Rule Four:\n");
		
		if(max != null)
			sb.append(String.format("\t%s\n",max.getName()));

		logger.log(Level.FINE, sb.toString());
		
		if(max != null) 
			playCard(max);

		
		return max;
		
	}
	
	private ArrayList<Structure> ruleFive() {
		
		ArrayList<Structure> temp = new ArrayList<Structure>();
		for(Structure s : playable)
			if(s.getColor() == CardColor.GREEN)
				temp.add(s);

		StringBuilder sb = new StringBuilder();
		sb.append("[RBAI1] Rule Five:\n");

		for(Structure s : temp) 
			sb.append(String.format("\t%s\n",s.getName()));

		logger.log(Level.FINE, sb.toString());
		if(!temp.isEmpty()) {

			Collections.shuffle(temp,random);
			playCard(temp.get(0));

		}
		
		return temp;
		
	}
	
}