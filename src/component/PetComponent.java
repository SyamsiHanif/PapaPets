package component;

import java.text.NumberFormat;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Pet;
import model.User;
import page.DetailPet;
import page.HomeCustomer;

public class PetComponent {
	
	User user;
	Pet pet;

	ImageView petImageIv;
	
	Label nameLb;
	Label speciesLb;
	Label breedLb;
	Label priceLb;
	
	VBox petComponentVb;
	StackPane containerSp;
	
	NumberFormat myFormat;
	
	private void init() {
		petImageIv = new ImageView(pet.getPetImage());
		
		nameLb = new Label(pet.getPetName());
		speciesLb = new Label(pet.getPetSpecies());
		breedLb = new Label(pet.getPetBreed());
		myFormat = NumberFormat.getInstance();
		myFormat.setGroupingUsed(true);
		priceLb = new Label("Rp " + myFormat.format(pet.getPetPrice()) + ",-");
		
		petComponentVb = new VBox();
		containerSp = new StackPane();
	}
	
	private void arrange() {
		petComponentVb.getChildren().addAll(petImageIv, nameLb, speciesLb, breedLb, priceLb);
		containerSp.getChildren().add(petComponentVb);
		containerSp.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				HomeCustomer.containerBp.setCenter(new DetailPet(user, pet).getParent());
			}
		});
		
	}
	
	private void refine() {
		petImageIv.setFitWidth(100);
		petImageIv.setFitHeight(100);
		
		nameLb.setFont(Font.font("Default", FontWeight.BOLD, 15));
		
		petComponentVb.setAlignment(Pos.CENTER_LEFT);
		petComponentVb.setMaxSize(110, 150);
		petComponentVb.setPadding(new Insets(5));
		
		containerSp.setStyle("-fx-background-color: #fcccb4;");
	}
	
	@SuppressWarnings("exports")
	public StackPane getNode() {
		return this.containerSp;
	}
	
	public PetComponent(User user, Pet pet) {
		this.user = user;
		this.pet = pet;
		init();
		arrange();
		refine();
	}
		
}
