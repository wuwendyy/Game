package model.furniture;

import java.util.*;


// not counted as furniture, as one room only has one door, and can't store items
// not available in furniture shop, can only buy when buy a new room/upgrade
public class Door {
	boolean isOpen;
	boolean isLocked;
	Material material;
	

	
	public Door(boolean isOpen, boolean isLocked, Material material) {
		super();
		this.isOpen = isOpen;
		this.isLocked = isLocked;
		this.material = material;
	}

	
	public boolean isOpen() {
		return isOpen;
	}


	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}


	public boolean isLocked() {
		return isLocked;
	}


	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}


	public Material getMaterial() {
		return material;
	}


	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public String getMaterialString() {
		return material.getDisplayString();
	}
	
	public String getIsOpenString() {
		String state = "";
		if (isOpen) {
			state = "open";
		}else {
			state = "closed";
		}
		return state;
	}
	
	public String getIsLockedString() {
		String state = "";
		if (isLocked) {
			state = "locked";
		}else {
			state = "not locked";
		}
		return state;
	}
	

	public static void main(String[] args){

		System.out.println("Hello, World!");

	}
}

