package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.BookOrder;
import car.tp4.persistance.BookOrderBean;

@Stateless
public class OrderService {
	@EJB
	private BookOrderBean bookOrderBean;
	
	public void saveBasket(final BookOrder basket) {
		bookOrderBean.validateOrder(basket);
	}

	public List<BookOrder> getAllOrders() {
		return bookOrderBean.getAllOrders();
	}
}
