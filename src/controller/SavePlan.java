package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.FlightPlan;
import model.Flight;
import model.HotelReservation;
import model.User;

@WebServlet("/SavePlan")
public class SavePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SavePlan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){

			User user = (User) request.getSession().getAttribute("CurrentUser");
			Flight flight = (Flight) request.getSession().getAttribute("flight");
			HotelReservation hotel = (HotelReservation) request.getSession().getAttribute("hotel");
			FlightPlan plan = new FlightPlan(user,flight,hotel);
			plan.saveHotelPlan();
			plan.saveFlightPlan();
			response.sendRedirect("SessionReset");
			return;
		}
		response.sendRedirect("Login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
