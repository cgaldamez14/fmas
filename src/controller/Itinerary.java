package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dbqueries.FlightPlan;
import model.DayWeather;
import model.Flight;
import queries.Weather;

/**
 * Servlet implementation class Itinerary
 */
@WebServlet("/Itinerary")
public class Itinerary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Itinerary() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Map<String,Object> plan = new FlightPlan().getFlightPlanByID(Integer.parseInt(request.getParameter("id")));
	Flight flight = (Flight) plan.get("flight");
	ArrayList<DayWeather> weather = null;
	try {
		weather = new Weather(flight.getAirports().get(1).getState(),flight.getAirports().get(1).getCity()).getTenDayWeather();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	}
	request.setAttribute("weather", weather);
	request.setAttribute("hotel", plan.get("hotel"));
	request.setAttribute("flight", flight);
	request.getRequestDispatcher("/WEB-INF/views/Itinerary.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
