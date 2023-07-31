package model.furniture;

import java.util.*;

public class Camera {
	private boolean isOn; // always off for home without camera, can become on after buying camera
	private String infoRecorded;
	

	
	public Camera(boolean isOn, String infoRecorded) {
		super();
		this.isOn = isOn;
		this.infoRecorded = infoRecorded;
	}


	public static void main(String[] args){
	
		System.out.println("Hello, World!");
	
	}


	public boolean isOn() {
		return isOn;
	}


	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}


	public String getInfoRecorded() {
		return infoRecorded;
	}


	public void setInfoRecorded(String infoRecorded) {
		this.infoRecorded = infoRecorded;
	}
	
	
}
