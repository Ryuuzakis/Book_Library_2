package car.tp4.persistence;

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
	
	protected void setEntityManager(final EntityManager em) {
		this.entityManager = em;
	}

	public void addBook(final Book book) {
		entityManager.persist(book);
	}
	
	public Book getBookById(final long id) {
		final Query query = entityManager.createQuery("SELECT b from Book as b where b.id = :id")
				.setParameter("id", id);
		return (Book) query.getSingleResult();
	}

	public List<Book> getAllBooks() {
		final Query query = entityManager.createQuery("SELECT m from Book as m");
		return query.getResultList();
	}

	public List<Book> getAllBooksOrderedByYearAsc() {
		final Query query = entityManager.createQuery("SELECT m from Book as m order by m.year ASC");
		return query.getResultList();
	}

	public List<Book> getAllBooksOrderedByYearDesc() {
		final Query query = entityManager.createQuery("SELECT m from Book as m order by m.year DESC");
		return query.getResultList();
	}

}