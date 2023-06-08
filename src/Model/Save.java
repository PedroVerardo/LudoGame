package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Save {
	
	static void save(List<Player> lplayers, Player playerOfTheRound) throws IOException {
		FileWriter escritor = new FileWriter("save.txt");
		
		for(Player player: lplayers) {
			writePawnsState(player.getPawns(), player, escritor);
		}
		
		escritor.write("PlayerOfTheRound: " + playerOfTheRound.getPlayerColor());
		
		escritor.flush();
		escritor.close();
	}
	
	
	static void writePawnsState(List<Pawn> lp, Player player, FileWriter fw) throws IOException {
		
		for(Pawn p: lp) {
			fw.write(translateColor(player.getPlayerColor()) 
					+ translatePosition(p, player));
			
		}
	}
	
	private static String translateColor(PlayerColor pc) {
		if(pc == PlayerColor.AMARELO) {return "AMARELO " + PlayerColor.AMARELO.getValue();}
		else if(pc == PlayerColor.VERDE) {return "VERDE " + PlayerColor.VERDE.getValue();}
		else if(pc == PlayerColor.VERMELHO) {return "VERMELHO " + PlayerColor.VERMELHO.getValue();}
		else{return "AZUL " + PlayerColor.AZUL.getValue();}
	}
	
	private static String translatePosition(Pawn p, Player pl) {
		if(p.inBase()) {
			return " position " + -1 + "\n";
		}
		else {
			return " position " + p.getPawnPositionInBoard(pl) + "\n";
		}
	}
}
