package application;

import java.util.HashMap;

@SuppressWarnings("serial")
public class MoveData extends HashMap<String, Move> {
	private static MoveData moveData;
	
	public MoveData() {
		initializeMoves();
	}
	
	private void initializeMoves() {
		put("Tackle", new Move("Tackle", Type.NORMAL, "Physical", 40, 100, 40, 0, null));
	}
	
	public static MoveData get() {
		if(moveData == null) {
			moveData = new MoveData();
		}
		return moveData;
	}
}
