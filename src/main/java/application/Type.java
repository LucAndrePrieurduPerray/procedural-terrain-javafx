package application;

import java.util.ArrayList;
import java.util.Arrays;

public enum Type {
	NORMAL {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {ROCK, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, 
					FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG, DRAGON, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {}));
		}
	},
	FIRE {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, WATER, ROCK, DRAGON}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, ELECTRIC, FIGHTING, POISON, GROUND, 
					FLYING, PSYCHIC, GHOST, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {GRASS, ICE, BUG, STEEL}));
		}
	},
	WATER {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {WATER, GRASS, DRAGON}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, ELECTRIC, ICE, FIGHTING, POISON, FLYING, 
					PSYCHIC, BUG, GHOST, DARK, STEEL, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {FIRE, GROUND, ROCK}));
		}
	},
	ELECTRIC {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {ELECTRIC, GRASS, DRAGON}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, ICE, FIGHTING, POISON, PSYCHIC, 
					BUG, ROCK, GHOST, DARK, STEEL, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {WATER, FLYING}));
		}
	},
	GRASS {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, GRASS, POISON, FLYING, BUG, DRAGON}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, ELECTRIC, ICE, FIGHTING, PSYCHIC, GHOST, 
					DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {WATER, GROUND, ROCK}));
		}
	},
	ICE {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, WATER, ICE, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, ELECTRIC, FIGHTING, POISON, PSYCHIC, 
					BUG, ROCK, GHOST, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {GRASS, GROUND, FLYING, DRAGON}));
		}
	},
	FIGHTING {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {POISON, FLYING, PSYCHIC, BUG, FAIRY}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {FIRE, WATER, ELECTRIC, GRASS, FIGHTING, 
					GROUND, DRAGON}));
			strongAgainst.addAll(Arrays.asList(new Type[] {NORMAL, ICE, ROCK, DARK, STEEL}));
		}
	},
	POISON {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {POISON, GROUND, ROCK, GHOST}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, ELECTRIC, ICE, FIGHTING, 
					FLYING, PSYCHIC, BUG, DRAGON, DARK}));
			strongAgainst.addAll(Arrays.asList(new Type[] {GRASS, FAIRY}));
		}
	},
	GROUND {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {GRASS, BUG}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, WATER, ICE, FIGHTING, GROUND, PSYCHIC, 
					GHOST, DRAGON, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {FIRE, ELECTRIC, POISON, ROCK, STEEL}));
		}
	},
	FLYING {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {ELECTRIC, ROCK, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, ICE, POISON, GROUND, 
					FLYING, PSYCHIC, GHOST, DRAGON, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {GRASS, FIGHTING, BUG}));
		}
	},
	PSYCHIC {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {PSYCHIC, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, GRASS, ICE, GROUND, FLYING, 
					BUG, ROCK, GHOST, DRAGON, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {FIGHTING, POISON}));
		}
	},
	BUG {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, FIGHTING, POISON, FLYING, GHOST, STEEL, FAIRY}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, WATER, ELECTRIC, ICE, GROUND, BUG, ROCK, 
					DRAGON}));
			strongAgainst.addAll(Arrays.asList(new Type[] {GRASS, PSYCHIC, DARK}));
		}
	},
	ROCK {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIGHTING, GROUND, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, WATER, ELECTRIC, GRASS, POISON, PSYCHIC, 
					ROCK, GHOST, DRAGON, DARK, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {FIRE, ICE, FLYING, BUG}));
		}
	},
	GHOST {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {DARK}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {FIRE, WATER, ELECTRIC, GRASS, ICE, FIGHTING, 
					POISON, GROUND, FLYING, BUG, ROCK, DRAGON, STEEL, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {PSYCHIC, GHOST}));
		}
	},
	DRAGON {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, 
					FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG, ROCK, GHOST, DARK}));
			strongAgainst.addAll(Arrays.asList(new Type[] {DRAGON}));
		}
	},
	DARK {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIGHTING, DARK, FAIRY}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE, 
					POISON, GROUND, FLYING, BUG, ROCK, DRAGON, STEEL}));
			strongAgainst.addAll(Arrays.asList(new Type[] {PSYCHIC, GHOST}));
		}
	},
	STEEL {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, WATER, ELECTRIC, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, GRASS, FIGHTING, POISON, GROUND, FLYING, 
					PSYCHIC, BUG, GHOST, DRAGON, DARK}));
			strongAgainst.addAll(Arrays.asList(new Type[] {ICE, ROCK, FAIRY}));
		}
	},
	FAIRY {
		private static ArrayList<Type> weakAgainst = new ArrayList<>();
		private static ArrayList<Type> neutralAgainst = new ArrayList<>();
		private static ArrayList<Type> strongAgainst = new ArrayList<>();
		
		public double effectivenessAgainst(Type type) {
			if(weakAgainst.size() == 0) {
				buildArrays();
			}
			if(type == null) {
				return 1;
			}
			
			if(strongAgainst.contains(type)) {
				return 2;
			} else if(neutralAgainst.contains(type)) {
				return 1;
			} else if(weakAgainst.contains(type)) {
				return .5;
			} else {
				return 0;
			}
		}
		
		private void buildArrays() {
			weakAgainst.addAll(Arrays.asList(new Type[] {FIRE, POISON, STEEL}));
			neutralAgainst.addAll(Arrays.asList(new Type[] {NORMAL, WATER, ELECTRIC, GRASS, ICE, GROUND, 
					FLYING, PSYCHIC, BUG, ROCK, GHOST, FAIRY}));
			strongAgainst.addAll(Arrays.asList(new Type[] {FIGHTING, DRAGON, DARK}));
		}
	};
	public abstract double effectivenessAgainst(Type type);
}
































