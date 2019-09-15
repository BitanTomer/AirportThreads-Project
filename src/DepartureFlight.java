
public class DepartureFlight extends Flight{
	
	private String Destination;

	//constructor
	public DepartureFlight(String flightCode, int numOfPassengers,int Time,String Destination) {
		super(flightCode, numOfPassengers,Time);
		this.Destination = Destination;
		// TODO Auto-generated constructor stub
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String Destination) {
		this.Destination = Destination;
	}
	
	public double getSleepTime() {
		return super.getSleepTime();
		
	}
	public void setSleepTime(double sleepTime) {
		super.setSleepTime(sleepTime);
	}
	
	

}
