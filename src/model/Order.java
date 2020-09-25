package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable{

	private static final long serialVersionUID = 1;
	private String code;
	private String date;
	private String status;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	public ArrayList<ItemOrder> productsList;
	/**
	 * 
	 * @param code 
	 * @param date
	 */
	@SuppressWarnings("static-access")
	public Order(String code, String clientIdNum, String restaurantNit) {
		this.code = code;
		this.date = "" + (LocalDate.now().getDayOfMonth()) + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear();
		this.clientIdNum = clientIdNum;
		this.restaurantNit = restaurantNit;
		productsList = new ArrayList<>();
		this.status = orderStat.REQUESTED.name();
	}
	
	public Order(String code, String date, String clientIdNum, String status, String restaurantNit) {
		this.code = code;
		this.date = date;
		this.clientIdNum = clientIdNum;
		this.status = status;
		this.restaurantNit = restaurantNit;
		productsList = new ArrayList<>();
	}

	public String addProducts(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		boolean unique = uniqueProductCode(p.getCode());
		if (unique) {
			productsList.add(new ItemOrder(p,this));
			info += "Product added!";
		}
		else
			info += "The product it's already in the order, you can't add it again";
		
		return info;
	}
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<productsList.size() && unique; i++){
			if(productsList.get(i).getProduct().getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
	
	@SuppressWarnings("static-access")
	public String updateStatus() {
		String info = "";
		boolean changed = true;
		if (status.equals(orderStat.REQUESTED.name())&&changed) {
			status = orderStat.IN_PROCESS.name();
			info += "Status is now " + orderStat.IN_PROCESS.name() + "\n";
			changed = false;
		}
		if (status.equals(orderStat.IN_PROCESS.name())&&changed) {
			status = orderStat.SENT.name();
			info += "Status is now" + orderStat.SENT.name() + "\n";
			changed = false;
		}
		if (status.equals(orderStat.SENT.name())&&changed) {
			status = orderStat.DELIVERED.name();
			info += "Status is now" + orderStat.DELIVERED.name() + "\n";
			changed = false;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<ItemOrder> getProductsList() {
		return productsList;
	}

	public void setProductsList(ArrayList<ItemOrder> productsList) {
		this.productsList = productsList;
	}

	public String getInfo() {
		String info = "";
		info += "Code: " + code + "\nDate: " + date + "\nStatus: " + status + "\nClient ID: " + clientIdNum + "\nRestaurant nit: " + restaurantNit + "\n";
		return info;
	}
	public String getProducts() {
		String info = "";
		if(!productsList.isEmpty()) {
			for (int i = 0; i < productsList.size(); i++) {
				info += productsList.get(i).getProduct().getInfoWithQuantity() + "\nThis is the product #" + (i+1) + "\n";
			}
		}
		else
			info += "No products in this order\n";
		return info;
	}
	
//	public String getProductsToExport() {
//		String info = "";
//		if(!productsList.isEmpty()) {
//			for (int i = 0; i < productsList.size(); i++) {
//				info += productsList.get(i).getInfoWithQuantityToExport();
//			}
//		}
//		else
//			info += "No products in this order";
//		return info;
//	}
}