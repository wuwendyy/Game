package model.item;

import java.util.*;

import model.game.ShopMenu;

public enum Toy {
	CARROT("carrot",4),
	BALL("ball",2),
	HEMP_ROPE("hemp rope", 3),
	FRISBEE("frisbee", 5), //interaction
	PLUSH_FILLED_TOY("plush filled toy", 2);
	
	
	// durability later to add 
	private int price;
	private int moodModifier;
	private int num;
	private String description;


	private Toy(String description, int moodModifier) {
			this.price = moodModifier;
			this.moodModifier = moodModifier;
			this.description = description;
			this.num = 0;
	}
	
	
	
	
	public int getPrice() {
		return price;
	}




	public int getMoodModifier() {
		return moodModifier;
	}




	public String getDescription() {
		return description;
	}




	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getDisplayString() {
		return this.description;
	}
	
	public String getDisplayStringWithNum() {
		return this.description + "(" + num + ")";
	}

	public static int getOptionNum() {
		return Toy.values().length;
	}

	public static Toy getOption(int num) {
		return Toy.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Main Menu\t*****";

		for (Toy m : Toy.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
	
	
	public static List<Toy> makeToyList() {
		List<Toy> toy = new ArrayList<>();
		for (Toy t : Toy.values()) { 
		    toy.add(t);
		}
		return toy;
	}




	public String getNumberedName(int i) {
		return getDescription() + " #" + i;
	}

}
