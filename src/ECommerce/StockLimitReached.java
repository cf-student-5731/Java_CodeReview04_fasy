package ECommerce;

public class StockLimitReached extends Exception{
	public StockLimitReached(String message) {
		super(message);
	}
}
