package controller;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import model.furniture.Door;
import model.furniture.Furniture;
import model.furniture.Material;
import model.home.Room;
import model.dog.*;


public class DogReader {

	public static Set<Dog> readDog(String dogFile) {
		Set<Dog> dogSet = new HashSet<>();
		try (FileInputStream fis = new FileInputStream(dogFile); Scanner sc = new Scanner(fis)) {
			// First: get each line and echo it.
			// sc.nextLine(); do not plan to have header
			while (sc.hasNextLine()) { // for files,keep going while there are still lines in the file
				String line = sc.nextLine();
				line = sc.nextLine(); // skip category line
				// parse this line into the Person object
				Dog d = parseDog(line);
				if (d != null) {
					dogSet.add(d); // add to people arraylist
				} else {
					System.out.println("Ignoring line of bad data: " + line);
				}
			}
			// handle exception
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find file: " + e);
			Scanner input = new Scanner(System.in);
			System.out.print("Enter new file name >"); // hard-coding (!MVC)
			dogFile = input.nextLine();
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO Exception Couldn't find file: " + e);
			e.printStackTrace();
		}
		return dogSet;
	}

	// parse dog from the whole dog info line
	public static Dog parseDog(String line) {
		// one line of data from dog file
		Dog dog = null;
		Breed breed = null;
		Size currentSize = null;
		try {
			Scanner ls = new Scanner(line);
			ls.useDelimiter("/");
			String name = ls.next();
			int age = ls.nextInt();
			int currentEL = ls.nextInt();
			int currentWL = ls.nextInt();
			int mood = ls.nextInt();
			int friendship = ls.nextInt();
			//size enum string
			currentSize = Size.valueOf(ls.next());
			// breed
			String breedString = ls.next();
			breed = parseBreed(breedString);
			dog = new Dog(name, age, breed, currentEL, currentWL, currentSize, mood, friendship);
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + line);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + line);
			e.printStackTrace();
		}
		return dog;
	}

	
	
	private static Breed parseBreed(String breedString) {
		Breed breed = null;
		try {
			Scanner ls = new Scanner(breedString);
			ls.useDelimiter(",");
			BreedName breedName = BreedName.valueOf(ls.next());
			Set<Characteristic> characteristics = parseCharacteristics(ls.next());
			ls.close();
			breed = new Breed(breedName, characteristics);
		} catch (InputMismatchException e) { // non-checked exception
			System.err.println("error happened while reading line: " + breedString);
			e.printStackTrace(); // show the other details
		} catch (NoSuchElementException e) {
			System.err.println("No such element reading line: (missing something)" + breedString);
			e.printStackTrace();
		}
		return breed;
	}

	private static Set<Characteristic> parseCharacteristics(String next) {
		Set<Characteristic> characteristics = new HashSet<>();
		if (next.contains("-")) {
			try {
				Scanner ls = new Scanner(next);
				ls.useDelimiter("-");
				while (ls.hasNext()){
					Characteristic p = Characteristic.valueOf(ls.next());
					if (p != null) {
						characteristics.add(p);
					}else {
						System.out.println("Error reading a characteristics.");
					}
				}
			} catch (InputMismatchException e) { // non-checked exception
				System.err.println("error happened while reading line: " + next);
				e.printStackTrace(); // show the other details
			} catch (NoSuchElementException e) {
				System.err.println("No such element reading line: (missing something)" + next);
				e.printStackTrace();
			}
		}else {
			Characteristic p = Characteristic.valueOf(next);
			if (p != null) {
				characteristics.add(p);
			}else {
				System.out.println("Error reading a characteristics.");
			}
		}
		return characteristics;
	}

	public static void main(String[] args) {
		
		DogReader.readDog("src/file/default_dog");
	}

}
