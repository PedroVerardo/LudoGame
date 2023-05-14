package Model;

import View.FRLudo;

public class App {
	public static void main(String[] args) {
		(new FRLudo(SingletonBoard.getInstance(52))).setVisible(true);
	}
}