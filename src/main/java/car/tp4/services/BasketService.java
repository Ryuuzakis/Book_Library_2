package car.tp4.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.persistence.BasketBean;
import car.tp4.persistence.BookBean;

/**
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 *
 * BasketService : 
 * gère le panier
 */
@Stateless
public class BasketService {
	@EJB
	private BasketBean basketBean;
	
	@EJB
	private BookBean bookBean;
	
	protected void setBookBean(final BookBean bookBean) {
		this.bookBean = bookBean;
	}
	
	protected void setBasketBean(final BasketBean basketBean) {
		this.basketBean = basketBean;
	}
	
	/**
	 * Ajoute un livre commandé au panier
	 * @param order
	 * @param bookId
	 * @param quantity
	 */
	public void addBook(final BookOrder order, final long bookId, final int quantity) {
		System.out.println(this.bookBean);
		final Book book = bookBean.getBookById(bookId);
		basketBean.addBook(order, book, quantity);
	}
}
