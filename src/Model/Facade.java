package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Controller.IObserver;

public class Facade {
	
	private static Facade facadeInstance;	
	
	List<Player> listp = Instance.getPlayerInstance();
	
	private Player pl4 = listp.get(3);
	private Player pl2 = listp.get(1);
	private Player pl3 = listp.get(2);
	private Player pl1 = listp.get(0);
	
	
	private SingletonBoard game = SingletonBoard.getInstance(76);
	private Round round = new Round(pl4, pl2, pl3, pl1);
	
	
	private Player acctualPlayer = pl4;
	
	
	public void printBoard() {
		for(int i = 0; i < 76;i++) {
			System.out.println("casa "+ i + "com" + game.getHousePosition(i).getPawnsInHouse());
		}
	}

	public Map<Short, Integer> getScore() {
		return Score.totalScore(getAllPlayers());
	}
	
	/**
	 * Singleton construction
	 * @return The instance of the facade class
	 */
	public static Facade getFacadeInstance() {
		if (facadeInstance == null){facadeInstance = new Facade();}
		
		return facadeInstance;
	}
	
	public void setBoard() {
		game.setBoard();
		
		//getPlayerOfRound();
		//loadGame();
	}
	
	public void subscribeInBoard(IObserver ob) {
		game.add(ob);
	}
	
	public List<Player> getAllPlayers(){
		List<Player> plist = new ArrayList<Player>(4);
		plist.add(pl4);
		plist.add(pl2);
		plist.add(pl3);
		plist.add(pl1);
		
		return plist;
	}
	
	public ArrayList<List<Integer>> getAllPawnsPositions(){
		List<Integer> l1 = pl4.getAllPawnsBoardposition();
		List<Integer> l2 = pl2.getAllPawnsBoardposition();
		List<Integer> l3 = pl3.getAllPawnsBoardposition();
		List<Integer> l4 = pl1.getAllPawnsBoardposition();
		
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(l1);
		result.add(l2);
		result.add(l3);
		result.add(l4);
		
		return result;
	}
	
	public ArrayList<List<Boolean>> getAllPawnsInBase(){
		List<Boolean> l1 = pl4.getIfPawnsInBase();
		List<Boolean> l2 = pl2.getIfPawnsInBase();
		List<Boolean> l3 = pl3.getIfPawnsInBase();
		List<Boolean> l4 = pl1.getIfPawnsInBase();
		
		ArrayList<List<Boolean>> result = new ArrayList<List<Boolean>>();
		
		result.add(l1);
		result.add(l2);
		result.add(l3);
		result.add(l4);
		
		return result;
	}
	
	//Player of the round
	public void getPlayerOfRound() {acctualPlayer = round.getNextPlayer();}
	
	//diceValue
	public int GetDiceRoll(){return Dice.getDiceValue()+1;}
	
	//Pawns in the House
	public int getPawnsInHouse(int pos){return game.getHousePosition(pos).getPawnsInHouse().size();}
	
	//Get player start house
	public int getPlayerInitialHouse() {return acctualPlayer.getStartHouse();}
	
	//Pawns positions of one player
	public List<Integer> getPawnsPositionOfPlayer(){return acctualPlayer.getAllPawnsBoardposition();}
	
	//Pawns moveTypes of one Player
	public List<Integer> getPawnsMoveTypesOfPlayer(int diceRoll){return acctualPlayer.getPawnsMoveTypes(game, diceRoll);}
	
	//If this house is safe
	public boolean isSafeHouse(int pos) {return game.getHousePosition(pos).isSafe();}
	
	//If this house is final
	public boolean isFinalHouse(int pos) {return game.getHousePosition(pos).isFinalHouse();}
	
	//If this house is Initial
	public boolean isInitialHouse(int pos) {return game.getHousePosition(pos).isInitialHouse();}
	
	//If this house is initial of one player
	public boolean isInitialHouseOfPlayer(int pos) { return pos == acctualPlayer.getStartHouse(); }
	
	//put pawn in base
	public void returnPawnToBase(int pos) { game.returnPawnToBase(pos);}
	
	//Pawn color
	public int pawnColor(int local) {
		List<Pawn> lis = acctualPlayer.getPawns();
		
		return lis.get(local).getColor();
	}
	
	public int getPlayerColor() {
		return acctualPlayer.getPlayerColor().getValue();
	}
	
	public void saveGame(){
		try {
			Save.save(getAllPlayers(), acctualPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadGame() {
		try {
			
			ArrayList<Player> playerList= Load.buildStateGame("C:\\Users\\Pedro\\eclipse-workspace\\LudoGame\\save.txt",getAllPlayers() ,round, game);
			pl4 = playerList.get(0);
			pl2 = playerList.get(1);
			pl3 = playerList.get(2);
			pl1 = playerList.get(3);
			
			round = new Round(pl4, pl2, pl3, pl1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void resetGame() {
		for(int i = 0; i < 76; i++) {
			if(!game.getHousePosition(i).getPawnsInHouse().isEmpty()) {
				game.getHousePosition(i).removeAllPawns();
				game.getHousePosition(i).setBarrierDown();
			}
				
		}
		
		List<Player> listp = Instance.getPlayerInstance();
		
		pl4 = listp.get(3);
		pl2 = listp.get(1);
		pl3 = listp.get(2);
		pl1 = listp.get(0);
		
		round = new Round(pl4, pl2, pl3, pl1);
		
		acctualPlayer = pl4;
	}
	
	
	//move in the game
	public void makeMove(int local, int pos1, int diceRoll){
		List<Pawn> lis = acctualPlayer.getPawns();
		Pawn p = lis.get(local);
		System.out.println(getPawnsMoveTypesOfPlayer(diceRoll));
		
		game.makeMove(p ,acctualPlayer, p.getPawnPositionInBoard(acctualPlayer), diceRoll);
		//printBoard();
		System.out.println(getAllPawnsPositions());
	}
	
}















