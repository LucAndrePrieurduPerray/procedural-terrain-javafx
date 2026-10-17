package application;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class GameButton extends Button {
	public GameButton() {
		super();
		addToScene();
	}
	public GameButton(String text) {
		super(text);
		addToScene();
	}
	public GameButton(String text, Node graphic) {
		super(text, graphic);
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







