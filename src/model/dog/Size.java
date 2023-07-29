package model.dog;

public enum Size {
	SMALL("small",5),
	MEDIUM("medium",10),
	BIG("big",15),
	GIANT("giant",20);
	
	// daily energy and water from food needed for dog of corresponding size 
	private int maxEnergyLevel;
	private int maxWaterLevel;
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	private Size(String description, int maxEnergyLevel) {
		this.description = description;
		this.maxEnergyLevel = maxEnergyLevel;
		this.maxWaterLevel = maxEnergyLevel;
	}

	public int getMaxEnergyLevel() {
		return maxEnergyLevel;
	}

	public void setMaxEnergyLevel(int maxEnergyLevel) {
		this.maxEnergyLevel = maxEnergyLevel;
	}

	public int getMaxWaterLevel() {
		return maxWaterLevel;
	}

	public void setMaxWaterLevel(int maxWaterLevel) {
		this.maxWaterLevel = maxWaterLevel;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
