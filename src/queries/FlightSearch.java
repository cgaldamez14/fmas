package queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dbqueries.AirlineQuery;
import dbqueries.AirportQuery;
import model.Airline;
import model.Airport;
import model.Flight;

public class FlightSearch extends XMLQuery {

	private Document doc;
	private Flight flight;
	private ArrayList<String> tagNames;
	private Map<String,String> results;
	private ArrayList<Map<String,String>> allResults;

	public FlightSearch(String x,String y,String year,String month, String day, String queryType) throws SAXException, IOException, ParserConfigurationException{

		tagNames = new ArrayList<String>();
		if(queryType.equals("upcoming")){
			Airline al = new AirlineQuery().getAirlineInfo(x);
			flight = new Flight();
			flight.setAirline(al);
			flight.setFlightNumber(y);

			System.out.println("https://api.flightstats.com/flex/schedules/rest/v1/xml/flight/"
					+flight.getAirline().getFs()+"/"+flight.getFlightNumber()+"/departing/"
					+ year+"/"+month+"/"+day+"?appId=" + APP_ID + "&appKey=" + APP_KEY);
			doc = openXML("https://api.flightstats.com/flex/schedules/rest/v1/xml/flight/"
					+flight.getAirline().getFs()+"/"+flight.getFlightNumber()+"/departing/"
					+ year+"/"+month+"/"+day+"?appId=" + APP_ID + "&appKey=" + APP_KEY);
		}
		else{
			System.out.println("https://api.flightstats.com/flex/schedules/rest/v1/xml/from/"
					+ x + "/to/" + y + "/departing/"
					+ year+"/"+month+"/"+day+"?appId=" + APP_ID + "&appKey=" + APP_KEY);
			doc = openXML("https://api.flightstats.com/flex/schedules/rest/v1/xml/from/"
					+ x + "/to/" + y + "/departing/"
					+ year+"/"+month+"/"+day+"?appId=" + APP_ID + "&appKey=" + APP_KEY);
		}
	}


	public Flight getFlightInformation(String departingFS){
		System.out.println(departingFS);
		try{
			tagNames.clear();
			tagNames.add("scheduledFlight");
			tagNames.add("departureAirportFsCode");
			tagNames.add("flightNumber");
			tagNames.add("arrivalAirportFsCode");
			tagNames.add("departureTerminal");
			tagNames.add("arrivalTerminal");
			tagNames.add("departureTime");
			tagNames.add("arrivalTime");

			if(getElements(tagNames,departingFS,doc) == null) 
				return null;
			results = getElements(tagNames,departingFS,doc);
			System.out.println("Results are: " + results);

			ArrayList<Airport> airports = new ArrayList<>();
			Airport depart = new AirportQuery().getAirportInfo(results.get("departureAirportFsCode"));
			Airport arrive = new AirportQuery().getAirportInfo(results.get("arrivalAirportFsCode"));

			airports.add(depart);
			airports.add(arrive);

			flight.setAirports(airports);

			flight.setArrivalTerminal(results.get("arrivalTerminal"));
			flight.setDepartureTerminal(results.get("departureTerminal"));

			flight.setoGADate(results.get("arrivalTime"));
			flight.setoGDDate(results.get("departureTime"));

		}catch(Exception e){		
			e.printStackTrace();	
		}

		return flight;
	}
	
	public Flight getFlightInformation(){
		try{
			tagNames.clear();
			tagNames.add("scheduledFlight");
			tagNames.add("departureAirportFsCode");
			tagNames.add("flightNumber");
			tagNames.add("arrivalAirportFsCode");
			tagNames.add("departureTerminal");
			tagNames.add("arrivalTerminal");
			tagNames.add("departureTime");
			tagNames.add("arrivalTime");
			
			
			if(getElements(tagNames,doc) == null) 
					return null;
			results = getElements(tagNames,doc).get(0);
			System.out.println("Results are: " + results);


			ArrayList<Airport> airports = new ArrayList<>();
			Airport depart = new AirportQuery().getAirportInfo(results.get("departureAirportFsCode"));
			Airport arrive = new AirportQuery().getAirportInfo(results.get("arrivalAirportFsCode"));

			airports.add(depart);
			airports.add(arrive);

			flight.setAirports(airports);

			flight.setArrivalTerminal(results.get("arrivalTerminal"));
			flight.setDepartureTerminal(results.get("departureTerminal"));

			flight.setoGADate(results.get("arrivalTime"));
			flight.setoGDDate(results.get("departureTime"));

		}catch(Exception e){		
			e.printStackTrace();	
		}

		return flight;
	}

	public ArrayList<Flight> getFlights(){
		ArrayList<Flight> list = null;
		tagNames.clear();
		tagNames.add("scheduledFlight");
		tagNames.add("carrierFsCode");
		tagNames.add("flightNumber");
		tagNames.add("departureAirportFsCode");
		tagNames.add("arrivalAirportFsCode");
		tagNames.add("departureTerminal");
		tagNames.add("departureTime");
		tagNames.add("arrivalTime");

		if(getElements(tagNames,doc) == null) return null;
		allResults = getElements(tagNames,doc);
		list = new ArrayList<>();
		for(int i = 0; i < allResults.size(); i ++){
			Flight current = new Flight();
			ArrayList<Airport> airports = new ArrayList<>();
			Airport depart = new AirportQuery().getAirportInfo(allResults.get(i).get("departureAirportFsCode"));
			Airport arrive = new AirportQuery().getAirportInfo(allResults.get(i).get("arrivalAirportFsCode"));

			airports.add(depart);
			airports.add(arrive);

			current.setAirports(airports);

			current.setDepartureTerminal(allResults.get(i).get("departureTerminal"));

			current.setoGADate(allResults.get(i).get("arrivalTime"));
			current.setoGDDate(allResults.get(i).get("departureTime"));

			Airline al = new AirlineQuery().getAirlineInfo(allResults.get(i).get("carrierFsCode"));
			current.setAirline(al);

			current.setFlightNumber(allResults.get(i).get("flightNumber"));

			list.add(current);
		}

		return list;	
	}
}
