package model;

public class Transaction {
	
	private String transactionID;
	private String transactionDate;
	private String customerID;
	private String customerName;
	private String petID;
	private String petName;
	private String petSpecies;
	private String petBreed;
	private String petPrice;
	
	public Transaction(String transactionID, String transactionDate, String customerID, String customerName,
			String petID, String petName, String petSpecies, String petBreed, String petPrice) {
		super();
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
		this.customerID = customerID;
		this.customerName = customerName;
		this.petID = petID;
		this.petName = petName;
		this.petSpecies = petSpecies;
		this.petBreed = petBreed;
		this.petPrice = petPrice;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetSpecies() {
		return petSpecies;
	}

	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}

	public String getPetPrice() {
		return petPrice;
	}

	public void setPetPrice(String petPrice) {
		this.petPrice = petPrice;
	}
	
	
}
