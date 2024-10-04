package page;

import java.io.InputStream;
import java.sql.ResultSet;

import component.MyBorder;
import component.PetComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.Main;
import model.Cat;
import model.Dog;
import model.Pet;
import model.User;
import util.Connect;

@SuppressWarnings("exports")
public class HomeCustomer {
	
	TabPane customerTab;
	Tab homeTab;
	Tab historyTab;
	
	User user;
	
	ObservableList<Pet> pets;
	
	ImageView logoIv;
	FlowPane listOfPetComponentFp;
	StackPane logoSp;
	
	Button logOutBt;
	VBox logOutVb;
	
	BorderPane headerBp;
	
	VBox containerVb;
	
	public static BorderPane containerBp;
	
	Connect connect = Connect.getConnection();
	
	private void init() {
		customerTab = new TabPane();
		homeTab = new Tab("Home");
		historyTab = new Tab("Profile");
		
		pets = FXCollections.observableArrayList();
		
		logoIv = new ImageView(Main.logoImage);
		logoSp = new StackPane();
		listOfPetComponentFp = new FlowPane();
		
		logOutBt = new Button("Log Out");
		logOutVb = new VBox();
		
		headerBp = new BorderPane();
		containerVb = new VBox();
		containerBp = new BorderPane();
	}
	
	private void arrange() {
		homeTab.setContent(containerBp);
		historyTab.setContent(new ProfileCustomer(user).getParent());
		customerTab.getTabs().addAll(homeTab, historyTab);
		homeTab.setClosable(false);
		historyTab.setClosable(false);
		
		logoSp.getChildren().add(logoIv);
		
		logOutVb.getChildren().add(logOutBt);
		
		headerBp.setLeft(logoIv);
		headerBp.setRight(logOutVb);
		
		for (Pet pet : pets) {
			listOfPetComponentFp.getChildren().add(new PetComponent(user, pet).getNode());
		}
		
		containerBp.setCenter(listOfPetComponentFp);
		containerVb.getChildren().add(headerBp);
		containerVb.getChildren().add(customerTab);
		
		logOutBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Main.primaryScene.setRoot(new SignIn().getParent());
			}
		});
	}
	
	@SuppressWarnings("static-access")
	private void refine() {
		logoIv.setFitHeight(50);
		logoIv.setFitWidth(60);
		listOfPetComponentFp.setPadding(new Insets(20));
		listOfPetComponentFp.setVgap(10);
		listOfPetComponentFp.setHgap(10);
		listOfPetComponentFp.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		containerVb.setAlignment(Pos.CENTER);
		logOutVb.setPadding(new Insets(0, 10, 0, 0));
		logOutVb.setAlignment(Pos.CENTER);
		
		headerBp.setAlignment(logOutVb, Pos.CENTER_RIGHT);
		headerBp.setStyle("-fx-background-color: #d4c9c5;");
		headerBp.setBorder(new MyBorder().getGrayBorder());
		
		Image backgroundImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\Images\\HomeCustomer bg.jpg");
		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER,
				new BackgroundSize(200, 200, false, false, false, false)
		);
		Background backgroundWithImage = new Background(background);
		containerBp.setBackground(backgroundWithImage);
		containerBp.setMinHeight(400);
		containerBp.setAlignment(containerBp.getCenter(), Pos.CENTER);
	}
	
	private void getData() {
		pets.clear();
		
		String query = "SELECT * FROM mspet"
				+ " WHERE PetStatus LIKE 'Available'";
		ResultSet rs = connect.executeQuery(query);
		
		try {
			while(rs.next()) {
				String petID = rs.getString("PetID");
				String species = rs.getString("PetSpecies");
				String petName = rs.getString("PetName");
				String breed = rs.getString("PetBreed");
				String gender = rs.getString("PetGender");
				Integer age = Integer.valueOf(rs.getString("PetAge"));
				Integer dogEnergyLevel = null;
				if (rs.getString("DogEnergyLevel") != null) {
					dogEnergyLevel = Integer.valueOf(rs.getString("DogEnergyLevel"));
				}
				InputStream imageData = rs.getBinaryStream("PetImage");
				Image image = (imageData != null) ? new Image(imageData) : null;
				Integer price = Integer.valueOf(rs.getString("PetPrice"));
				String petStatus = rs.getString("PetStatus");
				
				if (dogEnergyLevel != null) {
					pets.add(new Dog(petID, species, petName, breed, gender, age, image, price, petStatus, dogEnergyLevel));
				} else {
					pets.add(new Cat(petID, species, petName, breed, gender, age, image, price, petStatus));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Parent getParent() {
		return this.containerVb;
	}
	
	public HomeCustomer(User user) {
		this.user = user;
		init();
		getData();
		arrange();
		refine();
	}
}
