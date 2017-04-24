package car.tp4.persistance;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.Book;

@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	public void addBook(final Book book) {
		entityManager.persist(book);
	}
	
	public Book getBookById(final long id) {
		final Query query = entityManager.createQuery("SELECT b from Book as b where b.id = :id").setParameter("id", id);
		return (Book) query.getSingleResult();
	}

	private String defaultSelectQuery(boolean allBooks) {
		String jpql = "SELECT m from Book as m WHERE m.author LIKE :author AND m.title LIKE :title";

		if (!allBooks) {
			jpql += " AND m.quantity > 0";
		}

		return jpql;
	}

	public List<Book> getBooks(String author, String title, boolean allBooks) {
		return getBooks(defaultSelectQuery(allBooks), author, title);
	}

	public List<Book> getBooksOrderedByYearAsc(String author, String title, boolean allBooks) {
		String jpql = defaultSelectQuery(allBooks) + " order by m.year ASC";
		return getBooks(jpql, author, title);
	}

	public List<Book> getBooksOrderedByYearDesc(String author, String title, boolean allBooks) {
		String jpql = defaultSelectQuery(allBooks) + " order by m.year DESC";
		return getBooks(jpql, author, title);
	}

	public List<Book> getBooks(String jpql, String author, String title) {
		final Query query = entityManager.createQuery(jpql);
		query.setParameter("author", "%" + author + "%");
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

}