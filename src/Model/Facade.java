package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Facade {
	
	private static Facade facadeInstance;
	
	Pawn p1 = new Pawn(PlayerColor.VERMELHO);
	Pawn p2 = new Pawn(PlayerColor.VERMELHO);
	Pawn p3 = new Pawn(PlayerColor.VERMELHO);
	Pawn p4 = new Pawn(PlayerColor.VERMELHO);
	Pawn p5 = new Pawn(PlayerColor.AMARELO);
	Pawn p6 = new Pawn(PlayerColor.AMARELO);
	Pawn p7 = new Pawn(PlayerColor.AMARELO);
	Pawn p8 = new Pawn(PlayerColor.AMARELO);
	Pawn p9 = new Pawn(PlayerColor.AZUL);
	Pawn p10 = new Pawn(PlayerColor.AZUL);
	Pawn p11 = new Pawn(PlayerColor.AZUL);
	Pawn p12 = new Pawn(PlayerColor.AZUL);
	Pawn p13 = new Pawn(PlayerColor.VERDE);
	Pawn p14 = new Pawn(PlayerColor.VERDE);
	Pawn p15 = new Pawn(PlayerColor.VERDE);
	Pawn p16 = new Pawn(PlayerColor.VERDE);
	
	Player pl1 = new Player(2, p1, p2, p3, p4, PlayerColor.VERMELHO);
	Player pl2 = new Player(15, p5, p6, p7, p8, PlayerColor.AMARELO);
	Player pl3 = new Player(28, p9, p10, p11, p12, PlayerColor.AZUL);
	Player pl4 = new Player(41, p13, p14, p15, p16, PlayerColor.VERDE);
	
	private SingletonBoard game = SingletonBoard.getInstance(76);
	private Round round = new Round(pl1, pl2, pl3, pl4);

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
	
	//Player of the round
	public Player getPlayerOfRound() {return round.getNextPlayer();}
	
	//diceValue
	public int GetDiceRoll(){return Dice.getDiceValue();}
	
	//House
	public House getHouseByPosition(int pos){return game.getHousePosition(pos);}
	
	//Pawns in the House
	public LinkedList<Pawn> getPawnsInHouse(House h){return h.getPawnsInHouse();}
	
	//Pawns of one Player
	public List<Pawn> getPawnsOfPlayer(Player p) {return p.getPawns();}
	
	//Pawns positions of one player
	public List<Integer> getPawnsPositionOfPlayer(Player p){return p.getAllPawnsBoardposition();}
	
	//Pawns moveTypes of one Player
	public List<Integer> getPawnsMoveTypesOfPlayer(Player p, int diceRoll){return p.getPawnsMoveTypes(game, diceRoll);}
	
	//If this house is safe
	public boolean isSafeHouse(House h) {return h.isSafe();}
	
	//If this house is final
	public boolean isFinalHouse(House h) {return h.isFinalHouse();}
	
	//If is this house is Initial
	public boolean isInitialHouse(House h) {return h.isInitialHouse();}
	
	//Pawn color
	public int pawnColor(Pawn p) {return p.getColor();}
	
	//move in the game
	public void makeMove(Pawn p, Player pl, int pos1, int diceRoll){
		game.makeMove(p, pl, pos1, diceRoll);
	}
	
}















