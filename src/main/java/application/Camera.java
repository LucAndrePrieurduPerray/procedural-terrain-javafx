package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class Camera {
	private boolean isActive;
	private CameraHandler cameraHandler;
	private double x, y;
	private double prevX, prevY;
	
	public Camera() {
		isActive = false;
		cameraHandler = CameraHandler.get();
		cameraHandler.add(this);
		
		prevX = 0;
		prevY = 0;
		setBounds(0, 0);
	}
	
	public Camera(double x, double y) {
		isActive = false;
		cameraHandler = CameraHandler.get();
		cameraHandler.add(this);
		
		prevX = 0;
		prevY = 0;
		setBounds(x, y);
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	
	public void setActive(boolean active) {
		isActive = active;
		
		if(active) {
			cameraHandler.setActive(this);
		}
	}
	
	public void setBounds(double x, double y) {
		this.x = x;
		this.y = y;
		updateCameraPosition();
	}
	
	private void updateCameraPosition() {
		if(!isActive) {
			return;
		}
		
		ObjectHandler objectHandler = ObjectHandler.get();
		SceneManager sceneManager = SceneManager.get();
		String currentKey = sceneManager.getCurrentSceneEntry().getKey();
		TreeMap<Integer, ArrayList<GameObject>> gameObjectMap = objectHandler.getGameObjectMap().get(currentKey);
		Iterator<ArrayList<GameObject>> iter = gameObjectMap.values().iterator();
				
		while(iter.hasNext()) {
			ArrayList<GameObject> objList = iter.next();
			Iterator<GameObject> objIter = objList.iterator();

			while(objIter.hasNext()) {
				GameObject obj = objIter.next();

				obj.setBounds(obj.getX() - getX() + prevX, obj.getY() - getY() + prevY, obj.getFitWidth(), obj.getFitHeight());
			}
		}
		prevX = getX();
		prevY = getY();
	}
}









