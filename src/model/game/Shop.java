package model.game;

import java.util.*;

import model.furniture.Furniture;
import model.furniture.Material;
import model.item.*;

public class Shop {
	// not supposed to be changed by player
	private static final List<Furniture> furnitureList = makeFurnitureList();
	private static final List<Food> food = Food.makeFoodList();;
	private static final List<Toy> toy = Toy.makeToyList();;
	private static final List<Tool> tool = Tool.makeToolList();; //for cleaning
	private static final Map<String, Furniture> nameToFurMap = makeNameToFurMap();
	private static final Map<String, Integer> nameToPriceMap = makeNameToPriceMap();


	private static List<Furniture> makeFurnitureList() {
		List<Furniture> furnitureList = new ArrayList<>();
		// overload constructor
		Furniture woodChair = new Furniture("wood chair", 3, Material.WOOD, true, 3, 0, false);
		furnitureList.add(woodChair);
		Furniture plasticChair = new Furniture("plastic chair", 2, Material.PLASTIC, true, 3, 0, false);
		furnitureList.add(plasticChair);
		Furniture bed = new Furniture("bed", 15, Material.WOOD, true, 10, 0, false);
		furnitureList.add(bed);
		Furniture dogBed = new Furniture("dog bed", 2, Material.FABRIC, true, 4, 0, false);
		furnitureList.add(dogBed);
		Furniture procelainDogBowl = new Furniture("procelain dog bowl", 1, Material.PROCELAIN, true, 2, 0, false);
		furnitureList.add(procelainDogBowl);
		Furniture metalDogBowl = new Furniture("metal dog bowl", 2, Material.METAL, true, 2, 0, false);
		furnitureList.add(metalDogBowl);
		Furniture table = new Furniture("wood table",7, Material.WOOD, true, 6, 0, false);
		furnitureList.add(table);
		Furniture bureau = new Furniture("bureau", 12, Material.PLASTIC, false, 20, 0, false);
		furnitureList.add(bureau);
		Furniture woodSofa = new Furniture("wood sofa", 5, Material.WOOD, true, 7, 0, false);
		furnitureList.add(woodSofa);
		Furniture fabricSofa = new Furniture("fabric sofa", 5, Material.FABRIC, true, 5, 0, false);
		furnitureList.add(fabricSofa);
		Furniture procelainPottedPlant = new Furniture("plant in procelain pot", 2, Material.PROCELAIN, true, 1, 0, false);
		furnitureList.add(procelainPottedPlant);
		Furniture plasticPottedPlant = new Furniture("plant in plastic pot", 1, Material.PLASTIC, true, 1, 0, false);
		furnitureList.add(plasticPottedPlant);
		Furniture armChair = new Furniture("arm chair", 5, Material.FABRIC, true, 4, 0, false);
		furnitureList.add(armChair);
		Furniture TV = new Furniture("TV", 15, Material.LCD, false, 0, 0, false);
		furnitureList.add(TV);
		Furniture blanket = new Furniture("blanket", 1, Material.FABRIC, false, 0, 0, false);
		furnitureList.add(blanket);
		Furniture cushion = new Furniture("cusion", 1, Material.FABRIC, false, 0, 0, false);
		furnitureList.add(cushion);
		Furniture camera = new Furniture("camera", 15, Material.LCD, false, 0, 0, false);
		furnitureList.add(camera);
		return furnitureList;
	}
	
	

	public static Furniture matchStrToFur(String s) {
		Furniture f = nameToFurMap.get(s);
		return new Furniture(f.getName(), f.getPrice(), f.getMaterial(), f.isOpen(), f.getStoreSpace(), f.getSpaceUsed(), f.isDamaged());
	}
	

	private static Map<String, Furniture> makeNameToFurMap() {
		Map<String, Furniture> map = new HashMap<>();
		for (Furniture f : furnitureList) {
			map.put(f.getName(), f);
		}
		return map;
	}

	private static Map<String, Integer> makeNameToPriceMap() {
		Map<String, Integer> map = new HashMap<>();
		for (Furniture f : furnitureList) {
			map.put(f.getName(), f.getPrice());
		}
		return map;
	}
	
	public static int getPriceFromStr(String description) {
		int price =  nameToPriceMap.get(description);
		return price;
	}

	

	public static Map<String, Integer> getNametopricemap() {
		return nameToPriceMap;
	}

	public static List<Furniture> getFurniturelist() {
		return furnitureList;
	}



	public static Map<String, Furniture> getNametofurmap() {
		return nameToFurMap;
	}



	public static List<Furniture> getFurnitureList() {
		return furnitureList;
	}


	public List<Food> getFood() {
		return food;
	}

	public List<Toy> getToy() {
		return toy;
	}


	public List<Tool> getTool() {
		return tool;
	}


	public static void main(String[] args) {
		System.out.println(getPriceFromStr("bed"));
		
	}

	
}
