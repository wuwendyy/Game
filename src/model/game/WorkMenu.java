package model.game;

import java.util.*;

public enum WorkMenu {
	
	A(2, 3, "Work A require 2 minutes to finish and salary is $3"),
	B(5, 9, "Work B require 5 minutes to finish and salary is $9"),
	C(8, 15, "Work C require 8 minutes to finish and salary is $15"),
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
			// do not print null
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
