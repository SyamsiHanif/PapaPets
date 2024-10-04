package page;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Transaction;
import model.User;
import util.Connect;

public class ProfileCustomer {
	
	User user;
	
	ObservableList<Transaction> transactions;
	
	TableView<Transaction> transactionTv;
	
	TableColumn<Transaction, String> transactionIDTc;
	TableColumn<Transaction, String> transactionDateTc;
	TableColumn<Transaction, String> customerIDTc;
	TableColumn<Transaction, String> customerNameTc;
	TableColumn<Transaction, String> petIdTc;
	TableColumn<Transaction, String> petNameTc;
	TableColumn<Transaction, String> petSpeciesTc;
	TableColumn<Transaction, String> petBreedTc;
	TableColumn<Transaction, String> petPriceTc;
	
	BorderPane containerBp;
	Connect connect = Connect.getConnection();
	
	private void init() {
		transactions = FXCollections.observableArrayList();

		transactionTv = new TableView<>();

		transactionIDTc = new TableColumn<>("Transaction ID");
		transactionDateTc = new TableColumn<>("Transaction Date");
		customerIDTc = new TableColumn<>("Customer ID");
		customerNameTc = new TableColumn<>("Customer Name");
		petIdTc = new TableColumn<>("Pet ID");
		petNameTc = new TableColumn<>("Pet Name");
		petSpeciesTc = new TableColumn<>("Pet Species");
		petBreedTc = new TableColumn<>("Pet Breed");
		petPriceTc = new TableColumn<>("Pet Price");
		
		containerBp = new BorderPane();
	}
	
	private void setup() {
		setColumns();
		transactionTv.getColumns().add(transactionIDTc);
		transactionTv.getColumns().add(transactionDateTc);
		transactionTv.getColumns().add(customerIDTc);
		transactionTv.getColumns().add(customerNameTc);
		transactionTv.getColumns().add(petIdTc);
		transactionTv.getColumns().add(petNameTc);
		transactionTv.getColumns().add(petSpeciesTc);
		transactionTv.getColumns().add(petBreedTc);
		transactionTv.getColumns().add(petPriceTc);
		
		transactionTv.setItems(transactions);
		
		containerBp.setCenter(transactionTv);
	}
	
	private void setColumns() {
		transactionIDTc.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		transactionDateTc.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
		customerIDTc.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		customerNameTc.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		petIdTc.setCellValueFactory(new PropertyValueFactory<>("petID"));
		petNameTc.setCellValueFactory(new PropertyValueFactory<>("petName"));
		petSpeciesTc.setCellValueFactory(new PropertyValueFactory<>("petSpecies"));
		petBreedTc.setCellValueFactory(new PropertyValueFactory<>("petBreed"));
		petPriceTc.setCellValueFactory(new PropertyValueFactory<>("petPrice"));
	}
	
	private void getData() {
		transactions.clear();
		try {
			String query = String.format("SELECT * FROM TransactionHeader "
					+ "JOIN MsCustomer ON TransactionHeader.CustomerID = MsCustomer.CustomerID "
					+ "JOIN MsPet ON TransactionHeader.PetID = MsPet.PetID "
					+ "WHERE MsCustomer.CustomerID LIKE '"+ user.getCustomerId() + "'");

			ResultSet resultSet = connect.executeQuery(query);

			while (resultSet.next()) {
				String transactionID = resultSet.getString("TransactionID");
				String transactionDate = resultSet.getString("TransactionDate");
				String customerID = resultSet.getString("CustomerID");
				String customerName = resultSet.getString("CustomerUsername");
				String petID = resultSet.getString("PetID");
				String petName = resultSet.getString("PetName");
				String petSpecies = resultSet.getString("PetSpecies");
				String petBreed = resultSet.getString("PetBreed");
				String petPrice = resultSet.getString("PetPrice");

				Transaction transaction = new Transaction(
						transactionID, transactionDate, customerID, customerName,
						petID, petName, petSpecies, petBreed, petPrice);

				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void refine() {
		transactionTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		transactionTv.setMaxWidth(800);
		containerBp.setPadding(new Insets(50));
	}
	
	public ProfileCustomer(User user) {
		this.user = user;
		init();
		getData();
		setup();
		refine();
	}
	
	@SuppressWarnings("exports")
	public Parent getParent() {
		return this.containerBp;
	}
}
