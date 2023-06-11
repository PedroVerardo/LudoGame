package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Load {
	
	static void buildStateGame(String path, List<Player> listp, Round round, SingletonBoard board) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = null;
		scan = new Scanner(file);
		
		int cont = 0;
		List<ArrayList<Integer>> pawnsPositions = new ArrayList<ArrayList<Integer>>(4);
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		pawnsPositions.add(new ArrayList<Integer>());
		
		PlayerColor colorPlayerOfTheRound;
		
		//get the information from the save file
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String result = (String) line.subSequence(line.indexOf(':')+2, line.length());
			
			if(cont < 16) {
				pawnsPositions.get(cont/4).add(Integer.parseInt(result)); 
			}
			else {
				colorPlayerOfTheRound = translateColor(result);
			}
			
			cont++;
		}
		
		
		
		//build the game
		ArrayList<ArrayList<Pawn>> pawnsList = makePawn(pawnsPositions); //make a matrix of pawns
		buildBoard(board, pawnsList, pawnsPositions);
		
		System.out.println(pawnsList);
	}
	
	static void buildBoard(SingletonBoard board,ArrayList<ArrayList<Pawn>> pawnsList, List<ArrayList<Integer>> positions) {
		for(int i = 0; i < 76; i++) {
			if(!board.getHousePosition(i).getPawnsInHouse().isEmpty()) {
				board.getHousePosition(i).removeAllPawns();
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
		return playerPawns;
	}
	
	//add in pawn the qtd of moves
	static short translatePosition(short pawn, int pos, int playerInitialHouse) {
		System.out.println("oi");
		if(pos > playerInitialHouse && pos < 51) {
			
			pawn += pos - playerInitialHouse;
		}
		
		else if(pos < playerInitialHouse && pos < 51){
			pawn += 51 - playerInitialHouse + pos;
		}
		
		else {
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
	
	//get the turn defined in the file
	private static void translatePlayerTurn(PlayerColor colorOfTheTurn, Round round) {
		while(round.getCurrentPlayerColor() != colorOfTheTurn) {
			round.getNextPlayer();
		}
	}
	
	//translate the string in the file to playerColor
	private static PlayerColor translateColor(String result) {
		if(result.compareTo("VERMELHO") == 0) {return PlayerColor.VERMELHO;}
		
		else if(result.compareTo("AMARELO") == 0) {return PlayerColor.AMARELO;}
		
		else if(result.compareTo("AZUL") == 0) {return PlayerColor.AZUL;}
		
		else {return PlayerColor.VERDE;}
			
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
