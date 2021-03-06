package car.tp4.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import car.tp4.entity.Book;
import car.tp4.entity.BookOrder;
import car.tp4.entity.OrderEntry;

/**
 * Classe permettant de gérer les accès à la table Basket
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 */
@Stateless
@Local
public class BasketBean {

	public void addBook(final BookOrder order, final Book book, final int quantity) {
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
}
