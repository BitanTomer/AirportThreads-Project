import java.util.Vector;

public class UnboundedBuffer<T> {
	
	private Vector<T> vector;
	private boolean DayOver = false;
	
	public UnboundedBuffer(){
		this.vector = new Vector<T>();
	}
	
	public synchronized void insert(T item){// insert to the UnboundedBuffer
		vector.add(item);
		notifyAll();		
	}
	
	public synchronized T extract(){// extract to the UnboundedBuffer
		try{
			while(vector.isEmpty()&&(DayOver == false)) //While there is no object to extract thread is waiting
				wait();
			
			if(vector.size()==0)
				return null;
			T item = vector.elementAt(0);
			vector.remove(item);
			notifyAll();
			return item;
		}catch(InterruptedException e){
			System.out.println("InterruptedException");
			return null;
		}
	}
	public void setDayOver(boolean DayOver){
		this.DayOver = DayOver;
	}
	public boolean isEmpty(){
		return (vector.isEmpty());
	}
	public synchronized void wake()
	{
		notifyAll();
	}
}