/**
 * 
 */
package backend;

import java.sql.SQLException;

/**
 * @author Josh Wright
 * Created: Jan 28, 2014
 * Package: backend
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBHelper helper = new DBHelper("timeline.db");
		try {
			helper.doSomthing("create table t2 (t1key INTEGER PRIMARY KEY,data TEXT,num double,timeEnter DATE);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
