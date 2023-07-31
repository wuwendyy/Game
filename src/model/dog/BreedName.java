package model.dog;

import controller.MainMenu;

public enum BreedName {
	BORDER_COLLIE("border collie"),
	BEAGLE("beagle"),
	SAMOYED("samoyed");
//	GOLDEN_RETRIEVER("golden retriever"),
//	LABRADOR_RETRIEVER("labrador retriever"),
//	HUSKY("husky"),
//	DACHSHUND("duchshund"),
//	GERMAN_SHEPHERD_DOG("german sepherd dog"),
//	CORGI("corgi"),
//	SHIBA("shiba"),
//	SHELTIE("sheltie"),
//	SCHNAUZER("schnauzer"),
//	TERRIER("terrier");
	
	private String description;

	private BreedName(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}

	public static int getOptionNum() {
		return BreedName.values().length;
	}

	public static BreedName getOption(int num) {
		return BreedName.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Breed Menu\t*****";

		for (BreedName m : BreedName.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
}
