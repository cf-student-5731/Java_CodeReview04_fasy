package ECommerce;

import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
	//create shop01
	private static final Shop shop01 = new Shop("allStuff", "myStreet 11, 1234 myCity, myCountry");

	//HashMap to store thr users
	private static final HashMap<Integer, User> users = new HashMap<>();

	//getter for shop01 writing enabled
	public static Shop getShop01() {
		return shop01;
	}

	//getter for users, gives a copy
	public static HashMap<Integer, User> getUsers() {
		return new HashMap(users);
	}

	//main entry point
	public static void main(String[] args) {

		//define dateFormat
		final SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm dd.MM.yyyy");

		//HashMap for testing products
//		HashMap<Integer, Product> testProducts = new HashMap<>();

		//create test Objects and add them to test HashMap
/*

//		create testData and add to Hashmap
//		object is only created if maxStock is ok
		try {
//			product001 will not be created
			Product product001 = new Product("Summer T-Shirt", "Light T-shirt for hot temperatures", 25.90f, Product.ProductCategory.TSHIRTS, 17);
			testProducts.put(product001.getProductId(), product001);
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s%n", e.getMessage(), "product001 not created, you can only add " + Product.getMaxStock() + " items, please try again!");
		}
		try {
			Product product002 = new Product("Summer Trousers", "Light trousers for hot temperatures", 45.90f, Product.ProductCategory.TROUSERS, 5);
			testProducts.put(product002.getProductId(), product002);
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s%n", e.getMessage(), "product002 not created, you can only add " + Product.getMaxStock() + " items, please try again!");
		}
		try {
			Product product003 = new Product("Summer Shirt", "Light shirt for hot temperatures", 69.90f, Product.ProductCategory.SHIRTS, 8);
			testProducts.put(product003.getProductId(), product003);
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s%n", e.getMessage(), "product003 not created, you can only add " + Product.getMaxStock() + " items, please try again!");
		}
		try {
			Product product004 = new Product("Summer Jacket", "Light jacket for hot temperatures", 89.90f, Product.ProductCategory.JACKETS, 11);
			testProducts.put(product004.getProductId(), product004);
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s%n", e.getMessage(), "product004 not created, you can only add " + Product.getMaxStock() + " items, please try again!");
		}
		try {
			Product product005 = new Product("Golden Belt", "silver Belt to hold up your trousers", 112.90f, Product.ProductCategory.ACCESSORIES, 15);
			testProducts.put(product005.getProductId(), product005);
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s%n", e.getMessage(), "product005 not created, you can only add " + Product.getMaxStock() + " items, please try again!");
		}

//		test products
		System.out.printf("%n%s%n", "Product dummy Test");
		for (int i : testProducts.keySet()) {
			System.out.println(testProducts.get(i));
		}
		System.out.printf("%n%n%n");

*/
		//Version where products and users are created directly inside the HashMap

//		add products to shop01
		System.out.println("PRODUCTS DIRECTLY in SHOP");
//		object is only created if maxStock is ok
//		Winter T-Shirt will not be created
		addProductToShop(shop01, "Winter T-Shirt", "Light T-shirt for low temperatures", 25.90f, Product.ProductCategory.TSHIRTS, 17);

		addProductToShop(shop01, "Winter Trousers", "Heavy trousers for low temperatures", 45.90f, Product.ProductCategory.TROUSERS, 5);
		addProductToShop(shop01, "Winter Shirt", "Heavy shirt for low temperatures", 69.90f, Product.ProductCategory.SHIRTS, 8);
		addProductToShop(shop01, "Winter Jacket", "Light jacket for low temperatures", 89.90f, Product.ProductCategory.JACKETS, 11);
		addProductToShop(shop01, "Golden Belt", "Golden Belt to hold up your trousers", 112.90f, Product.ProductCategory.ACCESSORIES, 15);
		addProductToShop(shop01, "Leather Trousers", "Heavy weight leather trousers", 399.90f, Product.ProductCategory.TROUSERS, 7);
		addProductToShop(shop01, "Summer Shirt", "Light shirt for low temperatures", 22.90f, Product.ProductCategory.SHIRTS, 4);
		addProductToShop(shop01, "Summer Jacket", "Light jacket for low temperatures", 59.90f, Product.ProductCategory.JACKETS, 12);
		addProductToShop(shop01, "Silver Belt", "Silver Belt to hold up your trousers", 77.90f, Product.ProductCategory.ACCESSORIES, 10);
		addProductToShop(shop01, "Green Trousers", "Green trousers", 12.90f, Product.ProductCategory.TROUSERS, 4);
		addProductToShop(shop01, "Green Shirt", "Green Shirt", 14.90f, Product.ProductCategory.SHIRTS, 4);
		addProductToShop(shop01, "Green Jacket", "Green Jacket", 27.90f, Product.ProductCategory.JACKETS, 5);
		addProductToShop(shop01, "Green Belt", "Green Belt", 24.90f, Product.ProductCategory.ACCESSORIES, 9);
		addProductToShop(shop01, "Yellow Trousers", "Yellow trousers", 17.90f, Product.ProductCategory.TROUSERS, 8);
		addProductToShop(shop01, "Yellow Shirt", "Yellow Shirt", 24.90f, Product.ProductCategory.SHIRTS, 12);
		addProductToShop(shop01, "Yellow Jacket", "Yellow Jacket", 19.90f, Product.ProductCategory.JACKETS, 7);
		addProductToShop(shop01, "Yellow Belt", "Yellow Belt", 29.90f, Product.ProductCategory.ACCESSORIES, 5);
		addProductToShop(shop01, "Red Trousers", "Red trousers", 15.90f, Product.ProductCategory.TROUSERS, 15);
		addProductToShop(shop01, "Red Shirt", "Red Shirt", 25.90f, Product.ProductCategory.SHIRTS, 11);
		addProductToShop(shop01, "Red Jacket", "Red Jacket", 34.90f, Product.ProductCategory.JACKETS, 8);
		addProductToShop(shop01, "Red Belt", "Red Belt", 40.90f, Product.ProductCategory.ACCESSORIES, 4);


//		view shop01
		System.out.printf("%n%s%n", "Products in shop01");
		for (int i : shop01.getProducts().keySet()) {
			System.out.println(shop01.getProducts().get(i));
		}
		System.out.printf("%n%n%n");

//		create users and add them to HashMap
		addUser(users, "John", "Doe", "john.doe@mail.zz", "Long road 15", "9587", "User town", "+55 314 78 97");
		addUser(users, "Jane", "Doe", "jane.doe@mail.zz", "Long road 15", "9587", "User town", "+55 314 78 98");
		addUser(users, "Mister", "Smith", "mr.smith@mail.zz", "Short road 19", "7854", "Springfield", "+55 685 45 75");
		addUser(users, "Marry", "Christmas", "marry.christmas@mail.zz", "Icy road 11", "0011", "North pole", "+55 999 111 555");

//		view users
		System.out.printf("%n%s%n", "all users in users");
		for (int i : users.keySet()) {
			System.out.println(users.get(i));
		}
		System.out.printf("%n%n%n");

//		test purchase process
//		buy product(s)
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7005).getProductId(), 3);
//		print history of user
		users.get(1001).printPurchaseHistory();
		System.out.println();

