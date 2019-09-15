
public class SecurityMan extends Crew implements Runnable{
	
	private String rank;
	private int workTime;
	private UnboundedBuffer <ArrivalFlight> SecuirityLine;
	private BoundedBuffer<ArrivalFlight> FuelLine;
	private static int UniqueID = 1;

	//constructor
	public SecurityMan(String rank,int workTime, UnboundedBuffer <ArrivalFlight> SecuirityLine,BoundedBuffer<ArrivalFlight> FuelLine) {
		super(SecurityMan.UniqueID);
		this.setRank(rank);
		this.workTime = workTime;
		this.SecuirityLine = SecuirityLine;
		this.FuelLine = FuelLine;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public void run() { //Start the Thread.
		// TODO Auto-generated method stub
		ArrivalFlight F;
		while(Crew.DayOver!=true){
		F = this.SecuirityLine.extract();
		if(F==null)
			break;
		F.setSecurityIssue(true);
		try {
			Thread.sleep(this.workTime * 1000);
			F.setSleepTime(this.workTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double Random = Math.random();
		if(Random <= 0.05){
			try {
				Thread.sleep(2000);
				F.setSleepTime(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.FuelLine.insert(F);
	}
		this.SecuirityLine.wake();
		
	}

}
