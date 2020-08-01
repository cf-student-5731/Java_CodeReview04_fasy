package ECommerce;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PurchaseHistory {
	private final int userId, productId, amount;
	private final GregorianCalendar purchaseDate;
	final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, kk:mm");
	private float price;

	public PurchaseHistory(int userId, int productId, int amount, float price) {
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.purchaseDate = createTimeStamp();
		this.price = price;
	}

	private GregorianCalendar createTimeStamp(){
		return (GregorianCalendar) Calendar.getInstance();
	}

	public int getProductId() {
		return productId;
	}

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public String getPurchaseDate() {
		return dateFormat.format(purchaseDate.getTime());
	}

	public int getAmount() {
		return this.amount;
	}

	public float getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "PurchaseHistory{" +
				"userId=" + userId +
				", productId=" + productId +
				", amount=" + amount +
				", a " + price +
				", => " + amount*price +
				", purchaseDate=" + getPurchaseDate() +
				'}';
	}
}
