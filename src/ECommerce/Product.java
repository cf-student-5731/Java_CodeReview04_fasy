package ECommerce;

public class Product {

	private static int productCounter = 7000;    //static to keep id unique through all instances
	private final int productId;    //id for instance
	private final String productName, productDescription;
	private float productPrice;
	private final ProductCategory productCategory;
	private int stock;
	private final static int maxStock = 15;

	enum ProductCategory {
		TSHIRTS,
		TROUSERS,
		SHIRTS,
		JACKETS,
		ACCESSORIES
	}

	public Product(String productName, String productDescription, float productPrice, ProductCategory productCategory, int stock) throws StockLimitReached {
		increaseStock(stock);    //use setter to execute criteria
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productId = ++productCounter;    //automatically assign unique Id to new instance
	}

	//override automatic Id rule
	public Product(int productId, String productName, String productDescription, float productPrice, ProductCategory productCategory, int stock) throws StockLimitReached {
		increaseStock(stock);
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.productId = productId;
	}

	public int getProductId() {
		return this.productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public int getStock() {
		return stock;
	}

	public static int getMaxStock() {
		return maxStock;
	}

	public void increaseStock(int amount) throws StockLimitReached {
		if (this.stock + amount <= maxStock) {
			this.stock += amount;
		} else throw new StockLimitReached("Stock already full!");
	}

	public void decreaseStock(int amount) throws OutOfStockException {
		if (this.stock - amount >= 0) {
			this.stock -= amount;
			if(this.stock < 5){
				System.out.println("!!!@STAFF, " + this.productName + " IS GETTING LOW, PLEASE REORDER!!!");
			}
		} else throw new OutOfStockException("Out of Stock!");
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productDescription='" + productDescription + '\'' +
				", productPrice=" + productPrice +
				", productCategory=" + productCategory +
				", stock=" + stock +
				'}';
	}
}
