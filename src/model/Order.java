package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order implements Serializable{

	private static final long serialVersionUID = 1;
	public final static String SAVE_PATH_FILE_PRODUCTS_OF_ORDERS = "data/productsOrders.xd";	
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
	public Order(String code, String clientIdNum, String restaurantNit) {
		this.code = code;
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
	
	public void saveOrderProducts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_PRODUCTS_OF_ORDERS));
		oos.writeObject(productsList);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public String loadData() throws IOException, ClassNotFoundException{
		String info = "";
		File po = new File(SAVE_PATH_FILE_PRODUCTS_OF_ORDERS);
		if(po.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(po));
			productsList = (ArrayList<Product>)ois.readObject();
			info += "Products of the order loaded succesfully\n";
			ois.close();	
		}
		return info;
	}
	
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
	
	public void setClientIdNum(String clientIdNum) {
		this.clientIdNum = clientIdNum;
	}

	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
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