package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.services.StockService;

@WebServlet("/stock")
public class StockServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913008468739547020L;

	@EJB
	private StockService stockService;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("stocks", stockService.getAllStocks());
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/stock-list.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		final long stockId = Long.parseLong(request.getParameter("stockId"));
		
		final int quantity = Integer.parseInt(request.getParameter("quantity"));

		stockService.addQuantity(stockId, quantity);

		response.sendRedirect("/stock");
	}
}
