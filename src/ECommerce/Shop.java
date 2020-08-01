package ECommerce;

import java.util.*;

public class Shop {
	private String name, address;
	HashMap<Integer, Product> products = new HashMap<>();

	public Shop(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void addProduct(Product product){
		this.products.put(product.getProductId(), product);
	}

	public TreeMap<Integer, Product> getProducts() {	//TreeMap to get sorted output
		return new TreeMap<>(this.products);
	}

	public HashMap<Integer, Product> getProductsAsAdmin() {
		return products;
	}


}
