package Model;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import Controller.IObservableBoard;
import Controller.IObserver;


class SingletonBoard implements IObservableBoard{
	private ArrayList<House> board;
	private int length;
	private List<IObserver> subscribers = new ArrayList<IObserver>();
	private static SingletonBoard boardInstance;
	 
	private SingletonBoard(int length) {
		this.length = length;
		board = new ArrayList<House>(length);
	}
	
	
	static SingletonBoard getInstance(int lenght) {
		if(boardInstance == null) {
			boardInstance = new SingletonBoard(lenght);
		}
		return boardInstance;
	}
	
	/**
	 * Static definitions in the game
	 * */
	boolean isInitialHousePositions(int position) {
		return position == 2 || position == 15 || position == 28 || position == 41;
	}
	
	
	boolean isSafeHousePosition(int position) {
		return position == 11 || position == 24 || position == 37 || position == 50;
	}
	
	
	boolean isFinalHouse(int position) {
		return position == 0 || position == 13 || position == 26 || position == 39;
	}
	
	
	/**
	 * Function to initialize the board, considered the board of the phone game Ludo
	 * not the physically game one.
	 * */
	void setBoard() {
		int i = 0;
		while(i < length) {
			if(isInitialHousePositions(i)){
				board.add(new House(false, false, true));
			}
			
			else if(isSafeHousePosition(i)) {
				board.add(new House(true, false, false));
			}
			
			else if(isFinalHouse(i)) {
				board.add(new House(false, true, false));
			}
			
			else {
				board.add(new House(false, false, false));
			}
			
			i++;
		}
	}
	
	
	/**
	 * Function to get the house object of a specific position.
	 * 
	 * @param pos position in the ArrayList.
	 * 
	 * @return A House object 
	 * */
	House getHousePosition(int pos) {return board.get(pos);}
	
	
	/**
	 * Function to check if in this house have a barrier(two pawns of same type)
	 * 
	 * @param Object House 
	 * 
	 * @return Boolean that represent the barrier in the house
	 * */
	boolean haveBarrier(House house) {return house.isBarrierUp();}
	
