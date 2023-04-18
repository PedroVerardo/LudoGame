package Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import Model.BoardArray;


public class App{
	public static void main(String args[]) throws FileNotFoundException{
		//View dice = new View("./src/View/Dice.txt");
		//dice.printLines();
		BoardArray board = BoardArray.getInstance(52);
		board.setBoard(0, (byte) 0x10);
		board.setBoard(1, (byte) 0x10);
		board.setBoard(2, (byte) 0x20);
		board.setBoard(3, (byte) 0x40);
		board.setBoard(4, (byte) 0x40);
		
		System.out.println(board.makeContact(0, 3));
		System.out.println(board.makeContact(0, 1));
		System.out.println(board.makeContact(1, 2));
		System.out.println(board.makeContact(3, 4));
		
	}
}
