package model.home;

import java.util.*;

import model.dog.Dog;
import model.furniture.Camera;
import model.furniture.Door;
import model.furniture.Furniture;
import model.item.Food;
import model.item.Toy;
import view.Helper;

public class Home {
	private Set<Room> rooms;
	private int comfortLevel;
	private Map<Integer, String> intToNameMap;
	private Map<Integer, String> intToNameWithSpaceMap;
	private Map<String, Room> nameToRoomMap; // no duplicate name
	// to look up furnitures in a room
	private Map<Room, List<Furniture>> roomToFurMap;
	private Helper h;
	private static int maxRoomNum = 10;

	
	public Set<Room> getRooms() {
		return rooms;
	}


	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	
	public Home(Set<Room> rooms, Map<Room, List<Furniture>> roomToFurMap) {
		super();
		this.roomToFurMap = roomToFurMap;
		this.rooms = rooms;
		// need argument b/c the instance hasn't finished building yet
		this.comfortLevel = calculateComfortLevel(rooms);
		nameToRoomMap = createNameToRoomMap(rooms);
		intToNameMap = createIntToNameMap(rooms);
		intToNameWithSpaceMap = createIntToNameWithSpaceMap(rooms);
		h = new Helper();
	}


	private Map<Integer, String> createIntToNameMap(Set<Room> rooms) {
		Map<Integer, String> map = new HashMap<>();
		int i = 1;
		for (Room r : rooms) {
			map.put(i, r.getName());
			i++;
		}
		return map;
	}
	
	private Map<Integer, String> createIntToNameWithSpaceMap(Set<Room> rooms) {
		Map<Integer, String> map = new HashMap<>();
		int i = 1;
		for (Room r : rooms) {
			map.put(i, r.getName() + " (S" + r.getSpaceUsed() + ")");
			i++;
		}
		return map;
	}
	
	

	public Map<Integer, String> getIntToNameMap() {
		return intToNameMap;
	}
	
	public String getIntToRoomNameMapString() {
		String s = "";
		for (Integer i : intToNameMap.keySet()) {
			s += i.toString() + ". " + intToNameMap.get(i) + "\n";
		}
		return s;
	}
	
	public String getIntToRoomNameWithSpaceMapString() {
		String s = "";
		for (Integer i : intToNameWithSpaceMap.keySet()) {
			s += i.toString() + ". " + intToNameWithSpaceMap.get(i) + "\n";
		}
		return s;
	}
	
	public boolean buyToy(Toy t, int i) {
		System.out.println("\nChoose which room to place " + t.getNumberedName(i) + ":");
		System.out.println("Existing Rooms: ");
		System.out.println(getIntToRoomNameWithSpaceMapString());
		int roomOption = h.inputInt("Enter an int:", 1, rooms.size());
		return toyBought(roomOption, t, i);
	}

	private boolean toyBought(int roomNum, Toy t, int i) {
		// use roomNum to get the Room obj to be modified
		String roomName = intToNameMap.get(roomNum);
		System.out.println(t.getDescription() + " will be placed in " + roomName);
		Room room = nameToRoomMap.get(roomName);
		// individual room field (furnitureList, furnitureNumMap)
		return room.buyToy(t, i);
	}


	public boolean buyFood(Food f, int i) {
		System.out.println("\nChoose which room to place " + f.getNumberedName(i) + ":");
		System.out.println("Existing Rooms: ");
		System.out.println(getIntToRoomNameWithSpaceMapString());
		int roomOption = h.inputInt("Enter an int:", 1, rooms.size());
		return foodBought(roomOption, f, i);
	}
	
	
	private boolean foodBought(Integer roomNum, Food f, int i) {
		// use roomNum to get the Room obj to be modified
		String roomName = intToNameMap.get(roomNum);
		System.out.println(f.getDescription() + " will be placed in " + roomName);
		Room room = nameToRoomMap.get(roomName);
		// individual room field (furnitureList, furnitureNumMap)
		return room.buyFood(f, i);
	}


	public boolean buyFurniture(Furniture f, int i) {
		// choose which room to place furniture
		System.out.println("\nChoose which room to place " + f.getName(i) + ":");
		System.out.println("Existing Rooms: ");
		System.out.println(getIntToRoomNameWithSpaceMapString());
		int roomOption = h.inputInt("Enter an int:", 1, rooms.size());
		return furnitureBought(roomOption, f, i);
	}
	
