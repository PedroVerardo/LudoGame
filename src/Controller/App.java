package Controller;

import java.awt.Color;

import Model.*;
import View.BoardV;

public class App {
	public static void main(String args[]) {
		Board<Pawn_class> board = Board.getInstance(8, 8);
		board.setBoardPosition(new Pawn_class(Color.black), 1, 2);
		board.printa();
		//BoardV.showTable();
	}
}
