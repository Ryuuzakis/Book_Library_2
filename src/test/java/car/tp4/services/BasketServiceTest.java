package car.tp4.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.persistence.BasketBean;
import car.tp4.persistence.BookBean;

public class BasketServiceTest {
	private BookBean bookBean;
	private BasketBean basketBean;
	private BasketService basketService;
	
	@Before
	public void before() {
		bookBean = Mockito.mock(BookBean.class);
		basketService = new BasketService();
		basketService.setBookBean(bookBean);
		
		basketBean = Mockito.mock(BasketBean.class);
		basketService.setBasketBean(basketBean);
	}
	
	@Test
	public void addBookTest() {
		final BookOrder order = Mockito.mock(BookOrder.class);
		basketService.addBook(order, 42, 1337);
		Mockito.verify(bookBean).getBookById(42);
		Mockito.verify(basketBean).addBook(Mockito.eq(order), Mockito.any(Book.class), Mockito.eq(1337));
	}

}
