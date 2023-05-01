package Model;

import java.util.Random;

class Dice {
	static Random gerador = new Random();
	
	public static int getDiceValue() {
		return gerador.nextInt(6);
	}	
}
