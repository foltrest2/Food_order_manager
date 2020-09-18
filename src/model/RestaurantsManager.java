package model;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Comparable;

public class RestaurantsManager implements Comparable<Client>, Serializable{

	private static final long serialVersionUID = 1;
	public final static String SAVE_PATH_FILE_RESTAURANTS = "data/restaurants.xd";
	public final static String SAVE_PATH_FILE_CLIENTS = "data/clients.xd";
	public final static String SAVE_PATH_FILE_PRODUCTS = "data/products.xd";
	public final static String SAVE_PATH_FILE_ORDERS = "data/orders.xd";
	public ArrayList<Restaurant> restaurants;
	public ArrayList<Product> products;
	public ArrayList<Client> clients;
	public ArrayList<Order> orders;

	public RestaurantsManager() {
		restaurants = new ArrayList<>();
		products = new ArrayList<>();
		clients = new ArrayList<>();
		orders = new ArrayList<>();
	}


	//**************************************************************//

	//Methods of restaurants

	public String addRestaurant(String name, String nit, String manager) throws IOException {
		String info = "";
		Restaurant r = new Restaurant(name, nit, manager);
		boolean unique = uniqueProductCode(r.getNit());
		if(unique) {
			restaurants.add(r);
			saveData("rest");
			info += "Added!";
		}
		else
			info += "** Restaurant alredy exists **";

		return info;
	}

