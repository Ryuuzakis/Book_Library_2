package car.tp4.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.services.BasketService;
import car.tp4.services.BookService;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913008468739547020L;

	@EJB
	private BookService bookService;
	
	@EJB
	private BasketService basketService;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		BookOrder basket = (BookOrder) request.getSession().getAttribute("basket");
		if (basket == null) {
			basket = new BookOrder();
		}

		List<Book> books = getBooks(request);
		
		request.getSession().getAttribute("basket");
		request.setAttribute("books", books);
		request.setAttribute("basket", basket);
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Récupère les livres depuis la base de données selon les paramètres envoyés par le client dans la requête
	 * @param request la requête du client
	 * @return la liste des livres à afficher
	 */
	protected List<Book> getBooks(final HttpServletRequest request) {
		String order = request.getParameter("order");
		int orderValue = order == null ? -1 : order.equals("asc") ? 1 : 2;

		List<Book> books = null;
		switch(orderValue) {
			case 1:
				books = bookService.getAllBooksYearAsc();
				break;
			case 2:
				books = bookService.getAllBooksYearDesc();
				break;
			default:
				books = bookService.getAllBooks();
				break;
		}

		for (Book b : books) {
			System.out.println(b);
		}

		return books;
	}
}
