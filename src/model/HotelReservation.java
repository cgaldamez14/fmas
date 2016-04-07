package model;

public class HotelReservation {

	private String confirmationNumber;
	private String hotelName;
	private String hotelAddress;
	private String hotelCity;
	private String hotelState;
	private String hotelZip;
	private String checkInDate;
	private String checkOutDate;
	private String numberOfRooms;
	
	
	public HotelReservation(){}


	public HotelReservation(String confirmationNumber, String hotelName, String hotelAddress, String hotelCity,
			String hotelState, String hotelZip, String checkInDate, String checkOutDate, String numberOfRooms) {
		this.confirmationNumber = confirmationNumber;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.hotelCity = hotelCity;
		this.hotelState = hotelState;
		this.hotelZip = hotelZip;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfRooms = numberOfRooms;
	}


	public String getConfirmationNumber() {
		return confirmationNumber;
	}


	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public String getHotelAddress() {
		return hotelAddress;
	}


	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}


	public String getHotelCity() {
		return hotelCity;
	}


	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}


	public String getHotelState() {
		return hotelState;
	}


	public void setHotelState(String hotelState) {
		this.hotelState = hotelState;
	}


	public String getHotelZip() {
		return hotelZip;
	}


	public void setHotelZip(String hotelZip) {
		this.hotelZip = hotelZip;
	}


	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public String getNumberOfRooms() {
		return numberOfRooms;
	}


	public void setNumberOfRooms(String numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	
	
	
	
}
