package model.game;

import java.util.*;

import controller.InHomeMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

import model.dog.Breed;
import model.dog.BreedName;
import model.dog.Dog;
import model.furniture.Furniture;
import model.home.Home;
import model.home.Room;
import model.item.Food;
import model.item.Tool;
import model.item.Toy;
import view.Helper;

public class Game {

	private Random rand;
	private Set<Dog> dogs; // prone to change
	private Map<String, Dog> nameToDogMap;
	private Map<Integer, String> intToNameMap;
	private Home home; //prone to change
	private Shop shop; //fixed
	private int money; // change
	private Work work;
	private Helper h;
	private static final int maxDogNum = 5;
	
	//private time //work-exit program, come back determine if work finished,
	//total growth time = time spent on work (max = work duration),
	//when finished dog will not grow until enter home again and stay in home/work/walk dog
	
	// requirement of home increases as # dogs increases
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

	public Game(Set<Dog> dogs, Home home, int money, Work work) {
		super();
		this.dogs = dogs;
		this.home = home;
		this.money = money;
		this.shop = new Shop();
		this.work = work;
		this.h = new Helper();
		this.intToNameMap = createIntToNameMap(dogs);
		this.nameToDogMap = ceateNameToDogMap(dogs); // initialize int to name map as well
	}

	public int getNaxNumFurPerRoom() {
		return home.maxNumFurPerRoom();
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public void welcomeMessage() {
		System.out.println("Welcome to Docorators!\n"
				+ "This is a game to let players experience what raising dog(s) is like.\n"
				+ "You will live with your dog(s) in a sweet home, where you can place furniture,\n"
				+ "which must meet certain requirement for dog(s) to stay.\n"
				+ "You will need to work to earn money to buy food and necessities for your dog(s),\n"
				+ "The risk is that dog(s) can damage your furniture while you leave for work!\n"
				+ "This is why the game is called \"Docorators\" :\n"
				+ "Dog(s) plays the role as decorators (in a unconventional way) in your home!"
				+ "If you want to keep your cute dog(s), take the risk and loss!");
	}
	
	
	// modify furniture & items stored
	public void probabalisticDamageHappen(long timeOutside) {
		// if a considerate & no destructive characteristic doggie (power <=0) exists, result may be better
		// use list of dog breed & characteristics to determine
		boolean obstacle = false;
		// if a clever and destructive dog exists, result may be worse
		boolean helper = false;
		// loop through dogs to see if any exist
		
		if (timeOutside > 0) { //objectively have time to damage 
			for (Dog d : dogs) {
				home.damage(d, timeOutside, obstacle, helper);
			}
		}
	}

	public Set<Dog> getDogs() {
		return dogs;
	}
	
	public void showGeneralDogInfo() {
		if (dogs != null) {
			String dogsInfo = "";
			dogsInfo += dogs.size() + " dog(s) live in your home.\n";
			dogsInfo += "They are:\n";
			for (Dog d :dogs) {
				dogsInfo += d.getGeneralInfo() + "\n";
			}
			System.out.println(dogsInfo);
		}else {
			System.out.println("No dog live in your home.\n");
		}
	}

	
	public boolean furnitureBought(Furniture f, int i) {
		return home.buyFurniture(f, i);
	}
	
	public boolean FoodBought(Food f, int i) {
		return home.buyFood(f, i);
	}
	
	public boolean FoodBought(Toy t, int i) {
		return home.buyToy(t,i);
	}
	public void ToolBought() {
		
		
	}
	public Dog createDog() {
		Dog d = null;
		System.out.println("Enter dog info:");
		// choose dog (custom breed, name)
		System.out.println("\nAvailable breeds are:");
		BreedName.printMenuOptions();
		int c = h.inputInt("Choose an option by number", 1, BreedName.getOptionNum());
		BreedName breedName = BreedName.getOption(c);
		Breed breed = new Breed(breedName);
		String name = h.inputNonRepeatedWord("Give your dog a name:", getNameList());
		d = new Dog(name, breed);
		return d;
	}
	
	public void addSingleDog() {
		addDog(createDog());
	}
	
	public void addMultipleDogs(int restriction) {
		boolean quit = false;
		int i = 1;
		while (!quit && i <= restriction) {
			addSingleDog();
			i++;
			quit = h.inputBoolean("Have you finished with adding dogs?");
		}
	}

	
	public void addDog(Dog d) {
		this.dogs.add(d);
		this.nameToDogMap.put(d.getName(), d);
		this.intToNameMap.put(intToNameMap.size()+1, d.getName());
	}
	
	
	
	public void setDogs(Set<Dog> dogList) {
		//update dog set and also dog map
		this.dogs = dogList;
		this.nameToDogMap = ceateNameToDogMap(dogList);
		this.intToNameMap = createIntToNameMap(dogList);
	}
	
	
	
	private Map<String, Dog> ceateNameToDogMap(Set<Dog> dogList) {
		Map<String, Dog> map = new HashMap<>();
		for (Dog d : dogList) {
			map.put(d.getName(), d);
		}
		return map;
	}


	private Map<Integer, String> createIntToNameMap(Set<Dog> dogList) {
		Map<Integer, String> map = new HashMap<>();
		int i = 1;
		for (Dog d : dogList) {
			map.put(i, d.getName());
			i++;
		}
		return map;
	}




	public Home getHome() {
		return home;
	}


	public void setHome(Home home) {
		this.home = home;
	}

	


	public int getMoney() {
		return money;
	}



	public void setMoney(int money) {
		this.money = money;
	}

	public void spendMoney(int price) {
		setMoney(money-price);
	}


	public Work getWork() {
		return work;
	}



	public void setWork(Work work) {
		this.work = work;
	}




	public void showHomeInfo() {
		System.out.println(home.getHomeInfoString());
	}



	public void walkDog() {
		// consume dog evergy, increase mood
		for (Dog d : dogs) {
			d.walkDog();
		}
		
	}



	public void showCurrentDogInfo() {
		String dogsInfo = "";
		dogsInfo += "View the current physiological, emotional, and destructive power info:\n";
		for (Dog d :dogs) {
			dogsInfo += d.getCurrentInfo() + "\n";
		}
		System.out.println(dogsInfo);
	}



	public void feedDog(List<Food> foodList) {
		for (Food f : foodList) {
			// choose a dog to feed this food
			Dog dogChosen = chooseDog();
			dogChosen.consumeFood(f);
		}
	}



	public void playWithDog(Toy toy) {
		//choose dog/ not choose
		Dog dogChosen = chooseDog();
		// probabilistic stole by other dogs = change dog/ stored at something unreachable 
		dogChosen.playToy(toy);
	}



	private Dog chooseDog() {
		printDogMenu();
		int choice = h.inputInt("Choose a dog by number", 1, dogs.size());
		Dog option = getDogFromInt(choice);
		return option;
	}



	private Dog getDogFromInt(int choice) {
		Dog d = nameToDogMap.get(intToNameMap.get(choice));
		return d;
	}



	private void printDogMenu() {
		String menu = getDogMenu();
		System.out.println(getDogMenu());
	}



	private String getDogMenu() {
		String menu = "";
		for (Integer i : intToNameMap.keySet()) {
			// #. breedname name
			menu += i + ". " + nameToDogMap.get(intToNameMap.get(i)).getBreed().getName().getDisplayString() + " " +intToNameMap.get(i) + ".\n";
		}
		return menu;
	}



	public Set<String> getNameList() {
		// return the names of existing dogs
		Set<String> names = new HashSet<>();
		for (Dog d : dogs) {
			names.add(d.getName());
		}
		return names;
	}
	
	// run regularly as time goes
	public void checkCondition() {
		// check if home & care are good enough for dogs to live
		// basic: table, chair, bed are required
		if (home.getComfortLevel() < 19) {
			// give a 3 day grace period for buying furniture (don't know how to implement)
			System.out.println("home doesn't meet the standard for dog to live");
			// all dogs leave
			for (Dog d : dogs) {
				d.leave();
			}
			dogs = null; // need new initialization
		}
		
	}



	public void buyRoom() {
		if(home.buyRoom(money)) {
			money -= 300;
		}else {
			//fail
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
				success = furnitureBought((Furniture) obj, i);
			}
		}else if (obj instanceof Food){
			// match enum and update the variable num of that enum
			// update classes that has this obj
			success = canBuy(((Food) obj).getPrice());
			if (success) {
				price = ((Food) obj).getPrice();
				success = FoodBought((Food)obj, i);
			}
		}else if (obj instanceof Toy) {
			success = canBuy(((Toy) obj).getPrice());
			if (success){
				price = ((Toy) obj).getPrice();
				success = FoodBought((Toy)obj, i);
			}
		}else if (obj instanceof Tool) {
			success = canBuy(((Tool) obj).getPrice());
			price = ((Tool) obj).getPrice();
		}
		if (success) {
			spendMoney(price);
			System.out.println("Successful transaction. You have $" + money + " remaining.\n");
		}else {
			System.out.println("Transaction failed. You do not have enough money ($" + money + ".\n");
		}
		
	}
	
