import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class AirLine {

	private  UnboundedBuffer<ArrivalFlight> ArrivalFlightsLine; //Line Flights for ArrivalFlight.
	private  UnboundedBuffer<DepartureFlight> DepartureFlightLine; //Line Flights for DepartureFlight.
	private  UnboundedBuffer <ArrivalFlight> TechnicalLine; //Line Flights for TechnicalLine
	private  UnboundedBuffer <ArrivalFlight> SecuirityLine; //Line Flights for Security.
	private  UnboundedBuffer <FlightDetails> ManagmentLine; //Line Flights for management.
	private  UnboundedBuffer <ArrivalFlight> LogisticsLine; //Line Flights for Logistics.
	private  BoundedBuffer<ArrivalFlight> FuelLine; //Line Flights for Fuel.
	private  int counter;
	RunwayDirector r1,r2,r3;
	LogisticsCrew l1,l2,l3;
	SecurityMan s1,s2;
	FuelCrew f1, f2;
	TechnicalCrew t1;
	ManagementCrew m1;
	

	public AirLine (String FileFlights) 
	{
		ArrivalFlightsLine= new UnboundedBuffer<ArrivalFlight>();//Initial vectors
		DepartureFlightLine= new UnboundedBuffer<DepartureFlight>();
		TechnicalLine =  new UnboundedBuffer<ArrivalFlight>();
		SecuirityLine =  new UnboundedBuffer<ArrivalFlight>();
		ManagmentLine =  new UnboundedBuffer<FlightDetails>();
		LogisticsLine =  new UnboundedBuffer<ArrivalFlight>();
		FuelLine = new BoundedBuffer<ArrivalFlight>();
		readfromfile(FileFlights, "FlightsData");//send file in order to read from the file
		Thread r1 = new Thread( new RunwayDirector(ArrivalFlightsLine,ManagmentLine,TechnicalLine,LogisticsLine,DepartureFlightLine,counter));
		Thread t1 = new Thread( new TechnicalCrew(5,ManagmentLine,TechnicalLine,SecuirityLine,LogisticsLine));
		Thread r2 = new Thread(new RunwayDirector(ArrivalFlightsLine,ManagmentLine,TechnicalLine,LogisticsLine,DepartureFlightLine,counter));
		Thread r3 = new Thread(new RunwayDirector(ArrivalFlightsLine,ManagmentLine,TechnicalLine,LogisticsLine,DepartureFlightLine,counter));
		Thread l1 = new Thread(new LogisticsCrew(50,TechnicalLine,LogisticsLine,SecuirityLine));
		Thread l2 =new Thread(new LogisticsCrew(70,TechnicalLine,LogisticsLine,SecuirityLine));
		Thread l3 = new Thread(new LogisticsCrew(90,TechnicalLine,LogisticsLine,SecuirityLine));
		Thread s1 = new Thread(new SecurityMan("Zotar",5,SecuirityLine,FuelLine));
		Thread s2 = new Thread(new SecurityMan("Bahir",10,SecuirityLine,FuelLine));
		Thread f1 =new Thread(new FuelCrew(5000,FuelLine,TechnicalLine,ArrivalFlightsLine,ManagmentLine));
		Thread f2 = new Thread(new FuelCrew(10000,FuelLine,TechnicalLine,ArrivalFlightsLine,ManagmentLine));
		Thread m1 = new Thread(new ManagementCrew(counter,ManagmentLine,TechnicalLine,FuelLine,SecuirityLine,LogisticsLine));
		r1.start();
		r2.start();
		r3.start();
		l1.start();
		l2.start();
		l3.start();
		s1.start();
		s2.start();
		f1.start();
		f2.start();
		t1.start();
		m1.start();
	}

	protected String readfromfile(String  filename, String fileKind){ // Function that read from file.
		BufferedReader  in ;
		String str;
		try {
			in = new BufferedReader (new FileReader(filename+".txt"));
			str=in.readLine();
			while(in.ready()){// insert string to the vector until the file is empty;
				str=in.readLine();
				String [] arr = str.split("\\t");
				if(fileKind == "FlightsData"){
					createFlightsVector(arr);
				}

			}
			return str;
		}
		catch (IOException e) {// exception when we can't read the file
			System.out.println("Couldn't read file");
			return null;
		}
	}
	protected  void createFlightsVector(String[] arr){// create flight vector
		if((arr.length != 0)){
			counter++;
			if(arr[3].charAt(0)>='A' && arr[3].charAt(0)<='Z'){
				DepartureFlightLine.insert(new DepartureFlight(arr[0], Integer.parseInt(arr[1]),Integer.parseInt(arr[2]), arr[3]));
			}
			else{
				ArrivalFlightsLine.insert(new ArrivalFlight(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])));
			}
		}
		else
			return;

	}

}