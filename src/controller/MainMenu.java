package controller;

public enum MainMenu {
	VIEW_GAME_INFO("Learn about Docorators"),
	PLAY("Start Game"),
	CLEAR_DATA("Clear current game data"),
	QUIT("Save data and quit Program");

	private String description;

	private MainMenu(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}

	public static int getOptionNum() {
		return MainMenu.values().length;
	}

	public static MainMenu getOption(int num) {
		return MainMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Main Menu\t*****";

		for (MainMenu m : MainMenu.values()) { // array from the enum
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
