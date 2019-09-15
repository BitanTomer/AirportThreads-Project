
public class FlightDetails {

	private String type;
	private String flightID;
	private String destination;
	private int numOfPass;
	private int numOfLugg;
	private int cost;
	private static int numOfSecIssue=0;
	private boolean isSecurityIssue;
	private double totaltime;
	private boolean needTruck;
	
	public FlightDetails(ArrivalFlight f){
		this.type="ArrivalFlight";
		this.flightID = f.getFlightCode();
		this.numOfPass = f.getNumOfPassengers();
		this.numOfLugg = f.getnumOfLuggage();
		this.isSecurityIssue = f.isSecurityIssue();
		if(f.isSecurityIssue()==true)
			numOfSecIssue++;
		this.totaltime=f.getSleepTime();
		this.cost=f.getCost();
	}// Arrival flight constructor
	
	
	public FlightDetails(DepartureFlight f){
		this.type="DepartureFlight";
		this.flightID = f.getFlightCode();
		this.numOfPass = f.getNumOfPassengers();
		this.destination = f.getDestination();
		this.totaltime=f.getSleepTime();
	}//Departure flight constructor
	


	public boolean isSecurityIssue(){
		return isSecurityIssue;
	}
	//Check if the flight had a Security issue.
	public int intIsSecurityIssue(){
		if(isSecurityIssue==true)
			return 1;
		
		else
			return 0;
	}
	public boolean getNeedTruck(){
		return this.needTruck;
	}
	public double gettotaltime(){
		return this.totaltime;
	}
	
	public String getFlightID(){
		return flightID;
	}
	
	public int getNumOfPass() {
		return numOfPass;
	}
	
	public int getNumOfLugg() {
		return numOfLugg;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public int getCost() {
		return cost;
	}


	public int getNumOfSecIssue() {
		return numOfSecIssue;
	}

	@Override
	public String toString() {
		return "FlightDetails [flightID=" + flightID + ", destination=" + destination + ", cost=" + cost
				+ "]";
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
