package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.FlightPlan;
import model.User;

@WebServlet("/DeletePlan")
public class DeletePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeletePlan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){

			User user = (User) request.getSession().getAttribute("CurrentUser");
			int flightPlanId = Integer.parseInt(request.getParameter("id"));
			FlightPlan.deleteFlightPlan(flightPlanId, user.getId());
			response.sendRedirect("Welcome");
			return;

		}
		response.sendRedirect("Login");	}
}
