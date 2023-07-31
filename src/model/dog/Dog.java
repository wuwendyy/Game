package model.dog;

import java.util.*;

import model.home.Home;
import model.item.Food;
import model.item.Toy;

public class Dog {
	

	private String name; //dog's custom name
	private double age;
	private Breed breed; //include maxSize,max physiological levels, personalities
	
	private Size currentSize;
	private int currentEnergyLevel;
	private int currentWaterLevel;
	
	private int mood; //0-low 10-high
	// affected by continuity of friendship 
	private int friendship; // increase by good interaction & care, can decrease the effect of mood&physiological state of destructive power
	
	private int destructivePower; //(calculated from initialDP, age, mood, physiological state, currentSize, and friendship with player)
	private Random rand;
	
	// read from file, customized
	public Dog(String name, double age, Breed breed, int currentEnergyLevel, int currentWaterLevel, Size currentSize,
			int mood, int friendship) {
		super();
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.currentEnergyLevel = currentEnergyLevel;
		this.currentWaterLevel = currentWaterLevel;
		this.currentSize = currentSize;
		this.mood = mood;
		this.friendship = friendship;
		this.destructivePower = calculateDestructivePower(age, breed.getInitialDP(), currentEnergyLevel, currentWaterLevel, currentSize, mood, friendship);
	}
	
	
	
	// used within constructor
	public int calculateDestructivePower(double age, int initialDP, int currentEnergyLevel, int currentWaterLevel,
			Size currentSize, int mood, int friendship) {
		int power = 0;
		//age ↑ => power ↓     （age - 20 max)
		//initial power ↑ => power ↑
		//currentEnergyLevel ↑ => power ↑
		//currentWaterLevel ↑ => power ↑
		//currentSize ↑ => power ↑
		//mood ↑ => power ↓
		//friendship ↑ => power ↓
		// simplified version: just +/-
		power += (-age) + initialDP + currentEnergyLevel + currentWaterLevel - mood - friendship;
				
		// within 0 - 100, convert to % when used 
		return power;
	}




	// used with an existing instance (re-enter home construct damage/check dog state when leaving)
	public int calculateDestructivePower() {
		int power = 0;
		power += (-age) + breed.getInitialDP() + currentEnergyLevel + currentWaterLevel - mood - friendship;
		return power;
	}

	// initialize a newly born dog
	public Dog(String name, Breed breed) {
		this.name = name;
		this.breed = breed;
		this.age = 0;
		this.currentSize = Size.SMALL;
		this.currentEnergyLevel = currentSize.getMaxEnergyLevel();
		this.currentWaterLevel = currentSize.getMaxWaterLevel();
		this.mood = 5;
		this.friendship = 0;
		this.destructivePower = breed.getInitialDP();
	}
	
	public String getGeneralInfo() {
		String info = "";
		info = name + " is a " + breed.getName().getDisplayString() + " of " + age + " years old, it is currently a " 
		+ currentSize.getDescription() + " size dog." + "it is " + breed.getCharacteristicsString() + "."; 
		return info;
	}
	
	public String getCurrentInfo() {
		String info = "";
		info = breed.getName().getDisplayString() + " " + name +  "'s current energy level is " + currentEnergyLevel
				+ ", current water replenishment level is " + currentWaterLevel
				+ ".\nIts mood is " + getMoodToString() 
				+ ", friendship with you is " + getFriendShipToString()
				+ ".\nCurrent destructive power is " + calculateDestructivePower() + ".";
		return info;
	}
	
	private String getFriendShipToString() {
		String friendship = "";
		if (this.friendship < 10) {
			friendship = "bad";
		}else if (this.friendship >= 10 && this.friendship <20){
			friendship = "medium";
		}else if ((this.friendship >= 20) ) {
			friendship = "good";
		}
		return friendship;
	}



	public String getMoodToString() {
		String mood = "";
		if (this.mood < 10) {
			mood = "low";
		}else if (this.mood >= 10 && this.mood <20){
			mood = "medium";
		}else if ((this.mood >= 20) ) {
			mood = "high";
		}
		return mood;
	}

