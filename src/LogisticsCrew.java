
public class LogisticsCrew extends Crew implements Runnable{
	
	private int cargo;
	private static int UniqueID = 1;
	private UnboundedBuffer <ArrivalFlight> TechnicalLine;
	private UnboundedBuffer <ArrivalFlight> LogisticsLine;
	private UnboundedBuffer <ArrivalFlight> SecuirityLine;

	//Constructor 
	public LogisticsCrew(int cargo,UnboundedBuffer <ArrivalFlight> TechnicalLine,UnboundedBuffer <ArrivalFlight> LogisticsLine,UnboundedBuffer <ArrivalFlight> SecuirityLine) {
		super(UniqueID);
		this.cargo=cargo;
		this.TechnicalLine = TechnicalLine;
		this.LogisticsLine = LogisticsLine;
		this.SecuirityLine = SecuirityLine;
		LogisticsCrew.UniqueID++;
		// TODO Auto-generated constructor stub
	}
	// Check if the crew can carry the capacity of the luggage
	public synchronized void checkCapacity(ArrivalFlight F){
		if(this.cargo >= F.getnumOfLuggage()){
			int x = (F.getnumOfLuggage() / 10);
			try {
				Thread.sleep(x*1000);
				F.setSleepTime(x);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Thread.sleep(2000);
				F.setSleepTime(2);
				F.setNeedTruck(true);
				int y = (F.getnumOfLuggage() / 10);
				Thread.sleep(y * 1000);
				F.setSleepTime(y);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {// start the thread
		// TODO Auto-generated method stub
		ArrivalFlight F;
		while(Crew.DayOver!=true){
		F = this.LogisticsLine.extract();
		if(F==null)
			break;
		checkCapacity(F);
		double Random = Math.random();
		if(Random <= 0.1){
			F.setCameFrom("LogisticsCrew");
			this.TechnicalLine.insert(F);
		}
		else{
			this.SecuirityLine.insert(F);
		}
	}
	
	}
	

}
