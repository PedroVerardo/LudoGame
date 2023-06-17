package Controller;

public interface IObservableBoard {
	public void add(IObserver observer);
	
	
	public void notifyBoard();
}
