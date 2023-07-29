package model.dog;

import java.util.*;

import model.home.Home;

public class Dog {
	
	private String name; //dog's custom name
	private double age;
	private Breed breed; //include maxSize,max physiological levels, personalities
	
	private Size currentSize;
	private int currentEnergyLevel;
	private int currentWaterLevel;
	
	private int mood; //0-low 10-high
	private int friendship; // increase by good interaction & care, can decrease the effect of mood&physiological state of destructive power
	
	private int destructivePower; //(calculated from initialDP, age, mood, physiological state, currentSize, and friendship with player)
	
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
		info = name + " is a " + breed.getName().getDisplayString() + " of " + age + " years old, it is currently a " 
		+ currentSize.getDescription() + " size dog." + "it is " + breed.getCharacteristicsString() + "."; 
		return info;
	}

	public static void main(String[] args){
	
		System.out.println("Hello, World!");
	
	}
	
	// damage home within a given time period
	public void damage(Home home, int time, boolean obstacle, boolean helper) {
		//first update destructive power, depending on obstacle and helper
		destructivePower =  calculateDestructivePower();
		int totalNum = home.getTotalNumFur();
		int numFurDamaged = (int)destructivePower/50*totalNum;
		// randomly choose this number of furnitures to damage
		
	}
}

