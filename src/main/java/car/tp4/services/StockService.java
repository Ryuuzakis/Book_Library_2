package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Stock;
import car.tp4.persistence.StockBean;

/**
 * 
 * @author Louis GUILBERT & Jonathan LECOINTE
 *
 * StockService : 
 * gère les stocks
 */
@Stateless
public class StockService {
	@EJB
	private StockBean stockBean;

	protected void setStockBean(final StockBean stockBean) {
		this.stockBean = stockBean;
	}
	
	/**
	 * Ajoute un nouveau stock
	 * @param stock
	 */
	public void addStock(final Stock stock) {
		stockBean.addStock(stock);
	}
	
	/**
	 * Ajoute une quantité à un stock
	 * @param stockId
	 * @param qte
	 */
	public void addQuantity(final long stockId, final int qte) {
		final Stock stock = stockBean.getStockById(stockId);
		stock.setQuantity(stock.getQuantity() + qte);
		stockBean.addStock(stock);
	}

	/**
	 * récupère tous les stocks
	 * @return
	 */
	public List<Stock> getAllStocks() {
		return stockBean.getAllStocks();
	}
}
