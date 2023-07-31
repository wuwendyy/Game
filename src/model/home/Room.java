package model.home;

import java.util.*;

import model.furniture.Camera;
import model.furniture.Door;
import model.furniture.Furniture;
import model.furniture.Material;
import model.item.*;
import view.Helper;

public class Room {
	private String name;
	private Door door;
	private List<Furniture> furnitureList;
	private int comfortLevel;
	private Camera cam;
	// to look up # same type of furniture in the room
	// don't store furniture of # 0
	private Map<String, Integer> furnitureNumMap;
	private Map<Integer, Furniture> intToFurnitureMap;
	private Helper h;
	private static final int maxNumFur = 6;
	private int spaceUsed;
	
	
	public Room(String name, Door door, List<Furniture> furnitureList, int comfortLevel, Camera cam, Map<String, Integer> furnitureNumMap) {
		super();
		this.name = name;
		this.door = door;
		this.furnitureList = furnitureList;
		this.comfortLevel = comfortLevel;
		this.cam = cam;
		// if can't find key, its value is 0, do not store non-existing furniture
		this.furnitureNumMap = furnitureNumMap;
		this.h = new Helper();
		this.intToFurnitureMap = createIntToFurnitureMap(furnitureList);
		this.spaceUsed = furnitureList.size();
	}
	
	// for buying a new room
	public Room(String name) {
		this.name = name;
		this.door = new Door(false, false, Material.WOOD);
		furnitureList = new ArrayList<>();
		comfortLevel = 0;
		cam = null;
		furnitureNumMap = null;
		intToFurnitureMap = null;
		h = new Helper();
		spaceUsed = 0;
	}
	
	
	private Map<Integer, Furniture> createIntToFurnitureMap(List<Furniture> furnitureList) {
		Map<Integer, Furniture> map = new HashMap<>();
		int i = 1;
		for (Furniture f : furnitureList) {
			if (f.getSpaceUsed() < f.getStoreSpace()) {
				map.put(i, f);
				i++;
			}
		}
		return map;
	}
	
	public String getIntToFurnitureMapString() {
		String s = "";
		for (Integer i : intToFurnitureMap.keySet()) {
			s += i.toString() + ". " + intToFurnitureMap.get(i) + " " + intToFurnitureMap.get(i).getNameWithSpaceLeft() + "\n";
		}
		return s;
	}
	
	
	public boolean boughtFur(Furniture fur, int i) {
		if (spaceUsed > 0) {
			intToFurnitureMap.put(intToFurnitureMap.size()+1, fur);
			furnitureList.add(fur);
			if (!furnitureNumMap.containsKey(fur.getName())) {
				furnitureNumMap.put(fur.getName(), 1);
			}else {
				furnitureNumMap.put(fur.getName(),furnitureNumMap.get(fur.getName())+1);
			}
			// find fur obj !!
			this.intToFurnitureMap = createIntToFurnitureMap(furnitureList);
			System.out.println(fur.getName(i) + " is bought and placed in " + name);
			spaceUsed++;
			return true;
		}else {
			System.out.println("No space to put furniture in " + name + ". Please choose another room");
			return false;
		}
	}
	
	public void removeFur(Furniture fur) {
		int i = 0;
		for (Furniture f : furnitureList) {
			if (f.getName().equals(fur.getName()) && f.isDamaged() == true && i==0) {
				// not sure 
				furnitureList.remove(f);
				i++;
			}
		}
		this.intToFurnitureMap = createIntToFurnitureMap(furnitureList);
		furnitureNumMap.put(fur.getName(),furnitureNumMap.get(fur.getName())-1);
		
	}
	

	public int getComfortLevel() {
		return comfortLevel;
	}




	public Camera getCam() {
		return cam;
	}



	public void setCam(Camera cam) {
		this.cam = cam;
	}



	public Map<String, Integer> getFurnitureNumMap() {
		return furnitureNumMap;
	}



	public void setFurnitureNumMap(Map<String, Integer> furnitureNumMap) {
		this.furnitureNumMap = furnitureNumMap;
	}



