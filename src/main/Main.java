package main;

import page.SignIn;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@SuppressWarnings("exports")
public class Main extends Application{
	
	public static Image logoImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\Images\\Logo.png");
	
	public static Scene primaryScene;
	public static Stage primaryStage;
	
	@SuppressWarnings("static-access")
	public void start(Stage primaryStage) throws Exception {
		primaryScene = new Scene(new SignIn().getParent());
		this.primaryStage = primaryStage;
		primaryStage.setScene(primaryScene);
		primaryStage.setWidth(1000);
		primaryStage.setHeight(562);
		primaryStage.setTitle("PapaPets");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
