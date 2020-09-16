package ui;

import java.util.Scanner;

import model.RestaurantsManager;


public class Menu {
	private Scanner lector;
	private RestaurantsManager rm = new RestaurantsManager();
	final static int EXIT_MENU = 9;


	public Menu() {
		lector = new Scanner(System.in);
	}

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
			System.out.println("Please select the type of ID\n1.TI\n2.CC");
			choice = Integer.parseInt(lector.nextLine());
		} while(!((choice == 1) || (choice == 2)));		
		System.out.println("Please enter the client telephone: ");
		String tel = lector.nextLine();
		System.out.println("Please enter the client adress: ");
		String adress = lector.nextLine();
		System.out.println(rm.addClient(name, lastName, idNum, choice,tel,adress));
	}

	public String deployClients() {
		String info = "";
		if (!rm.clients.isEmpty()) {
			for (int i = 0; i < rm.clients.size(); i++) {
				info += "\n" + rm.clients.get(i).getInfo() + "This is the client #" + (i+1) + "\n";
			}
		}
		else
			info += "No clients registered yet";
		return info;
	}

	public void addProduct() {
		if (!rm.restaurants.isEmpty()) {
			System.out.println("Please enter the product name");
			String name = lector.nextLine();
			System.out.println("Please enter the product code");
			String code = lector.nextLine();
			System.out.println("Please enter the description of the product");
			String description = lector.nextLine();
			System.out.println("Please enter the cost of the product");
			double cost = lector.nextDouble();
			lector.nextLine();
			int choose = 0;
			do {
				System.out.println("Please type the number (#) of the restaurant which nit you want");
				System.out.println(deployRestaurants());
				choose = lector.nextInt();
				lector.nextLine();
			}while(choose<0||choose>rm.restaurants.size());
			String restaurantNit = rm.restaurants.get(choose-1).getNit();
			rm.addProduct(name, code, description, cost, restaurantNit);
		}
		else
			System.out.println("The product can't be created because the program doesn't have restaurants yet");
	}

	public String deployProducts() {
		String info = "";
		if (!rm.products.isEmpty()) {
			for (int i = 0; i < rm.products.size(); i++) {
				info += rm.products.get(i).getInfo() + "\n";
			}
		}
		else
			info += "No products registered yet";
		return info;
	}

	public String deployRestaurants() {
		String info = "";
		if (!rm.restaurants.isEmpty()) {
			for (int i = 0; i < rm.restaurants.size(); i++) {
				info += "\n" + rm.restaurants.get(i).getInfo() + "This is the restaurant #" + (i+1) + "\n";
			}
		}
		else
			info += "No restaurants registered yet";
		return info;
	}

	public void addRestaurant() {
		System.out.println("Please enter the restaurant name");
		String name = lector.nextLine();
		System.out.println("Please enter the restaurant nit");
		String nit = lector.nextLine();
		System.out.println("Please enter the restaurant manager name");
		String manager = lector.nextLine();
		rm.addRestaurant(name, nit, manager);
	}

	public void addOrder() {
		int choose = 0;
		if(!rm.clients.isEmpty()|| !rm.restaurants.isEmpty()) {
			System.out.println("Generating the order...\n");
			do {
				System.out.println("Please type the number (#) of the client who ordered");
				System.out.println(deployClients());
				choose = lector.nextInt();
				lector.nextLine();
			}while(choose<0||choose>rm.restaurants.size());
			String idNum = rm.clients.get(choose-1).getIdNum();
			do {
				System.out.println("Please type the number (#) of the restaurant which nit you want");
				System.out.println(deployRestaurants());
				choose = lector.nextInt();
				lector.nextLine();
			}while(choose<0||choose>rm.restaurants.size());
			String restaurantNit = rm.restaurants.get(choose-1).getNit();
			rm.addOrder(idNum, restaurantNit);
			
			int choose2 = 0;
			do {
				System.out.println("The order is ok, now enter the (#) of the order you need... (Just to confirm)\n");
				System.out.println(deployOrders());
				choose2 = Integer.parseInt(lector.nextLine());
			}while(choose2<0||choose2>rm.orders.size());
			System.out.println("Now let's add the products...");
			boolean finished = true;
			do {
				if(!rm.products.isEmpty()) {
					int choose3 = 0;
					do {
						System.out.println("Your restaurant has these products");
						System.out.println("Please type the number (#) of the product you want to add to the order");
						System.out.println(rm.restaurants.get(choose-1).showProducts());
						choose3 = Integer.parseInt(lector.nextLine());
					}while(choose3<0||choose3>rm.restaurants.get(choose-1).products.size());
					String name = rm.restaurants.get(choose-1).products.get(choose3-1).getName();
					String code = rm.restaurants.get(choose-1).products.get(choose3-1).getCode();
					String description = rm.restaurants.get(choose-1).products.get(choose3-1).getDescription();
					double cost = rm.restaurants.get(choose-1).products.get(choose3-1).getCost();
					System.out.println("Please enter the quantity of these product you need");
					int quantity = Integer.parseInt(lector.nextLine());
					rm.orders.get(choose2-1).addProducts(name, code, description, cost, restaurantNit, quantity);
					int decision = 0;
					System.out.println("Some product more?\n1. Yes\n2. No");
					decision = lector.nextInt();
					lector.nextLine();
					if(decision == 1)
						finished = false;
					else if(decision == 2)
						finished = true;
				}
				else {
					System.out.println("No products in any restaurant yet");
					finished = true;
				}
			}while(finished == false);
		}
		else
			System.out.println("We have no clients or restaurants registered yet");
	}

	public String deployOrders() {
		String info = "";
		if (!rm.orders.isEmpty()) {
			for (int i = 0; i < rm.orders.size(); i++) {
				info += rm.orders.get(i).getInfo() + "This is the order #" + (i+1) + "\n";
			}
		}
		else
			info += "No orders registered yet";
		return info;
	}

	private void executeOperation(int option) {
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
			System.out.println(deployRestaurants());

			break;
		case 6:
			System.out.println(deployProducts());

			break;
		case 7:
			System.out.println(deployClients());
			break;

		case 8:
			System.out.println(rm.showoOrders());
			break;

		case 9:
			exitProgram();
			break;
		default:
			break;
		}

	}


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
		menu += "9. Exit\n";
		menu += "Please enter an option\n";
		return menu;
	}
	private void exitProgram() {
		lector.close();
		System.out.println("Good bye!");
	}
	private int readOption() {
		int op;
		op = Integer.parseInt(lector.nextLine());
		return op;
	}
	public void startMenu() {
		String menu = getMenu();
		int option;
		do {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);

		} while (option!=EXIT_MENU);
	}
}
