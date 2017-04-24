package car.tp4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.BookOrder;

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

		request.getSession().setAttribute("basket", new BookOrder());

		request.setAttribute("order", basket);
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/order-valid.jsp");
		dispatcher.forward(request, response);
	}
}
