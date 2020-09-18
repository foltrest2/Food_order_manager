package model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order implements Serializable{

	private static final long serialVersionUID = 1;
	public static final String SAVE_PATH_FILE_ORDERS = "data/orders/products.xd";
	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	Random random = new Random();
	public ArrayList<Product> productsList;
	/**
	 * 
	 * @param code
	 * @param date
	 */
	public Order(String clientIdNum, String restaurantNit) {
		this.code = new BigInteger(50,random).toString(32);
		this.date = ""+(LocalDate.now().getDayOfMonth())+LocalDate.now().getMonthValue()+LocalDate.now().getYear();
		this.clientIdNum = clientIdNum;
		this.restaurantNit = restaurantNit;
		productsList = new ArrayList<>();
	}

	public String addProducts(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		boolean unique = uniqueProductCode(p.getCode());
		if (unique) {
			productsList.add(p);
		}
		return info;
	}
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<productsList.size() && unique; i++){
			if(productsList.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}

	//	***************************************************************

	//	Serializing
	
	public void saveOrder() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_ORDERS));
		oos.writeObject(productsList);
		oos.close();
	}

	
	
	//	
	public void setIdNum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public String getDate() {
		return date;
	}
	public String getClientIdNum() {
		return clientIdNum;
	}
	public String getRestaurantNit() {
		return restaurantNit;
	}
	public String getInfo() {
		String info = "";
		info += "Code: " + code + "\nDate: " + date + "\nClient ID: " + clientIdNum + "\nRestaurant nit: " + restaurantNit + "\n";
		return info;
	}
	public String getProducts() {
		String info = "";
		if(!productsList.isEmpty()) {
			for (int i = 0; i < productsList.size(); i++) {
				info += productsList.get(i).getInfoWithQuantity() + "\n";
			}
		}
		else
			info += "No products in these order yet";
		return info;
	}
}