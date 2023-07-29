package controller;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import model.dog.Dog;
import model.dog.Size;
import model.game.Game;
import model.game.Work;
import model.game.WorkMenu;
import model.home.*;

public class GameReader {
	
	
	public static Game readGame(String gameFile, String dogFile, String homeFile){
		Game game = null;
		try (FileInputStream fis = new FileInputStream(gameFile); Scanner sc = new Scanner(fis)) {
			// First: get each line and echo it.
			// sc.nextLine(); do not plan to have header
			if (sc.hasNext()) { // empty file if not played before
				Home home = HomeReader.readHome(homeFile);
				Set<Dog> dogs = DogReader.readDog(dogFile);
				int money = 0;
				Work work = null;
				if (sc.hasNextLine()) { // for files,keep going while there are still lines in the file
					String line = sc.nextLine();
					line = sc.nextLine(); // skip category line
					Scanner ls = new Scanner(line);
					ls.useDelimiter("/");
					money = ls.nextInt();
					boolean isWorking = ls.nextBoolean();
					WorkMenu workChosen = WorkMenu.valueOf(ls.next());
					work = new Work(isWorking, workChosen);
				}
				game = new Game(dogs, home, money, work);
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
		return game;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
