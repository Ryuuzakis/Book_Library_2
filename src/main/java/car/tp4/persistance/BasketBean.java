package car.tp4.persistance;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.entity.OrderEntry;

@Stateful
@Local
public class BasketBean {
	private final BookOrder order = new BookOrder();

	public void addBook(final Book book, final int quantity) {
		final List<OrderEntry> orderEntries = order.getOrderEntries();

		for (int i = 0 ; i < orderEntries.size() ; i++) {
			final OrderEntry entry = orderEntries.get(i);
			if (entry.getBook().equals(book)) {
				if(quantity <= 0) {
					orderEntries.remove(i);
				} else {					
					entry.setQuantity(quantity);
				}
				return;
			}
		}

		orderEntries.add(new OrderEntry(quantity, book));
	}

	public BookOrder getBasket() {
		return order;
	}
}
