package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayWeather {
	
	private Calendar date;
	private String temperature;
	private String conditions;
	private String wind;
	private String humidity;
	private String icon_url;
	
	
	public DayWeather(String day, String month, String year, String temperature, String conditions, String wind,
			String humidity, String icon_url) {
		
		date = 	new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
		this.temperature = temperature;
		this.conditions = conditions;
		this.wind = wind;
		this.humidity = humidity;
		this.icon_url = icon_url;
	}
	
	public DayWeather(){}
	
	public String getDate(){
		return new SimpleDateFormat("EEEE, MMMMM dd, yyyy").format(date.getTime());
	}
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
}
