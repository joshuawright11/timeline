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
import entities.Timeline.AxisLabel;

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
	private static final String ID = "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE"; // unique id for android
	public DBHelper(String dbName){
		this.dbName = dbName;
		open();
		try {
			statement.executeUpdate("CREATE TABLE timeline_info ("+ID+", timelineName TEXT, axisLabel TEXT);");
		} catch (SQLException e) {
			if(e.getMessage().contains("already exists")) {
				//it has already been created, no issues
			}else
				e.printStackTrace();
		}
		close();
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
		connection = DriverManager.getConnection("jdbc:sqlite:"+this.dbName+"");
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
					+" ("+ID+",eventName TEXT, type TEXT, startDate DATETIME, endDate DATETIME, category TEXT);");
			writeAxisLabel(tlName, timeline.getAxisLabel());
		} catch (SQLException e) {
			if(e.getMessage().contains("already exists")) {
				System.out.println("A timeline with that name already exists!");
				return false;
			}
			e.printStackTrace();
		}
		if(timeline.getEvents() == null)
			return false;
		for(TLEvent event : timeline.getEvents()){
			try {
				if(event instanceof Atomic){
					writeEvent((Atomic)event, tlName);
				}else if(event instanceof Duration){
					writeEvent((Duration)event, tlName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (NullPointerException e){
				System.out.println("Nothing!");
			}
		}
		close();
		return true;
	}
	private void writeAxisLabel(String timelineName, AxisLabel axisLabel) throws SQLException{
		
		String INSERT_LABEL = "INSERT INTO timeline_info (timelineName, axisLabel) VALUES "
				+"(?,?);";
		PreparedStatement pstmt = connection.prepareStatement(INSERT_LABEL);
		pstmt.setString(1, timelineName);
		pstmt.setString(2, axisLabel.toString());
		pstmt.executeUpdate();
	}
	private void removeAxisLabel(String timelineName) throws SQLException{
		String REMOVE_LABEL = "DELETE FROM timeline_info WHERE timelineName = ?;";
		PreparedStatement pstmt = connection.prepareStatement(REMOVE_LABEL);
		pstmt.setString(1, timelineName);
		pstmt.executeUpdate();
	}
	private int getAxisLabel(String timelineName) throws SQLException{
		String SELECT_LABEL = "SELECT axisLabel FROM timeline_info WHERE timelineName = ?;";
		PreparedStatement pstmt = connection.prepareStatement(SELECT_LABEL);
		pstmt.setString(1, timelineName);
		resultSet = pstmt.executeQuery();
		String labelName = resultSet.getString(1);
		switch(labelName){
		case "DAYS":
			return 0;
		case "MONTHS":
			return 2;
		case "YEARS":
			return 3;
		default:
			return 3;
		}
	}
	private void writeEvent(Atomic event, String tlName) throws SQLException{
		String INSERT_ATOMIC = "INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate,category) VALUES "
				+"(?,?,?,NULL,?);";
		PreparedStatement pstmt = connection.prepareStatement(INSERT_ATOMIC);
		pstmt.setString(1, event.getName());
		pstmt.setString(2, event.typeName());
		pstmt.setDate(3, event.getDate());
		pstmt.setString(4, event.getCategory());
		pstmt.executeUpdate();
	}
	private void writeEvent(Duration event, String tlName) throws SQLException{
		String INSERT_DURATION = "INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate,category) VALUES "
				+"(?,?,?,?,?);";
		PreparedStatement pstmt = connection.prepareStatement(INSERT_DURATION);
		pstmt.setString(1, event.getName());
		pstmt.setString(2, event.typeName());
		pstmt.setDate(3, event.getStartDate());
		pstmt.setDate(4, event.getEndDate());
		pstmt.setString(5, event.getCategory());
		pstmt.executeUpdate();
	}

	@Override
	public boolean removeTimeline(Timeline timeline) {
		open();
		try {
			statement.executeUpdate("DROP TABLE IF EXISTS'"+timeline.getName()+"';");
			removeAxisLabel(timeline.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return true;
	}

	@Override
	public Timeline[] getTimelines() { //this could probably be split up into methods
		open();
		try {
			resultSet = statement.executeQuery("select name from sqlite_master where type = \"table\" "
					+ "and name != \"sqlite_sequence\" and name != \"timeline_info\";");
			ArrayList<String> timelineNames = new ArrayList<String>();
			int numTimelines = 0;
			while(resultSet.next()){ // Get all timeline names
				numTimelines ++;
				timelineNames.add(resultSet.getString(1));
			}
			Timeline[] timelines = new Timeline[numTimelines];
			for(int j = 0; j < numTimelines; j++){ // Get all timelines event arrays
				resultSet = statement.executeQuery("select * from "+timelineNames.get(j)+";");
				ArrayList<TLEvent> events = new ArrayList<TLEvent>();
				int numEvents = 0;
				while(resultSet.next()){ // Get all events for the event
					numEvents++;
					String name = resultSet.getString("eventName");
					String type = resultSet.getString("type");
					TLEvent event = null;
					if(type.equals("atomic")){
						String category = resultSet.getString("Category");
						Date startDate = resultSet.getDate("startDate");
						event = new Atomic(name, category, startDate); // TODO Get category from database.
					}else if(type.equals("duration")){
						String category = resultSet.getString("Category");
						Date startDate = resultSet.getDate("startDate");
						Date endDate = resultSet.getDate("endDate");
						event = new Duration(name, category, startDate,endDate); // TODO Get category from database.
					}else{
						System.out.println("YOU DONE MESSED UP.");
					}
					events.add(event);
				}
				int label = getAxisLabel(timelineNames.get(j));
				Timeline timeline = new Timeline(timelineNames.get(j), events.toArray(new TLEvent[numEvents]), label);
				timelines[j] = timeline;
			}
			close();
			return timelines;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return null;
	}

}
