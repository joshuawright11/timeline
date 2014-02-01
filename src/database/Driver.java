/**
 * 
 */
package database;

import java.sql.Date;

import entities.Atomic;
import entities.Duration;
import entities.TLEvent;
import entities.Timeline;

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
		TLEvent event1 = new Atomic("one", new Date(12));
		TLEvent event2 = new Duration("two", new Date(12),new Date(12));
		Timeline test1 = new Timeline("Test1");
		Timeline test2 = new Timeline("Test2");
		test1.addEvent(event1);
		test1.addEvent(event2);
		helper.removeTimeline(test1);
		helper.removeTimeline(test2);
		helper.writeTimeline(test1);
		helper.writeTimeline(test2);
		helper.removeTimeline(test1);
		helper.changeTimeline(test2, test1);
		helper.getTimelines();
		System.out.println("Finished!");
	}

}
