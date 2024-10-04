package component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import page.SignIn;
import util.Connect;

public class UserDataInput {
	
	Label signUpLb;
	Label userTypeLb;
	Label customerRadioLb;
	Label adminRadioLb;
	Label usernameLb;
	Label genderLb;
	Label maleRadioLb;
	Label femaleRadioLb;
	Label emailLb;
	Label phoneNumberLb;
	Label passwordLb;
	Label confirmPasswordLb;
	Label addressLb;
	Label hyperlinkLb;
	
	RadioButton customerRb;
	RadioButton adminRb;
	RadioButton maleRb;
	RadioButton femaleRb;
	
	TextField usernameTf;
	TextField emailTf;
	TextField phoneNumberTf;
	PasswordField passwordPF;
	PasswordField confirmPasswordPf;
	TextField addressTf;
	
	Button signUpBt;
	
	Hyperlink signInHp;
	
	ToggleGroup genderTg;
	
	HBox userTypeRadioHb;
	HBox genderRadioHb;
	
	HBox signUpButtonHb;
	HBox hyperLinkHb;
	
	VBox userDataInputVb;
	BorderPane containerBp;
	
	Connect connect = Connect.getConnection();
		
	private void init() {
		signUpLb = new Label("Sign Up");
		userTypeLb = new Label("User Type");
		customerRadioLb = new Label("Customer");
		adminRadioLb = new Label("Admin");
		usernameLb = new Label("Username");
		genderLb = new Label("Gender");
		maleRadioLb = new Label("Male");
		femaleRadioLb = new Label("Female");
		emailLb = new Label("Email");
		phoneNumberLb = new Label("Phone Number");
		passwordLb = new Label("Password");
		confirmPasswordLb = new Label("Confirm Password");
		addressLb = new Label("Address");
		hyperlinkLb = new Label("Have an account?");

		customerRb = new RadioButton();
		adminRb = new RadioButton();
		maleRb = new RadioButton();
		femaleRb = new RadioButton();

		usernameTf = new TextField();
		emailTf = new TextField();
		phoneNumberTf = new TextField();
		passwordPF = new PasswordField();
		confirmPasswordPf = new PasswordField();
		addressTf = new TextField();

		signUpBt = new Button("Sign Up");

		signInHp = new Hyperlink("Sign In");
		
		genderTg = new ToggleGroup();
		
		// HBox
		userTypeRadioHb = new HBox(5);
		genderRadioHb = new HBox(5);
		hyperLinkHb = new HBox();
		
		signUpButtonHb = new HBox();
		
		userDataInputVb = new VBox(2);
		containerBp = new BorderPane();
		
	}
		
