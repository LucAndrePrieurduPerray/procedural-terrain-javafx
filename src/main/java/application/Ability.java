package application;

public class Ability {
	private Runnable action;
	
	public Ability(String ability1, String ability2, String hiddenAbility) {
		AbilityData abilityData = AbilityData.get();
		Ability ability;
		
		if(hiddenAbility != null && (int)(Math.random() * 250) == 0) {
			ability = abilityData.get(hiddenAbility);
		} else {
			if(ability2 == null || (int)(Math.random() * 2) == 0) {
				ability = abilityData.get(ability1);
			} else {
				ability = abilityData.get(ability2);
			}
		}
		action = ability.getAction();
	}
	
	public Ability(Runnable action) {
		this.action = action;
	}
	
	public Runnable getAction() {return action;}
}
