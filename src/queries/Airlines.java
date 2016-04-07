package queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Airlines extends XMLQuery {

	Document doc;
	ArrayList<String> tagNames;
	
	 public Airlines() throws SAXException, IOException, ParserConfigurationException{
		doc = openXML("https://api.flightstats.com/flex/airlines/rest/v1/xml/active?appId=8b8eefd0&appKey=bab84649784c5f166e3f9a5016be5993");
		tagNames = new ArrayList<String>();
		tagNames.add("airline");
		tagNames.add("fs");
		tagNames.add("name");
	}
	
	public ArrayList<Map<String,String>> getAirlineList(){
		return getElements(tagNames,doc);
	}
}
