package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SessionReset")
public class SessionReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionReset() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){

		request.getSession().removeAttribute("flight");
		request.getSession().removeAttribute("hotel");
		request.getSession().removeAttribute("weather");
		response.sendRedirect("Welcome");
		return;
		}
		response.sendRedirect("Login");
	}
}
