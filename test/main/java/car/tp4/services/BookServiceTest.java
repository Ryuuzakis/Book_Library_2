package car.tp4.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Book;
import car.tp4.persistence.BookBean;

public class BookServiceTest {

	private BookBean bookBean;
	private BookService bookService;
	
	@Before
	public void before() {
		bookBean = Mockito.mock(BookBean.class);
		bookService = new BookService();
		bookService.setBookBean(bookBean);
	}
	
	@Test
	public void setBookBeanTest() {
		final Book b = Mockito.mock(Book.class);
		bookService.addBook(b);
		Mockito.verify(bookBean).addBook(b);
	}
	
	
	@Test
	public void getAllBooksTest() {
		final Book b = Mockito.mock(Book.class);
		bookService.getAllBooks();
		Mockito.verify(bookBean).getAllBooks();
	}

}