	public boolean uniqueRestaurantCode(String nit){
		boolean unique = true;
		for(int i=0; i<restaurants.size() && unique; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				unique = false;
			}
		}
		return unique;
	}

	public String showRestaurants() {
		String info = "";
		if (restaurants.isEmpty()) {
			info = "There no restaurants in list\n";
		}
		else {
			for (int i = 0; i < restaurants.size(); i++) {
				info += restaurants.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}

	public String addProductToRestaurant(Product p) {
		String info = "";
		if (!restaurants.isEmpty()) {
			for (int i = 0; i < restaurants.size(); i++) {
				if(p.getRestaurantNit().equals(restaurants.get(i).getNit())) {
					restaurants.get(i).products.add(p);
					info += "Product added to the restaurant list";
				}
			}
		}
		else
			info += "No restaurants registered yet";
		return info;
	}
	//**************************************************************//

	//Methods of clients

	public String addClient(String name, String lastName, String idNum, int choice, String telephone, String adress) {
		String info = "";
		String idType = "";
		switch (choice) {
		case 1:
			idType = "TI";
			break;
		case 2:
			idType = "CC";
			break;

		default:
			break;
		}
		Client c = new Client(name, lastName, idNum, idType, telephone, adress);
		boolean unique = uniqueProductCode(c.getIdNum());
		if(clients.isEmpty()) {
			clients.add(c);
		}else if(!unique) {
			info += "The client already exists";
		}
		else {
			int i = 0;
			while(i < clients.size() && c.getName().compareTo(clients.get(i).getName())!=-1) {
				i++;
			}

			for (int j = 0; j < clients.size()-1; j++) {
				clients.get(j).getName().compareTo(clients.get(j+1).getName());
			}
			clients.add(compareTo(c),c);
		}
		return info;
	}

	@Override
	public int compareTo(Client c) {
		int r=0 , p=0;
		boolean done= false;
		for (int i = 0; i < clients.size()&& !done; i++) {
			r = c.getName().compareTo(clients.get(i).getName());
			if (r>0) {
				p=i;
				done = true;
			}
			else if (done && i == clients.size()) {
				i++;
				p=i;
			}
		}	
		return p;
	}

	public boolean uniqueClientId(String idNum){
		boolean unique = true;
		for(int i=0; i<clients.size() && unique; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				unique = false;
			}
		}
		return unique;
	}

	public String showClients() {
		String info = "";
		if (clients.isEmpty()) {
			info = "There no restaurants in list\n";
		}
		else {
			for (int i = 0; i < clients.size(); i++) {
				info += clients.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}

	//**************************************************************//

	//Methods of products

	public String addProduct(String name, String code, String description, double cost, String restaurantNit) {
		String info = "";
		Product p = new Product(name, code, description, cost, restaurantNit);
		boolean unique = uniqueProductCode(p.getCode());
		if(unique) {
			products.add(p);
			info += "Added!\n";
			info += addProductToRestaurant(p)+"\n";
		}
		else
			info += "** Product alredy exists **";

		return info;
	}

	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<products.size() && unique; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}

	public String showProducts() {
		String info = "";
		if (products.isEmpty()) {
			info = "There no products in list\n";
		}
		else {
			for (int i = 0; i < products.size(); i++) {
				info += products.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}
	//**************************************************************//

	//Methods of orders

	public String addOrder(String clientIdNum, String restaurantNit) {
		String info = "";
		Order o = new Order(clientIdNum, restaurantNit);
		boolean unique = uniqueOrderCode(o.getCode());
		if(unique) {
			orders.add(o);
			info += "Added!";
		}
		else
			info += "** Order alredy exists ***";

		return info;
	}

	public boolean uniqueOrderCode(String code){
		boolean unique = true;
		for(int i=0; i<orders.size() && unique; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}


	public String showoOrders() {
		String info = "";
		if (orders.isEmpty()) {
			info = "There no orders in list\n";
		}
		else {
			for (int i = 0; i < orders.size(); i++) {
				info += "This is the order (#)" + (i+1) + "\n";
				info += orders.get(i).getInfo()+"\n";
				info += "With these products requested\n";
				info += orders.get(i).getProducts() + "\n";
			}	
		}
		return info;
	}

	//	**************************************************************

	//	Update methods of restaurants

	public String updateRestaurantName(String nit, String name) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				restaurants.get(i).setName(name);
				info += "Name updated";
			}
		}
		return info;
	}

	public String updateRestaurantNit(String nit) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				restaurants.get(i).setNit(nit);
				info += "Nit updated";
				info += updateProductRestaurantNit(nit);
			}
		}
		return info;
	}

	public String updateRestaurantManager(String nit, String manager) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				restaurants.get(i).setManager(manager);
				info += "Manager updated\n";		
			}
		}
		return info;
	}

	//	**************************************************************

	//	Update methods of product

	public String updateProductName(String code, String name) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setName(name);
				info += "Name of product updated";
			}
		}
		return info;
	}

	public String updateProductCode(String code) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setCode(code);
				info += "Code of product updated";
			}
		}
		return info;
	}

	public String updateProductDescription(String code, String description) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setDescription(description);
				info += "Product's description updated";
			}
		}
		return info;
	}

	public String updateProductName(String code, double cost) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setCost(cost);
				info += "Product's name updated";
			}
		}
		return info;
	}

	public String updateProductQuantity(String code, int quantity) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setQuantity(quantity);
				info += "Product's name updated";
			}
		}
		return info;
	}

	public String updateProductRestaurantNit(String restaurantNit) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getRestaurantNit().equals(restaurantNit)) {
				products.get(i).setRestaurantNit(restaurantNit);
				info += "Product's restaurant nit updated";
			}
		}
		return info;
	}

	//	**************************************************************

	//	Update methods of client

	public String updateClientName(String idNum, String name) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setName(name);
				info += "Client's name updated";
			}
		}
		return info;
	}

	public String updateClientLastName(String idNum, String LastName) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setLastName(LastName);
				info += "Client's last name updated";
			}
		}
		return info;
	}

	public String updateClientIdNum(String idNum) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setIdNum(idNum);
				info += "Client's ID updated";
			}
		}
		return info;
	}


	//	*********************************************************************
	//	*********************************************************************

	//	Methods of serialization

	public void saveData(String type) throws IOException{
		if(type.equalsIgnoreCase("rest")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_RESTAURANTS));
			oos.writeObject(restaurants);
			oos.close();
		} 
		if(type.equalsIgnoreCase("client")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CLIENTS));
			oos.writeObject(clients);
			oos.close();
		}
		if(type.equalsIgnoreCase("products")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_PRODUCTS));
			oos.writeObject(products);
			oos.close();
		}
		if(type.equalsIgnoreCase("order")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_ORDERS));
			oos.writeObject(orders);
			oos.close();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean loadData(String type) throws IOException, ClassNotFoundException{
		File r = new File(SAVE_PATH_FILE_RESTAURANTS);
		File c = new File(SAVE_PATH_FILE_CLIENTS);
		File p = new File(SAVE_PATH_FILE_PRODUCTS);
		boolean loaded = false;
		if(r.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(r));
			if(type.equalsIgnoreCase("rest")) {
				restaurants = (ArrayList<Restaurant>)ois.readObject();
				loaded = true;
			}
			ois.close();	
		}
		if(c.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c));
			if(type.equalsIgnoreCase("client")) {
				clients = (ArrayList<Client>)ois.readObject();
				loaded = true;
			}
			ois.close();		
		}
		if(p.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p));
			if(type.equalsIgnoreCase("products")) {
				products = (ArrayList<Product>)ois.readObject();
				loaded = true;
			}
			ois.close();		
		} else {
			loaded = false;
		}
		return loaded;
	}

}