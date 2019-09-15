
public class FuelCrew extends Crew implements Runnable{
	
	private int capacity; 
	private int Currentcapacity;
	private static int UniqueID = 0;
	private BoundedBuffer<ArrivalFlight> FuelLine;
	private UnboundedBuffer <ArrivalFlight> TechnicalLine;
	private UnboundedBuffer <ArrivalFlight> ArrivalFlightsLine;
	private UnboundedBuffer <FlightDetails> ManagmentLine;

	//constructor 
	public FuelCrew(int capacity, BoundedBuffer<ArrivalFlight> FuelLine, UnboundedBuffer <ArrivalFlight> ArrivalFlightsLine,UnboundedBuffer <ArrivalFlight> TechnicalLine,UnboundedBuffer <FlightDetails> ManagmentLine) {
		super(UniqueID);
		this.capacity=capacity;
		this.FuelLine = FuelLine;
		this.TechnicalLine = TechnicalLine;
		this.ArrivalFlightsLine = ArrivalFlightsLine;
		this.ManagmentLine = ManagmentLine;
		UniqueID++;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() { // Start the thread.
		// TODO Auto-generated method stub
		ArrivalFlight F;
		while(Crew.DayOver!=true){
		F = this.FuelLine.extract();
		if(F==null)
			return;
		if(this.Currentcapacity >= 1000){
			Process(F);
		}
		else{
			this.FuelLine.insert(F);
			try {
				Thread.sleep(5000);
				Currentcapacity=capacity;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			F = this.FuelLine.extract();
			Process(F);
		}
		double x = Math.random();
		if(x <= 0.3){
			F.setCameFrom("FuelCrew");
			this.TechnicalLine.insert(F);
		}
		else{
			this.ManagmentLine.insert(new FlightDetails(F));
		}
		}
	}
	public synchronized void Process(ArrivalFlight F){ // Do all the process for the flight
			double x = Math.random() + 3;
			try {
				if(F==null)
					return;
				Thread.sleep((long)x * 1000);
				F.setSleepTime(x);
				this.Currentcapacity = Currentcapacity - 1000;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	
