package car.tp4.servlet;

import car.tp4.services.BookService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stock")
public class StockServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913008468739547020L;

	@EJB
	private BookService bookService;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("stocks", bookService.getBooks("", "", true));
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/stock-list.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		final long stockId = Long.parseLong(request.getParameter("stockId"));
		
		final int quantity = Integer.parseInt(request.getParameter("quantity"));

		bookService.addQuantity(stockId, quantity);

		response.sendRedirect("/stock");
	}
}
