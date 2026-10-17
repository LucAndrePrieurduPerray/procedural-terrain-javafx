package application;

import java.util.HashSet;
import java.util.Iterator;

public class CameraHandler {
	private static CameraHandler cameraHandler;
	private HashSet<Camera> cameraSet;
	private Camera activeCamera;
	
	public CameraHandler() {
		cameraSet = new HashSet<>();
	}
	
	public Camera getActiveCamera() {
		return activeCamera;
	}
	
	public void add(Camera camera) {
		cameraSet.add(camera);
		
		if(cameraSet.size() == 1) {
			camera.setActive(true);
			activeCamera = camera;
		}
	}
	
	public void setActive(Camera camera) {
		if(cameraSet.size() <= 1) {
			return;
		}
		
		Iterator<Camera> iter = cameraSet.iterator();
		while(iter.hasNext()) {
			Camera nextCamera = iter.next();
			
			if(nextCamera != camera) {
				nextCamera.setActive(false);
			} else {
				nextCamera.setActive(true);
				activeCamera = camera;
			}
		}
	}
	
	public static CameraHandler get() {
		if(cameraHandler == null) {
			cameraHandler = new CameraHandler();
		}
		return cameraHandler;
	}
}
