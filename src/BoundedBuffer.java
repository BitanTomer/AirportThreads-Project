import java.util.Vector;

public class BoundedBuffer<T>{

	private Vector<T> vector;
	private final int maxSize = 8;
	private boolean DayOver = false;
	
	public BoundedBuffer(){
		this.vector = new Vector<T>();
	}
	
	public synchronized void insert (T T){// insert to the BoundedBuffer
	try{
		while(vector.size() == maxSize) //While the BoundedBuffer is full the thread is waiting
			wait();
		vector.addElement(T);
		notifyAll();
		}catch(InterruptedException e){
			System.out.println("InterruptedException");
		}		
	}
	public synchronized void wake()
	{
		notifyAll();
	}
	public synchronized T extract(){// extract to the BoundedBuffer
		try{
			while(vector.isEmpty()&&(DayOver == false))
				wait();
			
			if(vector.size()==0)
				return null;
			T T = vector.elementAt(0);
			vector.remove(T);
			notifyAll();
			return T;
		}catch(InterruptedException e){
			System.out.println("InterruptedException");
			return null;
		}
	}
	public void setDayOver(boolean DayOver){
		this.DayOver = DayOver;
	}
}