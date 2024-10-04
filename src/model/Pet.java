package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class Pet {
	
	private String petID;
	private String petSpecies;
	private String petName;
	private String petBreed;
	private String petGender;
	private Integer petAge;
	private Image petImage;
	private Integer petPrice;
	private String petStatus;
	
	private ImageView iv;
	
	public Pet(String petID, String petSpecies, String petName, String petBreed, String petGender, Integer petAge,
			Image petImage, Integer petPrice, String petStatus) {
		super();
		this.petID = petID;
		this.petSpecies = petSpecies;
		this.petName = petName;
		this.petBreed = petBreed;
		this.petGender = petGender;
		this.petAge = petAge;
		this.petImage = petImage;
		this.petPrice = petPrice;
		this.petStatus = petStatus;
		
		setIv(new ImageView(petImage));
		iv.setFitHeight(50);
		iv.setFitWidth(50);
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public String getPetSpecies() {
		return petSpecies;
	}

	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

	public Image getPetImage() {
		return petImage;
	}

	public void setPetImage(Image petImage) {
		this.petImage = petImage;
	}

	public Integer getPetPrice() {
		return petPrice;
	}

	public void setPetPrice(Integer petPrice) {
		this.petPrice = petPrice;
	}

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}

	public String getPetStatus() {
		return petStatus;
	}

	public void setPetStatus(String petStatus) {
		this.petStatus = petStatus;
	}
	
	
	
}
