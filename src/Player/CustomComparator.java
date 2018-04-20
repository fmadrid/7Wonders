package Player;

import java.util.Comparator;

public class CustomComparator implements Comparator<Player> {
	
    public int compare(Player p1, Player p2) {  return p1.compareTo(p2);  }
    
}
