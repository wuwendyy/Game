package controller;

import java.io.*;


import java.util.*;

import model.furniture.Camera;
import model.furniture.Door;
import model.furniture.Furniture;
import model.furniture.Material;
import model.home.Home;
import model.home.Room;
import model.item.*;



public class HomeReader {

	public static Home readHome(String homeFile) {
		Home home = null;
		try (FileInputStream fis = new FileInputStream(homeFile); Scanner sc = new Scanner(fis)) {
			// First: get each line and echo it.
			// sc.nextLine(); do not plan to have header
			if (sc.hasNextLine()) { 
				String line = sc.nextLine();
				// unknown # rooms. can be 0 if no next line
				Set<Room> rooms = new HashSet<>();
				Map<Room, List<Furniture>> roomToFurMap = new HashMap<>();
				while (sc.hasNextLine()) { //file ends without new lines!
					
					String s = sc.nextLine();
					Room room = parseRoom(s);
					if (room != null) {
						rooms.add(room);
						roomToFurMap.put(room, room.getFurnitureList());
					
					}else {
						System.out.println("Error reading a room.");
					}
				}
				home = new Home(rooms, roomToFurMap);
			}
			sc.close();
			// handle exception
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find file: " + e);
			// file name was bad....
			Scanner input = new Scanner(System.in);
			System.out.print("Enter new file name >"); // hard-coding (!MVC)
			homeFile = input.nextLine();
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO Exception Couldn't find file: " + e);
			e.printStackTrace();
		}
		return home;
	}
	
