
abstract class Flight implements Runnable{
	
	private String FlightCode;
	private int numOfPassengers;
	private long Time;
	private double sleepTime;
	private boolean needTruck = false;
	


	public Flight(String flightCode, int numOfPassengers,int Time) { // Constructor 
		this.FlightCode = flightCode;
		this.setNumOfPassengers(numOfPassengers);
		this.Time = Time;
		this.sleepTime=0;
	} 
	
	public void run(){ // Start the Thread
		try {
			Thread.sleep(Time*1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		
	}
	public String getFlightCode() {
		return this.FlightCode;
	}
	public void setFlightCode(String flightCode) {
		FlightCode = flightCode;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public long getTime() {
		return Time;
	}
	public void setTime(long x) {
		this.Time = this.Time + x;
	}
	public double getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(double sleepTime) {
		this.sleepTime =this.sleepTime+ sleepTime;
	}
	public boolean getNeedTruck() {
		return needTruck;
	}

	public void setNeedTruck(boolean needTruck) {
		this.needTruck = needTruck;
	}
	
}
