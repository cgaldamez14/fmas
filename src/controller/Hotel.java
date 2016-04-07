package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.HotelQuery;
import model.HotelReservation;
import model.User;

@WebServlet("/Hotel")
public class Hotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Hotel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){
			request.setAttribute("invalid", false);
			request.getRequestDispatcher("/WEB-INF/views/Confirmation.jsp").forward(request,response);
			return;
		}
		response.sendRedirect("Login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){
			User user = (User)request.getSession().getAttribute("CurrentUser");
			String number = request.getParameter("confirmationNumber");

			HotelReservation hotel = new HotelQuery().getHotelInfo(user.getId(), number);
			
			if(hotel != null){
				request.setAttribute("hotel", hotel);
				request.getRequestDispatcher("/WEB-INF/views/Hotel.jsp").forward(request,response);
				return;
			}
			
			request.setAttribute("invalid", true);
			request.getRequestDispatcher("/WEB-INF/views/Confirmation.jsp").forward(request,response);
			return;
		}
		response.sendRedirect("Login");
	}

}
