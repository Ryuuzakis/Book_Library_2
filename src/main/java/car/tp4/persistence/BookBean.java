package car.tp4.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.entity.OrderEntry;
import car.tp4.exceptions.StockUnavailableException;

/**
 * Classe permettant de gérer les accès à la table Book
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 */
@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	protected void setEntityManager(final EntityManager em) {
		this.entityManager = em;
	}


	/**
	 * Sauvegarde un livre dans la base
	 * @param book le livre à sauvegarder
	 */
	public void addBook(final Book book) {
		entityManager.persist(book);
	}

	/**
	 * Récupère un livre de la base à partir de son id
	 * @param id id du livre
	 * @return le livre
	 */
	public Book getBookById(final long id) {
		final Query query = entityManager.createQuery("SELECT b from Book as b where b.id = :id").setParameter("id",
				id);
		return (Book) query.getSingleResult();
	}
	
	/**
	 * Requête permettant de récupérer les livres selon des critères de sélection
	 * @param author filtre sur l'auteur du livre
	 * @param title filtre sur le titre du livre
	 * @param allBooks true s'il faut prendre tous les livres, false s'il ne faut prendre que ceux qui ont du stock
	 * @return les livres répondant aux critères
	 */
	public List<Book> getBooks(final String author, final String title, final boolean allBooks) {
		return getBooks(defaultSelectQuery(allBooks), author, title);
	}

	/**
	 * Requête permettant de récupérer les livres selon des critères de sélection, triés par année croissante
	 * @param author filtre sur l'auteur du livre
	 * @param title filtre sur le titre du livre
	 * @param allBooks true s'il faut prendre tous les livres, false s'il ne faut prendre que ceux qui ont du stock
	 * @return les livres répondant aux critères
	 */
	public List<Book> getBooksOrderedByYearAsc(final String author, final String title, final boolean allBooks) {
		final String jpql = defaultSelectQuery(allBooks) + " order by m.year ASC";
		return getBooks(jpql, author, title);
	}

	/**
	 * Requête permettant de récupérer les livres selon des critères de sélection, triés par année décroissante
	 * @param author filtre sur l'auteur du livre
	 * @param title filtre sur le titre du livre
	 * @param allBooks true s'il faut prendre tous les livres, false s'il ne faut prendre que ceux qui ont du stock
	 * @return les livres répondant aux critères
	 */
	public List<Book> getBooksOrderedByYearDesc(final String author, final String title, final boolean allBooks) {
		final String jpql = defaultSelectQuery(allBooks) + " order by m.year DESC";
		return getBooks(jpql, author, title);
	}

	private String defaultSelectQuery(final boolean allBooks) {
		String jpql = "SELECT m from Book as m WHERE m.author LIKE :author AND m.title LIKE :title";

		if (!allBooks) {
			jpql += " AND m.quantity > 0";
		}

		return jpql;
	}

	private List<Book> getBooks(final String jpql, final String author, final String title) {
		final Query query = entityManager.createQuery(jpql);
		query.setParameter("author", "%" + author + "%");
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	public void validateOrder(final BookOrder order) throws StockUnavailableException {
		for (final OrderEntry entry : order.getOrderEntries()) {
			final Book savedBook = getBookById(entry.getBook().getId());
			if (savedBook.getQuantity() >= entry.getQuantity()) {
				savedBook.setQuantity(savedBook.getQuantity() - entry.getQuantity());
				entityManager.persist(savedBook);
			} else {
				throw new StockUnavailableException(savedBook.getTitle(),
						savedBook.getQuantity());
			}
		}
	}

}