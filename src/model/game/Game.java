package model.game;

import java.util.*;
import java.time.*;

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
	private Home home; //prone to change
	private Shop shop; //fixed
	private int money; // change
	private Work work;
	private Helper h;
	private static final int maxDogNum = 5;
	
	//private time //work-exit program, come back determine if work finished,
	//total growth time = time spent on work (max = work duration),
	//when finished dog will not grow until enter home again and stay in home/work/walk dog
	
	
	
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
	}

	public int getNaxNumFurPerRoom() {
		return home.maxNumFurPerRoom();
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public char[] toFileString() {
		// TODO Auto-generated method stub
		return null;
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
	public void probabalisticDamageHappen(int timeOutside) {
		// if a considerate & no destructive characteristic doggie (power <=0) exists, result may be better
		boolean obstacle = false;
		// if a clever and destructive doggie exists, result may be worse
		boolean helper = false;
		// loop through dogs to see if any exist
		
		if (timeOutside > 0) {
			for (Dog d : dogs) {
				d.damage(home, timeOutside, obstacle, helper);
			}
		}
	}

	public Set<Dog> getDogs() {
		return dogs;
	}
	
	public void showGeneralDogInfo() {
		String dogsInfo = "";
		dogsInfo += dogs.size() + " dog(s) live in your home.\n";
		dogsInfo += "They are:\n";
		for (Dog d :dogs) {
			dogsInfo += d.getGeneralInfo() + "\n";
		}
		System.out.println(dogsInfo);
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
	
	
	
	
	public void setDogs(Set<Dog> dogs) {
		this.dogs = dogs;
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



	public void showWorkState() {
		double timePointCalled = 0;
		System.out.println(work.getWorkStateString(timePointCalled));
		
	}


	public void showHomeInfo() {
		System.out.println(home.getHomeInfoString());
	}



	public Set<String> getExistRoomNames() {
		Set<String> set = home.getExistRoomNames();
		return set;
	}







}
