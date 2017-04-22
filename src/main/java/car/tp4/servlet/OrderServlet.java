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

import car.tp4.entity.BookOrder;
import car.tp4.entity.OrderEntry;
import car.tp4.services.OrderService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259173329397252502L;

	@EJB
	private OrderService orderService;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		final List<BookOrder> orders = orderService.getAllOrders();
		System.out.println("orders");
		System.out.println(orders);
		
		for(final BookOrder order : orders) {
			System.out.println("order");
			System.out.println(order);
			for(final OrderEntry entry : order.getOrderEntries()) {
				System.out.println("entry");
				System.out.println(entry);
				System.out.println("entry book");
				System.out.println(entry.getBook());
				System.out.println("entry quantity");
				System.out.println(entry.getQuantity());
			}
		}
		request.setAttribute("orders", orderService.getAllOrders());
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/order-list.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		BookOrder basket = (BookOrder) request.getSession().getAttribute("basket");

		if (basket == null) {
			basket = new BookOrder();
		}

		orderService.saveBasket(basket);
		request.getSession().setAttribute("basket", new BookOrder());

		response.sendRedirect("/order");
	}
}