	public static void main(String[] args){

		System.out.println("");

	}

	public String getName() {
		return name;
	}
	
	public String getNameWithSpaceUsed() {
		return name + " (" + spaceUsed + ")";
	}

	public void setName(String name) {
		this.name = name;
	}

	public Door getDoor() {
		return door;
	}

	public void setDoor(Door door) {
		this.door = door;
	}

	public List<Furniture> getFurnitureList() {
		return furnitureList;
	}

	public void setFurnitureList(List<Furniture> furnitureList) {
		this.furnitureList = furnitureList;
	}

	public String getRoomInfoString() {
		String roomInfo = "";
		roomInfo += name + " has a " + door.getMaterialString()
		+ " door that is " + door.getIsOpenString() + " and " + door.getIsLockedString()+ ".\n"
		+"Comfort level is " + comfortLevel + ".\n";
		//show each furniture storage
		roomInfo += "It has the following list of furniture:";
		int i = 1;
		for (Furniture f : furnitureList) {
			roomInfo += "\n\t" + i + ". " + f.getFurnitureString();
			i++;
		}
		return roomInfo;
	}

	public int getNumFur() {
		return furnitureList.size();
	}


	// call by Home
	public boolean buyFood(Food f, int i) {
		// choose which furniture to place food
		boolean success = false;
		int storageAllFur = getStorageSpaceFromAllFur();
		if(storageAllFur > 0) {
			System.out.println("\nChoose which furniture to place " + f.getNumberedName(i) + ":");
			System.out.println("Existing Furniture available for storage: ");
			System.out.println(getIntToFurnitureMapString());
			int furnitureOption = h.inputInt("Enter an int:", 1, furnitureList.size());
			Furniture furnitureChosen = intToFurnitureMap.get(furnitureOption);
			success = furnitureChosen.buyFood(f, i);
			System.out.println(" in " + name + ".");
		}else {
			System.out.println("No furniture in this room has space available for storage. Choose another room.");
			success = false;
		}
		return success;
	}

	public boolean buyToy(Toy t, int i) {
		// choose which furniture to place food
		boolean success = false;
		int storageAllFur = getStorageSpaceFromAllFur();
		if(storageAllFur > 0) {
			System.out.println("\nChoose which furniture to place " + t.getNumberedName(i) + ":");
			System.out.println("Existing Furniture available for storage: ");
			System.out.println(getIntToFurnitureMapString());
			int furnitureOption = h.inputInt("Enter an int:", 1, furnitureList.size());
			Furniture furnitureChosen = intToFurnitureMap.get(furnitureOption);
			success = furnitureChosen.buyToy(t, i);
			System.out.println(" in " + name + ".");
		}else {
			System.out.println("No furniture in this room has space available for storage. Choose another room.");
			success = false;
		}
		return success;
	}

	private int getStorageSpaceFromAllFur() {
		int i = 0;
		for (Furniture f : furnitureList) {
			i += f.getStoreSpace() - f.getSpaceUsed();
		}
		return i;
	}

	public int getSpaceUsed() {
		return spaceUsed;
	}

	public void setSpaceUsed(int spaceUsed) {
		this.spaceUsed = spaceUsed;
	}

	public static int getMaxNumFur() {
		return maxNumFur;
	}

	public String toFileString() {
		String str = "";
		str += name + "/" + cam.isOn() + "/" + cam.getInfoRecorded() + "/" + 
		comfortLevel + "/" + "/" + door.isOpen() + "/" + door.isLocked() + "/" + door.getMaterial().name();
		for (Furniture f : furnitureList){
			if (furnitureNumMap.get(f.getName()) != 0) {
				str += "/" + f.toFileString();
			}
		}
		
		return str;
	}

	public Integer availableSpace() {
		int i = 0;
		for (Furniture f : furnitureList) {
			i += f.getStoreSpace() - f.getSpaceUsed();
		}
		return (Integer) i;
	}
	
	
	
}

