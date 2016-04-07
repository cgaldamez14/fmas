package queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import model.Status;

public class FlightStatus extends XMLQuery {

	private Document doc;
	private ArrayList<String> tagNames;
	private Status status = new Status();
	
	public FlightStatus(String fs) throws SAXException, IOException, ParserConfigurationException{	
		doc = openXML("https://api.flightstats.com/flex/flightstatus/rest/v2/xml/airport/tracks/"+fs+"/arr?appId=8b8eefd0&appKey=bab84649784c5f166e3f9a5016be5993");
	}

	public Status getStatus(String flightNumber) throws SAXException, IOException, ParserConfigurationException{
			tagNames = new ArrayList<String>();
			tagNames.add("flightTrack");
			tagNames.add("flightNumber");
			tagNames.add("flightId");
			tagNames.add("delayMinutes");
			
			Map<String, String> results = getElements(tagNames, flightNumber,doc);
			tagNames.clear();
			status = new Status();
			status.setFlightId(results.get("flightId"));
			status.setDelayMinutes(results.get("delayMinutes"));

			doc = openXML("https://api.flightstats.com/flex/flightstatus/rest/v2/xml/flight/status/" + status.getFlightId() + "?appId=8b8eefd0&appKey=bab84649784c5f166e3f9a5016be5993");
			tagNames.add("flightStatus");
			tagNames.add("flightId");
			tagNames.add("status");
			results = getElements(tagNames,status.getFlightId(),doc);
			
			status.setStatus(results.get("status"));
			
//			NodeList nodes = doc.getElementsByTagName("flightTrack");		 
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Element element = (Element) nodes.item(i);
//
//				String num = xmlToString((Element)element.getElementsByTagName("carrierFsCode").item(0)) + xmlToString((Element)element.getElementsByTagName("flightNumber").item(0));
//				System.out.print(num.equals(flightNumber));
//				if(num.equals(flightNumber)){
//					status = new Status();
//					status.setFlightId(xmlToString((Element) element.getElementsByTagName("flightId").item(0)));
//					status.setDelayMinutes(xmlToString((Element) element.getElementsByTagName("delayMinutes").item(0)));
//					System.out.println(status.getDelayMinutes());
//					doc = openXML("https://api.flightstats.com/flex/flightstatus/rest/v2/xml/flight/status/" + status.getFlightId() + "?appId=8b8eefd0&appKey=bab84649784c5f166e3f9a5016be5993");
//					NodeList statusNodes = doc.getElementsByTagName("flightStatus");
//					Element statusElement = (Element) statusNodes.item(0);
//					status.setStatus(xmlToString((Element) statusElement.getElementsByTagName("status").item(0)));
//					break;
//				}
//				else{
//					status = new Status();
//				}
//			}
			return status;
	}
}

