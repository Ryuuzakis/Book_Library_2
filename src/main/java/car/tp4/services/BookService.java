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
	 * 
	 * @return la liste de tous les livres
	 */
	public List<Book> getAllBooks() {
		return bookBean.getAllBooks();
	}

	/**
	 * récupère les livres organisés par an ascendant
	 * @return
	 */
	public List<Book> getAllBooksYearAsc() {
		return bookBean.getAllBooksOrderedByYearAsc();
	}

	/**
	 * récupère les livres organisés par an déscendant
	 * @return
	 */
	public List<Book> getAllBooksYearDesc() {
		return bookBean.getAllBooksOrderedByYearDesc();
	}
}
