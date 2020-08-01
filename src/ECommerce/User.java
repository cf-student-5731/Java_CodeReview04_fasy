package ECommerce;

import java.util.ArrayList;

public class User {
	private static int userCounter = 1000;    //static to keep id unique through all instances
	private final int userId;	//id for instance
	private String firstName, lastName, eMail, address, zip, town, phone;
	private ArrayList<PurchaseHistory> purchaseHistory = new ArrayList<>();

	public User(String firstName, String lastName, String eMail, String address, String zip, String town, String phone) {
		this.userId = ++userCounter;    //automatically assign unique Id to new instance
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.address = address;
		this.zip = zip;
		this.town = town;
		this.phone = phone;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public String getAddress() {
		return address;
	}

	public String getZip() {
		return zip;
	}

	public String getTown() {
		return town;
	}

	public String getPhone() {
		return phone;
	}

	public ArrayList<PurchaseHistory> getPurchaseHistory() {
		return new ArrayList<>(purchaseHistory);
	}

	private void addPurchaseHistroyEntry(int productId, int amount, float price){
		this.purchaseHistory.add(new PurchaseHistory(this.userId, productId, amount, price));
	}

	public void buyProduct(Shop shop, int productId, int amount){
		for(int i: shop.getProducts().keySet()){
			if (shop.getProducts().get(i).getProductId() == productId){
				try {
					shop.getProducts().get(i).decreaseStock(amount);
					addPurchaseHistroyEntry(productId, amount, shop.getProducts().get(i).getProductPrice());
					System.out.println(this.firstName + " " + this.lastName + " bought " + amount + " item(s) of " + shop.getProducts().get(i));
				}catch(OutOfStockException e){
					System.out.printf("%n%s %n%s %s %s %d %s %s %s%n%n", e.getMessage(), this.firstName, this.lastName, "You can only buy", shop.getProducts().get(i).getStock(), "items of", shop.getProducts().get(i).getProductName(), "please try again!");
				}
			}
		}
	}

	public void printPurchaseHistory(){
		for(PurchaseHistory p : purchaseHistory){
			System.out.println(p);
		}
	}


	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", eMail='" + eMail + '\'' +
				", address='" + address + '\'' +
				", zip='" + zip + '\'' +
				", town='" + town + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
