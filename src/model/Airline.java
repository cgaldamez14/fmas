package model;

public class Airline {

	private int id;
	private String fs;
	private String name;
	private String phoneNumber;
	
	public Airline(int id, String fs, String name, String phoneNumber) {
		this.id = id;
		this.fs = fs;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public int getId(){
		return id;
	}
	
	public String getFs() {
		return fs;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
