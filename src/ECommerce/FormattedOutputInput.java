package ECommerce;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class FormattedOutputInput {
	private static String pattern01 = "%-25s %9s %4s %-40s %-12s %-6s %6s %-4s %8s%n";
	private static String pattern02 = "%-25s %9.2f %4s %-40s %-12s %-6d %6d %4s %8.2f%n";
	private static String pattern03 = "%-25s %9s %4s %-40s %-12s %-6s %6s %-4s %8.2f%n";

	public static String getPattern01() {
		return pattern01;
	}

	public static String getPattern03() {
		return pattern03;
	}

	//	methods for formatted output

	public static void displayAllUsers(HashMap<Integer, User> users) {
		float sum = 0;
		String userPattern01 = "%-20s %-27s %-15s %-8s %-13s %-18s %8s%n";
		String userPattern02 = "%-20s %-27s %-15s %-8s %-13s %-18s %8.2f%n";
		System.out.printf(userPattern01,
				"NAME", "E-MAIL", "ADDRESS", "ZIP", "TOWN", "PHONE", "REVENUE"
		);
		for (int i : users.keySet()) {
			float userSum = 0;
			for (PurchaseHistory p : users.get(i).getPurchaseHistory()) {
				userSum += p.getPrice() * p.getAmount();
			}
			System.out.printf(userPattern02,
					users.get(i).getFirstName() + " " + users.get(i).getLastName(),
					users.get(i).geteMail(),
					users.get(i).getAddress(),
					users.get(i).getZip(),
					users.get(i).getTown(),
					users.get(i).getPhone(),
					userSum
			);
			sum += userSum;
		}

		System.out.printf(userPattern01,
				" ", " ", " ", " ", " ", " ", "========");
		System.out.printf(userPattern02,
				" ", " ", " ", " ", " ", " ", sum);
		System.out.println();
	}

	public static TreeMap<Integer, Product.ProductCategory> displayAllCategories() {
		int i = 0;
		String pattern = "%-4s%s%n";
		TreeMap<Integer, Product.ProductCategory> categories = new TreeMap<>();
		System.out.println("Following Categories are available: ");
		for (Product.ProductCategory value : Product.ProductCategory.values()) {
			System.out.printf(pattern, ++i + ")", value);
			categories.put(i, value);
		}
		System.out.printf(pattern, 0 + ")", "MAIN MENU");
		System.out.print("Enter your choice: ");
		return categories;
	}

	public static float displayAllProductSpecs(Product product, boolean headerPrinted) {
		float sum = 0;
		if (!headerPrinted) {
			System.out.printf(pattern01,
					"NAME", "PRICE", " ", "DESCRIPTION", "CATEGORY", "ID", "STOCK", " ", "VALUE");
		}
		System.out.printf(pattern02,
				product.getProductName(),
				product.getProductPrice(),
				" ",
				product.getProductDescription(),
				product.getProductCategory(),
				product.getProductId(),
				product.getStock(),
				" ",
				product.getProductPrice() * product.getStock()
		);
		sum += product.getProductPrice() * product.getStock();
		return sum;
	}


	public static void displaySpecificUserdata(User user) {
		float sum = 0;
		String pattern01 = "%-8s %-15s %-15s %-27s %-18s %-10s %-15s %-12s%n";
		String pattern02 = "%-20s %7s %4s %-18s %10s %8s %10s %n";
		String pattern03 = "%-20s %7s %4s %-18s %10.2f %8d %10.2f %n";
		String pattern04 = "%-20s %7s %4s %-18s %10s %8s %10.2f %n";
		System.out.printf(pattern01, "ID", "First Name", "Last Name", "E-Mail", "ADDRESS", "ZIP", "TOWN", "PHONE");
		System.out.printf(pattern01, user.getUserId(), user.getFirstName(), user.getLastName(), user.geteMail(), user.getAddress(), user.getZip(), user.getTown(), user.getPhone());
		System.out.printf("%n%n%n");
		System.out.printf(pattern02, "DATE", "PRODID", " ", "PRODUCT", "PRICE", "AMOUNT", "TOTAL");
		for (PurchaseHistory h : user.getPurchaseHistory()) {
			System.out.printf(pattern03, h.getPurchaseDate(), h.getProductId(), " ", Main.getShop01().getProducts().get(h.getProductId()).getProductName(), h.getPrice(), h.getAmount(), h.getAmount() * h.getPrice());
			sum += h.getAmount() * h.getPrice();
		}
		System.out.printf(pattern02, " ", " ", " ", " ", " ", " ", "========");
		System.out.printf(pattern04, " ", " ", " ", " ", " ", " ", sum);
	}


	public static void writeUserDateToFile(User user) {
		float sum = 0;
		String pattern01 = "%-8s %-15s %-15s %-27s %-18s %-10s %-15s %-12s%n";
		String pattern02 = "%-20s %7s %4s %-18s %10s %8s %10s %n";
		String pattern03 = "%-20s %7s %4s %-18s %10.2f %8d %10.2f %n";
		String pattern04 = "%-20s %7s %4s %-18s %10s %8s %10.2f %n";
		try {
			PrintWriter pw = new PrintWriter("./data/" + user.getUserId() + "_" + user.getFirstName() + "_" + user.getLastName() + ".txt");
			pw.printf(pattern01, "ID", "First Name", "Last Name", "E-Mail", "ADDRESS", "ZIP", "TOWN", "PHONE");
			pw.printf(pattern01, user.getUserId(), user.getFirstName(), user.getLastName(), user.geteMail(), user.getAddress(), user.getZip(), user.getTown(), user.getPhone());
			pw.printf("%n%n%n");
			pw.printf(pattern02, "DATE", "PRODID", " ", "PRODUCT", "PRICE", "AMOUNT", "TOTAL");
			for (PurchaseHistory h : user.getPurchaseHistory()) {
				pw.printf(pattern03, h.getPurchaseDate(), h.getProductId(), " ", Main.getShop01().getProducts().get(h.getProductId()).getProductName(), h.getPrice(), h.getAmount(), h.getAmount() * h.getPrice());
				sum += h.getAmount() * h.getPrice();
			}
			pw.printf(pattern02, " ", " ", " ", " ", " ", " ", "========");
			pw.printf(pattern04, " ", " ", " ", " ", " ", " ", sum);
			pw.close();
			System.out.println("data\\" + user.getUserId() + "_" + user.getFirstName() + "_" + user.getLastName() + ".txt\" + File written successfully!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void printAllProductsToCSVFile() {
		TreeMap<Integer, Product> products = new TreeMap<>(Main.getShop01().getProducts());
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./data/PRODUCTS.csv"));
			for (int i : products.keySet()) {
				bw.write(products.get(i).getProductName() + ";" +
						products.get(i).getProductPrice() + ";" +
						products.get(i).getProductDescription() + ";" +
						products.get(i).getProductCategory() + ";" +
						products.get(i).getProductId() + ";" +
						products.get(i).getStock() + ";" +
						products.get(i).getProductPrice() * products.get(i).getStock() + "\n"
				);
			}

			System.out.println();
			System.out.println("data\\PRODUCTS.csv\" + File written successfully!");
			System.out.println();
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void restoreAllProductsFromCSVFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/PRODUCTS_RESTORE-2.csv"));
			Main.getShop01().getProductsAsAdmin().clear();
			String line;
			String[] lineArray;
			Product.ProductCategory category = Product.ProductCategory.TSHIRTS;

			while ((line = br.readLine()) != null) {
				lineArray = line.split(";");
				if (lineArray[3].equals("TSHIRTS")) {
					category = Product.ProductCategory.TSHIRTS;
				}
				if (lineArray[3].equals("TROUSERS")) {
					category = Product.ProductCategory.TROUSERS;
				}
				if (lineArray[3].equals("SHIRTS")) {
					category = Product.ProductCategory.SHIRTS;
				}
				if (lineArray[3].equals("JACKETS")) {
					category = Product.ProductCategory.JACKETS;
				}
				if (lineArray[3].equals("ACCESSORIES")) {
					category = Product.ProductCategory.ACCESSORIES;
				}
				try {
					if (Integer.valueOf(lineArray[5]) > Product.getMaxStock()) {
						System.out.println(lineArray[0] + "  " + Integer.valueOf(lineArray[5]) + " exceeds " + Product.getMaxStock() + " so only " + Product.getMaxStock() + " were added!");
						Main.getShop01().getProductsAsAdmin().put(Integer.valueOf(lineArray[4]), new Product(Integer.valueOf(lineArray[4]), lineArray[0], lineArray[2], Float.valueOf(lineArray[1]), category, Product.getMaxStock()));
					} else {
						Main.getShop01().getProductsAsAdmin().put(Integer.valueOf(lineArray[4]), new Product(Integer.valueOf(lineArray[4]), lineArray[0], lineArray[2], Float.valueOf(lineArray[1]), category, Integer.valueOf(lineArray[5])));
					}
				} catch (StockLimitReached f) {
					//do nothing, only max capacity is added!
				}
			}
			System.out.println();
			System.out.println("All Products successfully restored from: data\\PRODUCTS_RESTORE-2.csv\"!");
			System.out.println();
			br.close();
		} catch (IOException e) {
			System.out.println();
			System.out.println("Error while reading file!");
			System.out.println();
		}
	}

	public static void addProductsFromCSVFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/PRODUCTS_ADD-3.csv"));
			String line;
			String[] lineArray;
			Product.ProductCategory category = Product.ProductCategory.TSHIRTS;

			while ((line = br.readLine()) != null) {
				lineArray = line.split(";");
				if (lineArray[3].equals("TSHIRTS")) {
					category = Product.ProductCategory.TSHIRTS;
				}
				if (lineArray[3].equals("TROUSERS")) {
					category = Product.ProductCategory.TROUSERS;
				}
				if (lineArray[3].equals("SHIRTS")) {
					category = Product.ProductCategory.SHIRTS;
				}
				if (lineArray[3].equals("JACKETS")) {
					category = Product.ProductCategory.JACKETS;
				}
				if (lineArray[3].equals("ACCESSORIES")) {
					category = Product.ProductCategory.ACCESSORIES;
				}
				try {
					if (Integer.valueOf(lineArray[5]) > Product.getMaxStock()) {
						System.out.println(lineArray[0] + "  " + Integer.valueOf(lineArray[5]) + " exceeds " + Product.getMaxStock() + " so only " + Product.getMaxStock() + " were added!");
						Main.getShop01().addProduct(new Product(lineArray[0], lineArray[2], Float.valueOf(lineArray[1]), category, Product.getMaxStock()));
					} else {
						Main.getShop01().addProduct(new Product(lineArray[0], lineArray[2], Float.valueOf(lineArray[1]), category, Integer.valueOf(lineArray[5])));
					}
				} catch (StockLimitReached f) {
					//do nothing, only max capacity is added!
				}
			}
			System.out.println();
			System.out.println("Products added successfully restored from: data\\PRODUCTS_ADD-3.csv\"!");
			System.out.println();
			br.close();
		} catch (IOException e) {
			System.out.println();
			System.out.println("Error while reading file!");
			System.out.println();
		}
	}

}





