package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.persistence.BookBean;

@Stateless
public class BookService {

	@EJB
	private BookBean bookBean;

	protected void setBookBean(final BookBean bookBean) {
		this.bookBean = bookBean;
	}

	public void addBook(final Book book) {
		bookBean.addBook(book);
	}

	public List<Book> getAllBooks() {
		return bookBean.getAllBooks();
	}
}
