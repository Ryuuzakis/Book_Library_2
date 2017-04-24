package car.tp4.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Stock;
import car.tp4.persistence.StockBean;

public class StockServiceTest {
	private StockBean stockBean;
	private StockService stockService;
	
	@Before
	public void before() {
		stockBean = Mockito.mock(StockBean.class);
		stockService = new StockService();
		stockService.setStockBean(stockBean);
	}
	
	@Test
	public void addStockTest() {
		final Stock stock = Mockito.mock(Stock.class);
		stockService.addStock(stock);
		Mockito.verify(stockBean).addStock(stock);
	}
	
	@Test
	public void addQuantityTest() {
		final Stock stock = Mockito.mock(Stock.class);
		Mockito.when(stockBean.getStockById(42)).thenReturn(stock);
		
		stockService.addQuantity(42, 1337);
		Mockito.verify(stockBean).getStockById(42);
		Mockito.verify(stockBean).addStock(Mockito.any(Stock.class));
	}

	@Test
	public void getAllStocksTest() {
		stockService.getAllStocks();
		Mockito.verify(stockBean).getAllStocks();
	}
}
