package Model;

import java.util.Random;

class Dice {
	private static Random gerador = new Random();
	
	static int getDiceValue() {
		return gerador.nextInt(6);
	}	
}
