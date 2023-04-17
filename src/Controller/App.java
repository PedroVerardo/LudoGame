package Controller;

import java.io.FileNotFoundException;
import View.View;


public class App{
	public static void main(String args[]) throws FileNotFoundException{
		View dice = new View("./src/View/test.txt");
		dice.printLines();
	}
}
