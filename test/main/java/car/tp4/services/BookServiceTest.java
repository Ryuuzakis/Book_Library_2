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
	public void getBooksTest() {
		bookService.getBooks("tata", "toto", false);
		Mockito.verify(bookBean).getBooks("tata", "toto", false);
	}
	
	@Test
	public void getBooksOrderedByYearAscTest() {
		bookService.getBooksOrderedByYear("toto", "tata", true, false);
		Mockito.verify(bookBean).getBooksOrderedByYearAsc("toto", "tata", false);
	}
	
	@Test
	public void getBooksOrderedByYearDescTest() {
		bookService.getBooksOrderedByYear("toto", "tata", false, false);
		Mockito.verify(bookBean).getBooksOrderedByYearDesc("toto", "tata", false);
	}

}