	/**
	 * Function to check if the pawn in the selected house is the same or not.
	 * 
	 * @param p1 The pawn you want to move
	 * @param h2 The House you want to check
	 * 
	 * @return boolean representing if is equal or not
	 * */
	boolean comparePawns(Pawn p1, House h2) {
		LinkedList<Pawn> p2 = h2.getPawnsInHouse();
		
		if(p2.isEmpty()) {return false;}
		for(int i = 0; i < p2.size(); i++) {
			if(p1.equals(p2.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 1 -> apenas move
	 * 0 -> nao pode ser movido
	 * 2 -> move para o inicio da sua linha final
	 * 3 -> move para casa inicial do player referente ao turno
	 * 4 -> move e remove o peão da casa
	 * 5 -> levanta uma barreira
	 * */
	int possibleMove(Pawn pawn, Player player, int position1, int diceRoll) {
		
		int position2 = diceRoll + position1;
		
		if(position2 > 51) {
			position2 -= 52;
		}
		
		
		House h2 = board.get(position2);
		House initial = board.get(player.getStartHouse());
		int totalMove = pawn.getTotalMoves() + diceRoll;
		int finalPos = (52 + 6*((pawn.getColor() - 0x0100) >> 8) +5);
		
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		if(pawn.haveFinished()) {return 0;}
		
		if(totalMove == 57 || totalMove == 63 || totalMove == 69 || totalMove == 75 ) {
			return 6;
		}
		
		if(pawn.isInFinalLine()) {
			if(totalMove < finalPos ) {return 1;}
			
			else {return 0;}
		}
		
		else {
			for(int i = 1; i < diceRoll; i++) {
				if(pawn.getTotalMoves() + i == 52) {return 2;}
				
				else if(haveBarrier(board.get(position1 + i))) {return 0;}
			}
			if(pawn.inBase() && diceRoll != 5) {return 0;}
			
			else if(pawn.inBase() && diceRoll == 5 && !comparePawns(pawn, initial)) {return 3;}
			
			else if(pawn.inBase() && diceRoll == 5 && comparePawns(pawn, initial)) {return 0;}
			
			//don't have pawns
			else if(listH2.isEmpty() && !pawn.inBase()) {return 1;}
			
			//is safe and don't have same color pawn
			else if(h2.isSafe() && listH2.size() < 2 &&
					!comparePawns(pawn ,h2)){return 1;}
			
			else if(h2.isSafe() && comparePawns(pawn ,h2)){return 0;}
			
			else if(h2.isSafe() && listH2.size() == 2){return 0;}
			
			//eat one pawn
			else if(listH2.size() == 1 && !comparePawns(pawn, h2)) {return 4;}
			
			//barrier up
			else if(listH2.size() == 1 && comparePawns(pawn, h2)) {return 5;}
			
			return 0;
		}
	}
	
	
	/**
	 * Function to check's if is possible to eat a pawn in the position 
	 * the player can move.
	 * 
	 * @param p The Pawn object that be added in in the house
	 * @param position2 The position the pawn move
	 * @deprecated
	 * */
	boolean possibleEat(Pawn p, int position2) {
		House h2 = board.get(position2);
		LinkedList<Pawn> listH2 = h2.getPawnsInHouse();
		
		if(listH2.size() == 1 && !h2.isSafe() && 
				!h2.isInitialHouse()) {return true;}
		
		return false;
	}
	
	
	/**
	 * Function to see all possible moves.
	 *  
	 * only move the pawn, don't increment the pawn distance and
	 * don't remove a possible pawn in the position 2
	 * 
	 * @param p The Pawn object that be added in in the house
	 * @param position1 The position initial in the pawn
	 * @param position2 The position the pawn move
	 **/
	void moveTo(Pawn p, int position1, int position2) {	
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		if(h1.isBarrierUp()) {
			h1.setBarrierDown();
		}
		
		h1.removePawn(p);
		
		h2.addPawn(p);
	}
	
	void putPawn(Pawn p, Player pl) {
		House h2 = board.get((pl.getStartHouse()));
		
		h2.addPawn(p);
	}
	
	/**
	 * Function to remove a pawn of a specific location and add in the other
	 * position.
	 * 
	 * @param p The Pawn object that be added in in the house
	 * @param position1 The position initial in the pawn
	 * @param position2 The position the pawn move
	 * */
	void eatPawn(Pawn p, int position1, int position2) {
		House h1 = board.get(position1);
		House h2 = board.get(position2);
		
		
		if(h1.isBarrierUp()) {
			h1.setBarrierDown();
		}
		
		h1.removePawn(p);
		h2.removePawn();
		
		h2.addPawn(p);
	}
	
	void exitBase(Pawn pawn, Player player) {
		House h1 = board.get(player.getStartHouse());
		pawn.removeFromBase();
		h1.addPawn(pawn);
	}
	
	
	void makeMove(Pawn p, Player player, int position1, int diceRoll) {
		
		int position2 = position1 + diceRoll;
		if(position2 > 51 && !p.isInFinalLine()) {position2 = position2 - 52;}
		
		
		
		House h2 = board.get(position2);
		
		int moveType = possibleMove(p, player, position1, diceRoll);
		
		System.out.println("esta "+ position1 + " rodou " + diceRoll + "move type: " + moveType);
		System.out.println("Pawns pos: "+ p.getTotalMoves());
		System.out.println(p.getColor());
		
		//casa final
		if(moveType == 2) {
			
			position2 = 52 + 6*((p.getColor() - 0x0100) >> 8)  + 
					p.getPawnPositionInBoard(player) + diceRoll - 
					player.getStartHouse();
			p.setFinalLine();
			System.out.println("position2 "+ p.getPawnPositionInBoard(player));
			p.addMove(position2 - 50);
			System.out.println("position2 "+  (52 + 6*((p.getColor() - 0x0100) >> 8) +5));
			moveTo(p, position1, position2);
			if(p.haveFinished()) {
				player.incPawnsFinished();
			}
		}
		
		//move para casa inicial
		else if(moveType == 3) {
			p.removeFromBase();
			exitBase(p, player);
		}
		
		//come peao
		else if(moveType == 4) {
			p.addMove(diceRoll);
			Pawn p2 = h2.getPawnsInHouse().pop();
			p2.putInBase();
			p2.pawnResetDist();
			moveTo(p, position1, position2);
		}
		
		//barreira
		else if(moveType == 5) {
			p.addMove(diceRoll);
			
			moveTo(p, position1, position2);
			
			h2.setBarrierUp();
		}
		
		//apenas move
		else if(moveType == 1) {
			p.addMove(diceRoll);
			moveTo(p, position1, position2);	
		}
		
		else if(moveType == 6) {
			p.addMove(diceRoll);
			moveTo(p, position1, position2);
			p.finishedThePath();
			player.incPawnsFinished();
			//é para colocar um retorno apropiado aq sembolizando o player que ganhou
			if(player.isWinner()) { 
				return;
			}
		}
		
		//nao pode mover nao faz nada
		else if(moveType == 0) {
			return;
		}
		
		notifyBoard();
	}


	@Override
	public void add(IObserver observer) {
		subscribers.add(observer);
		
	}


	@Override
	public void notifyBoard() {
		for(IObserver ob : subscribers) {
			ob.updateBoard();
		}
		
	}
}

