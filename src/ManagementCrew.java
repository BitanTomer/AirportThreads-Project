import java.sql.*;


public class ManagementCrew extends Crew implements Runnable{

	private static int supposeToCome;
	private static int CurrentNumOfArrival=0;
	private static int UniqueID = 1;
	private UnboundedBuffer <FlightDetails> ManagmentLine;// Line Flights for management
	private  UnboundedBuffer <ArrivalFlight> TechnicalLine;// Line Flights for Technical
	private  UnboundedBuffer <ArrivalFlight> SecuirityLine;// Line Flights for security
	private  UnboundedBuffer <ArrivalFlight> LogisticsLine;// Line Flights for Logistics
	private  BoundedBuffer<ArrivalFlight> FuelLine; // Line Flights for Fuel
	private DataBase DataBase;
	private static int numOfPass=0; 
	private static int numOfLugg=0;
	private static int TotalCost=0;
	private static int numOfSecurity=0;
	private static int numOfTruck=0;
	private static int numOffuel=0;
	
	//constructor
	public ManagementCrew(int supposeToCome,UnboundedBuffer <FlightDetails> ManagmentLine, UnboundedBuffer<ArrivalFlight> technicalLine, BoundedBuffer<ArrivalFlight> fuelLine, UnboundedBuffer<ArrivalFlight> secuirityLine, UnboundedBuffer<ArrivalFlight> logisticsLine) {
		super(UniqueID);
		this.DataBase = new DataBase();
		this.DataBase.createTables("Landings", "Takeoffs", true);
		ManagementCrew.supposeToCome = supposeToCome;
		this.ManagmentLine = ManagmentLine;
		this.TechnicalLine = technicalLine;
		this.SecuirityLine = secuirityLine;
		this.LogisticsLine = logisticsLine;
		this.FuelLine = fuelLine;
		ManagementCrew.UniqueID++;
	
		
		// TODO Auto-generated constructor stub
	}
	//Update the data and print the time and cost for the flight
	public void  manageFlight(FlightDetails  F)
	{
		updateData(F);
		System.out.println("Flight unique Code:"+ F.getFlightID());
		System.out.println("Total time in airport:"+ F.gettotaltime());
		System.out.println("Cost:"+F.getCost());
		CurrentNumOfArrival++;
		isDayOver();
		
	}
	private void printRiport() { // Print the report for the day.
		// TODO Auto-generated method stub
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("~~~~~~~~AirlineReport:~~~~~~~~");
		System.out.println("Total Passengers: " +numOfPass);
		System.out.println("Total Luggage Amount: " +numOfLugg);
		System.out.println("Total Cost: " +TotalCost);
		System.out.println("Total Fuel Amount: " +numOffuel);
		System.out.println("Total Suspicious Object: " +numOfSecurity);
		System.out.println("Total Special Cargo Truck: " +numOfSecurity);
		

	}
	public static synchronized boolean isDayOver() { //Check if the number of income Flights equal to number of Suppose to come
		// TODO Auto-generated method stub
		if(supposeToCome == CurrentNumOfArrival){
			Crew.DayOver = true;
		}
		return DayOver;
	}
	public void updateData(FlightDetails  F)// Update the Sums and insert to the Data Base
	{
		calculateSums(F);
		insertFlight(F);
	}
	
	private void insertFlight(FlightDetails f){ // insert flight to Data base.
		if(f.getType().compareTo("ArrivalFlight")==0){
			DataBase.insertToTable("Landings", f);
		}
		else
		{
			DataBase.insertToTable("Takeoffs", f);
		}
		}
		

	private void calculateSums(FlightDetails  F)// summarize the numbers of passengers and luggage
	{
		numOfPass=numOfPass+F.getNumOfPass();
		numOfLugg=numOfLugg+F.getNumOfLugg();
		TotalCost=TotalCost+F.getCost();
		if(F.isSecurityIssue())
			numOfSecurity++;
		if(F.getNeedTruck())
			numOfTruck++;
		if(F.getType()=="ArrivalFlight")
			numOffuel=numOffuel+1000;
			
			
	}
	
	public void run() { //Start the threading 
		// TODO Auto-generated method stub
		FlightDetails f;
		while(Crew.DayOver!=true){
		f = this.ManagmentLine.extract();
		if(f==null)
			break;
		manageFlight(f);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		wakeAllThreads();
		printRiport();
		
	}
	
	private void wakeAllThreads() // Wake all the thread when the day is over.
	{
		TechnicalLine.setDayOver(true);
		TechnicalLine.wake();
		SecuirityLine.setDayOver(true);
		SecuirityLine.wake();
		LogisticsLine.setDayOver(true);
		LogisticsLine.wake();
		FuelLine.setDayOver(true);
		FuelLine.wake();
		ManagmentLine.setDayOver(true);
		ManagmentLine.wake();
		
	}
}