//		will not be executed
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7006).getProductId(), 12);
//		print history of user
		users.get(1002).printPurchaseHistory();
		System.out.println();

		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7007).getProductId(), 12);
//		print history of user
		users.get(1001).printPurchaseHistory();
		System.out.println();

		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7019).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7008).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7016).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7005).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7014).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7013).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7012).getProductId(), 1);
		users.get(1001).buyProduct(shop01, shop01.getProducts().get(7017).getProductId(), 1);
//		print history of user
		users.get(1001).printPurchaseHistory();
		System.out.println();

		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7018).getProductId(), 2);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7014).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7011).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7020).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7016).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7015).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7020).getProductId(), 1);
		users.get(1002).buyProduct(shop01, shop01.getProducts().get(7012).getProductId(), 1);
//		print history of user
		users.get(1001).printPurchaseHistory();
		System.out.println();

		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7008).getProductId(), 6);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7011).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7013).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7010).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7016).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7009).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7019).getProductId(), 1);
		users.get(1004).buyProduct(shop01, shop01.getProducts().get(7013).getProductId(), 1);

		users.get(1004).printPurchaseHistory();
		System.out.println();


//		@stuff refill product 7001 working
		try {
			shop01.getProducts().get(7005).increaseStock(11);
			System.out.println(shop01.getProducts().get(7005).getProductName() + " stock increased to " + shop01.getProducts().get(7005).getStock());

		} catch (StockLimitReached e) {
			System.out.println(e.getMessage());
			System.out.println("You can only add " + (Product.getMaxStock() - shop01.getProducts().get(7005).getStock()) + " items of " + shop01.getProducts().get(7005).getProductName() + ", please try again");
		}

//		@stuff refill product 7001 not working
		try {
			shop01.getProducts().get(7008).increaseStock(77);
			System.out.println(shop01.getProducts().get(7008).getProductName() + " stock increased to " + shop01.getProducts().get(7008).getStock());

		} catch (StockLimitReached e) {
			System.out.println("You can only add " + (Product.getMaxStock() - shop01.getProducts().get(7008).getStock()) + " items of " + shop01.getProducts().get(7008).getProductName() + ", please try again");
		}


//		view shop01
		System.out.printf("%n%s%n", "Products in shop01");
		for (int i : shop01.getProducts().keySet()) {
			System.out.println(shop01.getProducts().get(i));
		}
		System.out.printf("%n%n%n");

//		with menu
		Menu.buildMenu();


}    //MAIN

	private static void addProductToShop(Shop shop, String productName, String productDescription, float productPrice, Product.ProductCategory category, int stock) {
		try {
			shop.addProduct(new Product(productName, productDescription, productPrice, category, stock));
			System.out.println(stock + " item(s) of " + productName + " added to " + shop.getName());
		} catch (StockLimitReached e) {
			System.out.printf("%n%s%n%s %s%n%n", e.getMessage(), productName, "not added, you can only add " + Product.getMaxStock() + " items, please try again!");
		}
	}

	private static void addUser(HashMap<Integer, User> users, String firstName, String lastName, String eMail, String address, String zip, String town, String phone) {
		User tempUser = new User(firstName, lastName, eMail, address, zip, town, phone);
		users.put(tempUser.getUserId(), tempUser);
	}


}

