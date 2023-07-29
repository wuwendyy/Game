package model.furniture;

import model.game.Shop;

public enum FurnitureMenu {
	WOOD_CHAIR("wood chair"),
	PLASTIC_CHAIR("plastic chair"),
	BED("bed"),
	DOG_BED("dog bed"),
	PROCELAIN_DOG_BOWL("procelain dog bowl"),
	METAL_DOG_BOWL("metal dog bowl"),
	WOOD_TABLE("wood table"),
	BUREAU("bureau"),
	WOOD_SOFA("wood sofa"),
	FABRIC_SOFA("fabric sofa"),
	PROCELAIN_POTTED_PLANT("plant in procelain pot"),
	PLASTIC_POTTED_PLANT("plant in plastic pot"),
	ARM_CHAIR("arm chair"),
	TV("TV"),
	BLANKET("blanket"),
	CUSHION("cusion"),
	CAMERA("camera");
	
	private String description;


	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private FurnitureMenu(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return description + " ("+ Shop.getNametopricemap().get(description) +")";
	}

	public static int getOptionNum() {
		return FurnitureMenu.values().length;
	}

	public static FurnitureMenu getOption(int num) {
		return FurnitureMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Furniture Menu\t*****";

		for (FurnitureMenu m : FurnitureMenu.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + getInfoFromEnum(m);
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}
	
	private static String getInfoFromEnum(FurnitureMenu m) {
		return (Shop.matchStrToFur(m.getDescription())).getFurnitureShopString();
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
}
