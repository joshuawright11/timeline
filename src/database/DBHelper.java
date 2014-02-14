/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Atomic;
import entities.Duration;
import entities.TLEvent;
import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 * Using ideas and very minimal code from http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
 *
 */
public class DBHelper implements DBHelperAPI{
	private Connection connection = null;
	private ResultSet resultSet = null;  
    private Statement statement = null; 
	private String dbName;
	public static final String ID = "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE";
	public DBHelper(String dbName){
		this.dbName = dbName;
	}
	private void open(){
		try {
			openHelper(this.dbName);
		} catch (SQLException e) {
			System.out.println("Couldn't connect to database.");
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println("Class not found. Check your classpath for JDBC.");
			e.printStackTrace();
		}
	}
	private void openHelper(String dbName) throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:databases/"+this.dbName+"");
		statement = connection.createStatement();
	}
	private void close(){
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Timeline changeTimeline(Timeline oldTimeline, Timeline newTimeline) {
		removeTimeline(oldTimeline);
		writeTimeline(newTimeline);
		return newTimeline;
	}

	@Override
	public boolean writeTimeline(Timeline timeline) {
		String tlName = timeline.getName(); 
		open();
		try {
			statement.executeUpdate("CREATE TABLE "+tlName
					+" ("+ID+",eventName TEXT, type TEXT, startDate DATETIME, endDate DATETIME);");
		} catch (SQLException e) {
			if(e.getMessage().contains("already exists")) {
				System.out.println("A timeline with that name already exists!");
				return false;
			}
			e.printStackTrace();
		}
		for(TLEvent event : timeline.getEvents()){
			try {
				if(event instanceof Atomic){
					writeEvent((Atomic)event, tlName);
				}else if(event instanceof Duration){
					writeEvent((Duration)event, tlName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close();
		return true;
	}
	private void writeEvent(Atomic event, String tlName) throws SQLException{
		String INSERT_ATOMIC = "INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate) VALUES "
				+"(?,?,?,NULL);";
		PreparedStatement pstmt = connection.prepareStatement(INSERT_ATOMIC);
		pstmt.setString(1, event.getName());
		pstmt.setString(2, event.typeName());
		pstmt.setDate(3, event.getDate());
		pstmt.executeUpdate();
	}
	private void writeEvent(Duration event, String tlName) throws SQLException{
		String INSERT_DURATION = "INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate) VALUES "
				+"(?,?,?,?);";
		PreparedStatement pstmt = connection.prepareStatement(INSERT_DURATION);
		pstmt.setString(1, event.getName());
		pstmt.setString(2, event.typeName());
		pstmt.setDate(3, event.getStartDate());
		pstmt.setDate(4, event.getEndDate());
		pstmt.executeUpdate();
	}

	@Override
	public boolean removeTimeline(Timeline timeline) {
		open();
		try {
			statement.executeUpdate("DROP TABLE IF EXISTS'"+timeline.getName()+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return true;
	}

	@Override
	public Timeline[] getTimelines() {
		open();
		try {
			resultSet = statement.executeQuery("select name from sqlite_master where type = \"table\" "
					+ "and name != \"sqlite_sequence\";");
			ArrayList<String> timelineNames = new ArrayList<String>();
			int numTimelines = 0;
			while(resultSet.next()){ // Get all timeline names
				numTimelines ++;
				timelineNames.add(resultSet.getString(numTimelines));
			}
			Timeline[] timelines = new Timeline[numTimelines];
			for(int j = 0; j < numTimelines; j++){ // Get all timelines event arrays
				System.out.println("Getting timeline.");
				resultSet = statement.executeQuery("select * from "+timelineNames.get(j)+";");
				//int numEvents = resultSet.getFetchSize(); //doesn't work
				ArrayList<TLEvent> events = new ArrayList<TLEvent>();
				int numEvents = 0;
				while(resultSet.next()){ // Get all events for the event
					System.out.println("In event loop.");
					numEvents++;
					String name = resultSet.getString("eventName");
					String type = resultSet.getString("type");
					TLEvent event = null;
					if(type.equals("atomic")){
						Date startDate = resultSet.getDate("startDate");
						event = new Atomic(name, "", startDate); // TODO Get category from database.
					}else if(type.equals("duration")){
						Date startDate = resultSet.getDate("startDate");
						Date endDate = resultSet.getDate("endDate");
						event = new Duration(name, "", startDate,endDate); // TODO Get category from database.
					}else{
						System.out.println("YOU DONE MESSED UP.");
					}
					events.add(event);
				}
				Timeline timeline = new Timeline(timelineNames.get(j), events.toArray(new TLEvent[numEvents]));
				timelines[j] = timeline;
			}
			return timelines;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return null;
	}

}
