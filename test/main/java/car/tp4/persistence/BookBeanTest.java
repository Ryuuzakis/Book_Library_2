package car.tp4.persistence;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import car.tp4.entity.Book;
import car.tp4.persistence.BookBean;

@RunWith(MockitoJUnitRunner.class)
public class BookBeanTest {

	private BookBean bookBean;

	private EntityManager entityManager;

	@Before
	public void before() {
		this.bookBean = new BookBean();
		entityManager = Mockito.mock(EntityManager.class);
		bookBean.setEntityManager(entityManager);
		System.out.println("toto");
	}

	@Test
	public void testAddBook() {
		final Book b = Mockito.mock(Book.class);
		bookBean.addBook(b);
		Mockito.verify(entityManager).persist(b);
	}

}
