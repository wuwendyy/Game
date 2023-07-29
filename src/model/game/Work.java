package model.game;

public class Work {
	private boolean isWorking; // working or not working
	private WorkMenu workChosen;
	private double timePointLeft; 
	
	
	public Work(boolean workState, WorkMenu workChosen) {
		super();
		this.isWorking = workState;
		this.workChosen = workChosen;
		this.timePointLeft = 0; // modified by time management system
	}


	public boolean isWorking() {
		return isWorking;
	}


	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}


	public WorkMenu getWorkChosen() {
		return workChosen;
	}


	public void setWorkChosen(WorkMenu workChosen) {
		this.workChosen = workChosen;
	}


	public double getTimeLeft() {
		return timePointLeft;
	}


	public void setTimeLeft(double timeLeft) {
		this.timePointLeft = timeLeft;
	}
	
	
	
	public String getWorkStateString(double timePointCalled) {
		String state = "";
		if (isWorking) {
			state = "Work in process:" + workChosen.getDisplayString();
			state += "\nTime remaining: " + (timePointCalled - timePointLeft) + " unit.";
		}else {
			state = "Not working";
		}
		return state;
	}
	
}
