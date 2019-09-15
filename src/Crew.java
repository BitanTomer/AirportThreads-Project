
abstract class Crew {
	
	protected static boolean DayOver;
	private int UniqueID;

	public Crew(int UniqueID) {
		this.UniqueID = UniqueID;
		this.DayOver = false;
	}

	public boolean getDayOver() {
		return DayOver;
	}

	public void setDayOver(boolean dayOver) {
		DayOver = dayOver;
	}
	
	
}
