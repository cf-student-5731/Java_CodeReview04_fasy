package ECommerce;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

	private static void printMenuHeader() {
		String singleLine = "-".repeat(40);
		String doubleLine = "=".repeat(40);
		String pattern01 = "%1s  %-13s %-18s  %5s%n";

		System.out.printf("%1s%s%1s%n", "+", doubleLine, "+");
		System.out.printf(pattern01, "|", " ", "Welcome", "|");
		System.out.printf(pattern01, "|", " ", "to the", "|");
		System.out.printf(pattern01, "|", " ", "Shop!", "|");
		System.out.printf("%1s%s%1s%n", "+", singleLine, "+");
		System.out.println();
	}

	private static void printMenuBody() {
		String pattern02 = "%-4s%s%n";
		System.out.printf(pattern02, "1)", "Display all products.");
		System.out.printf(pattern02, "2)", "Display all products of specific category.");
		System.out.printf(pattern02, "3)", "Display all products where stock < specific value.");
		System.out.printf(pattern02, "4)", "Display all products out of stock.");
		System.out.printf(pattern02, "5)", "Display all users.");
		System.out.printf(pattern02, "6)", "Display specific user history.");
		System.out.printf(pattern02, "7)", "Print history of specific user to file.");
		System.out.printf(pattern02, "8)", "Print all products to PRODUCTS.CSV file.");
		System.out.printf(pattern02, "9)", "Restore all products from PRODUCTS_RESTORE-2.CSV file.");
		System.out.printf(pattern02, "10)", "Add products from PRODUCTS_ADD-3.CSV file.");
		System.out.printf(pattern02, "0)", "Exit.");
		System.out.println();
		System.out.print("Enter your choice: ");
	}

	private static boolean exit01 = false;
	private static boolean exit02 = false;

	private static void handleInput() {
		int input01;
		Scanner in = new Scanner(System.in);

		while (!exit01) {
			try {
				input01 = in.nextInt();
			} catch (Exception e) {
				input01 = -1;
			}

			switch (input01) {
				case 1 -> {
					System.out.println();
					boolean headerPrinted = false;
					float sum = 0;
					for (int i : Main.getShop01().getProducts().keySet()) {
						sum += FormattedOutputInput.displayAllProductSpecs(Main.getShop01().getProducts().get(i), headerPrinted);
						headerPrinted = true;
					}
					printSum(sum);
					printMenuBody();
				}
				case 2 -> {
					System.out.println();
					handleCategoryInput();
					exit02 = false;
				}
				case 3 -> {
					System.out.println();
					handleStockInput(false);
					exit02 = false;
				}
				case 4 -> {
					System.out.println();
					handleStockInput(true);
					exit02 = false;
				}
				case 5 -> {
					System.out.println();
					FormattedOutputInput.displayAllUsers(Main.getUsers());
					printMenuBody();
				}
				case 6 -> {
					handleDisplaySpecificUserdata();
					printMenuBody();
					exit02 = false;
				}

				case 7 -> {
					handleUserToFile();
					printMenuBody();
					exit02 = false;
				}

				case 8 -> {
					FormattedOutputInput.printAllProductsToCSVFile();
					printMenuBody();
					exit02 = false;
				}

				case 9 -> {
					FormattedOutputInput.restoreAllProductsFromCSVFile();
					printMenuBody();
					exit02 = false;
				}

				case 10 -> {
					FormattedOutputInput.addProductsFromCSVFile();
					printMenuBody();
					exit02 = false;
				}

				case 0 -> {
					System.out.println("Bye!");
					exit01 = true;
				}
				default -> {
					System.out.println("Invalid Input, please try again");
					printMenuBody();
					handleInput();
				}
			}
		}
	}

	private static void handleCategoryInput() {
		int input;
		Scanner in = new Scanner(System.in);
		TreeMap<Integer, Product.ProductCategory> categories = new TreeMap<>(FormattedOutputInput.displayAllCategories());

		while (!exit02) {
			try {
				input = in.nextInt();
			} catch (Exception e) {
				input = -1;
			}

			if (input < 0 || input > categories.size()) {
				input = -1;
			}

			switch (input) {
				case 0 -> {
					printMenuHeader();
					printMenuBody();
					exit02 = true;
				}
				case -1 -> {
					System.out.println("Invalid Input, please try again");
					handleCategoryInput();
				}
				default -> {
					boolean found = false;
					boolean headerPrinted = false;
					float sum = 0;
					for (int i : Main.getShop01().getProducts().keySet()) {
						if (Main.getShop01().getProducts().get(i).getProductCategory().equals(categories.get(input))) {
							found = true;
							sum += FormattedOutputInput.displayAllProductSpecs(Main.getShop01().getProducts().get(i), headerPrinted);
							headerPrinted = true;
						}
					}
					if (sum > 0) {
						printSum(sum);
					}
					if (!found) {
						System.out.println("No Products of " + categories.get(input) + " in the shop!");
					}
					System.out.println();
					handleCategoryInput();
				}
			}
		}
	}

	private static void handleStockInput(boolean askedOutOfStock) {
		int input;
		Scanner in = new Scanner(System.in);
		if (!askedOutOfStock) {
			System.out.print("Please enter the desired stock value or 0 for Main Menu: ");
		}
		while (!exit02) {
			if (!askedOutOfStock) {
				try {
					input = in.nextInt();
				} catch (Exception e) {
					input = -1;
				}
			} else {
				input = 1;
				exit02 = true;
			}

			if (input < 0) {
				input = -1;
			}

			switch (input) {
				case 0 -> {
					printMenuHeader();
					printMenuBody();
					exit02 = true;
				}
				case -1 -> {
					System.out.println("Invalid Input, please try again");
					handleStockInput(false);
				}
				default -> {
					boolean found = false;
					boolean headerPrinted = false;
					float sum = 0;
					for (int i : Main.getShop01().getProducts().keySet()) {
						if (Main.getShop01().getProducts().get(i).getStock() < input) {
							found = true;
							sum += FormattedOutputInput.displayAllProductSpecs(Main.getShop01().getProducts().get(i), headerPrinted);
							headerPrinted = true;
						}
					}
					printSum(sum);
					if (!found) {
						System.out.println("No Products of with stock < " + input + " in the shop!");
					}
					System.out.println();
					if (!askedOutOfStock) {
						handleStockInput(false);
					} else {
						printMenuHeader();
						printMenuBody();
					}
				}
			}
		}
	}

	private static void handleUserToFile() {
		int input;
		int j = 0;
		TreeMap<Integer, User> users = new TreeMap<>(Main.getUsers());
		System.out.println();
		String pattern = "%-4s%s %s%n";
		for (int i : users.keySet()) {
			System.out.printf(pattern, ++j + ")", users.get(i).getFirstName(), users.get(i).getLastName());
		}
		System.out.printf(pattern, 0 + ")", "MAIN MENU", " ");
		System.out.println();
		System.out.print("Enter your choice: ");

		Scanner in = new Scanner(System.in);
		while (!exit02) {
			try {
				input = in.nextInt();
			} catch (Exception e) {
				input = -1;
			}

			if (input < 0 || input > users.size()) {
				input = -1;
			}

			switch (input) {
				case 0 -> {
					System.out.println();
					exit02 = true;
				}
				case -1 -> {
					System.out.println("Invalid Input, please try again");
					handleUserToFile();
				}
				default -> {
					int k = 0;
					for (int i : users.keySet()) {
						if (++k == input) {
							FormattedOutputInput.writeUserDateToFile(users.get(i));
						}
					}
					handleUserToFile();
				}
			}
		}
	}

	private static void handleDisplaySpecificUserdata() {
		int input;
		int j = 0;
		TreeMap<Integer, User> users = new TreeMap<>(Main.getUsers());
		System.out.println();
		String pattern = "%-4s%s %s%n";
		for (int i : users.keySet()) {
			System.out.printf(pattern, ++j + ")", users.get(i).getFirstName(), users.get(i).getLastName());
		}
		System.out.printf(pattern, 0 + ")", "MAIN MENU", " ");
		System.out.println();
		System.out.print("Enter your choice: ");

		Scanner in = new Scanner(System.in);
		while (!exit02) {
			try {
				input = in.nextInt();
			} catch (Exception e) {
				input = -1;
			}

			if (input < 0 || input > users.size()) {
				input = -1;
			}

			switch (input) {
				case 0 -> {
					System.out.println();
					exit02 = true;
				}
				case -1 -> {
					System.out.println("Invalid Input, please try again");
					handleDisplaySpecificUserdata();
				}
				default -> {
					int k = 0;
					for (int i : users.keySet()) {
						if (++k == input) {
							FormattedOutputInput.displaySpecificUserdata(users.get(i));
						}
					}
					handleDisplaySpecificUserdata();
				}
			}
		}
	}

	public static void buildMenu() {
		printMenuHeader();
		printMenuBody();
		handleInput();
	}

	private static void printSum(float sum) {
		System.out.printf(FormattedOutputInput.getPattern01(),
				" ", " ", " ", " ", " ", " ", " ", " ", "========");
		System.out.printf(FormattedOutputInput.getPattern03(),
				" ", " ", " ", " ", " ", " ", " ", " ", sum);
	}
}



