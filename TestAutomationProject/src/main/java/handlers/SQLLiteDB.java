package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;

public class SQLLiteDB {

	public SQLLiteDB() {
		// TODO Auto-generated constructor stub
	}

	public void initRunDataDB (Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);
			//statement.executeUpdate("drop table if exists run_data");
			statement.executeUpdate("create table if not exists run_data (" 
				+"sid string,"
				+"path string,"
				+"steps integer," 
				+"status string,"
				+"runby string,"
				+"timestamp DATETIME NOT NULL DEFAULT "
				+"(strftime('%Y-%m-%d %H:%M:%f', 'now', 'localtime')))");
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public Connection openRunHistoryDB() {
		Connection con = null;
		try {		
			Class.forName("org.sqlite.JDBC");			
			con = DriverManager.getConnection("jdbc:sqlite:.\\src\\test\\resources\\RunHistoryDB.db");
			initRunDataDB (con);			
		} catch (Exception e) {e.printStackTrace();}
		return con;
	}
	
	public ResultSet selectRunHistoryTbl(Connection con, String query) {
		ResultSet rs = null;
		try {
			Statement statement = con.createStatement();
			rs = statement.executeQuery(query);
		} catch (Exception e) {e.printStackTrace();}
		return rs;
	}
	
	public void addRunHistory(Connection con, List<String> data) {
		
		try {
			
			Statement st = con.createStatement();		
			st.executeUpdate(
					"insert into run_data (sid, path, steps, status, runby)" 
						+"values('"
						+data.get(0)+"','"
						+data.get(1)+"','"
						+data.get(2)+"','"
						+data.get(3)+"','"
						+data.get(4)+"')");						
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	public void closeRunHistoryDB(Connection con) {
		try {
			if (con != null) con.close();
		} catch (Exception e) {e.printStackTrace();}
	}
/*
	public static void main(String[] args) {

		ResultSet rs = null;
		Connection con = openRunHistoryDB();		
				
		List<String> data = new ArrayList<String>(6);
		data.add("test1");
		data.add("test2");
		data.add("0");
		data.add("test3");
		data.add("test4");

		addRunHistory(con, data);
		
		try {						
			rs = selectRunHistoryTbl(con, "select * from run_data");			
			System.out.println("sid"+"\t"
					+"path"+ "\t"
					+"steps"+"\t"
					+"status"+"\t"
					+"runby"+"\t"
					+"timestamp");
			
			while (rs.next()) {
				// read the result set
				System.out.println(rs.getString("sid")+"\t"
						+rs.getString("path")+"\t"
						+rs.getInt("steps")+"\t"
						+rs.getString("status")+"\t"
						+rs.getString("runby")+"\t"
						+rs.getString("timestamp"));
			}
						
		} catch (Exception e) {e.printStackTrace();}
		
		closeRunHistoryDB(con);		 		
	}*/
}
