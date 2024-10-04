package page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import component.MyBorder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.Pet;
import model.User;
import util.Connect;

public class WaitingForPayment {
	
	User user;
	Pet pet;
	
	Label waitingForPaymentLb;
	
	ImageView petIv;
	Label nameLb;
	Label speciesLb;
	Label breedLb; 
	Label genderLb; 
	Label ageLb;
	Label priceLb;
	
	GridPane petGp;
	HBox petHb;
	
	Label virtualAccountLb;
	
	Label paymentDetailsLb;
	Label subTotalLb;
	Label subTotalValueLb;
	Label otherFeeLb;
	Label otherFeeValueLb;
	Label totalPriceLb;
	Label totalPriceValueLb;
	
	Button backBt;
	Button confirmPaymentBt;
	
	VBox leftVb;
	GridPane rightGp;
	HBox hb;
	BorderPane containerBp;
	
	Connect connect = Connect.getConnection();
	NumberFormat myFormat;
	
	private void init() {
		
		waitingForPaymentLb = new Label("Waiting For Payment");
		
		petIv = new ImageView(pet.getPetImage()) ;

		nameLb = new Label(pet.getPetName());
		speciesLb = new Label(pet.getPetSpecies());
		breedLb = new Label(pet.getPetBreed());
		genderLb = new Label(pet.getPetGender());
		ageLb = new Label(pet.getPetAge().toString() + " years");
		
		myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		priceLb = new Label("Rp " + myFormat.format(pet.getPetPrice())+ ",-");

		petGp = new GridPane();
		petHb = new HBox(10);
		
		virtualAccountLb = new Label("BCA Virtual Account : 1234567890");
		
		paymentDetailsLb = new Label("Payment Details");
		subTotalLb = new Label("Sub Total");
		subTotalValueLb = new Label("Rp " + myFormat.format(pet.getPetPrice()) + ",-");
		otherFeeLb = new Label("Other Fees");
		otherFeeValueLb = new Label("Rp 1,000,000,-");
		totalPriceLb = new Label("Total Price");
		totalPriceValueLb = new Label(String.format("Rp %s,-", myFormat.format(pet.getPetPrice())));
		
		backBt = new Button("Back");
		confirmPaymentBt = new Button("Confirm Payment");
		
		leftVb = new VBox(10);
		rightGp = new GridPane();
		hb = new HBox();
		containerBp = new BorderPane();
		
	}
	
	private void arrange() {
		
		petHb.getChildren().add(petIv);
		petHb.getChildren().add(petGp);
		
		petGp.add(nameLb, 0, 0);
		petGp.add(speciesLb, 0, 1);
		petGp.add(breedLb, 1, 1);
		petGp.add(genderLb, 0, 2);
		petGp.add(ageLb, 1, 2);
		petGp.add(priceLb, 0, 3);
		
		leftVb.getChildren().add(waitingForPaymentLb);
		leftVb.getChildren().add(petHb);
		leftVb.getChildren().add(virtualAccountLb);
		
		rightGp.add(paymentDetailsLb, 0, 0);
		rightGp.add(subTotalLb, 0, 1);
		rightGp.add(otherFeeLb, 0, 2);
		rightGp.add(totalPriceLb, 0, 3);
		
		rightGp.add(subTotalValueLb, 1, 1);
		rightGp.add(otherFeeValueLb, 1, 2);
		rightGp.add(totalPriceValueLb, 1, 3);
		rightGp.add(confirmPaymentBt, 0, 4);
		
		hb.getChildren().addAll(leftVb, rightGp);
		containerBp.setTop(backBt);
		containerBp.setCenter(hb);
		
		backBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				HomeCustomer.containerBp.setCenter(new DetailPet(user, pet).getParent());
			}
		});
		
		confirmPaymentBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				addData();
				String query = String.format("UPDATE MsPet SET PetStatus = 'Bought' WHERE PetID = '%s'", pet.getPetID());
				try {
					connect.executeUpdate(query);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Main.primaryScene.setRoot(new HomeCustomer(user).getParent());
				showAlert("Transaction Success!", AlertType.INFORMATION);
			}
		});
	}
		
	@SuppressWarnings("static-access")
	private void refine() {
		rightGp.setVgap(20);
		
		nameLb.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		paymentDetailsLb.setFont(Font.font("Arial",FontWeight.BOLD, 15));
		
		petIv.setFitHeight(100);
		petIv.setFitWidth(100);
		
		rightGp.setPadding(new Insets(50));
		leftVb.setPadding(new Insets(50));
		rightGp.setMaxHeight(120);
		
		containerBp.setAlignment(hb, Pos.CENTER);
		hb.setAlignment(Pos.CENTER);
		
		hb.setMaxSize(700, 350);
		Image backgroundImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\Images\\DetailPet bg.jpg");
		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, // Set the background position to the center
				new BackgroundSize(700, 350, false, false, false, false)
		);
		Background backgroundWithImage = new Background(background);
		hb.setBackground(backgroundWithImage);
		hb.setBorder(new MyBorder().getGrayBorder());
		
		
		waitingForPaymentLb.setFont(Font.font("Arial",FontWeight.BOLD, 20));
		containerBp.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		containerBp.setMaxHeight(450);
	}
	
	private void addData() {
		String customerID = user.getCustomerId();
		String transactionID = generateTransactionID();
		java.sql.Date transactionDate = new java.sql.Date(System.currentTimeMillis());
		
		String insertQuery = String.format("INSERT INTO TransactionHeader (TransactionID, TransactionDate, CustomerID, PetID) VALUES ('%s', '%s', '%s', '%s')",
				transactionID, transactionDate, customerID, pet.getPetID());

		connect.executeUpdate(insertQuery);
		
	}
	
	private String generateTransactionID() {
		String query = "SELECT MAX(TransactionID) AS MaxID FROM TransactionHeader";
		ResultSet resultSet = connect.executeQuery(query);

		try {
			if (resultSet.next()) {
				String maxID = resultSet.getString("MaxID");
				if (maxID != null) {
					int numericPart = Integer.parseInt(maxID.substring(2));
					String newID = String.format("TR%03d", numericPart + 1);
					return newID;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "TR001";
	}
	
	private void showAlert(String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public WaitingForPayment(User user, Pet pet) {
		this.user = user;
		this.pet = pet;
		init();
		arrange();
		refine();
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.containerBp;
	}
}
