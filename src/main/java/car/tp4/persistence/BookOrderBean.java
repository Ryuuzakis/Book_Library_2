package car.tp4.persistence;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.BookOrder;

/**
 * Classe permettant de gérer les accès à la table BookOrder
 *
 * @author Louis GUILBERT & Jonathan LECOINTE
 */
@Stateless
@Local
public class BookOrderBean {
	
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	protected void setEntityManager(final EntityManager em) {
		this.entityManager = em;
	}

	/**
	 * Méthode permettant de valider une commande
	 * @param order la commande à valider
	 */
	public void validateOrder(final BookOrder order) {
		System.out.println(order.getId());
		entityManager.persist(order);
	}

	/**
	 * Méthode permettant de récupérer une commande grâce à son id
	 * @param id l'id de la commande
	 * @return la commande
	 */
	public BookOrder getOrderById(final long id) {
		final Query query = entityManager.createQuery("SELECT o from BookOrder as o where o.id = :id").setParameter("id", id);
		return (BookOrder) query.getSingleResult();
	}

	/**
	 * Méthode permettant de récupérer toutes les commandes
	 * @return toutes les commandes
	 */
	public List<BookOrder> getAllOrders() {
		final Query query = entityManager.createQuery("SELECT o, e from BookOrder as o JOIN OrderEntry as e on e.id = o.OrderEntry");
		return query.getResultList();
	}
}
