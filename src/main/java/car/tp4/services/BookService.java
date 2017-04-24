package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.exceptions.NegativeQuantityException;
import car.tp4.persistence.BookBean;

/**
 * 
 * @author Louis GUILBERT & Jonathan LECOINTE
 *
 * BookService : 
 * gère les fonctionnalités de recherche/ajout de livre
 */
@Stateless
public class BookService {

	@EJB
	private BookBean bookBean;

	protected void setBookBean(final BookBean bookBean) {
		this.bookBean = bookBean;
	}

	/**
	 * Ajoute un livre à la base
	 * @param book
	 */
	public void addBook(final Book book) {
		bookBean.addBook(book);
	}

	public List<Book> getBooks(String author, String title, boolean allBooks) {
		return bookBean.getBooks(author, title, allBooks);
	}

	public List<Book> getBooksOrderedByYear(String author, String title, boolean asc, boolean allBooks) {
		if (asc) {
			return bookBean.getBooksOrderedByYearAsc(author, title, allBooks);
		} else {
			return bookBean.getBooksOrderedByYearDesc(author, title, allBooks);
		}
	}

	public void addQuantity(final long bookId, final int qty) throws NegativeQuantityException {
		final Book book = bookBean.getBookById(bookId);

		if (qty < 1) {
			throw new NegativeQuantityException();
		}

		book.setQuantity(book.getQuantity() + qty);
		bookBean.addBook(book);
	}
}
