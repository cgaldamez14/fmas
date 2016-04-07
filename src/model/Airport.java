package model;

public class Airport {
	
	private int id;
	private String fs;
	private String name;
	private String city;
	private String state;
	private String country;
	private String lat;
	private String lon;
	private String weather;

	public Airport(){}

	public Airport(int id, String fs, String name, String city, String state, String country, String lat, String lon, String weather) {
		
		this.id = id;
		this.fs = fs;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.weather = weather;
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

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}
	
	public String getWeather() {
		return weather;
	}
}
