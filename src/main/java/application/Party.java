package application;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Party extends ArrayList<Pokemon> {
	public boolean addPokemon(Pokemon pokemon) {
		if(size() < 6) {
			add(pokemon);
			return true;
		}
		return false;
	}
}
