package View;

import java.io.IOException;

import Model.Facade;

public class App {
	public static void main(String[] args) {	
		new FRLudo(Facade.getFacadeInstance()).setVisible(true);

		Facade oi = new Facade();
		
		
		//oi.saveGame();
		
		//oi.loadGame();
		
	}
}
