package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Facade {
	
	private static Facade facadeInstance;
	
	private Pawn p1 = new Pawn(PlayerColor.VERMELHO);
	private Pawn p2 = new Pawn(PlayerColor.VERMELHO);
	private Pawn p3 = new Pawn(PlayerColor.VERMELHO);
	private Pawn p4 = new Pawn(PlayerColor.VERMELHO);
	private Pawn p5 = new Pawn(PlayerColor.AMARELO);
	private Pawn p6 = new Pawn(PlayerColor.AMARELO);
	private Pawn p7 = new Pawn(PlayerColor.AMARELO);
	private Pawn p8 = new Pawn(PlayerColor.AMARELO);
	private Pawn p9 = new Pawn(PlayerColor.AZUL);
	private Pawn p10 = new Pawn(PlayerColor.AZUL);
	private Pawn p11 = new Pawn(PlayerColor.AZUL);
	private Pawn p12 = new Pawn(PlayerColor.AZUL);
	private Pawn p13 = new Pawn(PlayerColor.VERDE);
	private Pawn p14 = new Pawn(PlayerColor.VERDE);
	private Pawn p15 = new Pawn(PlayerColor.VERDE);
	private Pawn p16 = new Pawn(PlayerColor.VERDE);
	
	private Player pl4 = new Player(2, p13, p14, p15, p16, PlayerColor.VERDE);
	private Player pl2 = new Player(15, p5, p6, p7, p8, PlayerColor.AMARELO);
	private Player pl3 = new Player(28, p9, p10, p11, p12, PlayerColor.AZUL);
	private Player pl1 = new Player(41, p1, p2, p3, p4, PlayerColor.VERMELHO);
	
	
	private SingletonBoard game = SingletonBoard.getInstance(76);
	private Round round = new Round(pl4, pl2, pl3, pl1);
	
	private Player acctualPlayer;
	
	public void printBoard() {
		for(int i = 0; i < 76;i++) {
			System.out.println("casa "+ i + "com" + game.getHousePosition(i).getPawnsInHouse());
		}
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
	}
	
	public List<Player> getAllPlayers(){
		List<Player> plist = new ArrayList<Player>(4);
		plist.add(pl1);
		plist.add(pl2);
		plist.add(pl3);
		plist.add(pl4);
		
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
	
	//Pawns positions of one player
	public List<Integer> getPawnsPositionOfPlayer(){return acctualPlayer.getAllPawnsBoardposition();}
	
	//Pawns moveTypes of one Player
	public List<Integer> getPawnsMoveTypesOfPlayer(int diceRoll){return acctualPlayer.getPawnsMoveTypes(game, diceRoll);}
	
	//If this house is safe
	public boolean isSafeHouse(int pos) {return game.getHousePosition(pos).isSafe();}
	
	//If this house is final
	public boolean isFinalHouse(int pos) {return game.getHousePosition(pos).isFinalHouse();}
	
	//If is this house is Initial
	public boolean isInitialHouse(int pos) {return game.getHousePosition(pos).isInitialHouse();}
	
	//Pawn color
	public int pawnColor(int local) {
		List<Pawn> lis = acctualPlayer.getPawns();
		
		return lis.get(local).getColor();
	}
	
	
	//move in the game
	public void makeMove(int local,int pos1, int diceRoll){
		List<Pawn> lis = acctualPlayer.getPawns();
		Pawn p = lis.get(local);
		System.out.println(getPawnsMoveTypesOfPlayer(diceRoll));
		
		game.makeMove(p ,acctualPlayer, p.getPawnPositionInBoard(acctualPlayer), diceRoll);
		printBoard();
	}
	
	
	
}















