package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.image.Image;

public class Pokemon {
	private String name, nickname;
	private Image image;
	private Move[] currentMoves;
	private TreeMap<Integer, ArrayList<Move>> movePoolMap;
	private Type primaryType, secondaryType;
	/*
	 * 0 - health
	 * 1 - attack
	 * 2 - defense
	 * 3 - sp. attack
	 * 4 - sp. defense
	 * 5 - speed
	 */
	private int[] baseStats, stats;
	private Nature nature;
	private int level, expAmt;
	private String expGain;
	private int expYield;
	private int[] ivs, evs, evYield;
	private int evolutionLevel;
	private Pokemon evolution;
	private Ability ability;
	private int catchRate;
	private double health;
	
	public Pokemon(String pokemonName, int level) {
		PokemonData pokemonData = PokemonData.get();
		Pokemon pokemon = pokemonData.get(pokemonName);
		
		setVariables(pokemon.getName(), pokemon.getImage(), pokemon.getMovePoolMap(), 
				pokemon.getPrimaryType(), pokemon.getSecondaryType(), pokemon.getBaseStats().clone(), 
				pokemon.getExpGain(), pokemon.getExpYield(), 
				pokemon.getEvYield(), pokemon.getEvolutionLevel(), pokemon.getEvolution(), 
				pokemon.getAbility(), pokemon.getCatchRate());
		this.level = level;
		generateMoves();
		nature = new Nature();
		setIvs();
		evs = new int[6];
		updateStats();
		health = stats[0];
	}
	
	public Pokemon(String name, Image image, TreeMap<Integer, ArrayList<Move>> movePoolMap, 
			Type primaryType, Type secondaryType, int[] baseStats, 
			String expGain, int expYield, int[] evYield, int evolutionLevel, 
			Pokemon evolution, Ability ability, int catchRate) {
		setVariables(name, image, movePoolMap, primaryType, secondaryType, baseStats, 
				expGain, expYield, evYield, evolutionLevel, evolution, ability, catchRate);
	}
	
	private void setVariables(String name, Image image, TreeMap<Integer, ArrayList<Move>> movePoolMap, 
			Type primaryType, Type secondaryType, int[] baseStats, 
			String expGain, int expYield, int[] evYield, int evolutionLevel, 
			Pokemon evolution, Ability ability, int catchRate) {
		this.name = name;
		this.image = image;
		this.movePoolMap = movePoolMap;
		this.primaryType = primaryType;
		this.secondaryType = secondaryType;
		this.baseStats = baseStats.clone();
		this.expGain = expGain;
		this.expYield = expYield;
		this.evYield = evYield;
		this.evolutionLevel = evolutionLevel;
		this.evolution = evolution;
		this.ability = ability;
		this.catchRate = catchRate;
	}
	
	public String getName() {return name;}
	public String getNickName() {return nickname;}
	public Image getImage() {return image;}
	public Move[] getCurrentMoves() {return currentMoves;}
	public TreeMap<Integer, ArrayList<Move>> getMovePoolMap() {return movePoolMap;}
	public Type getPrimaryType() {return primaryType;}
	public Type getSecondaryType() {return secondaryType;}
	public int[] getBaseStats() {return baseStats;}
	public int[] getStats() {return stats;}
	public Nature getNature() {return nature;}
	public int getLevel() {return level;}
	public int getExpAmt() {return expAmt;}
	public String getExpGain() {return expGain;}
	public int getExpYield() {return expYield;}
	public int[] getIvs() {return ivs;}
	public int[] getEvs() {return evs;}
	public int[] getEvYield() {return evYield;}
	public int getEvolutionLevel() {return evolutionLevel;}
	public Pokemon getEvolution() {return evolution;}
	public Ability getAbility() {return ability;}
	public int getCatchRate() {return catchRate;}
	public double getHealth() {return health;}
	
	public void setHealth(double health) {this.health = health;}
	
	private void generateMoves() {
		currentMoves = new Move[4];
		ArrayList<Move> possibleMoves = new ArrayList<>();
		
		for(Map.Entry<Integer, ArrayList<Move>> entry : movePoolMap.entrySet()) {
			if(entry.getKey() <= level) {
				possibleMoves.addAll(entry.getValue());
			}
		}
		
		if(possibleMoves.get(3).getName() != null) {
			Collections.shuffle(possibleMoves);
		}
		
		for(int i = 0; i < 4; i++) {
			currentMoves[i] = possibleMoves.get(i);
		}
	}
	
	private void setIvs() {
		ivs = new int[6];
		
		for(int i = 0; i < 6; i++) {
			ivs[i] = (int)(Math.random() * 32);
		}
	}
	
	public void updateStats() {
		stats = new int[6];
		
		stats[0] = (int)(((2 * baseStats[0] + ivs[0] + evs[0] / 4.0) * level) / 100.0 + level + 10);
		for(int i = 1; i < 6; i++) {
			stats[i] = (int)((((2 * baseStats[i] + ivs[i] + evs[i] / 4.0) * level) / 100.0 + 5) * nature.get(i));
		}
	}
}













