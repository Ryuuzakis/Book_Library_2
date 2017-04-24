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

	/**
	 * Recherche des livres selon l'auteur ou le titre
	 * @param author un filtre sur l'auteur du livre, mettre une chaine vide pour ne pas filtrer
	 * @param title un filtre sur le titre du livre, mettre une chaine vide pour ne pas filtrer
	 * @param allBooks true s'il faut prendre tous les livres, false s'il ne faut prendre que ceux qui ont du stock
	 * @return la liste des livres filtrée
	 */
	public List<Book> getBooks(final String author, final String title, final boolean allBooks) {
		return bookBean.getBooks(author, title, allBooks);
	}

	/**
	 * Recherche des livres selon l'auteur ou le titre et les trie
	 * @param author un filtre sur l'auteur du livre, mettre une chaine vide pour ne pas filtrer
	 * @param title un filtre sur le titre du livre, mettre une chaine vide pour ne pas filtrer
	 * @param asc trie de manière croissante si true, de manière décroissante sinon
	 * @param allBooks true s'il faut prendre tous les livres, false s'il ne faut prendre que ceux qui ont du stock
	 * @return la liste des livres filtrée et triée
	 */
	public List<Book> getBooksOrderedByYear(final String author, final String title, final boolean asc, final boolean allBooks) {
		if (asc) {
			return bookBean.getBooksOrderedByYearAsc(author, title, allBooks);
		} else {
			return bookBean.getBooksOrderedByYearDesc(author, title, allBooks);
		}
	}

	/**
	 * Ajoute une quantité au stock d'un livre
	 * @param bookId id du book dont il faut modifier la quantité
	 * @param qty la quantité à ajouter
	 * @throws NegativeQuantityException
	 */
	public void addQuantity(final long bookId, final int qty) throws NegativeQuantityException {
		final Book book = bookBean.getBookById(bookId);

		if (qty < 1) {
			throw new NegativeQuantityException();
		}

		book.setQuantity(book.getQuantity() + qty);
		bookBean.addBook(book);
	}
}
