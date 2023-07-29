package controller;

import java.io.*;

import java.time.*;
import java.util.*;

import model.dog.Breed;
import model.dog.BreedName;
import model.dog.Characteristic;
import model.dog.Dog;
import model.dog.Size;
import model.furniture.Furniture;
import model.furniture.FurnitureMenu;
import model.game.Game;
import model.game.Shop;
import model.game.ShopMenu;
import model.game.WorkMenu;
import model.home.Home;
import model.item.Food;
import model.item.Tool;
import model.item.Toy;
import view.Helper;
import view.UI;
import view.UIConsole;

public class GameProgram{ 
	
	// inform time of day & dog age as system time goes
	// let certain function terminate after a fixed duration (walk dog)
	
	private Game game;
	private UI ui;
	private Helper h;
	
	public GameProgram() {
		ui = new UIConsole();
		h = new Helper();
		//default = new game 
		game = GameReader.readGame("src/file/game", "src/file/dog", "src/file/home"); 
		if (game == null || game.isEmpty()) {
			System.out.println("There is no existing game data from the system. We will start a new game for you.");
			game = GameReader.readGame("src/file/default_game", "src/file/default_dog", "src/file/default_home"); 
		
		}	
	}


	public void gameRun() {
		if(game != null) {
			game.welcomeMessage();
			pause();
			getMainMenuOption(); // 
			saveGameData("src/file/game", game); // record game info
			saveDogData("src/file/dog", game.getDogs());
			saveHomeData("src/file/home", game.getHome());
		}else {
			System.out.println("ERROR: No game to run.");
		}
		
	}
	