	private static Room parseRoom(String line) {
		// TODO Auto-generated method stub
		Room room = null;
		try {
			Scanner ls = new Scanner(line);
			ls.useDelimiter("/");
			String name = ls.next();
			boolean isOn = ls.nextBoolean();
			String record = ls.next();
			Camera cam = new Camera(isOn, record);
			int comfortLevel = ls.nextInt();
			Boolean isOpen = ls.nextBoolean();
			Boolean isLocked = ls.nextBoolean();
			Material material = Material.valueOf(ls.next());
			Door door = new Door(isOpen, isLocked, material);
			// a list of furniture
			List<Furniture> furniture = new ArrayList<>();
			Map<String, Integer> furnitureMap = new HashMap<>();
			String furnitureString = null;
			// no next stirng = no furniture
			while (ls.hasNext()){
				furnitureString = ls.next();
				Furniture f = parseFurniture(furnitureString);
				if (f != null) {
					furniture.add(f);
					if (!furnitureMap.containsKey(f.getName())) {
						furnitureMap.put(f.getName(), 1);
					}else {
						furnitureMap.put(f.getName(),furnitureMap.get(f.getName())+1);
					}
				}else {
					System.out.println("Error reading a furniture.");
				}
			}
			ls.close();
			room = new Room(name, door, furniture, comfortLevel, cam, furnitureMap);
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + line);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + line);
			e.printStackTrace();
		}
		return room;
	}
	
	private static Furniture parseFurniture(String line) {
		Furniture furniture = null;
		try {
			Scanner ls = new Scanner(line);
			ls.useDelimiter(",");
			String name = ls.next();
			int price = ls.nextInt();
			Material material = Material.valueOf(ls.next());
			boolean isOpen = ls.nextBoolean();
			int storeSpace = ls.nextInt();
			int spaceUsed = ls.nextInt();
		    boolean isDamaged = ls.nextBoolean();
		    String foodString = ls.next();
		    List<Food> food = parseFoods(foodString);
		    String toyString = ls.next();
		    List<Toy> toy = parseToys(toyString);
		    String toolString = ls.next();
		    List<Tool> tool = parseTools(toolString);
		    ls.close();
			furniture = new Furniture(name, price, material,isOpen, storeSpace, spaceUsed, isDamaged, food, toy, tool);
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + line);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + line);
			e.printStackTrace();
		}
		//System.out.println("furniture made");
		
		return furniture;
	}
	
	private static List<Tool> parseTools(String toolString) {
		List<Tool> tools = new ArrayList<>();
		if (!toolString.equals("NULL")) {
			if (toolString.contains("-")) {
				try {
					Scanner ls = new Scanner(toolString);
					ls.useDelimiter("-");
					String single = null;
					while (ls.hasNext()){
						single = ls.next();
						Tool t = parseTool(single);
						if (t != null) {
							tools.add(t);
						}else {
							System.out.println("error at multiple");
							System.out.println("Error reading tools.");
						}
					}
				    ls.close();
				} catch (InputMismatchException e) { // non-checked exception
					System.err.println("error happened while reading line: " + toolString);
					e.printStackTrace(); // show the other details
				} catch (NoSuchElementException e) {
					System.err.println("No such element reading line: (missing something)" + toolString);
					e.printStackTrace();
				}
			}else {
				Tool t = parseTool(toolString);
				if (t != null) {
					tools.add(t);
				}else {
					System.out.println("error at single");
					System.out.println("Error reading tools.");
				}
			}
		}
		return tools;
	}

	private static Tool parseTool(String next) {
		Tool tool = null;
		try {
			Scanner ls = new Scanner(next);
			ls.useDelimiter("\'");
			tool = Tool.valueOf(ls.next());
			tool.setNum(ls.nextInt());
			ls.close();
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + next);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + next);
			e.printStackTrace();
		}
		return tool;
	}

	private static List<Toy> parseToys(String toyString) {
		List<Toy> toys = new ArrayList<>();
		if (!toyString.equals("NULL")) {
			if (toyString.contains("-")) {
				try {
					Scanner ls = new Scanner(toyString);
					ls.useDelimiter("-");
					while (ls.hasNext()){
						Toy t = parseToy(ls.next());
						if (t != null) {
							toys.add(t);
						}else {
							System.out.println("Error reading toys.");
						}
					}
				    ls.close();
				} catch (InputMismatchException e) { // non-checked exception
					System.err.println("error happened while reading line: " + toyString);
					e.printStackTrace(); // show the other details
				} catch (NoSuchElementException e) {
					System.err.println("No such element reading line: (missing something)" + toyString);
					e.printStackTrace();
				}
			}else {
				Toy t = parseToy(toyString);
				if (t != null) {
					toys.add(t);
				}else {
					System.out.println("Error reading toys.");
				}
			}
		}
		return toys;
	}

	private static Toy parseToy(String next) {
		Toy toy = null;
		try {
			Scanner ls = new Scanner(next);
			ls.useDelimiter("\'");
			toy = Toy.valueOf(ls.next());
			toy.setNum(ls.nextInt());
			ls.close();
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + next);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + next);
			e.printStackTrace();
		}
		return toy;
	}

	private static List<Food> parseFoods(String foodString) {
		List<Food> foods = new ArrayList<>();
		if (!foodString.equals("NULL")) {
			if (foodString.contains("-")) {
				try {
					Scanner ls = new Scanner(foodString);
					ls.useDelimiter("-");
					while (ls.hasNext()){
						Food f = parseFood(ls.next());
						if (f != null) {
							foods.add(f);
						}else {
							System.out.println("Error reading foods.");
						}
					}
				    ls.close();
				} catch (InputMismatchException e) { // non-checked exception
					System.err.println("error happened while reading line: " + foodString);
					e.printStackTrace(); // show the other details
				} catch (NoSuchElementException e) {
					System.err.println("No such element reading line: (missing something)" + foodString);
					e.printStackTrace();
				}
			}else {
				Food f = parseFood(foodString);
				if (f != null) {
					foods.add(f);
				}else {
					System.out.println("Error reading foods.");
				}
			}
		}
		return foods;
	}

	private static Food parseFood(String next) {
		Food food = null;
		try {
			Scanner ls = new Scanner(next);
			ls.useDelimiter("\'");
			food = Food.valueOf(ls.next());
			food.setNum(ls.nextInt());
			ls.close();
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + next);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + next);
			e.printStackTrace();
		}
		return food;
	}

	public static void main(String[] args){

		HomeReader.readHome("src/file/default_home");
		System.out.println("success");
	}

	
}
