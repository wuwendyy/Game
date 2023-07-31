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
	private Map<String, Integer> stringToSpaceUsedMap;
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
		stringToSpaceUsedMap = createStringToSpaceUsedMap(rooms);
		h = new Helper();
	}

	private Map<String, Integer> createStringToSpaceUsedMap(Set<Room> rooms2) {
		Map<String, Integer> map = new HashMap<>();
		int i = 1;
		for (Room r : rooms) {
			map.put(r.getName(), + (Integer)r.getSpaceUsed());
			i++;
		}
		return map;
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

	private Map<Integer, String> createIntToNameWithSpaceMap() {
		Map<Integer, String> map = new HashMap<>();
		int i = 1;
		for (Room r : rooms) {
			map.put(i, intToNameMap.get((Integer)i)); //+ " (Space Used (max 6): " + stringToSpaceUsedMap.get(intToNameMap.get((Integer)i)) + ")");
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
		for (Integer i : createIntToNameWithSpaceMap().keySet()) {
			s += i.toString() + ". " + createIntToNameWithSpaceMap().get(i) + "\n";
		}
		return s;
	}

	public boolean buyToy(Toy t, int i) {
		Map<Room, Integer> map = new HashMap<>();
		for (Room r : rooms) {
			map.put(r, (Integer)r.availableSpace());
		}
		boolean allRoomFail = false;
		int fullNum = 0; // # rooms that are full
		for (Room m : rooms) {
			if (map.get(m) ==0) {
				fullNum++;
			}
		}
		if (fullNum == rooms.size()) {
			allRoomFail = true;
		}
		boolean success = false;
		while (!success && !allRoomFail) {
			System.out.println("\nChoose which room to place " + t.getNumberedName(i) + ":");
			System.out.println("Existing Rooms: ");
			System.out.println(getIntToRoomNameWithSpaceMapString());
			int roomOption = h.inputInt("Enter an int:", 1, rooms.size());
			// use roomNum to get the Room obj to be modified
			String roomName = intToNameMap.get(roomOption);
			System.out.println(t.getNumberedName(i) + " will be placed in " + roomName);
			Room room = nameToRoomMap.get(roomName);
			// individual room field (furnitureList, furnitureNumMap)
			success =  room.buyToy(t, i);
			map.put(room, map.get(room)-1);
			fullNum = 0; // # rooms that are full
			for (Room m : rooms) {
				if (map.get(m) ==0) {
					fullNum++;
				}
			}
			if (fullNum == rooms.size()) {
				allRoomFail = true;
			}
		}
		return success;
	}

//	public boolean helperBuyToy(Toy t, int i) {
//		System.out.println("\nChoose which room to place " + t.getNumberedName(i) + ":");
//		System.out.println("Existing Rooms: ");
//		System.out.println(getIntToRoomNameWithSpaceMapString());
//		int roomOption = h.inputInt("Enter an int:", 1, rooms.size());
//		// use roomNum to get the Room obj to be modified
//		String roomName = intToNameMap.get(roomOption);
//		System.out.println(t.getNumberedName(i) + " will be placed in " + roomName);
//		Room room = nameToRoomMap.get(roomName);
//		// individual room field (furnitureList, furnitureNumMap)
//		
//		return room.buyToy(t, i);
//	}

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
		System.out.println(f.getNumberedName(i) + " will be placed in " + roomName);
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
			homeInfo += "\n" + count + "." + r.getRoomInfoString() + "\n";
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
		for (Room r : rooms) {
			set.add(r.getName());
		}
		return set;
	}

	public int maxNumFurPerRoom() {
		return Room.getMaxNumFur();
	}

	public void damage(Dog d, long timeOutside, boolean obstacle, boolean helper) {
		// get percent to damage from dog destructive power (further modified by
		// obstacle/helper)

		// get the random chance to damage or not (affected by dog characteristics, has
		// obstacle/helper)

		// randomly choose the fixed percent of furniture to damage

		// wood door inside home can be damaged

		// with location setting, the damage is restricted to the area the dog in
		// (certain rooms)

		// if some dogs damage door/ open door, no location restriction

		// if door is locked, the other rooms are safe but the damage to that single
		// room will be amplifided

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
			intToNameMap.put(rooms.size() + 1, r.getName());
			// new room, 0 space used
			stringToSpaceUsedMap.put(r.getName(),0);
			return true;
		} else if (money > 300) {
			System.out.println("You do not have enough money to buy a new room.");
			return false;
		} else if (rooms.size() < maxRoomNum) {
			System.out.println("You have exceeded max number of rooms to own");
			return false;
		}
		return false;
	}

	public String getHomeToFileString() {
		String str = "";
		int i = 1;
		for (Room r : rooms) {
			if (i < rooms.size()) {
				str += r.toFileString() + "\n";
			} else {
				str += r.toFileString();
			}
		}
		return str;
	}

}
