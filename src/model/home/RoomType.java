package model.home;

import controller.MainMenu;

public enum RoomType {
	LIVINGROOM("living room"),
	BEDROOM("bedroom"),
	BATHROOM("bathroom"),
	KITCHEN("kitchen"),
	PLAYROOM("playroom"),
	BALCONY("belcony");
	

	private String description;

	private RoomType(String description) {
		this.description = description;
	}

	public String getDisplayString() {
		return this.description;
	}

	public static int getOptionNum() {
		return RoomType.values().length;
	}

	public static RoomType getOption(int num) {
		return RoomType.values()[num - 1];
	}

	public static String getMenuOptions() {
		String prompt = "*****\t Docorators Main Menu\t*****";

		for (RoomType m : RoomType.values()) { // array from the enum
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
