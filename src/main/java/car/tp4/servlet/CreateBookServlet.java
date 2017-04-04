package car.tp4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create")
public class CreateBookServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("create get");

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/create.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int year = Integer.parseInt(request.getParameter("year"));
		
		// TODO: ajout dans la base
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/create-valid.jsp");
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("year", year);
		dispatcher.forward(request, response);
	}

}
