package BankServer;

import java.io.Serializable;
import java.util.ArrayList;

import Stock.Stock;
import Stock.StockMarket;
import Stock.StockMarket.StockPrice;

public class StockServer implements Runnable, Serializable {
	private StockMarket stockMarket;
	public StockServer(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}
	public void setMarket(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}
	public StockMarket getMarket() {
		return stockMarket;
	}
	public void run() {
		stockMarket.setStockPrice(new ArrayList<StockPrice>());
		Stock samsung = new Stock("삼성전자", 100);
		StockPrice samsungPrice = stockMarket.new StockPrice(samsung.getName(), 1000, 50000);
		stockMarket.setOpen(true);
		stockMarket.getStockPrice().add(samsungPrice);
		//Thread samsungThread = new Thread(stockMarket.getStockPrice().get(0));

		Stock apple = new Stock("Apple", 100);
		StockPrice applePrice = stockMarket.new StockPrice(apple.getName(), 1000, 100000);
		stockMarket.getStockPrice().add(applePrice);
		//Thread appleThread = new Thread(stockMarket.getStockPrice().get(1));

		Stock andamiro = new Stock("안다미로", 100);
		StockPrice andamiroPrice = stockMarket.new StockPrice(andamiro.getName(), 1000, 25000);
		stockMarket.getStockPrice().add(andamiroPrice);
		//Thread andamiroThread = new Thread(stockMarket.getStockPrice().get(2));
		System.out.println(stockMarket.getStockPrice());
		
		System.out.println(stockMarket.getStockPrice().size());
		while(!stockMarket.getOpen()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}