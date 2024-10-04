package model;

import javafx.scene.image.Image;

public class Dog extends Pet {
	
	private Integer dogEnergyLevel;
	
	@SuppressWarnings("exports")
	public Dog(String petID, String petSpecies, String petName, String petBreed, String petGender, Integer petAge,
			Image petImage, Integer petPrice, String petStatus, Integer dogEnergyLevel) {
		super(petID, petSpecies, petName, petBreed, petGender, petAge, petImage, petPrice, petStatus);
		this.dogEnergyLevel = dogEnergyLevel;
	}

	public Integer getDogEnergyLevel() {
		return dogEnergyLevel;
	}

	public void setDogEnergyLevel(Integer dogEnergyLevel) {
		this.dogEnergyLevel = dogEnergyLevel;
	}
	
}

