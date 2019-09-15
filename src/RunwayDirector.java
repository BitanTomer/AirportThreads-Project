
public class RunwayDirector implements Runnable{

	protected static boolean DayOver = false;
	private static int CountFlights=0;
	private int supposeToCome;
	private UnboundedBuffer<ArrivalFlight> ArrivalFlightsLine;
	private UnboundedBuffer <FlightDetails> ManagmentLine; 
	private UnboundedBuffer <ArrivalFlight> TechnicalLine;
	private UnboundedBuffer <ArrivalFlight> LogisticsLine;
	private UnboundedBuffer<DepartureFlight> DepartureFlightLine;
	
	//constructor
	public RunwayDirector(UnboundedBuffer<ArrivalFlight> ArrivalFlightsLine,UnboundedBuffer<FlightDetails> ManagmentLine,UnboundedBuffer <ArrivalFlight> TechnicalLine,UnboundedBuffer <ArrivalFlight> LogisticsLine,UnboundedBuffer<DepartureFlight> DepartureFlightLine,int supposeToCome) {
		this.ArrivalFlightsLine = ArrivalFlightsLine;
		this.ManagmentLine = ManagmentLine;
		this.TechnicalLine = TechnicalLine;
		this.LogisticsLine = LogisticsLine;
		this.DepartureFlightLine = DepartureFlightLine;
		this.supposeToCome = supposeToCome;
		
		
	}
	public void run() { // Start the thread
		while(RunwayDirector.DayOver!=true){
				extractFromQueue();
				isDayOver();
		}
		wakeAll();
	}
	
	public void wakeAll() // Wake all the thread when the day is over
	{
		ArrivalFlightsLine.setDayOver(true);
		DepartureFlightLine.setDayOver(true);
		ArrivalFlightsLine.wake();
		DepartureFlightLine.wake();
	}
	public void extractFromQueue(){ //Extract Flight from the line and start Direct her
		if(!this.ArrivalFlightsLine.isEmpty()){
			ArrivalFlight f = this.ArrivalFlightsLine.extract();
			if(f==null)
				return;
			process(f);
			updateData();
		}
		else{
		if(!this.DepartureFlightLine.isEmpty()){
			DepartureFlight f = this.DepartureFlightLine.extract();
			if(f==null)
				return;
			processDEP(f);
			this.ManagmentLine.insert(new FlightDetails(f));
			updateData();
		}
	}
	}
	

	private synchronized void updateData() // update all Data.
	{
		CountFlights++;
		isDayOver();
	}
	private synchronized static void setDayOver(boolean dayOver) {//update the class that day is over.
		DayOver = dayOver;
	}
	protected synchronized void isDayOver()
	{
		if(RunwayDirector.CountFlights==this.supposeToCome)
			RunwayDirector.setDayOver(true);
	}
	public void processDEP(DepartureFlight F){ // Do the process than you ask for Departure Flight
		long r = (long) (Math.random() * (5)) +5;
		try {
			Thread.sleep(r * 1000);
			F.setSleepTime(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void process(ArrivalFlight F){ // Do the process than you ask for Arrival Flight
		long r = (long) (Math.random() * (5)) +5;
		try {
			Thread.sleep(r * 1000);
			F.setSleepTime(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double x = Math.random();
		if(x <= 0.25){
			F.setCameFrom("RunwayDirector");
			this.TechnicalLine.insert(F);
			
		}
		else{
			this.LogisticsLine.insert(F);
		}

	}
	
	
}