	private boolean canBuy(int price) {
		if (money - price >= 0) {
			return true;
		}else {
			return false;
		}
	}



	public void work(WorkMenu option, String startTime) {
		work = new Work(option, startTime);
		System.out.println("Leaves home and starts working at " + startTime + ".");
	}



	public long getTimeOutside(String currentTime) {
		if (work == null){ // didn't go out for work 
			return 0;
		}
		return findMinDifference(work.getStartTime(), currentTime);
	}
	

	public boolean getWorkState(String currentTime) {
		boolean isWorking = false;
		if (work == null) { // hasen't been working
			isWorking = false;
			System.out.println("Hasn't been outside working.");
		}else { // has been working 
			long timeOutside = getTimeOutside(currentTime);
			if (work.getWorkState(timeOutside)) { //finished work
				money += work.getWorkChosen().getSalary();
				isWorking = false;
			}else { // work not finished 
				isWorking = true;
			}
		}
		return isWorking;
	}
	
	// from https://www.geeksforgeeks.org/find-the-duration-of-difference-between-two-dates-in-java/
	private long findMinDifference(String startTime, String currentTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		long difference_In_Time = 0;
		long difference_In_Minutes = 0;
	    // Try Block
	    try {
	    	 System.out.println(startTime);
	    	 System.out.println(currentTime);
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
	    System.out.println(difference_In_Minutes);
		return difference_In_Minutes;
	}


	public char[] toFileString() {
		// TODO Auto-generated method stub
		return null;
	}


	public char[] getDogstoFileString() {
		// TODO Auto-generated method stub
		return null;
	}



	public char[] getHometoFileString() {
		// TODO Auto-generated method stub
		return null;
	}




}
