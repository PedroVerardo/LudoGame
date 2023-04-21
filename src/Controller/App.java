package Controller;

import java.io.IOException;
import View.View;

public class App{
	public static void main(String args[]) throws IOException{
		//View dice = new View("./src/View/Dice.txt");
		//dice.printLines();
		
		View board = new View("./src/View/LudoBoard.txt");
		board.setBoard();
		board.printBoard();
	}
}
