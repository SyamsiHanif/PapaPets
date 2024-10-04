package model;

public class User {
	
	private String customerID;
	private String customerUsername;
	private String customerEmail;
	private String customerGender;
	private String customerPhone;
	private String customerAddress;
	private String customerPassword;
	
	public User(String customerID, String customerUsername, String customerEmail, String customerGender,
			String customerPhone, String customerAddress, String customerPassword) {
		super();
		this.customerID = customerID;
		this.customerUsername = customerUsername;
		this.customerEmail = customerEmail;
		this.customerGender = customerGender;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.customerPassword = customerPassword;
	}

	public String getCustomerId() {
		return customerID;
	}

	public void setCustomerId(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
}
