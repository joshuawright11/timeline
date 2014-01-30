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
public class DBHelper {
	private Connection connection = null;
	private ResultSet resultSet = null;  
    private Statement statement = null; 
	private String dbName;
	DBHelper(String dbName){
		this.dbName = dbName;
		try {
			connectToDB(this.dbName);
		} catch (SQLException e) {
			System.out.println("Couldn't connect to database.");
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println("Class not found. Check your classpath for JDBC.");
			e.printStackTrace();
		}
	}
	private void connectToDB(String dbName) throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:databases/"+this.dbName+"");
		statement = connection.createStatement();
	}
	public ResultSet doSomthing(String query) throws SQLException{
		resultSet = statement.executeQuery(query);
		return resultSet;
	}
}
