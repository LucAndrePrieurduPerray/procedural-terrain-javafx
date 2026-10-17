package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends GameObject {
	private double speed;
	private boolean goUp, goDown, goLeft, goRight, running;
	
	public Player() {
		super(2);

		Image playerImage = new Image(getClass().getResourceAsStream("/images/PokemonSpriteDownIdle.png"));
		setImage(playerImage);
		setBounds(0, 0, 78, 78);
		setCollider(12, 0, -12, 0);
		
		SceneManager sceneManager = SceneManager.get();
		Scene scene = sceneManager.getCurrentScene();
		speed = 3;
		BackgroundProcesses.addAction(() -> movePlayer());
		BackgroundProcesses.addAction(() -> animatePlayer());
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @SuppressWarnings("incomplete-switch")
			@Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    
                    	goUp = true; 
                    	break;
                    case S:  
                    	goDown = true; 
                    	break;
                    case A:  
                    	goLeft = true; 
                    	break;
                    case D: 
                    	goRight = true; 
                    	break;
                    case SHIFT: 
                    	running = true; 
                    	break;
                }
            }
        });
 
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @SuppressWarnings("incomplete-switch")
			@Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    
                    	goUp = false; 
                    	break;
                    case S:  
                    	goDown = false; 
                    	break;
                    case A:  
                    	goLeft = false; 
                    	break;
                    case D: 
                    	goRight = false; 
                    	break;
                    case SHIFT: 
                    	running = false; 
                    	break;
                }

                if(event.getCode() == KeyCode.G) {
                	Party tempPlayerParty = new Party();
                	tempPlayerParty.addPokemon(new Pokemon("Squirtle", 10));
                	
                	Party tempOpponentParty = new Party();
                	tempOpponentParty.addPokemon(new Pokemon("Squirtle", 10));
                	new BattleScene(tempPlayerParty, tempOpponentParty, false, 0);
                }
            }
        });
	}
	
	private void movePlayer() {
		boolean hasMoved = false;
		double initialX = getX();
		double initialY = getY();

		double diagonalMultiplier = 1;
		int movementNum = 0;

		if(goUp) {movementNum++;}
		if(goDown) {movementNum++;}
		if(goLeft) {movementNum++;}
		if(goRight) {movementNum++;}

		if(movementNum == 2) {
			diagonalMultiplier = Math.sqrt(2) / 2;
		}

		if(goUp) {
			double moveDistance = speed * diagonalMultiplier;
			
			if(running) {
				moveDistance *= 2;
			}
			setBounds(getX(), getY() - moveDistance, getFitWidth(), getFitHeight());
			
			if(hasCollided()) {
				setBounds(getX(), getY() + moveDistance, getFitWidth(), getFitHeight());
			} else {
				hasMoved = true;
			}
		}
		if(goDown) {
			double moveDistance = speed * diagonalMultiplier;
			
			if(running) {
				moveDistance *= 2;
			}
			setBounds(getX(), getY() + moveDistance, getFitWidth(), getFitHeight());
			
			if(hasCollided()) {
				setBounds(getX(), getY() - moveDistance, getFitWidth(), getFitHeight());
			} else {
				hasMoved = true;
			}
		}
		if(goLeft) {
			double moveDistance = speed * diagonalMultiplier;
			
			if(running) {
				moveDistance *= 2;
			}
			setBounds(getX() - moveDistance, getY(), getFitWidth(), getFitHeight());
			
			if(hasCollided()) {
				setBounds(getX() + moveDistance, getY(), getFitWidth(), getFitHeight());
			} else {
				hasMoved = true;
			}
		}
		if(goRight) {
			double moveDistance = speed * diagonalMultiplier;
			
			if(running) {
				moveDistance *= 2;
			}
			setBounds(getX() + moveDistance, getY(), getFitWidth(), getFitHeight());
			
			if(hasCollided()) {
				setBounds(getX() - moveDistance, getY(), getFitWidth(), getFitHeight());
			} else {
				hasMoved = true;
			}
		}

		if(hasMoved) {
			CameraHandler cameraHandler = CameraHandler.get();
			Camera camera = cameraHandler.getActiveCamera();
			camera.setBounds(camera.getX() + getX() - initialX, camera.getY() + getY() - initialY);
		}
	}

	private ArrayList<Image> upImages;
	@SuppressWarnings("unused")
	private Iterator<Image> upIter;

	private ArrayList<Image> downImages;
	private Iterator<Image> downIter;

	private ArrayList<Image> leftImages;
	@SuppressWarnings("unused")
	private Iterator<Image> leftIter;

	private ArrayList<Image> rightImages;
	@SuppressWarnings("unused")
	private Iterator<Image> rightIter;

	private int timingNum;
	private int currentDir;
	private Iterator<Image> currentIter;

	private void animatePlayer() {
		if(upImages == null) {
			initializeAnimatorVariables();
		}
		int movingDir = getMovingDirection();

		if(movingDir != -1) {
			timingNum++;

			if(movingDir != currentDir) {
				timingNum = 0;
				resetIterators(movingDir);
				currentIter.next();
				setImage(currentIter.next());
				currentDir = movingDir;
			} else if(running && timingNum == 5) {
				timingNum = 0;

				if(!currentIter.hasNext()) {
					resetIterators(movingDir);
				}
				setImage(currentIter.next());
			} else if(timingNum == 10) {
				timingNum = 0;

				if(!currentIter.hasNext()) {
					resetIterators(movingDir);
				}
				setImage(currentIter.next());
			}
		} else {
			timingNum = 0;
			resetIterators(currentDir);
			setImage(currentIter.next());
		}
	}

	private void initializeAnimatorVariables() {
		upImages = new ArrayList<>();
		upImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteUpIdle.png")));

		for(int i = 1; i < 4; i++) {
			upImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteUp" + i + ".png")));
		}
		upIter = upImages.iterator();

		downImages = new ArrayList<>();
		downImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteDownIdle.png")));

		for(int i = 1; i < 4; i++) {
			downImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteDown" + i + ".png")));
		}
		downIter = downImages.iterator();

		leftImages = new ArrayList<>();
		leftImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteLeftIdle.png")));

		for(int i = 1; i < 4; i++) {
			leftImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteLeft" + i + ".png")));
		}
		leftIter = leftImages.iterator();

		rightImages = new ArrayList<>();
		rightImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteRightIdle.png")));

		for(int i = 1; i < 4; i++) {
			rightImages.add(new Image(getClass().getResourceAsStream("/images/PokemonSpriteRight" + i + ".png")));
		}
		rightIter = rightImages.iterator();

		timingNum = 0;
		currentDir = 2;
		currentIter = downIter;
	}

	private int getMovingDirection() {
		int dir = -1;

		if(goLeft && !goRight) {
			dir = 3;
		} else if(goRight && !goLeft) {
			dir = 4;
		} if(goUp && !goDown) {
			dir = 1;
		} else if(goDown && !goUp) {
			dir = 2;
		}
		return dir;
	}

	private void resetIterators(int movingDir) {
		upIter = upImages.iterator();
		downIter = downImages.iterator();
		leftIter = leftImages.iterator();
		rightIter = rightImages.iterator();

		switch(movingDir) {
			case 1:
				currentIter = upImages.iterator();
				break;

			case 2:
				currentIter = downImages.iterator();
				break;

			case 3:
				currentIter = leftImages.iterator();
				break;
			
			case 4:
				currentIter = rightImages.iterator();
				break;
		}
	}
	
	private boolean hasCollided() {
		SceneManager sceneManager = SceneManager.get();
		String key = sceneManager.getCurrentSceneEntry().getKey();
		ObjectHandler objectHandler = ObjectHandler.get();
		TreeMap<Integer, ArrayList<GameObject>> gameObjectMap = objectHandler.getGameObjectMap().get(key);
		
		Iterator<ArrayList<GameObject>> iter = gameObjectMap.values().iterator();
		boolean hasCollided = false;
		
		while(iter.hasNext()) {
			ArrayList<GameObject> objList = iter.next();
			Iterator<GameObject> objIter = objList.iterator();

			while(objIter.hasNext()) {
				GameObject obj = objIter.next();
			
				if(this != obj && isCollidingWith(obj)) {
					hasCollided = true;
				}
			}
		}
		return hasCollided;
	}
}








