package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.BookOrder;
import car.tp4.exceptions.StockUnavailableException;
import car.tp4.services.BookService;

/**
 * 
 * @author Louis GUILBERT & Jonathan LECOINTE
 *
 *         OrderServlet : Contrôle les commandes
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259173329397252502L;

	@EJB
	private BookService bookService;
	
	/**
	 * Commande la requête POST sur /order
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		BookOrder basket = (BookOrder) request.getSession().getAttribute("basket");

		if (basket == null) {
			basket = new BookOrder();
		}
	
		String error = null;
		
		try {
			bookService.validateOrder(basket);
		} catch (final StockUnavailableException e) {
			System.out.println("Error !");
			System.out.println(e.getMessage());
			error = e.getMessage();
			System.out.println(error);
		}

		request.getSession().setAttribute("basket", new BookOrder());
		

		request.setAttribute("error", error);
		request.setAttribute("order", basket);
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/order-valid.jsp");
		dispatcher.forward(request, response);
	}
}
