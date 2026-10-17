package application;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class GameLabel extends Label {
	public GameLabel() {
		super();
		addToScene();
	}
	
	public GameLabel(String str) {
		super(str);
		addToScene();
	}
	
	public GameLabel(String str, Node graphic) {
		super(str, graphic);
		addToScene();
	}
	
	private void addToScene() {
		SceneManager sceneManager = SceneManager.get();
		Scene scene = sceneManager.getCurrentScene();
		Group root = (Group)scene.getRoot();
		ObservableList<Node> rootList = root.getChildren();
		rootList.add(this);
	}
	
	public void setBounds(double x, double y, double width, double height) {
		setLayoutX(x);
		setLayoutY(y);
		
		setMinSize(width, height);
		setPrefSize(width, height);
		setMaxSize(width, height);
	}
}








