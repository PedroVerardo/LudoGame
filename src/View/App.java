package View;

import Model.Facade;

public class App {
	public static void main(String[] args) {	
		new FRLudo(Facade.getFacadeInstance()).setVisible(true);
		
	}
}
