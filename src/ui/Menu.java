package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import exceptions.InvalidCodeException;
import exceptions.InvalidIdNumException;
import exceptions.InvalidNitException;
import exceptions.InvalidRouteException;
import model.RestaurantsManager;


public class Menu {
	private Scanner lector;
	private RestaurantsManager rm = new RestaurantsManager();
	final static int EXIT_MENU = 15;

	public Menu() {
		lector = new Scanner(System.in);
	}
	/**
	 * This method gets the info to create a client
	 */
	public void addClient() {
		int choice;
		System.out.println("Adding client...\n");
		System.out.println("Please enter the client name: ");
		String name = lector.nextLine();
		System.out.println("Please enter the client lastname: ");
		String lastName = lector.nextLine();
		System.out.println("Please enter the client ID number: ");
		String idNum = lector.nextLine();
		do {
			System.out.println("Please select the type of ID\n1.TI\n2.CC\n3. CE\n4. PP");
			choice = Integer.parseInt(lector.nextLine());
		} while(!((choice == 1) || (choice == 2))|| (choice == 3)|| (choice == 4));		
		System.out.println("Please enter the client telephone: ");
		String tel = lector.nextLine();
		System.out.println("Please enter the client adress: ");
		String adress = lector.nextLine();
		try {
			System.out.println(rm.addClient(name, lastName, idNum, choice,tel,adress));
			System.out.println("Client saved!");
		} catch (IOException e) {
			System.err.println("The data can't be saved");
		}
	}
	/**
	 * This method deploy clients and their position 
	 * @return the info to show
	 */
	public String deployClients() {
		String info = "";
		if (!rm.clients.isEmpty()) {
			for (int i = 0; i < rm.clients.size(); i++) {
				info += "\n******************************************************\n";
				info += "\n" + rm.clients.get(i).getInfo() + "This is the client #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
		}
		else
			info += "No clients registered yet";
		return info;
	}
	/**
	 * This method gets the info to create a product
	 */
	public void addProduct() {
		if (!rm.restaurants.isEmpty()) {
			System.out.println("Please enter the product name");
			String name = lector.nextLine();
			System.out.println("Please enter the product code");
			String code = lector.nextLine();
			System.out.println("Please enter the description of the product");
			String description = lector.nextLine();
			System.out.println("Please enter the cost of the product");
			double cost = Double.parseDouble(lector.nextLine());
			int choose = 0;
			do {
				System.out.println(deployRestaurantsWithoutProducts());
				System.out.println("Please type the number (#) of the restaurant which nit you want");
				choose = Integer.parseInt(lector.nextLine());
			}while(choose<0||choose>rm.restaurants.size());
			String restaurantNit = rm.restaurants.get(choose-1).getNit();
			try {
				System.out.println(rm.addProduct(name, code, description, cost, restaurantNit));
				System.out.println("Product saved!");
			} catch (IOException e) {
				System.err.println("Product can't be saved");
			}
		}
		else
			System.out.println("The product can't be created because the program doesn't have restaurants yet");
	}
	/**
	 * This method deploy products and their position 
	 * @return the info to show
	 */
	public String deployProducts() {
		String info = "";
		if (!rm.products.isEmpty()) {
			for (int i = 0; i < rm.products.size(); i++) {
				info += "\n******************************************************\n";
				info += rm.products.get(i).getInfo() + "\nThis is the product #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
		}
		else
			info += "No products registered yet";
		return info;
	}
	/**
	 * This method deploy restaurants and their products with the position of both 
	 * @return the info to show
	 */
	public String deployRestaurantsWithProducts() {
		String info = "";
		if (!rm.restaurants.isEmpty()) {
			for (int i = 0; i < rm.restaurants.size(); i++) {
				info += "\n******************************************************\n";
				info += "\n" + rm.restaurants.get(i).getInfoWithProducts() + "\nThis is the restaurant #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
		}
		else
			info += "No restaurants registered yet";
		return info;
	}
	/**
	 * This method deploy restaurants and their position 
	 * @return the info to show
	 */
	public String deployRestaurantsWithoutProducts() {
		String info = "";
		if (!rm.restaurants.isEmpty()) {
			for (int i = 0; i < rm.restaurants.size(); i++) {
				info += "\n******************************************************\n";
				info += "\n" + rm.restaurants.get(i).getInfoWithoutProducts() + "This is the restaurant #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
		}
		else
			info += "No restaurants registered yet";
		return info;
	}
	/**
	 * This method gets the info to create a restaurant
	 */
	public void addRestaurant() {
		System.out.println("Please enter the restaurant name");
		String name = lector.nextLine();
		System.out.println("Please enter the restaurant nit");
		String nit = lector.nextLine();
		System.out.println("Please enter the restaurant manager name");
		String manager = lector.nextLine();
		try {
			System.out.println(rm.addRestaurant(name, nit, manager));
			System.out.println("Restaurant saved");
		} catch (IOException e) {
			System.err.println("Restaurant can't be saved");
		}
	}
	/**
	 * This method gets the info to create a order
	 */
	public void addOrder() {
		int choose = 0;
		if(!rm.clients.isEmpty()&&!rm.restaurants.isEmpty()&&!rm.products.isEmpty()) {
			System.out.println("Generating the order...\n");
			Random random = new Random();
			String code = new BigInteger(50,random).toString(32);
			do {
				System.out.println(deployClients());
				System.out.println("Please type the number (#) of the client who ordered");
				choose = Integer.parseInt(lector.nextLine().trim());
			}while(choose<0||choose>rm.clients.size());
			String idNum = rm.clients.get(choose-1).getIdNum();
			do {
				System.out.println(deployRestaurantsWithoutProducts());
				System.out.println("Please type the number (#) of the restaurant which nit you want");
				choose = Integer.parseInt(lector.nextLine().trim());
			}while(choose<0||choose>rm.restaurants.size());
			String restaurantNit = rm.restaurants.get(choose-1).getNit();
			System.out.println(rm.addOrder(code, idNum, restaurantNit));
			int position = rm.positionWithOrderCode(code);
			System.out.println("Now let's add the products...");
			boolean finished = true;
			do {
				int choose2 = 0;
				do {
					System.out.println(rm.restaurants.get(choose-1).showProducts());
					System.out.println("Please type the number (#) of the product you want to add to the order");
					choose2 = Integer.parseInt(lector.nextLine());
				}while(choose2<0||choose2>rm.restaurants.get(choose-1).products.size());
				String code2 = rm.restaurants.get(choose-1).products.get(choose2-1).getCode();
				System.out.println("Please enter the quantity of these product you need");
				int quantity = Integer.parseInt(lector.nextLine());
				System.out.println(rm.orders.get(position).addProducts(code2, quantity));
				System.out.println("Some product more?\n1. Yes\n2. No");
				int decision = Integer.parseInt(lector.nextLine());
				if(decision == 1)
					finished = false;
				else if(decision == 2)
					finished = true;
			}while(finished == false);
			try {
				rm.saveData("order");
				System.out.println("\nOrder saved!");
			} catch (IOException e) {
				System.err.println("Order can't be saved");
				e.printStackTrace();
			}
		}
		else
			System.out.println("We have no clients or restaurants or products registered yet");
	}
	/**
	 * This method deploy orders with their products and the position of both 
	 * @return the info to show
	 */
	public String deployOrders() {
		String info = "";
		if (!rm.orders.isEmpty()) {
			for (int i = 0; i < rm.orders.size(); i++) {
				info += "\n******************************************************\n";
				info += rm.orders.get(i).getInfo();
				info += "\n===================\n";
				info += "With these products requested\n";
				info +=	rm.orders.get(i).getProducts();
				info += "=====================\n";
				info +=	"\nThis is the order #" + (i+1) + "\n";
				info += "******************************************************\n";
			}
		}
		else
			info += "No orders registered yet";
		return info;
	}
	/**
	 * This method load the data and catch the exception
	 */
	public void loadData() {
		try {
			System.out.println("\n" + rm.loadData() + "\n");
		} catch (ClassNotFoundException e) {
			System.err.println("Something going wrong\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Data can't be loaded\n");
			e.printStackTrace();
		}
	}
	/**
	 * This method call the methods to search the position of the object requested
	 * @param selection is the object requested
	 * @return the position of the object requested
	 * @throws InvalidNitException when the nit doesn't exists
	 * @throws InvalidCodeException when the code doesn't exists
	 * @throws InvalidIdNumException when the ID number doesn't exists
	 */
	public int whichOne(int selection) throws InvalidNitException, InvalidCodeException, InvalidIdNumException {
		int position = 0;
		switch (selection) {
		case 1:
			System.out.println(deployRestaurantsWithoutProducts());
			System.out.println("What of these restaurants you need? Choose it by it's nit");
			String nit = lector.nextLine();
			position = rm.searchRestaurant(nit);
			break;
		case 2:
			System.out.println(deployProducts());
			System.out.println("What of these products you need? Choose it by it's code");
			String code = lector.nextLine();
			position = rm.searchProduct(code);
			break;
		case 3:
			System.out.println(deployClients());
			System.out.println("What of these clients you need? Choose it by it's ID");
			String idNum = lector.nextLine();
			position = rm.searchClient(idNum);
			break;
		case 4:
			System.out.println(deployOrders());
			System.out.println("What of these orders you need? Choose it by it's (#) number");
			String orderCode = lector.nextLine();
			position = rm.searchOrder(orderCode);
			break;
		default:
			break;
		}
		return position;
	}
	/**
	 * This method direct and call the methods to update all the attributes of restaurant
	 * @param position is the position of the restaurant
	 */
	public void updateRestaurant(int position) {
		System.out.println("Please select what do you want to change\n");
		System.out.println("1. Name\n2. Nit\n3. Manager");
		int selection = Integer.parseInt(lector.nextLine());
		switch (selection) {
		case 1:
			System.out.println("What's the new name?");
			String newName = lector.nextLine();
			System.out.println(rm.updateRestaurantName(rm.restaurants.get(position).getNit(), newName));
			break;
		case 2:
			System.out.println("What's the new nit?");
			String newNit = lector.nextLine();
			System.out.println(rm.updateRestaurantNit(rm.restaurants.get(position).getNit(), newNit));
			break;
		case 3:
			System.out.println("Who's the new manager?");
			String newManager = lector.nextLine();
			System.out.println(rm.updateRestaurantManager(rm.restaurants.get(position).getNit(), newManager));
			break;
		default:
			break;
		}
		try {
			rm.saveData("rest");
			System.out.println("Update saved!\n");
		} catch (IOException e) {
			System.err.println("The updating can't be saved");
		}
	}
	/**
	 * This method direct and call the methods to update all the attributes of product
	 * @param position is the position of the product
	 */
	public void updateProduct(int position) {
		System.out.println("Please select what do you want to change\n*The restaurant nit only change if you update it directly in the restaurant option*\n");
		System.out.println("1. Name\n2. Code\n3. Description\n4. Cost\n");
		int selection = Integer.parseInt(lector.nextLine());
		switch (selection) {
		case 1:
			System.out.println("What's the new name?");
			String newName = lector.nextLine();
			rm.updateProductName(rm.products.get(position).getCode(), newName);
			break;
		case 2:
			System.out.println("What's the new code?");
			String newCode = lector.nextLine();
			rm.updateProductCode(rm.products.get(position).getCode(), newCode);
			break;
		case 3:
			System.out.println("What's the new description?");
			String newDescription = lector.nextLine();
			rm.updateProductDescription(rm.products.get(position).getCode(), newDescription);
			break;
		case 4:
			System.out.println("What's the new cost?");
			double newCost = Double.parseDouble(lector.nextLine());
			rm.updateProductCost(rm.products.get(position).getCode(), newCost);
			break;
		default:
			break;
		}
		try {
			rm.saveData("products");
			rm.saveData("order");
			System.out.println("Update saved!\n");
		} catch (IOException e) {
			System.err.println("The updating can't be saved");
		}

	}
	/**
	 * This method direct and call the methods to update all the attributes of client
	 * @param position is the position of the client
	 */
	public void updateClient(int position) {
		System.out.println("Please select what do you want to change\n");
		System.out.println("1. Name\n2. Last name\n3. ID\n4. ID type\n5. Telephone\n6. Address\n");
		int selection = Integer.parseInt(lector.nextLine());
		switch (selection) {
		case 1:
			System.out.println("What's the new name?");
			String newName = lector.nextLine();
			rm.updateClientName(rm.clients.get(position).getIdNum(), newName);
			break;
		case 2:
			System.out.println("What's the new last name?");
			String newLastName = lector.nextLine();
			rm.updateClientLastName(rm.clients.get(position).getIdNum(), newLastName);
			break;
		case 3:
			System.out.println("What's the new ID?");
			String newIdNum = lector.nextLine();
			rm.updateClientIdNum(rm.clients.get(position).getIdNum(), newIdNum);
			break;
		case 4:
			System.out.println("What's the new ID type?");
			String newIdType = lector.nextLine();
			rm.updateClientIdType(rm.clients.get(position).getIdNum(), newIdType);
			break;
		case 5:
			System.out.println("What's the new telephone?");
			String newTelephone = lector.nextLine();
			rm.updateClientTelephone(rm.clients.get(position).getIdNum(), newTelephone);
			break;
		case 6:
			System.out.println("What's the new address?");
			String newAddress = lector.nextLine();
			rm.updateClientAddress(rm.clients.get(position).getIdNum(), newAddress);
			break;
		default:
			break;
		}
		try {
			rm.saveData("client");
			System.out.println("Update saved!\n");
		} catch (IOException e) {
			System.err.println("The updating can't be saved");
		}
	}
	/**
	 * This method call the method to show products in order and able to choose one
	 * @param position of the order
	 * @return position of the product
	 */
	public int whichProduct(int position) {
		int productPosition = 0;
		do {
			System.out.println(rm.orders.get(position).getProducts());
			System.out.println("Which product you need? Choose it by it's (#) number");
			productPosition = Integer.parseInt(lector.nextLine());
		}while(productPosition<0||productPosition>rm.orders.get(position).productsList.size());
		return productPosition-1;
	}
	/**
	 * This method direct and call the methods to update some attributes of order
	 * @param position is the position of the order
	 */
	public void updateOrder(int position) {
		System.out.println("Please select what do you want to change\n");
		System.out.println("1. Product's quantity\n2. Delete\n3. Update status");
		int selection = Integer.parseInt(lector.nextLine());
		switch (selection) {
		case 1:
			int productPosition = whichProduct(position);
			System.out.println("What's the new quantity?");
			int newQuantity = Integer.parseInt(lector.nextLine());
			rm.updateProductQuantityOfOrder(rm.orders.get(position).getProductsList().get(productPosition).getProduct().getCode(), newQuantity);
			break;
		case 2:
			rm.orders.remove(position);
			System.out.println("Order deleted");
			break;
		case 3:
			System.out.println(rm.orders.get(position).updateStatus());
			break;
		default:
			break;
		}
		try {
			rm.saveData("order");
			System.out.println("Update saved!\n");
		} catch (IOException e) {
			System.err.println("The updating can't be saved");
		}

	}
	/**
	 * This method direct the petition to their respective method 
	 * @throws InvalidNitException when the nit doesn't exists
	 * @throws InvalidCodeException when the code doesn't exists
	 * @throws InvalidIdNumException when the ID number doesn't exists
	 */
	public void updateAll() throws InvalidNitException, InvalidCodeException, InvalidIdNumException {
		System.out.println("Please select what do you want to update\n");
		System.out.println("1. Restaurants\n2. Products\n3. Clients\n4. Orders\n");
		int selection = Integer.parseInt(lector.nextLine());
		int position = 0;
		switch (selection) {
		case 1:
			position = whichOne(selection);
			updateRestaurant(position);
			break;
		case 2:
			position = whichOne(selection);
			updateProduct(position);
			break;
		case 3:
			position = whichOne(selection);
			updateClient(position);
			break;
		case 4:
			position = whichOne(selection);
			updateOrder(position);
			break;
		default:
			break;
		}
	}
	/**
	 * This method get's the route of the file to import and directs to the method of import data
	 */
	public void importData() {
		System.out.println("Please, entry the route of the file\n");
		String fileName = lector.nextLine();
		try {
			System.out.println("Importing, wait a minute...");
			rm.importData(fileName);
			System.out.println("Data imported!");
			rm.saveData("order");
			System.out.println("Saved!");
		} catch (IOException | InvalidRouteException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * This method call the method to sort the arrayList of restaurants by name
	 */
	public void sortRestaurantsByName() {
		rm.sortRestaurantsByName();
		System.out.println(deployRestaurantsWithoutProducts());
	}
	/**
	 * This method call the method to sort the arrayList of clients by telephone
	 */
	public void sortClientsByTelephone() {
		System.out.println(rm.sortClientsByTelephone());
	}
	/**
	 * This method request a client name and last name, then call a method in Restaurants Manager to execute the binary search, finally show the searching time.
	 */
	private void searchClientByName() {
		System.out.println("Enter the first name of client ");
		String name = lector.nextLine();
		System.out.println("Enter the last name of client ");
		String lastName = lector.nextLine();
		boolean found;
		long start = System.currentTimeMillis();
		found = rm.searchClientName(name, lastName);
		long end = System.currentTimeMillis();
		if(found) {
			System.out.println("Client :"+name+" "+lastName+" was found!");
		} else {
			System.out.println("Client :"+name+" "+lastName+" was not found!");
		}
		System.out.println("Start: "+start);
		System.out.println("End: "+end);
		System.out.println("Searching time was: "+(end-start));
	}
	/**
	 * This method directs the petition to the respective method
	 * @param option is the petition
	 */
	private void executeOperation(int option){
		switch (option) {
		case 1:
			addRestaurant();
			break;

		case 2:
			addProduct();

			break;
		case 3:
			addClient();

			break;
		case 4:
			addOrder();

			break;
		case 5:
			System.out.println(deployRestaurantsWithProducts());

			break;
		case 6:
			System.out.println(deployProducts());

			break;
		case 7:
			System.out.println(deployClients());
			break;

		case 8:
			System.out.println(deployOrders());
			break;

		case 9:
			try {
				updateAll();
			} catch (InvalidNitException | InvalidCodeException | InvalidIdNumException e) {
				System.err.println(e.getMessage());
			}
			break;

		case 10:
			sortRestaurantsByName();
			break;

		case 11:
			sortClientsByTelephone();
			break;

		case 12:
			searchClientByName();
			break;
			
		case 13:
			importData();
			break;

		case 14:
			try {
				rm.exportOrder(";");
				System.out.println("Export succesfully");
			} catch (FileNotFoundException e) {
				System.err.println("Order can't be export");
			}
			break;

		case 15:
			exitProgram();
			break;
		default:
			break;
		}

	}
	/**
	 * This method show the menu
	 * @return the menu
	 */
	private String getMenu() {
		String menu;
		menu = "----------------------------\n";
		menu = "           WELCOME\n";
		menu = "-----------------------------\n";
		menu += "     Restaurant Manager    \n";
		menu += "----------------------------\n";
		menu += "1. Add restaurant\n";
		menu += "2. Add product\n";
		menu += "3. Add client\n";
		menu += "4. Add Order\n";
		menu += "5. Show restaurants list\n";
		menu += "6. Show products list\n";
		menu += "7. Show clients list\n";
		menu += "8. Show orders list\n";
		menu += "9. Update information\n";
		menu += "10. Show restaurants sorted by name\n";
		menu += "11. Show clients sorted by telephone\n";
		menu += "12. Search client\n";
		menu += "13. Import data\n";
		menu += "14. Export order\n";
		menu += "15. Exit\n";
		menu += "Please enter an option\n";
		return menu;
	}
	/**
	 * This method closes the Scanner and says goodbye
	 */
	private void exitProgram() {
		lector.close();
		System.out.println("Good bye!");
	}
	/**
	 * This method read the petition
	 * @return the petition
	 */
	private int readOption() {
		int op;
		op = Integer.parseInt(lector.nextLine());
		return op;
	}
	/**
	 * This method initialize the program
	 */
	public void startMenu() {
		String menu = getMenu();
		loadData();
		int option;
		do {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);

		} while (option!=EXIT_MENU);
	}
}
