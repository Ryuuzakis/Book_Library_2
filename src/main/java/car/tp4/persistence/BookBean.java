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

@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	protected void setEntityManager(final EntityManager em) {
		this.entityManager = em;
	}

	public void addBook(final Book book) {
		entityManager.persist(book);
	}

	public Book getBookById(final long id) {
		final Query query = entityManager.createQuery("SELECT b from Book as b where b.id = :id").setParameter("id",
				id);
		return (Book) query.getSingleResult();
	}

	private String defaultSelectQuery(final boolean allBooks) {
		String jpql = "SELECT m from Book as m WHERE m.author LIKE :author AND m.title LIKE :title";

		if (!allBooks) {
			jpql += " AND m.quantity > 0";
		}

		return jpql;
	}

	public List<Book> getBooks(final String author, final String title, final boolean allBooks) {
		return getBooks(defaultSelectQuery(allBooks), author, title);
	}

	public List<Book> getBooksOrderedByYearAsc(final String author, final String title, final boolean allBooks) {
		final String jpql = defaultSelectQuery(allBooks) + " order by m.year ASC";
		return getBooks(jpql, author, title);
	}

	public List<Book> getBooksOrderedByYearDesc(final String author, final String title, final boolean allBooks) {
		final String jpql = defaultSelectQuery(allBooks) + " order by m.year DESC";
		return getBooks(jpql, author, title);
	}

	public List<Book> getBooks(final String jpql, final String author, final String title) {
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