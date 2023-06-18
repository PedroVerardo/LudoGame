package Controller;

import java.util.List;

import Model.*;
import View.*;


public class Controller {
	
	private int initGame = 0;
	private int tokenRepeat = 0;
	private int captureOrFinal = 0;
	
	Facade facade = Facade.getFacadeInstance();
	Menu menu = new Menu(facade);
	
	public Controller() {}
	
	public void inicializeGame() { facade.setBoard(); }
	
	/**
	 * Receives two coordinates of a mouse click and returns the position that corresponds to them
	 **/
	public int getPositionByClick(int x, int y) {
		
		int[] finalHouses = {56, 63, 69, 75};
		// side portions of board: red+blue+housesBetween | green+yellow+housesBetween 
		if (x < 6 || x > 8) { return sidePortions(x, y); }
		
		// middle portion of board, except final houses
		else if (x <= 8 && (y < 6 || y > 8)) { return middlePortion(x, y); }
		
		// final houses possible coordinates
		else if (x >= 6 && x <= 8 && y >= 6 && y <= 8) { return 72; }
		
			
		// didn't find corresponding position
		return -5;
	}
	
	/* 
	 * makes a move based on a dice roll and on a board click
	 * if a move is not possible, return -1
	 * if function made move, returns 1
	 */
	public void makeMoveController(int roll, int click, Menu menu) {
		
//		if (initGame == 0) { facade.getPlayerOfRound(); }
//		initGame = 1;
		
		List<Integer> pawnsPositions = facade.getPawnsPositionOfPlayer();
		List<Integer> moveTypes = facade.getPawnsMoveTypesOfPlayer(roll);
		List<Integer> pawnsTotalMoves = facade.pawnTotalMove();
		int indexClick = validateClick(click, pawnsPositions, moveTypes);
		int indexBarrier = searchBarrier(roll, pawnsPositions);
		
		// if player has no possible moves
		if (verifyMoveTypes(moveTypes) == 0) {
			menu.setDiceButton(true);
			facade.getPlayerOfRound(); 
			return; }		
		// if click is not in a valid position, the round must be locked to that player 
		if (indexClick == -1) { 
			return; }
		
		// if dice roll equals 6 and there is a barrier in player round
		if (roll == 6 && indexBarrier != -1 && click != indexBarrier) { 
			return; }
		// if dice roll equals 5 and there are pawns in base
		if (roll == 5 && setPawnOutOfBase(roll, pawnsPositions, moveTypes) == 1 
				&& click != facade.getPlayerInitialHouse()) { 
			return; }
			
		facade.makeMove(indexClick, click, roll);
		
		if (facade.checkIfPlayerWin()) { 
			new FRScoreBoard(facade).setVisible(true); return; }
		
		
		int extraMove = makeExtraMovement(roll, moveTypes.get(indexClick)); 
		// if dice roll was 6
		if (extraMove == 1 && captureOrFinal == 0) {
			// if roll equals 6 three times in a row and player has not arrived in final house ;
			if (tokenRepeat == 2) {
				if (pawnsTotalMoves.get(indexClick) + 6 < 50) {
					facade.returnPawnToBase(pawnsPositions.get(indexClick) + 6); 
				}
				
			
			}
			else {
				tokenRepeat++;
				menu.setDiceButton(true);
				return; 
			}
		}
		
		// if captured pawn or arrived in final house, players wins 6 movements
		if (extraMove == 2 || extraMove == 3 || extraMove == 4) {
			captureOrFinal = 1;
			menu.setDiceToSix();
			return;
		}
		
		captureOrFinal = 0;
		tokenRepeat = 0;
		menu.setDiceButton(true);	

		facade.getPlayerOfRound();
		return;
	}
	
	/*
	 * Verify if player has moves. 
	 * If not, round is passed to other player.
	 * */
	public int verifyMoveTypes(List<Integer> moveTypes) {
		
		for (int i = 0; i < moveTypes.size(); i++) {
			 
			if (moveTypes.get(i) != 0) { return 1; }
		}
		
		return 0;
	}
	
	/*
	 * Receives a board click and verifies:
	 * - if it corresponds to a pawn position
	 * - if move is possible
	 * */
	int validateClick(int click, List<Integer> pawnsPositions, List<Integer> moveTypes) {
		for (int i = 0; i < pawnsPositions.size(); i++) {
			if (click == pawnsPositions.get(i) && moveTypes.get(i) != 0) { 
				return i; }
		}
		return -1;
	}
	
	/*
	 * Verifies if a player needs to set pawn out of base 
	 * */
	int setPawnOutOfBase(int roll, List<Integer> pawnsPositions, List<Integer> moveTypes) {
		for (int i = 0; i < pawnsPositions.size(); i++) {
			if (pawnsPositions.get(i) == facade.getPlayerInitialHouse() 
					&& moveTypes.get(i) != 0) { return 1; }	
		}
		return 0;
	}
	
	/*
	 * Function that verifies if another move is possible
	 * */
	int makeExtraMovement(int roll, int type) {
        
        if (roll == 6) { return 1; }
        if (type == 4) { return 2; }
        if (type == 6) { return 3; }
        if (type == 7) { return 4; }
        
        return 0;
	}
	
	/*
	 * check if a barrier is up
	 * returns barrier position in case is up
	 * else returns -1  
	 * */
	int searchBarrier(int roll, List<Integer> pawnsPositions) {
		for (int i = 0; i < 4; i++) {
			Integer pawn = pawnsPositions.get(i);
			for (int j = i+1; j < 4; j++ ) {
				if (pawn.compareTo(pawnsPositions.get(j)) == 0
						&& !facade.isInitialHouseOfPlayer(pawn)
						&& !isFinalHouse(pawn)) {  return pawn; }
			}
		}
		
		return -1;
	}
	
	boolean isFinalHouse(int pos) {
		int[] finalHouses = {57, 63, 69, 75};
		
		for (int i = 0; i < 4; i++) {
			if (pos == finalHouses[i]) { return true; }
			
		}
		return false;
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
			return 64 + (13 - y);
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
			if (x < 6) {return 41;} // red base
			return 2; // green base
		}
		
		if (y > 8) {
			if (x < 6) {return 28;} // blue
			return 15; // yellow base
		}
		
		// first line from top to bottom 
		if (y == 6) {
			if (x < 6) {return 40 + x;} // between red and blue
			return 7 + (x - 9); // between green and yellow
			}
		// second line 
		if (y == 7) {
			if (x == 0) {return 39;} // house before red final line
			if (x < 6) {return 69 + x;} // red final line
			if (x == 14) {return 13;} // house before yellow final line
			return 58 + (13 - x); // yellow final line
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