	public static void main(String[] args){
	
		System.out.println("Hello, World!");
	
	}
	
	// damage home within a given time period
	public int damage(long timeOutside, boolean obstacle, boolean helper) {
		//first update destructive power, depending on obstacle and helper
		destructivePower =  calculateDestructivePower();
		return destructivePower;
	}
	
	public void walkDog() {
		currentEnergyLevel -= 4;
		currentWaterLevel -= 4;
		mood += 4;
		System.out.println(name + " is happy outside! Some energy and water in body are comsumed.");
	}
	
	public void leave() {
		// when physiological/psychological state are below certain level for some, leave the player for a better place
		System.out.println("Goodbye.");
		// need constant checking (not sure how to implement yet)
	}



	public void playToy(Toy toy) {
		System.out.println("dogname is playing toyname");
		// Probabilistic destroy toy based on destructive power & mood?
		if (destructivePower > 15) {
			// generate random number
			rand = new Random();
			double d = rand.nextDouble(); 
			// modify d with destructive power & mood? 
			// if num ~ 1 
			if (d < 0.5) {
				// call destroy
				System.out.println("Oops!" + name + " destroyed the toy " + toy.getDescription() + "!!");
				destroyToy(toy); // add the variable of # times toy is played & calculate level of abrasion is good but not currently considered
			}else {
				// don't call
			}
		}
		// maybe implement interaction design? throw and catch 
		
		// increase mood and friendship with player
		mood += toy.getMoodModifier();
		friendship += 3;
		// inform about the mood & relationship increase
		System.out.println("toyname increases dogname's mood by int, increases the friendship between dogname and you by int");
		// reduce energy & water replenishment
		currentEnergyLevel -= 1;
		currentWaterLevel -= 1;
		// inform about the physical state change
		System.out.println("dogname spend # energy and # water after playing with the toyname");
	}
	
	public void destroyToy(Toy toy) {
		toy.setNum(toy.getNum()-1);
	}
	
	public void consumeFood(Food food) {
		// probabilistic refuse to eat if mood is low
		if (mood < 5) {
			rand = new Random();
			double d = rand.nextDouble(); 
			if (d< 0.5) {
				//refuse
			}
		}
		System.out.println("successful consumption message");
		// increase mood and friendship with player
		mood += 2; // increase proportional to physical state?
		friendship += 2;
		System.out.println();
		// increase energy & water replenishment
		currentEnergyLevel += food.getEnergy();
		currentWaterLevel += food.getWaterReplenishment();
		food.setNum(food.getNum()-1);
	}
	
	public void sleep() {
		// increase energy
		
		// probabilistic woken up by other dogs
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getAge() {
		return age;
	}



	public void setAge(double age) {
		this.age = age;
	}



	public Breed getBreed() {
		return breed;
	}



	public void setBreed(Breed breed) {
		this.breed = breed;
	}



	public Size getCurrentSize() {
		return currentSize;
	}



	public void setCurrentSize(Size currentSize) {
		this.currentSize = currentSize;
	}



	public int getCurrentEnergyLevel() {
		return currentEnergyLevel;
	}



	public void setCurrentEnergyLevel(int currentEnergyLevel) {
		this.currentEnergyLevel = currentEnergyLevel;
	}



	public int getCurrentWaterLevel() {
		return currentWaterLevel;
	}



	public void setCurrentWaterLevel(int currentWaterLevel) {
		this.currentWaterLevel = currentWaterLevel;
	}



	public int getMood() {
		return mood;
	}



	public void setMood(int mood) {
		this.mood = mood;
	}



	public int getFriendship() {
		return friendship;
	}



	public void setFriendship(int friendship) {
		this.friendship = friendship;
	}



	public int getDestructivePower() {
		return destructivePower;
	}



	public void setDestructivePower(int destructivePower) {
		this.destructivePower = destructivePower;
	}




	
	
	
}

