package Model;

import View.FRLudo;

public class App {
	public static void main(String[] args) {
		Pawn pawnYellow = new Pawn(PlayerColor.AMARELO);
		Pawn pawnYellow2 = new Pawn(PlayerColor.AMARELO);
		Pawn pawnYellow3 = new Pawn(PlayerColor.AMARELO);
		Pawn pawnYellow4 = new Pawn(PlayerColor.AMARELO);	
		Pawn pawnRed = new Pawn(PlayerColor.VERMELHO);
		Pawn pawnRed2 = new Pawn(PlayerColor.VERMELHO);
		Pawn pawnRed3 = new Pawn(PlayerColor.VERMELHO);
		Pawn pawnRed4 = new Pawn(PlayerColor.VERMELHO);
		Pawn pawnBlue = new Pawn(PlayerColor.AZUL);
		Pawn pawnBlue2 = new Pawn(PlayerColor.AZUL);
		Pawn pawnBlue3 = new Pawn(PlayerColor.AZUL);
		Pawn pawnBlue4 = new Pawn(PlayerColor.AZUL);
		Pawn pawnGreen = new Pawn(PlayerColor.VERDE);
		Pawn pawnGreen2 = new Pawn(PlayerColor.VERDE);
		Pawn pawnGreen3 = new Pawn(PlayerColor.VERDE);
		Pawn pawnGreen4 = new Pawn(PlayerColor.VERDE);
		pawnGreen3.addMove(1);
		pawnRed.addMove(5);
		pawnBlue2.addMove(8);
		pawnYellow4.addMove(14);
		Player players[] = {new Player(2, pawnGreen, pawnGreen2, pawnGreen3, pawnGreen4),new Player(15, pawnYellow, pawnYellow2, pawnYellow3, pawnYellow4),new Player(28, pawnBlue, pawnBlue2, pawnBlue3, pawnBlue4),new Player(41, pawnRed, pawnRed2, pawnRed3, pawnRed4)};	
		(new FRLudo(SingletonBoard.getInstance(52),players)).setVisible(true);
	}
}