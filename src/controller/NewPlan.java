package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DayWeather;
import model.Flight;
import model.HotelReservation;
import queries.FlightSearch;
import queries.Weather;

@WebServlet("/NewPlan")
public class NewPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewPlan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String name = request.getParameter("name");
			String flightNum = request.getParameter("num");
			String date = request.getParameter("date");
			String airport = request.getParameter("airport");
			String[] dateParse = date.split(" ");
			System.out.println(dateParse[1]);
			Date dateO = null;
			try {
				dateO = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(dateParse[1]);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateO);
			int month = cal.get(Calendar.MONTH);
			System.out.println(month);

			try {
				FlightSearch search = new FlightSearch(name, flightNum, dateParse[3], (month + 1) + "", dateParse[2].substring(0,2), "upcoming");
				Flight flight = search.getFlightInformation(airport);
				ArrayList<DayWeather> weather = new Weather(flight.getAirports().get(1).getState(),flight.getAirports().get(1).getCity()).getTenDayWeather();
				request.getSession().setAttribute("weather", weather);
				request.getSession().setAttribute("flight", flight);
				request.getRequestDispatcher("/WEB-INF/views/Itinerary.jsp").forward(request,response);

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String confirmNum = request.getParameter("confirm");
		String hotelName = request.getParameter("name");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String checkIn = request.getParameter("in");
		String checkOut = request.getParameter("out");
		String numOfRooms = request.getParameter("rooms");


		try {

			HotelReservation hotel = new HotelReservation(confirmNum, hotelName,address,city,state,zip,checkIn,checkOut,numOfRooms);

			request.getSession().setAttribute("hotel", hotel);
			request.getRequestDispatcher("/WEB-INF/views/Itinerary.jsp").forward(request,response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
