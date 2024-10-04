package page;

import component.UserDataInput;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class SignUp {
	
	UserDataInput userDataInput;
	BorderPane containerBp;
	
	private void init() {
		userDataInput = new UserDataInput();
		containerBp = new BorderPane();
	}
	
	private void arrange() {
		containerBp.setCenter(userDataInput.getRoot());
	}
	
	private void refine() {
		Image backgroundImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\Images\\SignUp bg.jpg");

		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(1000, 562, false, false, false, false)
		);
		Background backgroundWithImage = new Background(background);
		containerBp.setBackground(backgroundWithImage);
		
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.containerBp;
	}
	
	public SignUp() {
		init();
		arrange();
		refine();
	}
}
