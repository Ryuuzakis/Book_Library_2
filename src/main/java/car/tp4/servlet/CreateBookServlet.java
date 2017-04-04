package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.services.BookService;

@WebServlet("/create")
public class CreateBookServlet extends HttpServlet {

	@EJB
	private BookService bookService;
	
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

		// TODO : Add year
		bookService.addBook(new Book(author, title));

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/create-valid.jsp");
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("year", year);
		dispatcher.forward(request, response);
	}

}
