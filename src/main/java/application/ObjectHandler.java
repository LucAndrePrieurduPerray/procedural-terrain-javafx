package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

public class ObjectHandler {
	private static ObjectHandler objectHandler;
	private HashMap<String, TreeMap<Integer, ArrayList<GameObject>>> gameObjectMap;
	
	public ObjectHandler() {
		gameObjectMap = new HashMap<>();
	}
	
	public Map<String, TreeMap<Integer, ArrayList<GameObject>>> getGameObjectMap() {
		return gameObjectMap;
	}
	
	public boolean add(GameObject obj) {
		boolean success;
		SceneManager sceneManager = SceneManager.get();
		HashMap.Entry<String, Scene> entry = sceneManager.getCurrentSceneEntry();
		String key = entry.getKey();
		
		initializeSet(key);
		TreeMap<Integer, ArrayList<GameObject>> objectMap = gameObjectMap.get(key);
		if(!objectMap.keySet().contains(obj.getLayer())) {
			objectMap.put(obj.getLayer(), new ArrayList<>());

			success = objectMap.get(obj.getLayer()).add(obj);
		} else {
			success = objectMap.get(obj.getLayer()).add(obj);
		} 
		updateObjects();
	
		return success;
	}

	public boolean remove(GameObject obj) {
		SceneManager sceneManager = SceneManager.get();
		HashMap.Entry<String, Scene> entry = sceneManager.getCurrentSceneEntry();
		String key = entry.getKey();
		TreeMap<Integer, ArrayList<GameObject>> objectMap = gameObjectMap.get(key);
		
		return objectMap.get(obj.getLayer()).remove(obj);
	}
	
	public void initializeSet(String key) {
		if(gameObjectMap.get(key) == null) {
			gameObjectMap.put(key, new TreeMap<>());
		}
	}

	public void updateObjects() {
		SceneManager sceneManager = SceneManager.get();
		HashMap.Entry<String, Scene> entry = sceneManager.getCurrentSceneEntry();
		Scene currentScene = entry.getValue();
		Group group = (Group)currentScene.getRoot();
		ObservableList<Node> groupList = group.getChildren();
		String key = entry.getKey();

		groupList.removeAll(groupList);
		TreeMap<Integer, ArrayList<GameObject>> objectMap = gameObjectMap.get(key);
		Iterator<ArrayList<GameObject>> iter = objectMap.values().iterator();

		while(iter.hasNext()) {
			ArrayList<GameObject> objectList = iter.next();
			Iterator<GameObject> listIter = objectList.iterator();

			while(listIter.hasNext()) {
				GameObject obj = listIter.next();

				groupList.add(obj);
			}
		}
	}
	
	public static ObjectHandler get() {
		if(objectHandler == null) {
			objectHandler = new ObjectHandler();
		}
		return objectHandler;
	}
}
