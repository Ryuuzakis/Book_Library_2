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
import car.tp4.entity.Stock;
import car.tp4.services.BookService;
import car.tp4.services.StockService;

@WebServlet("/create")
public class CreateBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7762866734262256594L;

	@EJB
	private BookService bookService;
	
	@EJB private StockService stockService;

	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("create get");

		final RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/jsp/create.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		request.setCharacterEncoding("UTF-8");

		final String title = request.getParameter("title");
		final String author = request.getParameter("author");
		final int year = Integer.parseInt(request.getParameter("year"));

		final Book book = new Book(author, title, year);
		bookService.addBook(book);
		stockService.addStock(new Stock(book, 0));

		final RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/jsp/create-valid.jsp");
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("year", year);
		dispatcher.forward(request, response);
	}

}
