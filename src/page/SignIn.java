package page;

import java.sql.ResultSet;
import java.sql.SQLException;

import component.MyBorder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.Admin;
import model.Customer;
import model.User;
import util.Connect;

public class SignIn {
	
	Label hanifLb, graceLb, danielLb, edbertLb;
	FlowPane watermarkFp;
	
	User user;
	
	ImageView logoIv;
	Label signInLb, usernameLb, passwordLb, hyperLinkLb;
	TextField usernameTf;
	PasswordField passwordPf;
	Button signInBt;
	Hyperlink signUpHp;
	
	VBox usernameVb, passwordHb, signInVb, containerVb;
	HBox hyperLinkHb;
	
	BorderPane containerBp;
	
	Connect connect = Connect.getConnection();
	
	private void init() {
		hanifLb = new Label("M. Syamsi Hanif B. - 2602112335");
		graceLb = new Label("Grace Cleverisa I. G. - 2602119404");
		danielLb = new Label("Daniel Shira - 2602120886");
		edbertLb = new Label("Edbert Kifli - 2602122784");
		
		watermarkFp = new FlowPane(40, 0);
		
		logoIv = new ImageView(Main.logoImage);
		
		signInLb = new Label("Sign In");
		usernameLb = new Label("Username");
		passwordLb = new Label("Password");
		hyperLinkLb = new Label("Need an account?");
		
		usernameTf = new TextField();
		passwordPf = new PasswordField();
		
		signInBt = new Button("Sign In");
		
		signUpHp = new Hyperlink("Sign Up");
		
		usernameVb = new VBox();
		passwordHb = new VBox();
		hyperLinkHb = new HBox();
		
		signInVb = new VBox(5);
		containerVb = new VBox(20);
		containerBp = new BorderPane();
		
	}

	
	private void setup() {
		
		watermarkFp.getChildren().addAll(hanifLb, graceLb, danielLb, edbertLb);

		usernameVb.getChildren().addAll(usernameLb, usernameTf);
		passwordHb.getChildren().addAll(passwordLb, passwordPf);

		hyperLinkHb.getChildren().addAll(hyperLinkLb, signUpHp);

		signInVb.getChildren().addAll(signInLb, usernameVb, passwordHb, signInBt, hyperLinkHb);
		containerVb.getChildren().addAll(logoIv, signInVb);

		usernameTf.setPromptText("Enter username");
		passwordPf.setPromptText("Enter password");

		containerBp.setTop(watermarkFp);
		containerBp.setCenter(containerVb);

		signInBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				user = validateUser(usernameTf.getText(), passwordPf.getText());
				if (user != null) {
					if (user instanceof Admin) {
						Main.primaryScene.setRoot(new HomeAdmin(user).getParent());
					} else {
						Main.primaryScene.setRoot(new HomeCustomer(user).getParent());
					}
					
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password");
					alert.showAndWait();
				}
			}
		});
		
		signUpHp.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Main.primaryScene.setRoot(new SignUp().getParent());
			}
		});
	}
	
	private void refine() {
		hanifLb.setFont(Font.font(null, FontWeight.BOLD, 12));
		graceLb.setFont(Font.font(null, FontWeight.BOLD, 12));
		danielLb.setFont(Font.font(null, FontWeight.BOLD, 12));
		edbertLb.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		watermarkFp.setAlignment(Pos.CENTER);
		
		signInLb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		signInLb.setPadding(new Insets(0, 0, 10, 0));
	
		passwordHb.setPadding(new Insets(0, 0, 15, 0));
		
		hyperLinkHb.setAlignment(Pos.CENTER);
		
		logoIv.setFitWidth(140);
		logoIv.setFitHeight(100);
		
		signInVb.setMaxSize(200, 250);
		signInVb.setPadding(new Insets(20, 20, 20, 20));
		signInVb.setBorder(new MyBorder().getGrayBorder());
		
		signInVb.setAlignment(Pos.CENTER);
		containerVb.setAlignment(Pos.CENTER);
		
		
		Image backgroundImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\SignIn bg.jpg");
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(1000, 562, false, false, false, false));
		Background backgroundWithImage = new Background(background);
		containerBp.setBackground(backgroundWithImage);
		
		signInVb.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		
	}
	
	private User validateUser(String username, String password) {
		String customerQuery = String.format("SELECT * FROM MsCustomer WHERE CustomerUsername='%s' AND CustomerPassword='%s'", username, password);

		String adminQuery = String.format("SELECT * FROM MsAdmin WHERE AdminUsername='%s' AND AdminPassword='%s'", username, password);

		try {
			ResultSet customerResultSet = connect.executeQuery(customerQuery);
			if (customerResultSet.next()) {
				return new Customer(
					customerResultSet.getString("CustomerID"),
					customerResultSet.getString("CustomerUsername"),
					customerResultSet.getString("CustomerEmail"),
					customerResultSet.getString("CustomerGender"),
					customerResultSet.getString("CustomerPhone"),
					customerResultSet.getString("CustomerAddress"),
					customerResultSet.getString("CustomerPassword")
				);
			}

			ResultSet adminResultSet = connect.executeQuery(adminQuery);
			if (adminResultSet.next()) {
				return new Admin(
					adminResultSet.getString("AdminID"),
					adminResultSet.getString("AdminUsername"),
					adminResultSet.getString("AdminEmail"),
					adminResultSet.getString("AdminGender"),
					adminResultSet.getString("AdminPhone"),
					adminResultSet.getString("AdminAddress"),
					adminResultSet.getString("AdminPassword")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; 
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.containerBp;
	}
	
	public SignIn() {
		init();
		setup();
		refine();
	}
	
}
