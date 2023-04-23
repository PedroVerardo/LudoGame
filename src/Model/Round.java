package Model;

import java.util.Queue;

public class Round {
	Queue<Player> queue = null;
	Player currentPlayer;
	
	public void inicialQueue(Player p1, Player p2, Player p3, Player p4) {
		queue.add(p1);
		queue.add(p2);
		queue.add(p3);
		queue.add(p4);
	}
	
	public Player getNextPlayer() {
		currentPlayer = queue.remove();
		queue.add(currentPlayer);
		
		return currentPlayer;
	}
}
