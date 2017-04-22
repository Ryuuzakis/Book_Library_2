package car.tp4.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.persistance.BasketBean;
import car.tp4.persistance.BookBean;

@Stateless
public class BasketService {
	@EJB
	private BasketBean basketBean;
	
	@EJB
	private BookBean bookBean;
	
	public void addBook(final BookOrder order, final long bookId, final int quantity) {
		final Book book = bookBean.getBookById(bookId);
		basketBean.addBook(order, book, quantity);
	}
}
