package controller;

public enum InHomeMenu {
	VIEW_STATE("View dog(s)'current states"),
	FEED("Give food or water"),
	PLAY("Play with dog"),
	WALK_DOG("Walk dog"), 
	// don't leave this menu, just fixed at the state of dog walking, 
	// hen finished come back to home
	WORK("Leave home and work for money"),
	QUIT("Go back to game menu");

	private String description;

	private InHomeMenu(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}

	

	public static InHomeMenu getOption(int num) {
		return InHomeMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Home Menu\t*****";

		for (InHomeMenu m : InHomeMenu.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}

	public static int getOptionNum() {
		return InHomeMenu.values().length;
	}

}
