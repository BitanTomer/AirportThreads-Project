
public class TechnicalCrew extends Crew implements Runnable{
	
	private static int UniqueID = 1;
	private UnboundedBuffer <FlightDetails> ManagmentLine;
	private UnboundedBuffer <ArrivalFlight> TechnicalLine;
	private UnboundedBuffer <ArrivalFlight> SecuirityLine;
	private UnboundedBuffer <ArrivalFlight> LogisticsLine;

	//constructor
	public TechnicalCrew(int UniqueID,UnboundedBuffer <FlightDetails> ManagmentLine,UnboundedBuffer <ArrivalFlight> TechnicalLine,UnboundedBuffer <ArrivalFlight> SecuirityLine,UnboundedBuffer <ArrivalFlight> LogisticsLine) {
		super(UniqueID);
		this.ManagmentLine = ManagmentLine;
		this.TechnicalLine = TechnicalLine;
		this.SecuirityLine = SecuirityLine;
		this.LogisticsLine = LogisticsLine;
		TechnicalCrew.UniqueID++;
		// TODO Auto-generated constructor stub
	}
	
	//  take care the flight.
	public void Treatment(ArrivalFlight F) throws InterruptedException{
		long r = (long) (Math.random() * (5 - 3)) + 3;
		int x = (int)(Math.random() * (1000 - 500)) + 500;
		F.setCost(x);
		Thread.sleep(r * 1000);
		F.setSleepTime(r);
		if(F.getCameFrom().equals("FuelCrew")){
			Thread.sleep(1000);
			F.setSleepTime(1);
		}
	}
	public void run() {// start the thread
		// TODO Auto-generated method stub
		ArrivalFlight F;
		while(Crew.DayOver!=true){
		F = this.TechnicalLine.extract();
		if(F==null)
			break;
		try {
			Treatment(F);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findQueue(F);
		}
	}
	//Find where the Flight came from and give back to the next line
	public void findQueue(ArrivalFlight f){ 
		if(f.getCameFrom().equals("FuelCrew")){
			this.ManagmentLine.insert(new FlightDetails(f)); 
			
		}
		else{
		if(f.getCameFrom().equals("LogisticsCrew")){
			this.SecuirityLine.insert(f);
			
		}
		else{
		if(f.getCameFrom().equals("RunwayDirector")){
			this.LogisticsLine.insert(f);

		}
		}
		}
	}

}
