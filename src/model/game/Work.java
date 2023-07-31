package model.game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Work {
	private WorkMenu workChosen;
	private String startTime;
	
	
	public Work(WorkMenu workChosen, String startTime) {
		super();
		this.workChosen = workChosen;
		this.startTime = startTime;
	}
	
	


	public String getStartTime() {
		return startTime;
	}




	public WorkMenu getWorkChosen() {
		return workChosen;
	}


	public void setWorkChosen(WorkMenu workChosen) {
		this.workChosen = workChosen;
	}

	
	public boolean getWorkState(long timeOutSide) {
		boolean finished = false;
		if (timeOutSide >= workChosen.getDuration()) { // finished
			finished = true;
			System.out.println("Work finished. Salary is $" + workChosen.getSalary() + ".");
		}else {
			System.out.println("Work in process:" + workChosen.getDisplayString()
			+ "\nTime remaining: " + (workChosen.getDuration() - timeOutSide) + " minutes.");
		}
		return finished;
	}

	

	
}
