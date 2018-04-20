/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import SevenWonders.Constants.Adjacency;
import SevenWonders.Constants.Trade;
import Tokens.Resources;

public class ResourcesManager {
	
	private static final Logger logger = Logger.getLogger("myLogger");
	
	private Resources staticResources;
	private ArrayList<Resources> dynamicResources;
	private ArrayList<Resources> combinations;
	
	private Neighbor left;
	private Neighbor right;
	
	public class Neighbor {
		
		private Resources resources;
		private int       rawPrice;
		private int       manufacturedPrice;
		
		public Neighbor() {
			
			resources         = new Resources.Builder().build();
			rawPrice          = 2;
			manufacturedPrice = 2;
						
		}
		
		public Resources getResources()            {  return resources;          }
		public int       getRawPrice()             {  return rawPrice;           } 
		public int       getManufacturedPrice()    {  return manufacturedPrice;  }
		public void      addResources(Resources r) {  resources.add(r);          }
		
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Raw: [%d] Manufactured: [%d]",rawPrice, manufacturedPrice));
			
			return sb.toString();
		}
	}
	
	public ResourcesManager() {
		
		staticResources  = new Resources.Builder().build();
		dynamicResources = new ArrayList<Resources>();
		combinations     = new ArrayList<Resources>();
		combinations.add(new Resources.Builder().build());
		
		left  = new Neighbor();
		right = new Neighbor();
		
	}

	public void addDiscount(Trade t, Adjacency a) {
		
		switch(t) {
		
			case RAW_DISCOUNT :
				
				if(a == Adjacency.LEFT)
					left.rawPrice = 1;
				
				if(a == Adjacency.RIGHT)
					right.rawPrice = 1;
				
				break;
					
			case MANUFACTURED_DISCOUNT:
				
				if(a == Adjacency.LEFT)
					left.manufacturedPrice = 1;
				
				if(a == Adjacency.RIGHT)
					right.manufacturedPrice = 1;
				
				break;
			
		}
		
	}

	public ArrayList<Resources> getCombinations()     {  return combinations;      }
	public Neighbor             getLeftNeighbor()     {  return left;              }
	public Neighbor             getRightNeighbor()    {  return right;             }
	
	public void addResources(Resources r, boolean c) {
		
		if(c) {	
			dynamicResources.add(r);
			generateCombinations(r);
		}
		else {	
			staticResources.add(r);
			for(Resources s : combinations)
				s.add(r);
		}
		
		for(Resources x : combinations)
			logger.log(Level.FINE, "\t" + x.toString() + "\n");
		
	}
	
	public void addResources(Adjacency a, Resources r) {
		//MUST UPDATE
		switch(a) {
		
			case LEFT :
				
				getLeftNeighbor().addResources(r);
				break;
				
			case RIGHT :
				
				getRightNeighbor().addResources(r);
				break;
				
			default :
			
		}
		
	}
	
	private void generateCombinations(Resources r) {
		
		ArrayList<Resources> tempList = new ArrayList<Resources>();
		
		while(!r.isEmpty()) {
			
			if(r.getWood() != 0) {
				
				r.remove(new Resources.Builder().wood(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().wood(1).build()));
				continue;
				
			}
			
			if(r.getStone() != 0) {
				
				r.remove(new Resources.Builder().stone(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().stone(1).build()));
				continue;
				
			}
			
			if(r.getBrick() != 0) {
				
				r.remove(new Resources.Builder().brick(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().brick(1).build()));
				continue;
				
			}
			
			if(r.getOre() != 0) {
				
				r.remove(new Resources.Builder().ore(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().ore(1).build()));
				continue;
				
			}
			
			if(r.getGlass() != 0) {
				
				r.remove(new Resources.Builder().glass(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().glass(1).build()));
				continue;
				
			}
			
			if(r.getLoom() != 0) {
				
				r.remove(new Resources.Builder().loom(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().loom(1).build()));
				continue;
				
			}
			
			if(r.getPapyrus() != 0) {
				
				r.remove(new Resources.Builder().papyrus(1).build());
				for(Resources s : combinations)
					tempList.add(s.sum(new Resources.Builder().papyrus(1).build()));
				continue;
				
			}
			
		}
		
		combinations = tempList;
		
	}

	public boolean canAfford(Resources r) {
		
		for(Resources c : combinations)
			if(r.subtract(c).isEmpty())
				return true;
		
		for(Resources c : combinations)
			if(r.subtract(c.sum(left.getResources())).isEmpty())
				if(c.getGold() >= (r.subtract(c).cardinalityRaw() * left.getRawPrice() + r.subtract(c).cardinalityManufactured() * left.getManufacturedPrice()))
					return true;

		for(Resources c : combinations)
			if(r.subtract(c.sum(right.getResources())).isEmpty())
				if(c.getGold() >= (r.subtract(c).cardinalityRaw() * right.getRawPrice() + r.subtract(c).cardinalityManufactured() * right.getManufacturedPrice()))
					return true;

		return false;
		
	}
	
	
	public void loseGold(int g) {
		
		if(g == 0)
			return;
		
		logger.log(Level.FINE, String.format("[RESOURCESMANAGER] Lost [%d] gold.\n", g));
		
		staticResources.spendGold(g);
		
		for(Resources c : combinations)
			c.spendGold(g);
		
	}
	
	public boolean canProduce(Resources r) {
		
		if(r.getWood() != 0 && staticResources.getWood() == 0)
			return true;
		
		if(r.getWood() != 0)
			for(Resources s : dynamicResources)
				if(s.getWood() != 0)
					return false;
		
		if(r.getStone() != 0 && staticResources.getStone() == 0)
			return true;
		
		if(r.getStone() != 0)
			for(Resources s : dynamicResources)
				if(s.getStone() != 0)
					return false;
		
		if(r.getOre() != 0 && staticResources.getOre() == 0)
			return true;
		
		if(r.getOre() != 0)
			for(Resources s : dynamicResources)
				if(s.getOre() != 0)
					return false;
		
		if(r.getBrick() != 0 && staticResources.getBrick() == 0)
			return true;
		
		if(r.getBrick() != 0)
			for(Resources s : dynamicResources)
				if(s.getBrick() != 0)
					return false;
		
		if(r.getGlass() != 0 && staticResources.getGlass() == 0)
			return true;
		
		if(r.getGlass() != 0)
			for(Resources s : dynamicResources)
				if(s.getGlass() != 0)
					return false;
		
		if(r.getLoom() != 0 && staticResources.getLoom() == 0)
			return true;
		
		if(r.getLoom() != 0)
			for(Resources s : dynamicResources)
				if(s.getLoom() != 0)
					return false;
		
		if(r.getPapyrus() != 0 && staticResources.getPapyrus() == 0)
			return true;
		
		if(r.getPapyrus() != 0)
			for(Resources s : dynamicResources)
				if(s.getPapyrus() != 0)
					return false;
		
		return true;
			
	}
	
}
