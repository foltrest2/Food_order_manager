package model;
import java.util.ArrayList;
import java.lang.Comparable;

//
//	Recordar hacer el método para separar lo que hay en el arraylist de products por 
//	restaurantes.
//
//
//

public class RestaurantsManager implements Comparable<Client>{

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

	public String addRestaurant(String name, String nit, String manager) {
		String info = "";
		Restaurant r = new Restaurant(name, nit, manager);
		boolean unique = uniqueProductCode(r.getNit());
		if(unique) {
			restaurants.add(r);
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
			info += "** Product alredy exists **";

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
			if (clients.get(i).getName().equals(idNum)) {
				clients.get(i).setName(name);
				info += "Client's name updated";
			}
		}
		return info;
	}

}