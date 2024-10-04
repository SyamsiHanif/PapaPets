package page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import component.MyBorder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import main.Main;
import model.Cat;
import model.Dog;
import model.Pet;
import model.User;
import util.Connect;
import javafx.beans.property.SimpleObjectProperty;

public class HomeAdmin {
	
	TabPane adminTp;
	
	Tab homeTab;
	Tab historyTab;
	
	User user;
	
	ObservableList<Pet> pets;
	TableView<Pet> petTv;
	
	TableColumn<Pet, String> petIdTc;
	TableColumn<Pet, String> petSpeciesTc;
	TableColumn<Pet, String> petNameTc;
	TableColumn<Pet, String> petBreedTc;
	TableColumn<Pet, String> petGenderTc;
	TableColumn<Pet, Integer> petAgeTc;
	TableColumn<Pet, Integer> dogEnergyTc;
	TableColumn<Pet, Integer> petPriceTc;
	TableColumn<Pet, ImageView> petImageTc;
	TableColumn<Pet, String> petStatusTc;
	
	// pet data input
	Label speciesLb;
	Label catRadioLb;
	Label dogRadioLb;
	Label petNameLb;
	Label breedLb;
	Label genderLb;
	Label maleLb;
	Label femaleLb;
	Label ageLb;
	Label dogEnergyLevelLb;
	Label priceLb;
	Label imageLb;
	Label imageNameLb;
	Label statusLb;
	Label availableLb;
	Label boughtLb;
	
	RadioButton catRb;
	RadioButton dogRb;
	RadioButton maleRb;
	RadioButton femaleRb;
	RadioButton availableRb;
	RadioButton boughtRb;
	
	ToggleGroup speciesTg;
	ToggleGroup genderTg;
	ToggleGroup statusTg;
	
	TextField petNameTf;
	TextField breedTf;
	TextField ageTf;
	TextField dogEnergyLevelTf;
	TextField priceTf;
	
	Button uploadImageBt;
	Button addBt;
	Button updateBt;
	Button deleteBt;
	
	FileChooser fileChooser;
	File selectedFile;
	Image selectedImage;
	
	HBox buttonHb;
	HBox speciesRadioHb;
	HBox genderRadioHb;
	HBox statusRadioHb;
	
	VBox petDataInputContainerVb;
	VBox petDataInputContainerVb2;
	BorderPane petDataInputContainerBp;
	
	ImageView logoIv;
	StackPane logoSp;
	Button logOutBt;
	BorderPane headerBp;
	
	VBox logOutVb;
	
	Connect connect = Connect.getConnection();
	
	BorderPane bp;
	BorderPane containerBp;
	
	private void init() {
		adminTp = new TabPane();
		
		homeTab = new Tab("Home");
		historyTab = new Tab("History");
		
		bp = new BorderPane();
		
		pets = FXCollections.observableArrayList();
		
		petTv = new TableView<>();
		petTv.setItems(pets);
		
		petIdTc = new TableColumn<>("ID");
		petSpeciesTc = new TableColumn<>("Species");
		petNameTc = new TableColumn<>("Name");
		petBreedTc = new TableColumn<>("Breed");
		petGenderTc = new TableColumn<>("Gender");
		petAgeTc = new TableColumn<>("Age");
		petPriceTc = new TableColumn<>("Price");
		dogEnergyTc = new TableColumn<>("Energy");
		petImageTc = new TableColumn<>("Image");
		petStatusTc = new TableColumn<>("Status");
		
		// pet data input
		speciesLb = new Label("Species");
		catRadioLb = new Label("Cat");
		dogRadioLb = new Label("Dog");
		petNameLb = new Label("Pet Name");
		breedLb = new Label("Breed");
		genderLb = new Label("Gender");
		maleLb = new Label("Male");
		femaleLb = new Label("Female");
		ageLb = new Label("Age");
		dogEnergyLevelLb = new Label("Energy Level");
		imageLb = new Label("Image");
		imageNameLb = new Label();
		priceLb = new Label("Price");
		statusLb = new Label("Label");
		availableLb = new Label("Available");
		boughtLb = new Label("Bought");

		catRb = new RadioButton();
		dogRb = new RadioButton();
		maleRb = new RadioButton();
		femaleRb = new RadioButton();
		availableRb = new RadioButton();
		boughtRb = new RadioButton();
		
		speciesTg = new ToggleGroup();
		genderTg = new ToggleGroup();
		statusTg = new ToggleGroup();

		petNameTf = new TextField();
		breedTf = new TextField();
		ageTf = new TextField();
		dogEnergyLevelTf = new TextField();
		priceTf = new TextField();
		
		uploadImageBt = new Button("Upload Image");
		addBt = new Button("Add");
		updateBt = new Button("Update");
		deleteBt = new Button("Delete");
		
		fileChooser = new FileChooser();
		
		buttonHb = new HBox(5);
		speciesRadioHb = new HBox(2);
		genderRadioHb = new HBox(2);
		statusRadioHb = new HBox(2);
		
		logoIv = new ImageView(Main.logoImage);
		logoSp = new StackPane();
		logOutBt = new Button("Log Out");
		headerBp = new BorderPane();
		
		logOutVb = new VBox();
		
		petDataInputContainerVb = new VBox();
		petDataInputContainerVb2 = new VBox();
		petDataInputContainerBp = new BorderPane();
		
		containerBp = new BorderPane();
	}
	