	private void getMainMenuOption() {
		// TODO Auto-generated method stub
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Main Menu");
			MainMenu.printMenuOptions();
			int choice = h.inputInt("Choose an option by number", 1, MainMenu.getOptionNum());
			MainMenu option = MainMenu.getOption(choice);
			switch (option) {
			case VIEW_GAME_INFO:
				System.out.println("game design & tutorial");
				break;
			case PLAY:
				//enter game menu
				System.out.println("--- Loading Game Menu ---");
				getGameMenuOption();
				break;
			case QUIT:
				quit = true;
				System.out.println("Goodbye! Your data will be saved.");
				break;
			}
			pause();
		}
	}

	
	private void getGameMenuOption() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Game Menu");
			GameMenu.printMenuOptions();
			int choice = h.inputInt("Choose an option by number", 1, GameMenu.getOptionNum());
			GameMenu option = GameMenu.getOption(choice);
			switch (option) {
			case INITIALIZAZTION:
				System.out.println("Choose the doggie to live with!(home is defualt with basic furniture)");
				// choose dog (custom breed, name)
				System.out.println("\nAvailable breeds are:");
				BreedName.printMenuOptions();
				int c = h.inputInt("Choose an option by number", 1, BreedName.getOptionNum());
				BreedName breedName = BreedName.getOption(c);
				Breed breed = new Breed(breedName);
				String name = h.inputWord("Give your dog a name:");
				Dog d = new Dog(name, breed);
				Set<Dog> dogs = new HashSet<>();
				dogs.add(d);
				game.setDogs(dogs);
				break;
			case VIEW_HOME_INFO:
				//show existing rooms & furniture, but not the actual state
				//need to enter home to view actual state (not implemented yet)
				
				// need modification: for now just show all info
				game.showHomeInfo();
				break;
			case VIEW_DOG_INFO:
				// general info, without current state
				// name, age, size, characteristics
				game.showGeneralDogInfo();
				break;
			case SHOP:
				getShopMenu();
				break;
			case VIEW_WORK_STATE:
				game.showWorkState();
				break;
			case ENTER_HOME:
				// calculate time elapse while not in home (only leave due to work is valid)
				// dog.damage(time); update condition of furniture
				int timeOutside = 0; // round up
				game.probabalisticDamageHappen(timeOutside);
				
				getInHomeMenu();
				break;
			case QUIT:
				quit = true;
				System.out.println("--- Loading Main Menu ---");
				break;
			}
			pause();
		}
		
	}



	private void getShopMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Shop Menu");
			System.out.println("You have $" + game.getMoney() + ".");
			ShopMenu.printMenuOptions();
			int choice = h.inputInt("Choose an option by number", 1, ShopMenu.getOptionNum());
			ShopMenu option = ShopMenu.getOption(choice);
			switch (option) {
			case BUY_FURNITURE:
				getBuyFurnitureMenu();
				break;
			case BUY_FOOD:
				getBuyFoodMenu();
				break;
			case BUY_TOY:
				getBuyToyMenu();
				break;
			case BUY_TOOL:
				System.out.println("Not implemented yet");
				//getBuyToolMenu();
				// clean up the mess
				break;
			case BUY_ROOM:
				Set<String> existRoomNames = game.getExistRoomNames();
				String roomName = h.inputNonRepeatedWord("Enter a name for the room", existRoomNames);
				break;
			case FIX_DOOR:
				System.out.println("Not implemented yet");
				// door damaged by dog
				break;
			case QUIT:
				quit = true;
				System.out.println("--- Loading Game Menu ---");
				break;
			}
			pause();
		}
		
	}


	private void getBuyToolMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Cleaning Tool Menu");
			Tool.printMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, Tool.getOptionNum());
			if (choice != 0) {
				Tool option = Tool.getOption(choice);
				int num = h.inputInt("How many " + option.getDescription() + " do you want? (1~10)", 1, 10);
				for (int i = 0; i < num; i++) {
					buyItem(option, i+1);
				}
			}
			quit = !h.inputYesNo("Do you want to continue buying cleaning tools? (y/n)");
		}
	}


	private void getBuyToyMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Dog Toy Menu");
			Toy.printMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, Toy.getOptionNum());
			if (choice != 0) {
				Toy option = Toy.getOption(choice);
				int num = h.inputInt("How many " + option.getDescription() + " do you want? (1~10)", 1, 10);
				for (int i = 0; i < num; i++) {
					buyItem(option, i+1);
				}
			}
			quit = !h.inputYesNo("Do you want to continue buying dog toys (y/n)");
		}
	}


	private void getBuyFoodMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Dog Food Menu");
			System.out.println("(\"E\" means energy supplied, \"W\" means water supplied)");
			Food.printMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, Food.getOptionNum());
			if (choice != 0) {
				Food option = Food.getOption(choice);
				int num = h.inputInt("How many " + option.getDescription() + " do you want? (1~10)", 1, 10);
				for (int i = 0; i < num; i++) {
					buyItem(option, i+1);
				}
			}
			quit = !h.inputYesNo("Do you want to continue buying dog food? (y/n)");
		}
	}


	private void getBuyFurnitureMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Furniture Menu. You have $" + game.getMoney() + ".\n");
			System.out.println("\"S\" means max storage space. Each room can place at most " + game.getNaxNumFurPerRoom() + " pieces of furniture");
			FurnitureMenu.printMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, FurnitureMenu.getOptionNum());
			if (choice != 0) {
				FurnitureMenu option = FurnitureMenu.getOption(choice);
				String name = option.getDescription();
				Furniture f = Shop.matchStrToFur(name);
				int num = h.inputInt("How many " + f.getName() + " do you want? (1~10)", 1, 10);
				for (int i = 0; i < num; i++) {
					buyItem(f, i+1);
				}
			}
			quit = !h.inputYesNo("Do you want to continue buying furniture? (y/n)");
		}
	}
	
	public void buyItem(Object obj, int i) {
		boolean success = false;
		int price = 0;
		if (obj instanceof Furniture) {
			success = canBuy(((Furniture) obj).getPrice());
			if (success) {
				price = ((Furniture) obj).getPrice();
				// update all list, maps containing Furniture (Room, Home)
				success = game.furnitureBought((Furniture) obj, i);
			}
		}else if (obj instanceof Food){
			// match enum and update the variable num of that enum
			// update classes that has this obj
			success = canBuy(((Food) obj).getPrice());
			if (success) {
				price = ((Food) obj).getPrice();
				success = game.FoodBought((Food)obj, i);
			}
		}else if (obj instanceof Toy) {
			success = canBuy(((Toy) obj).getPrice());
			if (success){
				price = ((Toy) obj).getPrice();
				success = game.FoodBought((Toy)obj, i);
			}
		}else if (obj instanceof Tool) {
			success = canBuy(((Tool) obj).getPrice());
			price = ((Tool) obj).getPrice();
		}
		if (success) {
			game.spendMoney(price);
			System.out.println("Successful transaction. You have $" + game.getMoney() + " remaining.\n");
		}else {
			System.out.println("Transaction failed. You do not have enough money ($" + game.getMoney() + ".\n");
		}
	}
	
	private boolean canBuy(int price) {
		if (game.getMoney() - price >= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	private void getInHomeMenu() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Home Menu");
			InHomeMenu.printMenuOptions();
			int choice = h.inputInt("Choose an option by number", 1, InHomeMenu.getOptionNum());
			InHomeMenu option = InHomeMenu.getOption(choice);
			switch (option) {
			// consume items
			case VIEW_STATE:
				// dogs' current state
				break;
			case FEED:
				// choose food from food list
				
				// consume = dog state change by the amount of food
				
				// delete food from food list
				break;
			case PLAY:
				// choose toy from toy list
				
				// consume = dog state change by the amount of toy
				
				// wear toy 
				break;
			case WALK_DOG:
				// fixed a duration of a fixed screen
				// record start time point
				// increase dog state when finished
				break;
			case WORK:
				getWorkMenu();
				// modify variable work, record start time
				break;
			case QUIT:
				quit = true;
				System.out.println("--- Loading Game Menu ---");
				break;
			}
			pause();
		}
	}


	private void getWorkMenu() {
		System.out.println("\nYou are at Work Menu");
		WorkMenu.printMenuOptions();
		int choice = h.inputInt("Choose an option by number", 1, InHomeMenu.getOptionNum()-1);
		WorkMenu option = WorkMenu.getOption(choice);
		switch (option) {
		// record start time & update work
		case A:
			//
			
			break;
		case B:
			
			break;
		case C:
			
			break;
		}
	}


	public void pause() {
		String pause = ui.inputLine("Hit enter to continue --");
	}

	
	public void saveData(String gameFile, Game game) {
		// command-shift -o -- organizes imports (add needed imports to top of program)
		// try FileOutputStrea & PrintWriter
		// try with resources
		try (FileOutputStream fos = new FileOutputStream(gameFile); PrintWriter out = new PrintWriter(fos)) {
			// want to write each USER to the file
			// header row
			out.println(game.toFileString());
			System.out.println("Saved users to: " + gameFile);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + gameFile);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource

	}
	
	

	// for system-wide data (player, money, time last left)
	public void saveGameData(String gameFile, Game game) {
		try (FileOutputStream fos = new FileOutputStream(gameFile); PrintWriter out = new PrintWriter(fos)) {
			out.println(game.toFileString());
			System.out.println("Saved game system info to: " + gameFile);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + gameFile);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
		
	}
	
	// for information specific to dog
	public void saveDogData(String dogFile, Set<Dog> dog) {
		try (FileOutputStream fos = new FileOutputStream(dogFile); PrintWriter out = new PrintWriter(fos)) {
			out.println(game.toFileString());
			System.out.println("Saved dog(s) to: " + dogFile);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + dogFile);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
		
	}
	
	// for information specific to dog
	public void saveHomeData(String homeFile, Home home) {
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream(homeFile); PrintWriter out = new PrintWriter(fos)) {
			out.println(game.toFileString());
			System.out.println("Saved home to: " + homeFile);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + homeFile);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
		
	}
	
	public static void main(String[] args) {
		GameProgram gp = new GameProgram();
		gp.gameRun();
	}

}
