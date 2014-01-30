/**
 * 
 */
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	DBHelper(String dbName){
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
			connection.close();
			statement.close();
			resultSet.close();
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
	public Timeline writeTimeline(Timeline timeline) {
		String tlName = timeline.getName(); 
		open();
		try {
			statement.executeUpdate("CREATE TABLE "+tlName
					+" ("+ID+",eventName TEXT, type TEXT, startDate DATETIME, endDate DATETIME);");
		} catch (SQLException e) {
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
		return timeline;
	}
	private void writeEvent(Atomic event, String tlName) throws SQLException{
		statement.executeUpdate("INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate)" + " VALUES "
				+"'"+event.getName()+"',"+"'"+event.typeName()+"','"
				+event.getDate().toString()+"', NULL)"
				+";");
	}
	private void writeEvent(Duration event, String tlName) throws SQLException{
		statement.executeUpdate("INSERT INTO "+tlName
				+" (eventName,type,startDate,endDate)" + " VALUES "
				+"'"+event.getName()+"',"+"'"+event.typeName()+"','"
				+event.getStartDate().toString()+"','"+event.getEndDate().toString()+"'+)"
				+";");
	}
	/* (non-Javadoc)
	 * @see backend.DBHelperAPI#removeTimeline(backend.Timeline)
	 */
	@Override
	public boolean removeTimeline(Timeline timeline) {
		open();
		try {
			statement.executeUpdate("DROP TABLE IF EXISTS'"+timeline.getName()+"'");
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
		//TODO WRITE THIS
		close();
		return null;
	}

}
