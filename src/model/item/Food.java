package model.item;

import java.util.*;

public enum Food {
	PURIFIED_WATER("purified water", 1,1,5),
	PUPPUCCINO("puppuccino", 5,3,1),
	MILK("milk",3,2,3),
	BISCUITS("biscuits", 5,6,0),
	KIBBLE("kibble", 3,5,0),
	FREEZE_DRIED("freeze fried", 8,7,0),
	FISH_OIL("fish oil", 1,1,0),
	TIN("tin", 9,7,1),
	APPLE("apple", 1,2,1),
	BANANA("banana", 1,2,1),
	STRAWBERRY("strawberry", 5,2,1),
	BLUEBERRY("blueberry",6,2,1),
	CABBAGE("cabbage", 2,3,0),
	CUCUMBER("cucumber", 2,3,1);
	
	private int price; // price per serving
	private int energy; //for dog
	private int waterReplenishment;
	private int num;
	private String description;

	
	//maybe add mood increase level
	private Food(String description, int price, int energy, int waterReplenishment) {
		this.description = description;
		this.price = price;
		this.energy = energy;
		this.waterReplenishment = waterReplenishment;
		this.num = 0;
	}
	
	
	
	public int getNum() {
		return num;
	}


	// updated when shopping
	public void setNum(int num) {
		this.num = num;
	}

	

	public int getPrice() {
		return price;
	}



	public int getEnergy() {
		return energy;
	}



	public int getWaterReplenishment() {
		return waterReplenishment;
	}



	public String getDescription() {
		return description;
	}



	public String getDisplayString() {
		return this.description;
	}
	
	public String getDisplayStringWithNum() {
		return this.description + "(" + num + ")";
	}
	
	public String getDisplayStringWithInfo() {
		return this.description + "( $" + price + "， E" + energy + ", W" + waterReplenishment + ")";
	}


	public static int getOptionNum() {
		return Food.values().length;
	}

	public static Food getOption(int num) {
		return Food.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Main Menu\t*****";

		for (Food m : Food.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayStringWithInfo();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
	
	
	
	public static List<Food> makeFoodList() {
		List<Food> food = new ArrayList<>();
		for (Food f : Food.values()) { 
		    food.add(f);
		}
		return food;
	}



	public String getNumberedName(int i) {
		return getDescription() + " #" + i ;
	}



	public static void printConsumeMenuOptions() {
		String prompt = getConsumeMenuOptions();
		System.out.println(prompt);
		
	}



	public static String getConsumeMenuOptions() {
		String prompt = "*****\t Docorators Toys In Storage Menu\t*****";

		for (Food f : Food.values()) { // array from the enum
			if (f.num > 0) {
				prompt += "\n" + (f.ordinal() + 1) + ": " + f.getDisplayStringWithNum();
			}
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}



	public static int getAvailableOptionNum() {
		int i = 0;
		for (Food f : Food.values()) {
			if (f.num > 0) {
				i++;
			}
		}
		return i;
	}



	public String toFileString() {
		String str = "";
		str += this.name();
		return str;
	}
}
