package model;

public class User {

	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	public User(){}
	
	public User(int id, String name, String email, String phoneNumber, String address, String city, String state, String zip){
		this.id= id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;	
	}
	
	public User(String name, String email, String phoneNumber, String address, String city, String state, String zip){
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;	
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password= password;
	}

}