	private void arrange () {
		setColumns();
		
		homeTab.setContent(bp);
		historyTab.setContent(new HistoryAdmin(user).getParent());
		homeTab.setClosable(false);
		historyTab.setClosable(false);
		
		adminTp.getTabs().addAll(homeTab, historyTab);
		
		logOutVb.getChildren().add(logOutBt);
		
		headerBp.setLeft(logoIv);
		headerBp.setRight(logOutVb);
		
		petTv.getColumns().add(petIdTc);
		petTv.getColumns().add(petSpeciesTc);
		petTv.getColumns().add(petNameTc);
		petTv.getColumns().add(petBreedTc);
		petTv.getColumns().add(petGenderTc);
		petTv.getColumns().add(petAgeTc);
		petTv.getColumns().add(dogEnergyTc);
		petTv.getColumns().add(petImageTc);
		petTv.getColumns().add(petPriceTc);
		petTv.getColumns().add(petStatusTc);
		
		containerBp.setTop(headerBp);
		bp.setLeft(petTv);
		
		// pet data input
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		
		speciesRadioHb.getChildren().addAll(catRb, catRadioLb, dogRb, dogRadioLb);
		genderRadioHb.getChildren().addAll(maleRb, maleLb, femaleRb, femaleLb);
		statusRadioHb.getChildren().addAll(availableRb, availableLb, boughtRb, boughtLb);
		
		catRb.setToggleGroup(speciesTg);
		dogRb.setToggleGroup(speciesTg);
		
		maleRb.setToggleGroup(genderTg);
		femaleRb.setToggleGroup(genderTg);
		
		availableRb.setToggleGroup(statusTg);
		boughtRb.setToggleGroup(statusTg);
		
		buttonHb.getChildren().addAll(addBt, updateBt, deleteBt);
		
		petDataInputContainerVb2.getChildren().add(petDataInputContainerVb);
		bp.setCenter(petDataInputContainerVb2);
		
		containerBp.setCenter(adminTp);
		
		logOutBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Main.primaryScene.setRoot(new SignIn().getParent());
			}
		});
		
		petTv.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (petTv.getSelectionModel().getSelectedItem() != null) {
					Pet selectedPet = petTv.getSelectionModel().getSelectedItem();
					
					System.out.println(petTv.getSelectionModel().getSelectedIndex());
					
					if (selectedPet != null) {
						if (selectedPet instanceof Dog) {
							Dog selectedDog = (Dog) selectedPet;
							dogRb.setSelected(true);
							dogEnergyLevelTf.setText(selectedDog.getDogEnergyLevel().toString());
						} else {
							catRb.setSelected(true);
							dogEnergyLevelTf.clear();
						}
						
						maleRb.setSelected("Male".equals(selectedPet.getPetGender()));
						femaleRb.setSelected("Female".equals(selectedPet.getPetGender()));
						
						availableRb.setSelected("Available".equals(selectedPet.getPetStatus()));
						boughtRb.setSelected("Bought".equals(selectedPet.getPetStatus()));
						
						petNameTf.setText(selectedPet.getPetName());
						breedTf.setText(selectedPet.getPetBreed());
						ageTf.setText(selectedPet.getPetAge().toString());
						
						selectedImage = (selectedPet.getPetImage() != null) ? selectedPet.getPetImage() : null;
						imageNameLb.setText(selectedPet.getPetImage() != null ? "Image Selected" : "No Image Selected");
						
						priceTf.setText(selectedPet.getPetPrice().toString());
						selectedFile = null;
					}
				}
			}
		});
		
		dogRb.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				petDataInput();
			}
		});
		
		catRb.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				petDataInput();
			}
		});
		
		uploadImageBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				selectedFile = fileChooser.showOpenDialog(Main.primaryStage);
				
				if (selectedFile != null) {
					selectedImage = new Image(selectedFile.toURI().toString());
					imageNameLb.setText(selectedFile.getName());
				}
			}
		});
		
		addBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (checkData()) {
					addData();
					refreshTable();
					showAlert(petNameTf.getText() + " Successfully Added", AlertType.INFORMATION);
				}
			}
		});
		
		updateBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (petTv.getSelectionModel().getSelectedItem() != null && checkData()) {
					Pet selectedPet = petTv.getSelectionModel().getSelectedItem();
					if (showAlert(String.format("Are you sure you want to update %s?", selectedPet.getPetID()), AlertType.CONFIRMATION)) {
						updatePet(selectedPet);
						showAlert(String.format("%s data successfully updated", selectedPet.getPetID()), AlertType.INFORMATION);
						refreshTable();
					}
				}
			}
		});
		
		deleteBt.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (petTv.getSelectionModel().getSelectedItem() != null) {
					Pet selectedPet = petTv.getSelectionModel().getSelectedItem();
					if (showAlert(String.format("Are you sure you want to delete %s?", selectedPet.getPetID()), AlertType.CONFIRMATION)) {
						deletePet(selectedPet.getPetID());
						showAlert(String.format("%s data successfully deleted", selectedPet.getPetID()), AlertType.CONFIRMATION);
						refreshTable();
					}
				}
			}
		});
	}
	
	@SuppressWarnings("static-access")
	private void refine() {
		logOutVb.setPadding(new Insets(0, 10, 0, 0));
		logOutVb.setAlignment(Pos.CENTER);
		buttonHb.setPadding(new Insets(10, 0, 0, 0));
		headerBp.setStyle("-fx-background-color: #d4c9c5;");
		headerBp.setBorder(new MyBorder().getGrayBorder());
		headerBp.setAlignment(logOutBt, Pos.CENTER_RIGHT);
		petDataInputContainerVb2.setAlignment(Pos.CENTER);
		petDataInputContainerVb.setMaxWidth(200);
		petDataInputContainerVb.setPadding(new Insets(10, 10, 10, 10));;
		petDataInputContainerVb.setBorder(new MyBorder().getGrayBorder());
		petTv.setMaxWidth(700);
		petTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		logoIv.setFitHeight(50);
		logoIv.setFitWidth(60);
		
		Image backgroundImage = new Image("File:C:\\Users\\syams\\Documents\\Kuliah\\Semester 3\\BAD LEC\\PapaPets\\Images\\HomeCustomer bg.jpg");

		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER, // Set the background position to the center
				new BackgroundSize(200, 200, false, false, false, false)
		);
		Background backgroundWithImage = new Background(background);
		containerBp.setBackground(backgroundWithImage);
		petDataInputContainerVb.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85);");
	}
	
	private void setColumns() {
		petIdTc.setCellValueFactory(new PropertyValueFactory<>("petID"));
		petSpeciesTc.setCellValueFactory(new PropertyValueFactory<>("petSpecies"));
		petNameTc.setCellValueFactory(new PropertyValueFactory<>("petName"));
		petBreedTc.setCellValueFactory(new PropertyValueFactory<>("petBreed"));
		petGenderTc.setCellValueFactory(new PropertyValueFactory<>("petGender"));
		petAgeTc.setCellValueFactory(new PropertyValueFactory<>("petAge"));
		dogEnergyTc.setCellValueFactory(cellData -> {
			Pet pet = cellData.getValue();
			if (pet instanceof Dog) {
				return new SimpleObjectProperty<>(((Dog) pet).getDogEnergyLevel());
			} else {
				// Handle the case for Cat or other subclasses
				return new SimpleObjectProperty<>(null); // Or handle appropriately based on your requirements
			}
		});
		petImageTc.setCellValueFactory(new PropertyValueFactory<>("iv"));
		petPriceTc.setCellValueFactory(new PropertyValueFactory<>("petPrice"));
		petStatusTc.setCellValueFactory(new PropertyValueFactory<>("petStatus"));
	  
	}
	
	private void petDataInput() {
		petDataInputContainerVb.getChildren().clear();
		petDataInputContainerVb.getChildren().addAll(speciesLb, speciesRadioHb);
		petDataInputContainerVb.getChildren().addAll(petNameLb, petNameTf);
		petDataInputContainerVb.getChildren().addAll(breedLb, breedTf);
		petDataInputContainerVb.getChildren().addAll(genderLb, genderRadioHb);
		petDataInputContainerVb.getChildren().addAll(ageLb, ageTf);
		if (dogRb.isSelected()) {
			petDataInputContainerVb.getChildren().addAll(dogEnergyLevelLb, dogEnergyLevelTf);
		}
		petDataInputContainerVb.getChildren().add(imageLb);
		petDataInputContainerVb.getChildren().addAll(imageNameLb, uploadImageBt);
		petDataInputContainerVb.getChildren().addAll(priceLb, priceTf);
		petDataInputContainerVb.getChildren().addAll(statusLb, statusRadioHb);
		petDataInputContainerVb.getChildren().add(buttonHb);
		
	}
	
	
	private boolean checkData() {
		if (speciesTg.getSelectedToggle() == null) {
			showAlert("Please select species (Cat/Dog).", Alert.AlertType.ERROR);
			return false;
		}

		if (petNameTf.getText().isEmpty()) {
			showAlert("Please enter pet name.", Alert.AlertType.ERROR);
			return false;
		}

		if (breedTf.getText().isEmpty()) {
			showAlert("Please enter pet breed.", Alert.AlertType.ERROR);
			return false;
		}

		if (genderTg.getSelectedToggle() == null) {
			showAlert("Please select gender (Male/Female).", Alert.AlertType.ERROR);
			return false;
		}

		if (ageTf.getText().isEmpty() && !isNumeric(ageTf.getText())) {
			showAlert("Please enter a valid pet age.", Alert.AlertType.ERROR);
			return false;
		}

		if (dogRb.isSelected() && dogEnergyLevelTf.getText().isEmpty() && !isNumeric(dogEnergyLevelTf.getText())) {
			showAlert("Please enter a valid dog energy level.", Alert.AlertType.ERROR);
			return false;
		}

		if (priceTf.getText().isEmpty() && !isNumeric(priceTf.getText())) {
			showAlert("Please enter a valid pet price.", Alert.AlertType.ERROR);
			return false;
		}

		if (statusTg.getSelectedToggle() == null) {
			showAlert("Please select pet status (Available/Bought).", Alert.AlertType.ERROR);
			return false;
		}
		
		return true;
	}
	
	public boolean isNumeric(String str) { 
		  try {  
			Double.parseDouble(str);  
			return true;
		  } catch(NumberFormatException e){  
			return false;  
		  }  
		}
	
	private void addData() {
		String petID = generatePetID();
		String species = (catRb.isSelected()) ? "Cat" : "Dog";
		String petName = petNameTf.getText();
		String breed = breedTf.getText();
		String gender = (maleRb.isSelected()) ? "Male" : "Female";
		String age = ageTf.getText();
		String dogEnergyLevel = dogEnergyLevelTf.getText();
		String price = priceTf.getText();
		String status = (availableRb.isSelected()) ? "Available" : "Bought";
		
		String query = "INSERT INTO MsPet (PetID, PetSpecies, PetName, PetBreed, PetGender, PetAge, DogEnergyLevel, PetImage, PetPrice, PetStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = (connect.prepareStatement(query));
			preparedStatement.setString(1, petID);
			preparedStatement.setString(2, species);
			preparedStatement.setString(3, petName);
			preparedStatement.setString(4, breed);
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, age);
			if (dogEnergyLevelTf != null && !dogEnergyLevelTf.getText().isEmpty()) {
				preparedStatement.setString(7, dogEnergyLevel);
			} else {
				preparedStatement.setNull(7, java.sql.Types.VARCHAR);
			}
			if (selectedFile != null) {
				FileInputStream fis = new FileInputStream(selectedFile);
				preparedStatement.setBinaryStream(8, fis, (int) selectedFile.length());
			} else {
				preparedStatement.setNull(8, java.sql.Types.BLOB);
			}
			preparedStatement.setString(9, price);
			preparedStatement.setString(10, status);
			preparedStatement.executeUpdate();
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getData() {
		pets.clear();
		String query = "SELECT * FROM mspet";
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
				String status = rs.getString("PetStatus");
				
				if (dogEnergyLevel != null) {
					pets.add(new Dog(petID, species, petName, breed, gender, age, image, price, status, dogEnergyLevel));
				} else {
					pets.add(new Cat(petID, species, petName, breed, gender, age, image, price, status));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deletePet(String petID) {
		String query = "DELETE FROM mspet WHERE PetID = ?";
		try {
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, petID);
			preparedStatement.executeUpdate();
			refreshTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePet(Pet selectedPet) {
		String query;
		if (selectedFile != null) {
			query = "UPDATE MsPet SET PetSpecies=?, PetName=?, PetBreed=?, PetGender=?, PetAge=?, DogEnergyLevel=?, PetImage=?, PetPrice=?, PetStatus=? WHERE PetID=?";
		} else {
			query = "UPDATE MsPet SET PetSpecies=?, PetName=?, PetBreed=?, PetGender=?, PetAge=?, DogEnergyLevel=?, PetPrice=?, PetStatus=? WHERE PetID=?";
		}
		
		try {
			int i = 7;
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			
			preparedStatement.setString(1, (catRb.isSelected()) ? "Cat" : "Dog");
			preparedStatement.setString(2, petNameTf.getText());
			preparedStatement.setString(3, breedTf.getText());
			preparedStatement.setString(4, (maleRb.isSelected()) ? "Male" : "Female");
			preparedStatement.setString(5, ageTf.getText());
			
			if (dogRb.isSelected()) {
				preparedStatement.setString(6, dogEnergyLevelTf.getText());
			} else {
				preparedStatement.setNull(6, java.sql.Types.VARCHAR);
			}

			if (selectedFile != null) {
				FileInputStream fis = new FileInputStream(selectedFile);
				preparedStatement.setBinaryStream(i++, fis, (int) selectedFile.length());
			} else if (selectedPet.getPetImage() == null) {
				
			} 
			
			preparedStatement.setString(i++, priceTf.getText());
			preparedStatement.setString(i++, (availableRb.isSelected()) ? "Available" : "Bought");
			preparedStatement.setString(i++, selectedPet.getPetID());

			preparedStatement.executeUpdate();
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void refreshTable() {
		getData();
		ObservableList<Pet> thisObs = FXCollections.observableArrayList(pets);
		petTv.setItems(thisObs);
	}
	
	private String generatePetID() {
		String prefix = "PT";
		int nextIDNumber = 1;

		if (!pets.isEmpty()) {
			String lastPetID = pets.get(pets.size() - 1).getPetID();
			int lastIDNumber = Integer.parseInt(lastPetID.substring(2));
			nextIDNumber = lastIDNumber + 1;
		}

		String formattedNextID = String.format("%03d", nextIDNumber);
		return prefix + formattedNextID;
	}
	
	private boolean showAlert(String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setHeaderText(null);
		alert.setContentText(message);

		if (alertType == Alert.AlertType.CONFIRMATION) {
			alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

			Optional<ButtonType> result = alert.showAndWait();

			return result.isPresent() && result.get() == ButtonType.OK;
		} else {
			alert.showAndWait();
			return false;
		}
	}
	
	public HomeAdmin(User user) {
		this.user = user;
		init();
		arrange();
		petDataInput();
		refine();
		getData();
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.containerBp;
	}
}
