package model.item;

import java.util.ArrayList;
import java.util.List;

//先不做打扫功能
public enum Tool {
	RAG("rag",3),
	TISSUE("tissue",1),
	PLASTIC_BAG("plastic bag",1);
	
	
	private int price;
	private int num;
	private String description;


	private Tool(String description, int price) {
			this.price = price;
			this.description = description;
			this.num = 0;
	}
	
	
	
	
	public int getPrice() {
		return price;
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
		return Tool.values().length;
	}

	public static Tool getOption(int num) {
		return Tool.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Main Menu\t*****";

		for (Tool m : Tool.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
	
	private Tool(int price) {
		this.price = price;
		this.num = 0;
	}

	public static List<Tool> makeToolList() {
		List<Tool> tool = new ArrayList<>();
		for (Tool t : Tool.values()) { 
		    tool.add(t);
		}
		return tool;
	}
	
}
