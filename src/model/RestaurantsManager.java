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
	public final static String SAVE_PATH_FILE_PRODUCTS_OF_RESTAURANTS = "data/restaurantsProducts.xd";
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
		boolean done = false;

		if(restaurants.isEmpty()) {
			restaurants.add(r);
			saveData("rest");
			info += "Added!";
			done = true;
		}
		else if(!done) {
			boolean unique = uniqueRestaurantNit(r.getNit());
			if(unique) {
				restaurants.add(r);
				saveData("rest");
				info += "Added!";
			}
			else
				info += "** Restaurant alredy exists **";
		}
		return info;
	}

	public boolean uniqueRestaurantNit(String nit){
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

	public String addProductToRestaurant(Product p) throws IOException {
		String info = "";
		if (!restaurants.isEmpty()) {
			for (int i = 0; i < restaurants.size(); i++) {
				if(p.getRestaurantNit().equals(restaurants.get(i).getNit())) {
					restaurants.get(i).products.add(p);
					saveData("restProd");
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

	public String addClient(String name, String lastName, String idNum, int choice, String telephone, String adress) throws IOException {
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
		boolean unique = uniqueClientId(c.getIdNum());
		if(clients.isEmpty()) {
			clients.add(c);
			saveData("client");
			info += "Added!";
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
			saveData("client");
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

	public String addProduct(String name, String code, String description, double cost, String restaurantNit) throws IOException {
		String info = "";
		Product p = new Product(name, code, description, cost, restaurantNit);
		boolean unique = uniqueProductCode(p.getCode());
		if(unique) {
			products.add(p);
			info += "Added!\n";
			info += addProductToRestaurant(p)+"\n";
			saveData("products");
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

	public String addOrder(String code, String clientIdNum, String restaurantNit) throws IOException {
		String info = "";
		Order o = new Order(code, clientIdNum, restaurantNit);
		boolean unique = uniqueOrderCode(o.getCode());
		if(unique) {
			orders.add(o);
			info += "Added!";
			saveData("order");
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
	
	public int positionWithOrderCode(String code){
		int position = 0;
		for(int i=0; i<orders.size(); i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				position = i;
			}
		}
		return position;
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
	
	public int searchOrder(String clientIdNum, String restaurantNit) {
		int position = 0;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getClientIdNum().equals(clientIdNum)&&orders.get(i).getRestaurantNit().equals(restaurantNit))
				position = i;
		}
		return position;
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
				info += updateOrderRestaurantNit(nit);
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
				info += updateOrderClientIdNum(idNum);
			}
		}
		return info;
	}

	public String updateClientIdType(String idNum, String idType) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setIdType(idType);
				info += "Client's ID type updated";
			}
		}
		return info;
	}

	public String updateClientTelephone(String idNum, String telephone) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setTelephone(telephone);
				info += "Client's telephone updated";
			}
		}
		return info;
	}

	public String updateClientAddress(String idNum, String address) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setAddress(address);
				info += "Client's ID updated";
			}
		}
		return info;
	}

	//	*********************************************************************

	//	Update methods of order

	public String updateOrderClientIdNum(String code, String clientIdNum) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCode().equals(code)) {
				orders.get(i).setClientIdNum(clientIdNum);
				info += "Order's client ID num updated";
			}
		}
		return info;
	}

	public String updateOrderClientIdNum(String clientIdNum) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getClientIdNum().equals(clientIdNum)) {
				orders.get(i).setClientIdNum(clientIdNum);
				info += "Order's client ID num updated";
			}
		}
		return info;
	}

	public String updateOrderRestaurantNit(String code, String restaurantNit) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getCode().equals(code)) {
				orders.get(i).setRestaurantNit(restaurantNit);
				info += "Order's restaurant nit updated";
			}
		}
		return info;
	}

	public String updateOrderRestaurantNit(String restaurantNit) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getRestaurantNit().equals(restaurantNit)) {
				orders.get(i).setRestaurantNit(restaurantNit);
				info += "Order's restaurant nit updated";
			}
		}
		return info;
	}

	//	*********************************************************************

	//	Methods of serialization

	public void saveData(String type) throws IOException{
		if(type.equalsIgnoreCase("rest")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_RESTAURANTS));
			oos.writeObject(restaurants);
			oos.close();
		} 
		if(type.equalsIgnoreCase("restProd")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_PRODUCTS_OF_RESTAURANTS));
			for (int i = 0; i < restaurants.size(); i++) {
				oos.writeObject(restaurants.get(i).getProducts());
			}
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
	public String loadData() throws IOException, ClassNotFoundException{
		File r = new File(SAVE_PATH_FILE_RESTAURANTS);
		File c = new File(SAVE_PATH_FILE_CLIENTS);
		File p = new File(SAVE_PATH_FILE_PRODUCTS);
		File o = new File(SAVE_PATH_FILE_ORDERS);
		File rp = new File(SAVE_PATH_FILE_PRODUCTS_OF_RESTAURANTS);
		String info = "";
		boolean loaded = false;
		if(r.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(r));
			restaurants = (ArrayList<Restaurant>)ois.readObject();
			info += "Restaurants loaded succesfully\n";
			ois.close();	
			loaded = true;
		}
		if(c.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c));
			clients = (ArrayList<Client>)ois.readObject();
			info += "Clients loaded succesfully\n";
			ois.close();
			loaded = true;
		}
		if(p.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p));
			products = (ArrayList<Product>)ois.readObject();
			info += "Products loaded succesfully\n";
			ois.close();
			loaded = true;
		} 
		if(o.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(o));
			orders = (ArrayList<Order>)ois.readObject();
			info += "Orders loaded succesfully\n";
			ois.close();
			loaded = true;
		}
		if(rp.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rp));
			for (int i = 0; i < restaurants.size(); i++) {
				@SuppressWarnings("unused")
				ArrayList<Product> products = restaurants.get(i).getProducts(); 
				products = (ArrayList<Product>)ois.readObject();
			}
			info += "Restaurant products loaded succesfully\n";
			ois.close();
			loaded = true;
		}
		if(loaded == false) {
			info = "Nothing to load";
		}
		return info;
	}

}