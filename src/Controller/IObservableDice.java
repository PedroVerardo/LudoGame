package Controller;

public interface IObservableDice {
	public void add(IObserver observer);
	
	
	public void notifyDice();
}
