package model.furniture;

import java.util.*;

import model.functionality.*;
import model.item.*;

public class Furniture implements Purchasable, Sellable, Movable{
	
	private String name;
	private int price;
	Material material; 
	private boolean isOpen; // changed when things stored into/ taken out from wardrobe 
	private int storeSpace; // # items(food, toys) can be placed in/on this furniture
	private int spaceUsed;
	private boolean isDamaged;	
	
	private List<Food> food; 
	private List<Toy> toy; 
	private List<Tool> tool; 


	 
	// use map to keep track of numbers or restrict # furniture to one for now?
	public Furniture(String name, int price, Material material, boolean isOpen, int storeSpace,
			int spaceUsed, boolean isDamaged, List<Food> food, List<Toy> toy, List<Tool> tool) {
		super();
		this.name = name;
		this.price = price;
		this.material = material;
		this.isOpen = isOpen;
		this.storeSpace = storeSpace;
		this.spaceUsed = spaceUsed;
		this.isDamaged = isDamaged;
		this.food = food;
		this.toy = toy;
		this.tool = tool;
	}
	
	public String getFurnitureShopString() {
		String info = "";
		info += name + " ($" + price + ", S" + storeSpace + ")";
		return info;
	}
	
	public String getFurnitureString() {
		String info = "";
		info += name + " is " + getIsOpenString() + " and has storage space at most " + storeSpace + " items.\n";
		info += "\tStorage space has " + (storeSpace - spaceUsed) + " items left.\n";
		info += "\tIt is " + getIsDamagedString() + ".\n";
		info += "\tIt has stored items as listed below:";
		info += "\t" + getStorageString();
		return info;
	}
	
	// don't print items with #0
	public String getStorageString() {
		String storage = "";
		int count = 0;
		storage += "\n\t\tFood:";
		if (food != null) {
			for (Food f : food) {
				if (f.getNum() > 0) {
					count++;
					if (count < food.size()) {
						storage += f.getDisplayStringWithNum() + ", ";
					}else {
						storage += f.getDisplayStringWithNum();
					}
				}
			}
		}
		count = 0;
		storage+="\n\t\tToy:";
		if (toy != null) {
			for (Toy t : toy) {
				if (t.getNum() > 0) {
					count++;
					if (count < toy.size()) {
						storage += t.getDisplayStringWithNum() + ", ";
					}else {
						storage += t.getDisplayStringWithNum();
					}
				}
			}
		}
		count = 0;
		storage+= "\n\t\tTool:";
		if (tool != null) {
			for (Tool t : tool) {
				if (t.getNum() > 0) {
					count++;
					if (count < tool.size()) {
						storage += t.getDisplayStringWithNum() + ", ";
					}else {
						storage += t.getDisplayStringWithNum();
					}
				}
			}
		}
		return storage;
	}
	
	public boolean buyFood(Food f, int i) {
		if (spaceUsed < storeSpace) {
			food.add(f);
			f.setNum(f.getNum()+1);
			System.out.print(f.getNumberedName(i) + " is bought and placed in a " + name);
			return true;
		}else {
			System.out.println(this.name + " is full. Please choose another furniture.\n");
			return false;
		}
	}
	
	public boolean buyToy(Toy t, int i) {
		if (spaceUsed < storeSpace) {
			toy.add(t);
			t.setNum(t.getNum()+1);
			System.out.print(t.getNumberedName(i) + " is bought and placed in a " + name);
			return true;
		}else {
			System.out.println(this.name + " is full. Please choose another furniture.\n");
			return false;
		}
	}

	public String getIsOpenString() {
		String state = "";
		if (isOpen) {
			state = "open";
		}else {
			state = "closed";
		}
		return state;
	}
	

	public String getIsDamagedString() {
		String state = "";
		if (isDamaged) {
			state = "damaged";
		}else {
			state = "in good condition";
		}
		return state;
	}
	
	
	
	public int getPrice() {
		return price;
	}

	public Material getMaterial() {
		return material;
	}

	public int getStoreSpace() {
		return storeSpace;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isOpen() {
		return isOpen;
	}




	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}




	public int getSpaceUsed() {
		return spaceUsed;
	}




	public void setSpaceUsed(int spaceUsed) {
		this.spaceUsed = spaceUsed;
	}




	public boolean isDamaged() {
		return isDamaged;
	}




	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}




	public List<Food> getFood() {
		return food;
	}




	public void setFood(List<Food> food) {
		this.food = food;
	}




	public List<Toy> getToy() {
		return toy;
	}




	public void setToy(List<Toy> toy) {
		this.toy = toy;
	}




	public List<Tool> getTool() {
		return tool;
	}




	public void setTool(List<Tool> tool) {
		this.tool = tool;
	}



	public static void main(String[] args){
	
		System.out.println("Hello, World!");
	
	}

	public String getName(int i) {
		return this.getName() + " #" + i;
	}

	public String getNameWithSpaceLeft() {
		return name + " (" + (storeSpace - spaceUsed) + ")";
	}



}

