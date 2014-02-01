/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Atomic;
import entities.Duration;
import entities.TLEvent;
import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
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
	/* (non-Javadoc)
	 * @see backend.DBHelperAPI#changeTimeline(backend.Timeline)
	 */
	@Override
	public Timeline changeTimeline(Timeline oldTimeline, Timeline newTimeline) {
		removeTimeline(oldTimeline);
		writeTimeline(newTimeline);
		return newTimeline;
	}
	/* (non-Javadoc)
	 * @see backend.DBHelperAPI#writeTimeline(backend.Timeline)
	 */
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
		statement.executeUpdate("INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate)" + " VALUES "
				+"('"+event.getName()+"',"+"'"+event.typeName()+"','"
				+event.getDate().toString()+"', NULL)"
				+";");
	}
	private void writeEvent(Duration event, String tlName) throws SQLException{
		statement.executeUpdate("INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate)" + " VALUES "
				+"('"+event.getName()+"',"+"'"+event.typeName()+"','"
				+event.getStartDate().toString()+"','"+event.getEndDate().toString()+"')"
				+";");
	}
	/* (non-Javadoc)
	 * @see backend.DBHelperAPI#removeTimeline(backend.Timeline)
	 */
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
	/* (non-Javadoc)
	 * @see backend.DBHelperAPI#getTimelines()
	 */
	@Override
	public Timeline[] getTimelines() {
		open();
		try {
			resultSet = statement.executeQuery("select name from sqlite_master where type = \"table\" "
					+ "and name != \"sqlite_sequence\";");
			int numTimelines = resultSet.getFetchSize();
			String[] timelineNames = new String[numTimelines];
			for(int i = 0; i<numTimelines;i++){ // Get all timeline names
				resultSet.next();
				timelineNames[i] = resultSet.getString(i);
			}
			Timeline[] timelines = new Timeline[numTimelines];
			for(int j = 0; j < numTimelines; j++){ // Get all timelines event arrays
				resultSet = statement.executeQuery("select * from "+timelineNames[j]+";");
				int numEvents = resultSet.getFetchSize();
				TLEvent[] events = new TLEvent[numEvents];
				for(int k = 0;k < numEvents;k++){ // Get all events for the event
					String name = resultSet.getString("eventName");
					String type = resultSet.getString("type");
					TLEvent event = null;
					if(type.equals("atomic")){
						Date startDate = resultSet.getDate("startDate");
						event = new Atomic(name, startDate);
					}else if(type.equals("duration")){
						Date startDate = resultSet.getDate("startDate");
						Date endDate = resultSet.getDate("endDate");
						event = new Duration(name,startDate,endDate);
					}else{
						System.out.println("YOU DONE MESSED UP.");
					}
					events[k] = event;
				}
				Timeline timeline = new Timeline(timelineNames[j], events);
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
