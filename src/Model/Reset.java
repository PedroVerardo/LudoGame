package Model;

public class Reset {
	static void resetBoard(SingletonBoard board) {
		for(int i = 0; i < 76;i++) {
			House h = board.getHousePosition(i);
			while(h.getPawnsInHouse().size() != 0) {
				h.removePawn();
			}
		}
	}
	
	static void resetPawn(Pawn p) {
		p.putInBase();
		p.resetFinished();
		p.pawnResetDist();
		p.resetFinalLine();
	}
}
