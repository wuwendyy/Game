package model.game;

import java.util.*;

public enum WorkMenu {
	
	A(2, 30, "Work A require 2h to finish and salary is 30"),
	B(5, 90, "Work B require 5h to finish and salary is 90"),
	C(8, 150, "Work C require 8h to finish and salary is 150"),
	NULL(0,0,"");
	
	private int duration; //time required to finish work
	private int salary;
	private String description;
	
	

	private WorkMenu(int duration, int salary, String description) {
		this.duration = duration;
		this.salary = salary;
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}


	public static WorkMenu getOption(int num) {
		return WorkMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Work Menu\t*****";
		int count = 0;
		for (WorkMenu m : WorkMenu.values()) { // array from the enum
			count++;
			if (count < getOptionNum()) {
				prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString();
			}
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}

	public static int getOptionNum() {
		return WorkMenu.values().length;
	}

}
