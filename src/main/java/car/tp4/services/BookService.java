package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.persistence.BookBean;

/**
 * 
 * @author Louis GUILBERT & Jonathan LECOINTe
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

	/**
	 * recherche des livres selon l'auteur ou le titre
	 * @param author
	 * @param title
	 * @param allBooks
	 * @return la liste des livre filtré
	 */
	public List<Book> getBooks(final String author, final String title, final boolean allBooks) {
		return bookBean.getBooks(author, title, allBooks);
	}

	public List<Book> getBooksOrderedByYear(final String author, final String title, final boolean asc, final boolean allBooks) {
		if (asc) {
			return bookBean.getBooksOrderedByYearAsc(author, title, allBooks);
		} else {
			return bookBean.getBooksOrderedByYearDesc(author, title, allBooks);
		}
	}

	/**
	 * Ajoute une quantité au stock d'un livre
	 * @param bookId
	 * @param qty
	 */
	public void addQuantity(final long bookId, final int qty) {
		final Book book = bookBean.getBookById(bookId);

		if (qty < 1) {
			// TODO: error
		}

		book.setQuantity(book.getQuantity() + qty);
		bookBean.addBook(book);
	}
}
