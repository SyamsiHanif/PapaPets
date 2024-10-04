package model;

import javafx.scene.image.Image;

public class Cat extends Pet {
	
	@SuppressWarnings("exports")
	public Cat(String petID, String petSpecies, String petName, String petBreed, String petGender, Integer petAge,
			Image petImage, Integer petPrice, String petStatus) {
		super(petID, petSpecies, petName, petBreed, petGender, petAge, petImage, petPrice, petStatus);
	}

}


