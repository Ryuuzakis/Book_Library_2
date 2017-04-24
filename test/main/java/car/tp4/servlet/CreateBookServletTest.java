package car.tp4.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Book;
import car.tp4.entity.Stock;
import car.tp4.services.BookService;
import car.tp4.services.StockService;

public class CreateBookServletTest {
	private CreateBookServlet createBookServlet;
	private BookService bookService;
	private StockService stockService;

	private RequestDispatcher dispatcher;
	private ServletContext context;

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void before() {

		dispatcher = Mockito.mock(RequestDispatcher.class);
		context = Mockito.mock(ServletContext.class);
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);

		createBookServlet = Mockito.spy(new CreateBookServlet());

		bookService = Mockito.mock(BookService.class);
		createBookServlet.setBookService(bookService);

		stockService = Mockito.mock(StockService.class);
		createBookServlet.setStockService(stockService);

		Mockito.when(context.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
		Mockito.doReturn(context).when(createBookServlet).getServletContext();
	}

	@Test
	public void doGetTest() throws ServletException, IOException {
		createBookServlet.doGet(request, response);

		Mockito.verify(dispatcher).forward(request, response);
	}

	@Test
	public void doPostTest() throws ServletException, IOException {
		Mockito.when(request.getParameter("title")).thenReturn("toto");
		Mockito.when(request.getParameter("author")).thenReturn("tata");
		Mockito.when(request.getParameter("year")).thenReturn("1234");
		
		createBookServlet.doPost(request, response);
		
		Mockito.verify(request).getParameter("title");
		Mockito.verify(request).getParameter("author");
		Mockito.verify(request).getParameter("year");
		
		Mockito.verify(bookService).addBook(Mockito.any(Book.class));
		Mockito.verify(stockService).addStock(Mockito.any(Stock.class));
		Mockito.verify(dispatcher).forward(request, response);
		
		Mockito.verify(request).setAttribute("title", "toto");
		Mockito.verify(request).setAttribute("author", "tata");
		Mockito.verify(request).setAttribute("year", 1234);
		
		Mockito.verify(dispatcher).forward(request, response);
	}

}
