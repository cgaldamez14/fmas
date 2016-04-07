package queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Airports extends XMLQuery {
	Document doc;
	ArrayList<String> tagNames;
	
	 public Airports() throws SAXException, IOException, ParserConfigurationException{
		doc = openXML("https://api.flightstats.com/flex/airports/rest/v1/xml/active?appId=8b8eefd0&appKey=bab84649784c5f166e3f9a5016be5993");
		tagNames = new ArrayList<String>();
		tagNames.add("airport");
		tagNames.add("fs");
		tagNames.add("name");
		tagNames.add("city");
		tagNames.add("stateCode");
		tagNames.add("countryName");
		tagNames.add("latitude");
		tagNames.add("longitude");
		tagNames.add("weatherUrl");
	}
	
	public ArrayList<Map<String,String>> getAirportList(){
		return getElements(tagNames,doc);
	}
}
