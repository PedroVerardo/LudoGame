package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Load {
	
	static void buildStateGame(String path, List<Player> listp) throws FileNotFoundException {
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
	}
	
	static ArrayList<ArrayList<Short>> makePawn(ArrayList<ArrayList<Integer>> list) {
		ArrayList<ArrayList<Short>> playerPawns = new ArrayList<ArrayList<Short>>();
		
		for(int i = 0; i < list.size(); i++) {
			ArrayList<Short> pawns = new ArrayList<Short>();
			short pawn = 0;
			
			for(int j = 0; j < list.get(i).size(); j++) {
				
				int pos = list.get(i).get(j);
				
				putColor(pawn, i);
				//logica de tradução da posição
				
				
			}
			playerPawns.add(pawns);
		}
		return playerPawns;
	}
	
	static void translatePosition(short pawn, int pos, int playerInitialHouse) {
		if(pos > playerInitialHouse && pos < 51) {
			pawn += pos - playerInitialHouse;
		}
		
		else if(pos < playerInitialHouse && pos < 51){
			pawn += 51 - playerInitialHouse + pos;
		}
	}
	
	static void translateSpecialPosition(short pawn, int pos) {
		if(pos == -1) {putInBase(pawn);}
		
		if(pos == 57 || pos == 63 || pos == 69 || pos == 75) {finishedThePath(pawn);}
		
		else if(pos > 51){ setFinalLine(pawn);}
	}
	
	static PlayerColor translateColor(String result) {
		if(result.compareTo("VERMELHO") == 0) {return PlayerColor.VERMELHO;}
		
		else if(result.compareTo("AMARELO") == 0) {return PlayerColor.AMARELO;}
		
		else if(result.compareTo("AZUL") == 0) {return PlayerColor.AZUL;}
		
		else {return PlayerColor.VERDE;}
			
	}
	
	static short putColor(short pawn, int pos) {
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
	
	static void setFinalLine(short pawn) {pawn |= 0x8000;}
	static void putInBase(short pawn) {pawn |= 0x4000;}
	static void finishedThePath(short pawn) {pawn |= 0x2000;}
	
	
	static void refreshPawns(List<Player> listp, ArrayList<ArrayList<Short>> playerPawns) {
		for(Player p : listp) {
			List<Pawn> pawns = p.getPawns();
			
			for(Pawn pawn : pawns) {
				
			}
		}
	}
	
}
