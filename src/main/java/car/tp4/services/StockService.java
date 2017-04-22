package car.tp4.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Stock;
import car.tp4.persistance.StockBean;

@Stateless
public class StockService {
	@EJB
	private StockBean stockBean;

	public void addStock(final Stock stock) {
		stockBean.addStock(stock);
	}
	
	public void addQuantity(final long stockId, final int qte) {
		final Stock stock = stockBean.getStockById(stockId);
		stock.setQuantity(stock.getQuantity() + qte);
		stockBean.addStock(stock);
	}

	public List<Stock> getAllStocks() {
		return stockBean.getAllStocks();
	}
}
