import java.sql.*;

public class DataBase {

	private Connection conn;
	private Statement stmt;

	//----------------------CONSTRUCTOR -----------------------------------------
	public DataBase(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test";
			conn=DriverManager.getConnection(url, "root", "root");
			stmt=conn.createStatement();
		
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) {  e.printStackTrace(); }
		

} // constructor
	
	
	
	
	
	//----------------------CREATE TABLES METHOD -----------------------------------------
	public void createTables(String Landings, String Takeoffs, boolean tablesExistAlready){
		try {
			
			if (tablesExistAlready){
				stmt.executeUpdate("Drop TABLE "+Landings);
				stmt.executeUpdate("Drop TABLE "+Takeoffs);
			}
			String table1="CREATE TABLE "+Landings+"(ID Varchar(10),Passengers int ,Cargo int, Cost int,isSecurityIssue bool, timeInAirfield int )"; // create table 1
			String table2="CREATE TABLE "+Takeoffs+"(ID Varchar(10),Passengers int,Destination Varchar(30), timeInAirfield int )"; //create table 2
		
			stmt.executeUpdate(table1);
			stmt.executeUpdate(table2);
			

		} catch (SQLException e) { e.printStackTrace(); }
		
	}//createTables
	
	//----------------------INSERT TO A TABLE METHOD -----------------------------------------
	public void insertToTable(String tableName, FlightDetails f ){
		String str="";
		
		if(tableName.equals("Landings"))
		{
			str="INSERT INTO " +tableName+" VALUES('"+f.getFlightID()+"','"+f.getNumOfPass()+"','"+f.getNumOfLugg()+"','"
					+f.getCost()+"','"+f.intIsSecurityIssue()+"','"+f.gettotaltime()+"')" ;
		}
		else
		{
			str="INSERT INTO " +tableName+" VALUES('"+f.getFlightID()+"','"+f.getNumOfPass()+"','"+f.getDestination()+"','"
					+f.gettotaltime()+"')" ;
		}	
			
			 try {
					stmt.executeUpdate(str);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
	}	
	
	//----------------------PULL OUT DATA METHOD -----------------------------------------
	public String extract(String tableName,String colName){
		ResultSet result=null;
		String ans="";
		String str="select * from " + tableName;
		try {
			
			result=stmt.executeQuery(str);
			result.next(); // move to the first line
			ans=result.getString(colName);
			
			
		} catch (SQLException e) { e.printStackTrace();	}
		
	return ans;
		
	}	
	}