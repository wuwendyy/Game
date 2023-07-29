package controller;

public enum GameMenu {
	INITIALIZAZTION("1st time playing"), //choose dog, buy basic furniture and automatically enter home
	VIEW_HOME_INFO("View home information"),// only show info at the time left last time. Enter home to view newest info
	VIEW_DOG_INFO("View dog information"), 
	SHOP("Buy furniture, food, toy, and tool"),
	VIEW_WORK_STATE("View work state"), 
	ENTER_HOME("Enter home"),
	QUIT("Go back to main menu");

	private String description;

	private GameMenu(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}


	public static GameMenu getOption(int num) {
		return GameMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Game Menu\t*****";

		for (GameMenu m : GameMenu.values()) { // array from the enum
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
		return GameMenu.values().length;
	}

}
