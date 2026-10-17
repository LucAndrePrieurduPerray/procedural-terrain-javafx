package application;

import javafx.scene.shape.Rectangle;

public class Trigger extends Rectangle {

	public Trigger(double x, double y, double width, double height) {
		setBounds(x, y, width, height);
		setVisible(false);
	}
	
	public void setBounds(double x, double y, double width, double height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
}