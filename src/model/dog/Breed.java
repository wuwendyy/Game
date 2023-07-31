package model.dog;

import java.util.HashSet;
import java.util.Set;

import model.game.ShopMenu;

public class Breed {
	
	private BreedName name; // breed name 
	// size can change as age increases, stop at max size
	private Size maxSize;
	private Set<Characteristic> characteristics; // probabalistic, can change with age
	private int initialDP; //initial destructive power

	
	// used when reading from file. characteristics may have been customized
	public Breed(BreedName name, Set<Characteristic> characteristics) {
		super();
		this.name = name;
		this.maxSize = setSize(name);
		this.characteristics = characteristics;
		this.initialDP = calculateIDP(characteristics); // calculated from characteristics (stable breed characteristic)
	}
	
	// used only when choosing breed
	public Breed(BreedName name) {
		super();
		this.name = name;
		this.maxSize = setSize(name);
		this.characteristics = generateCharacteristics(name);
		this.initialDP = calculateIDP(characteristics); // calculated from characteristics (stable breed characteristic)
	}
	
	
	private Set<Characteristic> generateCharacteristics(BreedName breedName) {
		Set<Characteristic> set = new HashSet<>();
			switch (breedName) {
			case BORDER_COLLIE:
				// ideally based on probability of a pool of common characteristics for this breed
				// for now just fix for all dogs of this breed
				set.add(Characteristic.CLEVER);
				set.add(Characteristic.TIMID);
				set.add(Characteristic.ENERGETIC);
				set.add(Characteristic.SELF_CENTERED);
				break;
			case BEAGLE:
				set = new HashSet<>();
				set.add(Characteristic.DISOBEDIENT);
				set.add(Characteristic.SELF_CENTERED);
				set.add(Characteristic.ENERGETIC);
				set.add(Characteristic.HIGH_SPIRITED);
				break;
			case SAMOYED:
				set.add(Characteristic.DUMB);
				set.add(Characteristic.NAUGHTY);
				set.add(Characteristic.TIMID);
				set.add(Characteristic.LOYAL);
				set.add(Characteristic.HIGH_SPIRITED);
				break;
			}		
		return set;
	}


	private int calculateIDP(Set<Characteristic> characteristics) {
			int i = 0;
			for (Characteristic p : characteristics) {
				i += p.getDestructiveModifier();
			}
			return 0;
	}
	
	private Size setSize(BreedName breedName) {
		Size s = null;
		switch (breedName) {
		case BORDER_COLLIE:
			s = Size.MEDIUM;
			break;
		case BEAGLE:
			s = Size.MEDIUM;
			break;
		case SAMOYED:
			s = Size.MEDIUM;
			break;
		}
		return s;
	}

	

	public BreedName getName() {
		return name;
	}

	public void setName(BreedName name) {
		this.name = name;
	}

	public Size getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Size maxSize) {
		this.maxSize = maxSize;
	}
	
	


	public Set<Characteristic> getCharacteristics() {
		return characteristics;
	}


	public void setCharacteristics(Set<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}


	public int getInitialDP() {
		return initialDP;
	}

	public void setInitialDP(int initialDP) {
		this.initialDP = initialDP;
	}

	public String getCharacteristicsString() {
		String info = "";
		int i = 1;
		for (Characteristic c : characteristics) {
			if (i < characteristics.size()) {
				info += c.getDisplayString() + ", ";
			}else {
				info += c.getDisplayString();
			}
			i++;
		}
		return info;
	}
	
	public String getCharacteristicToFileString() {
		String info = "";
		int i = 1;
		for (Characteristic c : characteristics) {
			if (i < characteristics.size()) {
				info += c.name() + "-";
			}else {
				info += c.name();
			}
			i++;
		}
		return info;
	}
	
	
}
