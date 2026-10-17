package application;

public class Nature {
	private double[] statMultiplierArr;
	
	public Nature() {
		statMultiplierArr = new double[6];
		for(int i = 0; i < 6; i++) {
			statMultiplierArr[i] = 1.0;
		}
		
		int randomNum = (int)(Math.random() * 5 + 1);
		statMultiplierArr[randomNum] += .1;
		randomNum = (int)(Math.random() * 5 + 1);
		statMultiplierArr[randomNum] -= .1;
	}
	
	public double get(int index) {
		return statMultiplierArr[index];
	}
}
