package controller;

import java.io.*;
import java.text.*;

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
	// keep track of time & energy change due to time ? regular reward from dog?
	
	// inform time of day & dog age as system time goes
	
	
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
			case CLEAR_DATA:
				boolean check = h.inputBoolean("Are you sure to clear existing game data? It can't be undo.");
				if (check) {
					clearData();
					System.out.println("All data have been deleted.");
				}
			case QUIT:
				quit = true;
				System.out.println("Goodbye! Your data will be saved.");
				break;
			}
			pause();
		}
	}

	
	private void clearData() {
		resetGameFile("src/file/game");
		resetDogFile("src/file/dog");
		resetHomeFile("src/file/home");
	}


	private void resetGameFile(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName); PrintWriter out = new PrintWriter(fos)) {
//			out.print("money/workChosen/startTime\n");
//			out.print("20/NULL/dd-MM-yyyy HH:mm:ss");
			out. write(32);
			System.out.println("Saved home to: " + fileName);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + fileName);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
	}


	private void resetHomeFile(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName); PrintWriter out = new PrintWriter(fos)) {
//			out.print("RoomName/isOn/record/ComfortLevel/furName,price,material,isOpen,storeSpace,spaceUsed,isDamaged,food1*food2,toy1,tool1\n");
//			out.print("Room1/false/none/3/true/false/WOOD/wood chair,2,WOOD,false,2,2,false,PUPPUCCINO'1,NULL,RAG'1/wood table,2,WOOD,false,6,3,false,NULL,CARROT'1,TISSUE'1\n");
//			out.print("Room2/false/none/4/true/false/WOOD/bed,15,WOOD,false,10,0,false,NULL,NULL,NULL/cabinet,7,WOOD,false,20,3,false,KIBBLE'2-TIN'1,NULL,PLASTIC_BAG'3");
			out. write(32);
			System.out.println("Saved home to: " + fileName);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + fileName);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
	}


	private void resetDogFile(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName); PrintWriter out = new PrintWriter(fos)) {
//			out.print("name/age/currentEL/currentWL/mood/friendship/destructivePower/currentSize/breedName,initialDP,maxSize,Personality1-Personality2-Personality3\n");
//			out.print("NULL");
			out. write(32);
			System.out.println("Saved home to: " + fileName);
			// catch Exception 1
		} catch (FileNotFoundException e) {
			System.err.println("File not found, problem saving data... " + fileName);
			// catch Exception 2
		} catch (IOException e) {
			System.err.println("Some other IO Problem happened while saving data... " + e);
		} // ��files are closed automatically during try-with-resource
	}


	private void getGameMenuOption() {
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Game Menu");
			GameMenu option = null;
			if (game.getDogs().size() != 0){ // basically force player to have dog whenever in game
				GameMenu.printMenuOptions();
				int choice = h.inputInt("Choose an option by number", 1, GameMenu.getOptionNum()-1) +1;
				option = GameMenu.getOption(choice); // should not include initilization
			}else {
				option = GameMenu.INITIALIZAZTION;
			}
			String currentTime = "";
			switch (option) {
			case INITIALIZAZTION:
				System.out.println("As there's no dog in your home, choose a pet dog!(home is defualt with basic furniture)");
				game.addSingleDog();
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
			case ADD_DOGS:
				System.out.println("Not implemented yet");
				// meet standards to call game.addMultipleDogs(int)
				break;
			case SHOP:
				getShopMenu();
				break;
			case VIEW_WORK_STATE:
				currentTime = getCurrentTimeString();
				game.getWorkState(currentTime);
				break;
			case ENTER_HOME:
				// calculate time elapse while not in home (only leave due to work is valid)
				// dog.damage(time); update condition of furniture
				currentTime = getCurrentTimeString();
				long timeOutSide = 0; 
				timeOutSide = game.getTimeOutside(currentTime);
				if (!game.getWorkState(currentTime)) { // if have finished work/did't leave home for work before
					System.out.println("As you don't have work in process, you can enter home without loss.");
					game.probabalisticDamageHappen(timeOutSide);
					getInHomeMenu();
				}else { // work not finished
					boolean doEnterHome = false;
					doEnterHome = h.inputYesNo("You have unfinished work, if you leave work for home now you will not get salary."
							+ "\nAre you sure you want to come home? (y/n)");
					if (doEnterHome){
						// no salary and work is abandoned
						game.setWork(null);;
						game.probabalisticDamageHappen(timeOutSide);
						getInHomeMenu();
					}
				}
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
				game.buyRoom();
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
					game.buyItem(option, i+1);
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
					game.buyItem(option, i+1);
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
					game.buyItem(option, i+1);
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
					game.buyItem(Shop.matchStrToFur(name), i+1);
				}
			}
			quit = !h.inputYesNo("Do you want to continue buying furniture? (y/n)");
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
				game.showCurrentDogInfo();
				break;
			case FEED:
				// choose food from food list
				List<Food> foodList = new ArrayList<>();
				foodList = getUseFoodMenu();
				if (foodList != null) {
					game.feedDog(foodList);
				}else {
					System.out.println("You haven't chosen any food.");
				}
				break;
			case PLAY:
				// reasonable to play one toy at a time
				Toy toy = null;
				// choose toy from toy list
				toy = getUseToyMenu();	
				if (toy != null) {
					game.playWithDog(toy);
				}else {
					System.out.println("You haven't chosen any toys.");
				}
				break;
			case WALK_DOG:
				// fixed a duration of a fixed screen
				// record start time point
				// increase dog state when finished
				System.out.println("You will walk dog for 1 minutes."); // test
				game.walkDog();
				String startTime = getCurrentTimeString();
				boolean finished = false;
				while (!finished) { // fixed at the current screen
					String currentTime = getCurrentTimeString();
					long diffInMinutes = findMinDifference(startTime, currentTime);
					if (diffInMinutes >= 1) {
						finished = true;
					}
				}
				System.out.println("Finished walking dog.");
				break;
			case WORK:
				// must have dogs to work, or player can continuously working without any potential loss
				if (game.getDogs() != null) {
					getWorkMenu();
					quit = true; // force leave home due to work
					// modify variable work, record start time
				}else {
					System.out.println("You can only work when you have a dog");
				}
				break;
			case QUIT:
				quit = true;
				System.out.println("--- Loading Game Menu ---");
				break;
			}
			pause();
		}
	}


	private Toy getUseToyMenu() {
		Toy toy = null;
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Dog Toy In Storage Menu");
			Toy.printConsumeMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, Toy.getAvailableOptionNum());
			if (choice != 0) {
				toy = Toy.getOption(choice);
			}
			quit = !h.inputYesNo("Do you want to change the dog toy chosen? (y/n)");
		}
		return toy;
	}


	private List<Food> getUseFoodMenu() {
		List<Food> foodList = new ArrayList<>();
		boolean quit = false;
		while (!quit) {
			System.out.println("\nYou are at Dog Food In Storage Menu");
			Food.printConsumeMenuOptions();
			int choice = h.inputInt("Choose an option by number (0 to quit)", 0, Food.getAvailableOptionNum());
			if (choice != 0) {
				Food f = Food.getOption(choice);
				foodList.add(f);
			}
			quit = !h.inputYesNo("Do you want to add more food? (y/n)");
		}
		return foodList;
	}

	// from https://www.geeksforgeeks.org/find-the-duration-of-difference-between-two-dates-in-java/
	private long findMinDifference(String startTime, String currentTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		long difference_In_Time = 0;
		long difference_In_Minutes = 0;
	    // Try Block
	    try {
	
	        // parse method is used to parse
	        // the text from a string to
	        // produce the date
	        Date d1 = sdf.parse(startTime);
	        Date d2 = sdf.parse(currentTime);
	
	        // Calculate time difference
	        // in milliseconds
	        difference_In_Time
	            = d2.getTime() - d1.getTime();
	
	        difference_In_Minutes
	            = (difference_In_Time
	               / (1000 * 60))
	              % 60;
	    }  // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
		return difference_In_Minutes;
	}


	private void getWorkMenu() {
		System.out.println("\nYou are at Work Menu");
		WorkMenu.printMenuOptions();
		// can't choose null
		int choice = h.inputInt("Choose an option by number", 1, InHomeMenu.getOptionNum()-1);
		WorkMenu option = WorkMenu.getOption(choice);
		if (option != WorkMenu.NULL) {
			String startTime = getCurrentTimeString();
			game.work(option, startTime);
		}
	}


	private String getCurrentTimeString() {
		String currentTime = "";
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
	    currentTime = formatter.format(date); 
		return currentTime;
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
			out.print(game.toFileString());
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
			out.print(game.getDogstoFileString());
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
			out.print(game.getHometoFileString());
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
