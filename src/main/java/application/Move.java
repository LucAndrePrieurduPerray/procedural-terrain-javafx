package application;

public class Move {
	private String name;
	private Type type;
	private String category;
	private int power, accuracy, pp, priority;
	private Runnable action;
	
	public Move(String moveName) {
		MoveData moveData = MoveData.get();
		Move move = moveData.get(moveName);
		
		if(move != null) {
			setVariables(move.getName(), move.getType(), move.getCategory(), move.getPower(), 
					move.getAccuracy(), move.getPP(), move.getPriority(), move.getAction());
		} else {
			System.out.println("Move " + moveName + " does not exist.");
		}
	}
	
	public Move(String name, Type type, String category, 
			int power, int accuracy, int pp, int priority, Runnable action) {
		setVariables(name, type, category, power, accuracy, pp, priority, action);
	}
	
	private void setVariables(String name, Type type, String category, 
			int power, int accuracy, int pp, int priority, Runnable action) {
		this.name = name;
		this.type = type;
		this.category = category;
		this.power = power;
		this.accuracy = accuracy;
		this.action = action;
	}
	
	public String getName() {return name;}
	public Type getType() {return type;}
	public String getCategory() {return category;}
	public int getPower() {return power;}
	public int getAccuracy() {return accuracy;}
	public int getPP() {return pp;}
	public int getPriority() {return priority;}
	public Runnable getAction() {return action;}
	
	public String toString() {
		return name;
	}
}















