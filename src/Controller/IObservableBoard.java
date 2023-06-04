package Controller;

public interface IObservableBoard {
	void add(IObserver observer);
	
	
	void notifyBoard();
}
