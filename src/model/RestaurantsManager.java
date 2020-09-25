package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exceptions.InvalidCodeException;
import exceptions.InvalidIdNumException;
import exceptions.InvalidNitException;
import exceptions.InvalidRouteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
				info += restaurants.get(i).getInfoWithProducts()+"\n";
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
					saveData("rest");
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
		case 3:
			idType = "CE";
			break;
		case 4:
			idType = "PP";
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
			r = c.getFullName().compareToIgnoreCase(clients.get(i).getFullName());
			if (r>0) {
				p=i;
				done = true;
			}
			else if (!done && i == clients.size()-1) {
				i++;
				p=i;
				done = true;
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

	@SuppressWarnings("unchecked")
	public ArrayList<Client> reOrganizeClients() {
		ArrayList<Client> organized = (ArrayList<Client>) clients.clone();
		return organized;
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

	public String addOrder(String code, String clientIdNum, String restaurantNit) {
		String info = "";
		Order o = new Order(code, clientIdNum, restaurantNit);
		boolean unique = uniqueOrderCode(o.getCode());
		if(unique) {
			orders.add(o);
			info += "Added!";
		}
			info += "** Order alredy exists ***";

		return info;
	}

	public String addOrder(String code, String date, String clientIdNum, String status, String restaurantNit) {
		String info = "";
		Order o = new Order(code, date, clientIdNum, status, restaurantNit);
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

	public int positionWithOrderCode(String code){
		int position = 0;
		for(int i=0; i<orders.size(); i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				position = i;
			}
		}
		return position;
	}

	//	*****************************************************************************

	//	Sorting methods

	public void sortRestaurantsByName() {
		Restaurant temp;
		for (int i = restaurants.size(); i > 0; i--) {
			for (int j = 0; j < i-1; j++) {
				if (restaurants.get(j).getName().compareTo(restaurants.get(j+1).getName())>0) {
					temp = restaurants.get(j);
					restaurants.set(j,restaurants.get(j+1));
					restaurants.set(j+1, temp);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String sortClientsByTelephone() {
		String info = "";
		if (!clients.isEmpty()) {
			ArrayList<Client> organized = (ArrayList<Client>) clients.clone();
			int i,j;
			for (i = 0; i < clients.size(); i++) {
				Client temp = clients.get(i);
				j = i;
				while ((j > 0) && clients.get(j-1).getTelephone().compareTo(temp.getTelephone())>0) {
					clients.set(j,clients.get(j-1));
					j--;
				}
				clients.set(j, temp);
			}
			for (i = 0; i < clients.size(); i++) {
				info += "\n******************************************************\n";
				info += "\n" + clients.get(i).getInfo() + "This is the client #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
			clients = organized;
		}
		else
			info += "No clients registered yet";
		return info;
	}

	public void sortOrder() {
		class SortOrder implements Comparator<ItemOrder>{
			@Override
			public int compare(ItemOrder o1, ItemOrder o2) {
				int value1 = 0, value2 = 0, value3 = 0;
				value1 = o1.getOrder().getRestaurantNit().compareTo(o2.getOrder().getRestaurantNit());
				if(value1 == 0) {
					value2 = o2.getOrder().getClientIdNum().compareTo(o1.getOrder().getClientIdNum());
					if(value2 == 0) {
						value3 = o1.getOrder().getDate().compareTo(o2.getOrder().getDate());
						if (value3 == 0) {	
							return o1.getProduct().getCode().compareTo(o2.getProduct().getCode());
						}
						else {
							return value3;
						}
					}
					else {
						return value2;
					}
				}
				return value1;
			}
		}
		for (int i = 0; i < orders.size(); i++) {
			Collections.sort(orders.get(i).productsList, new SortOrder());
		}

	}
	//	*****************************************************************************

	//	Searching methods

	public int searchRestaurant(String nit) throws InvalidNitException {
		int position = 0;
		boolean exists = false;
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				position = i;
				exists = true;
			}
		}
		if(exists == false) {
			throw new InvalidNitException();
		}
		return position;
	}

	public int searchProduct(String code) throws InvalidCodeException {
		int position = 0;
		boolean exists = false;
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code)) {
				position = i;
				exists = true;
			}
		}
		if(exists == false) {
			throw new InvalidCodeException();
		}
		return position;
	}

	public int searchClient(String idNum) throws InvalidIdNumException {
		int position = 0;
		boolean exists = false;
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idNum)) {
				position = i;
				exists = true;
			}
		}
		if(exists == false) {
			throw new InvalidIdNumException();
		}
		return position;
	}

	public int searchOrder(String orderCode) throws InvalidCodeException {
		int position = 0;
		boolean exists = false;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(orderCode)) {
				position = i;
				exists = true;
			}
		}
		if(exists == false) {
			throw new InvalidCodeException();
		}
		return position;
	}

	//	**************************************************************

	//	Update methods of restaurants

	public String updateRestaurantName(String nit, String newName) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				restaurants.get(i).setName(newName);
				info += "Name updated";
			}
		}
		return info;
	}

	public String updateRestaurantNit(String nit, String newNit) {
		String info = "";
		if(uniqueRestaurantNit(newNit)) {
			for (int i = 0; i < restaurants.size(); i++) {
				if(restaurants.get(i).getNit().equals(nit)) {
					info += updateProductRestaurantNit(nit, newNit);
					for (int j = 0; j < restaurants.get(i).products.size(); j++) {
						restaurants.get(i).products.get(j).setRestaurantNit(newNit);
						info += "Nit of the restaurant own products updated\n";
					}
					info += updateOrderRestaurantNit(nit, newNit);
					restaurants.get(i).setNit(newNit);
					info += "Restaurant nit updated\n";
				}
			}
		}
		else
			info += "The nit alredy exist\n";
		return info;
	}

	public String updateRestaurantManager(String nit, String newManager) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(nit)) {
				restaurants.get(i).setManager(newManager);
				info += "Manager updated\n";		
			}
		}
		return info;
	}

	//	**************************************************************

	//	Update methods of product

	public String updateProductName(String code, String newName) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setName(newName);
				info += "Name of product updated";
			}
		}
		return info;
	}

	public String updateProductCode(String code, String newCode) {
		String info = "";
		if(uniqueProductCode(newCode)) {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getCode().equals(code)) {
					info += updateProductCodeOfOrder(code, newCode);
					info += updateProductCodeOfRestaurantProducts(code, newCode);
					products.get(i).setCode(newCode);
					info += "Code of product updated\n";
				}
			}
		}
		else
			info += "The code already exists";
		return info;
	}

	public String updateProductCodeOfOrder(String code, String newCode) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.get(i).productsList.size(); j++) {
				if (orders.get(i).productsList.get(j).getProduct().getCode().equals(code)) {
					orders.get(i).productsList.get(j).getProduct().setCode(newCode);
					info += "Product's code in the order list updated\n";
				}
			}
		}
		return info;
	}

	public String updateProductCodeOfRestaurantProducts(String code, String newCode) {
		String info = "";
		for (int i = 0; i < restaurants.size(); i++) {
			for (int j = 0; j < restaurants.get(i).products.size(); j++) {
				if (restaurants.get(i).products.get(j).getCode().equals(code)) {
					restaurants.get(i).products.get(j).setCode(newCode);
					info += "Product's code in the restaurant list updated\n";
				}
			}
		}
		return info;
	}

	public String updateProductDescription(String code, String newDescription) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setDescription(newDescription);
				info += "Product's description updated";
			}
		}
		return info;
	}

	public String updateProductCost(String code, double newCost) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getCode().equals(code)) {
				products.get(i).setCost(newCost);
				info += "Product's name updated";
			}
		}
		return info;
	}

	public String updateProductRestaurantNit(String restaurantNit, String newRestaurantNit) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getRestaurantNit().equals(restaurantNit)) {
				products.get(i).setRestaurantNit(newRestaurantNit);
				info += "Product's restaurant nit updated\n";
			}
		}
		return info;
	}

	public String updateProductQuantityOfOrder(String code, int newQuantity) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.get(i).productsList.size(); j++) {
				if (orders.get(i).productsList.get(j).getProduct().getCode().equals(code)) {
					orders.get(i).productsList.get(j).getProduct().setQuantity(newQuantity);
					info += "Product's quantity in the order list updated\n";
				}
			}
		}
		return info;
	}

	//	**************************************************************

	//	Update methods of client

	public String updateClientName(String idNum, String newName) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setName(newName);
				info += "Client's name updated";
			}
		}
		return info;
	}

	public String updateClientLastName(String idNum, String newLastName) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setLastName(newLastName);
				info += "Client's last name updated";
			}
		}
		return info;
	}

	public String updateClientIdNum(String idNum, String newIdNum) {
		String info = "";
		if(uniqueClientId(newIdNum)) {
			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getIdNum().equals(idNum)) {
					info += updateOrderClientIdNum(idNum,newIdNum);
					clients.get(i).setIdNum(newIdNum);
					info += "Client's ID updated";
				}
			}	
		}
		else
			info += "The ID already exist";
		return info;
	}

	public String updateClientIdType(String idNum, String newIdType) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setIdType(newIdType);
				info += "Client's ID type updated";
			}
		}
		return info;
	}

	public String updateClientTelephone(String idNum, String newTelephone) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setTelephone(newTelephone);
				info += "Client's telephone updated";
			}
		}
		return info;
	}

	public String updateClientAddress(String idNum, String newAddress) {
		String info = "";
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNum().equals(idNum)) {
				clients.get(i).setAddress(newAddress);
				info += "Client's ID updated";
			}
		}
		return info;
	}

	//	*********************************************************************

	//	Update methods of order

	public String updateOrderClientIdNum(String clientIdNum,String newClientIdNum) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getClientIdNum().equals(clientIdNum)) {
				orders.get(i).setClientIdNum(newClientIdNum);
				info += "Order's client ID num updated";
			}
		}
		return info;
	}

	public String updateOrderRestaurantNit(String restaurantNit ,String newRestaurantNit) {
		String info = "";
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getRestaurantNit().equals(restaurantNit)) {
				orders.get(i).setRestaurantNit(newRestaurantNit);
				info += "Order's restaurant nit updated\n";
			}
		}
		return info;
	}

	//	*********************************************************************

	//	Import methods

	public void importData(String fileName) throws IOException, InvalidRouteException {
		if(fileName.equals("data/restaurants.csv")) {
			importRestaurantData("data/restaurants.csv");
		}
		else if (fileName.equals("data/products.csv")) {
			importProductData("data/products.csv");
		}
		else if(fileName.equals("data/clients.csv")) {
			importClientData("data/clients.csv");
		}
		else if(fileName.equals("data/orders.csv")) {
			importOrderData("data/orders.csv");
		}
		else {
			throw new InvalidRouteException();
		}
	}

	public void importRestaurantData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(",");
			addRestaurant(parts[0],parts[1],parts[2]);	
			line = br.readLine();
		}
		br.close();
	}

	public void importProductData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(",");
			addProduct(parts[0],parts[1],parts[2],Double.parseDouble(parts[3]),parts[4]);	
			line = br.readLine();
		}
		br.close();
	}

	public void importClientData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(",");
			addClient(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]),parts[4],parts[5]);	
			line = br.readLine();
		}
		br.close();
	}

	public void importOrderData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(";");
			addOrder(parts[0],parts[1],parts[2], parts[3],parts[4]);
			int j = linesWithSomething(fileName);
			for (int i = 0; i < j; i++) {
				String [] analize = br.readLine().split(";");
				if (!parts[0].equals(analize[0])) {
					orders.get(i).addProducts(parts[5], Integer.parseInt(parts[6]));
					i = j-1;
				}
				else {
					orders.get(i).addProducts(parts[5], Integer.parseInt(parts[6]));
				}
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public int linesWithSomething(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		int j = 0;
		String line = "";
		do {
			line = br.readLine();
			j++;
		}
		while(line!=null);
		br.close();
		return j;
	}

	//	*********************************************************************

	//	Export method

	public void exportOrder(String separator) throws FileNotFoundException {
		sortOrder();
		PrintWriter pw = new PrintWriter("data/orders.csv");
		pw.println("Code"+separator+"Date"+separator+"Client ID"+separator+"Status"+separator+"Restaurant Nit"+separator+"Product code"+separator+"Product quantity");
		for (int i = 0; i < orders.size(); i++)  {
			if (orders.get(i).getProductsList().size()==1) 
				pw.println(orders.get(i).getCode()+separator+orders.get(i).getDate()+separator+orders.get(i).getClientIdNum()+separator+orders.get(i).getStatus()+separator+orders.get(i).getRestaurantNit()+separator+orders.get(i).getProductsList().get(0).getProduct().getInfoWithQuantityToExport(separator)); 		
			if(orders.get(i).getProductsList().size()>1) {
				for (ItemOrder elem : orders.get(i).getProductsList()) {
					pw.println(elem.getOrder().getCode()+separator+elem.getOrder().getDate()+separator+elem.getOrder().getClientIdNum()+separator+elem.getOrder().getStatus()+separator+elem.getOrder().getRestaurantNit()+separator+elem.getProduct().getInfoWithQuantityToExport(separator));
				} 
			}
		}
		pw.close();
	}

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
	public String loadData() throws IOException, ClassNotFoundException{
		File r = new File(SAVE_PATH_FILE_RESTAURANTS);
		File c = new File(SAVE_PATH_FILE_CLIENTS);
		File p = new File(SAVE_PATH_FILE_PRODUCTS);
		File o = new File(SAVE_PATH_FILE_ORDERS);
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
		if(loaded == false) {
			info = "Nothing to load";
		}
		return info;
	}

}