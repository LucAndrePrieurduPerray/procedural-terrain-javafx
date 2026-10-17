package application;

import java.util.HashMap;

@SuppressWarnings("serial")
public class AbilityData extends HashMap<String, Ability> {
	private static AbilityData abilityData;
	
	public AbilityData() {
		initializeAbilities();
	}
	
	private void initializeAbilities() {
		put("Torrent", new Ability(() -> torrent()));
	}
	
	private void torrent() {
		
	}
	
	public static AbilityData get() {
		if(abilityData == null) {
			abilityData = new AbilityData();
		}
		return abilityData;
	}
}
