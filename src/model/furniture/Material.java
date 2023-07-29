package model.furniture;


public enum Material {
	//weaker material, higher comfort
	WOOD("wood", 6),
	METAL("metal", 5),
	PLASTIC("plastic",4),
	FABRIC("fabric",3),
	LCD("LCD",2), // for TV and camera
	PROCELAIN("procelain",1);
	
	
	private int durability; // time that dogs take to damage
	//private int comfortLevel; // how comfortable the furniture of certain material is
	private String description;
	
	public String getDisplayString() {
		return this.description;
	}
	
	private Material(String description, int durability) {
		this.description = description;
		this.durability = durability;
		//this.comfortLevel = 5 - durability;
	}
	
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public static int getOptionNum() {
		return Material.values().length;
	}

	public static Material getOption(int num) {
		return Material.values()[num - 1];
	}
	
	// not to be used?
	public static String getMenuOptions() {
		String prompt = "*****\t Materials \t*****";

		for (Material m : Material.values()) { // array from the enum
			prompt += "\n" + (m.ordinal() + 1) + ": " + m.getDisplayString()+ "'s durability: " + m.getDurability();
		}
		prompt += "\n**********************************************\n";
		return prompt;
	}

	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}

}
