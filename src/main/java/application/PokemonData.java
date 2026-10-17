package application;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class PokemonData extends HashMap<String, Pokemon> {
	private static PokemonData pokemonData;
	
	public PokemonData() {		
		initializePokemon();
	}
	
	private void initializePokemon() {
		put("Squirtle", new Pokemon("Squirtle", new Image(getClass().getResourceAsStream("/images/SubstituteTemplate.jpg")), generateMovePoolMap("Squirtle"), 
				Type.WATER, null, new int[] {44, 48, 65, 50, 64, 43}, "Medium Slow", 63, new int[] {0, 0, 1, 0, 0, 0}, 16, null, 
				new Ability("Torrent", null, "Rain Dish"), 45));
	}
	
	private TreeMap<Integer, ArrayList<Move>> generateMovePoolMap(String pokemonName) {
		TreeMap<Integer, ArrayList<Move>> movePool = new TreeMap<>();
		InputStream file = getClass().getResourceAsStream("/movepools/" + pokemonName + "MovePool");
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
			int index = scanner.nextInt();
			movePool.put(index, new ArrayList<>());
			
			String[] moves = scanner.nextLine().split(", ");
			moves[0] = moves[0].substring(1);
			
			for(String move : moves) {
				movePool.get(index).add(new Move(move));
			}
		}
		scanner.close();
		return movePool;
	}
	
	public static PokemonData get() {
		if(pokemonData == null) {
			pokemonData = new PokemonData();
		}
		return pokemonData;
	}
}
