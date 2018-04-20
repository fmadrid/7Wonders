/**
 * @author Frank Madrid
 * @purpse CMPS 490 - Artificial Intelligence for Modern Boardgames: Designing an Intelligent Agent for the Boardgame '7 Wonders'
 */

package Tokens;

/**
 * 	Object representation of resource costs, resources owned by players, and resources granted by
 * 	structures or stages. The eight resource types are categorized by gold, raw materials and
 * 	manufactured goods.
 *
 */
public class Resources {

	private int gold, wood, stone, brick, ore, glass, loom, papyrus;
	
	/** 
	 * 	Builder pattern for object <i>Resource</i>. All attributes are optional and defaulted to zero
	 */
	public static class Builder {
		
		private int gold, wood, stone, brick, ore, glass, loom, papyrus = 0;
		
		public Builder() {}
		
		public Builder gold(int value)    {  gold    = value;   return this;  }
		public Builder wood(int value)    {  wood    = value;   return this;  }
		public Builder stone(int value)   {  stone   = value;   return this;  }
		public Builder brick(int value)   {  brick   = value;   return this;  }
		public Builder ore(int value)     {  ore     = value;   return this;  }
		public Builder glass(int value)   {  glass   = value;   return this;  }
		public Builder loom(int value)    {  loom    = value;   return this;  }
		public Builder papyrus(int value) {  papyrus = value;   return this;  }

		public Resources build() {  return new Resources(this);  }
		
	}

	public Resources(Builder b) {
		
		gold    = b.gold;
		wood    = b.wood;
		stone   = b.stone;
		brick   = b.brick;
		ore     = b.ore;
		glass   = b.glass;
		loom    = b.loom;
		papyrus = b.papyrus;
		
	}

	public int getGold()    {  return gold;     }
	public int getWood()    {  return wood;     }
	public int getStone()   {  return stone;    }
	public int getBrick()   {  return brick;    }
	public int getOre()     {  return ore;      }
	public int getGlass()   {  return glass;    }
	public int getLoom()    {  return loom;     }
	public int getPapyrus() {  return papyrus;  }
	
	public Resources(Resources r) {
		
		this.gold    = r.gold;
		this.wood    = r.wood;
		this.stone   = r.stone;
		this.brick   = r.brick;
		this.ore     = r.ore;
		this.glass   = r.glass;
		this.loom    = r.loom;
		this.papyrus = r.papyrus;
		
	}

	/**
	 * Combines two resource objects by adding their corresponding components and returning the result.
	 */
	public void add(Resources r) {
		
		this.gold    += r.gold;
		this.wood    += r.wood;
		this.stone   += r.stone;
		this.brick   += r.brick;
		this.ore     += r.ore;
		this.glass   += r.glass;
		this.loom    += r.loom;
		this.papyrus += r.papyrus;
		
	}
	
	public void remove(Resources r) {
		
		this.gold    -= r.gold;
		this.wood    -= r.wood;
		this.stone   -= r.stone;
		this.brick   -= r.brick;
		this.ore     -= r.ore;
		this.glass   -= r.glass;
		this.loom    -= r.loom;
		this.papyrus -= r.papyrus;
		
	}

	public Resources sum(Resources r) {
		
		return new Resources.Builder().gold(gold + r.getGold()).
				wood(wood + r.getWood()).stone(stone + r.getStone()).
				brick(brick + r.getBrick()).
				ore(ore + r.getOre()).
				glass(glass + r.getGlass()).
				loom(loom + r.getLoom()).
				papyrus(papyrus + r.getPapyrus()).build();
	}
	
	/**
	 * Subtracts the resource values of <i>r</i> from this object's resources with a minimum value of 0.
	 * @param r
	 */
	public Resources subtract(Resources r) {
		
		return new Resources.Builder().gold((gold - r.gold > 0) ? gold - r.gold : 0).wood((wood - r.wood > 0) ? wood - r.wood : 0).
				stone((stone - r.stone > 0) ? stone - r.stone : 0).brick((brick - r.brick > 0) ? brick - r.brick : 0).
				ore((ore - r.ore > 0) ? ore - r.ore : 0).glass((glass - r.glass > 0) ? glass - r.glass : 0).
				loom((loom - r.loom > 0) ? loom - r.loom : 0).papyrus((papyrus - r.papyrus > 0) ? papyrus - r.papyrus : 0).build();
		
	}
	
	public Resources subtractRaw(Resources r) {
		
		return new Resources.Builder().wood((wood - r.wood > 0) ? wood - r.wood : 0).stone((stone - r.stone > 0) ? stone - r.stone : 0).
				brick((brick - r.brick > 0) ? brick - r.brick : 0).ore((ore - r.ore > 0) ? ore - r.ore : 0).build();
		
	}
	
	public Resources subtractManufactured(Resources r) {
		return new Resources.Builder().glass((glass - r.glass > 0) ? glass - r.glass : 0).loom((loom - r.loom > 0) ? loom - r.loom : 0).
				papyrus((papyrus - r.papyrus > 0) ? papyrus - r.papyrus : 0).build();
		
	}
	
	/**
	 * Removes <i>g</i> gold from this resource
	 * @param g
	 */
	public void spendGold(int g) {  gold -= g;  }
	
	/**
	 * If each resource is 0, then returns true; otherwise, returns false.
	 * @return
	 */
	public boolean isEmpty() {
		
		if(gold == 0 && wood == 0 && stone == 0 && brick == 0 && ore == 0 && glass == 0 && loom == 0 && papyrus == 0)
			return true;
		
		return false;
		
	}

	/**
	 * 	Subtracts this resource from the resource cost <i>r</i>. If the result is empty, then return 
	 * 	true; otherwise, return false.
	 * 	@param r
	 * 	@return
	 */
	public boolean canAfford(Resources r) {
	
		Resources temp = r.subtract(this);
		
		if(temp.isEmpty())
			return true;
		
		return false;
		
	}
	
	public int cardinalityRaw()          {  return wood + stone + ore + brick;  }
	public int cardinalityManufactured() {  return glass + loom + papyrus;      }
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
	
		if(gold != 0)
			sb.append(String.format("(%d) %-7s ", gold, "Gold"));
		
		if(wood != 0)
			sb.append(String.format("(%d) %-7s ", wood, "Wood"));  
		
		if(stone != 0)        
			sb.append(String.format("(%d) %-7s ", stone, "Stone"));  
		
		if(brick != 0)        
			sb.append(String.format("(%d) %-7s ", brick, "Brick"));  
		
		if(ore != 0)          
			sb.append(String.format("(%d) %-7s ", ore, "Ore"));    
		
		if(glass != 0)        
			sb.append(String.format("(%d) %-7s ", glass, "Glass")); 
		
		if(loom != 0)         
			sb.append(String.format("(%d) %-7s ", loom, "Loom"));   
		
		if(papyrus != 0)      
			sb.append(String.format("(%d) %-7s ", papyrus, "Papyrus"));
		
		if(sb.length() == 0)
			return "NONE";
		
		return sb.toString();
		
	}
}