package car.tp4.persistance;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.BookOrder;

@Stateless
@Local
public class BookOrderBean {
	
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	public void validateOrder(final BookOrder order) {
		System.out.println(order.getId());
		entityManager.persist(order);
	}
	
	public BookOrder getOrderById(final long id) {
		final Query query = entityManager.createQuery("SELECT o from BookOrder as o where o.id = :id").setParameter("id", id);
		return (BookOrder) query.getSingleResult();
	}

	public List<BookOrder> getAllOrders() {
		final Query query = entityManager.createQuery("SELECT o, e from BookOrder as o inner join OrderEntry as e on e.id = o.OrderEntry");
		return query.getResultList();
	}
}