	private void arrange() {
		maleRb.setToggleGroup(genderTg);
		femaleRb.setToggleGroup(genderTg);
		
		userTypeRadioHb.getChildren().addAll(customerRb, customerRadioLb);
		userTypeRadioHb.getChildren().addAll(adminRb, adminRadioLb);
		
		genderRadioHb.getChildren().addAll(maleRb, maleRadioLb, femaleRb, femaleRadioLb);
		
		hyperLinkHb.getChildren().addAll(hyperlinkLb, signInHp);
		
		signUpButtonHb.getChildren().add(signUpBt);
		
		userDataInputVb.getChildren().add(signUpLb);
		userDataInputVb.getChildren().addAll(usernameLb, usernameTf);
		userDataInputVb.getChildren().addAll(genderLb, genderRadioHb);
		userDataInputVb.getChildren().addAll(emailLb, emailTf);
		userDataInputVb.getChildren().addAll(phoneNumberLb, phoneNumberTf);
		userDataInputVb.getChildren().addAll(addressLb, addressTf);
		userDataInputVb.getChildren().addAll(passwordLb, passwordPF);
		userDataInputVb.getChildren().addAll(confirmPasswordLb, confirmPasswordPf);
		userDataInputVb.getChildren().add(signUpButtonHb);
		userDataInputVb.getChildren().add(hyperLinkHb);
		
		containerBp.setCenter(userDataInputVb);
		
		usernameTf.setPromptText("Must be at least 3 characters");
		emailTf.setPromptText("Must be a valid email");
		phoneNumberTf.setPromptText("Must be 12 digits");
		addressTf.setPromptText("Must be at least 6 characters");
		passwordPF.setPromptText("Must be at least 6 characters");
		confirmPasswordPf.setPromptText("Must be the same as password");
		
		signUpBt.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (isValid()) {
					addData();
					showAlert("Sign Up Success!", Alert.AlertType.CONFIRMATION);
					Main.primaryScene.setRoot(new SignIn().getParent());
				}
			}
		});
		
		signInHp.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Main.primaryScene.setRoot(new SignIn().getParent());
			}
		});
	}
	
	private void refine() {
		
		signUpLb.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		hyperLinkHb.setAlignment(Pos.CENTER);
		
		signUpButtonHb.setAlignment(Pos.CENTER);
		
		signUpButtonHb.setPadding(new Insets(5));
		userDataInputVb.setMaxSize(250, 350);
		userDataInputVb.setBorder(new MyBorder().getGrayBorder());
		userDataInputVb.setPadding(new Insets(20, 20, 20, 20));
		userDataInputVb.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
	}
	
	private void addData() {
		try {
			
			String query = "INSERT INTO MsCustomer (CustomerID, CustomerUsername, CustomerEmail, CustomerGender, CustomerPhone, CustomerAddress, CustomerPassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			
			preparedStatement.setString(1, generateID());
			preparedStatement.setString(2, usernameTf.getText());
			preparedStatement.setString(3, emailTf.getText());
			
			String selectedGender = maleRb.isSelected() ? "Male" : "Female";
			preparedStatement.setString(4, selectedGender);
			
			preparedStatement.setString(5, phoneNumberTf.getText());
			preparedStatement.setString(6, addressTf.getText());
			preparedStatement.setString(7, passwordPF.getText());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isValid() {
		boolean valid = true;

		// Check username
		if (usernameTf.getText().isEmpty()) {
			valid = false;
			showAlert("Username is required.", Alert.AlertType.ERROR);
		}
		
		else if (isUsernameExists(usernameTf.getText())) {
			valid = false;
			showAlert("Username already exists.", Alert.AlertType.ERROR);
		}
		// Check gender
		else if (!maleRb.isSelected() && !femaleRb.isSelected()) {
			valid = false;
			showAlert("Please select a gender.", Alert.AlertType.ERROR);
		}

		// Check email
		else if (emailTf.getText().isEmpty() || !emailTf.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
			valid = false;
			showAlert("Invalid email address.", Alert.AlertType.ERROR);
		}

		// Check phone number
		else if (phoneNumberTf.getText().isEmpty() || !phoneNumberTf.getText().matches("\\d{12}")) {
			valid = false;
			showAlert("Invalid phone number.", Alert.AlertType.ERROR);
		}

		// Check address
		else if (addressTf.getText().isEmpty()) {
			valid = false;
			showAlert("Address is required.", Alert.AlertType.ERROR);
		} else if (passwordPF.getText().length() < 6) {
			valid = false;
			showAlert("Password must be at least 6 characters", Alert.AlertType.ERROR);
		}

		// Check password
		else if (passwordPF.getText().isEmpty() || confirmPasswordPf.getText().isEmpty() || !passwordPF.getText().equals(confirmPasswordPf.getText())) {
			valid = false;
			showAlert("Passwords do not match.", Alert.AlertType.ERROR);
		}
		return valid;
	}
	
	private boolean isUsernameExists(String username) {
		try {
			String customerQuery = "SELECT * FROM MsCustomer WHERE CustomerUsername = ?";
			PreparedStatement customerStatement = connect.prepareStatement(customerQuery);
			customerStatement.setString(1, username);
			ResultSet customerResultSet = customerStatement.executeQuery();
	
			String adminQuery = "SELECT * FROM MsAdmin WHERE AdminUsername = ?";
			PreparedStatement adminStatement = connect.prepareStatement(adminQuery);
			adminStatement.setString(1, username);
			ResultSet adminResultSet = adminStatement.executeQuery();
	
			return customerResultSet.next() || adminResultSet.next();
	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void showAlert(String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private String generateID() {
		try {
			String query = "SELECT MAX(CustomerID) FROM MsCustomer";
			ResultSet resultSet = connect.executeQuery(query);

			if (resultSet.next()) {
				String maxID = resultSet.getString(1);

				if (maxID != null) {
					int numericPart = Integer.parseInt(maxID.substring(2)) + 1;
					return String.format("CU%03d", numericPart);
				} else {
					return "CU001";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@SuppressWarnings("exports")
	public BorderPane getRoot() {
		return this.containerBp;
	}
	
	public UserDataInput() {
		init();
		arrange();
		refine();
	}
	
}
