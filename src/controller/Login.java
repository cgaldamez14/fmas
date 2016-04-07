package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.AirlineQuery;
import dbqueries.AirportQuery;
import dbqueries.ImageQuery;
import dbqueries.UserQuery;
import model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		/* List of airlines and airports are set in the application scope for
		 * demonstration purposes */
		getServletContext().setAttribute("airlines", airlineList());
		getServletContext().setAttribute("airports", airportList());
		getServletContext().setAttribute("images", new ImageQuery().getImages());

		try{
			Class.forName( "com.mysql.jdbc.Driver" );
		}
		catch( ClassNotFoundException e ){
			throw new ServletException( e );
		}
		finally{
			try {
				/* These are only needed to set up airport and airline tables in the fmas database.
				 * Fmas database also needs to be created.*/
				AirportQuery.setAirportTable();
				AirlineQuery.setAirlineTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user = new UserQuery().verifyUser(request.getParameter("username"), request.getParameter("password"));
		if(user != null){
			request.getSession().setAttribute("CurrentUser", user);	
			response.sendRedirect("Welcome");
			return;
		}
		request.setAttribute("valid","no");
		request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
	}

	private ArrayList<String> airlineList(){
		ArrayList<String> airlineMenu = new ArrayList<>();
		airlineMenu.add("Alaska Airlines");
		airlineMenu.add("American Airlines");
		airlineMenu.add("Delta Air Lines");
		airlineMenu.add("Frontier Airlines");
		airlineMenu.add("Hawaiian Airlines");
		airlineMenu.add("JetBlue Airways");
		airlineMenu.add("Spirit Airlines");
		airlineMenu.add("Southwest Airlines");
		airlineMenu.add("Virgin America");
		return airlineMenu;
	}

	private ArrayList<String> airportList(){
		ArrayList<String> airportMenu = new ArrayList<>();
		airportMenu.add("Los Angeles International Airport (LAX)");
		airportMenu.add("Washington Dulles International Airport (IAD)");
		airportMenu.add("Miami International Airport (MIA)");
		airportMenu.add("Chicago Rockford International Airport (RFD)");
		airportMenu.add("Denver International Airport (DEN)");
		airportMenu.add("Chicago Midway International Airport (MDW)");
		airportMenu.add("Ronald Reagan National Airport (DCA)");
		airportMenu.add("Logan International Airport (BOS)");
		airportMenu.add("Portland International Airport (PDX)");
		airportMenu.add("William P. Hobby Airport (HOU)");
		airportMenu.add("George Bush Intercontinental Airport (IAH)");
		airportMenu.add("Seattle-Tacoma International Airport (SEA)");
		airportMenu.add("Albany International Airport (ALB)");
		airportMenu.add("Buffalo Niagara International Airport (BUF)");
		airportMenu.add("John F. Kennedy International Airport (JFK)");
		airportMenu.add("LaGuardia Airport (LGA)");
		airportMenu.add("Greater Rochester International Airport (ROC)");
		airportMenu.add("Stewart International Airport (SWF)");
		return airportMenu;
	}
}
