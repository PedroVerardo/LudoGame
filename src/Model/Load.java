package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Load {
	private static PlayerColor color;
	
	static ArrayList<Player> buildStateGame(String path, List<Player> listp, Round round, SingletonBoard board) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = null;
		scan = new Scanner(file);
		
		int cont = 0;
		List<ArrayList<Integer>> pawnsPositions = new ArrayList<ArrayList<Integer>>(4);
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		
		PlayerColor colorPlayerOfTheRound = PlayerColor.VERDE;
		
		//get the information from the save file
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String result = (String) line.subSequence(line.indexOf(':')+2, line.length());
			
			if(cont < 16) {
				pawnsPositions.get(cont/4).add(Integer.parseInt(result)); 
			}
			else {
				color = translateColor(result);
			}
			
			cont++;
		}
		
		
		
		//build the game
		ArrayList<ArrayList<Pawn>> pawnsList = makePawn(pawnsPositions); //make a matrix of pawns
		buildBoard(board, pawnsList, pawnsPositions);
		ArrayList<Player> playerList = createPlayerList(pawnsList);
		//translatePlayerTurn(colorPlayerOfTheRound, round);
		
		
		
		return playerList;
	}
	
	static ArrayList<Player> createPlayerList(ArrayList<ArrayList<Pawn>> pawnsList) {
		ArrayList<Player> newPlayerList = new ArrayList<Player>();
		ArrayList<Pawn> pawnsGreen = pawnsList.get(0);
		ArrayList<Pawn> pawnsYellow = pawnsList.get(1);
		ArrayList<Pawn> pawnsBlue = pawnsList.get(2);
		ArrayList<Pawn> pawnsRed = pawnsList.get(3);
		
		Player pl1 = new Player(2,pawnsGreen.get(0),pawnsGreen.get(1),pawnsGreen.get(2),pawnsGreen.get(3),PlayerColor.VERDE);
		Player pl2 = new Player(15,pawnsYellow.get(0),pawnsYellow.get(1),pawnsYellow.get(2),pawnsYellow.get(3),PlayerColor.AMARELO);
		Player pl3 = new Player(28,pawnsBlue.get(0),pawnsBlue.get(1),pawnsBlue.get(2),pawnsBlue.get(3),PlayerColor.AZUL);
		Player pl4 = new Player(41,pawnsRed.get(0),pawnsRed.get(1),pawnsRed.get(2),pawnsRed.get(3),PlayerColor.VERMELHO);
		
		newPlayerList.add(pl1);
		newPlayerList.add(pl2);
		newPlayerList.add(pl3);
		newPlayerList.add(pl4);
		
		return newPlayerList;
	}
	
	static void buildBoard(SingletonBoard board,ArrayList<ArrayList<Pawn>> pawnsList, List<ArrayList<Integer>> positions) {
		for(int i = 0; i < 76; i++) {
			if(!board.getHousePosition(i).getPawnsInHouse().isEmpty()) {
				board.getHousePosition(i).removeAllPawns();
				board.getHousePosition(i).setBarrierDown();
			}
				
		}
		
		for(int i = 0; i < pawnsList.size(); i++) {
			for(int j = 0; j < pawnsList.get(i).size(); j++) {
				
				
				if(positions.get(i).get(j) != -1) {
					board.putPawnInPosition(board,pawnsList.get(i).get(j), positions.get(i).get(j));
				}
				
			}
		}
		
	}
	
	static ArrayList<ArrayList<Pawn>> makePawn(List<ArrayList<Integer>> pawnsPositions) {
		ArrayList<ArrayList<Pawn>> playerPawns = new ArrayList<ArrayList<Pawn>>();
		
		for(int i = 0; i < pawnsPositions.size(); i++) {
			ArrayList<Pawn> pawns = new ArrayList<Pawn>();
			int initialHouse = translateInitialHouse(i);
			
			for(int j = 0; j < pawnsPositions.get(i).size(); j++) {
				short pawn = 0;
				int pos = pawnsPositions.get(i).get(j);
				System.out.println(pos);
				//mouting the pawn
				pawn = putColor(pawn, i);
				pawn = translateSpecialPositionAndPosition(pawn, pos, initialHouse);
				pawns.add(new Pawn(pawn));
				
			}
			playerPawns.add(pawns);
		}
		System.out.println(playerPawns);
		return playerPawns;
	}
	
	//add in pawn the qtd of moves
	static short translatePosition(short pawn, int pos, int playerInitialHouse) {
		
		if(pos >= playerInitialHouse && pos <= 51) {
			System.out.println("case 1");
			pawn += pos - playerInitialHouse;
		}
		
		else if(pos < playerInitialHouse && pos < 51){
			System.out.println("case 2");
			pawn += 52 - playerInitialHouse + pos;
		}
		
		else {
			System.out.println("case 3");
			pawn += pos;
		}
		
		return pawn;
	}
	
	static int translateInitialHouse(int line) {
		return 2 + line*13;
	}
	
	static short translateSpecialPositionAndPosition(short pawn, int pos, int playerInitialHouse) {
		//System.out.println(pawn+ " pos:" + pos + " iniHouse: " + playerInitialHouse);
		if(pos == -1) {
			pawn = putInBase(pawn);
		}
		
		else if(pos == 57 || pos == 63 || pos == 69 || pos == 75) {
			pawn = finishedThePath(pawn);
			pawn = translatePosition(pawn, pos, playerInitialHouse);
		}
		
		else if(pos > 51){ 
			pawn = setFinalLine(pawn);
			pawn = translatePosition(pawn, pos, playerInitialHouse);
		}
		
		else {
			pawn = translatePosition(pawn, pos, playerInitialHouse);
		}
		
		return pawn;
	}
	
	static PlayerColor getColor() {
		return color;
	}
	
	//get the turn defined in the file
	static void translatePlayerTurn( Round round) {
		System.out.println(color);
		
		while(round.getCurrentPlayerColor() != color) {
			round.getNextPlayer();
		}
		
		System.out.println(round.getCurrentPlayerColor());
	}
	
	//translate the string in the file to playerColor
	private static PlayerColor translateColor(String result) {
		if(result.compareTo("VERMELHO") == 0) {return PlayerColor.AZUL;}
		
		else if(result.compareTo("AMARELO") == 0) {return PlayerColor.VERDE;}
		
		else if(result.compareTo("AZUL") == 0) {return PlayerColor.AMARELO;}
		
		else {return PlayerColor.VERMELHO;}
			
	}
	
	//add the color to the pawn
	private static short putColor(short pawn, int pos) {
		if(pos == 0) {
			pawn = PlayerColor.VERDE.getValue();
		}
		else if(pos == 1) {
			pawn = PlayerColor.AMARELO.getValue();
		}
		else if(pos == 2) {
			pawn = PlayerColor.AZUL.getValue();
		}
		else {
			pawn = PlayerColor.VERMELHO.getValue();
		}
		return pawn;
	}
	
	//function used in pawn
	private static short setFinalLine(short pawn) {return pawn |= 0x8000;}
	private static short putInBase(short pawn) {return pawn |= 0x4000;}
	private static short finishedThePath(short pawn) {return pawn |= 0x2000;}
	
}
