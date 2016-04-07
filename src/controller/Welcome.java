package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.FlightPlan;
import dbqueries.PhotobookQuery;
import model.Flight;
import model.Photobook;
import model.User;
import queries.FlightSearch;

@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public boolean invalid = false;
	
    public Welcome() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){
			request.getSession().removeAttribute("flight");
			request.getSession().removeAttribute("hotel");
			request.getSession().removeAttribute("weather");
			User user = (User)request.getSession().getAttribute("CurrentUser");
			ArrayList<Flight> flights = new FlightPlan().getFlightPlans(user.getId());
			ArrayList<Flight> history = new FlightPlan().getOldFlightPlans(user.getId());
			ArrayList<Photobook> photobooks = new PhotobookQuery().getPhotobooks(user.getId());
			request.setAttribute("photobooks", photobooks);	
			request.setAttribute("old", history);
			request.setAttribute("plans", flights);
			request.getRequestDispatcher("/WEB-INF/views/Welcome.jsp").forward(request,response);
			return;
		}
		response.sendRedirect("Login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("CurrentUser");
		ArrayList<Flight> flts = new FlightPlan().getFlightPlans(user.getId());
		ArrayList<Flight> history = new FlightPlan().getOldFlightPlans(user.getId());
		ArrayList<Photobook> photobooks = new PhotobookQuery().getPhotobooks(user.getId());
		request.setAttribute("photobooks", photobooks);	
		request.setAttribute("old", history);
		request.setAttribute("plans", flts);
		
		if (request.getParameter("form").equals("left")){
			String name = request.getParameter("name");
			String flightNum = request.getParameter("flight-num");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");


			FlightSearch flight = null;
			try {
				flight = new FlightSearch(name, flightNum, year, month, day,"upcoming");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Flight newFlight = flight.getFlightInformation();
			System.out.println("new flight is: " + newFlight);
			if(newFlight == null){
				invalid = true;
				request.setAttribute("name", name);
				request.setAttribute("flightNum", flightNum);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				request.setAttribute("day", day);
				request.setAttribute("invalid", invalid);
				request.setAttribute("form", "left");
				request.getRequestDispatcher("/WEB-INF/views/Welcome.jsp").forward(request,response);
			}
			else{
				request.setAttribute("flight", newFlight);
				request.getRequestDispatcher("/WEB-INF/views/Welcome.jsp").forward(request,response);
			}
		}
		else{
			String departing = request.getParameter("depart");
			String arriving = request.getParameter("arrive");
			
			String[] fs = departing.split(" ");
			String departingFS = fs[fs.length - 1].substring(1, 4);
			fs = arriving.split(" ");
			String arrivingFS = fs[fs.length - 1].substring(1, 4);
			
			String year = request.getParameter("year2");
			String month = request.getParameter("month2");
			String day = request.getParameter("day2");


			FlightSearch flight = null;
			try {
				flight = new FlightSearch(departingFS, arrivingFS, year, month, day,"future");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArrayList<Flight> flights = flight.getFlights();
			if(flights == null){
				invalid = true;
				request.setAttribute("depart", departing);
				request.setAttribute("arrive", arriving);
				request.setAttribute("year2", year);
				request.setAttribute("month2", month);
				request.setAttribute("day2", day);
				request.setAttribute("invalid", invalid);
				request.setAttribute("form", "right");
				request.getRequestDispatcher("/WEB-INF/views/Welcome.jsp").forward(request,response);
			}
			else{
				request.setAttribute("flights", flights);
				request.getRequestDispatcher("/WEB-INF/views/Welcome.jsp").forward(request,response);
			}
		}
	}
}
