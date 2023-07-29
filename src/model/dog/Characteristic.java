package model.dog;

public enum Characteristic {
	// neutral (when combined with other personality may have different effect)
	CLEVER("clever",0),
	DUMB("dumb",0),
	TIMID("timid", 0),
	//decrease 
	STABLE("emotionally stable",-5),
	LOYAL("loyal",-5),
	DOCILE("docile",-5),
	//increase
	NAUGHTY("naughty",0),
	ENERGETIC("energetic", 10),
	HIGH_SPIRITED("high spirited",10),
	DISOBEDIENT("disobedient",10),
	STUBBURN("stubbern",10),
	SELF_CENTERED("self-centered",10);
	
	
	private String description;

	private int destructiveModifier;
	
	private Characteristic(String description, int adder) {
		this.description = description;
		this.destructiveModifier = adder;
	}
	

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getDestructiveModifier() {
		return destructiveModifier;
	}


	public void setDestructiveModifier(int destructiveModifier) {
		this.destructiveModifier = destructiveModifier;
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
		String prompt = "*****\t Docorators Main Menu\t*****";

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
