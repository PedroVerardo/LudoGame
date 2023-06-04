package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Controller.IObservableDice;
import Controller.IObserver;

class Dice implements IObservableDice{
	private static Random gerador = new Random();
	private List<IObserver> subscribers = new ArrayList<IObserver>();
	

	public void add(IObserver observer) {
		subscribers.add(observer);
	}

	public void notifyDice() {
		for(IObserver ob: subscribers) {
			ob.updateDice();
		}
		
	}	
	
	void updateState() {
		notifyDice();
	}
	
	static int getDiceValue() {
		return gerador.nextInt(6);
	}
}
