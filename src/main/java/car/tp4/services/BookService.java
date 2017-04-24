package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.persistance.BookBean;

@Stateless
public class BookService {

	@EJB
	private BookBean bookBean;

	public void addBook(final Book book) {
		bookBean.addBook(book);
	}

	public List<Book> getAllBooks() {
		return bookBean.getAllBooks();
	}

	public List<Book> getAllBooksYearAsc() {
		return bookBean.getAllBooksOrderedByYearAsc();
	}

	public List<Book> getAllBooksYearDesc() {
		return bookBean.getAllBooksOrderedByYearDesc();
	}
}
