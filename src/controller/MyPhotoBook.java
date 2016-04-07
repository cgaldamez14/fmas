package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbqueries.PhotobookQuery;
import model.Photobook;
import model.User;

/**
 * Servlet implementation class MyPhotoBook
 */
@WebServlet("/MyPhotoBook")
public class MyPhotoBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPhotoBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("CurrentUser") != null){
		User user = (User)request.getSession().getAttribute("CurrentUser");
		ArrayList<Photobook> photobooks = new PhotobookQuery().getAllPhotobooks(user.getId());
		request.setAttribute("count", photobooks.size());
		request.setAttribute("photobooks", photobooks);	
		request.getRequestDispatcher("/WEB-INF/views/MyPhotoBook.jsp").forward(request, response);
		}
		response.sendRedirect("Login");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
