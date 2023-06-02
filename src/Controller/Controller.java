package Controller;

import java.util.List;

import Model.*;
import View.*;

public class Controller {
	
	private int tokenRepeat;

	Facade facade = Facade.getFacadeInstance();
	
	public Controller() {}
	
	public void inicializeGame() { facade.setBoard(); }
	
	public int getDiceRoll() { return facade.GetDiceRoll(); }
	
	/**
	 * Receives two coordinates of a mouse click and returns the position that corresponds to them
	 **/
	public int getPositionByClick(int x, int y) {
		// side portions of board: red+blue+housesBetween | green+yellow+housesBetween 
		if (x < 6 || x > 8) { return sidePortions(x, y); }
		
		// middle portion of board, except final houses
		else if (x <= 8 && (y < 6 || y > 8)) { return middlePortion(x, y); }
		
		// final houses possible coordinates
		else if (x >= 6 && x <= 8 && y >= 6 && y <= 8) { return 72; }
		
		// didn't find corresponding position
		return -5;
	}
	
	/**
	 * Function that verifies possibles moves for all player pawns and makes a move based on player selection
	 * returns to view which pawns can be moved based on their positions
	 **/
	public int makeMove(int roll, int click) {
        List<Integer> moveTypesOfPlayer = facade.getPawnsMoveTypesOfPlayer(roll);
        List<Integer> pawnsPositions = facade.getPawnsPositionOfPlayer();
        tokenRepeat = 1;
        
        if (verifyIfPlayerHasMoves(moveTypesOfPlayer) == 0) { facade.getPlayerOfRound(); return 0; }
        
        while (tokenRepeat != 0) {
	        for (int i = 0; i < moveTypesOfPlayer.size(); i++) {
	        	// if mouse click is in one pawns positions AND the move is possible
	        	if (click == pawnsPositions.get(i) && moveTypesOfPlayer.get(i) != 0) {
	        		facade.makeMove(i, pawnsPositions.get(i), roll);
	        	}
	        	
	        	if (roll != 6 || moveTypesOfPlayer.get(i)!= 4) {tokenRepeat = 0;}
	        } 
        }
        
        
        return 1;
	}
	
	int verifyIfPlayerHasMoves(List<Integer> moveTypesArray) {
		for (int i = 0; i < moveTypesArray.size(); i++) {
			if (moveTypesArray.get(i) != 0)
				return 1;
		}
		
		return 0;
	}
	
	int middlePortion(int x, int y) {
		// first line left to right
		if (x == 6) {
			// between red and green
			if (y < 6) {return 51 - y;}
			// between blue and yellow
			return 32 - (y - 9);
		}
		// middle line
		if (x == 7) {
			if (y == 0) {return 0;}
			if (y == 14) {return 26;}
			// green final houses
			if (y < 6) {return 51 + y;}
			// blue final houses
			return 62 + (13 - y);
		}
		// third line from left to right
		if (x == 8) {
			// between red and green
			if (y < 6) {return 1 + y;}
			// between blue and yellow
			return 20 + (y - 9);
		}
		
		// didn't find corresponding position
		return -10;
	}
	
	int sidePortions(int x, int y) {
		if (y < 6)
		{
			if (x < 6) {return -4;} // red base
			return -1; // green base
		}
		
		if (y > 8) {
			if (x < 6) {return -3;} // blue
			return -2; // yellow base
		}
		
		// first line from top to bottom 
		if (y == 6) {
			if (x < 6) {return 40 + x;} // between red and blue
			return 7 + (x - 9); // between green and yellow
			}
		// second line 
		if (y == 7) {
			if (x == 0) {return 39;} // house before red final line
			if (x < 6) {return 66 + x;} // red final line
			if (x == 14) {return 13;} // house before yellow final line
			return 57 + (13 - x); // yellow final line
		}
		// third line from top to bottom
		if (y == 8) {
			if (x < 6) {return 38 - x;} // between red and blue
			return 19 - (x - 9); // between green and yellow
		}
		
		// didn't find corresponding position
		return -10;
	}
}
