/**
 * 
 */
package backend;

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
		helper.getTimelines();
	}

}
