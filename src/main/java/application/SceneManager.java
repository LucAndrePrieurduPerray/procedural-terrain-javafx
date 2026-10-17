package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	private static SceneManager sceneManager;
	private static HashMap<String, Scene> sceneMap;
    private static final String DEFAULT = "default";
	private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int DEFAULT_WIDTH = (int)screen.getWidth();
	public static final int DEFAULT_HEIGHT = (int)screen.getHeight();
	private Entry<String, Scene> currentEntry;
	
	public SceneManager() {
		sceneMap = new HashMap<>();
		sceneMap.put(DEFAULT, new Scene(new Group(), DEFAULT_WIDTH, DEFAULT_HEIGHT));
		loadScene(DEFAULT);
		
		ObjectHandler objectHandler = ObjectHandler.get();
		objectHandler.initializeSet(DEFAULT);
	}
	
	public void addScene(String key, Scene scene) {
		sceneMap.put(key, scene);
		ObjectHandler objectHandler = ObjectHandler.get();
		objectHandler.initializeSet(key);
	}
	
	public void loadScene(String key) {
		Iterator<Entry<String, Scene>> iter = sceneMap.entrySet().iterator();
		
		while(iter.hasNext()) {
			Entry<String, Scene> entry = iter.next();
			
			if(entry.getKey().equals(key)) {
				currentEntry = entry;
			}
		}
		Stage stage = Window.getStage();
		stage.setScene(currentEntry.getValue());
	}
	
	public Scene getCurrentScene() {return currentEntry.getValue();}
	public Entry<String, Scene> getCurrentSceneEntry() {return currentEntry;}
	
	public static SceneManager get() {
		if(sceneManager == null) {
			sceneManager = new SceneManager();
		}
		return sceneManager;
	}
}
