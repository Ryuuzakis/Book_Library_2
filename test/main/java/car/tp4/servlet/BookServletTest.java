package car.tp4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.services.BasketService;
import car.tp4.services.BookService;

public class BookServletTest {
	private BookServlet bookServlet;
	private BookService bookService;
	private BasketService basketService;

	private RequestDispatcher dispatcher;
	private ServletContext context;
	private HttpSession session;

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void before() {

		dispatcher = Mockito.mock(RequestDispatcher.class);
		context = Mockito.mock(ServletContext.class);
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		session = Mockito.mock(HttpSession.class);

		bookServlet = Mockito.spy(new BookServlet());

		basketService = Mockito.mock(BasketService.class);
		bookService = Mockito.mock(BookService.class);
		
		bookServlet.setBookService(bookService);
		bookServlet.setBasketService(basketService);

		Mockito.when(request.getSession()).thenReturn(session);
		Mockito.when(context.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
		Mockito.doReturn(context).when(bookServlet).getServletContext();
	}

	@Test
	public void doGetTest() throws ServletException, IOException {
		bookServlet.doGet(request, response);

		Mockito.verify(request, Mockito.times(2)).getSession();
		Mockito.verify(session, Mockito.times(2)).getAttribute("basket");
		Mockito.verify(request).setAttribute(Mockito.eq("books"), Mockito.any());
		Mockito.verify(request).setAttribute(Mockito.eq("basket"), Mockito.any());
		
		Mockito.verify(dispatcher).forward(request, response);
	}

}
