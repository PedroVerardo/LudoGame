package Model;

import java.util.ArrayList;
import java.util.List;

class Instance {
	
	static List<Player> getPlayerInstance(){
		
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
		
		Player pl4 = new Player(2, p13, p14, p15, p16, PlayerColor.VERDE);
		Player pl2 = new Player(15, p5, p6, p7, p8, PlayerColor.AMARELO);
		Player pl3 = new Player(28, p9, p10, p11, p12, PlayerColor.AZUL);
		Player pl1 = new Player(41, p1, p2, p3, p4, PlayerColor.VERMELHO);
		
		List<Player> list= new ArrayList<Player>();
		
		list.add(pl1);
		list.add(pl2);
		list.add(pl3);
		list.add(pl4);
		
		return list; 
	}
}
