
public class ArrivalFlight extends Flight{
	
	private int numOfLuggage;
	private String cameFrom;
	private boolean isSecurityIssue;
	private int cost;
	

	// constructor 
	public ArrivalFlight(String flightCode, int numOfPassengers,int Time,int numOfLuggage) { 
		super(flightCode, numOfPassengers,Time);
		this.numOfLuggage = numOfLuggage;
		this.cameFrom = null;
		this.isSecurityIssue=false;
		this.cost=0;
		// TODO Auto-generated constructor stub
	}

	public int getnumOfLuggage() {
		return numOfLuggage;
	}

	public void setnumOfLuggage(int numOfLuggage) {
		this.numOfLuggage = numOfLuggage;
	}

	public String getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(String cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	public boolean isSecurityIssue() {
		return isSecurityIssue;
	}

	public void setSecurityIssue(boolean isSecurityIssue) {
		this.isSecurityIssue = isSecurityIssue;
	}
	
	public int getCost() {
		return this.cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public double getSleepTime() {
		return super.getSleepTime();
		
	}
	public void setSleepTime(double sleepTime) {
		super.setSleepTime(sleepTime);
	}
	

}
