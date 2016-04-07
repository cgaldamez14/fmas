package queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import model.DayWeather;

public class Weather extends XMLQuery{
	Document doc;
	ArrayList<String> tagNames;
	String newCity = "";
	
	 public Weather(String state, String city) throws SAXException, IOException, ParserConfigurationException{
		 if(city.contains(" ")){
			String cityParse[] = city.split(" ");
				
			for(int i = 0; i < cityParse.length; i++)
				newCity = newCity + cityParse[i];
		}
		 else{
			 newCity = city;
		 }
		 System.out.println("http://api.wunderground.com/api/" + WEATHER_KEY + "/forecast10day/q/" + state + "/" + newCity + ".xml");
		 doc = openXML("http://api.wunderground.com/api/" + WEATHER_KEY + "/forecast10day/q/" + state + "/" + newCity + ".xml");
		System.out.println("http://api.wunderground.com/api/" + WEATHER_KEY + "/forecast10day/q/" + state + "/" + newCity + ".xml");
		tagNames = new ArrayList<String>();
		tagNames.add("forecastdays");
		tagNames.add("day");
		tagNames.add("month");
		tagNames.add("year");
		tagNames.add("fahrenheit");
		tagNames.add("conditions");
		tagNames.add("icon_url");
		tagNames.add("mph");
		tagNames.add("dir");
		tagNames.add("avehumidity");
		
	}
	
	public ArrayList<DayWeather> getTenDayWeather()throws SAXException, IOException, ParserConfigurationException{
		ArrayList<Map<String,String>> results = getElements(tagNames,doc,"");
		ArrayList<DayWeather> list = new ArrayList<>();
		
		for(int i = 0; i < results.size();i++){
			String day = results.get(i).get("day");
			String month = results.get(i).get("month");
			String year = results.get(i).get("year");
			String temperature = results.get(i).get("fahrenheit") + "\u00b0 F";
			String conditions = results.get(i).get("conditions");
			String wind = ((results.get(i).get("dir") != null)?results.get(i).get("dir"): "") + " " + results.get(i).get("mph") + " mph ";
			String humidity = results.get(i).get("avehumidity");
			String icon_url = results.get(i).get("icon_url");
			
			DayWeather w = new DayWeather(day,month,year,temperature,conditions,wind,humidity,icon_url);
			list.add(w);
			
		}
		return list;
	}
}
