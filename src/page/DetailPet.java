package page;

import java.text.NumberFormat;

import component.MyBorder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.Dog;
import model.Pet;
import model.User;

public class DetailPet {
	
	User user;
	
	Pet pet;
	
	StackPane logoSp;
	ImageView petImageIv;
	
	TextField searchbarTf;
	
	Label helloLb;
	Label speciesLb;
	Label breedLb; 
	Label genderLb; 
	Label ageLb;
	Label dogEnergyLevelLb;
	Label priceLb;
	
	Label nameValueLb;
	Label speciesValueLb;
	Label breedValueLb; 
	Label genderValueLb; 
	Label ageValueLb;
	Label dogEnergyLevelValueLb;
	Label priceValueLb;
	
	Button backBt;
	Button buyNowBt;
	
	VBox speciesVb;
	VBox breedVb;
	VBox genderVb;
	VBox ageVb;
	VBox dogEnergyLevelVb;
	
	GridPane petDescriptionGp;
	HBox detailPetHb;
	VBox vb;
	BorderPane detailPetBp;
	
	NumberFormat myFormat;

	private void init() {
		logoSp = new StackPane();	
		petImageIv = new ImageView(pet.getPetImage());	
		
		searchbarTf = new TextField();
		
		helloLb = new Label("Hello! I'm");
		speciesLb = new Label("Species");
		breedLb = new Label("Breed");
		genderLb = new Label("Gender");
		ageLb = new Label("Age");
		if (pet instanceof Dog) {
			dogEnergyLevelLb = new Label("Energy Level");
		}
		priceLb = new Label("Price");
		
		nameValueLb = new Label(pet.getPetName());
		speciesValueLb = new Label(pet.getPetSpecies());
		breedValueLb = new Label(pet.getPetBreed());
		genderValueLb = new Label(pet.getPetGender());
		ageValueLb = new Label(pet.getPetAge().toString() + " Years");
		if (pet instanceof Dog) {
			dogEnergyLevelValueLb = new Label(((Dog) pet).getDogEnergyLevel().toString());
		}
		myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		priceValueLb = new Label("Rp " + myFormat.format(pet.getPetPrice()) + ",-");
		
		backBt = new Button("Back");
		buyNowBt = new Button("Buy now");
		
		speciesVb = new VBox();
		breedVb = new VBox();
		genderVb = new VBox();
		ageVb = new VBox();
		dogEnergyLevelVb = new VBox();
		
		petDescriptionGp = new GridPane();
		detailPetHb = new HBox(40);
		detailPetBp = new BorderPane();
		vb = new VBox();
	}
	
	private void arrange() {
		
		speciesVb.getChildren().addAll(speciesLb, speciesValueLb);
		breedVb.getChildren().addAll(breedLb, breedValueLb);
		genderVb.getChildren().addAll(genderLb, genderValueLb);
		ageVb.getChildren().addAll(ageLb, ageValueLb);
		
		if (pet instanceof Dog) {
			dogEnergyLevelVb.getChildren().addAll(dogEnergyLevelLb, dogEnergyLevelValueLb);
			petDescriptionGp.add(dogEnergyLevelVb, 0, 2);
		}
		
		petDescriptionGp.add(speciesVb, 0, 0);
		petDescriptionGp.add(breedVb, 1, 0);
		petDescriptionGp.add(genderVb, 0, 1);
		petDescriptionGp.add(ageVb, 1, 1);
		petDescriptionGp.add(priceValueLb, 0, 3);
		
		
		vb.getChildren().add(helloLb);
		vb.getChildren().add(nameValueLb);
		vb.getChildren().add(petDescriptionGp);
		vb.getChildren().add(buyNowBt);
		
		
		detailPetHb.getChildren().add(petImageIv);
		detailPetHb.getChildren().add(vb);
		
		detailPetBp.setTop(backBt);
		detailPetBp.setCenter(detailPetHb);
		
		backBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Main.primaryScene.setRoot(new HomeCustomer(user).getParent());
			}
		});
		
		buyNowBt.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				HomeCustomer.containerBp.setCenter(new WaitingForPayment(user, pet).getParent());
			}
		});
	}
	
	@SuppressWarnings("static-access")
	private void refine() {
		petImageIv.setFitWidth(300);
		petImageIv.setFitHeight(250);
		
		helloLb.setFont(Font.font(30));
		nameValueLb.setPadding(new Insets(0, 0, 10, 0));
		
		nameValueLb.setFont(Font.font("Verdana",FontWeight.BOLD, 35));
		nameValueLb.setWrapText(true);
		
		speciesLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		breedLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		genderLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		ageLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		if (pet instanceof Dog) {
			breedVb.setPrefWidth(100);
			dogEnergyLevelLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		} else {
			breedVb.setPrefWidth(57);
		}
		priceValueLb.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
		
		vb.setMaxHeight(250);
		
		petDescriptionGp.setVgap(10);
		petDescriptionGp.setHgap(10);
		
		detailPetHb.setMaxWidth(650);
		detailPetHb.setMaxHeight(350);
		detailPetHb.setAlignment(Pos.CENTER);
		detailPetHb.setStyle("-fx-background-color: #fcccb4;");
		detailPetHb.setBorder(new MyBorder().getGrayBorder());
		
		buyNowBt.setAlignment(Pos.CENTER);
		
		detailPetBp.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
		detailPetBp.setMinHeight(400);
		
		detailPetBp.setAlignment(detailPetHb, Pos.TOP_CENTER);
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.detailPetBp;
	}
	
	public DetailPet(User user, Pet pet) {
		this.user = user;
		this.pet = pet;
		init();
		arrange();
		refine();
	}
}
