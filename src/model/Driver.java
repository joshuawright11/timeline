/**
 * 
 */
package model;

/**
 * @author josh.l.wright
 *
 */
public class Driver {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new TimelineMaker()).start();
	}
}
