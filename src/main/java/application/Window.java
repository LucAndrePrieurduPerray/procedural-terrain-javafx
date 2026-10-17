package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Window extends Application {
	private static Window window = null;
	private static Stage stage;
	
	public static void main(String[] args) {
		//--module-path C:\Users\lucpr\Downloads\javafx-sdk-20.0.1\lib --add-modules=javafx.controls,javafx.fxml
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = new Window();
		stage = primaryStage;
		SceneManager.get();

		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		primaryStage.show();

		BackgroundProcesses.get();
		new MainLevel();
	}
		
	public static Stage getStage() {return stage;}
	public static Window get() {return Window.window;}
	
	//https://stackoverflow.com/questions/47879463/2d-camera-in-javafx
}













