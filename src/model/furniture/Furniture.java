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
	private Map<String, Integer> itemNameToNumMap;
	
	private Map<Food, Integer> foodToNumMap; 
	private Map<Toy, Integer> toyToNumMap; 
	private Map<Tool, Integer> toolToNumMap; 


	 
	// use map to keep track of numbers or restrict # furniture to one for now?
	public Furniture(String name, int price, Material material, boolean isOpen, int storeSpace,
			int spaceUsed, boolean isDamaged, Map<Food, Integer> foodToNumMap, Map<Toy, Integer> toyToNumMap, Map<Tool, Integer> toolToNumMap) {
		super();
		this.name = name;
		this.price = price;
		this.material = material;
		this.isOpen = isOpen;
		this.storeSpace = storeSpace;
		this.spaceUsed = spaceUsed;
		this.isDamaged = isDamaged;
		this.foodToNumMap = foodToNumMap;
		this.toyToNumMap = toyToNumMap;
		this.toolToNumMap = toolToNumMap;
	}
	

	public Furniture(String name, int price, Material material, boolean isOpen, int storeSpace,
			int spaceUsed, boolean isDamaged) {
		super();
		this.name = name;
		this.price = price;
		this.material = material;
		this.isOpen = isOpen;
		this.storeSpace = storeSpace;
		this.spaceUsed = spaceUsed;
		this.isDamaged = isDamaged;
		this.foodToNumMap = new HashMap<>();
		this.toyToNumMap = new HashMap<>();
		this.toolToNumMap =  new HashMap<>();
		this.itemNameToNumMap = new HashMap<>();
	}
	
	public String getFurnitureShopString() {
		String info = "";
		info += name + " ($" + price + ", Storage Space: " + storeSpace + ")";
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
		if (!foodToNumMap.isEmpty()) {
			for (Food f : foodToNumMap.keySet()) {
				count++;
				if (count < foodToNumMap.size()) {
					storage += f.getDescription() + " (" + foodToNumMap.get(f) + "), ";
				}else {
					storage += f.getDescription() + " (" + foodToNumMap.get(f) + ")";
				}
				
			}
		}
		count = 0;
		storage+="\n\t\tToy:";
		if (!toyToNumMap.isEmpty()) {
			for (Toy t : toyToNumMap.keySet()) {
				count++;
				if (count < toyToNumMap.size()) {
					storage += t.getDescription() + " (" + toyToNumMap.get(t) + "), ";
				}else {
					storage += t.getDescription() + " (" + toyToNumMap.get(t) + ")";
				}
				
			}
		}
		count = 0;
		storage+= "\n\t\tTool:";
		if (!toolToNumMap.isEmpty()) {
			for (Tool s : toolToNumMap.keySet()) {
				count++;
				if (count < toolToNumMap.size()) {
					storage += s.getDescription() + " (" + toolToNumMap.get(s) + "), ";
				}else {
					storage += s.getDescription() + " (" + toolToNumMap.get(s) + ")";
				}
				
			}
		}
		return storage;
	}
	

	
	public boolean buyFood(Food f, int i) {
		if (spaceUsed < storeSpace) {
			if (foodToNumMap.containsKey(f)) {
					System.out.println( "key = " + f.name() + ", num = " + foodToNumMap.get(f));
				foodToNumMap.put(f, (Integer)foodToNumMap.get(f)+1);
			}else {
				foodToNumMap.put(f, (Integer)1);
			}
			spaceUsed++;
			System.out.print(f.getNumberedName(i) + " is bought and placed in a " + name);
			return true;
		}else {
			System.out.println(this.name + " is full. Please choose another furniture.\n");
			return false;
		}
	}
	
	public boolean buyToy(Toy t, int i) {
		if (spaceUsed < storeSpace) {
			if (toyToNumMap.containsKey(t.name())) {
				toyToNumMap.put(t, (Integer)toyToNumMap.get(t)+1);
			}else {
				toyToNumMap.put(t,(Integer)1);
			}
			spaceUsed++;
			System.out.print(t.getNumberedName(i) + " is bought and placed in a " + name);
			return true;
		}else {
			// choose other furniture
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




	public static void main(String[] args){
	
		System.out.println("Hello, World!");
	
	}

	public String getName(int i) {
		return this.getName() + " #" + i;
	}

	public String getNameWithSpaceLeft() {
		return name + " (Space Available: " + (storeSpace - spaceUsed) + ")";
	}

	public String toFileString() {
		String str = "";
		str += name + "," + price + "," + material.name() + "," + isOpen + "," + storeSpace + "," + spaceUsed
				 + "," + isDamaged + ",";
		int i = 1;
		if (foodToNumMap.size() > 0) {
			for (Food f : foodToNumMap.keySet()) {
					if (i < foodToNumMap.size()) {
						str += f.name() + "\'" + foodToNumMap.get(f)  + "-";
					}else {
						str += f.name() + "\'" + foodToNumMap.get(f) + ",";
					}
			}
		}else if (foodToNumMap.size() == 0) {
			str += "NULL" + ",";
		}
		i = 1;
		if (toyToNumMap.size() > 0) {
			for (Toy t : toyToNumMap.keySet()) {
					if (i < toyToNumMap.size()) {
						str += t.name() + "\'" + toyToNumMap.get(t)  + "-";
					}else {
						str += t.name() + "\'" + toyToNumMap.get(t) + ",";
					}
			}
		}else if (toyToNumMap.size() == 0) {
			str += "NULL" + ",";
		}
		i = 1;
		if (toolToNumMap.size() > 0) {
			for (Tool tl : toolToNumMap.keySet()) {
					if (i < toolToNumMap.size()) {
						str += tl.name() + "\'" + toolToNumMap.get(tl)  + "-";
					}else {
						str += tl.name() + "\'" + toolToNumMap.get(tl) + ",";
					}
			}
		}else if (toolToNumMap.size() == 0) {
			str += "NULL" + ",";
		}
		return str;
	}



}