	private boolean furnitureBought(Integer roomNum, Furniture fur, int i) {
		// use roomNum to get the Room obj to be modified
		String roomName = intToNameMap.get(roomNum);
		System.out.println(roomName);
		Room room = nameToRoomMap.get(roomName);
		// individual room field (furnitureList, furnitureNumMap)
		boolean success = room.boughtFur(fur, i);
		// room - fur map
		if (success) {
			roomToFurMap.put(room, room.getFurnitureList());
		}
		return success;
	}
	
	public void furnitureRemove(Integer roomNum, Furniture fur) {
		// use roomNum to get the Room obj to be modified
		String roomName = intToNameMap.get(roomNum);
		Room room = nameToRoomMap.get(roomName);
		System.out.println("damaged " + fur.getName() + " will be removed from " + roomName + ".\n");
		// individual room field (furnitureList, furnitureNumMap)
		room.removeFur(fur);
		// room - fur map
		roomToFurMap.put(room, room.getFurnitureList());
	}


	public void setIntToNameMap(Map<Integer, String> intToNameMap) {
		this.intToNameMap = intToNameMap;
	}

	private Map<String, Room> createNameToRoomMap(Set<Room> roomList) {
		Map<String, Room> map = new HashMap<>();
		for (Room r : roomList) {
			map.put(r.getName(), r);
		}
		return map;
	}


	private int calculateComfortLevel(Set<Room> roomList) {
		// to be modified
		int i = 0;
		for (Room r : roomList) {
			i += r.getComfortLevel();
		}
		return i;
	}

	

	public int getComfortLevel() {
		return comfortLevel;
	}


	public Map<String, Room> getNameToRoomMap() {
		return nameToRoomMap;
	}


	public void setNameToRoomMap(Map<String, Room> nameToRoomMap) {
		this.nameToRoomMap = nameToRoomMap;
	}


	public Map<Room, List<Furniture>> getRoomToFurMap() {
		return roomToFurMap;
	}


	public void setRoomToFurMap(Map<Room, List<Furniture>> roomToFurMap) {
		this.roomToFurMap = roomToFurMap;
	}


	public String getHomeInfoString() {
		String homeInfo = "";
		homeInfo += "Home has " + rooms.size() + " rooms:\n";
		
		int count = 1;
		for (Room r : rooms) {
			homeInfo += "\n" + count + "." +  r.getRoomInfoString() + "\n"; 
			count++;
		}
		return homeInfo;
	}


	public int getTotalNumFur() {
		int i = 0;
		for (Room r : rooms) {
			i += r.getNumFur();
		}
		return i;
	}


	public Set<String> getExistRoomNames() {
		Set<String> set = new HashSet<>();
		for (Room r : rooms){
			set.add(r.getName());
		}
		return set;
	}


	public int maxNumFurPerRoom() {
		return Room.getMaxNumFur();
	}


	public void damage(Dog d, long timeOutside, boolean obstacle, boolean helper) {
		// get percent to damage from dog destructive power (further modified by obstacle/helper)
		
		// get the random chance to damage or not (affected by dog characteristics, has obstacle/helper)
		
		// randomly choose the fixed percent of furniture to damage
		
		// wood door inside home can be damaged 
		
		
		// with location setting, the damage is restricted to the area the dog in (certain rooms)
		
		// if some dogs damage door/ open door, no location restriction
		
		// if door is locked, the other rooms are safe but the damage to that single room will be amplifided
		
	}


	// can't be undo. don't need delete room
	public boolean buyRoom(int money) {
		if (rooms.size() < maxRoomNum && money > 300) {
			Set<String> existRoomNames = getExistRoomNames();
			String roomName = h.inputNonRepeatedWord("Enter a name for the room", existRoomNames);
			Room r = new Room(roomName);
			rooms.add(r);
			roomToFurMap.put(r, r.getFurnitureList());
			nameToRoomMap.put(roomName, r);
			intToNameMap.put(rooms.size()+1, r.getName());
			intToNameWithSpaceMap.put(rooms.size()+1, r.getName() + " (S" + r.getSpaceUsed() + ")");
			return true;
		}else if (money > 300){
			System.out.println("You do not have enough money to buy a new room.");
			return false;
		}else if (rooms.size() < maxRoomNum) {
			System.out.println("You have exceeded max number of rooms to own");
			return false;
		}
		return false;
	}
	






}

