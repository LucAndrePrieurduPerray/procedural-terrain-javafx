package application;

public class Weather {
	private static Weather weather;
	private Type currentWeatherType;
	
	public Weather() {
		
	}
	
	public void setToSunnyDay() {
		currentWeatherType = Type.FIRE;
	}
	
	public void setToRain() {
		currentWeatherType = Type.WATER;
	}
	
	public double getEffectivenessOf(Type type) {
		double effectiveness = 1.0;
		
		if(currentWeatherType.equals(type)) {
			effectiveness = 1.5;
		} else if(!currentWeatherType.equals(null) && type.equals(Type.FIRE) || type.equals(Type.WATER)) {
			effectiveness = 0.5;
		}
		return effectiveness;
	}
	
	public static Weather get() {
		if(weather == null) {
			weather = new Weather();
		}
		return weather;
	}
}
