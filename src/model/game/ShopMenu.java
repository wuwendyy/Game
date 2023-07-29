package model.game;

public enum ShopMenu {
	BUY_FURNITURE("Buy furniture"),
	BUY_FOOD("Buy food"),
	BUY_TOY("Buy toy"),
	BUY_TOOL("Buy tool"),
	BUY_ROOM("Buy room"),
	FIX_DOOR("Fix door"),
	QUIT("Go back to game menu");
	
	
	private String description;

	private ShopMenu(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}

	public static int getOptionNum() {
		return ShopMenu.values().length;
	}

	public static ShopMenu getOption(int num) {
		return ShopMenu.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Shop Menu\t*****";

		for (ShopMenu m : ShopMenu.values()) { // array from the enum
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
