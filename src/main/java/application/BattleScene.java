package application;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BattleScene {	
	private Group primaryGroup, fightGroup, catchGroup, runGroup;
	private Pokemon currentPlayerPokemon, currentOpponentPokemon;
	private GameObject currentPlayerPokemonPicture, currentOpponentPokemonPicture;
	private int aiLevel;
	private GameLabel playerHealthMeter, opponentHealthMeter;
	
	public BattleScene(Party playerTeam, Party opponentTeam, boolean catchable, int aiLevel) {
		SceneManager sceneManager = SceneManager.get();
		Scene scene = new Scene(new Group(), SceneManager.DEFAULT_WIDTH, SceneManager.DEFAULT_HEIGHT);
		this.aiLevel = aiLevel;
		
		primaryGroup = new Group();
		fightGroup = new Group();
		catchGroup = new Group();
		runGroup = new Group();
		
		sceneManager.addScene("battleScene", scene);		
    	sceneManager.loadScene("battleScene");
    	startBattle(playerTeam, opponentTeam, catchable);
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.H) {
                	SceneManager sceneManager = SceneManager.get();
                	sceneManager.loadScene("mainLevel");
                }
            }
        });
	}
	
	public void startBattle(Party playerTeam, Party opponentTeam, boolean catchable) {
		currentPlayerPokemon = playerTeam.get(0);
		currentOpponentPokemon = opponentTeam.get(0);
		addHealthBars();
		addPokemon();
		addButtons();
		
		switchGroups(null, primaryGroup);
	}
	
	private void addHealthBars() {
		playerHealthMeter = new GameLabel("Health: " + currentPlayerPokemon.getHealth());
		playerHealthMeter.setBounds(100, 25, 200, 50);
		
		opponentHealthMeter = new GameLabel("Health: " + currentOpponentPokemon.getHealth());
		opponentHealthMeter.setBounds(1620, 25, 200, 50);
	}
	
	private void addPokemon() {
		Image playerPokemonImage = currentPlayerPokemon.getImage();
		currentPlayerPokemonPicture = new GameObject(playerPokemonImage, 100, 100, 500, 500, 0);
		currentPlayerPokemonPicture.flipImage();
		
		Image opponentPokemonImage = currentOpponentPokemon.getImage();
		currentOpponentPokemonPicture = new GameObject(opponentPokemonImage, 1320, 100, 500, 500, 0);
	}
	
	private void addButtons() {
		addPrimaryButtonSet();
		addFightButtonSet();
		addCatchButtonSet();
		addRunButtonSet();
	}
	
	private void addPrimaryButtonSet() {
		Button fightButton = new Button("Fight");
		Button catchButton = new Button("Catch");
		Button runButton = new Button("Run");
		
		setButtonBounds(fightButton, 250, 750, 200, 100);
		setButtonBounds(catchButton, 100, 900, 200, 100);
		setButtonBounds(runButton, 400, 900, 200, 100);
		
		ObservableList<Node> groupList = primaryGroup.getChildren();
		groupList.add(fightButton);
		groupList.add(catchButton);
		groupList.add(runButton);
		
		fightButton.setOnAction(e -> {switchGroups(primaryGroup, fightGroup);});
		catchButton.setOnAction(e -> {switchGroups(primaryGroup, catchGroup);});
		runButton.setOnAction(e -> {switchGroups(primaryGroup, runGroup);});
	}
	
	private void addFightButtonSet() {
		Button[] moveButtonArr = new Button[4];
		
		for(int i = 0; i < 4; i++) {
			Move move = currentPlayerPokemon.getCurrentMoves()[i];
			String moveName = move.getName();
			
			if(moveName != null) {
				moveButtonArr[i] = new Button(moveName);
				moveButtonArr[i] = setFightButtonAction(moveButtonArr[i], move);
			} else {
				moveButtonArr[i] = new Button("N/A");
			}
		}
		
		for(int i = 0; i < 4; i++) {
			setButtonBounds(moveButtonArr[i], 100 + i % 2 * 300, 750 + i / 2 * 150, 200, 100);
		}
		ObservableList<Node> groupList = fightGroup.getChildren();
		
		for(int i = 0; i < 4; i++) {
			groupList.add(moveButtonArr[i]);
		}
		Button returnButton = new Button("<-");
		setButtonBounds(returnButton, 10, 1020, 50, 50);
		groupList.add(returnButton);
		
		returnButton.setOnAction(e -> {switchGroups(fightGroup, primaryGroup);});
	}
	
	private Button setFightButtonAction(Button button, Move move) {
		button.setOnAction(e -> {
			Move opponentMove = null;
			
			switch(aiLevel) {
			case 0:
				while(opponentMove.getName() == null) {
					opponentMove = currentOpponentPokemon.getCurrentMoves()[(int)(Math.random() * 4)];
				}
			}
			
			if(move.getPriority() == opponentMove.getPriority()) {
				int playerPokemonSpeed = currentPlayerPokemon.getStats()[5];
				int opponentPokemonSpeed = currentOpponentPokemon.getStats()[5];
				
				if(playerPokemonSpeed > opponentPokemonSpeed) {
					damage(currentOpponentPokemon, currentPlayerPokemon, move);
					damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
				} else if(opponentPokemonSpeed > playerPokemonSpeed) {
					damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
					damage(currentOpponentPokemon, currentPlayerPokemon, move);
				} else {
					if((int)(Math.random() * 2) == 0) {
						damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
						damage(currentOpponentPokemon, currentPlayerPokemon, move);
					} else {
						damage(currentOpponentPokemon, currentPlayerPokemon, move);
						damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
					}
				}
			} else if(move.getPriority() > opponentMove.getPriority()) {
				damage(currentOpponentPokemon, currentPlayerPokemon, move);
				damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
			} else {
				damage(currentPlayerPokemon, currentOpponentPokemon, opponentMove);
				damage(currentOpponentPokemon, currentPlayerPokemon, move);
			}
		});
		return button;
	}
	
	private void damage(Pokemon pokemon1, Pokemon pokemon2, Move move) {
		int level = 0, movePower = 0, moveStat = 0, moveDefense = 0;

		level = pokemon2.getLevel();
		movePower = move.getPower();
		
		switch(move.getCategory()) {
		case "Physical":
			moveStat = pokemon2.getStats()[1];
			moveDefense = pokemon1.getStats()[2];
			break;
		case "Special":
			moveStat = pokemon2.getStats()[3];
			moveDefense = pokemon1.getStats()[4];
			break;
		}
		double criticalValue = 1.0, randomValue = 0.0, stabValue = 1.0, effectiveness = 1.0;
		
		if((int)(Math.random() * 10000 + 1) <= 417) {
			criticalValue = 1.5;
		}
		randomValue = (Math.random() * 16 + 85) / 100.0;
		Type moveType = move.getType();
		
		if(moveType.equals(pokemon2.getPrimaryType()) || moveType.equals(pokemon2.getSecondaryType())) {
			stabValue = 1.5;
		}
		effectiveness = moveType.effectivenessAgainst(pokemon1.getPrimaryType()) * moveType.effectivenessAgainst(pokemon1.getSecondaryType());
		
		//(((2 * level) / 5 + 2) * movePower * (moveStat / moveDefense) / 50 + 2) * 
		//weather * citicalValue * randomValue * stabValue * effectiveness * burn * other
	}
	
	private void addCatchButtonSet() {
		Button[] moveButtonArr = new Button[4];
		String[] buttonNames = {"Poke Ball", "Great Ball", "Ultra Ball", "Master Ball"};
		
		for(int i = 0; i < 4; i++) {
			moveButtonArr[i] = new Button(buttonNames[i]);
		}
		
		for(int i = 0; i < 4; i++) {
			setButtonBounds(moveButtonArr[i], 100 + i % 2 * 300, 750 + i / 2 * 150, 200, 100);
		}
		ObservableList<Node> groupList = catchGroup.getChildren();
		
		for(int i = 0; i < 4; i++) {
			groupList.add(moveButtonArr[i]);
		}
		Button returnButton = new Button("<-");
		setButtonBounds(returnButton, 10, 1020, 50, 50);
		groupList.add(returnButton);
		
		returnButton.setOnAction(e -> {switchGroups(catchGroup, primaryGroup);});
	}
	
	private void addRunButtonSet() {
		Button runButton = new Button("Confirm");
		setButtonBounds(runButton, 250, 825, 200, 100);
		
		ObservableList<Node> groupList = runGroup.getChildren();
		groupList.add(runButton);
		
		Button returnButton = new Button("<-");
		setButtonBounds(returnButton, 10, 1020, 50, 50);
		groupList.add(returnButton);
		
		returnButton.setOnAction(e -> {switchGroups(runGroup, primaryGroup);});
	}
	
	private void switchGroups(Group group1, Group group2) {
		SceneManager sceneManager = SceneManager.get();
		Scene scene = sceneManager.getCurrentScene();
		Group root = (Group)scene.getRoot();
		ObservableList<Node> rootList = root.getChildren();
		
		rootList.remove(group1);
		rootList.add(group2);
	}
	
	private void setButtonBounds(Button button, double x, double y, double width, double height) {
		button.setLayoutX(x);
		button.setLayoutY(y);
		button.setMinSize(width, height);
		button.setPrefSize(width, height);
		button.setMaxSize(width, height);
	}
}











