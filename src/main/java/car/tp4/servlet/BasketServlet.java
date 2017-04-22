package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.services.BasketService;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6913008468739547020L;

	@EJB
	private BasketService basketService;
	
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		final long bookId = Long.parseLong(request.getParameter("bookId"));
		final int quantity = Integer.parseInt(request.getParameter("quantity"));

		basketService.addBook(bookId, quantity);

		response.sendRedirect("/books");
	}
}
