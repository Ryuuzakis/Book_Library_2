package car.tp4.persistance;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.Stock;

@Stateless
@Local
public class StockBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	public void addStock(final Stock stock) {
		entityManager.persist(stock);
	}

	public Stock getStockById(final long id) {
		final Query query = entityManager.createQuery("SELECT s from Stock as s where s.id = :id").setParameter("id", id);
		return (Stock) query.getSingleResult();
	}
	
	public List<Stock> getAllStocks() {
		final Query query = entityManager.createQuery("SELECT s from Stock as s");
		return query.getResultList();
	}
}